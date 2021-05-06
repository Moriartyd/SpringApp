<!DOCTYPE html>
<html lang="en">
<head>
  <title>Вход</title>
</head>
<body>
<form action="/login" method="post">
  <div><label> User Name : <input type="text" name="username"/> </label></div>
  <div><label> Password: <input type="password" name="password"/> </label></div>
  <div><input type="submit" value="Log In"/></div>
  <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</body>
</html>