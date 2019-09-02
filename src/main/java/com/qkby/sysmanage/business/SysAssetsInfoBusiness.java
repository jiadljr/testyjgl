package com.qkby.sysmanage.business;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysAssetsInfo;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月22日下午3:05:56 
 *     
 * @version </pre>
 */
public interface SysAssetsInfoBusiness {
	/**
	 * <pre>selectAll (查询全部)
	 * Created by 家栋梁 on 2017年9月25日上午9:35:53  
	 *
	 * @return</pre>
	 */
    public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>insert (新增)
     * Created by 家栋梁 on 2017年9月25日上午9:35:56  
     *
     * @param sysAssetsInfo
     * @return</pre>
     */
    int insert(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>deleteAssets (根据ID进行删除)
     * Created by 家栋梁 on 2017年9月25日上午11:58:44  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    int deleteAssets(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查找)
     * Created by 家栋梁 on 2017年9月25日下午1:03:33  
     *
     * @param id
     * @return</pre>
     */
    Map<String,Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>addAssets (跳转新增页面)
     * Created by 家栋梁 on 2017年9月25日下午2:24:18  
     *
     * @return</pre>
     */
    Map<String,Object> addAssets()throws Exception;
    /**
     * <pre>updateAssets (修改)
     * Created by 家栋梁 on 2017年9月25日下午3:20:59  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    int updateAssets(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>seleAssets (查找所有部门)
     * Created by 家栋梁 on 2017年9月25日下午5:13:37  
     *
     * @return</pre>
     */
    Map<String,Object> seleAssets()throws Exception;
    /**
     * <pre>seleAssById (根据ID进行查询)
     * Created by 家栋梁 on 2017年9月25日下午5:14:10  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    Map<String,Object> seleAssById(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * 根据资产类型查询资产
     * @author 李帅
     * @param typeId
     * @return
     */
    List<SysAssetsInfo> selectAssetsByThreeLayer(int typeId)throws Exception;
    /**
     * 资产导入
     * @author 李帅
     * @param list
     */
	int insertAssetsByList(List<List<String>> excelList,HttpServletRequest req)throws Exception;
	/**
	 * 下载模板文件
	 * 2017年12月29日 下午12:02:10
	 * @李帅
	 * @param path
	 * @throws IOException 
	 */
	void downLoadExcel(File f ,HttpServletResponse response)throws Exception;
}
 