<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<nav class="whitebox">
    <h2><spring:message code="mainsite.label.topics"/></h2>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li><a href="/${category.urlTitle}">${category.name}</a></li>
        </c:forEach>
    </ul>
</nav>






