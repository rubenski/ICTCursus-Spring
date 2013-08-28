<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
<c:forEach items="${result}" var="doc">
    <tr>
        <td>${doc}</td>

    </tr>

</c:forEach>

<tr><td>range facets</td></tr>

<c:forEach items="${rangeFacets}" var="rangeFacet">
    <tr>
        <td>${rangeFacet}</td>

    </tr>

</c:forEach>
</table>