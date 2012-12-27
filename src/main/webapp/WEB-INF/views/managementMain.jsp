<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/style.css" rel="stylesheet">
    <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/static/javascript/autosuggest.js"></script>
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
        <div id="menu">
            <jsp:include page="components/managementMenu.jsp"/>
        </div>
        <div id="main-content">
            ${mainMessage}
            <jsp:include page="${mainContent}.jsp"/>
        </div>
    </div>
    <div id="footer">
        <jsp:include page="components/footer.jsp"/>
    </div>
</div>
</body>
</html>


