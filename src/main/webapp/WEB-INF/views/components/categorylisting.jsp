<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2><spring:message code="mainsite.label.topics"/></h2>
<ul>
    <c:forEach items="${categories}" var="category">
        <li><a href="/c/${category.urlTitle}">${category.name}</a>
        <c:forEach items="${category.children}" var="subCategory">
            <li><a href="/c/${subCategory.urlTitle}">${subCategory.name}</a></li>
        </c:forEach>
        </li>
    </c:forEach>
</ul>
