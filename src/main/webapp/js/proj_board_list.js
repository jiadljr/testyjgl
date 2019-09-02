var oldNum=[];//这是一个全局变量，为了记录原有的存储状态
var allArrs=[];
$(function(){
	$('input[name=allSelect]').click(function(){
		if($(this).is(":checked")==true){
			$.each(	$('input[name=item1]'),function(){
				allArrs.push($(this).parent().parent().attr("rel"))
			})
		}else{
			allArrs=[];
		}
	})
	$.ajax({		
        url :getPath()+"/selectProjectBoardProjectQuery.do",
        dataType : 'json',
        type : "post",
        traditional: true,//属性在这里设置
        success : function(result) {
        	for(var i=0;i<result.length;i++){
        		allArrs.push(result[i].id);
        		oldNum.push(result[i].id);
        	}        	
        },error:function(err){
        	alert('失败！');
        }
    });
		$("#sidebar").hide();		
		$("#searchTree").keyup(function(){
			if ($("#searchTree").val() != "") {
				$("#searchTreeClose").show();
				removeLi();
			} else {
				$("#searchTreeClose").hide();
				removeLi();
			}
			$('#a').colExpAll({
				clickType : 'search'
			});
			removeLi();
		})
		var url = getPath()+"/findAllDept.do";
		var deptOne;
		var deptTwo;
		var deptThree;
		var deptFour;
		$.ajax({
			url:url,
			data:{},
			type:'post',
			async:false,
			success:function(result){
			deptOne = result.deptOne;
			deptTwo = result.deptTwo;
			deptThree = result.deptThree;
			deptFour = result.deptFour;
			$.each(deptOne,function(i,one){
				var type = "<li  class=\"deptOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType("+deptOne[i].id+",\'"+one.name+"\')\">";
					type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
				$.each(deptTwo,function(j,two){
					if(deptOne[i].id == deptTwo[j].parentId){
						type += "<li class=\"deptTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType("+ deptTwo[j].id + ",\'"+two.name+"\')\">";
						type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
						$.each(deptThree,function(k,three){
							if (deptTwo[j].id == deptThree[k].parentId) {
								type += "<li class=\"deptThree\"><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+deptThree[k].id+",\'"+three.name+"\')\">";
								type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a><ul id=\"three"+k+"\">";
								$.each(deptFour,function(m,four){
									if (deptThree[k].id == deptFour[m].parentId) {
										type += "<li><a autobypy="+four.extend2 +"href=\"#\" onclick=\"queryType("+deptFour[m].id+",\'"+four.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+four.name+"</a>";
									}
								})
								type+="</ul></li>";
							}
						})
						type+="</ul></li>"; 
					}
				})
				type += "</ul></li>"; 
				$(".bzmenus_sub").append(type);
				$("#treeDiv a").click(function(){
					$("#treeDiv a").css({"color":"#183152"});
					$(this).css({"color":"red"});
					$("#openTree").css({"color":"#183152"})
					 
				})
				
			})}
		})
		removeLi();
		$("#openTree").click();
		$("#openTree").css({"color":"#183152"})
		
		$("#searchTree").one("click",function(){
		$.each($(".collapsable"),function(){
			$(this).click();
		})
		$(".last_collapsable").click();
	})
	$("#searchTree,#clearInput").mouseover(function(e){
		$("#clearInput").show();
		
	})

	$("#searchTree,#clearInput").mouseout(function(){
		$("#clearInput").hide()
	})

	$("#clearInput").click(function(){
		$("#sidebar a").css("color","#183152");
		$("#idDept").val("");
		$("#searchTree").val("");
		if($("#already").is(":checked")==true){
			runProjInfo();
		
		}else{
			showProjInfoList();
		}
	
	})
				 
	//根据项目名称查询
	$("#button_search").click(function(){	
		//参数只传项目名称
		$("#serach_proj_name").val("searchName");
		showProjInfoList();
	})
})
function queryType(id,name){
	$("#idDept").val(id);
	$("#searchTree").val(name);
	$("#treeDiv").hide();
	$("#sidebar").hide();
	showProjInfoList();
}
function showTree(){
	$("#treeDiv").show();
	$('body').bind("mousedown", onBodyDown);
	$("#sidebar").show();
}
function onBodyDown(event) {
   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
	$("#treeDiv").hide();
	$("#sidebar").hide();
   }
   if($("#searchTree").val() == ""){
	   $("#idDept").val("");
   }
}
//项目列表
function showProjInfoList() {
	$('#paging').empty();	
	$('#paging').bPage({
		url : getPath() + "/findProjectBoardList.do",
		// 开启异步处理模式
		asyncLoad : true,
		// 关闭服务端页面模式
		serverSidePage : false,
		// 数据自定义填充
		render : function(data) {
			//看板列表
			var projBody = $("#projBody").empty();
			var projInfoList = data.projInfoList;
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			$.each(projInfoList,function(k, projInfo) {
				var id=projInfo.id;
				var projCode=projInfo.projCode;
				var projSpeed = projInfo.projSpeed;
				projSpeed=Number(projSpeed*100).toFixed(0);
				projSpeed+="%";				
				var checked = ""; 		
				for(var k=0; k<allArrs.length;k++){
					if(id==allArrs[k]){
						checked="checked";
					}
				}
				var projTd = "<tr rel='"+id+"'>" +
					"<td>"+"<input type='checkbox' name='item1' "+checked+"><input type='hidden' name='id' value=\'"+projCode+"\'></td>"+
					"<td>"+ projInfo.projName+"</td>"+
					"<td>" +projSpeed+"</td>" +
					"<td>" +isnull(projInfo.extend1)+"</td></tr>" ;		
				projBody.append(projTd);
			})
			$('input[name=item1]').click(function(){
				if($(this).is(':checked')==true){
					allArrs.push($(this).parent().parent().attr("rel")-0);
				}else{
					for(var i=0; i<allArrs.length;i++){
						if(allArrs[i]==$(this).parent().parent().attr("rel")-0){
							delete allArrs[i];
						}
					}
				}
			})
			runWidth()		
		},
		params : function() {
			var searchName = $("#serach_proj_name").val();
			var projName = "";
			var projType = "";
			var idDept = "";
			var amtFrom = "";
			var projStatus = "";
			if(searchName =="searchName"){
				projName = $("#projName").val();
			}else{
				projType = $("#projType").val();
				idDept = $("#idDept").val();
				if($("#searchTree").val() == ""){
					idDept = "";
				}
				amtFrom = $("#amtFrom").val();
				projStatus = $("#projStatus").val();
			}
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			if(totalrow == "" || totalrow==null || totalrow==undefined){
				pages = "";
			}
			return {
				"projName" : projName,
				"projType": projType,
				"idDept": idDept,
				"amtFrom": amtFrom,
				"projStatus":projStatus,
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
			};
		}
	});
	$("#pages").val("");
	$("#serach_proj_name").val("");
}
function getProjType(){
	$.ajax({
		url:getPath()+"/findProjTypeList.do",
		dataType: "json",
		type:"post",
		success:function(data){
			var option = "";
			$.each(data,function(k,v){
				option += "<option value="+v.id+">"+v.name+"</option>";
			})
			$("#projType").append(option);
		}
	})	
}
//返回至项目配置页面
function cancelToProjConfig(){
	location.href = getPath() + "/toProjConfig.do";
}
//重置数据
function resetForm(){
	document.getElementById("form1").reset();
	$("#projName").val("");
	$("#serach_proj_name").val("");
	showProjInfoList();
	allArrs=oldNum;
}
//保存数据
function saveChecked(){
	var newNum=[];
	//获取全部选中数据
	$.each($('input[name=item1]'),function(){
		if($(this).is(':checked')==true){
			newNum.push($(this).parent().parent().attr("rel")-0);
		}
	})
	function unHaveArr(keepArr,unKeepArr){
		//返回前者有后者没有的数据
		var unArr="";
		for(var i=0;i<keepArr.length;i++){
			var j=0;
			for(var k=0;k<unKeepArr.length;k++){
				if(keepArr[i]!=unKeepArr[k]){
					j++;
					if(j==unKeepArr.length){
						unArr+=keepArr[i]+",";	
					}					
				}				
			}
		}
		unArr=unArr.substring(0,unArr.length-1)
		return unArr;
	}
	var changeData={
		addData:unHaveArr(allArrs,oldNum),
		delData:unHaveArr(oldNum,allArrs)
	}
	$.ajax({
        url :getPath()+"/updateProjectBoard.do",
        data :changeData,
        dataType : 'text',
        type : "post",
        traditional: true,//属性在这里设置
        success : function(result) {
        	alert("保存成功！")
        },error:function(err){
        	alert('失败！');
        }
    });
}
function unique(arr){
	var res =[];
	var json = {};
	for(var i=0;i<arr.length;i++){
		if(!json[arr[i]]){
			res.push(arr[i]);
			json[arr[i]] = 1;
		}
	}
	return res;
}
function runWidth(){
	var relTable=$('.relTable');
	var RH=$('body').height()-210;		     
	var relTable=$('.relTable');
	var relTab=$('.relTable .table');
	var thTab=$('.fixTable .table tr th');
	var relIconTab=$('.relTable .table .relIcon td');
	var tdTab=$('.relTable .table tr:eq(0) td');
	var len=thTab.length;			
	var relTabHeight=relTab.height();
	for(var thI=0;thI<(len);thI++){
		tdTab.eq(thI).width(thTab.eq(thI).width()) ;
		relIconTab.eq(thI).width(thTab.eq(thI).width());
	}
	if(RH<relTabHeight){
		for(var thI=0;thI<(len-1);thI++){
			tdTab.eq(thI).width(thTab.eq(thI).width()) ;	
			relIconTab.eq(thI).width(thTab.eq(thI).width()) ;

		}		
	}
}
function runProjInfo(){
	var searchName = $("#serach_proj_name").val();
	var projName = $("#projName").val();
	var projType = $("#projType option:selected").val();
	var idDept = $("#idDept").val();
	var amtFrom = $("#amtFrom option:selected").val();
	var projStatus = $("#projStatus option:selected").val();
	$.ajax({
		url:getPath()+"/findAllProjectList.do",
		data:{	"projName" : projName,
			"projType": projType,
			"idDept": idDept,
			"amtFrom": amtFrom,
			"projStatus":projStatus},
		dataType: "json",
		type:"post",
		success:function(data){
			
			$("#projBody").empty();
			var projTd="";	
			for(var i=0;i<data.length;i++){
	
				var id=data[i].id;
				var projCode=data[i].projCode;
				var projSpeed = data[i].projSpeed;
				projSpeed=Number(projSpeed*100).toFixed(0);
				projSpeed+="%";				
				var checked = ""; 
				console.log(id)
				console.log(allArrs)
				for(var k=0; k<allArrs.length;k++){				
					if(id==allArrs[k]){
						projTd += "<tr rel='"+id+"'>" +
						"<td>"+"<input type='checkbox' name='item1' checked /><input type='hidden' name='id' value=\'"+projCode+"\'></td>"+
						"<td>"+data[i].projName+"</td>"+
						"<td>" +projSpeed+"</td>" +
						"<td>" +data[i].extend1+"</td></tr>" ;	
					}
				}
					
			}
			$("#projBody").append(projTd);
			runWidth();
		}
	})
}
