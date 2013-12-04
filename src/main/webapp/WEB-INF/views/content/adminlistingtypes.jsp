<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../components/adminproductsettingstabs.jsp"/>
<div class="default-block">

    <h2><spring:message code="products.listingtype.title"/></h2>

    <form:form modelAttribute="productSettings">


        <table cellpadding="4" width="600">
            <tr>
                <td><form:radiobutton path="courseListingType" value="Eenvoudig" /></td>
                <td><spring:message code="products.listingtype.explanation.simple"  /><br/><br/>
                    <spring:message code="generic.message.example"  />:<br/> <br/>
                    <img src="/static/img/simple-course-item.png"/>
                </td>
            </tr>
            <tr>
                <td><form:radiobutton path="courseListingType" value="Uitgebreid" /></td>
                <td><spring:message code="products.listingtype.explanation.advanced"  /><br/><br/>
                    <spring:message code="generic.message.example"  />:<br/> <br/>
                    <img src="/static/img/advanced-course-item.png"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Opslaan"/>


    </form:form>
</div>
