package com.qkby.sysmanage.dao;

import com.qkby.sysmanage.entity.SysUserInfo;

import java.util.List;
import java.util.Map;

public interface SysUserInfoDao {
	/**
	 * 查询所有人员
	 * @author 李帅
	 */
	List<SysUserInfo> selectUserAll()throws Exception;
	/**
	 * 根据条件查询人员总数
	 * @author 李帅
	 */
    int countByExample(Map<String,Object> map)throws Exception;
    /**
	 * 根据条件删除人员
	 * @author 李帅
	 */
    int deleteByExample(SysUserInfo example)throws Exception;
    /**
	 * 根据id删除人员
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Map<String,Object> map)throws Exception;
    /**
	 * 添加人员
	 * @author 李帅
	 */
    int insert(SysUserInfo record)throws Exception;
    /**
	 * 根据条件查询人员
	 * @author 李帅
	 */
    List<SysUserInfo> selectByUserExample(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectLogin (登陆)
     * Created by 家栋梁 on 2017年10月31日下午3:01:48  
     *
     * @param map
     * @return</pre>
     */
    List<SysUserInfo> selectLogin(Map<String,Object> map)throws Exception;
    /**
	 * 根据id查询人员
	 * @author 李帅
	 */
    SysUserInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改人员
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysUserInfo record)throws Exception;
    /**
	 * 查找所有运维人员
	 * @author 李帅
	 */
    List<SysUserInfo> userByRoleType(Map<String, Object> paramMap)throws Exception;
    /**
     * 运维人员总数
     * @author 李帅
     */
    int countUserByRoleType(int role_id)throws Exception;
    /**
	 * 根据Id查询部门，职位等
	 * @author 李帅
	 */
    Map<String,Object> selectNameById(int id)throws Exception;
    /**
	 * 新增完成之后查询最后一条id
	 * @author 李帅
	 */
    int selectLastUserId()throws Exception;
    /**
	 * 根据code查找ID
	 * @author 李帅
	 */
    SysUserInfo selectByName(String code)throws Exception;
    /**
     * 逻辑删除
     * @author 李帅
     */
    int updateLogic(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectDeptUser (根据部门ID查询人员)
     * Created by 家栋梁 on 2017年10月11日上午11:30:38  
     *
     * @param id
     * @return</pre>
     */
    List<SysUserInfo> selectDeptUser(int id)throws Exception;
    /**
     * <pre>userEventId 
     * Created by 家栋梁 on 2017年10月16日下午5:54:41  
     *
     * @param id
     * @return</pre>
     */
    SysUserInfo userEventId(int id)throws Exception;
    /**
     * <pre>selectPostUser 
     * Created by 家栋梁 on 2017年10月18日下午3:09:11  
     *
     * @param id
     * @return</pre>
     */
    List<SysUserInfo> selectPostUser(int id)throws Exception;
    /**
     * 运维人员处理总数统计
     * @author 李帅
     * @param map
     * @return
     */
    List<SysUserInfo> selectDealCount(Map<String, Object> map)throws Exception;
    /**
     * 运维人员受理总数统计
     * @author 李帅
     * @param map
     * @return
     */
    List<SysUserInfo> selectAcceptCount(Map<String, Object> map)throws Exception;
    /**
     * 查询处理人最近一个月处理事件总数
     * @author 李帅
     * @return
     */
    List<SysUserInfo> selectDealCouByMon()throws Exception;
    /**
     * 查询处理人最近一个月未处理事件总数
     * @author 李帅
     * @return
     */
    List<SysUserInfo> selectNotDealByMon(int role_id)throws Exception;
    /**
     * 查询所有未处理数
     * @author 李帅
     * @return
     */
    List<SysUserInfo> selectNotDealAll(int role_id)throws Exception;
    /**
     * 查询增援转派人员
     * @author 李帅
     * @param pgMap
     * @return
     */
    List<SysUserInfo> selectDealUser(Map<String, Object> pgMap)throws Exception;
    /**
     * <pre>selectAudit (查询四级以上事件的审核人)
     * Created by 家栋梁 on 2017年11月30日上午11:16:05  
     *
     * @return</pre>
     */
    List<SysUserInfo> selectAudit(int role_id)throws Exception;
    
    /**
     * 处理人情况
     * @author 李帅
     * @param pgMap
     * @return
     */
    List<Map<String, Object>> selectEventCountByStatus(Map<String, Object> pgMap)throws Exception;
    /**
     * <pre>selectSystem (查询系统管理人员)
     * Created by 家栋梁 on 2017年12月4日下午2:27:34  
     * @param pgMap
     * @return</pre>
     */
    List<Map<String,Object>> selectSystem(Map<String, Object> pgMap)throws Exception;
    
    List<SysUserInfo> seleUserAll()throws Exception;
    
    int updateOAUser(Map<String,SysUserInfo> map)throws Exception;
    /**
     * 查询已经参与事件的处理人
     * 2018年1月10日 上午11:45:39
     * @李帅
     * @param
     */
    List<SysUserInfo> selectNowDealUser(Map<String, Object> pgMap)throws Exception;

    List<Map<String,Object>> selectDeptByUserName(Map<String,Object> map);
}