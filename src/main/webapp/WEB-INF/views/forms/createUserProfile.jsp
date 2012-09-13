<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" commandName="userProfile">
    <div id="data-entry-form">
        <form:hidden path="id" />
        <div class="form-entry">
            <form:label path="firstName">Voornaam</form:label>
            <form:input path="firstName" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="firstName" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <form:label path="lastName">Achternaam</form:label>
            <form:input path="lastName" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="lastName" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <form:label path="email">Email</form:label>
            <form:input path="email" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="email" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <form:label path="phone">Phone</form:label>
            <form:input path="phone" cssClass="form-input" cssErrorClass="form-input-error"/>
            <form:errors path="phone" cssClass="form-error" />
        </div>
        <div class="form-entry">
            <input type="submit" value="Verzenden"/>
        </div>
    </div>
</form:form>
