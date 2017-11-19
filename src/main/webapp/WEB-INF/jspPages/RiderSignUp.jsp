<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RiderRegistration</title>
</head>
<body>
<form action="FrontController" method="post">
    <input type="hidden" name="command" value="userSignUp" />
    <input type="hidden" name="role" value="rider" />
    <div>
        <label for="email">Email</label>
        <input id="email" name="email" placeholder="Email" />
    </div>
    <div>
        <label for="pass">Password</label>
        <input type="password" name="password" id="pass" placeholder="Password" />
    </div>
    <div>
        <input type="submit" value="SIGN UP">
    </div>
</form>
</body>
</html>