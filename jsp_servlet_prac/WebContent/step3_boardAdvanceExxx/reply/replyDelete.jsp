<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>replyDelete</title>
</head>
<body>

	<div style="paddig-top:100px" align="center">
		<form action="replyDelete" method="post">
		<h3>댓글 삭제</h3>
		<br>
		<table  style="width: 700px" border="1">
			
			
			<tr>
				<td>작성자</td>
				<td>${replyDTO.writer }</td>
			</tr>
			<tr>
				<td>내용</td>
				<textarea>
					${replyDTO.content }
				</textarea>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${replyDTO.enrollDt }</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="passwd" size="60"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="hidden" name="replyId" value="${replyDTO.replyId }">
					<input type="hidden" name="boardId" value="${replyDTO.boardId }">
					<input type="submit" value="댓글삭제">
				</td>
			</tr>
		</table>
		</form>
	
	</div>

</body>
</html>