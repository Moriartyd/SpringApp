<!DOCTYPE html>
<html lang="en">
<head>
    <title>Регистрация нового пользователя</title>
</head>
<body>
<form action="/user/registration" method="post">
    <div><label> User Name : <input type="text" name="login"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Name: <input type="text" name="name"/> </label></div>
    <div><label> Surname: <input type="text" name="surname"/> </label></div>
    <div><label> Age: <input type="number" name="age"/> </label></div>
    <div><input type="submit" value="Sign Up"/></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
<#if message??><h1>${message}</h1></#if>
</body>
</html>