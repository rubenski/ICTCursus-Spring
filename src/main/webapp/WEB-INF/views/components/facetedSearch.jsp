<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox" id="facets">
    <c:forEach items="${searchResult.facetFieldViews}" var="view">
        <h2><spring:message key="${view.getFieldHeaderKey()}"/></h2>
        <c:forEach items="${view.filterLinks}" var="link" varStatus="loop">
            <c:set var="id" value="${link.fieldName}-${link.value}"/>
            <input type="checkbox" name="price" id="${id}" value="/${dir}/${link.getUrl()}"/>
                <label for="${id}">
                    <spring:message key="${link.getNameKey()}"/></label> (${link.getCount()})<br>
        </c:forEach>
    </c:forEach>
</div>