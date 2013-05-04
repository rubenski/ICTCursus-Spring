<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!doctype html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/admin.css" rel="stylesheet">
    <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/static/javascript/tiny_mce/tiny_mce.js"></script>
    <c:if test="${accountRequestsPage}">
        <script type="text/javascript" src="/static/javascript/sysadmin/accountrequests.js"></script>
    </c:if>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "specific_textareas",
            editor_selector : "richtext",
            theme_advanced_buttons1 : "bold, italic, underline, separator, cut, copy, paste, separator, bullist, numlist, separator, undo, redo, separator, link, unlink",
            content_css: "/static/style/admin.css"

        });
    </script>
    <title>
        <jsp:include page="components/title.jsp"/>
    </title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_PERM_edit_everything')">
    <div id="wrapper">

        <jsp:include page="components/header.jsp"/>

        <div id="middle">
            <div id="left">
                <jsp:include page="components/sysAdminMenu.jsp"/>
            </div>
            <div id="main-content">
                <jsp:include page="components/adminHeader.jsp"/>
                    ${mainMessage}
                <jsp:include page="${mainContent}.jsp"/>
            </div>

        </div>
    </div>
</sec:authorize>
</body>
</html>

