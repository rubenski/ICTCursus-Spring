<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<c:if test="${param.error == 'true'}">
    <span class="error"><spring:message code="login.form.error"/></span>
</c:if>

<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
    <div id="data-entry-form">

        <div class="form-entry">
            <label><spring:message code="login.form.label.email"/></label>
            <input type='text' name='j_username' value=''>
        </div>
        <div class="form-entry">
            <label><spring:message code="login.form.label.password"/></label>
            <input type='password' name='j_password'/>
        </div>
        <div class="form-entry">
            <input type="submit" value="Verzenden"/>
        </div>
        <div class="form-entry">
            <a href="/login/requestpassword"><spring:message code="login.form.label.forgotpassword"/></a>
        </div>
    </div>
</form>