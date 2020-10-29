<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Login to your account</h2>
<form action="#" th:action="@{/login}" th:object="${user}"  method="post">
    <input type="text" name="uName" id="uName" th:field="*{uName}"/>
    <input type="text" name="password" id="password" th:value="*{password}"/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>