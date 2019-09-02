package com.qkby.sysmanage.business;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysconfig.dao.SysRoleInfoDao;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysmanage.dao.SysCopInfoDao;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserPostInfoDao;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserPostInfo;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.Utils;


/**
 * 
 * @author L.S
 */
@Service("SysUserInfoBusiness")
public class SysUserInfoBusinessImpl implements SysUserInfoBusiness {
	@Resource
	SysUserInfoDao sysUserInfoDao;
	
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	
	@Resource
	SysCopInfoDao sysCopInfoDao;
	
	@Resource
	SysUserPostInfoDao sysUserPostInfoDao;
	@Resource
	SysRoleInfoDao sysRoleInfoDao;
	@Resource
	LogOperInfoDao logOperInfoDao;
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;
	//查询所有
	@Override
	public List<SysUserInfo> selectUserAll() throws Exception {
		return sysUserInfoDao.selectUserAll();
	}
	//添加
	@Override
	@Transactional( value = "transactionManager_1", readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		SysUserInfo user = setUser(request, response);
		String code = user.getCode();
		SysUserInfo ByCode = sysUserInfoDao.selectByName(code);
		if (ByCode != null) {
			return "codeError";
		}
		//验证身份证
		String uuid = user.getUuid();
		pgMap.clear();
		pgMap.put("uuid", uuid);
		int uuidCount = sysUserInfoDao.countByExample(pgMap);
		if (uuidCount != ConstantMenu.EXECUTE_FAIL) {
			return "repetUuidError";
		}
		String Init_PWD = "";//初始密码
		String pwdAging = "";//密码失效时间
		HttpSession session = request.getSession();
		String basePath = session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in =new FileInputStream(basePath);
		   prop.load(in);
			Set<Object> keyValue= prop.keySet();
			for(Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
			   String key = (String) it.next();
			   if (key.equals("Init_PWD")) {
				   Init_PWD = (String) prop.get(key);
			   }else if(key.equals("pwdAging")){
				   pwdAging = (String) prop.get(key);
			   }
			}
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {  
			if (in != null) {  
				try {  
					in.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		} 
		user.setPassword(Utils.crypt(Init_PWD));
		user.setCreateDate(new Date());
		//创建人员
		int userNow = (int)session.getAttribute("user_id");
		user.setCreateUser(userNow);
		//密码过期时间
		int pastPass=Integer.parseInt(pwdAging);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, pastPass);
		user.setPwdExpiredDate(calendar.getTime());
		user.setDs(ConstantMenu.LOCK_ZERO);
		int ins = sysUserInfoDao.insert(user);
		Integer pkId = user.getId();
		String result="success";
		if (ins==0) {
			throw new BusinessException("", "添加失败");
		}
		//添加岗位
		SysUserPostInfo userpost=new SysUserPostInfo();
		String postId=request.getParameter("userPost");
		if ("".equals(postId) || postId==null){
			postId=null;
		}else{
			int pId=Integer.parseInt(postId);
			userpost.setIdPost(pId);
		}
		int userId = sysUserInfoDao.selectLastUserId();
		userpost.setIdUser(userId);
		sysUserPostInfoDao.insert(userpost);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_user", 1);
		logOperInfoDao.insert(logOperInfo);
		return result;
	}
	@Override
	public SysUserInfo setUser(HttpServletRequest request, HttpServletResponse response) {
		SysUserInfo user=new SysUserInfo();
		user.setArrangeProxy(ConstantMenu.LOCK_ONE);
		String userName=request.getParameter("userName");
		if ("".equals(userName)) {
			userName=null;
		}
		user.setName(userName);
		String code=request.getParameter("code");
		if ("".equals(code)) {
			code=null;
		}
		user.setCode(code);
		
		String userSex=request.getParameter("userSex");
		if ("".equals(userSex) || userSex==null) {
			userSex=null;
		}else{
			int sex=Integer.parseInt(userSex);
			user.setSex(sex);
		}
		String userUuid=request.getParameter("userUuid");
		if ("".equals(userUuid)) {
			userUuid=null;
		}
		user.setUuid(userUuid);
		String userWechat=request.getParameter("userWechat");
		if ("".equals(userWechat)) {
			userWechat=null;
		}
		user.setWechat(userWechat);
		//部门
		String userDept=request.getParameter("idDept");
		if ("".equals(userDept) || userDept==null) {
			userDept=null;
		}else{
			int deptId=Integer.parseInt(userDept);
			user.setIdDept(deptId);
		}
		//公司
		String userCom=request.getParameter("userCom");
		if ("".equals(userCom) || userCom==null) {
			userCom=null;
		}else{
			int idCmpy=Integer.parseInt(userCom);
			user.setIdCmpy(idCmpy);
		}
		//电话手机微信地址
		String userTel=request.getParameter("userTel");
		if ("".equals(userTel)) {
			userTel=null;
		}
		user.setTel(userTel);
		String userCall=request.getParameter("userCall");
		if ("".equals(userCall)) {
			userCall=null;
		}
		user.setCal(userCall);
		String userMail=request.getParameter("userMail");
		if ("".equals(userMail)) {
			userMail=null;
		}
		user.setMail(userMail);
		String userAddr=request.getParameter("userAddr");
		if ("".equals(userAddr)) {
			userAddr=null;
		}
		user.setAddress(userAddr);
		return user;
	}
	//id查询
	@Override
	public Map<String, Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String ids=request.getParameter("id");
		int id = Integer.parseInt(ids);
		SysUserInfo sysUserOne = sysUserInfoDao.selectByPrimaryKey(id);
		if(sysUserOne == null){
			throw new BusinessException("", "为找到对应人员的信息");
		}
		Integer idDept = sysUserOne.getIdDept();
		map.put("sysUserOne", sysUserOne);
		map.put("idDept", idDept);
		return map;
	}
	//修改
	@Override
	@Transactional
	public void updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUserInfo user = setUser(request, response);
		String userId=request.getParameter("userId");
		int id=Integer.parseInt(userId);
		user.setId(id);
		user.setUpdateDate(new Date());
		HttpSession session = request.getSession();
		int userNow = (int)session.getAttribute("user_id");
		user.setUpdateUser(userNow);
		int in = sysUserInfoDao.updateByPrimaryKeySelective(user);
		if (in==0) {
			throw new BusinessException("", "修改人员信息失败！找不到该人员的信息");
		}
		SysUserPostInfo userpost=new SysUserPostInfo();
		String idPost=request.getParameter("userPost");
		if ("".equals(idPost) || idPost==null){
			idPost=null;
		}else{
			int idP=Integer.parseInt(idPost);
			userpost.setIdPost(idP);
		}
		String postId=request.getParameter("postId");
		if (postId.equals("") || postId==null) {
			postId=null;
		}else{
			int pId = Integer.parseInt(postId);
			userpost.setId(pId);
		}
		userpost.setIdUser(id);
		sysUserPostInfoDao.updateByPrimaryKeySelective(userpost);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, id,"sys_user", 2);
		logOperInfoDao.insert(logOperInfo);
	}
	//删除
	@Override
	public Map<String, Object> deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String,Object>();
		String[] ids = request.getParameterValues("ids");
		map.put("ids", ids);
		//逻辑删除:将ds字段改为1
		SysUserInfo user = new SysUserInfo();
		user.setDs(ConstantMenu.DS_ONE);
		int update = sysUserInfoDao.updateLogic(map);
		if (update == 0) {
			throw new BusinessException("", "删除人员信息失败");
		}
		for(int i = 0;i<ids.length;i++){
			int pkId = Integer.valueOf(ids[i]);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId,"sys_user", 3);
			logOperInfoDao.insert(logOperInfo);
		}
		return map;
	}
	//条件查询
	@Override
	public Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		//参数
		Map<String, Object> listMap=new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		if ("".equals(userName)) {
			userName=null;
		}
		listMap.put("name", userName);
		String userDept = request.getParameter("userDept");
		if ("".equals(userDept) || userDept==null) {
			userDept=null;
		}else{
			int idDept=Integer.parseInt(userDept);
			listMap.put("idDept", idDept);
		}
		String cal=request.getParameter("cal");
		if ("".equals(cal)) {
			cal=null;
		}
		listMap.put("cal", cal);
		String perNum=request.getParameter("perNum");
		if ("".equals(perNum)) {
			perNum=null;
		}
		listMap.put("perNum", perNum);
		String pages = request.getParameter("pages");
		SysUserRoleInfo userRole = sysUserRoleInfoDao.selectUserByRoleId(ConstantMenu.OPS_SUP);
		if (userRole != null) {
			listMap.put("superId", userRole.getIdUser());
		}
		if(!"".equals(pages)){
			Utils.pages(request, listMap, map);
		}else{
			int totalRow = sysUserInfoDao.countByExample(listMap);
			Utils.paging(request, totalRow, listMap, map);
		}
		List<SysUserInfo> userAll = sysUserInfoDao.selectByUserExample(listMap);
		if(userAll == null){
			throw new BusinessException("500","");
		}
		map.put("userAll", userAll);
		//查询所有部门名称
		List<SysDeptInfo> deptAll = sysDeptInfoDao.selectPrimAll();
		List<SysRoleInfo> roleAll = sysRoleInfoDao.selectRoleAll();
		map.put("deptAll", deptAll);
		map.put("roleAll", roleAll);
		return map;
	}
	@Override
	public Map<String,Object> userByRoleType(Map<String,Object> pgMap,HttpServletRequest request ) throws Exception {
		String parameter = request.getParameter("time");
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysUserInfo> userByRoleType = sysUserInfoDao.userByRoleType(pgMap);
		map.put("userByRoleType", userByRoleType);
		map.put("parameter", parameter);
		return map;
	}
	@Override
	public Map<String, Object> selectNameById(int id) throws Exception {
		Map<String, Object> selectNameById = sysUserInfoDao.selectNameById(id);
		  if(selectNameById == null){
			  throw new BusinessException("500","");
		  }
		return selectNameById;
	}
	@Override
	public  Map<String,Object> selectDeptUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(Integer.valueOf(id));
		if(selectUser == null){
			throw new BusinessException("500","");
		}
		SysDeptInfo selectDept = sysDeptInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		if(selectDept == null){
			throw new BusinessException("500","");
		}
		String tel = selectDept.getTel();
		map.put("selectUser", selectUser);
		map.put("tel", tel);
		return map;
	}
	@Override
	public Map<String, Object> serviceAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("dep_id");
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(id);
		if(selectUser == null){
			throw new BusinessException("500","");
		}
		map.put("selectAll", selectAll);
		map.put("selectUser", selectUser);
		return map;
	}
	 /**
     * 根据部门ID查询人员信息
	 * @throws Exception 
     * */
	@Override
	public List<SysUserInfo> findUserInfoByDept(int id_dept) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("idDept", id_dept);
		return sysUserInfoDao.selectByUserExample(map);
	}
	
	@Override
	public List<SysUserInfo> selectAudit() throws Exception {
		List<SysUserInfo> selectAudit = sysUserInfoDao.selectAudit(ConstantMenu.OPS_CEK);
		return selectAudit;
	}
	@Override
	public List<Map<String,Object>> selectSystem() throws Exception {
		Map<String, Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_MEN);
		pgMap.put("role_id_two", ConstantMenu.OPS_AUD);
		List<Map<String,Object>> selectSystem = sysUserInfoDao.selectSystem(pgMap);
		return selectSystem;
	}
	@Override
	public int personalUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysUserInfo user = setUser(request, response);
		String userId=request.getParameter("userId");
		int id=Integer.parseInt(userId);
		user.setId(id);
		user.setUpdateDate(new Date());
		HttpSession session = request.getSession();
		int userNow = (int)session.getAttribute("user_id");
		user.setUpdateUser(userNow);
		int in = sysUserInfoDao.updateByPrimaryKeySelective(user);
		if (in == 0) {
			throw new BusinessException("", "修改失败");
		}
		return in;
	}
	@Override
	@Transactional
	public Map<String,Object> accreditProxy(int oper_id,String dutyNo,HttpServletRequest request) throws Exception {
		/**先判断目前是否有代理人*/
		Map<String, Object> pgMap = new HashMap<String, Object>();
		pgMap.put("arrangeProxy", ConstantMenu.LOCK_ZERO);
		List<SysUserInfo> userList = sysUserInfoDao.selectByUserExample(pgMap);
		/**先判断当前登录人是代理人还是值班人
		 * 如果是代理人
		 * */
		SysUserInfo record = new SysUserInfo();
		if (dutyNo.equals("dutyPerson")) {//是代理人,获取当前代理人id,将当前代理人的arrange_proxy状态改为1
			Integer nowProxyId = userList.get(0).getId();
			record.setId(nowProxyId);
			record.setArrangeProxy(ConstantMenu.LOCK_ONE);
			sysUserInfoDao.updateByPrimaryKeySelective(record);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, nowProxyId, "sys_user", 3);
			logOperInfoDao.insert(logOperInfo);
		}else{//值班人
			if (userList!=null && userList.size()>0){//判断存不存在代理人，如果存在直接返回代理人信息
				pgMap.put("proxyList", userList.get(0));
				return pgMap;
			}else{
				pgMap.put("dutyTrue", "yes");
			}
		}
		
		record.setId(oper_id);
		record.setArrangeProxy(ConstantMenu.LOCK_ZERO);
		sysUserInfoDao.updateByPrimaryKeySelective(record);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, oper_id, "sys_user", 3);
		logOperInfoDao.insert(logOperInfo);
		return pgMap;
	}
	
	@Override
	public Map<String, Object> toDistroyProxy() throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		pgMap.put("arrangeProxy", ConstantMenu.LOCK_ZERO);
		List<SysUserInfo> userList = sysUserInfoDao.selectByUserExample(pgMap);
		if (userList.size() == 0||userList == null) {
			pgMap.put("noProxyPerson", "1");
		}else{
			pgMap.put("proxyUser", userList.get(0));
		}
		return pgMap;
	}
	@Override
	@Transactional
	public int distroyProxy(SysUserInfo user,HttpServletRequest request) throws Exception {
		int in = sysUserInfoDao.updateByPrimaryKeySelective(user);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, user.getId(), "sys_user", 3);
		logOperInfoDao.insert(logOperInfo);
		return in;
	}
	@Override
	@Transactional
	public int continueAccreditProxy(int user_id, int oper_id,HttpServletRequest request) throws Exception {
		LogOperInfo logOperInfo = new LogOperInfo();
		SysUserInfo record = new SysUserInfo();
		record.setId(user_id);
		record.setArrangeProxy(ConstantMenu.LOCK_ONE);
		sysUserInfoDao.updateByPrimaryKeySelective(record);
		logOperInfo = Utils.insertLogOper(request, user_id, "sys_user", 3);
		logOperInfoDao.insert(logOperInfo);
		record.setId(oper_id);
		record.setArrangeProxy(ConstantMenu.LOCK_ZERO);
		logOperInfo = Utils.insertLogOper(request, oper_id, "sys_user", 3);
		logOperInfoDao.insert(logOperInfo);
		return sysUserInfoDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<Map<String, Object>> selectDeptByUserName(Map<String, Object> map) {
		return sysUserInfoDao.selectDeptByUserName(map);
	}
}
