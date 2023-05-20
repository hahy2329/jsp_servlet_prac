<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetail</title>
</head>
<body>
	
	<div align="center" style="padding-top:100px">
		<h3>게시글 상세</h3>
		<table style="width: 700px; text-align:center" border="1">
		
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr>
				<td>제목</td>
				<td>${mainBoardDTO.subject }</td>
			
			</tr>
			<tr>
				<td>조회수</td>
				<td>${mainBoardDTO.readCnt }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${mainBoardDTO.writer }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${mainBoardDTO.enrollDt }</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>${mainBoardDTO.content }</td>
			</tr>
			
			<tr align="right">
				<td colspan="2">
					<input type="button" value="수정" onclick="location.href='boardUpdate?boardId=${mainBoardDTO.boardId}'">
					<input type="button" value="삭제" onclick="location.href='boardDelete?boardId=${mainBoardDTO.boardId}'">
					<input type="button" value="목록보기" onclick="location.href='boardList'">
				
				</td>
			</tr>
			
	
	</table>
	
	
	</div>
	

</body>
</html>