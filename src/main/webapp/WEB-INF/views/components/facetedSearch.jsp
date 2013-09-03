<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="whitebox">
    <c:forEach items="${searchResult.produxFacetFields}" var="facetField">
        <h2>${facetField.fieldName}</h2>
        <c:forEach items="${facetField.values}" var="entry">
            ${entry.key} (${entry.value})<br>
        </c:forEach>
    </c:forEach>
</div>