<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html xmlns:th="https://thymeleaf.org">

<html lang="en">

    <head>
        <title>Index</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
    </head>
    <body>
    <h1><a href="/">News Feed</a></h1>

    <c:choose>
        <c:when test="${not empty newsStories}">
            <table class="notes">
                <tr>
                     <th>Title</th>
                     <th>Time Posted</th>
                     <th>Id</th>
                     <th>Category</th>
                </tr>

                <c:forEach var="newsStory" items="${newsStories}">
                    <tr>
                        <td><a href="${newsStory.link}">${newsStory.title}</a></td>
                        <td>${newsStory.timePosted}</td>
                        <td>${newsStory.id}</td>
                        <td>${newsStory.categories}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3>Nothing!</h3>
        </c:otherwise>
    </c:choose>

    </body>
</html>
