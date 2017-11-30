<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DriverRegistration</title>
</head>
<body>
    <form enctype="multipart/form-data" action="ApplicationController" method="post">
        <input type="hidden" name="command" value="send_driver_application" />
        <input type="hidden" name="role" value="driver" />
        <div>
            <label for="first_name_id">First name</label>
            <input id="first_name_id" name="first_name" placeholder="First name" />
        </div>
        <div>
            <label for="last_name_id">Last name</label>
            <input id="last_name_id" name="last_name" placeholder="Last name" />
        </div>
        <div>
            <label for="patronymic_id">Patronymic</label>
            <input id="patronymic_id" name="patronymic" placeholder="Patronymic" />
        </div>
        <div>
            <label for="phone_number_id">Phone number</label>
            <input id="phone_number_id" name="phone_number" placeholder="Phone number" />
        </div>
        <div>
            <label for="email_id">Email</label>
            <input id="email_id" name="email" placeholder="Email" />
        </div>
        <div>
            <label for="driver_license_id">Driver license</label>
            <input type="file" id="driver_license_id" name="driver_license" placeholder="Driver license" />
        </div>
        <div>
            <label for="passport_id">Passport</label>
            <input type="file" id="passport_id" name="passport" placeholder="Passport" />
        </div>
        <div>
            <input type="submit" value="SEND APPLICATION">
        </div>
    </form>
</body>
</html>