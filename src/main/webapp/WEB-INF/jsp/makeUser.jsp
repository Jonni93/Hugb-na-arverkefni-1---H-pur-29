<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Index</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
    </head>
    <body>

    <h1><a href="/">new user made</a></h1>
            <table class="regestiredUsers">

                <thead>
                <tr>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Categories</th>
                </tr>
                </thead>
                    <tr>
                        <td>${user.uName}</td>
                        <td>${user.password}</td>
                        <td>${user.categories}</td>
                    </tr>
            </table>
    </body>
</html>