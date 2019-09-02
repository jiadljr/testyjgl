package com.qkby.connector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qkby.constant.ConstantMenu;
import com.qkby.proj.dao.ProjInfoDao;
import com.qkby.proj.dao.ProjTaskDao;
import com.qkby.proj.entity.ProjectInformation;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserPostInfoDao;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserPostInfo;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.Utils;
import com.qkby.work.dao.WorkPlanDao;
import com.qkby.work.entity.WorkPlanInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class PortInsertUserDept {

	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	SysUserPostInfoDao sysUserPostInfoDao;
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;
	@Resource
	ProjInfoDao projInfoDao;
	@Resource
	ProjTaskDao projTaskDao;
	@Resource
	WorkPlanDao workPlanDao;

	JSONArray childMenu = null;
	Map<String, Object> deMap = null;
	Map<String, Object> paramMap = null;

	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(cron = "0 */1 * * * ?")//五秒
	public void send() throws Exception {
		childMenu = new JSONArray();
		deMap = new HashMap<String, Object>();
		updatePlanType();
		updateProjStatus(paramMap);
		updateTaskStatus(paramMap);
		 insertDeptInfo();
		 updateDeptPid();
		 insertUserInfo();
		 updateUserDeptId();
	}

	public JSONArray selectOADept(String parentId) {
		String test = HttpUrl.test("http://172.16.16.3:9009/rest?method=sys.businessunit.getlist&sessionKey=2ec00cf2-a484-4136-8fef-e2a2719c5ed6&ParentId="+parentId+"&pageSize=1000&pageNumber=1");
		JSONObject jsonObject = JSONObject.fromObject(test);
		JSONArray jsonArray = (JSONArray) jsonObject.get("listData");
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonMenu = (JSONObject) jsonArray.get(i);
				String dateType = jsonMenu.getString("datatype");
				if(dateType.equals("systemUser")){
					return null;
				}
				String menuId = jsonMenu.getString("businessUnitId");
				String pId = jsonMenu.getString("parentBusinessUnitId");
				if ("".equals(pId)) {
					jsonMenu.put("level", 1);
				} else if (pId.equals(parentId)) {
					JSONObject object = (JSONObject) deMap.get(pId);
					int level = (int) object.get("level");
					jsonMenu.put("level", ++level);
				}
				deMap.put(menuId, jsonMenu);
				childMenu.add(jsonMenu);
				selectOADept(menuId);
			}
		}
		return childMenu;
	}

	public void insertDeptInfo() throws Exception {
		JSONArray jsonArray = selectOADept("");
		List<SysDeptInfo> seleDeptInfo = sysDeptInfoDao.seleDeptInfo();
		Map<String, SysDeptInfo> deptMap = new HashMap<String, SysDeptInfo>();
		String code = "";
		for (SysDeptInfo sysDeptInfo : seleDeptInfo) {
			code += sysDeptInfo.getCode() + ",";
			deptMap.put(sysDeptInfo.getCode(), sysDeptInfo);
		}
		String[] codeSplit = null;
		if (!"".equals(code)) {
			code = code.substring(0, code.length() - 1);
			codeSplit = code.split(",");
		}
		if (jsonArray == null) {
			return;
		}
		if (codeSplit == null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject dept = (JSONObject) jsonArray.get(i);
				String businessUnitId = (String) dept.get("businessUnitId");
				String name = (String) dept.get("name");
				String parentBusinessUnitId = (String) dept.get("parentBusinessUnitId");
				int level = (int) dept.get("level");
				SysDeptInfo sysDept = new SysDeptInfo();
				sysDept.setExtend1(parentBusinessUnitId);
				sysDept.setName(name);
				sysDept.setCode(businessUnitId);
				sysDept.setLevel(level);
				sysDeptInfoDao.insert(sysDept);
			}
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject dept = (JSONObject) jsonArray.get(i);
			String businessUnitId = (String) dept.get("businessUnitId");
			String name = (String) dept.get("name");
			String parentBusinessUnitId = (String) dept.get("parentBusinessUnitId");
			int level = (int) dept.get("level");
			if (HttpUrl.useList(codeSplit, businessUnitId)) {
				if (deptMap.get(businessUnitId).getCode().equals(businessUnitId)) {
					if (deptMap.get(businessUnitId).getName().equals(name)) {
						if (deptMap.get(businessUnitId).getExtend1().equals(parentBusinessUnitId)) {
							if (deptMap.get(businessUnitId).getLevel() == level) {
								continue;
							} else {
								deptMap.get(businessUnitId).setLevel(level);
								SysDeptInfo sysDept = deptMap.get(businessUnitId);
								sysDeptInfoDao.updateByPrimaryKeySelective(sysDept);
								continue;
							}
						} else {
							deptMap.get(businessUnitId).setExtend1(parentBusinessUnitId);
							SysDeptInfo sysDept = deptMap.get(businessUnitId);
							sysDeptInfoDao.updateByPrimaryKeySelective(sysDept);
							continue;
						}
					} else {
						deptMap.get(businessUnitId).setName(name);
						SysDeptInfo sysDept = deptMap.get(businessUnitId);
						sysDeptInfoDao.updateByPrimaryKeySelective(sysDept);
						continue;
					}
				}
			} else {
				SysDeptInfo sysDept = new SysDeptInfo();
				sysDept.setExtend1(parentBusinessUnitId);
				sysDept.setCode(businessUnitId);
				sysDept.setName(name);
				sysDept.setLevel(level);
				sysDeptInfoDao.insert(sysDept);
			}
		}

	}

	public void updateDeptPid() throws Exception {
		List<SysDeptInfo> seleDeptInfo = sysDeptInfoDao.seleDeptInfo();
		Map<String, SysDeptInfo> map = new HashMap<String, SysDeptInfo>();
		for (SysDeptInfo sysDeptInfo : seleDeptInfo) {
			map.put(sysDeptInfo.getCode(), sysDeptInfo);
		}
		for (SysDeptInfo dept : seleDeptInfo) {
			String extend1 = dept.getExtend1();
			if ("".equals(extend1)) {
				continue;
			}
			if (map.get(extend1) != null) {
				dept.setParentId(map.get(extend1).getId());
				sysDeptInfoDao.updateByPrimaryKeySelective(dept);
			} else {
				dept.setDs(1);
				sysDeptInfoDao.updateByPrimaryKeySelective(dept);
			}
		}
	}

	public void insertUserInfo() throws Exception {
		String test = HttpUrl.test("http://172.16.16.3:9009/rest?method=sys.users.search&sessionKey=2ec00cf2-a484-4136-8fef-e2a2719c5ed6&pageSize=1000&pageNumber=1");
		JSONObject jsonObject = JSONObject.fromObject(test);
		JSONArray jsonArray = (JSONArray) jsonObject.get("listData");
		if (jsonArray == null) {
			return;
		}
		Map<String, SysUserInfo> map = new HashMap<String, SysUserInfo>();
		List<SysUserInfo> seleUserAll = sysUserInfoDao.seleUserAll();
		String code = "";
		for (SysUserInfo seleUser : seleUserAll) {
			code += seleUser.getCode() + ",";
			map.put(seleUser.getCode(), seleUser);
		}
		code = code.substring(0, code.length() - 1);
		String[] codeSplit = code.split(",");
		for (int j = 0; j < jsonArray.size(); j++) {
			JSONObject object = (JSONObject) jsonArray.get(j);
			String userName = (String) object.get("userName");
			String fullName = (String) object.get("fullName");
			String businessUnitId = (String) object.get("businessUnitId");
			if (HttpUrl.useList(codeSplit, userName)) {
				if (map.get(userName).getCode().equals(userName)) {
					if (map.get(userName).getName().equals(fullName)) {
						if (map.get(userName).getExtend1().equals(businessUnitId)) {
							continue;
						} else {
							map.get(userName).setExtend1(businessUnitId);
							SysUserInfo sysUserInfo = map.get(userName);
							sysUserInfoDao.updateByPrimaryKeySelective(sysUserInfo);
							continue;
						}
					} else {
						map.get(userName).setName(fullName);
						SysUserInfo sysUserInfo = map.get(userName);
						sysUserInfoDao.updateByPrimaryKeySelective(sysUserInfo);
						continue;
					}
				}
			} else {
				SysUserInfo sysUserInfo = selectUserPassword();
				sysUserInfo.setCode(userName);
				sysUserInfo.setName(fullName);
				sysUserInfo.setExtend1(businessUnitId);
				sysUserInfoDao.insert(sysUserInfo);
				Integer id = sysUserInfo.getId();
				SysUserPostInfo sysUserPost = new SysUserPostInfo();
				sysUserPost.setIdUser(id);
				sysUserPost.setIdPost(ConstantMenu.USER_POST_NINE);
				sysUserPostInfoDao.insert(sysUserPost);
				SysUserRoleInfo sysUserRoleInfo = new SysUserRoleInfo();
				sysUserRoleInfo.setIdUser(id);
				sysUserRoleInfo.setIdRole(ConstantMenu.OPS_OPR);
				sysUserRoleInfoDao.insert(sysUserRoleInfo);
			}
		}
	}

	public void updateUserDeptId() throws Exception {
		List<SysDeptInfo> seleDeptInfo = sysDeptInfoDao.seleDeptInfo();
		String code = "";
		for (int i = 0; i < seleDeptInfo.size(); i++) {
			SysDeptInfo dept = seleDeptInfo.get(i);
			code += dept.getCode() + ",";
		}
		code = code.substring(0, code.length() - 1);
		String[] codeSplit = code.split(",");
		List<SysUserInfo> seleUserAll = sysUserInfoDao.seleUserAll();
		for (int i = 0; i < seleUserAll.size(); i++) {
			SysUserInfo user = seleUserAll.get(i);
			String extend1 = user.getExtend1();
			if (HttpUrl.useList(codeSplit, extend1)) {
				int depId = sysDeptInfoDao.selectDeptByCode(extend1);
				Integer id = user.getId();
				Integer idDept = user.getIdDept();
				if (idDept == null) {
					SysUserInfo sysUserInfo = new SysUserInfo();
					sysUserInfo.setIdDept(depId);
					sysUserInfo.setId(id);
					sysUserInfoDao.updateByPrimaryKeySelective(sysUserInfo);
				} else if (idDept != depId) {
					SysUserInfo sysUserInfo = new SysUserInfo();
					sysUserInfo.setIdDept(depId);
					sysUserInfo.setId(id);
					sysUserInfoDao.updateByPrimaryKeySelective(sysUserInfo);
				}
			}
		}
	}

	public SysUserInfo selectUserPassword() {
		SysUserInfo sysUserInfo = new SysUserInfo();
		// HttpSession session = request.getSession();
		// String basePath =
		// session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String Init_PWD = resource.getString("Init_PWD");
		String pwdAging = resource.getString("pwdAging");
		sysUserInfo.setPassword(Utils.crypt(Init_PWD));
		sysUserInfo.setCreateDate(new Date());
		// 密码过期时间
		int pastPass = Integer.parseInt(pwdAging);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, pastPass);
		sysUserInfo.setPwdExpiredDate(calendar.getTime());
		sysUserInfo.setDs(ConstantMenu.LOCK_ZERO);
		sysUserInfo.setArrangeProxy(ConstantMenu.LOCK_ONE);
		return sysUserInfo;
	}
	/**
	 * 修改项目状态
	 * 2018年3月11日 下午7:09:11
	 * @author 李帅
	 * void
	 * @throws Exception 
	 */
	public void updateProjStatus(Map<String, Object> paramMap) throws Exception{
		System.out.println(new Date());
		//1.查询所有项目的集合，创建一个空的integer集合，用来装要修改状态的id
		//2.循环，如果项目结束时间的毫秒值<当前时间的毫秒值，即为延期，放到集合中。
		//3.最后循环集合，将集合中所有的项目进行状态的修改
		//4.注意，查询的时候要查询所有，因为有可能项目修改时间的话，要将状态修改为正常
		paramMap = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.PROJ_NORMAL);
		paramMap.put("statusList", statusList);
		paramMap.put("pf", 0);
		List<ProjectInformation> projList = projInfoDao.selectProjInfoByMap(paramMap);//过滤所有完成的，并且不是延期状态的项目
		Date date = new Date();
		List<Integer> updatePtList = new ArrayList<Integer>();//修改成超期的集合
		for(ProjectInformation proj:projList){
			if (proj.getDateEnd().getTime() < date.getTime()) {
					updatePtList.add(proj.getId());
			}
		}
		for (int i = 0; i < updatePtList.size(); i++) {
			ProjectInformation projInfo = new ProjectInformation();
			projInfo.setId(updatePtList.get(i));
			projInfo.setPf(1);
			projInfoDao.updateProjPf(projInfo);
		}
	}
	/**
	 * 修改任务状态
	 * 2018年3月11日 下午7:09:11
	 * @author 李帅
	 * void
	 * @throws Exception 
	 */
	public void updateTaskStatus(Map<String, Object> paramMap) throws Exception{
		paramMap = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.TASK_NORMAL);
		paramMap.put("statusList", statusList);
		paramMap.put("pf", 0);
		List<ProjectTask> taskList = projTaskDao.selectProjTaskInfoByMap(paramMap);
		Date date = new Date();
		List<Integer> updatePfList = new ArrayList<Integer>();
		for(ProjectTask task : taskList){
			if(task.getDateEnd().getTime()<date.getTime()){
					updatePfList.add(task.getId());
			}
		}
		
		for (int i = 0; i < updatePfList.size(); i++) {
			ProjectTask projTask= new ProjectTask();
			
			projTask.setId(updatePfList.get(i));
			projTask.setPf(1);
			projTaskDao.updateTaskPf(projTask);
		}
	}
	public void updatePlanType(){
		WorkPlanInfo workPlanInfo = new WorkPlanInfo();
		List<WorkPlanInfo> selectTimePlanType = workPlanDao.selectTimePlanType();
		Date date = new Date();
		for (WorkPlanInfo workPlan : selectTimePlanType) {
			Date planBeginTime = workPlan.getPlanBeginTime();
			int id = workPlan.getId();
			int planState = workPlan.getPlanState();
			if(planState == 2){
				if (planBeginTime.before(date)){
					workPlanInfo.setPlanState(ConstantMenu.PLAN_ONE_STATE);  
					workPlanInfo.setId(id);
					workPlanDao.updatePlanType(workPlanInfo);
					}
			}
		}
	}
}
