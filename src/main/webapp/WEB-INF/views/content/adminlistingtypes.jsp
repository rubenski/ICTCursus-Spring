<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../components/adminproductsettingstabs.jsp"/>
<jsp:include page="../forms/submitmessage.jsp"/>
<div class="default-block">

    <h2><spring:message code="products.listingtype.title"/></h2>
    <p><spring:message code="products.listingtypes.explanation"/></p>
    <form:form modelAttribute="productSettings">

        <form:hidden path="companyId" />
        <form:hidden path="companyInfoActive" />
        <form:hidden path="externalCourseLinksActive"/>

        <table cellpadding="4" width="600">
            <tr>
                <td><form:radiobutton path="courseListingType" value="Eenvoudig" /></td>
                <td><spring:message code="products.listingtypes.simple"  /></td>
            </tr>
            <tr>
                <td><form:radiobutton path="courseListingType" value="Uitgebreid" /></td>
                <td><spring:message code="products.listingtypes.advanced"  /></td>
            </tr>
        </table>
        <input type="submit" value="Opslaan"/>


    </form:form>
    <br/><br/>
    <h2><spring:message code="products.listingtypes.simple"  /></h2>
    <p><spring:message code="products.listingtype.explanation.simple"  /></p>
    <p><spring:message code="generic.message.example"  />:</p>
    <p><img src="/static/img/simple-course-item.png"/></p>
    <br/><br/>
    <h2><spring:message code="products.listingtypes.advanced"  /></h2>
    <p><spring:message code="products.listingtype.explanation.advanced"  /></p>
    <p><spring:message code="generic.message.example"  />:</p>
    <img src="/static/img/advanced-course-item.png"/>


</div>
