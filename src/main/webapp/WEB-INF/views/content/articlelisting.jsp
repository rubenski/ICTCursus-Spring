<%--@elvariable id="category" type="nl.codebasesoftware.produx.domain.dto.entity."--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="right" class="whitebox">
    <header>Online cursussen en artikelen over ${category.name}</header>

    <c:forEach items="${articles}" var="article">
        <div class="course-list-item">
            <div class="description">
                <a href="${article.url}"><h3>${article.title}</h3></a>
                <p>${article.teaser}</p>
            </div>
        </div>
    </c:forEach>
</div>
