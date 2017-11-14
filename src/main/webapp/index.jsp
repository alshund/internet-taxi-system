<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div align="center">
        <form action="ClientController" method="get">
            <input type="hidden" name="command" value="goToAuthorizationPage" />
            <input type="submit" value="Авторизация" />
        </form>
        <form action="ClientController" method="get">
            <input type="hidden" name="command" value="goToRegistrationPage" />
            <input type="submit" value="Регистрация" />
        </form>
    </div>
</body>
</html>
