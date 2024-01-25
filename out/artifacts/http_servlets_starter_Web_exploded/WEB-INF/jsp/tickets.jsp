<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Купленные билеты</h1>
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${ticket.seatNo}</li>
        </c:forEach>
    </ul>
</body>
</html>
