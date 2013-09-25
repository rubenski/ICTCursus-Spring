<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="right" class="whitebox">
    <h2>Online cursussen en artikelen over ${category.name}</h2>

    <c:forEach items="${articles}" var="article">
        <div class="course-list-item">
            <div class="description">
                <a href="${article.url}"><h3>${article.title}</h3></a>
                <p>${article.teaser}</p>
            </div>
        </div>
    </c:forEach>
</div>
