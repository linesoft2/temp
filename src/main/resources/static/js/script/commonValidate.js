var istrue = false;
function checkImageResolution(){//核对上传图片的分辨率
	   var imageState = false;
	   $("#form1").ajaxSubmit({
			type : "POST",
			async:false,
			dataType: "JSON",
			url : "${cp}/admin/shufflingFigure_checkResolution.action",
			success : function(rs) {
				if(rs.status==0){
					$("#messageErrorFile").html("");
					imageState = true;
				}else if(rs.status==1){
					$("#messageErrorFile").html("请上传指定分辨率的图片");
					imageState = false;
				}
			}
		});
	   return imageState;
}

function validateFileIsImage(obj){//验证上传文件是否为图片并验证像素
	fileType = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if(fileType!='.jpg' && fileType!='.png' ){
		$("#messageErrorFile").html("请上传后缀名为jpg或png的照片!");
		return false;
	}else
		return checkImageResolution();//验证上传图片的像素
}

function validateFileSize(obj){//验证上传文件大小
		 if(obj.value==""){ 
			 $("#messageErrorFile").html("请先选择上传文件");  
        return false;  
    }
		 
		 var maxsize = 10*1024*1024;//10M  
       var errMsg = "上传的附件文件不能超过10M！";  
       var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过10M，建议使用IE、FireFox、Chrome浏览器。";
	     var  browserCfg = {};  
    var ua = window.navigator.userAgent;  
    if (ua.indexOf("MSIE")>=1){  
        browserCfg.ie = true;  
    }else if(ua.indexOf("Firefox")>=1){  
        browserCfg.firefox = true;  
    }else if(ua.indexOf("Chrome")>=1){  
        browserCfg.chrome = true;  
    }
		 var filesize = 0;  
		 
    if(browserCfg.firefox || browserCfg.chrome ){  
        filesize = obj.files[0].size;  
    }else if(browserCfg.ie){
        var obj_img = document.getElementById('tempimg');  
        obj_img.dynsrc=obj.value;  
        filesize = obj_img.fileSize;  
    }else{  
   	 $("#messageErrorFile").html(tipMsg);  
        return false;  
    }  
    if(filesize==-1){  
   	 $("#messageErrorFile").html(tipMsg);  
        return false;  
    }else if(filesize>maxsize){  
   	 $("#messageErrorFile").html(errMsg);  
        return false;  
    }else{
        //文件大小符合要求 
        $("#messageErrorFile").html(""); 
        return true;  
    }  
}

function validateValue(id,errorId){//验证值是否为空
   	var str = $("#"+id).val();
   	if(str==null || str==""){
   		$("#"+errorId).html("必选字段");
   		istrue = false;
   	}else{
   		istrue= true;
   		$("#"+errorId).html("");
   	}
}

function validateLength(id,errorId,maxlen){//验证值得长度是否满足要求
   	var str = $("#"+id).val();
   	if(str==null || str==""){
   		$("#"+errorId).html("必选字段");
   		istrue = false;
   	}else{
   		if(str.length>maxlen){
   			$("#"+errorId).html("请输入一个长度最多是"+maxlen+"的字符串");
   			istrue = false;
   		}else{
   			$("#"+errorId).html("");
   			istrue = true;
   		}
   	}
}

function validateCkeditor(editorContent,errorId){//验证富文本内容是否为空
  	 if(editorContent==null || editorContent==""){
  		 $("#"+errorId).html("必选字段");
  		 istrue = false;
  	 }else{
  		 $("#"+errorId).html("");
  		 istrue = true;
  	 }
}

/**富文本关联的点击事件**/
function editor1F() {
    CKEDITOR.tools.setTimeout(function () {  
    	$("#messageError1").html("");
    }, 0);  
} 
function editor2F() {
    CKEDITOR.tools.setTimeout(function () {  
    	$("#messageError2").html("");
    }, 0);  
} 
function editor3F() {
    CKEDITOR.tools.setTimeout(function () {  
    	$("#messageError3").html("");
    }, 0);  
} 
function editor4F() {
    CKEDITOR.tools.setTimeout(function () {  
    	$("#messageError4").html("");
    }, 0);  
} 
/**富文本关联的点击事件**/


