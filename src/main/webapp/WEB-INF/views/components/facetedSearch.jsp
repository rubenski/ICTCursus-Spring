<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox">
    <c:forEach items="${searchResult.facetFieldViews}" var="normalFacetFieldField">
        <h2><spring:message key="${normalFacetFieldField.getFieldHeaderKey()}"/></h2>
        <c:forEach items="${normalFacetFieldField.values}" var="nameValue">
            <a href="${nameValue.getUrlToken()}"><spring:message key="${nameValue.getNameKey()}"/></a> (${nameValue.getCount()})<br>
        </c:forEach>
    </c:forEach>
</div>