package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysDeptInfo;

/**
 * 
 * @author L.S
 */
public interface SysDepInfoBusiness {
	/**
	 * 查询所有部门信息
	 * @author 李帅
	 */
    public List<SysDeptInfo> selectPrimAll(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 根据id删除部门
	 * @author 李帅
	 */
    public String deleteByPrimaryKey(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 添加部门
	 * @author 李帅
	 */
    String insert(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 根据id查询部门信息
	 * @author 李帅
	 */
    Map<String, Object> selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response) throws Exception;
    
    /**
	 * 根据id修改部门信息
	 * @author 李帅
	 */
    String updateByPrimaryKeySelective(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 添加修改参数
	 * @author 李帅
	 */
    SysDeptInfo setDept(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 根据条件查询部门信息
	 * @author 李帅
	 */
    Map<String, Object> selectByExample(HttpServletRequest request,HttpServletResponse response)throws Exception;
    
    /**
	 * 查询一二级部门
	 * @author 李帅
	 */
    List<SysDeptInfo> selectParent(List<Integer> levelList,Map<String, Object> listMap)throws Exception;
    /**
     * <pre>countDeptApply 
     * Created by 家栋梁 on 2017年10月25日下午5:12:11  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String,Object>> countDeptApply(Map<String, Object> map)throws Exception;
    /**
     * 根据id查询部门电话
     * @author 李帅
     * @param id
     * @return
     */
    SysDeptInfo selectTelByDeptId(int id)throws Exception;
    /**
     * 根据等级查询科室
     * @author 李帅
     * @param id
     * @return
     */
    List<SysDeptInfo> selectDeptByLevel(Map<String, Object> map)throws Exception;

	public Map<String, Object> selectDeptsTree()throws Exception;

	/**
	 * 查询部门集合
	 * 2018年1月19日 下午3:58:05
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	List<SysDeptInfo> selectDeptForTree() throws Exception;
	
	SysDeptInfo deptEvent(int id);

	/**
	 * 根据部门查询事件，并导出Excel
	 * 2018年4月8日 下午2:21:09
	 * @李帅
	 * @param
	 * paramMap
	 */
	public void deptEventExport(Map<String, Object> paramMap);
	
	public List<SysDeptInfo> selectDeptPareateId(Integer id);
}
