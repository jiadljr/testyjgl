<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
<style type="text/css">
.QK_search{ width:100%; height:30px; position:relative;}
#searchTree{ height:20px; border:1px solid #ccc; line-height:20px; margin:0; width:240px;}

#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;}
.tree_list{height:154px !important; overflow:auto;}
#searchMenu{margin-bottom:5px; position:relative;}
 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
.error{border-color:red !important;}
.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
#one{width:200px; height:180px; float:left}
#two{width:50px; height:180px; float:left}
#three{width:200px; height:180px; float:left}
</style>
</head>
<body>
<div class="QK_search">
	<div id="searchMenu">
		<input type="hidden" name="idDept" id="idDept" value="${idDept }" /> 
	    <input id="searchTree" type="text" class="span2" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
	</div> 
	<div id="sidebar">
		<div class="toggleCollapse">
	       	<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
	          	<div class="accordionContent">
             		<ul id="a" class="tree treeFolder ">
	               		<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openTree">资产类型</a>
	                			<ul id="bzmenus" class="bzmenus_sub"></ul>
	               		</li>
             		</ul>
	           	</div>
	        </div>
	     </div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		
		var url = getPath()+"/selectAssetsTypeForTree.do";
		var typeOne;
		var typeTwo;
		var typeThree;
		$.ajax({
			url:url,
			data:{},
			type:'post',
			async:false,
			success:function(result){
				typeOne = result.assetsTypeOne;
				typeTwo = result.assetsTypeTwo;
				typeThree = result.assetsTypeThree;
				$.each(typeOne,function(i,one){
					var type = "<li  class=\"typeOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType("+typeOne[i].id+",\'"+one.name+"\')\">";
						type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
					$.each(typeTwo,function(j,two){
						if(typeOne[i].id == typeTwo[j].parentId){
							type += "<li class=\"typeTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType("+ typeTwo[j].id + ",\'"+two.name+"\')\">";
							type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
							$.each(typeThree,function(k,three){
								if (typeTwo[j].id == typeThree[k].parentId) {
									type += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+typeThree[k].id+",\'"+three.name+"\')\">";
									type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
								}
							})
							type+="</ul></li>"; 
						}
					})
					type += "</ul></li>"; 
					$(".bzmenus_sub").append(type);
					$("a").click(function(){
						$("a").css({"color":"#183152"});
						$(this).css({"color":"red"});
						$("#openTree").css({"color":"#183152"})
						 
					})
					 
				})
			}
		})
		removeLi();
		$("#openTree").click();
		$("#openTree").css({"color":"#183152"})
	})
	function queryType(id,name){
		$("#idDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
	}
	function showTree(){
		$("#treeDiv").show();
		$('body').bind("mousedown", onBodyDown);
	}
	function onBodyDown(event) { 
		   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
			$("#treeDiv").hide();
		   }  
		}
</script>
</body>
</html>