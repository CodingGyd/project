$(function(){
	//自动播放
	bfVidio();
	//最后一个页出不来，暂未解决
	var pageNum = 0;
	var length = $('.runPage').length;
	for (var i = 0; i < $('.runPage').length; i++) {
		$('.runPage').eq(i).css('z-index',length-i);
		$('.runPage').eq(i).children('div').css('z-index',length-i);
		$('.runPage').eq(i).children('img').css('z-index',length-i-1);
	};

	$('.nextBtn').bind('click',function(){
			if ( pageNum <= length-2 ) {
				runNext(pageNum);
			pageNum++;
			};
			console.log(pageNum);					
	});

	function runNext(index){
		$('.runPage').eq(index).addClass('runClass');
		zIndexNext(index,$('.runPage').eq(index));
	}

	function zIndexNext(index,element){
		if ( index >= 1 ) {
			element.css('z-index',index+2);
		};	
		setTimeout(function(){
			if (index==0) {
				element.css('z-index',index+2);
			};
			element.children('div').css('z-index',index+1);
			element.children('img').css('z-index',index+2);		
		},1000);
	}

	$('.lastBtn').bind('click',function(){
			if ( pageNum >= 1 ) {				
			pageNum--;
			runLast(pageNum);
			};
			console.log(pageNum);					
	});

	function runLast(index){
		$('.runPage').eq(index).removeClass('runClass');
		zIndexLast(index,$('.runPage').eq(index));
	}

	function zIndexLast(index,element){
		if (index == 0) {
			element.css('z-index',length-index);
		};
		setTimeout(function(){
			element.css('z-index',length-index);
			element.children('div').css('z-index',length-index);
			element.children('img').css('z-index',length-index-1);		
		},1000);
	}


	function bfVidio(){
		var audio = document.getElementById('audio');
		if(audio!==null){
			//检测播放是否已暂停.audio.paused 在播放器播放时返回false.
			if(audio.paused)                     {
				audio.play();//audio.play();// 这个就是播放
			}else{
				audio.pause();// 这个就是暂停
			}
		}
	}

});