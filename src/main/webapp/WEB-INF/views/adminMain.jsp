<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="nl" class="no-js">
<head>
    <meta charset="UTF-8">
    <jsp:include page="components/adminHtmlHeader.jsp"/>
    <title>
        ICTCursus admin
    </title>
</head>
<body>

<div id="wrapper">
    <header id="admin-header">
        <a href="/admin"><span id="logo">ICTCursus Admin</span></a>
    </header>
    <div id="middle">
        <sec:authorize access="hasRole('ROLE_PERM_access_admin_screens') or hasRole('ROLE_PERM_edit_everything')">
            <div id="left">
                <jsp:include page="components/adminMenu.jsp"/>
            </div>
            <div id="main-content">
                ${systemMessage}
                <jsp:include page="components/adminHeader.jsp"/>
                    ${mainMessage}
                <jsp:include page="${mainContent}.jsp"/>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
</html>


