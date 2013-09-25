<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="whitebox" id="facets">
    <c:forEach items="${searchResult.facetFieldViews}" var="view">
        <div class="facet">
            <h2><spring:message key="${view.getFieldHeaderKey()}"/></h2>
            <c:forEach items="${view.filterLinks}" var="link">
                <c:choose>
                    <c:when test="${!link.hasDocuments()}">
                        <c:set var="disable" value="disabled=\"disabled\""/>
                        <c:set var="disableClass" value="disabled"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="disable" value=""/>
                        <c:set var="disableClass" value=""/>
                    </c:otherwise>
                </c:choose>
                <div>
                    <input type="checkbox" class="css-checkbox" name="price" id="${link.getUrlToken()}" value="/${searchResult.getBasePath()}/${link.getCompleteUrl()}" ${disable}/>
                    <label class="css-label ${disableClass}" for="${link.getUrlToken()}"> ${link.getLabel()} </label> (${link.getCount()})
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>