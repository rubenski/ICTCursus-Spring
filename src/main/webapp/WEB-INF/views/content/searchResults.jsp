<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

U heeft gezocht op: ${terms}
<br><br>
Het resultaat:
<br><br>

<c:forEach items="${courses}" var="course">
    ${course.name}<br>
</c:forEach>
<br><br>


<c:if test="${categoryFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${categoryFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${tagFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${tagFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${regionFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${regionFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${incompanyFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${incompanyFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${certificateFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${certificateFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${priceFacets.valueCount > 0}">
    <ul>
        <c:forEach items="${priceFacets.values}" var="facet">
            <c:if test="${facet.count > 0}">
                <li>${facet.name} (${facet.count})</li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>
