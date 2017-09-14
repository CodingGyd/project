// JavaScript Document
$(document).ready(function(e) {
	initPager();
});

function initPager() {

	var options = {

		bootstrapMajorVersion : 3, // 版本

		currentPage : 1, // 当前页数

		totalPages : 5, // 总页数

		numberOfPages : 5,

		itemTexts : function(type, page, current) {
			switch (type) {

			case "first":

				return "首页";

			case "prev":

				return "上一页";

			case "next":

				return "下一页";

			case "last":

				return "末页";

			case "page":

				return page;

			}

		},// 点击事件，用于通过Ajax来刷新整个list列表

		onPageClicked : function(event, originalEvent, type, page) {

			$.ajax({

				url : "/page",

				type : "Post",

				dataType : "json",

				data : {"page":page},

				success : function(data) {
					alert(data);
				}

			});

		}

	};

	$('#pageUl').bootstrapPaginator(options);

}