<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>bookstore</title>
</head>
<body>
<c:set var="menu" value="register" />
<%@ include file="common/nav.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1 class="border bg-light p-2 fs-4">회원가입</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<p>회원가입 정보를 입력하세요</p>
			
			<form:form modelAttribute="registerFrom" id="form-register" class="border bg-light p-3" method="post" action="register">
				<div class="mb-3">
					<label class="form-label">아이디</label>
					<form:input class="form-control form-control-sm" path="id" />
					<form:errors path="id" cssClass="text-danger" />
				</div>
				<div>
					<label class="form-label">비밀번호</label>
					<form:password path="encryptPassword" class="form-control form-control-sm" name="encryptPassword" />
					<form:errors path="encryptPassword" cssClass="text-danger" />
				</div>
				<div>
					<label class="form-label">비밀번호 확인</label>
					<form:password path="passwordConfirm" class="form-control form-control-sm" name="passwordConfirm" />
					<form:errors path="passwordConfirm" cssClass="text-danger" />
				</div>
				<div class="mb-3">
					<label class="form-label">이름</label>
					<form:input path="name" class="form-control form-control-sm" name="name" />
					<form:errors path="name" cssClass="text-danger" />
				</div>
				<div class="mb-3">
					<label class="form-label">이메일</label>
					<form:input path="email" class="form-control form-control-sm" name="email" />
					<form:errors path="email" cssClass="text-danger" />
				</div>
				<div class="mb-3">
					<label class="form-label">전화번호</label>
					<form:input path="tel" class="form-control form-control-sm" name="tel" />
					<form:errors path="tel" cssClass="text-danger" />
				</div>
				<div class="text-end">
					<a href="/" class="btn btn-secondary btn-sm">취소</a>
					<button type="submit" id="btn-register" class="btn btn-primary btn-sm">가입</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>