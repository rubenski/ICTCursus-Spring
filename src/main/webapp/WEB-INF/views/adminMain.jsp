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
    <script type="text/javascript" src="/static/javascript/admin/courseform.js"></script>
    <c:if test="${companyform}">
        <script type="text/javascript" src="/static/javascript/admin/file-upload.js"></script>
    </c:if>
    <c:if test="${articleform}">
        <script type="text/javascript" src="/static/javascript/admin/article.js"></script>
    </c:if>
    <c:if test="${articlePage}">
        <script type="text/javascript" src="/static/javascript/admin/articlepage.js"></script>
    </c:if>
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
        ICT Cursus admin
    </title>
</head>
<body>


<div id="wrapper">
    <header id="admin-header">
        <a href="/"><span id="logo">ICTCursus Admin</span></a>
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


