<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="post" modelAttribute="accountRequestFormData">

    <jsp:include page="submitmessage.jsp"/>

    <jsp:include page="companydataformpart.jsp"/>
    <jsp:include page="personaldataformpart.jsp"/>

    <div class="default-block">
        <input type="submit" value="Opslaan"/>
    </div>


</form:form>



