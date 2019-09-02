package com.qkby.event.business;

import java.util.List;
import java.util.Map;

import com.qkby.event.entity.KbEventFileInfo;

public interface KbEventFileInfoBusiness {
	/**
	 * <pre>insert (新增中间表)
	 * Created by 家栋梁 on 2017年11月28日下午3:18:01  
	 *
	 * @param kbEventFileInfo
	 * @return</pre>
	 */
    public int insert(KbEventFileInfo kbEventFileInfo)throws Exception;
    /**
     * <pre>selectKbEventFileById (根据ID进行查询)
     * Created by 家栋梁 on 2017年11月28日下午6:50:11  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectKbEventFileById(int id)throws Exception;
}
