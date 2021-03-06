<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://cdn.ckeditor.com/ckeditor5/25.0.0/classic/ckeditor.js"></script>

<div class="write_content">
	<c:if test="${param.category == 1}">
		<h1 class="h1">책방일지 후기 글쓰기</h1>
	</c:if>
	<c:if test="${param.category == 2}">
		<h1 class="h1">공지사항 글쓰기</h1>
	</c:if>
	<c:if test="${param.category == 11}">
		<h1 class="h1">모집 글쓰기</h1>
	</c:if>
	<c:if test="${param.category == 12}">
		<h1 class="h1">모집 마감 글쓰기</h1>
	</c:if>

	<form id="write_frm" action="/board/${requestScope.data == null ? 'write' : 'mod' }" method="post" onsubmit="return chk()">
		<input type="hidden" name="category" value="${param.category}">
		<input type="hidden" name="boardPk" value="${requestScope.data == null ? '0' : requestScope.data.boardPk}">
		
		<div>
			<input type="text" class="write_title" name="title" placeholder="제목을 입력해주세요." required value="${requestScope.data.title}">
		</div>
		<textarea name="content" id="editor" placeholder="내용을 입력해주세요." required>
			${requestScope.data.content}
		</textarea>
		<div>
			<input type="submit" class="write_btn" value="${requestScope.data == null ? '등록' : '수정'}">
		</div>
	</form>
</div>


<!-- ckeditor js -->
<script src="/res/js/ckeditor.js?ver=2"></script>



<!-- 
<div class="write_content">
	<h1 class="h1">책방일지와 함께하며</h1>
	<form id="write_frm" action="/board/write" method="post" onsubmit="return chk()">
		<input type="hidden" name="category" value="${param.category}">
		<div>
			<input type="text" class="write_title" name="title" placeholder="제목을 입력해주세요." required>
		</div>
		<textarea name="content" id="editor" placeholder="내용을 입력해주세요."></textarea>
		<div>
			<input type="submit" class="write_btn" value="등록">
		</div>
	</form>
</div>
 -->

 
 
 