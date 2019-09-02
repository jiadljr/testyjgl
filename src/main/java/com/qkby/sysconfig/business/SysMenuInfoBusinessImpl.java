package com.qkby.sysconfig.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysconfig.dao.SysMenuInfoDao;
import com.qkby.sysconfig.dao.SysRoleInfoDao;
import com.qkby.sysconfig.dao.SysRoleMenuInfoDao;
import com.qkby.sysconfig.entity.SysMenuInfo;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysconfig.entity.SysRoleMenuInfo;
import com.qkby.sysmanage.dao.SysArrangeDao;
import com.qkby.sysmanage.dao.SysDutyInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysArrange;
import com.qkby.sysmanage.entity.SysDutyInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.Utils;
@Service("SysMenuInfoBusiness")
/**
 * 
 * @author L.S
 */
public class SysMenuInfoBusinessImpl implements SysMenuInfoBusiness{
	@Resource
	SysMenuInfoDao sysMenuInfoDao;
	@Resource
	SysRoleMenuInfoDao sysRoleMenuInfoDao;
	@Resource
	SysRoleInfoDao sysRoleInfoDao;
	@Resource
	private SysDutyInfoDao sysDutyInfoDao;
	@Resource
	private SysUserRoleInfoDao sysUserRoleInfoDao;
	@Resource
	LogOperInfoDao logOperInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysArrangeDao sysArrangeDao;
	/**
	 * 根据条件查询所有菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> pgMap=new HashMap<String, Object>();
		Map<String, Object> maps=new HashMap<String, Object>();
		String menuName = request.getParameter("menuName");
		if ("".equals(menuName)) {
			menuName = null;
		}
		pgMap.put("name", menuName);
		String meauUp = request.getParameter("meauUp");
		if ("".equals(meauUp) || meauUp==null) {
			meauUp=null;
		}else{
			int parentId=Integer.parseInt(meauUp);
			pgMap.put("parentId", parentId);
		}
		String menuLevel = request.getParameter("menuLevel");
		if ("".equals(menuLevel) || menuLevel==null) {
			menuLevel = null;
		}else{
			int level=Integer.parseInt(menuLevel);
			pgMap.put("menuLevel", level);
		}
		//父节点名称
		List<SysMenuInfo> parName = sysMenuInfoDao.selectParName();
		//菜单角色
		List<SysRoleInfo> roleAll = sysRoleInfoDao.selectRoleAll();
		//分页参数
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
			Utils.pages(request, pgMap, maps);
		}else{
			int totalRow = sysMenuInfoDao.selectCount(pgMap);
			Utils.paging(request, totalRow, pgMap, maps);
		}
		List<SysMenuInfo> SysMenuInfo = sysMenuInfoDao.selectByExample(pgMap);
		if(SysMenuInfo == null){
			throw new BusinessException();
		}
		maps.put("roleAll", roleAll);
		maps.put("parName", parName);
		maps.put("SysMenuInfo", SysMenuInfo);
		return maps;
	}
	/**
	 * 查询单个菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String ids=request.getParameter("id");
		int id = Integer.parseInt(ids);
		List<SysMenuInfo> menuByParentId = sysMenuInfoDao.selectMenuByParentId(id);
		String flag = "no";
		if (menuByParentId != null && menuByParentId.size()>0) {
			flag = "yes";
		}
		SysMenuInfo sysMenuOne = sysMenuInfoDao.selectByPrimaryKey(id);
		map.put("sysMenuOne", sysMenuOne);
		map.put("flag", flag);
		return map;
	}
	/**
	 * 删除菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询所有菜单,比较删除的id中有没有下级菜单
		List<SysMenuInfo> sysMenuInfoAll = sysMenuInfoDao.selectByExampleAll();
		String parent_id="";
		for (SysMenuInfo sysMenuInfo : sysMenuInfoAll) {
			parent_id += String.valueOf(sysMenuInfo.getParentId())+",";
		}
		String ParentId1 = parent_id.substring(0, parent_id.length()-1);
		String[] ids = request.getParameterValues("ids");
		String result="success";
		for (int i = 0; i < ids.length; i++) {
			if(ParentId1.contains(ids[i])){
				result="pError";
				return result;
			}
		}
		//先删除中间表再删除菜单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		sysRoleMenuInfoDao.deleteByMap(map);
		int delete = sysMenuInfoDao.deleteByPrimaryKey(map);
		if (delete == 0) {
			result="error";
		}
		for(int i = 0;i<ids.length;i++){
			int pkId = Integer.valueOf(ids[i]);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_menu", 3);
			logOperInfoDao.insert(logOperInfo);
		}
		return result;
	}
	/**
	 * 添加菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public void insertSysMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//添加参数
		SysMenuInfo sysMenu = setMenu(request, response);
		int add = sysMenuInfoDao.insertSysMenu(sysMenu);
		if (add == 0) {
			throw new BusinessException("", "新增菜单失败！填写信息不完整");
		}
		Integer menuId = sysMenu.getId();
		SysRoleMenuInfo roleMenu=new SysRoleMenuInfo();
		roleMenu.setIdRole(1);
		roleMenu.setIdMenu(menuId);
		sysRoleMenuInfoDao.insert(roleMenu);
		int pkId = sysMenu.getId();
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_menu", 1);
		logOperInfoDao.insert(logOperInfo);
	}
	@Override
	/**
	 * 修改菜单
	 * @author 李帅
	 */
	public void updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String menuId = request.getParameter("menuId");
		int id=Integer.parseInt(menuId);
		SysMenuInfo sysMenu = setMenu(request, response);
		sysMenu.setId(id);
		int update = sysMenuInfoDao.updateByPrimaryKeySelective(sysMenu);
		if (update==0) {
			throw new BusinessException("", "修改信息失败！");
		}
		LogOperInfo logOperInfo = Utils.insertLogOper(request, id, "sys_menu", 2);
		logOperInfoDao.insert(logOperInfo);
	}
	/**
	 * 添加修改所需参数
	 * @author 李帅
	 */
	@Override
	public SysMenuInfo setMenu(HttpServletRequest request, HttpServletResponse response) {
		SysMenuInfo sysMenu=new SysMenuInfo();
		String code=request.getParameter("code");
		if ("".equals(code)) {
			code=null;
		}
		String meauName=request.getParameter("meauName");
		if ("".equals(meauName)) {
			meauName=null;
		}
		//菜单等级
		String meauGrade=request.getParameter("level");
		if (Utils.isNum(meauGrade)) {
			int level=Integer.parseInt(meauGrade);
			sysMenu.setMenuLevel(level);
		}
		//上级菜单
		String meauUp=request.getParameter("meauUp");
		if ("".equals(meauUp) || meauUp==null) {
			meauUp=null;
		}else{
			int parentId=Integer.parseInt(meauUp);
			sysMenu.setParentId(parentId);
		}
		String meauLink=request.getParameter("meauLink");
		if ("".equals(meauLink)) {
			meauLink=null;
		}
		String meauDesc=request.getParameter("meauDesc");
		if ("".equals(meauDesc)) {
			meauDesc=null;
		}
		String meauImage=request.getParameter("meauImage");
		if ("".equals(meauImage)) {
			meauImage=null;
		}
		sysMenu.setName(meauName);
		sysMenu.setMenuUrl(meauLink);
		sysMenu.setRemark(meauDesc);
		sysMenu.setMenuImg(meauImage);
		sysMenu.setCode(code);
		return sysMenu;
	}
	/**
	 * 查询所有
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public List<SysMenuInfo> selectByExampleAll() throws Exception {
		return sysMenuInfoDao.selectByExampleAll();
	}
	/**
	 * 查询上级菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public List<SysMenuInfo> selectParName() throws Exception {
		return sysMenuInfoDao.selectParName();
	}
	
	/**
	 * 查询用户对应菜单
	 * @throws ParseException 
	 * */
	public Map<String,List<SysMenuInfo>> loadMenu(SysUserInfo user,Integer dutyYes,Integer userId,String basePath,HttpServletRequest request) throws Exception{
		Map<String,List<SysMenuInfo>> map = new HashMap<String,List<SysMenuInfo>>();
		List<SysMenuInfo> modList = new ArrayList<SysMenuInfo>();
		List<SysMenuInfo> funList = new ArrayList<SysMenuInfo>();
		if(user != null){
			List<SysMenuInfo> smList = sysMenuInfoDao.selectByUserId(user.getId());
			if(smList == null) return map;
			for(SysMenuInfo menu:smList){
				if(menu.getMenuLevel() == 1){
					modList.add(menu);
				}else{
					List<SysUserRoleInfo> urList = sysUserRoleInfoDao.selectRoleByUserId(user.getId());
					Boolean fg_duty = this.checkDutyByUser(dutyYes,userId);
					if("受理".equals(menu.getName())){//非当前值班人不可使用受理功能
						Boolean is_sup = false;//是否系统管理员
						for(SysUserRoleInfo ur : urList){
							if(ConstantMenu.OPS_SUP == ur.getIdRole()){
								is_sup = true;
							}
						}
						if(!is_sup && !fg_duty){
								continue;
						}
					}
					if("待办事件".equals(menu.getName())){//非当前值班人不可使用受理功能
						Boolean is_sup = false;//是否系统管理员或运维人员
						for(SysUserRoleInfo ur : urList){
							if(ConstantMenu.OPS_SUP == ur.getIdRole() || ConstantMenu.OPS_SIX == ur.getIdRole()){
								is_sup = true;
							}
						}
						if(!is_sup){
								continue;
						}
					}
					if("审核".equals(menu.getName())){//非当前值班人不可使用受理功能
						Boolean is_sup = false;//是否系统管理员或审核人员
						for(SysUserRoleInfo ur : urList){
							if(ConstantMenu.OPS_SUP == ur.getIdRole() || ConstantMenu.OPS_CEK == ur.getIdRole()){
								is_sup = true;
							}
						}
						if(!is_sup){
								continue;
						}
					}
					if("服务台首页".equals(menu.getName())){
						if(!fg_duty){
							Boolean is_sup = false;//是否系统管理员
							for(SysUserRoleInfo ur : urList){
								if(ConstantMenu.OPS_SUP == ur.getIdRole()){
									is_sup = true;
								}
							}
							if(!is_sup){
								continue;
							}
						}
					}
					funList.add(menu);
				}
			}
		}
		map.put("mod", modList);
		map.put("fun", funList);
		return map;
	}
	/**
	 * 判断是否值班人员
	 * */
	public  Boolean checkDutyByUser(Integer dutyYes,Integer userId) throws Exception{
		//获取值班时间
		if (dutyYes.equals(userId)){
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<SysMenuInfo> selectMenuByLevel(Map<String, Object> map) throws Exception {
		return sysMenuInfoDao.selectMenuByLevel(map);
	}
}
