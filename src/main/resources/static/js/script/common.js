function isMobil(s) {
	var patrn = /^0{0,1}(18[0-9]|13[0-9]|15[0-9])[0-9]{8}$/;
	if (!patrn.exec(s)) {
		return false;
	}
	return true;
}
function isEmail(s) {
	var patrn = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	if (!patrn.exec(s)) {
		return false;
	}
	return true;
}
function isWord(value) {
	var regPex = /^[\u4e00-\u9fa5]*$/;
	if (!regPex.exec(value)) {
		return false;
	}
	return true;
}

function setButtonDisabledTimeOutById(objId, time) {
	$("#" + objId).attr("disabled", true);
	time--;
	if (time > 0) {
		$("#" + objId).val("倒计时" + time + "s");
		setTimeout(
				"setButtonDisabledTimeOutById('" + objId + "'," + time + ")",
				1000);
	} else {
		$("#" + objId).val("获取验证码");
		$("#" + objId).attr("disabled", false);
	}
}
function setButtonDisabledTimeOutByClass(objClass, time) {
	$("." + objClass).attr("disabled", true);
	time--;
	if (time > 0) {
		$("." + objClass).val("倒计时" + time + "s");
		setTimeout("setButtonDisabledTimeOutByClass('" + objClass + "'," + time
				+ ")", 1000);
	} else {
		$("." + objClass).val("获取验证码");
		$("." + objClass).attr("disabled", false);
	}
}

function alert_message(msg, isredirect, url) {
	$.blockUI({
		theme : true,
		title : '提示',
		message : '<p>' + msg + '</p>',
		timeout : 1000,
		onUnblock : function() {
			if (isredirect) {
				if (url == null || url == "") {
					location.reload();
				} else {
					window.location.href = url;
				}
			}
		}
	});
}

// ---------------操作公共方法-------------

/**
 *
 * @param optionId
 *            操作按钮id
 * @param url
 *            操作地址
 * @param msg
 *            提示信息（可选）
 *
 */
function update(optionId, url, msg) {
	$("#" + optionId).click(function() {
		var checks = $("input[name='check']:checkbox:checked");
		if (checks.length != 1) {
			if (msg == undefined) {
				layer.msg("只能选择一项进行修改");
			} else {
				layer.msg(msg);
			}

			return false;
		} else {
			var id = checks.val();
			// alert(id);
			window.location.href = url + "?id=" + id;
		}
	});
};

/**
 *
 * @param optionId
 *            操作按钮id
 * @param url
 *            操作地址
 * @param msg
 *            提示信息（可选）
 *
 */
function detail(optionId, url, msg) {
	$("#" + optionId).click(function() {
		var checks = $("input[name='check']:checkbox:checked");
		if (checks.length != 1) {
			if (msg == undefined) {
				layer.msg("只能选择一项进行查看");
			} else {
				layer.msg(msg);
			}

			return false;
		} else {
			var id = checks.val();
			// alert(id);
			window.location.href = url + "?id=" + id;
		}
	});
};

/**
 * 删除操作方法
 *
 * @param optionId
 *            操作id
 * @param url
 *            地址
 * @param msg
 *            提示信息（可选）
 */
function delObj(optionId, url, msg) {

	$("#" + optionId).click(
			function() {
				var checks = $("input[name='check']:checkbox:checked");
				if (checks.length == 0) {
					if (msg == undefined) {
						alert("请选择要删除的数据");
					} else {
						alert(msg);
					}
					return false;
				} else {
					if (confirm("确定要删除数据么？")) {
						var ids = "";
						for (var i = 0; i < checks.length; i++) {
							ids += checks[i].value + ",";
						}
						window.location.href = url + "?ids="
								+ ids.substring(0, ids.length - 1);
					}
				}

			});

}

/**
 * 返回操作
 */
function goBack() {
	history.go(-1);
}

/**
 * 批量操作方法
 *
 * @param optionId
 *            操作按钮
 * @param url
 *            操作地址
 * @param configmsg
 *            询问提示信息
 * @param errMsg
 *            错误提示信息
 */
function beatchOption(optionId, url, configmsg, errMsg) {
	$("#" + optionId).click(
			function() {
				var checks = $("input[name='check']:checkbox:checked");
				if (checks.length == 0) {
					layer.msg(errMsg);
					return false;
				} else {
					layer.confirm(configmsg, function(){
						var ids = "";
						for (var i = 0; i < checks.length; i++) {
							ids += checks[i].value + ",";
						}
						window.location.href = url + "?ids="
								+ ids.substring(0, ids.length - 1);
					}, function(){


					});

				}

			});
};

