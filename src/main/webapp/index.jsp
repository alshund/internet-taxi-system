<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="signIn" />
        <div>
            <label for="login">Email</label>
            <input id="login" name="login" placeholder="Email" />
        </div>
        <div>
            <label for="pass">Password</label>
            <input type="password" id="pass" name="password" placeholder="Password" />
        </div>
        <input type="submit" value="SIGN IN" />
    </form>
    <div>
        Don't have an account?
        <a href="RiderSignUp">Sign up</a>
        <a href="">Are you a driver?</a>
    </div>
</html>
