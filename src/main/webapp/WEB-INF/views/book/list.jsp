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
	<div class="row mb-2 d-flex justify-content-between align-items-end border-bottom border-dark border-2">
		<div class="col-2">
			<p>도서 목록을 확인하세요</p>
		</div>
		<div class="col-6">
			<form id="form-paging" class="d-flex mb-3" method="get" action="list">
				<input type="hidden" name="page" />
				<select class="form-select input-group" name="sort">
					<option value="recent" ${param.sort eq 'recent' ? 'selected' : '' } > 신상품순 </option>
					<option value="title" ${param.sort eq 'title' ? 'selected' : '' } > 제목순 </option>
					<option value="low" ${param.sort eq 'low' ? 'selected' : '' } > 최저가순 </option>
					<option value="high" ${param.sort eq 'high' ? 'selected' : '' } > 최고가순 </option>
				</select>
				<div class="input-group ms-3">
					<select class="form-select" name="opt">
						<option selected="selected" disabled="disabled"> 검색조건</option>
						<option value="all" ${param.opt eq 'all' ? 'selected' : '' }> 전체</option>
						<option value="title" ${param.opt eq 'title' ? 'selected' : '' }> 제목</option>
						<option value="author" ${param.opt eq 'author' ? 'selected' : '' }> 저자</option>
						<option value="publisher" ${param.opt eq 'publisher' ? 'selected' : '' }> 출판사</option>
					</select>
				</div>
				<div class="input-group mx-3">
					<input type="text" class="form-control" name="keyword" value="${param.keyword }">
				</div>
				<div class="input-group" style="width: 40%;">
					<button type="submit" class="btn btn-outline-primary" >검색</button>
				</div>
			</form>
		</div>
	</div>
	<div class="row mb-2">
		<c:choose>
			<c:when test="${empty bookList }">
				<div class="text-center">
					<p>등록된 도서 정보가 없습니다.</p>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="book" items="${bookList }">
					<div class="col-4 mb-2">
						<div class="card">
							<div class="card-header">
								<div class="row">
									<div class="col-10"><a href="/book/detail?id=${book.id }" class="text-decoration-none text-dark">${book.title }</a> </div>
									<div class="col-2 text-end">
										<a href="/cart/add?id=${book.id }"class="btn text-success float-end"><i class="bi bi-bag-check-fill"></i></a>
									</div>
								</div>
							</div>
							<div class="card-body">
								<p class="mt-1 d-flex justify-content-between text-secondary">
									<span>${book.author }</span> <span>${book.publisher }</span>
								</p>
								<p class="mb-0 d-flex justify-content-between">
									<small class="text-decoration-line-through"><fmt:formatNumber value="${book.price }" pattern="#,###"/> 원</small> 
									<span><strong class="text-danger"><fmt:formatNumber value="${book.discountPrice }" pattern="#,###"/></strong> 원</span>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	
	<c:if test="${not empty bookList }">
		<div class="row mb-1">
			<div class="row mb-1">
				<div class="col-12">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<li class="page-item ${pagination.first ? 'disabled' : '' }">
								<a class="page-link" href="/book/list?page=${pagination.prevPage }" data-page-no="${pagination.prevPage }">이전</a>
							</li>
		
							<c:forEach var="number" begin="${pagination.beginPage }" end="${pagination.endPage }">
								<li class="page-item ${pagination.page eq number ? 'active' : '' }">
									<a class="page-link" href="/book/list?page=${number }" data-page-no="${number }">${number }</a>
								</li>
							</c:forEach>
		
							<li class="page-item ${pagination.last ? 'disabled' : '' }">
								<a class="page-link" href="/book/list?page=${pagination.nextPage }" data-page-no="${pagination.nextPage }">다음</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
	// 페이지 클릭 이벤트
	$(".pagination a").click(function(event) {
		event.preventDefault();
		let pageNo = $(this).attr("data-page-no");
		$("input[name=page]").val(pageNo);
		
		$("form").trigger("submit");
	});
})
</script>
</body>
</html>