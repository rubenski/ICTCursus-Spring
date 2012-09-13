<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="post" action="adduserprofile" modelAttribute="bindableUserProfile">
    <table>
        <tr>
            <td><form:label path="firstName">Voornaam</form:label></td>
            <td>
                <form:input path="firstName"/>
                <form:errors path="firstName" />
            </td>
        </tr>
        <tr>
            <td><form:label path="lastName">Achternaam</form:label></td>
            <td>
                <form:input path="lastName"/>
                <form:errors path="lastName" />
            </td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td>
                <form:input path="email"/>
                <form:errors path="email" />
            </td>
        </tr>
        <tr>
            <td><form:label path="phone">Telefoon</form:label></td>
            <td>
                <form:input path="phone"/>
                <form:errors path="phone" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Verzenden"/>
            </td>
        </tr>
    </table>
</form:form>
