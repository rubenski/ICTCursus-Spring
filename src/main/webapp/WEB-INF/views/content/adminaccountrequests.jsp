<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="default-block">
    <h2><spring:message code="accountrequests.nonevaluated"/></h2>
    <c:choose>
        <c:when test="${numberOfUnevaluatedRequests > 0}">

            <table cellpadding="4">
                <tr>
                    <th><spring:message code="generic.message.id"/></th>
                    <th><spring:message code="sysadmin.account.request.date"/></th>
                    <th><spring:message code="generic.message.name"/></th>
                    <th><spring:message code="company.form.name"/></th>
                    <th><spring:message code="company.form.chamberofcommercenumber"/></th>
                    <th><spring:message code="accountrequest.accept"/> / <spring:message code="accountrequest.reject"/></th>

                </tr>
                <c:forEach items="${unevaluatedRequests}" var="basicRequest">
                    <tr>
                        <td>
                            <a href="/admin/sys/accountrequests/${basicRequest.request.id}">${basicRequest.request.id}</a>
                        </td>
                        <td>
                            <fmt:formatDate value="${basicRequest.request.requestDate.time}" type="date" pattern="dd-MM-yyyy HH:mm"/>
                        </td>
                        <td>
                                ${basicRequest.request.fullNameFormal}
                        </td>
                        <td>
                                ${basicRequest.request.companyName}
                        </td>

                        <td>
                                ${basicRequest.request.tradeNumber}
                        </td>
                        <td>
                            <a class="toggleAccountRequestData" href="#"><spring:message code="accountrequest.moreinfolink"/></a>
                        </td>
                    </tr>
                    <tr class="hidden accountrequestdata">
                        <td class="emptycell">
                        </td>
                        <td colspan="5">

                            <form method="post" action="/admin/sys/accountrequests/acceptreject">
                                <input type="hidden" name="id" value="${basicRequest.request.id}"/>
                                <table width="100%">
                                    <tr>
                                        <th class="subheader">
                                            <spring:message code="generic.message.data"/>
                                        </th>
                                        <th class="subheader">
                                            <spring:message code="accountrequest.evaluation.message"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td width="50%">
                                            <table>
                                                <tr>
                                                    <td>Ok?</td>

                                                    <c:if test="${basicRequest.ok}">
                                                        <td><spring:message code="generic.message.ok"/></td>
                                                    </c:if>
                                                    <c:if test="${!basicRequest.ok}">
                                                        <td class="error"><spring:message code="generic.message.notok"/></td>
                                                    </c:if>

                                                </tr>
                                                <tr>
                                                    <td><spring:message code="user.email.exists"/></td>
                                                    <td>
                                                        <c:if test="${basicRequest.userEmailExists}"><span class="error"><spring:message
                                                                code="generic.message.yes"/></span></c:if>
                                                        <c:if test="${!basicRequest.userEmailExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.email.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyEmailExists}"><span class="error"><spring:message
                                                                code="generic.message.yes"/></span></c:if>
                                                        <c:if test="${!basicRequest.companyEmailExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.name.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyNameExists}"><span class="error"><spring:message
                                                                code="generic.message.yes"/></span></c:if>
                                                        <c:if test="${!basicRequest.companyNameExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.vat.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyVatExists}"><span class="error"><spring:message
                                                                code="generic.message.yes"/></span></c:if>
                                                        <c:if test="${!basicRequest.companyVatExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.prefix.notpresent"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyPrefixNotPresent}"><span class="error"><spring:message
                                                                code="generic.message.yes"/></span></c:if>
                                                        <c:if test="${!basicRequest.companyPrefixNotPresent}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="50%">
                                            <textarea rows="10" cols="45" name="adminMessage"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="right-aligned">
                                            <c:if test="${basicRequest.ok}"><input type="submit" name="accept" value="Toekennen"/></c:if>
                                            <c:if test="${!basicRequest.ok}"><input type="submit" name="reject" value="Afwijzen"/></c:if>
                                        </td>
                                    </tr>
                                </table>
                            </form>


                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="sysadmin.norequests.message"/>
        </c:otherwise>
    </c:choose>

</div>

