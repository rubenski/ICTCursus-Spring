<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox">
    <c:forEach items="${searchResult.produxFacetFields}" var="facetField">
        <h2><spring:message key="${facetField.getFieldHeaderKey()}"/></h2>
        <c:forEach items="${facetField.values}" var="nameValue">
            <a href="${nameValue.getUrl()}"><spring:message key="${nameValue.getNameKey()}"/></a> (${nameValue.getCount()})<br>
        </c:forEach>
    </c:forEach>
</div>