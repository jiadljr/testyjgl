$(function(){
	  var $list = $("#thelist1");
	  var fileSize = 0;  //总文件大小
	  var fileName = []; //文件名列表
	  var fileSizeOneByOne =[];//每个文件大小
	  var  uploader ;// 实例化   
	  uploader = WebUploader.create({ 
	         auto:false, //是否自动上传
	         pick: {
	              id: '#multi',
	              label: '点击选择文件',
	              name:"multiFile"
	          },
	          swf: '../../main/js/Uploader.swf',  
	          fileVal:'multiFile',              //和name属性配合使用
	          server: "Webuploader!ajaxAttachUpload2.action",  
	          duplicate:true, //同一文件是否可重复选择
	          resize: false,
	          formData: {
	              "status":"multi",
	              "contentsDto.contentsId":"0000004730",
	              "uploadNum":"0000004730",
	              "existFlg":'false'
	          },  
	          chunked: true,  //分片
	          chunkSize: 5 * 1024 * 1024,   //每片5M
	          chunkRetry:false,//如果失败，则不重试
	          threads:1,//上传并发数。允许同时最大上传进程数。
	          //fileNumLimit:50,//验证文件总数量, 超出则不允许加入队列
	          // runtimeOrder: 'flash',  
	          // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。  
	          disableGlobalDnd: true
	      });  

	     // 当有文件添加进来的时候
	     uploader.on( "fileQueued", function( file ) {
	      
	         $list.append( "<div id='"+  file.id + "' class='item'>" +
	             "<h4 class='info'>" + file.name + "</h4>" +
	             "<p class='state'>等待上传...</p>" +
	         "</div>" );
	     });

	     // 当开始上传流程时触发
	     uploader.on( "startUpload", function() {
	       
	       //添加额外的表单参数
	         $.extend( true, uploader.options.formData, {"fileSize":fileSize,"multiFileName":fileName,"fileSizeOneByOne":fileSizeOneByOne}); 
	     });

	     //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
	     uploader.on("uploadAccept",function(object,ret){
	         //服务器响应了
	         //ret._raw  类似于 data
	     
	     
	         var data =JSON.parse(ret._raw);
	         if(data.resultCode!="1" && data.resultCode !="3"){
	        if(data.resultCode == "9"){
	          alert("error");
	          uploader.reset();
	          return;
	        }
	      }else{
	        uploader.reset();
	        alert("error");
	      }
	    })

	     uploader.on( "uploadSuccess", function( file ) {
	         $( "#"+file.id ).find("p.state").text("已上传");
	     });

	     //出错之后要把文件从队列中remove调，否则，文件还在队里中，还是会上传到后台去
	     uploader.on( "uploadError", function( file,reason  ) {
	       $( "#"+file.id ).find("p.state").text("上传出错");
	   
	         //多个文件
	         var fileArray = uploader.getFiles();
	         for(var i = 0 ;i<fileArray.length;i++){
	             //取消文件上传
	              uploader.cancelFile(fileArray[i]);
	              //从队列中移除掉
	              uploader.removeFile(fileArray[i],true);
	        }
	         //发生错误重置webupload,初始化变量
	         uploader.reset();
	         fileSize = 0;
	         fileName = [];
	         fileSizeOneByOne=[];
	     });
	   //当validate不通过时，会以派送错误事件的形式通知调用者
	     uploader.on("error",function(){
	      
	    	 
	       uploader.reset();
	       fileSize = 0;
	         fileName = [];
	         fileSizeOneByOne=[];
	        alert("error");
	     })

	     /**
	     * 多文件上传
	     */
	    $("#multiUpload").on("click",function(){
	      uploader.upload();
	    })

	   /**
	    *取得每个文件的文件名和文件大小
	   */
	    //选择文件之后执行上传  
	    $(document).on("change","input[name='multiFile']", function() {
	          //multiFileName
	          var fileArray1 = uploader.getFiles();
	          var fileNames = [];
	           for(var i = 0 ;i<fileArray1.length;i++){
	              fileNames.push(fileArray1[i].name); //input 框用
	              //后台用
	              fileSize +=fileArray1[i].size;
	               fileSizeOneByOne.push(fileArray1[i].size);
	               fileName.push(fileArray1[i].name);
	            }
	          
	    })
	});
