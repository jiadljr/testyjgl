package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkby.exception.BusinessException;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.sysmanage.entity.SysFileInfo;

	/**
	 * 
	 * <pre>项目名称：Security_20170911    
	 * Created by 家栋梁 on 2017年9月20日下午5:30:12 
	 *     
	 * @version </pre>
	 */
	public interface SysFileInfoBusiness {
	/**
	 * <pre>selectAll (查询全部)
	 * Created by 家栋梁 on 2017年9月20日下午5:32:36  
	 *
	 * @param map
	 * @return</pre>
	 */
   public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>insert (新增)
    * Created by 家栋梁 on 2017年9月20日下午7:20:30  
    *
    * @return</pre>
 * @throws BusinessException 
    */
   public int insert(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>deleteByPrimaryKey (根据ID进行删除)
    * Created by 家栋梁 on 2017年9月21日下午12:12:45  
    *
    * @param id
    * @return</pre>
    */
   public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>selectByExample (根据条件进行查询)
    * Created by 家栋梁 on 2017年9月21日下午1:49:29  
    *
    * @return</pre>
    */
   public Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>updateByPrimaryKeySelective (修改)
    * Created by 家栋梁 on 2017年9月21日下午3:31:23  
    *
    * @param request
    * @param response
    * @return</pre>
    */
   public int updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response)throws Exception;	
   /**
    * <pre>selectAllFileType (查找全部文件类型)
    * Created by 家栋梁 on 2017年9月28日下午4:18:52  
    *
    * @return</pre>
    */
   Map<String,Object> selectAllFileType()throws Exception;
   SysFileInfo selectDealFile(int id)throws Exception;
   
   /**
    * 通用添加文件的方法
    * 2018年2月9日 上午11:39:40
    * @author 李帅
    * @param sysFile
    * @return
    * @throws Exception
    * int
    */
   public int insertGeneralFile(SysFileInfo sysFile)throws Exception;
   
   /**
    * 查询项目下的文件
    * 2018年2月27日 上午11:11:15
    * @author 李帅
    * @param projCode
    * void
    */
   public List<SysFileInfo> selectFileByProjCode(Map<String,Object> paramMap);
   
   /**
    * 根据条件删除文件
    * 2018年3月2日 下午1:26:55
    * @author 李帅
    * @param fileParamMap
    * @return
    * int
 * @throws Exception 
    */
   public int deleteByExample(Map<String, Object> fileParamMap) throws Exception;
   
   /**
    * 批量删除项目中的文件
    * 2018年3月2日 下午1:46:06
    * @author 李帅
    * @param fileParamMap
    * void
 * @throws Exception 
    */
   public int deleteProjFiles(Map<String, Object> fileParamMap) throws Exception;
   
   /**
    * 任务记录集合
    * 2018年3月9日 上午9:56:59
    * @author 李帅
    * @param valueOf
    * @return
    * List<ProjTaskRecord>
    */
   public List<TaskRecord> getTaskRecordList(Integer valueOf);
   
   /**
    * 删除本地文件
    * 2018年4月18日 下午8:42:24
    * @李帅
    * @param
 * @throws Exception 
    */
   public void deleteLocalFile(List<Integer> deleteFileIdList) throws Exception;
}
