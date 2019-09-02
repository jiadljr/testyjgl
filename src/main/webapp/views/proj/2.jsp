<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
<style>
body,html{width:100%; height:100%; overflow:hidden;}
 .way1 {  cursor: pointer; display:block;  margin: 8px 10px;  float: left;  width: 70px; height:24px;  position: relative;  background: #1476d9;  border-radius: 4px;  line-height: 24px;  }
 .way1 input { cursor: pointer;  display: block;  width: 100%;  height: 45px;  opacity: 0;  position: absolute;  z-index: 3;  top: 0;  left: 0;  }
 .way1 a { cursor: pointer;  color:#fff;  display: block;  width: 100%; position: absolute;  z-index: 2;  top: 0;  left: 0;  text-align: center  }
 .btnList{ height:40px; line-height:40px; text-align:right;position:absolute; bottom:0; left:0;width:100%;}
 .btnList a{margin-right:20px;} 
</style>
<title>Insert title here</title>

</head>
<body>
	<div class="fileTab taskList">
		<a class="btn_base btn_strong btn_blue " style="font-size:14px; float:left;padding:5px 12px; margin-top:9px;margin-left:20px; "  >模版下载</a>
		    <div class="way1">
								<input type="file">
				<a href="javascript:;"> 文件导入</a>
	   </div>  									
	</div>
	<div class="btnList">
		<a class="btn_base btn_strong btn_blue "  >确认</a>
		<a class="btn_base btn_strong btn_red "  >取消</a>
		
	</div>
</body>
</html>