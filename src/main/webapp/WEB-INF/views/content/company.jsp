<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>${company.name}</h1>


<table class="default company" width="100%">
    <tr>
        <td width="50%">
            <c:if test="${company.hasLogo()}">
                <img src="${company.getNormalLogoUrl()}"/>
            </c:if>
        </td>
        <td>
            <table class="default-table">
                <tr>
                    <td>${company.name}</td>
                </tr>
                <tr>
                    <td>${company.address}</td>
                </tr>
                <tr>
                    <td>${company.zipCode} ${company.city}</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<p>
    ${company.description}
</p>


