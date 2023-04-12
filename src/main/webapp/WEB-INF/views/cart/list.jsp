<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>bookstore</title>
</head>
<body>
<c:set var="menu" value="cart" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
    <div class="row mb-2">
		<div class="col">
			<p>장바구니에 담기 책 목록을 확인하세요</p>
			<form id="form-cart-item-list" method="get" action="order">
				<table class="table" id="table-cart-items">
					<colgroup>
						<col width="5%">
						<col width="*">
						<col width="12%">
						<col width="10%">
						<col width="10%">
						<col width="17%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" ${not empty cartList ? 'checked' : '' } /></th>
							<th>제목</th>
							<th>가격</th>
							<th>판매가격</th>
							<th>수량</th>
							<th>구매가격</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty cartList }">
							<c:forEach var="cartItem" items="${cartList }">
								<tr>
									<td><input type="checkbox" name="cartId" value="${cartItem.cartId }" checked /></td>
									<td><a href="/book/detail?id=${cartItem.bookId }">${cartItem.title }</a></td>
									<td><fmt:formatNumber value="${cartItem.price }" />원</td>
									<td><fmt:formatNumber value="${cartItem.discountPrice }" />원</td>
									<td class="pe-4">
										<div class="input-group input-group-sm">
											<button class="btn btn-outline-secondary btn-minus" type="button"><i class="bi bi-dash-lg"></i></button>
											<input type="text" name="quantity" class="form-control border-secondary text-center" id="quantity-${cartItem.cartId }" value="${cartItem.quantity }" data-stock="${cartItem.stock }" readonly="readonly">
											<button class="btn btn-outline-secondary btn-plus" type="button"><i class="bi bi-plus-lg"></i></button>
										</div>
									</td>
									<td>
										<strong class="text-danger"><fmt:formatNumber value="${cartItem.discountPrice*cartItem.quantity }" /></strong>원
										<small>(<fmt:formatNumber value="${cartItem.price*cartItem.quantity - cartItem.discountPrice*cartItem.quantity }" />원 할인)</small>
									</td>
									<td>
										<a href="/cart/delete?bookId=${cartItem.cartId }" class="btn btn-outline-secondary btn-sm">삭제</a>
										<a href="/cart/order?bookId=${cartItem.cartId }" class="btn btn-outline-secondary btn-sm">주문</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${empty cartList }">
					<div class="pt-3">
						<div class="text-center mb-3">
							<strong>장바구니에 담긴 상품이 없습니다.</strong>
						</div>
						<div class="text-center">
							<a href="/book/list" class="btn btn-outline-primary btn-lg">쇼핑 계속하기</a>
						</div>
					</div>
				</c:if>
			</form>
		</div>
	</div>
	<c:if test="${not empty cartList }">
		<div class="row mb-2">
			<div class="col">
				<div class="row mb-3">
					<div class="col">
						<div class="card">
							<div class="card-body">
								<p>총 상품금액을 확인하세요</p>
								<div class="d-flex justify-content-between mb-2"><strong>총 상품금액</strong> <span><strong><fmt:formatNumber value="${totalPrice }"/></strong> 원</span></div>
								<div class="d-flex justify-content-between mb-3"><strong>총 할인금액</strong> <span><strong><fmt:formatNumber value="${totalDiscountPrice }"/></strong> 원</span></div>
								<hr/>
								<div class="d-flex justify-content-between"><strong>총 결재금액</strong> <span><strong class="text-danger"><fmt:formatNumber value="${totalPaymentPrice }"/></strong> 원</span></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col text-end">
				<button class="btn btn-outline-secondary" type="button" id="btn-delete-cart-items">삭제하기</button>
				<button class="btn btn-primary" type="button"  id="btn-order-cart-items">주문하기</button>
			</div>
		</div>
	</c:if>
</div>
<script type="text/javascript">
	$(function() {
		// 수량 minus 변경
		$(".btn-minus").click(function() {
			let $quantity = $(this).next($("input[name=quantity]"));
			let quantityVal = parseInt($quantity.val());
			
			if(quantityVal <= 1) {
				alert("수량은 최소 1권 이상이여야합니다.");
				return false;
			}
			
			$quantity.val(quantityVal-1);
		});

		// 수량 plus 변경
		$(".btn-plus").click(function() {
			let $quantity = $(this).prev($("input[name=quantity]"));
			let quantityVal = parseInt($quantity.val());
			let stock = $quantity.attr("data-stock");
			
			if(quantityVal >= stock) {
				alert("재고량이 "+stock+"권까지 있습니다.");
				return false;
			}
			
			$quantity.val(quantityVal+1);
		});
	})
</script>
</body>
</html>