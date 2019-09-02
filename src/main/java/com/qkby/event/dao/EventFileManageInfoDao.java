package com.qkby.event.dao;

import java.util.List;
import java.util.Map;

import com.qkby.event.entity.EventFileManageInfo;

public interface EventFileManageInfoDao {
	/**
	 * <pre>insert 
	 * Created by 家栋梁 on 2017年10月20日下午1:55:23  
	 *
	 * @param eventFileManage
	 * @return</pre>
	 */
   int insert(EventFileManageInfo eventFileManage) throws Exception;
   /**
    * 删除处理文件表的文件
    * @author 李帅
    * @param fileId
    * @return
    */
   int deleteDealFileByFileId(int fileId) throws Exception;

   List<EventFileManageInfo> selectFileId(Map<String,Object> map) throws Exception;
}