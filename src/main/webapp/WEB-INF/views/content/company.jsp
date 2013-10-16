<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>${company.name}</h1>

<c:if test="${company.hasLogo()}">
    <img src="${company.getNormalLogoUrl()}"/>
</c:if>

<p>
    ${company.description}
</p>