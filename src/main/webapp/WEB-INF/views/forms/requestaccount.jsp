<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="user.request.account"/></h1>
<p><spring:message code="explanation.request.account"/></p>
<form:form method="post" modelAttribute="accountRequestFormData">

    <jsp:include page="submitmessage.jsp"/>

    <div class="formcontainer">
        <div class="formblock">
            <h2><spring:message code="company.data"/></h2>
            <jsp:include page="companydataformpart.jsp"/>
        </div>
        <div class="formblock">
            <h2><spring:message code="personal.data"/></h2>
            <jsp:include page="personaldataformpart.jsp"/>
        </div> <br style="clear:both"/>
        <div class="default-block">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>

</form:form>



