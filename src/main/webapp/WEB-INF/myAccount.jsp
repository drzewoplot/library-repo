<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteka - Moje Konto</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/navbar.jspf"/>

<c:choose>
    <c:when test="${not empty requestScope.books}">
        <h1> Aktualnie wypożyczone książki: </h1>
        <c:forEach var="book" items="${requestScope.books}">
            <div class="container">
                <div class="row bs-callout bs-callout-primary">
                    <div class="col col-md-11 col-sm-10">
                        <h3 class="centered"><c:out value="${book.title}"/> </a></h3>
                        <h4>Nazwisko Autora: <c:out value="${book.lastName}"/></h4>
                        <h5>Imię Autora: <c:out value="${book.firstName}"/></h5>
                        <h6>Isbn: <c:out value="${book.isbn}"/></h6>
<%--                        To Be Done ....--%>
<%--                        <h4>Wypożyczono: <c:out value="${order.date}"/></h4>--%>
<%--                        <h5>Nr zamówienia: <c:out value="${order.orderId}"/></h5>--%>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
    <h1 class="centered">Aktualnie nie masz wypożyczonych książek,
        <a href="${pageContext.request.contextPath}/">Sprawdź dostępne książki</a></h1>
    </c:otherwise>
</c:choose>


<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
