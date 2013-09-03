<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox">
    <c:forEach items="${searchResult.produxFacetFields}" var="facetField">
        <h2><spring:message key="${facetField.getFieldHeaderKey()}"/></h2>
        <c:forEach items="${facetField.values}" var="nameValue">
            <spring:message key="${nameValue.getNameKey()}"/> (${nameValue.value})<br>
        </c:forEach>
    </c:forEach>
</div>