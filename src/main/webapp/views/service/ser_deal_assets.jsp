<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/utilx.js"></script>
<style type="text/css">
body{ overflow:auto;}

#searchTreeBtn{　width:20px ;height:22px; margin:0; display:inline-block;position:absolute;right:0px; top:0px;}
#searchTree{ height:20px; border:1px solid #ccc; line-height:20px; margin:0; width:217px;}
.tree_icon{width:20px ; height:22px; border:1px solid #ccc; border-left:none; display:inline-block;background:url('<%=request.getContextPath() %>/images/treeSearch.png') no-repeat 4px 4px;}
.event_look{ width: 560px; margin:0 auto; height:410px;}
#sidebar{ width:240px; float:left; height:184px; border:1px solid #ccc; padding:5px; margin-top:5px;}
.tree_list{height:154px !important; overflow:auto;}
#searchMenu{margin-bottom:5px; position:relative;}
 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
.error{border-color:red !important;}
.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
#one{width:200px; height:180px; float:left}
#two{width:50px; height:180px; float:left}
#three{width:200px; height:180px; float:left}
.btn{width:50px; height:30px; margin-top:10px; cursor:pointer;}
.left_select{float:left; width:240px; margin-top:5px; padding:5px; border:1px solid #ccc;}
.left_select select{ width:240px;  height:150px; overflow:auto; border:none}
.check_select{float:right; margin-right:10px;margin-top:100px; }
.right_select{ float:right;width:220px; height:360px ; margin-top:5px; border:1px solid #ccc; }
.right_select p{ height:40px; width:100%; font-size:14px;line-height:40px; background:#f2f2f3;color:#a0a0a1;}
.right_select p span{  float:right;width:20px ; height:20px; margin:10px; border-left:none; display:inline-block;background:url('<%=request.getContextPath() %>/images/treeSearch.png') no-repeat 4px 4px;}
.right_select select{  height:310px ; width:220px ; border:none;overflow:auto; }
.btnList{ width:100%; height:32px; text-align:right; float:left; margin-top:10px;}
.btnList a{ color:#fff; border-radius:3px; display:inline-block; padding :4px 8px; font-size:14px; background:#007eff;}

</style>
</head>
<body>

<div class="event_look">

	<div id="sidebar">
		<div class="toggleCollapse">
			
			<div id="searchMenu">
               	<input id="searchTree" type="text" class="span2" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')">
               	<a id="searchTreeClose" title="清除条件" class="treeclosebtn hide"><i class="myicons iconfont icon-close tree-close"></i></a> <a id="searchTreeBtn" title="搜索菜单" class="treesearchbtn"><i class="myicons iconfont icon-search tree-search"></i><span class="tree_icon"></span></a> 
           	</div>            
        	<div class="accordion" fillSpace="sidebar">
           		<div class="accordionContent">
              		<ul id="a" class="tree treeFolder collapse">
                		<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openTree">资产类型</a>
                 			<ul id="bzmenus" class="bzmenus_sub"></ul>
                		</li>
              		</ul>
            	</div>
         	</div>
      	</div>
	</div>
 
	<div class="right_select">
			<p>　保存分组<span></span></p>
          <select multiple="multiple" id="select2" >
          	<c:if test="${assetsListAll != null}">
	         	<c:forEach items="${assetsListAll }" var="assets">
	          		<option value="${assets.assets }">${assets.prop }</option>
	         	</c:forEach>
          	</c:if>
          </select>
    </div>
    	<div class="check_select">
	 	 <span id="add">
          <input type="button" class="btn" value=">"/>
          </span><br />
         <span id="add_all">
          <input type="button" class="btn" value=">>"/>
          </span> <br />
         <span id="remove">
          <input type="button" class="btn" value="<"/>
          </span><br />
         <span id="remove_all">
          <input type="button" class="btn" value="<<"/>

          </span> 
	</div>
	<div class="left_select">
     	<select multiple="multiple" id="select1" ">
     	</select>
	</div>
	
	<div class="btnList">
		<a href="#" onclick="sureAssets()">确定</a>
	</div>
    

      
</div>


       
        
        

<script type="text/javascript">
//下拉框交换JQuery
$(function(){
	$("#searchTree").focus();
    //移到右边
    $('#add').click(function() {
    //获取选中的选项，删除并追加给对方
        $('#select1 option:selected').appendTo('#select2');
		selectReload();
    });
    //移到左边
    $('#remove').click(function() {
        $('#select2 option:selected').appendTo('#select1');
		selectReload();
    });
    //全部移到右边
    $('#add_all').click(function() {
        //获取全部的选项,删除并追加给对方
        $('#select1 option').appendTo('#select2');
		selectReload();
    });
    //全部移到左边
    $('#remove_all').click(function() {
        $('#select2 option').appendTo('#select1');
		selectReload();
    });
    //双击选项
    $('#select1').dblclick(function(){ //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected",this).appendTo('#select2'); //追加给对方
    });
    //双击选项
    $('#select2').dblclick(function(){
       $("option:selected",this).appendTo('#select1');
    });
	
	//动态变换第三个选择框的值
	$('#select1').dblclick(function(){
		selectReload();
	});

	//动态变换第三个选择框的值
	$('#select2').dblclick(function(){
		selectReload();
	});
	
	//select3初始化
	function selectReload(){
		$('#select3').empty();
		var obj = document.getElementById('select2');
		var options = obj.options;
		var opt = new Array();
		for(var i=0,len=options.length;i<len;i++){
			opt = options[i];
		   var option=new Option() ;  
		   option.innerHTML = opt.text;
		   option.value = opt.value;
		   $('#select3').append(option);   
		}
	}
	var select2Options = document.getElementById("select2").options
	if (select2Options[0].value == '') {
		$("#removeOpt").remove();
	}
});
</script>
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
					var type = "<li  class=\"typeOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType('"+typeOne[i].id+"')\">";
						type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
					$.each(typeTwo,function(j,two){
						if(typeOne[i].id == typeTwo[j].parentId){
							type += "<li class=\"typeTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType('"+typeTwo[j].id+"')\">";
							type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
							$.each(typeThree,function(k,three){
								if (typeTwo[j].id == typeThree[k].parentId) {
									type += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType('"+typeThree[k].id+"')\">";
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
	function queryType(id){
		var url = getPath()+"/selectAssetsTypeByThreeLayer.do";
		$.ajax({
			url:url,
			data:{'typeId':id},
			dataType:'json',
			type:'post',
			async:false,
			success:function(result){
				var select1=$("#select1").empty();
				var assets = result;
				var str = "";
				for (var i = 0; i < assets.length; i++) {
					var sure = true;
					var select2 = document.getElementById("select2").options;
					for (var j = 0; j < select2.length; j++) {
						if (assets[i].id == select2[j].value) {
							sure = false;
						}
					}
					if (sure) {
						str += "<option value="+assets[i].id+">"
								+ assets[i].name + "</option>";
					}
				}
				select1.append(str);
			}
		})
	}
	function sureAssets() {
		var arrayId = new Array(); //定义数组   
		var arrayName = new Array(); //定义数组   
		$("#select2 option").each(function() { //遍历所有option  
			var id = $(this).val(); //获取option值   
			var name = $(this).text(); //获取option值   
			if (id != '') {
				arrayId.push(id); //添加到数组中  
			}
			if (name != '') {
				arrayName.push(name);
			}
		})
		$('#assets_id',parent.document).val(arrayId);
		$('#property',parent.document).val(arrayName);
		//alert(array);
		var index = parent.layer.getFrameIndex(window.name); 
		parent.layer.close(index)
	}
</script>
</body>
</html>