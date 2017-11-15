<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DriveLogin</title>
</head>
<body>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="driverSignIn" />
        <div>
            <label for="login">Email</label>
            <input id="login" placeholder="Email" />
        </div>
        <div>
            <label for="pass">Password</label>
            <input type="password" id="pass" placeholder="Password" />
        </div>
        <div><input type="submit" value="SIGN IN" /></div>
    </form>
    <div>Don't have an account? <a href="DriverSignUp">Sign up</a></div>
</body>
</html>