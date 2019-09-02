package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.entity.SysUserInfo;

/**
 * 
 * @author L.S
 */
public interface SysUserInfoBusiness {
	/**
	 * 根据条件查询人员信息
	 * @author 李帅
	 */
	Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 查询所有人员信息
	 * @author 李帅
	 */
	List<SysUserInfo> selectUserAll()throws Exception;
	/**
	 * 添加人员
	 * @author 李帅
	 * @throws Exception 
	 */
	String insert(HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 添加修改参数
	 * @author 李帅
	 */
    SysUserInfo setUser(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 根据id查询人员
	 * @author 李帅
	 */
    Map<String, Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 修改人员
	 * @author 李帅
	 */
    void updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 删除人员
	 * @author 李帅
	 */
    Map<String, Object> deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 查找运维人员
	 * @author 李帅
	 */
    Map<String,Object> userByRoleType(Map<String,Object> pgMap,HttpServletRequest request )throws Exception;
    /**
	 * 根据Id查询人员
	 * @author 李帅
	 */
    Map<String, Object> selectNameById(int id)throws Exception;
    /**
     * <pre>selectDeptUser (根据部门查询人员)
     * Created by 家栋梁 on 2017年11月10日下午12:00:49  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    Map<String,Object> selectDeptUser(HttpServletRequest request, HttpServletResponse response)throws Exception; 
    /**
     * <pre>serviceAdd 
     * Created by 家栋梁 on 2017年11月10日下午12:19:57  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public Map<String, Object> serviceAdd(HttpServletRequest request, HttpServletResponse response)throws Exception;
    
    /**
     * 根据部门ID查询人员信息
     * */
    List<SysUserInfo> findUserInfoByDept(int id_dept)throws Exception;
    /**
     * <pre>selectAudit (查询四级以上事件的审核人员)
     * Created by 家栋梁 on 2017年11月30日上午11:16:58  
     *
     * @return</pre>
     */
    List<SysUserInfo> selectAudit()throws Exception;
    /**
     * <pre>selectSystem (查询系统管理人员)
     * Created by 家栋梁 on 2017年12月4日下午2:28:15  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectSystem()throws Exception;
    
    int personalUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * <pre>填加代理人
     * 2018年1月8日 下午1:51:32
     * Created by  @李帅
     * @param oper_id
     * @return int
     * @throws BusinessException 
     */
    Map<String, Object> accreditProxy(int oper_id,String dutyNo,HttpServletRequest request) throws Exception;
    /**
     * 确认更改代理人
     * 2018年1月9日 下午1:57:54
     * @李帅
     * @param
     */
    int continueAccreditProxy(int user_id,int oper_id,HttpServletRequest request)throws Exception;
    /**
     * 跳转取消代理人页面
     * 2018年1月9日 上午11:31:46
     * @李帅
     * @param
     */
    Map<String, Object> toDistroyProxy()throws Exception;
    /**
     * 取消代理人
     * 2018年1月9日 上午11:46:49
     * @李帅
     * @param user
     */
    int distroyProxy(SysUserInfo user,HttpServletRequest request)throws Exception;

    List<Map<String,Object>> selectDeptByUserName(Map<String,Object> map);
}
