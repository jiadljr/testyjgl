function getPath(){
	var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return projectName;
}

/**   
 *转换日期对象为日期字符串   
 * @param date 日期对象   
 * @param isFull 是否为完整的日期数据,   
 *               为true时, 格式如"2000-03-05 01:05:04"   
 *               为false时, 格式如 "2000-03-05"   
 * @return 符合要求的日期字符串   
 */    
 function getSmpFormatDate(date, isFull) {  
     var pattern = "";  
     if (isFull == true || isFull == undefined) {  
         pattern = "yyyy-MM-dd hh:mm:ss";  
     } else {  
         pattern = "yyyy-MM-dd";  
     }  
     return getFormatDate(date, pattern);  
 }

/**   
 *转换日期对象为日期字符串   
 * @param l long值   
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
 * @return 符合要求的日期字符串   
 */    
 function getFormatDate(date, pattern) {
     if (date == undefined || date == 'Invalid Date') {  
         date = new Date();  
     }  
     if (pattern == undefined) {  
         pattern = "yyyy-MM-dd hh:mm:ss";  
     }  
    
     return date.format(pattern);  
 } 
 function getFormatDates(date, pattern) {
     if (date == undefined || date == 'Invalid Date') {
         date = "";  
     }
     if (pattern == undefined) {
         pattern = "yyyy-MM-dd hh:mm:ss";  
     }
    
     return date.format(pattern);  
 } 
 
 
//js里面格式化日期
 /**
  * @param strTime 
  */
 function FormatDate (strTime) {
	 if(strTime==0||strTime===null){
		 return "";
	 }
     var date = new Date(strTime);
     return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
 }
 function FormatTime (strTime) {
	 if(strTime==0||strTime===null){
		 return "";
	 }
     var date = new Date(strTime);
     return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
 }
//js里面格式化日期
 /**
  * @param strTime 
  */
 function FormatDateTime (strTime) {
	 if(strTime==0||strTime===null){
		 return "";
	 }
     var date = new Date(strTime);
     return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
 }
 
//$('#next').click(function(){++start;searchFile(start);});
//$('#last').click(function(){--start;loadFileAll(start);});
function findfunc(){
	var url=getPath()+'/findfunc.do';
	var rdata;
	$.ajax({
        type: "post",
        url: url,
        async: false,
        data: "",
        dataType: "json", 
        success: function(result){
        	if(result.state==0){
    			 rdata=result.data;
    		}else{
    			alert(result.message);
    		}
        	}  
       }); 
	return rdata;
} 
 
 
 
 /**   
  * Date Format 
  * @param style date format like 'yyyyMMdd'
  */   
 Date.prototype.format = function(style) {
   var o = {   
     "M+" : this.getMonth() + 1, //month   
     "d+" : this.getDate(),      //day   
     "h+" : this.getHours(),     //hour   
     "m+" : this.getMinutes(),   //minute   
     "s+" : this.getSeconds(),   //second   
     "w+" : "日一二三四五六".charAt(this.getDay()),   //week   
     "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter   
     "S"  : this.getMilliseconds() //millisecond   
   }   
   if(/(y+)/.test(style)) {   
 	style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));   
   }
   for(var k in o){
     if(new RegExp("("+ k +")").test(style)){   
       style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));   
     }
   } 
   return style;
 };

 
 	//录入框的校验样式显示
 	function showTooltips(msgid,msg){
		if (msgid==''){ return; }
		if (msg==''){ msg='Error!'; }
		$('#'+msgid).prepend("<div class='for_fix_ie6_bug' style='position:relative;'><div class='tooltips_main'><div class='tooltips_box'><div class='tooltips'><div class='msg'>"+msg+"</div></div><div class='ov'></div></div></div></div>");
		$('#'+msgid+' .tooltips_main').fadeIn("slow").animate({ marginTop: "-23px"}, {queue:true, duration:400});
	}
 	//录入框的校验样式隐藏
	function hideTooltips(msgid){
		try{
			$('#'+msgid).find('.tooltips_main').fadeOut("slow");
			$('#'+msgid).find('.tooltips_main').remove();
		}catch(e){}
	}
	//取消全部录入框的校验样式
	function hideAllTooltips(){
		try{
			$('.tooltips_main').fadeOut("slow");
			$('.tooltips_main').remove();
		}catch(e){}
	}
	
	
	function checkParameter(spanId,labelId,Msg){
		var param = $.trim($("#"+labelId).val());
		if("" == param){
			showTooltips(spanId,Msg);
//			setTimeout(function(){
//				hideTooltips(spanId);
//			}, 1000*3);
		}else{
			hideTooltips(spanId);
		}
	}
	