// 兼容火狐、IE8
// 显示遮罩层
function showMask() {
	$("#mask").css("height", $(document).height());
	$("#mask").css("width", $(document).width());
	$("#mask").show();
}
// 隐藏遮罩层
function hideMask() {
	$("#mask").hide();
}

function beathAjaxOption(optionId, _url, configmsg, errMsg, selectUrl) {

	$("#" + optionId).click(function() {
		var checks = $("input[name='check']:checkbox:checked");
		if (checks.length == 0) {
			layer.msg(errMsg);
			return false;
		} else {

			layer.confirm(configmsg, {icon: 3}, function(index){
				var index = layer.load();
				var ids = "";
				for (var i = 0; i < checks.length; i++) {
					ids += checks[i].value + ",";
				}

				$.ajax({
					type : "post",
					url : _url,
					dataType : "json",
					data : "ids=" + ids.substring(0, ids.length - 1),
					success : function(data) {
						layer.close(index);
						layer.msg(data.msg);
						if(data.status == 0){
							window.location.href = selectUrl;
						}

					}
				});

			});
			/*if (confirm(configmsg)) {
				var ids = "";
				for (var i = 0; i < checks.length; i++) {
					ids += checks[i].value + ",";
				}

				showMask();
				$.ajax({
					type : "post",
					url : _url,
					dataType : "json",
					data : "ids=" + ids.substring(0, ids.length - 1),
					success : function(msg) {
						hideMask();
						layer.msg(msg.data);
						window.location.href = selectUrl;
					}
				});

			}*/
		}

	});

}

/**
 * 异步提交
 * @param optionId 操作id
 * @param _url 操作地址
 * @param configmsg 询问信息
 * @param errMsg 错误信息
 * @param selectUrl 查询地址
 * @param otherValue 排除值
 * @param otherMsg 排除错误信息
 */
function beathAjaxOption2(optionId, _url, configmsg, errMsg, selectUrl,otherValue,otherMsg) {

	$("#" + optionId).click(function() {
		var checks = $("input[name='check']:checkbox:checked");
		if (checks.length == 0) {
			layer.msg(errMsg);
			return false;
		} else {

			layer.confirm(configmsg, {icon: 3}, function(index){
				var index = layer.load();
				var ids = "";
				var flag = true;
				for (var i = 0; i < checks.length; i++) {
					ids += checks[i].value + ",";
					if(checks[i].id==otherValue){
						flag = false;
						layer.close(index);
						layer.msg(otherMsg);
						break;
					}
				}

				if(flag)
				$.ajax({
					type : "post",
					url : _url,
					dataType : "json",
					data : "ids=" + ids.substring(0, ids.length - 1),
					success : function(data) {
						layer.close(index);
						layer.msg(data.msg);
						if(data.status == 0){
							window.location.href = selectUrl;
						}

					}
				});

			});

		}

	});

}


/**
 * 全选操作
 *
 * @param checkAll
 *            全选操作id
 * @param check
 *            单个checkbox 的name
 */
function checkAll(checkAll, check) {
	$("#" + checkAll).click(function() {
		$("input[name='" + check + "']").attr("checked", this.checked);
	});
};


/**
 * 初始化树
 *
 * @param id
 *            初始化id
 * @param _url
 *            远程连接地址
 */
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e,xhr,options){
	xhr.setRequestHeader(header,token);
});
function myInitTree(id, _url) {

	$.ajax({
		type : "POST",
		url : _url,
		dataType:'json',
		success : function(zNodes) {
			console.log(zNodes);
			// 初始化树
			$(document).ready(function() {
				$.fn.zTree.init($("#" + id), setting, zNodes);
			});

		}
	});
}
/**
 * 添加收藏方法
 *
 * @param optionId
 *            操作按钮id
 * @param targetId
 *            收藏id
 * @param targetType
 *            收藏类型 1：资源 2：资讯 3：课程 4 ：主题
 * @param url
 *            路径 参数自带
 */
function userCollection(optionId, targetId, targetType, url) {
	$("#" + optionId).click(function() {
		$.ajax({
			type : "post",
			url : "courseweb_collection.action",
			data : "targetId=" + targetId + "&targetType=" + targetType,
			success : function(msg) {
				alert(msg.data);
				window.location.href = url;
			}
		});
	});
}
/**
 * 取消收藏方法
 *
 * @param optionId
 *            操作按钮id
 * @param targetId
 *            收藏id
 * @param targetType
 *            收藏类型 1：资源 2：资讯 3：课程 4 ：主题
 * @param url
 *            路径 参数自带
 */
