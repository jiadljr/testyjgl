package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysAssetsType;

public interface SysAssetsTypeBusiness {
	/**
	 * 根据条件查询资产类型
	 * @author 李帅
	 */
	Map<String, Object> selectByExample(HttpServletRequest request,HttpServletResponse response)throws Exception;
	/**
	 * 查询上级资产类型
	 * @author 李帅
	 * @throws Exception 
	 */
	List<SysAssetsType> selectParentName(int modelId) throws Exception;
	/**
	 * 添加资产类型
	 * @author 李帅
	 */
    void insert(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 添加修改参数
	 * @author 李帅
	 */
    SysAssetsType setAssetsType(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 根据id查询资产类型
	 * @author 李帅
	 */
    SysAssetsType selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response)throws Exception;
    /**
	 * 修改资产类型
	 * @author 李帅
	 */
    void updateByPrimaryKey(HttpServletRequest request,HttpServletResponse response)throws Exception;
    /**
	 * 删除资产类型
	 * @author 李帅
	 */
    String deleteByPrimaryKey(HttpServletRequest request,HttpServletResponse response)throws Exception;
    /**
     * 根据父id查询
     * @param id
     * @return
     */
    List<SysAssetsType> selectAssetsTypeByparentId(int id)throws Exception;
    /**
     * 根据等级查询资产类别
     * @author 李帅
     * @param map
     * @return
     */
    List<SysAssetsType> selectAssetsTypeByLevel(Map<String, Object> map)throws Exception;
    /**
   	 * 查询所有资产类型
   	 * @author 李帅
   	 */
 	List<SysAssetsType> selectPrimAll()throws Exception;
 	/**
 	 * 查询资产类型
 	 * @author 李帅
 	 * @return
 	 */
 	Map<String, Object> selectAssetsTypeForTree()throws Exception;
 	/**
 	 * <pre>selectAll (查询全部资产类型)
 	 * Created by 家栋梁 on 2017年12月4日上午9:52:05  
 	 *
 	 * @return</pre>
 	 */
 	public List<SysAssetsType> selectAll()throws Exception;
 	
 	List<SysAssetsType> selectAssetsTypePareateId(Integer id);
}
