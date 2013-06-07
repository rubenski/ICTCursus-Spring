<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="nl" class="no-js">
<head>
    <meta charset="utf-8"/>
    <link href="/static/style/shared.css" rel="stylesheet">
    <link href="/static/style/style.css" rel="stylesheet">

    <c:if test="${showLightboxLink or includeCourseJs}">
        <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    </c:if>
    <c:if test="${includeCourseJs}">
        <script type="text/javascript" src="/static/javascript/course.js"></script>
    </c:if>
    <c:if test="${showLightboxLink}">
        <script type="text/javascript" src="/static/javascript/lightbox.js"></script>
    </c:if>

    <title>
        ${title}
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
                            <a href="/admin/logout"><spring:message code='generic.message.logout'/></a>
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
        <jsp:include page="left.jsp"/>
        <div id="maincontent">
            <jsp:include page="${mainContent}.jsp"/>
        </div>
        <jsp:include page="${rightColumn}.jsp"/>
    </div>
</div>

<footer id="site-footer">
    hallo
</footer>


</body>
</html>