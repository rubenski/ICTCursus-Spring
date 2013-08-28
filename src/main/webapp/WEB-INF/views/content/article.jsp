<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="whitebox" itemscope itemtype="http://schema.org/Article">

    <h1 itemprop="headLine">${currentArticlePage.title}</h1>

    <span itemprop="articleBody">
        ${currentArticlePage.text}
    </span>
</div>

