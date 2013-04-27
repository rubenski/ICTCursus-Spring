<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/style.css" rel="stylesheet">
    <title>
        <jsp:include page="components/title.jsp"/>
    </title>
</head>
<body>

<header id="site-header">
    <div id="innerheader">
        <a href="/"><span id="logo">ICTCursus</span></a>
        <nav id="topnav">
            <ul>
                <li><a href="#">Voor cursusaanbieders</a></li>
                <li><a href="/login">Inloggen</a></li>
            </ul>
        </nav>
    </div>
    <div id="search">
        <div id="innersearch">
            <jsp:include page="components/search.jsp"/>
        </div>
    </div>
</header>


<div id="main">
    <div id="innermain">
        <nav id="topics">
            <jsp:include page="components/categorylisting.jsp"/>
        </nav>

        <jsp:include page="${mainContent}.jsp"/>
    </div>
</div>

<footer id="site-footer">
    hallo
</footer>


</body>
</html>