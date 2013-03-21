<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/admin.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/libs/jquery-ui-1.10.1.custom/css/smoothness/jquery-ui-1.10.1.custom.min.css"/>
    <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/jquery-ui-1.10.1.custom.min.js"></script>
    <script type="text/javascript" src="/static/libs/jquery-ui-1.10.1.custom/js/datepicker-lang/jquery.ui.datepicker-nl.js"></script>
    <script type="text/javascript" src="/static/javascript/courseform.js"></script>
    <script type="text/javascript" src="/static/javascript/file-upload.js"></script>
    <script type="text/javascript" src="/static/javascript/tiny_mce/tiny_mce.js"></script>

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
<div id="wrapper">
    <div id="header">
        <jsp:include page="components/header.jsp"/>
    </div>
    <div id="middle">
        <sec:authorize access="hasRole('ROLE_PERM_access_admin_screens')">
            <div id="left">
                <jsp:include page="components/adminMenu.jsp"/>
            </div>
            <div id="main-content">
                <jsp:include page="components/adminHeader.jsp"/>
                ${mainMessage}
                <jsp:include page="${mainContent}.jsp"/>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
</html>


