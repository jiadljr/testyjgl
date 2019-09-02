package com.qkby.contract.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.constant.ConstantMenu;
import com.qkby.contract.business.ContractFileBusiness;
import com.qkby.contract.business.ContractInfoBusiness;
import com.qkby.contract.entity.ContractFile;
import com.qkby.contract.entity.ContractInfo;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.Utils;
import com.qkby.work.entity.WorkReportFileInfo;

@Controller
public class ContractInfoController {
	
	@Resource
	private ContractInfoBusiness contractInfoBusiness;
	@Resource
	private SysFileInfoBusiness sysFileInfoBusiness;
	@Resource
	private ContractFileBusiness contractFileBusiness;
	/**
	 * 
	 * @Description (查询合同信息)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectContractAll.do")
	@ResponseBody
	public Map<String,Object> selectContractAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pMap = new HashMap<String,Object>();
		String firstPartyUnit = request.getParameter("firstPartyUnit");
		if("".equals(firstPartyUnit)){
			firstPartyUnit = null;
		}
		String signTimeStart = request.getParameter("signTimeStart");
		if("".equals(signTimeStart)){
			signTimeStart = null;
		}
		String signTimeEnd = request.getParameter("signTimeEnd");
		if("".equals(signTimeEnd)){
			signTimeEnd = null;
		}
		String contractTypeCode = request.getParameter("contractTypeCode");
		if("".equals(contractTypeCode)){
			contractTypeCode = null;
		}
		String contractMoneyEnd = request.getParameter("contractMoneyEnd");
		if("".equals(contractMoneyEnd)){
			contractMoneyEnd = null;
		}
		String contractMoneyStrat = request.getParameter("contractMoneyStrat");
		if("".equals(contractMoneyStrat)){
			contractMoneyStrat = null;
		}
		String fundSource = request.getParameter("fundSource");
		if("".equals(fundSource)){
			fundSource = null;
		}
		String paymentMethod = request.getParameter("paymentMethod");
		if("".equals(paymentMethod)){
			paymentMethod = null;
		}
		String conKeyWord = request.getParameter("conKeyWord");
		if("".equals(conKeyWord)){
			conKeyWord = null;
		}
		map.put("conKeyWord", conKeyWord);
		map.put("firstPartyUnit", firstPartyUnit);
		map.put("signTimeStart", signTimeStart);
		map.put("signTimeEnd", signTimeEnd);
		map.put("contractMoneyStrat", contractMoneyStrat);
		map.put("contractTypeCode", contractTypeCode);
		map.put("contractMoneyEnd", contractMoneyEnd);
		map.put("fundSource", fundSource);
		map.put("paymentMethod", paymentMethod);
		int totalRow = contractInfoBusiness.selectContractInfoCount(map);
		Utils.paging(request, totalRow, map, pMap);
		List<ContractInfo> selectContractInfoAll = contractInfoBusiness.selectContractInfoAll(map);
		pMap.put("contractInfoAll", selectContractInfoAll);
		return pMap;
	}
	/**
	 * 
	 * @Description (新增合同信息)
	 * @param request
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/insertContract.do")
	@ResponseBody
	public Map<String,Object> insertContract(HttpServletRequest request) throws NumberFormatException, Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo contract = new ContractInfo();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat sd = new SimpleDateFormat ("yyyyMMddHHmmss");
		String contractName = request.getParameter("contractName");
		String firstPartyUnit = request.getParameter("firstPartyUnit");
		String secondPartyUnit = request.getParameter("secondPartyUnit");
		String firstPartyPrincipal = request.getParameter("firstPartyPrincipal");
		String secondPartyPrincipal = request.getParameter("secondPartyPrincipal");
		String firstPartyContactWay = request.getParameter("firstPartyContactWay");
		String secondPartyContactWay = request.getParameter("secondPartyContactWay");
		String contractTypeCode = request.getParameter("contractTypeCode");
		String signTime = request.getParameter("signTime");
		String contractMoney = request.getParameter("contractMoney");
		String currencyId = request.getParameter("currencyId");
		String fundSource = request.getParameter("fundSource");
		String paymentMethod = request.getParameter("paymentMethod");
		String contractStartTime = request.getParameter("contractStartTime");
		String contractEndTime = request.getParameter("contractEndTime");
		String contractObject = request.getParameter("contractObject");
		String contractDesc = request.getParameter("contractDesc");
		Date signDate = sdf.parse(signTime);
		Date contractStartDate = sdf.parse(contractStartTime);
		Date contractEndDate = sdf.parse(contractEndTime);
		double parseDouble = Double.parseDouble(contractMoney);
		String uuid = UUID.randomUUID().toString(); 
		uuid = uuid.replace("-", "");
		Date date= new Date();
        String format = sd.format(date);
        String code = "HTGL"+format;
        contract.setCode(code);
		contract.setId(uuid);
		contract.setHonourAgreement(ConstantMenu.HONOUR_AN_AGREEMENT);
		contract.setContractTitle(contractName);
		contract.setFirstPartyUnit(firstPartyUnit);
		contract.setSecondPartyUnit(secondPartyUnit);
		contract.setFirstPartyPrincipal(firstPartyPrincipal);
		contract.setSecondPartyPrincipal(secondPartyPrincipal);
		contract.setFirstPartyContactWay(firstPartyContactWay);
		contract.setSecondPartyContactWay(secondPartyContactWay);
		contract.setContractTypeCode(contractTypeCode);
		contract.setSignTime(signDate);
		contract.setContractMoney(parseDouble);
		contract.setCurrencyId(currencyId);
		contract.setFundSource(fundSource);
		contract.setPaymentMethod(paymentMethod);
		contract.setContractStartTime(contractStartDate);
		contract.setContractEndTime(contractEndDate);
		contract.setContractObject(contractObject);
		contract.setContractDesc(contractDesc);
		contract.setLogicDelete(ConstantMenu.CONTRACT_ONE);
		int insertContractInfo = contractInfoBusiness.insertContractInfo(contract);
		insertProjFile(request,uuid);
		map.put("insertContractInfo", insertContractInfo);
		map.put("contractId", uuid);
		return map;
	}
	/**
	 * 
	 * @Description (修改合同台账)
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateContractInfoById.do")
	@ResponseBody
	public Map<String,Object> updateContractInfoById(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo contract = new ContractInfo();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		String contractId = request.getParameter("contractId");
		String contractName = request.getParameter("contractName");
		String firstPartyUnit = request.getParameter("firstPartyUnit");
		String secondPartyUnit = request.getParameter("secondPartyUnit");
		String firstPartyPrincipal = request.getParameter("firstPartyPrincipal");
		String secondPartyPrincipal = request.getParameter("secondPartyPrincipal");
		String firstPartyContactWay = request.getParameter("firstPartyContactWay");
		String secondPartyContactWay = request.getParameter("secondPartyContactWay");
		String contractTypeCode = request.getParameter("contractTypeCode");
		String signTime = request.getParameter("signTime");
		String contractMoney = request.getParameter("contractMoney");
		String currencyId = request.getParameter("currencyId");
		String fundSource = request.getParameter("fundSource");
		String paymentMethod = request.getParameter("paymentMethod");
		String contractStartTime = request.getParameter("contractStartTime");
		String contractEndTime = request.getParameter("contractEndTime");
		String contractObject = request.getParameter("contractObject");
		String contractDesc = request.getParameter("contractDesc");
		String fileProjIds = request.getParameter("fileProjIds");
		String fileIds = request.getParameter("fileIds");
		Date signDate = sdf.parse(signTime);
		Date contractStartDate = sdf.parse(contractStartTime);
		Date contractEndDate = sdf.parse(contractEndTime);
		double parseDouble = Double.parseDouble(contractMoney);
		contract.setId(contractId);
		contract.setContractTitle(contractName);
		contract.setFirstPartyUnit(firstPartyUnit);
		contract.setSecondPartyUnit(secondPartyUnit);
		contract.setFirstPartyPrincipal(firstPartyPrincipal);
		contract.setSecondPartyPrincipal(secondPartyPrincipal);
		contract.setFirstPartyContactWay(firstPartyContactWay);
		contract.setSecondPartyContactWay(secondPartyContactWay);
		contract.setContractTypeCode(contractTypeCode);
		contract.setSignTime(signDate);
		contract.setContractMoney(parseDouble);
		contract.setCurrencyId(currencyId);
		contract.setFundSource(fundSource);
		contract.setPaymentMethod(paymentMethod);
		contract.setContractStartTime(contractStartDate);
		contract.setContractEndTime(contractEndDate);
		contract.setContractObject(contractObject);
		contract.setContractDesc(contractDesc);
		int updateContractInfoById = contractInfoBusiness.updateContractInfoById(contract);
		Map<String, Object> fileParamMap = new HashMap<String, Object>();
		if (!"".equals(fileIds) && fileIds!=null) {
			String[] fileIdsArray = fileIds.split(",");
			fileParamMap.put("id", fileIdsArray);
			sysFileInfoBusiness.deleteProjFiles(fileParamMap);//
			if(!"".equals(fileProjIds) && fileProjIds!=null){
				String[] fileProjIdArray = fileProjIds.split(",");
				for (int i = 0; i < fileProjIdArray.length; i++) {
					contractFileBusiness.deleteContractFile(fileProjIdArray[i]);
				}
			}
		}
		insertProjFile(request,contractId);
		map.put("contractInfo", updateContractInfoById);
		map.put("contractId", contractId);
		return map;
	}
	/**
	 * 
	 * @Description (根据ID查询合同信息)
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/selectContractInfoById.do")
	@ResponseBody
	public ModelAndView selectContractInfoById(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		 Map<String, Object> selectContractInfoById = contractInfoBusiness.selectContractInfoById(id);
		 return new ModelAndView("contract/contractIndexEdit",selectContractInfoById);
	}
	/**
	 * 
	 * @Description (跳转至新增页面)
	 * @return
	 */
	@RequestMapping("/queryInsertPage.do")
	public ModelAndView queryInsertPage(){
		Map<String, Object> queryInsertPage = contractInfoBusiness.queryInsertPage();
		return new ModelAndView("contract/contractIndexAdd",queryInsertPage);
	}
	/**
	 * 
	 * @Description (跳转至付款登记页面)
	 * @return
	 */
	@RequestMapping("/queryPayRegisterPage.do")
	public ModelAndView queryPayRegisterPage(HttpServletRequest request){
		String id = request.getParameter("id");
		Map<String, Object> queryPayRegisterPage = contractInfoBusiness.queryPayRegisterPage(id);
		return new ModelAndView("contract/payRegister",queryPayRegisterPage);
	}
	/**
	 * 
	 * @Description (修改履约情况)
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateHonourAgreement.do")
	@ResponseBody
	public int updateHonourAgreement(HttpServletRequest request){
		ContractInfo contract = new ContractInfo();
		String contractId = request.getParameter("contractId");
		String status = request.getParameter("status");
		if("1".equals(status)){
			contract.setId(contractId);
			contract.setHonourAgreement(ConstantMenu.IN_INNEGOTIATION);
		}else if("2".equals(status)){
			contract.setId(contractId);
			contract.setHonourAgreement(ConstantMenu.ACCOMPLISH);
		}else if("3".equals(status)){
			contract.setId(contractId);
			contract.setHonourAgreement(ConstantMenu.HONOUR_AN_AGREEMENT);
		}
		return contractInfoBusiness.updateHonourAgreement(contract);
	}
	/**
	 * 
	 * @Description (合同管理逻辑删除)
	 * @param request
	 * @return
	 */
	@RequestMapping("/contractLogicDelete.do")
	@ResponseBody
	public int contractLogicDelete(HttpServletRequest request){
		ContractInfo contract = new ContractInfo();
		String contractId = request.getParameter("contractId");
		contract.setId(contractId);
		contract.setLogicDelete(ConstantMenu.CONTRACT_TWO);
		return contractInfoBusiness.contractLogicDelete(contract);
	}
	/**
	 * 
	 * @Description (查询付款台账)
	 * @return
	 */
	@RequestMapping("/selectPaymentStandingBook.do")
	@ResponseBody
	public Map<String,Object> selectPaymentStandingBook(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pMap = new HashMap<String,Object>();
		String firstPartyUnit = request.getParameter("firstPartyUnit");
		if("".equals(firstPartyUnit)){
			firstPartyUnit = null;
		}
		String signTime = request.getParameter("signTime");
		if("".equals(signTime)){
			signTime = null;
		}
		String contractCode = request.getParameter("contractCode");
		if("".equals(contractCode)){
			contractCode = null;
		}
		String contractTypeCode = request.getParameter("contractTypeCode");
		if("".equals(contractTypeCode)){
			contractTypeCode = null;
		}
		String contractMoney = request.getParameter("contractMoney");
		if("".equals(contractMoney)){
			contractMoney = null;
		}
		String fundSource = request.getParameter("fundSource");
		if("".equals(fundSource)){
			fundSource = null;
		}
		String closedProportion = request.getParameter("closedProportion");
		if("".equals(closedProportion)){
			closedProportion = null;
		}
		String conKeyWord = request.getParameter("conKeyWord");
		if("".equals(conKeyWord)){
			conKeyWord = null;
		}
		map.put("conKeyWord", conKeyWord);
		map.put("firstPartyUnit", firstPartyUnit);
		map.put("signTime", signTime);
		map.put("contractCode", contractCode);
		map.put("contractTypeCode", contractTypeCode);
		map.put("contractMoney", contractMoney);
		map.put("fundSource", fundSource);
		map.put("closedProportion", closedProportion);
		int totalRow = contractInfoBusiness.selectContractInfoCount(map);
		Utils.paging(request, totalRow, map, pMap);
		List<ContractInfo> selectPaymentStandingBook = contractInfoBusiness.selectPaymentStandingBook(map);
		pMap.put("paymentStandingBook", selectPaymentStandingBook);
		return pMap; 
	}
	/**
	 * 
	 * @Description (查询预览的导出)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectExportPreview.do")
	@ResponseBody
	public Map<String,Object> selectExportPreview(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String contractTypeCode = request.getParameter("contractTypeCode");
		String honourAgreement = request.getParameter("honourAgreement");
		String signTime = request.getParameter("signTime");
		String paymentMethod = request.getParameter("paymentMethod");
		String paymentName = request.getParameter("paymentName");
		String fundSource = request.getParameter("fundSource");
		String closedAmount = request.getParameter("closedAmount");
		String closedProportion = request.getParameter("closedProportion");
		String openAmount = request.getParameter("openAmount");
		String invoice = request.getParameter("invoice");
		String paymentDesc = request.getParameter("paymentDesc");
		String registrant = request.getParameter("registrant");
		List<ContractInfo> selectExportPreview = contractInfoBusiness.selectExportPreview();
		
		return map;
	}
	/**
	 * 
	 * @Description (查询合同明细)
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/selectContractDetail.do")
	public ModelAndView selectContractDetail(HttpServletRequest request) throws Exception{
		String contractId = request.getParameter("id");
		Map<String, Object> selectContractDetail = contractInfoBusiness.selectContractDetail(contractId);
		return new ModelAndView("contract/contractPayDetail",selectContractDetail);
	}
	public String insertProjFile(HttpServletRequest request,String fileProjCode) throws Exception{
	    String file_name_from = "";
		String path_to = "";
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");  
	    //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){
           //转换成多部分request    
           MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
           //取得request中的所有文件名  
           Iterator<String> iter = multiRequest.getFileNames();  
           while(iter.hasNext()){  
               //取得上传文件  
               MultipartFile file = multiRequest.getFile(iter.next());  
               if(file != null){  
                   //取得当前上传文件的文件名称  
                   String myFileName = file.getOriginalFilename();  
					String s = myFileName.substring(myFileName.indexOf(".")+1);
                   //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                   if(myFileName.trim() !=""){  
                	   file_name_from = myFileName;
						String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
				        uuid = uuid.replace("-", "");
						String newFileName = uuid +"."+s;
						String path = domainName+ newFileName;
						path_to = path;
                       File localFile = new File(path); 
                       file.transferTo(localFile);  
                       //存入数据库
                       ContractFile contractFile = new ContractFile();
	               		SysFileInfo sysFileInf = new SysFileInfo();
	               		HttpSession session = request.getSession();
	               		int userId = (int) session.getAttribute("user_id");
	               		String file_name = file_name_from.split("\\.")[0];
	               		if(!"".equals(path_to)){
	               			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	               			Date date = new Date();
	               			String format = sf.format(date);
	               			int i=(int)(Math.random()*900)+100;
	               			String fileCode = "HTGL"+format+i;
	               			sysFileInf.setCode(fileCode);
	               			sysFileInf.setIdFileType(5);
	               			sysFileInf.setPath(path_to);
	               			sysFileInf.setName(file_name);
	               			sysFileInf.setCreateDate(new Date());
	               			sysFileInf.setCreateUser(userId);
	               			sysFileInf.setRemark("合同管理下的文件");
	               			sysFileInfoBusiness.insertGeneralFile(sysFileInf);
	               			Integer id2 = sysFileInf.getId();
	               			String uuids= UUID.randomUUID().toString(); //获取UUID并转化为String对象  
					        uuids = uuids.replace("-", "");
					        contractFile.setId(uuids);
	               			contractFile.setFileId(id2);
	               			contractFile.setContractId(fileProjCode);
	               			contractFileBusiness.insertContractFile(contractFile);
	               		}
                   }  
               }  
           }  
       }
	return null;
}
}
