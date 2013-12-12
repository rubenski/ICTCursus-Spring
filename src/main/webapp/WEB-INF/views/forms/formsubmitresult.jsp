<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${valid != true}">
    <div class="default-block error">
        Het formulier bevat fouten en is niet verstuurd.
    </div>
</c:if>