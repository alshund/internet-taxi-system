<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DriverRegistration</title>
</head>
<body>
    <form action="FrontController" method="get">
        <input type="hidden" name="command", value="driverSignUp" />
        <div>
            <label for="firstName">First name</label>
            <input id="firstName" name="firstName" placeholder="First name" />
        </div>
        <div>
            <label for="lastName">Last name</label>
            <input id="lastName" name="lastName" placeholder="Last name" />
        </div>
        <div>
            <label for="phoneNumber">Phone number</label>
            <input id="phoneNumber" name="phoneNumber" placeholder="Phone number" />
        </div>
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