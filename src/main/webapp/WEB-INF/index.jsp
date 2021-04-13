<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteka</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/navbar.jspf"/>

<c:if test="${not empty requestScope.books}">
    <c:forEach var="book" items="${requestScope.books}">
        <div class="container">
            <div class="row bs-callout bs-callout-primary">
                <div class="col col-md-11 col-sm-10">
                    <h3 class="centered"><c:out value="${book.title}"/> </a></h3>
                    <h4>Nazwisko Autora: <c:out value="${book.lastName}"/></h4>
                    <h5>Imię Autora: <c:out value="${book.firstName}"/></h5>
                    <h6>Isbn: <c:out value="${book.isbn}"/></h6>
                    <c:choose>
                        <c:when test="${book.orderType == 'DOSTEPNA'}">
                            <a href="${pageContext.request.contextPath}/order?book_id=${book.id}"
                               class="btn btn-lg btn-primary"><span class="glyphicon glyphicon-plus"></span> Zamów</a>
                        </c:when>
                        <c:otherwise>
                            <h5>Książka wypożyczona</h5>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<jsp:include page="fragment/footer.jspf"/>

</body>
</html>