//弹窗的关闭
	$("#can .close").click(function(){
		$('#can').empty().hide();
		$('.opacity_bg').empty().hide();
	})

//ie backspace的禁用
function banBackSpace(e){   
    var ev = e || window.event;//获取event对象   
    var obj = ev.target || ev.srcElement;//获取事件源   
    
    var t = obj.type || obj.getAttribute('type');//获取事件源类型  
    
    //获取作为判断条件的事件类型
    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');
    //处理null值情况
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;
    vEnabled = (vEnabled == null) ? true : vEnabled;
    
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readonly属性为true或enabled属性为false的，则退格键失效
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
                && (vReadOnly==true || vReadOnly=="readonly" || vEnabled!=true))?true:false;
   
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                ?true:false;        
    
    //判断
    if(flag2){
        return false;
    }
    if(flag1){   
        return false;   
    }   
}

    //禁止后退键 作用于Firefox、Opera
    document.onkeypress=banBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown=banBackSpace;
    
/*//阻止浏览器的默认时间
    function stopDefault(e){
    	if(e && e.preventDefault){
    		e.preventDefault(); 
    		}else{
    			window.event.returnValue = false;
    			} 
    	return false; 
    		} 
    stopDefault();*/
    
//设置input的行高
$(function(){
	  $("input[type]").each(function(){
	    var hei=$(this).height();
	    $(this).css("line-height",hei + "px");
	  })
	  
	  //表单的文本验证
	  $(".ver-null").on("blur",function(){
		if($(this).val()==""){
			var error=$(this).attr("data-message");
			$(this).val(error);
			$(this).addClass("error");
		}		
	}).on("focus",function(){
		var error=$(this).attr("data-message");
		if($(this).val()=="" || $(this).val()==error){			
			$(this).val("");
			$(this).removeClass("error");
		}
	})
})
    	
//判断拿到的数据是否为空或未定义，展示时设置为空
function isnull(str){
	if (str == null || str == undefined) {
		str = '';
		return str;
	}
	return str;
}
//弹窗的关闭
function closeWin(){
	$(".check-win").hide();
	$(".mask").hide();
}
//table的mouseover出现title
$(function(){
	$("tbody").on("mouseover","td",function(){
		var x1=$(this).text();
		$(this).attr("title",x1);
		$(this).children("a").parents("td").attr("title","");
		if($(this).children().length>0)
			$(this).children().parents("td").attr("title","");
	})
})
/*
 * 正则手机号验证
 */
function checkPhone(){ 
    var phone = document.getElementById('userCall').value;
    if (phone == "") {
    	$("#userCall").attr("data-message","手机号不得为空");
	}else {
		if(!(/^1[34578]\d{9}$/.test(phone))){ 
	    	$("#userCall").addClass("error");
	    	return false;
	    }else{
	    	$("#userCall").removeClass("error");
	    }
	}
}
/*
 * 正则身份证号验证
 */
function checkUuid(){
	var uuid = document.getElementById('userUuid').value;
	if (uuid == "") {
		$("#userUuid").attr("data-message","身份证号不得为空");
	}else {
		if(!(/^\d{17}([0-9]|X)$/.test(uuid))){
			$("#userUuid").addClass("error");
			return false;
		}else{
			$("#userUuid").removeClass("error");
		}
	}
}
function indexs(){
	return "456";
}

/**
 * 去掉空格
 * @param str
 * @returns
 */
function Trim(str)
{
    return str.replace(/(^\s*)|(\s*$)/g, "");
}