<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>





<div class="default-block">

    <p>Results for batch with id ${invoiceBatch.id} started at <fmt:formatDate value="${invoiceBatch.jobStarted.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/>
        and completed at <fmt:formatDate value="${invoiceBatch.jobCompleted.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/>.</p>

    <table>
        <tr>
            <th>Attempt</th>
            <th>Invoice</th>
            <th>Time</th>
            <th>Status</th>
            <th>Exception</th>
        </tr>
    <c:forEach items="${invoiceBatch.invoiceProcessingAttempts}" var="attempt">
        <tr>
            <td>${attempt.id}</td>
            <td>${attempt.invoice.id}</td>
            <td><fmt:formatDate value="${attempt.timeSent.time}" type="date" pattern="dd-MM-yyyy HH:mm:ss"/></td>
            <td>${attempt.status}</td>
            <td>${attempt.exceptionStackTrace}</td>
        </tr>
    </c:forEach>

    </table>

</div>