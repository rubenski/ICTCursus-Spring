<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/style.css" rel="stylesheet">
    <script type="text/javascript" src="/static/javascript/custom.js"></script>
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
                <c:choose>
                    <c:when test="${loggedIn}">
                        <li><a href="/admin">Admin</a></li>
                        <li>
                            <a href="/logout"><spring:message code='generic.message.logout'/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <a href="/login">Inloggen</a>
                    </c:otherwise>
                </c:choose>
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
        <jsp:include page="${leftColumn}.jsp"/>
        <div id="maincontent">
            <jsp:include page="${mainContent}.jsp"/>
        </div>
    </div>
</div>

<footer id="site-footer">
    hallo
</footer>


</body>
</html>