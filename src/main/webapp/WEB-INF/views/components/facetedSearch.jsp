<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox" id="facets">
    <c:forEach items="${searchResult.facetFieldViews}" var="view">
        <h2><spring:message key="${view.getFieldHeaderKey()}"/></h2>
        <c:forEach items="${view.filterLinks}" var="link" varStatus="loop">
            <input type="checkbox" name="price" id="${link.asUrlToken()}" value="/${dir}/${link.getUrl()}"/>
                <label for="${link.asUrlToken()}">
                    <spring:message key="${link.getNameKey()}"/></label> (${link.getCount()})<br>
        </c:forEach>
    </c:forEach>
</div>