<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox" id="facets">
    <c:forEach items="${searchResult.facetFieldViews}" var="view">
        <h2><spring:message key="${view.getFieldHeaderKey()}"/></h2>

        <c:if test="${view.provideClearLink}">
            <c:set var="textkey" value="facet.field.clearlink.${view.fieldName}"/>
            <a href="#" id="clear-${view.fieldName}"><spring:message key="${textkey}" /></a><br/>
        </c:if>

        <c:forEach items="${view.filterLinks}" var="link" varStatus="loop">
            <input type="checkbox" name="price" id="${link.getUrlToken()}" value="/${dir}/${link.getCompleteUrl()}"/>
                <label for="${link.getUrlToken()}">
                    <spring:message key="${link.getNameKey()}"/></label> (${link.getCount()})<br>
        </c:forEach>
    </c:forEach>
</div>