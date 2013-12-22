<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../components/adminyourcompanytabs.jsp"/>

<div class="default-block">
    <h2><spring:message code="admin.your.invoices"/></h2>
    <c:choose>
        <c:when test="${numberOfInvoices > 0}">

            <table cellpadding="4" width="600">
                <tr>
                    <th><spring:message code="admin.invoice.number"/></th>
                    <th><spring:message code="generic.userMessage.date"/></th>
                    <th><spring:message code="admin.invoice.download"/></th>
                </tr>
                <c:forEach items="${invoices}" var="invoice">
                    <tr>
                        <td>
                            ${invoice.invoiceNumber}
                        </td>
                        <td>
                            ${invoice.getCreationDatePretty()}
                        </td>
                        <td>
                           <a href="/admin/invoices/${invoice.id}">${invoice.getInvoiceTitle()}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="admin.invoice.noinvoices"/>
        </c:otherwise>
    </c:choose>
</div>
