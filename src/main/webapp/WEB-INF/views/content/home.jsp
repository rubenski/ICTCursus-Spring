<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul>
    <c:forEach items="${categories}" var="category">
        <li>${category.name}</li>
    </c:forEach>
</ul>