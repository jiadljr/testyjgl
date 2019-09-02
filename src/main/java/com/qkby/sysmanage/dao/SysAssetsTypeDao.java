package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysAssetsType;

public interface SysAssetsTypeDao {
	/**
	 * 查询上级资产类型
	 * @author 李帅
	 */
	List<SysAssetsType> selectParentName()throws Exception;
	/**
	 * 根据条件查询资产类型总数
	 * @author 李帅
	 */
    int countByExample(Map<String, Object> map)throws Exception;
    /**
	 * 根据id删除资产类型
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Map<String, Object> map)throws Exception;
    /**
	 * 添加资产类型
	 * @author 李帅
	 */
    int insert(SysAssetsType record)throws Exception;
    /**
	 * 查询所有资产类型
	 * @author 李帅
	 */
  	List<SysAssetsType> selectPrimAll()throws Exception;
  	/**
	 * 根据条件查询资产类型
	 * @author 李帅
	 */
    List<SysAssetsType> selectByExample(Map<String, Object> map)throws Exception;
    /**
	 * 根据id查询资产类型
	 * @author 李帅
	 */
    SysAssetsType selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改资产类型
	 * @author 李帅
	 */
    int updateByPrimaryKey(SysAssetsType record)throws Exception;
    /**
	 * 查询所有资产类型
	 * @author 李帅
	 */
    List<SysAssetsType> selectAll()throws Exception;
    
    /**
     * 根据父id查询
     * @author 李帅
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
    
    List<SysAssetsType> selectAssetsTypePareateId(Integer id);
}