/**
 * 
 */

var pageInfo = document.querySelector('#pageInfo')

if(pageInfo) {
	console.log('category'+ pageInfo.dataset.category)
	console.log('boardpk'+ pageInfo.dataset.boardpk)
	console.log('loginuserpk'+ pageInfo.dataset.loginuserpk)
}


//window.onload = likeLoad()



// $(document).ready(likeLoad())

function likeLoad() {
	
	var resetLike = document.querySelectorAll('.likeCount')
	var i
	
	for (i = 0; i < resetLike.length; i++) {
    	resetLike[i].innerHTML = "";
	}
	
	var cmtBoardPk = pageInfo.dataset.boardpk


	fetch(`/liked?cmtBoardPk=${cmtBoardPk}`)
		.then(res => res.json())
		.then(result => {
			console.log(result)
			chkLike(result)
		})
		
}

setTimeout(likeLoad(), 500)






function countLoad(cmtBoardPk, cmtSeq) {
	
	console.log('cmtBoardPk' + cmtBoardPk)
	console.log('cmtSeq' + cmtSeq)
	
	var eachCmt = document.querySelector(`#count${cmtSeq}`)
	eachCmt.innerHTML = ''
	
	fetch(`/liked/total?cmtBoardPk=${cmtBoardPk}&cmtSeq=${cmtSeq}`)
		.then(res => res.json())
		.then(function(result) {
				
			eachCmt.innerHTML = '좋아요 : '+`${result}`
		})
	

	
}





function chkLike(result) {
	
	var loginUserPk = pageInfo.dataset.loginuserpk
	
	if(result.length === 0) {
		
		return 
	}
	result.forEach(function(item) {
		
		
		
		if(item.userPk == loginUserPk) {
			
			var cmtId = document.querySelector(`#heart-icon${item.cmtSeq}`)
			console.log(cmtId)
		
			const PRESSED_CLASS = "press"
			cmtId.classList.add(PRESSED_CLASS)
		}

		countLoad(item.cmtBoardPk, item.cmtSeq)
		
	})
}






function liked(cmtSeq, loginUserPk) {
	
	console.log('loginuserpk'+ loginUserPk)
	console.log('cmtSeq'+ cmtSeq)
	
	if(isNaN(loginUserPk)) {
		if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')) {
			location.href = '/user/login'
			return
		}
	} else {

		// 동작 모션
		likeMotion(cmtSeq)
		
		var cmtId = document.querySelector(`#heart-icon${cmtSeq}`)
		
		if(cmtId.classList.contains('press')) {
		
			// DB에 좋아요 추가
			likeAddAjax(cmtSeq, loginUserPk)
		}
		
		else {
			// DB에 좋아요 삭제
			likeDelAjax(cmtSeq, loginUserPk)
		}
		
		
		
	}

	
}


// DB에 좋아요 저장
function likeAddAjax(cmtSeq, loginUserPk) {
	
	var param = {
		cmtBoardPk: pageInfo.dataset.boardpk,
		cmtSeq: cmtSeq,
		userPk: loginUserPk
	}
	
	fetch('/liked', {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}).then(function(res) {
		return res.json()
	}).then(function(result) {
		if(result === 1) {
			console.log('좋아요 저장')
			likeLoad()
		} else {
			console.log('오류')
		}
	})

	
}

// DB에 좋아요 삭제
function likeDelAjax(cmtSeq, loginUserPk) {
	
	var cmtBoardPk = pageInfo.dataset.boardpk
	var	userPk = pageInfo.dataset.loginuserpk
	
	fetch(`/liked?cmtBoardPk=${cmtBoardPk}&cmtSeq=${cmtSeq}&userPk=${userPk}`, {
		method: 'delete'
	}).then(res => res.json())
	.then(result => {
		if(result === 1) {
			console.log('좋아요 취소')
			likeLoad()
		}
	})
}




function likeMotion(cmtSeq) {
	
	const iconElem = document.querySelector(`#heart-icon${cmtSeq}`)
	const spanElem = document.querySelector(`#liked-span${cmtSeq}`)
	
	const PRESSED_CLASS = "press"
		
  	iconElem.classList.toggle(PRESSED_CLASS)
 	spanElem.classList.toggle(PRESSED_CLASS)
}















