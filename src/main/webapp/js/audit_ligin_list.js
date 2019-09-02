$(function(){
	loginInfo();
	operation();
    $("#button_log_search").click(function(){
    	loginInfo();
    })
    $("#button_search").click(function(){
    	operation();
    })
     })
     function loginInfo(){
	$("#pageTable").empty();
	$('#pageTable').bPage({
    		 url:getPath()+"/selectLoginAll.do",
    		 asyncLoad : true,
 		     serverSidePage : false,
 			 render:function(data){
    			 $("#DeptTbody").empty();
    				for(var i=0; i<data.selectLoginAll.length;i++){
    					var num = i+1; 
    					var tr="";
    					id = data.selectLoginAll[i].id;
    					userCode = data.selectLoginAll[i].userCode;
    					userCode=isnull(userCode);
    					userName = data.selectLoginAll[i].userName;
    					userName=isnull(userName);
    					dateLogin = data.selectLoginAll[i].dateLogin;
    					dateLoginOut = data.selectLoginAll[i].dateLoginOut;
    					ipLogin = data.selectLoginAll[i].ipLogin;
    					if(ipLogin == 'null'){
    						ipLogin = "";
    					}
    					macLogin = data.selectLoginAll[i].macLogin;
    					macLogin=isnull(macLogin);
    					tr="<tr>"
    						+ "<td>" + num +"</td>"
    						+ "<td>" + userCode +"</td>"
    						+ "<td>" + userName +"</td>"
    						+ "<td>" + getFormatDate(new Date(dateLogin), "yyyy-MM-dd hh:mm:ss") +"</td>";
    					   if(dateLoginOut == undefined){
    						   tr += "<td></td>"
		    						+ "<td>" + ipLogin +"</td>"
		    						+ "<td>" + macLogin +"</td></tr>";
    					   }else{
	    					   tr += "<td>" + getFormatDate(new Date(dateLoginOut), "yyyy-MM-dd hh:mm:ss") +"</td>"
		    						+ "<td>" + ipLogin +"</td>"
		    						+ "<td>" + macLogin +"</td></tr>";
    					   }
    					$("#DeptTbody").append(tr);
    				}
    				var mrh=$('.mRight').height();
    		    	$('.mRbot').height(mrh-180);
    		    	$('.l_audit .relTable').height(mrh-265)
    		        var mtHead= $('.l_audit .fixTable .table tr th');
    		    	
    		        var mtBody= $('.l_audit .relTable .table tr td');
    		        var mTBH=$('.l_audit .relTable').height()-20;
    		        for(var i=0;i<mtHead.length-1;i++){
    		            mtBody.eq(i).width(mtHead.eq(i).width());
    		        }
    		 },
	     params : function(){
		   var loginTime = $("#loginTime").val();
		   if(loginTime == '1'){
			   var loginStartTime = $("#startTime").val();
			   var loginEndTime = $("#endTime").val();
		   }else if(loginTime == '2'){
			   var logOutStartTime = $("#startTime").val();
			   var logOutEndTime = $("#endTime").val();
		   }
		    var staffContact = $("#staffContact").val();
		    var staffName = $("#staffName").val();
		    var clientIP = $("#clientIP").val();
		    var clientMac = $("#clientMac").val();
		    return {
		    	"loginStartTime":loginStartTime,
		    	"loginEndTime":loginEndTime,
		    	"logOutStartTime":logOutStartTime,
		    	"logOutEndTime":logOutEndTime,
		    	"staffContact":staffContact,
		    	"staffName":staffName,
		    	"clientIP":clientIP,
		    	"clientMac":clientMac
		    }
	    }
    	 })
}
function operation(){
	$("#pageTables").empty();
	$('#pageTables').bPage({
    		 url:getPath()+"/selectOperAll.do",
    		 asyncLoad : true,
 		     serverSidePage : false,
 			 render:function(data){	
 				 $("#operTbody").empty();
 				for(var i=0; i<data.selectOperAll.length;i++){
 					var num = i+1; 
 					var tr="";
 					id = data.selectOperAll[i].id;
 					id=isnull(id);
 					code = data.selectOperAll[i].code;
 					code=isnull(code);
 					nameUser = data.selectOperAll[i].nameUser;
 					nameUser=isnull(nameUser);
 					tableOper = data.selectOperAll[i].tableOper;
 					tableOper=isnull(tableOper);
 					typeOper = data.selectOperAll[i].typeOper;
 					typeOper=isnull(typeOper);
 					if(typeOper == 1){
 						typeOper = "新增";
 					}
 					if(typeOper == 2){
 						typeOper = "修改";
 					}
 					if(typeOper == 3){
 						typeOper = "删除";
 					}
 					dateOper = data.selectOperAll[i].dateOper;
 					dateOper=isnull(dateOper);
 					tr="<tr>"
 						+ "<td>" + num +"</td>"
 						+ "<td>" + code +"</td>"
 						+ "<td>" + nameUser +"</td>"
 						+ "<td>" + tableOper +"</td>"
 						+ "<td>" + typeOper +"</td>"
 						+ "<td>" + getFormatDate(new Date(dateOper), "yyyy-MM-dd hh:mm:ss") +"</td>"
 						+"</tr>";
 					$("#operTbody").append(tr);
 				}
 				var orh=$('.mRight').height();
		    	$('.mRbot').height(orh-180);
		    	$('.o_audit .relTable').height(orh-265)
		        var otHead= $('.perpens .table tr th');
		  
		        var otBody= $('.o_audit .relTable .table tr td');
		        
		        for(var i=0;i<5;i++){
		        	
		            otBody.eq(i).width(otHead.eq(i).width());
		        }
    		 },
	     params : function(){
	    	 var startTime = $("#startTimes").val();
	    	 var endTime = $("#endTimes").val();
	    	 var staffContact = $("#staffContacts").val();
	    	 var staffName = $("#staffNames").val();
	    	 var operationType = $("#operationTypes").val();
	    	 var operationTable = $("#operationTables").val();
	    	 return {
	    		 "startTime":startTime,
	    		 "endTime":endTime,
	    		 "staffContact":staffContact,
	    		 "staffName":staffName,
	    		 "operationType":operationType,
	    		 "operationTable":operationTable
	    	 }
	    }
    	 })
}