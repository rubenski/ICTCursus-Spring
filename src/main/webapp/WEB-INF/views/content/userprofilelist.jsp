<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
<c:forEach items="${userProfiles}" var="userProfile">
    <li>${userProfile.firstName} ${userProfile.lastName}</li>
</c:forEach>
</ul>