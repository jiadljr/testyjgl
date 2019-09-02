$(function(){	
	var parentTitle={};
	var sonTitle={};
	var parentTitle={
	        parentUrl:["a1.html"],
	        parentImgUrl:["01.jpg"],
	        parentName:["系统主页"]
	    };
	    var sonTitle={
	        url:["a1.html","a2.html","a1.html","a1.html","a1.html","a1.html","a1.html","a1.html","a1.html"],
	        imgUrl:["01.jpg","01.jpg","01.jpg","01.jpg","01.jpg","01.jpg","01.jpg","01.jpg","01.jpg"],
	        name:["1","2","3","4","5","6","7","8","9"]
	    };
	    for(i=0;i<parentTitle.parentUrl.length;i++){
	        var li=$("<li class='list-group-item text-center'>"
	            + "<a href='" +parentTitle.parentUrl[i] +"'><span class='glyphicon " +parentTitle.parentImgUrl[i] +"'></span>" +parentTitle.parentName[i] + "</a></li>");
	        for(j=0;j<sonTitle.url.length;j++){
	            if((j+1)%5==1){
	                var ul=$("<ul></ul>");
	            }
	            var node="<li class='list-group-item text-center'>"
	                + "<a href='" +sonTitle.url[j] +"'><span class='glyphicon " +sonTitle.imgUrl[j] +"'></span>" +sonTitle.name[j] + "</a></li>";
	            ul=ul.append(node);
	            if((j+1)%5==1){
	                li.append(ul);
	            }
	        }
	        $("#dh").append(li); 
	        } 
	    $("ul>li>ul").wrapAll("<div class='dropdown'></div>");
	    $("#dh>li").hover(function(){
			$(this).find(".dropdown").show();
		},function(){
			$(this).find(".dropdown").hide();
		});
		$(".dropdown").hover(function(){
			$(this).show();
		},function(){
			$(this).hide();
		})
	/*for(i=0;i<parentTitle.length;i++){
		var li="<li class='list-group-item text-center'>"
			+ "<a href='" +parentUrl +"'><span class='glyphicon " +parentImgUrl +"'></span>" +parentName + "</a></li>";
		for(j=0;sonTitle;j++){
			if((j+1)%5==1){
				var ul=$("<ul></ul>");
			}
			var node="<li class='list-group-item text-center'>"
				+ "<a href='" +url[j] +"'><span class='glyphicon " +imgUrl[j] +"'></span>" +name[j] + "</a></li>";
			ul.append(node);
			if((j+1)%5==0){
				li.append(ul);
			}
		}
		$("#dh").append(li);		
	}*/
})