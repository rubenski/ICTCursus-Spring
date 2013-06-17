<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<h2><spring:message code="mainsite.label.topics"/></h2>

<nav class="prettymain">
    <ul>
        <c:forEach items="${categories}" var="category">
            <li><a href="/${category.urlTitle}">${category.name}</a>
            <c:forEach items="${category.children}" var="subCategory">
                <li><a href="/${subCategory.urlTitle}">${subCategory.name}</a></li>
            </c:forEach>
            </li>
        </c:forEach>
    </ul>
</nav>






