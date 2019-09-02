$(function() {
	// 初始化左边页面,现在需要在页面中调用jtree_init
	// initleftside();

	// 调用jtree
	jtree_init();
	// 111
	$("#searchTreeBtn").bind("click", function() {
		if ($("#searchTree").val() != "") {
			$("#searchTreeClose").show();
			removeLi();
		}
		$('ul.tree').colExpAll({
			clickType : 'search'
		});
		removeLi();
	});
	//222
	$("#searchTree").bind("keydown", function(e) {
		if (e.keyCode == 13) {
			if ($("#searchTree").val() != "") {
				$("#searchTreeClose").show();
				removeLi();
			} else {
				$("#searchTreeClose").hide();
				removeLi();
			}
			$('ul.tree').colExpAll({
				clickType : 'search'
			});
			removeLi();
		}
	});
	$("#searchTreeClose").bind("click", function() {
		$("#searchTreeClose").hide();
		$("#searchTree").val("");
		$('ul.tree').colExpAll({
			clickType : 'close'
		});
		removeLi();
	});
})

function jtree_init() {
	var $p = $(document);
	$("ul.tree", $p).jTree();
}

$.fn.extend({
	size : function() {
		return this.length;
	}
})
function removeLi() {
	if($(".deptOne").length>0){
		for (var n = 0; n < $(".deptOne").length; n++) {
			if ($("#one" + n + " li").length == 0) {
				$("#one" + n).remove();
			}
		}
	}
	if($(".deptTwo").length>0){
		for (var m = 0; m < $(".deptTwo").length; m++) {
			if ($("#two" + m + " li").length == 0) {
				$("#two" + m).remove();
			}
		}
	}
	if($(".deptThree").length>0){
		for (var m = 0; m < $(".deptThree").length; m++) {
			if ($("#three" + m + " li").length == 0) {
				$("#three" + m).remove();
			}
		}
	}
	
	$("#treeDiv a").click(function(){
		$("#treeDiv a").css({"color":"#183152"});
		$(this).css({"color":"red"});
		$("#openTree").css({"color":"#183152"})
		 
	})
}
function removeAs() {
	if($(".typeOne").length>0){
		for (var n = 0; n < $(".typeOne").length; n++) {
			if ($("#one" + n + " li").length == 0) {
				$("#one" + n).remove();
			}
		}
	}
	if($(".typeTwo").length>0){
		for (var m = 0; m < $(".typeTwo").length; m++) {
			if ($("#two" + m + " li").length == 0) {
				$("#two" + m).remove();
			}
		}
	}
	if($(".typeThree").length>0){
		for (var m = 0; m < $(".typeThree").length; m++) {
			if ($("#three" + m + " li").length == 0) {
				$("#three" + m).remove();
			}
		}
	}
	
	$("#assetsDiv b").click(function(){
		$("#assetsDiv b").css({"color":"#183152"});
		$(this).css({"color":"red"});
		$("#assTree").css({"color":"#183152"})
		 
	})
}

