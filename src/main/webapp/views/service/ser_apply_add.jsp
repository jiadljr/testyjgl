<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select2/category_filter.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select2/jQuery.Hz2Py-min.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_apply_list.js"></script>
	<script>
		function closeLon(){
			var index = parent.layer.getFrameIndex(window.name);  
			setTimeout(function(){parent.layer.close(index)}, 100); 
		}
	</script>
	<style type="text/css">
	.QK_search{ width:100%; height:30px; position:relative;}
	#searchTree{ height:20%; border:1px solid #ccc; line-height:20px; margin:0; width:100%;}
	.selDiv{ width:200px; height:30px; position:relative;}
	.selDiv #selInp{ width:100%; height:30px;  border-radius:4px; border:1px solid #aaa; }
	.selDiv #sellist{display:none; position:absolute; left:0; top:32px; z-index:10000; border-radius:0 0 5px 5px;}
	#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:99;}
	.tree_list{height:154px !important; overflow:auto;}
	#searchMenu{margin-bottom:5px; position:relative;}
	.accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
	.error{border-color:red !important;}
	.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
	#one{width:200px; height:180px; float:left}
	#two{width:50px; height:180px; float:left}
	#three{width:200px; height:180px; float:left}
	 *{margin:0;padding:0;}
	 	.btnHover:hover{text-decoration: none; color: #fff; opacity: 0.8;cursor: pointer;}
        .mRwell_box{
            width:100%;
            height: 100%;
            min-height: 353px;
            background: #f0f0f0;
        }
        .layui-layer-TipsG{ display:none;}
        label { margin: 0; font-weight: normal; text-align: right;}
            .mRwell_box .mRwell-con-tit-big{width:100%; height:24px; margin: 0; text-align: left; line-height: 24px; font-size: 12px; color: #fff;background: #3b85c7;}
        .mRwell-con-tit-big span{ float:right; display:inline-block; font-size:14px; margin-right:10px; height:24px; line-height:24px;cursor: pointer; }
        .mRwell_box .mRwell_inner{width:650px;box-shadow:0 5px 20px #d2d2d2;min-height: 320px; margin: 5px auto 7px auto;background: #fff; padding-top: 8px;}
        .mRwell_box  .mRwell_inner .mRwell_top{position: relative; width:640px;min-height: 305px; margin:0 auto; border: 1px solid #a2a0a0;box-sizing: border-box; }
        .mRwell_box  .mRwell_inner .mRwell_bot{position: relative; width: 640px ;min-height:210px ; margin: 8px 2px;border: 1px solid #a2a0a0;box-sizing: border-box;}
        .mRwell_box  .mRwell_inner .rel_tit{ display: inline-block; padding: 0 4px; background: #fff; color: #3d3d3d;position: absolute; left: 4px; top: -6px; font-size: 12px;height: 12px; line-height: 12px; }
        .row_item{ height: 30px; line-height: 30px; margin-top:20px; }
        .row_item label{width:25%; height: 30px; line-height: 30px;font-size: 12px; float: left; color: #3d3d3d; }
        .row_item input{ width: 25%; height: 30px; line-height: 30px;font-size: 12px; float: left; border-radius:3px; border:1px solid #bbb;}
        .row_item .row_model{width: 25%; height: 30px; line-height: 30px;font-size: 12px; border: none; float: left; }
        .row_item .row_model input{ text-align:left;   width: 100%; height: 30px; line-height: 30px;font-size: 12px; float: left; }
        .row_item .row_txt{width:70%; height: 60px; line-height: 40px; float: left;}
        .row_item .row_txt textarea{width: 100%;resize:none; line-height:20px; height: 100%;border-radius:4px; border-color:#bbb;}
        .row_item .row_tel{width: 20%;}

        .row_item select {width: 20%; height:30px;line-height:30px; float: left; text-align: center;}
        .row_item select option{ width: 20%;text-align: center;}
        .row_item .deal_model{width: 20%; float: left; }
        .row_item .deal_model select{width: 100%;float: left}
        .row_item .deal_model select option{ text-align: center;}
        .btnLast{ float: right;}
        .btn_bass{ height: 20px; line-height: 20px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
        .btn_add{background: #3b85c7;}
        .btn_close{ background: #e2614e; }
		 .QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;position:absolute;right:9px; top:12px; z-index:98; }
	 	.selDiv{ width:25%; float:left;}
	</style>	
</head>
<body>
		
	<form role="form" class="form-horizontal" id="form1">
        <div class="mRwell_box add_page">
			<input type="hidden" id="sonParameter" value="">
            <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
            <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
            <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
            <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
            <input type="hidden" name="token" value="${token}" />
            <div class="mRwell_inner">
                <div class="mRwell_top">
                    <span class="rel_tit">申告事件</span>

                    <div class="row_item">
						<label for="depart" class="">申告部门: </label>
						<div class="row_model" style="position: relative">
						    <span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${userOne.idDept }" /> 
							    <input id="searchTree" type="text" onChange="deptChange()" value="${userOne.extend2 }" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
							</div> 
							<select id="eventDept" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onChange="userChange()" size="5" >
							</select>
							<div id="sidebar">
								<div class="toggleCollapse">
							       	<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
							          		<div class="accordionContent" style="background-color: #ffffff">
							             		<ul id="a" class="tree treeFolder ">
							               		<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openTree">部门信息</a>
							                			<ul id="bzmenus" class="bzmenus_sub"></ul>
							               		</li>
							             		</ul>
							           	</div>
							        	</div>
							     	</div>
							</div>
						</div>
						</div>
					</div>
					<div class="row_item">
						<label for="userApply" class="">申告人: </label>
						<div id="selDiv" class="selDiv selDivTop" style="position:relative;">
							<span class="QK_arrow"></span>
							<input id="selInp" type="text" value="${user_name }" class="qkby_input" readonly style="background:#fff; width:100%;" >
							<select id="eventUser" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onchange="userChange()" size="5" >
								<c:forEach var="sfm" items="${selectUser}">
									<option value="${sfm.id }"
										<c:if test="${sfm.id eq user_id}"> selected="selected"</c:if>>${sfm.name }</option>
								</c:forEach>
							</select>
						</div>
						 <label for="" class="row_tel" >联系电话: </label>

						<c:if test="${userOne.cal != null}">
							<input type="text" id="callApply" name="callApply"
								value="${userOne.cal }" class="telClass" data-message="*电话不得为空">
						</c:if>
						<c:if test="${userOne.cal == null}">
							<input type="text" id="callApply" name="callApply"
								value="${dep_tel }" class="telClass" data-message="*电话不得为空">
						</c:if>

					</div>
                    <div class="row_item">
                        <label for="tit" >事件标题<span style="color:red;font-size:16px;">*</span>:　</label>
                        <input type="text" id="eventName" name="eventName" class="error_null error_one" data-message="事件标题不得为空！" >
                    </div>
                    <div class="row_item">
                        <label for="remark" class=" ">事件描述<span style="color:red;font-size:16px;">*</span>:　</label>
                        <div class="row_txt">
                          <textarea id="eventDesc" name="eventDesc" class="error_null" data-message="事件描述不得为空！"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="btnLast" >
                <a class="btn_bass btn_add btnHover" id="addConfirm" >保存</a>
                <a class="btn_bass btn_close btnHover" onclick="closeLon()">取消</a>
            </div>
				<div id="tipsBox" style="width:10px; height:10px; position: absolute; top:30px; right: 200px; "></div>
			
        </div>

    </form>
				
<div class="mRpage" id="page2"></div>
	<script type="text/javascript">
	$(function(){
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
	})

	function queryType(id,name){
		$("#idDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
		$("#sidebar").hide();
		document.getElementById("eventUser").options.length = 0;
		$.ajax({
			type : 'post',
			url : getPath() + "/selectDeptUser.do",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.selectUser != null) {
					var thirdSelect = document.getElementById("eventUser");
					for (var i = 0; i < result.selectUser.length; i++) {
						thirdSelect.add(new Option(result.selectUser[i].name,
								result.selectUser[i].id));
					}
					if ($('#eventUser').fireEvent)
					{
					$('#eventUser').fireEvent('onchange');
					}
					else
					{
					$('#eventUser').change();
					}
				}
				deptCall = result.tel;
				$("#callApply").val(result.tel);
			}
		})
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
		}
		$(function(){
			$(".qkby_input").click(function(){
				$(this).removeClass("error")
				var $qkbySel=$(this).next();
				$(this).next().show();
				$(this).next().click();
			
			})
			$(".qkby_sel option").click(function(){
			$(this).parent().hide();
			$(this).parent().prev().val($(this).text());
			})
			$(".qkby_sel").blur(function(){
				$(this).hide();		
			})
			$("#addConfirm").click(function(){
 				var eventUser = $("#eventUser").val();	
 				if(eventUser == "" || eventUser == null){
 					$("#selInp").addClass("error");
 					return false;
 				}
				if($(".error_null").val()==''||$("#eventDesc").val().length=='0'|| $(".error_null").hasClass("error")){
					$.each($(".error_null"),function(){
						if($(this).val()==""){
							var error=$(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				}
				$.ajax({
					type:"post",
					url:getPath() + "/addEventInfo.do",
					data:$("#form1").serialize(),
					traditional: true,
					success:function(result){
						if(result==1){
						  	window.parent.sunFun();
							var index = parent.layer.getFrameIndex(window.name);  
							setTimeout(function(){parent.layer.close(index);}, 50);
						}else if(result == 0){
							alert("新增数据失败");
						}
					},error:function(err){
						alert($.parseJSON(err.responseText).msg);
					}
				})
			})
			 $('#eventName').bind('input propertychange',function(){ //添加监听input值的改变事件
				   var tvalmum;
				     //统计input输入字段的长度
				   tvalnum = $(this).val().length;
				   //如果大于8个字直接进行字符串截取
				   if(tvalnum>50){
				     var tval = $(this).val();        
				     tval = tval.substring(0,50);        
				     $(this).val(tval);
				     alert('最多只能输入50个字符！'); 
				   }
				});
		})
	</script>
</body>
</html>