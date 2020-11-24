<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<html lang="is">
<head>
    <meta charset="UTF-8">
    <link href="/css/bootstraps.css"
          th:href="@{/css/bootstraps.css}"
          rel="stylesheet" media="screen"/>
    <link href="/css/logins.css"
          th:href="@{/css/logins.css}"
          rel="stylesheet" media="screen"/>
    <title>Login</title>
</head>
<body>
<form action="#" th:action="@{/signup/new} + ${userType}" th:object="${user}" method="post" class="form-signin">
    <h1 class="font-weight-bold">Sign Up</h1>
    <input type="uName" name="uName" id="uName" th:field="*{uName}"/>
    <input type="password" name="password" id="password" th:value="*{password}"/>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="Confirm">Confirm</button>
</form>
</body>
</html>