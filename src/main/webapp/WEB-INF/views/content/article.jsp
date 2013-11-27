<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="roundedbox" itemscope itemtype="http://schema.org/Article">

    <h1 itemprop="headLine">${currentArticlePage.title}</h1>

    <div itemprop="articleBody">
        ${currentArticlePage.text}
    </div>
    <div id="paging">
        <jsp:include page="../components/articlePageList.jsp">
            <jsp:param name="viewType" value="pagination" />
        </jsp:include>
    </div>
</div>

