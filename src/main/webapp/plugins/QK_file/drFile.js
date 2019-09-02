function drAddFile(fileId){
	$(fileId).empty();
	const fileDiv="<p class='drHandle'><span>添加文件:</span><a href='#' id='drBtnFile'>浏览</a></p><div class='drInputDiv'></div><div class='drTextDiv'></div>";
	$(fileId).append(fileDiv);
	let i=0;
	let tpl="";
	$("#drBtnFile").click(function() {
		let index=i++;
		tpl = "<input type='file' name='file" + index + "' id='file" + index + "'/><br>";
		$(".drInputDiv").append(tpl);
		$('#file' + index + '').click();
		$('#file' + index + '').change(function () {
			let file= document.getElementById("file"+index+"").files[0];
			let tpFile="<p id='del"+index+"' class='drFileList' ><a href='javascript:;' class='drFileItem'>"+file.name+"</a><a href='javascript:remove("+index+");' class='drFileDel' >删除</a></p>"
			$(".drTextDiv").append(tpFile);
			const len=$("#text .drFileItem").length;
			let drFileNameK='';
			for(let ks=0;ks<len-1;ks++){
				if(len=='1'){break;}
				drFileNameK=document.getElementById("file"+ks+"").files[0];
				if(file.name==drFileNameK.name){
				$('#file' + index + '').remove();
				$('#del'+index+'').remove();
			 	}else{}
			}
		})
	})
}
function remove(index) {
	$('#file' + index + '').remove();
	$('#del' + index + '').remove();
}
