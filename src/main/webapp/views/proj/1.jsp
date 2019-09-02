<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<title>Insert title here</title>
<style type="text/css">
   .full_box{
        width: 1000px;height: 200px
          }
</style>
</head>
<body>
<div id="serCountByTime" class="full_box"></div>
<script type="text/javascript">
  $(function(){
	  $.ajax({
		  type:"post",
		  url:getPath()+"/selectProjectCapitalTypeCount.do",
		  dataType:"json",
		  async:false,
		  success:function(data){
			  console.dir(data);
		  }
	  })
	  var serCountByTime = echarts.init(document.getElementById('serCountByTime'));
	 var option = {
		    title : {
		        text: '某站点用户访问来源',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['蓝色','灰色','粉色','绿色','红色']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:eval(dataValue),
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	 serCountByTime.setOption(option);
  })
</script>
</body>
</html>