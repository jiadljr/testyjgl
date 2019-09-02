//跳转项目模板页面
function configProjTemplate(){
	location.href = getPath()+"/toProjTemplate.do";
}
//跳转项目类型页面
function configProjType(){
	var jump_url = getPath()+"/toProjTypeConfig.do";
	layer.open({
	    type: 2,
	    title: '项目类型设置',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['660px', '400px'],
	    content: [jump_url,'yes']
	  })
}
//跳转项目看板配置页面
function configProjBoard(){
	location.href = getPath()+"/toProjBoardConfig.do";
}
function configProjBoards(){
	window.open(getPath()+"/views/1.jsp");  
	
}