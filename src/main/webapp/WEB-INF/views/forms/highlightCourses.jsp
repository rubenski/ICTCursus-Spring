<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="lightbox">
    <div id="lightbox-panel" class="advertising-lightbox">
        <h1>${headerText}</h1>

        <p>
            <spring:message code="highlight.your.courses.explain"/>
        </p>

        <script language="JavaScript">
            <!--
            function autoResize(id) {
                var newheight;
                var newwidth;

                if (document.getElementById) {
                    newheight = document.getElementById(id).contentWindow.document.body.scrollHeight;
                    newwidth = document.getElementById(id).contentWindow.document.body.scrollWidth;
                }

                document.getElementById(id).height = (newheight) + "px";
                document.getElementById(id).width = (newwidth) + "px";
            }
            //-->
        </script>

        <iframe id="highlight-iframe" onLoad="autoResize('highlight-iframe');" src="/admin/form/highlightcourses/${category.id}" width="700" height="400"
                frameBorder="0" scrolling="no"/>

        <p align="center">
            <a onclick="javascript:closePanel()" href="#">Sluiten</a>
        </p>
    </div>
</div>



