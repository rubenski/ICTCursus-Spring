<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../components/adminproductsettingstabs.jsp"/>
<jsp:include page="../forms/submitmessage.jsp"/>


<div class="default-block">

    <h2><spring:message code="products.externalcourselinks.title"/></h2>
    <p><spring:message code="products.companyinfo.explanation"/></p>
    <form:form modelAttribute="productSettings">

        <form:hidden path="companyId" />
        <form:hidden path="courseListingType" />
        <form:hidden path="externalCourseLinksActive"/>

        <table cellpadding="4" width="600">
            <tr>
                <td><form:radiobutton path="companyInfoActive" value="false" /></td>
                <td><spring:message code="products.companyinfo.dontshow"  /></td>
            </tr>
            <tr>
                <td><form:radiobutton path="companyInfoActive" value="true" /></td>
                <td><spring:message code="products.companyinfo.show"  /></td>
            </tr>
        </table>
        <input type="submit" value="Opslaan"/>


    </form:form>

    <br/><br/>


    <h2><spring:message code="products.companyinfo.dontshow"/></h2>
    <p><spring:message code="products.companyinfo.explanation.none"  /></p>
    <p><spring:message code="generic.message.example"  />:</p>
    <img src="/static/img/company-info-simple.png"/>

    <br/><br/>

    <h2><spring:message code="products.companyinfo.show"/></h2>
    <p><spring:message code="products.companyinfo.explanation.enabled"  /></p>
    <p><spring:message code="generic.message.example"  />:</p>
    <p><img src="/static/img/company-info-advanced.png"/></p>
    <p><spring:message code="products.companyinfo.companypagexample"/>:</p>
    <p><img src="/static/img/company-page-example.png"/></p>


</div>


