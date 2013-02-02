<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<ul>
    <c:forEach items="${categories}" var="category">
        <li><a href="/c/${category.urlTitle}">${category.name}</a>
            <c:if test="${fn:length(category.children) > 0}">
                <ul>
                    <c:forEach items="${category.children}" var="subCategory">
                        <li><a href="/c/${subCategory.urlTitle}">${subCategory.name}</a>
                    </c:forEach>
                </ul>
            </c:if>
        </li>
    </c:forEach>
</ul>