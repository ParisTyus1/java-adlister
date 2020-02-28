
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Products</title>
    <jsp:include page="/partials/head.jsp"/>
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<h1>Here are all the ads!</h1>
<div class="container">
<c:forEach var="ad" items="${ads}">
    <div class="col-md-6">
        <h3>Title: $ ${ad.title}</h3>
        <p>Id: ${ad.id}</p>
        <p>UserId: $ ${ad.userId}</p>

        <p>Description: $ ${ad.description}</p>

    </div>
</c:forEach>
</div>


</body>
</html>