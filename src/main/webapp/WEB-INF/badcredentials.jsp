<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Błąd logowania</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/navbar.jspf"/>


<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <h1>Błąd logowania</h1>
        <p>Wprowadzono błędną nazwę użytkownika lub hasło.</p>
        <a href="${pageContext.request.contextPath}/login">Spróbuj ponownie</a>
    </div>
</div>


<jsp:include page="fragment/footer.jspf"/>

</body>
</html>