package com.qkby.analysis.business;

import java.util.List;
import com.qkby.analysis.entity.EventInfoSky;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午4:03:26 
 *     
 * @version </pre>
 */
public interface EventInfoSkyBusiness {
     List<EventInfoSky> selectSky() throws Exception;
}
