<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Pagina's</h2>

<nav class="prettymain">
    <ul>
        <li><a href="${article.url}">${article.title}</a></li>
        <c:forEach items="${article.pages}" var="page">
            <li><a href="${page.url}">${page.title}</a></li>
        </c:forEach>
    </ul>
</nav>
