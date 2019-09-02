package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;
import com.qkby.sysmanage.entity.SysFileInfo;

public interface SysFileInfoDao {
	/**
	 * 下载
	 * @author 李帅
	 * @param id
	 * @return
	 */
	SysFileInfo downloadDealFile(int id)throws Exception;
	/**
	 * 查询上传处理的文件
	 * @author 李帅
	 * @param id
	 * @return
	 */
	List<SysFileInfo> selectDealFile(int id)throws Exception;
	//查询数量
    int countByExample(Map<String,Object> map)throws Exception;
    //根据条件删除
    int deleteByExample(Map<String,Object> paramMap)throws Exception;
    //根据Id删除
    int deleteByPrimaryKey(Map<String,Object> map)throws Exception;
    //添加
    int insert(SysFileInfo record)throws Exception;
    //根据条件查询
    List<SysFileInfo> selectByExample(Map<String,Object> map)throws Exception;
  	//根据Id查询
    SysFileInfo selectByPrimaryKey(Integer id)throws Exception;
    //根据Id修改
    int updateByPrimaryKeySelective(SysFileInfo record)throws Exception;
    //查询全部
    List<SysFileInfo> selectAll(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectByIdAll (根据ID进行查询所有地址)
     * Created by 家栋梁 on 2017年11月29日下午5:23:43  
     *
     * @param map
     * @return</pre>
     */
    SysFileInfo selectByIdAll(Map<String,Object> map)throws Exception;
    /**
     * 查询项目下的文件
     * 2018年2月27日 上午11:05:06
     * @author 李帅
     * @param projCode
     * @return
     * SysFileInfo
     */
    List<SysFileInfo> selectFileByProjCode(Map<String,Object> paramMap);
    
    /**
     * 查询删除本地文件的信息
     * 2018年4月18日 下午9:20:59
     * @李帅
     * @param
     */
    List<String> selectDeleteLocalFileList(List<Integer> idList);
}