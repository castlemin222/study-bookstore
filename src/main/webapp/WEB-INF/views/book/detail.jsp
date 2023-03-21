<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../common/tags.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>bookstore</title>
</head>
<body>
<c:set var="menu" value="book" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
    <div class="row">
    	<div class="col-12 border-bottom border-dark border-2">
			<p>도서 상세 정보</p>
    	</div>
    </div>
    <div class="row my-3">
		<div class="col-10">
			<div class="row">
				<div class="col">
					<table class="table">
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td colspan="3">${book.title }</td>
							</tr>
							<tr>
								<th>등록일</th>
								<td><fmt:formatDate value="${book.createdDate }" pattern="yyyy년 M월 d일"/> </td>
								<th>최종수정일</th>
								<td><fmt:formatDate value="${book.updatedDate }" pattern="yyyy년 M월 d일"/> </td>
							</tr>
							<tr>
								<th>리뷰평점</th>
								<td><strong class="text-danger">${book.reviewScore }</strong> </td>
								<th>리뷰갯수</th>
								<td><fmt:formatNumber  value="${book.reviewCount }" /> 개</td>
							</tr>
							<tr>
								<th>할인가격</th>
								<td><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</td>
								<th>가격</th>
								<td><fmt:formatNumber value="${book.price }" pattern="#,###"/> 원</td>
							</tr>
							<tr>
								<th>저자</th>
								<td>${book.author }</td>
								<th>출판사</th>
								<td>${book.publisher }</td>
							</tr>
							<tr>
								<th>판매여부</th>
								<td>${book.onSell eq 'Y' ? '판매중' : '판매완료' }</td>
								<th>재고량</th>
								<td><fmt:formatNumber value="${book.stock }"/> 권</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-2">
			<div class="row mb-3">
				<div class="col">
					<div class="card">
						<div class="card-body">
							<p>수량을 선택하세요</p>
							<form id="form-order" class="row" method="get" action="/order/form">
								<input type="hidden" name="id" value="${book.id }" />
								<div class="mb-3">
									<div class="input-group mb-3">
										<button class="btn btn-outline-secondary" id="btn-minus-quantity" type="button"><i class="bi bi-dash-lg"></i></button>
										<input type="text" class="form-control border-secondary" style="padding-left: 32px;" name="quantity" value="1" readonly="readonly">
										<button class="btn btn-outline-secondary" id="btn-plus-quantity" type="button"><i class="bi bi-plus-lg"></i></button>
									</div>
								</div>
								<div class="d-grid gap-2 mb-2">
									<button class="btn btn-primary" type="button" id="btn-add-cart">카트에 담기</button>
								</div>
								<div class="d-grid gap-2">
									<button class="btn btn-success" type="submit">바로구매</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>