$(document).ready(function() {
});
function doPraise(a,id,totalPraise) {
	$(a).removeAttr('href');//去掉a标签中的href属性
	$(a).removeAttr('onclick');//去掉a标签中的onclick事件
 	var newPraise = isNewPraise(id);// 
	if (newPraise === true) {
		 $.ajax({
		        type: "Post",
		        url: "/dopraise",
		        data:{"id":id},
		        async:true,
		        success: function(result){

		        	if (null != result) {
		        		alert(result.msg);
		        		setCookie("gydblog-praised-" + id, "true", 500);
		        		$(a).text("点个赞！ ("+(++totalPraise)+")");
		        	} 
		       	}
		     });
	} else {
		alert("已经点过赞啦~");
	}

}

function isNewPraise(id) {
	return true;
	var flg = getCookie("gydblog-praised-" + id);
	if (flg === "") {
		return true;
	} else {
		return false;
	}
}
// 写字段到cookie
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires + ";path=/";
}
// 读cookie
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0)
			return c.substring(name.length, c.length);
	}
	return "";
}

 