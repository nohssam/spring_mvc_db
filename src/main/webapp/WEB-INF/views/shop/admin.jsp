<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
		text-align: center;
	}
	table {
		width: 600px;
    }
    input{
    	padding: 5px;
    	margin: 5px;
    }
    div{
    	width: 600px;
    	margin: 0px auto;
    }
    h2{
    	text-align: center;
    }
</style>
<script type="text/javascript">
	function product_add(f) {
		// 유효성 검사 
		f.action="/MyController?cmd=productAdd";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>상품 등록</h2>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<th>분류</th>
						<td>
							<input type="radio" name="category" value="com001">컴퓨터
							<input type="radio" name="category" value="ele002">가전제품
							<input type="radio" name="category" value="sp003">스포츠
						</td>
					</tr>
					<tr>
						<th>제품번호</th>
						<td><input type="text" name="p_num" > </td>
					</tr>
					<tr>
						<th>제품명</th>
						<td><input type="text" name="p_name" > </td>
					</tr>
					<tr>
						<th>판매사</th>
						<td><input type="text" name="p_company" > </td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><input type="number" name="p_price" > </td>
					</tr>
					<tr>
						<th>할인가격</th>
						<td><input type="number" name="p_saleprice" > </td>
					</tr>
					<tr>
						<th>상품이미지-S</th>
						<td><input type="file" name="p_image_s" > </td>
					</tr>
					<tr>
						<th>상품이미지-L</th>
						<td><input type="file" name="p_image_l" > </td>
					</tr>
					<tr>
						<th colspan="2">상품내용</th>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="50" name="p_content"></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="상품등록" onclick="product_add(this.form)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>