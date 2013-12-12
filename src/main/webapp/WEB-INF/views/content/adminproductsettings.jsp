<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../forms/submitmessage.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        $("#cost").animate({ backgroundColor: "#000066" }, 500);
    });
</script>

<div class="default-block">

    <h2><spring:message code="product.settings.title"/></h2>
    <spring:message code="product.settings.explain"/>

    <form:form modelAttribute="productSettings">

        <form:hidden path="companyId" />

        <table class="productstable">
            <tr>
                <th>Uw huidige kostenprofiel</th>
            </tr>
            <tr>
                <td colspan="3" id="cost" class="center">
                    U betaalt momenteel <span class="big">${productSettings.percentagePerRequest} procent</span> van de cursusprijs per aanvraag die u binnenkrijgt via ictcursus.nl.<br/>
                    <c:if test="${productSettings.externalCourseLinksActive}">
                        U betaalt momenteel &#0128;0,75 per klik naar uw website via een directe link.<br/>
                    </c:if>
                </td>
            </tr>
        </table>

        <table class="productstable">
            <tr>
                <td width="33%">

                    <table cellpadding="4">
                        <tr>
                            <th colspan="2"><spring:message code="products.listingtype.title"/></th>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.listingtypes.simple" var="courseListingTypeEenvoudig" />
                                <form:radiobutton path="courseListingType" value="Eenvoudig" label="${courseListingTypeEenvoudig}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.listingtypes.advanced" var="courseListingTypeUitgebreid" />
                                <form:radiobutton path="courseListingType" value="Uitgebreid" label="${courseListingTypeUitgebreid}"/>
                            </td>
                        </tr>
                    </table>

                </td>

                <td width="33%">

                    <table cellpadding="4">
                        <tr>
                            <th colspan="2"><spring:message code="products.companyinfo.title"/></th>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.companyinfo.dontshow" var="companyInfoActiveLabelTrue"/>
                                <form:radiobutton path="companyInfoActive" value="false" label="${companyInfoActiveLabelTrue}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.companyinfo.show" var="companyInfoActiveLabelFalse"/>
                                <form:radiobutton path="companyInfoActive" value="true" label="${companyInfoActiveLabelFalse}" />
                            </td>
                        </tr>
                    </table>

                </td>

                <td width="33%">

                    <table cellpadding="4">
                        <tr>
                            <th colspan="2"><spring:message code="products.externalcourselinks.title"/></th>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.directlinks.none" var="externalCourseLinksActiveFalse"/>
                                <form:radiobutton path="externalCourseLinksActive" value="false" label="${externalCourseLinksActiveFalse}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="inlineCheckBox">
                                <spring:message code="products.directlinks.links"  var="externalCourseLinksActiveTrue"/>
                                <form:radiobutton path="externalCourseLinksActive" value="true" label="${externalCourseLinksActiveTrue}"/>
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td class="center"><a href="/admin/faq/products/listingtypes"><spring:message code="generic.message.moreinfo"/></a></td>
                <td class="center"><a href="/admin/faq/products/companyinfo"><spring:message code="generic.message.moreinfo"/></a></td>
                <td class="center"><a href="/admin/faq/products/directlinks"><spring:message code="generic.message.moreinfo"/></a></td>
            </tr>
            <tr>
                <td colspan="3" class="center"><input type="submit" value="Opslaan"/></td>
            </tr>
        </table>
    </form:form>


</div>


