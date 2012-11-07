<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/style.css" rel="stylesheet">
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
            <jsp:include page="components/menu.jsp"/>
        </div>
        <div id="main-content">
            <jsp:include page="${mainContent}.jsp"/>
        </div>
    </div>
    <div id="footer">
        <jsp:include page="components/footer.jsp"/>
    </div>
</div>
</body>
</html>


