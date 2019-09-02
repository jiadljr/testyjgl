function showFrontDesk(){

		
		$.ajax({
			data:{},
			dataType:'json',
			type : 'post',
			url:getPath()+"/selectFrontDesk.do",
			success:function(result){
				var titHeight=$('.title_bgk').height();
				//待受理
				var acceptList = result.acceptList;
				var dataCount=acceptList.length;
				 $("#acceptCount").text(acceptList.length);
			
				var spn=1;
				var cpn=1; 
				var dpn=1;
				/*
				 * 自动播放效果实现
				 * 生成分页
				 * 利用计时器循环分页
				 * 当播放到最后一页时
				 * 计数器调0
				 * 
				 * */
				$('.nextA').click(function(){
					if(spn>Math.ceil(dataCount/5)-1){
						return false;
					}else{
						spn++;
						acceptPage(spn);
						
					
					}
				})
				
				$('.prevA').click(function(){
					if(spn=='1'){
						return false;
					}else{
						spn--;	
						acceptPage(spn);
					}
				})
				acceptPage(1);
				function acceptPage(pn){
					var pageSize=5;
					var pageCount=Math.ceil(dataCount/pageSize);
					if(pn==0||pn>pageCount){
			            return;
			        }
			        var startPage = pageSize * (pn - 1);
			       
			        var acceptTbody = $("#acceptTbody").empty();
			        var endPage = pn<pageCount?pageSize * pn:dataCount;
			            for (var j =startPage; j < endPage; j++) {
			            	var accept = acceptList[j];
							var acceptId = accept.id;
							var dateCreate = accept.dateCreate;
							var acceptName = accept.eventName;
							var acceptDept = accept.extend1;
							dateCreate = isnull(dateCreate);
							acceptName = isnull(acceptName);
							acceptDept = isnull(acceptDept);
							var str = "<tr><td>"+getFormatDate(new Date(dateCreate), "yyyy-MM-dd hh:mm:ss")+"</td>"+
							"<td>"+acceptDept+"</td>"+
							"<td>"+acceptName+"</td>"+
							"<td><a href='javascript:checkAccept("
							+ acceptId
							+ ")' >受理</a></td>"
							"</tr>";
							acceptTbody.append(str);
							var waHeight=$('.waitTable').height();
						
							var newHeightC=waHeight-titHeight-25;
							var ndk=newHeightC/5+'px';
							$('.waitTable .table tr').height(newHeightC/5);
							$('.waitTable .table tr td').height(newHeightC/5);
							$('.waitTable .table tr td').css({'padding':'0','line-height':ndk});
			            }			           
				
				}
				
				$('.prevB').click(function(){
					if(cpn=='1'){
						return false;
					}else{
						cpn--;	
						surePage(cpn);
					}
				})
				//超时事件
				var overTimeList = result.overTimeList;
				var dateOverTime=overTimeList.length;
				$('.nextC').click(function(){
					if(dpn>Math.ceil(dateOverTime/5)-1){
						
						return false;
					}else{
						dpn++;
						overTimePage(dpn);
						
					
					}
				})
				
				$('.prevC').click(function(){
					if(dpn=='1'){
						return false;
					}else{
						dpn--;	
						overTimePage(dpn);
					}
				})
				
				$("#overTimeCount").text(dateOverTime);
				overTimePage(1);
				function overTimePage(tn){
					var pageSize=5;
					var pageCount=Math.ceil(dateOverTime/pageSize);
					if(tn==0||tn>pageCount){
			            return;
			        }
				    var startPage = pageSize * (tn - 1);
			        var endPage = tn<pageCount?pageSize * tn:dateOverTime;
					var overTimeTbody = $("#overTimeTbody").empty();
					for (var l=startPage; l < endPage; l++) {
						var overTime = overTimeList[l];
						var overTimeId = overTime.id;
						var dateCreate = overTime.dateCreate;
						var overTimeName = overTime.eventName;
						var overTimeDept = overTime.extend1;
						dateCreate = isnull(dateCreate);
						overTimeName = isnull(overTimeName);
						overTimeDept = isnull(overTimeDept);
						var str = "<tr><td>"+getFormatDate(new Date(dateCreate), "yyyy-MM-dd hh:mm:ss")+"</td>"+
									"<td>"+overTimeDept+"</td>"+
									"<td>"+overTimeName+"</td>"+
									"<td><a href='javascript:overTimeSee("
									+ overTimeId
									+ ")'  >查看</a></td>"
									"</tr>";
						overTimeTbody.append(str);
						var waHeight=$('.waitTable').height();
					
						var newHeightC=waHeight-titHeight-25;
						var ndk=newHeightC/5+'px';
						$('.waitTable .table tr').height(newHeightC/5);
						$('.waitTable .table tr td').height(newHeightC/5);
						
						$('.waitTable .table tr td').css({'padding':'0','line-height':ndk});
					
					}
				}
				//今日事件统计
				var todayEvent=result.todayEvent;
				var dateEvent=todayEvent.length;
				var todayTbody=$("#todayTbody");
				todayTbody.empty();
				$('#eve_new').html(dateEvent);
				var eve_deal_num=0;
				var eve_finsh_num=0;
				nums();
				function nums(){
					for (var i = 0; i < dateEvent; i++) {						
						var tEvent=todayEvent[i];
						var level=tEvent.eventLevel;
						level=isnull(level);
						var code=tEvent.eventCode;
						code=isnull(code);
						var name=tEvent.eventName;
						name=isnull(name);
						var dept=tEvent.extend1;
						dept=isnull(dept);
						var date=tEvent.dateCreate;
						date=isnull(date);
						var service=tEvent.extend2;
						service=isnull(service);
						var eventStatus=tEvent.eventStatus
						eventStatus=isnull(eventStatus)						
						if (eventStatus == 20) {								
							var status="申告";
						}
						if (eventStatus == 50) {
							var status="已受理";
							eve_deal_num++;
						}
						if (eventStatus == 61) {
							var status="已受理";
							eve_deal_num++;
						}
						if (eventStatus == 60) {
							var status="已处理";
							eve_deal_num++;
						}
						if (eventStatus == 70) {
							var satus = "已完成";
							eve_finsh_num++;
							eve_deal_num++;
						}
						if (eventStatus == 77) {
							var satus = "已完成";
							eve_finsh_num++;
							eve_deal_num++;							
						}
						  
					}

					$('#eve_deal').html(eve_deal_num);
					$('#eve_finsh').html(eve_finsh_num);
				} 
				
				var tpn=1;
					
				$('.nextD').click(function(){
					if(tpn>Math.ceil(dateEvent/7)-1){
						return false;
					}else{
						tpn++;
						TodayNewEvent(tpn);
				
					
					}
				})
				
				$('.prevD').click(function(){
					if(tpn=='1'){
						return false;
					}else{
						tpn--;	
						TodayNewEvent(tpn);
					}
				})
			
				TodayNewEvent(1);
				function TodayNewEvent(pms){
					todayTbody.empty();
					var pageSize=7;
					var pageCount=Math.ceil(dateEvent/pageSize);
					if(pms==0||pms>pageCount){
			            return;
			        }
				    var startPage = pageSize * (pms - 1);
			        var endPage = pms<pageCount?pageSize * pms:dateEvent;
					
					for (var i = startPage; i < endPage; i++) {
						
						var tEvent=todayEvent[i];
						var id = tEvent.id;
						var level=tEvent.eventLevel;
						level=isnull(level);
						var code=tEvent.eventCode;
						code=isnull(code);
						var name=tEvent.eventName;
						name=isnull(name);
						var dept=tEvent.extend1;
						dept=isnull(dept);
						var date=tEvent.dateCreate;
						date=isnull(date);
						var service=tEvent.extend2;
						service=isnull(service);
						var eventStatus=tEvent.eventStatus
						eventStatus=isnull(eventStatus)
						if (eventStatus == 20) {
							var status = "申告";
						}
						if (eventStatus == 29) {
							var status = "已撤销";
						}
						if (eventStatus == 50) {
							var status="已受理";
						}	
											
						if (eventStatus == 60) {
							var status="已处理";
							
						}
						if (eventStatus == 61) {
							var status="已受理";
							
						}
						if (eventStatus == 70) {
							var status="已完成";
							
						}
						if (eventStatus == 77) {
							var status="已完成";
														
						}
						var str="<tr onclick='checkEvent(\""+id+"\",\""+eventStatus+"\")'><td>"+code+"</td>"
							+"<td>"+name+"</td>"
							+"<td style='\width: 100px; \'>"+dept+"</td>"
							+"<td>"+getFormatDate(new Date(date), "yyyy-MM-dd hh:mm:ss")+"</td>"
							+"<td style='\width: 100px; \'>"+service+"</td>"
							+"<td style='\width: 80px; \'>"+status+"</td>"+
							"</tr>";
						todayTbody.append(str);
					}
					var NewEventHeight=$('.todayNewEvent').height();
					var fixtHeight=$('.fixTable .table').height();
					$('.fixTable').height(fixtHeight)
					var rpk=NewEventHeight-titHeight-fixtHeight-35;
					$('.relTable,.relTable').height(rpk);
					var uzi=(rpk/7)-2;
					var mpk=uzi+'px';
					$('.relTable .table tr').height(uzi);
					
					
					$('.relTable .table tr td').css({'padding':'0','line-height':mpk,'height':mpk});
					
					
				}
			
				//处理人未处理数量
				$("#tul").empty();
				var str = "";
				var notDealAll=result.notDealAll;
				for (var l = 0; l < notDealAll.length; l++) {
					var notDeal = notDealAll[l];
					var notDealName=notDeal.name;
					notDealName=isnull(notDealName);//处理人姓名
					var notDealCount = notDeal.extend1;
					notDealCount=isnull(notDealCount);//处理人未处理事件
					
					str+="<li title='"+notDealName+"'><i><span>"+notDealCount+"</span></i><b>"+notDealName+"</b></li>";
				}
				$("#tul").append(str);
				
				var wTulWidth=$('#tul li').width();
				$('#tul li').height(wTulWidth);
				//事件等级分布
				var levelList = result.levelList;
				/*
				 * 事件等级分布实现
				 * 创建数组 arrB arrA 分别为事件等级 以及对应Class名
				 * 遍历数组以及数据
				 * 2017/11/29
				 */
				var arrA=['red','orange','yellow','blue'];
				var arrB=['一','二','三','四'];
	
				$("#eventRank").empty();
				var LLstr='';
				for (var m = 0; m < levelList.length; m++) {
					LLstr+="<li class='"+arrA[m]+"' ><p class='topper'>"+levelList[m]+"</p><p>"+arrB[m]+"级事件</p></li>";
				}
				$("#eventRank").append(LLstr);
			}
			
		})
}
//受理
function checkAccept(eventId) {
	var front = "服务台首页";
	window.location.href = getPath() + "/eventAccept.do?eventId=" + eventId +"&front="+front;
}
//审核(待确定)
function checkNormal(eventId) {
	var front = "服务台首页";
	window.location.href = getPath() + "/checkEvent.do?eventId=" + eventId+"&front="+front;
}
//超时事件查看
function overTimeSee(eventId){
	var jump_url=getPath() + "/overTimeSee.do?eventId=" + eventId;
	 layer.open({
		    type: 2,
		    title: '超时事件查看',
		    scrollbar: true,
		    shadeClose: true,
		    area: ['670px', '400px'],
		    content: [jump_url,'yes']
		  })
}
function checkEvent(id,status){
	var jump_url=getPath()+"/checkEventOne.do?eventId="+id+"&status="+status;
	 layer.open({
		    type: 2,
		    title: '查看',
		    scrollbar: true,
		    shadeClose: false,
		    area: ['670px', '400px'],
		    content: [jump_url,'yes']

		  })
}