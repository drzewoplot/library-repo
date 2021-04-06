
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteka - dodaj książkę do bazy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/navbar.jspf" />

<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <form class="form-signin" method="post" action="add">
            <h2 class="form-signin-heading"> Dodaj nową książkę</h2>
            <input name="inputFirstName" type="text" class="form-control" placeholder="Imię Autora"required autofocus />
            <input name="inputLastName" type="text" class="form-control" placeholder="Nazwisko Autora"required autofocus />
            <input name="inputTitle" type="text" class="form-control" placeholder="Tytuł książki"required autofocus />
            <input name="inputIsbn" type="text" class="form-control" placeholder="nr isbn"required autofocus />
             <input class="btn btn-lg btn-primary btn-block" type="submit" value="Dodaj!" />
        </form>
    </div>
</div>

<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>
