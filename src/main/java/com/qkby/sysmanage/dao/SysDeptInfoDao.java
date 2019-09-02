package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;
import com.qkby.sysmanage.entity.SysDeptInfo;

public interface SysDeptInfoDao {
	/**
	 * 查询所有部门信息
	 * @author 李帅
	 */
	List<SysDeptInfo> selectPrimAll()throws Exception;
	/**
	 * 根据条件查询部门总数
	 * @author 李帅
	 */
    int countByExample(Map<String, Object> map)throws Exception;
    /**
	 * 根据条件删除部门
	 * @author 李帅
	 */
    int deleteByExample(SysDeptInfo example)throws Exception;
    /**
	 * 根据id删除部门
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Map<String, Object> map)throws Exception;
    /**
	 * 添加部门
	 * @author 李帅
	 */
    int insert(SysDeptInfo record)throws Exception;
    /**
	 * 根据条件查询部门信息
	 * @author 李帅
	 */
    List<SysDeptInfo> selectByExample(Map<String,Object> map)throws Exception;
    /**
	 * 根据id查询部门信息
	 * @author 李帅
	 */
    SysDeptInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 根据id修改部门信息
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysDeptInfo record)throws Exception;
    /**
	 * 根据id修改部门信息
	 * @author 李帅
	 */
    int updateLogic(Map<String,Object> map)throws Exception;
    /**
     * <pre>deptEvent 
     * Created by 家栋梁 on 2017年10月16日下午5:58:31  
     *
     * @param id
     * @return</pre>
     */
    SysDeptInfo deptEvent(int id)throws Exception;
    /**
     * 科室统计
     * @author 李帅
     * @param map
     * @return
     */
     List<Map<String,Object>> countDeptApply(Map<String, Object> map)throws Exception;
     /**
      * 根据父id查询
      * @author 李帅
      * @param id
      * @return
      */
     List<SysDeptInfo> selectDeptByParentId(int id)throws Exception;
     /**
      * 根据等级查询科室
      * @author 李帅
      * @param map
      * @return
      */
     List<SysDeptInfo> selectDeptByLevel(Map<String, Object> map)throws Exception;
     /**
      * 科室申告统计
      * 大屏展示
      * @author 李帅
      * @return
      */
     List<SysDeptInfo> deptCountSum()throws Exception;
     
     /**
      * 查询部门所涉及的事件等级统计
      * @author 李帅
      * @return
      * @throws Exception
      */
     List<Map<String, Object>>selectLevelByDept(Map<String, Object> paramMap) throws Exception;
     
     List<SysDeptInfo> seleDeptInfo()throws Exception;
     
     int selectDeptByCode(String code)throws Exception;
     
     int updateBatch(List<Object> list)throws Exception;
     
     List<SysDeptInfo> selectDeptPareateId(Integer id);
}