function userCanCollection(optionId, targetId, targetType, url) {
	$("#" + optionId).click(function() {
		$.ajax({
			type : "post",
			url : "courseweb_canCollection.action",
			data : "targetId=" + targetId + "&targetType=" + targetType,
			success : function(msg) {
				alert(msg.data);
				window.location.href = url;
			}
		});
	});
}
/**
 * 添加分享方法
 *
 * @param optionId
 *            操作按钮id
 * @param targetId
 *            分享id
 * @param targetType
 *            分享类型 1：资源 2：资讯 3：课程 4 ：主题
 */
function userSharea(optionId, targetId, targetType) {
	$("#" + optionId).click(function() {
		$.ajax({
			type : "post",
			url : "courseweb_addShare.action",
			data : "targetId=" + targetId + "&targetType=" + targetType,
			success : function(msg) {
				layer.msg(msg);
			}
		});
	});
}
/**
 * 异步取消关注和添加关注
 * @param atteId 被关注id
 * @param status 是否关注  0为取消关注，1为关注
 * @param optionId 操作id
 * @param imgesId 图片id
 * @param imgesattr1 加关注 imgesattr2取消关注 图片地址
 */
function addandremattention(optionId,atteId,status,imgesId,imgesattr1,imgesattr2){
	$("#"+optionId).click(function(){
		$.ajax({
			type : "post",
			url : "vip_addAttention2.action",
			dataType : "json",
			data : "attentionId="+atteId+"&atteflag="+status,
			success : function(msg) {
				if(msg.status==2){
					alert(msg.msg);
					window.location.href="/index.jsp";
				}else {
					if(status == 0){
						$("#"+imgesId).attr("src",imgesattr1);
						status = (status+1)%2;
						addandremattention(optionId,atteId,status,imgesId,imgesattr1,imgesattr2);
					}else if(status == 1){
						$("#"+imgesId).attr("src",imgesattr2);
						status = (status+1)%2;
						addandremattention(optionId,atteId,status,imgesId,imgesattr1,imgesattr2);
					}
					//alert(msg.msg);
				}
			}
		});
	});

}
/**
* 截取字符串，classStr 按类截取，many截多少，where：从哪截取。split  替换成什么字符串
*/
	function mySubString(classStr,many,where,split){
		var contents = $("."+classStr);
		for(var i = 0;i<contents.length;i++){
			var str = $.trim(contents[i].innerHTML);
			if(str.length>where){
				str = str.replace(/[\r\n]/g,",");
				str = str.substring(0,many);
				str += split;
				contents[i].innerHTML=str;
			}
		}
	}

/**
 * 收藏或取消收藏，不跳转方法
 * @param optionId  操作id
 * @param collId	收藏id
 * @param status	0，取消收藏1，收藏
 * @param imgesId	图片id
 * @param imgesattr1	图片路径
 * @param imgesattr2	图片路径
 * @param targetType	收藏类型 1：资源 2：资讯 3：课程 4 ：主题
 * @param fontId 	需要显示文字的话这里传文字id
 * @param font1 	收藏
 * @param font2  	取消收藏
 */
function addAndRemoveUserCollection(optionId,collId,status,targetType,imgesId,imgesattr1,imgesattr2,fontId,font1,font2){

	$("#"+optionId).click(function(){

		$.ajax({
			type : "post",
			url : "courseweb_addAndRemoveUserCollection.action",
			dataType : "json",
			data : "collId="+collId+"&collFlag="+status+"&targetType="+targetType,
			success : function(msg) {
				if(msg.status==2){
					alert(msg.msg);
					window.location.href="/index.jsp";
				}else {
					if(status == 0){
						$("#"+imgesId).attr("src",imgesattr1);
						if(fontId != undefined){
							$("#"+fontId).text(font1);
						}
						status = (status+1)%2;
						addAndRemoveUserCollection(optionId,collId,status,targetType,imgesId,imgesattr1,imgesattr2,fontId,font1,font2);
					}else if(status == 1){
						$("#"+imgesId).attr("src",imgesattr2);
						if(fontId != undefined){
							$("#"+fontId).text(font2);
						}
						status = (status+1)%2;
						addAndRemoveUserCollection(optionId,collId,status,targetType,imgesId,imgesattr1,imgesattr2,fontId,font1,font2);
					}
				}
			}
		});
	});
}



/**
 * 加载按钮
 * @author 孙立保
 * @param menuId 菜单id
 */