<div class="default-block">

    <h2><spring:message code="accountrequests.evaluated"/></h2>

    <c:choose>
        <c:when test="${numberOfEvaluatedRequests > 0}">

            <table cellpadding="4">
                <tr>
                    <th><spring:message code="generic.message.id"/></th>
                    <th><spring:message code="sysadmin.account.request.date"/></th>
                    <th><spring:message code="generic.message.name"/></th>
                    <th><spring:message code="company.form.name"/></th>
                    <th><spring:message code="company.form.chamberofcommercenumber"/></th>
                    <th><spring:message code="accountrequest.accepted.header"/></th>
                    <th><spring:message code="accountrequest.moreinfoheader"/></th>

                </tr>
                <c:forEach items="${evaluatedRequests}" var="basicRequest">
                    <tr>
                        <td>
                            <a href="/admin/sys/accountrequests/${basicRequest.request.id}">${basicRequest.request.id}</a>
                        </td>
                        <td>
                            <fmt:formatDate value="${basicRequest.request.requestDate.time}" type="date" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>
                                ${basicRequest.request.fullNameFormal}
                        </td>
                        <td>
                                ${basicRequest.request.companyName}
                        </td>

                        <td>
                                ${basicRequest.request.tradeNumber}
                        </td>
                        <td>
                            <c:if test="${basicRequest.request.isGranted()}">
                                <spring:message code="generic.message.yes"/>
                            </c:if>
                            <c:if test="${!basicRequest.request.isGranted()}">
                                <spring:message code="generic.message.no"/>
                            </c:if>
                        </td>
                        <td>
                            <a class="toggleAccountRequestData" href="#"><spring:message code="accountrequest.moreinfolink"/></a>
                        </td>
                    </tr>
                    <tr class="hidden accountrequestdata">
                        <td class="emptycell">
                        </td>
                        <td colspan="6">


                            <table width="100%">
                                <tr>
                                    <c:if test="${!basicRequest.request.isGranted()}">
                                        <th class="subheader">
                                            <spring:message code="generic.message.data"/>
                                        </th>
                                    </c:if>
                                    <th class="subheader">
                                        <spring:message code="accountrequest.evaluation.message"/>
                                    </th>
                                </tr>
                                <tr>
                                    <c:if test="${!basicRequest.request.isGranted()}">
                                        <td width="50%">
                                            <table>
                                                <tr>
                                                    <td>Ok?</td>

                                                    <c:if test="${basicRequest.ok}">
                                                        <td><spring:message code="generic.message.ok"/></td>
                                                    </c:if>
                                                    <c:if test="${!basicRequest.ok}">
                                                        <td class="error"><spring:message code="generic.message.notok"/></td>
                                                    </c:if>

                                                </tr>
                                                <tr>
                                                    <td><spring:message code="user.email.exists"/></td>
                                                    <td>
                                                        <c:if test="${basicRequest.userEmailExists}"><spring:message
                                                                code="generic.message.yes"/></c:if>
                                                        <c:if test="${!basicRequest.userEmailExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.email.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyEmailExists}"><spring:message
                                                                code="generic.message.yes"/></c:if>
                                                        <c:if test="${!basicRequest.companyEmailExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.name.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyNameExists}"><spring:message
                                                                code="generic.message.yes"/></c:if>
                                                        <c:if test="${!basicRequest.companyNameExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.vat.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyVatExists}"><spring:message
                                                                code="generic.message.yes"/></c:if>
                                                        <c:if test="${!basicRequest.companyVatExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <spring:message code="company.tradenumber.exists"/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${basicRequest.companyTradeNumberExists}"><spring:message
                                                                code="generic.message.yes"/></c:if>
                                                        <c:if test="${!basicRequest.companyTradeNumberExists}"><spring:message
                                                                code="generic.message.no"/></c:if>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </c:if>
                                    <td width="50%">
                                        <textarea rows="10" cols="45" name="adminMessage"
                                                  disabled="disabled">${basicRequest.request.adminMessage}</textarea>
                                    </td>
                                </tr>

                            </table>


                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        <c:otherwise>
            <spring:message code="sysadmin.norequests.message"/>
        </c:otherwise>
    </c:choose>
</div>
