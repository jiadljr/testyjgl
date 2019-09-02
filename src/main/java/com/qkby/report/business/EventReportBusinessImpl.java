package com.qkby.report.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import com.qkby.analysis.dao.EventInfoDayDao;
import com.qkby.analysis.dao.EventInfoSkyDao;
import com.qkby.analysis.entity.EventInfoDay;
import com.qkby.analysis.entity.EventInfoSky;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.jchart.ImageFTL;
import com.qkby.jchart.ReportChart;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysServiceType;

@Service("EventReportBusiness")
public class EventReportBusinessImpl implements EventReportBusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	SysServiceTypeDao sysServiceTypeDao;
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	EventInfoDayDao eventInfoDayDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;
	
	@Override
	public Map<String, Object> getDataMap(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> dataMap = eventInfoDao.selectLevelCountByTime(paramMap);//将查询到的等级数量放在map中
		//事件分布
		List<Map<String, Object>> dayMap = eventInfoDao.selectApplyCountByTime(paramMap);
		DefaultCategoryDataset eventDataset = new DefaultCategoryDataset();
		if (dayMap != null && dayMap.size()>0) {
			for(Map<String, Object> day : dayMap){
				eventDataset.setValue((long)day.get("amount"), "事件统计", String.valueOf(day.get("dates")));
			}
			JFreeChart lineChart = ReportChart.getLineChart(eventDataset, "", "", "事件分布图");
			String eventImage = ImageFTL.getImage(lineChart);
			dataMap.put("eventImage", eventImage);
		}
		//事件概况
		List<SysServiceType> serTypeList = sysServiceTypeDao.selectServicePercentByTime(paramMap);//服务类型top5统计
		dataMap.put("serTypeList", serTypeList);
		DefaultPieDataset serDatase = new DefaultPieDataset();
		if (serTypeList != null && serTypeList.size()>0) {
			long allCount = (long) dataMap.get("allCount");
			for(SysServiceType serType : serTypeList){
				allCount-= Integer.valueOf(serType.getExtend1());
				serDatase.setValue(serType.getName(), Double.valueOf(serType.getExtend1()));
			}
			serDatase.setValue("其他", allCount);
			JFreeChart chart = ReportChart.getPieChart3D(serDatase, "主要服务分类分布图");
			String serImage = ImageFTL.getImage(chart);
			dataMap.put("serImage", serImage);
		}
		List<Map<String, Object>> deptEventList = sysDeptInfoDao.selectLevelByDept(paramMap);
		dataMap.put("deptEventList", deptEventList);
		
		//事件详情
		paramMap.remove("startPos");
		paramMap.remove("pageSize");
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.EVENT_DEAL_PENDING);
		statusList.add(ConstantMenu.EVENT_ANALYSIS_PENDING);
		statusList.add(ConstantMenu.EVENT_ANALYSIS_FINISH);
		paramMap.put("eventStatus",statusList);
		List<Map<String, Object>> eventList = eventInfoDao.selectAnaListByExample(paramMap);//事件集合
		List<Map<String, Object>> dealList = eventInfoDealDao.selectSureList(paramMap);//事件的所有处理单集合
		List<Map<String, Object>> newEventList = new ArrayList<Map<String, Object>>();//事件的所有处理单集合
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		//最后组合成的集合
		if (eventList != null && eventList.size()>0) {
			for (int i = 0; i < eventList.size(); i++) {
				Map<String, Object> eventMap = new HashMap<String, Object>();
				Map<String, Object> event = eventList.get(i);
				String status = "";
				int eventStatus = Integer.valueOf((String)event.get("eventStatus"));
				if (eventStatus== ConstantMenu.EVENT_TWENTY) {
					status = "待受理";
				}else if (eventStatus == ConstantMenu.EVENT_DEAL_PENDING) {
					status = "待处理";
				}else if (eventStatus == ConstantMenu.EVENT_ANALYSIS_PENDING) {
					status = "待评价";
				}else if (eventStatus == ConstantMenu.EVENT_ANALYSIS_FINISH) {
					status = "已完成";
				}
				String level = "";
				Object  eventLevel= event.get("eventLevel");
				if (eventLevel != null && !"".equals(eventLevel)) {
					if ((int)eventLevel == ConstantMenu.EVENT_ONE) {
						level = "一级";
					}else if ((int)eventLevel == ConstantMenu.EVENT_TWO) {
						level = "二级";
					}else if ((int)eventLevel == ConstantMenu.EVENT_THREE) {
						level = "三级";
					}else if ((int)eventLevel == ConstantMenu.EVENT_FOUR) {
						level = "四级";
					}
				}
				event.put("applyTime",sf.format((Date)event.get("dateCreate")));//格式化申告事件
				event.put("level",level);//事件等级
				event.put("status",status);//事件状态
				eventMap.put("event", event);
				List<Map<String, Object>> newDealList = new ArrayList<Map<String, Object>>();//事件对应所有处理单
				if (dealList != null && dealList.size()>0) {
					for (int j = 0; j < dealList.size(); j++) {
						Map<String, Object> deal = dealList.get(j);
						if ((int)event.get("id") == (int)deal.get("id_event")) {
							Object dateDeal = deal.get("dateDeal");
							if ( dateDeal!= null && !"".equals(dateDeal)) {
								deal.put("dateDeal", sf.format((Date)dateDeal));//格式化处理时间
							}
							String dealResult = null;
							if ((int)deal.get("dealStatus") == 2) {
								dealResult="解决";
							}else if ((int)deal.get("dealStatus") == 3) {
								dealResult="转派"+(String)deal.get("reinName");
							}
							//资产
							int dealId = (int)deal.get("id");
							List<SysAssetsInfo> assetsList = sysAssetsInfoDao.selectAssetsByDealId(dealId);
							if (assetsList != null && assetsList.size()>0) {
								for (int k = 0; k < assetsList.size(); k++) {
									assetsList.get(k).setExtend1(assetsList.get(k).getAsIp()+"-"+assetsList.get(k).getName());
								}
							}
							deal.put("assetsList", assetsList);
							deal.put("dealResult", dealResult);//处理结果
							newDealList.add(deal);
						}
					}
					eventMap.put("newDealList", newDealList);
				}
				newEventList.add(eventMap);
			}
			dataMap.put("newEventList", newEventList);
		}
		return dataMap;
	}
	
	
}
