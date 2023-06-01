<%-- 
    Document   : test
    Created on : May 21, 2023, 4:49:20 PM
    Author     : ACER NITRO
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="testurl" method="POST">
            <c:forEach items="${sessionScope.test}" var="quiz">
                <p>${quiz.key.description}</p>
                <c:forEach items="${quiz.value}" var ="selection">
                    <input type="checkbox" name="${selection.questionId}${selection.id}" value="${selection.charId}">
                    ${selection.charId}.${selection.description}
                    <br>
                </c:forEach>
            </c:forEach>
            <input type="submit" name="finish" value="Finish">
        </form>
    </body>
</html>