function initButton(menuId){
	$.ajax({
		type : "post",
		url : "${cp}/admin/system_loadButton.action",
		dataType : "html",
		data : "roleCode="+menuId,
		success : function(msg) {
			$("#myButton").html(msg);
		}
	});
}



//var commonIP = "http://182.92.176.140:8480/blxServer";

/**
 * 上传文件并且预览
 * @param fileId 上传文件file的id
 * @param imgId 预览图片id
 * @param picture 接收路径的隐藏表单
 */
function uploadFile(fileId,imgId,picture,IP){

	$("#"+fileId).live("change",function () {
        $.ajaxFileUpload ({
             url:IP+"/common/uploadFile.action",
             // url: '/upload.aspx', //用于文件上传的服务器端请求地址
             secureuri: false, //是否需要安全协议，一般设置为false
             fileElementId: fileId, //文件上传域的ID
             //contentType: "application/x-www-form-urlencoded",//(可以)
             // contentType: "application/json; charset=utf-8",//(可以)
             // contentType: "text/xml",//(可以)
             contentType: "application/json; charset=utf-8",//(可以)
             dataType: 'json', //返回值类型 一般设置为json
             success: function (data, status) { //服务器成功响应处理函数
            	  $("#"+imgId).show();
                  $("#"+imgId).attr("src", "/static/"+data.data);
                  console.log(picture);
                  $("#"+picture).attr("value",data.data);
             },
             error: function (data, status, e){//服务器响应失败处理函数

             }
        });

    });


};

/**
 * 上传文件到七牛云并且预览
 * @param fileId 上传文件file的id
 * @param imgId 预览图片id
 * @param picture 接收路径的隐藏表单
 */
function uploadFileQiNiu(fileId,imgId,picture,IP){

	$("#"+fileId).live("change",function () {
        $.ajaxFileUpload ({
             url:IP+"/common/uploadFileQiNiu.action",
             // url: '/upload.aspx', //用于文件上传的服务器端请求地址
             secureuri: false, //是否需要安全协议，一般设置为false
             fileElementId: fileId, //文件上传域的ID
             //contentType: "application/x-www-form-urlencoded",//(可以)
             // contentType: "application/json; charset=utf-8",//(可以)
             // contentType: "text/xml",//(可以)
             contentType: "application/json; charset=utf-8",//(可以)
             dataType: 'json', //返回值类型 一般设置为json
             success: function (data, status) { //服务器成功响应处理函数
            	  $("#"+imgId).show();
                  $("#"+imgId).attr("src", data.data);
                  console.log(picture);
                  $("#"+picture).attr("value",data.data);
             },
             error: function (data, status, e){//服务器响应失败处理函数

             }
        });

    });


};

/**
 * 初始化编辑器
 * @param id 要初始化的id
 */
function initEdit(id){

  var ue = UE.getEditor(id);

}


/**
 * 导出数据
 * @param targetId 触发对象Id
 * @param fromId 查询的form的id
 * @param url 导出的地址
 */
function exportExcel(targetId,fromId,url){

	 $("#"+targetId).click(function(){

	    	var action = $("#"+fromId).attr("action");

	    	$("#"+fromId).attr("action",url);

	    	$("#"+fromId).submit();

	    	$("#"+fromId).attr("action",action);


		});

}

/**
 *
 * @param id 要绑定控件的id
 */
/*function showCity(id){

	//出发城市数据
	$("#"+id).click(function () {
	    dialog({
	        align: 'bottom left',
	        url: commonIP+"/html/fromCity.html",
	        //type:城市类型 country:国籍类型：空全部显示,  hot:是否显示热门 1、显示
	        data: { type: "1", country: "1", hot: "0" },
	        padding: 0,
	        quickClose: true,
	    }).showModal(document.getElementById(id));
	});



}*/

/*//type:城市类型 country:国籍类型：空全部显示,  hot:是否显示热门 1、显示
 var id_type="";//1=出发城市     2=目的地
 var id_check = "";//点击城市后，是否关闭  1=不关闭      null=关闭
 var id_hot="";//热门城市  1=显示      null=不显示
 var id_country="";//显示国际 1=显示    null=不显示
*/


/**
 *
 * @param t 1-目的地
 *//*
function setCityParter(t){

	if(1==t){
		 id_type=2;//1=出发城市     2=目的地
		 id_check = null;//点击城市后，是否关闭  1=不关闭      null=关闭
		 id_hot="1";//热门城市  1=显示      null=不显示
		 id_country="1";//显示国际 1=显示    null=不显示
	}



}*/


