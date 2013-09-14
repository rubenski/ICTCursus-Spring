<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox" id="facets">
    <c:forEach items="${searchResult.facetFieldViews}" var="view">
        <div class="facet">
            <h2><spring:message key="${view.getFieldHeaderKey()}"/></h2>
            <c:forEach items="${view.filterLinks}" var="link">
                <c:if test="${!link.hasDocuments()}">
                    <c:set var="disable" value="disabled=\"disabled\""/>
                    <c:set var="disableClass" value="class=\"disabled\""/>
                </c:if>

                <c:set var="label" value="${link.value}"/>
                <c:if test="${view.useSpringMessagesForValues}">
                    <spring:message key="${link.getNameKey()}" var="label"/>
                </c:if>

                <input type="checkbox" name="price" id="${link.getUrlToken()}"
                       value="/${dir}/${link.getCompleteUrl()}" ${disable}/>
                <label for="${link.getUrlToken()}" ${disableClass}> ${label} </label> (${link.getCount()})<br>
            </c:forEach>
        </div>
    </c:forEach>
</div>