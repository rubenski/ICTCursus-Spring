<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<jsp:include page="../components/breadcrumb.jsp"/>

<div itemscope itemtype="http://schema.org/Article">
    <h1 itemprop="name">${currentArticlePage.title}</h1>

    <div itemprop="articleBody">
        ${currentArticlePage.text}
    </div>
    <div id="paging">
        <jsp:include page="../components/articlePageList.jsp">
            <jsp:param name="viewType" value="pagination"/>
        </jsp:include>
    </div>
</div>


