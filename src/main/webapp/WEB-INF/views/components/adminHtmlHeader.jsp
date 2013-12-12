<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<meta charset="utf-8"/>
<link href="<spring:url value="/static/style/admin.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<spring:url value="/static/libs/jquery-ui-1.10.1.custom/css/smoothness/jquery-ui-1.10.1.custom.min.css"/>"/>
<script type="text/javascript" src="<spring:url value='/static/javascript/jquery-1.8.3.min.js'/>"></script>
<script type="text/javascript" src="<spring:url value='/static/libs/jquery-ui-1.10.1.custom/js/jquery-ui-1.10.1.custom.min.js'/>"></script>
<script type="text/javascript" src="<spring:url value='/static/libs/jquery-ui-1.10.1.custom/js/datepicker-lang/jquery.ui.datepicker-nl.js'/>"></script>


<c:if test="${courseForm}">
    <script type="text/javascript" src="/static/javascript/admin/courseform.js"></script>
    <script type="text/javascript" src="/static/libs/ckeditor/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            CKEDITOR.replace('longDescription',
                    {
                        customConfig: '/static/libs/ckeditor/produx_custom_config/produx_config.js',
                        height: '800px'
                    }
            );
        };
    </script>
</c:if>

<c:if test="${articleForm}">
    <script type="text/javascript" src="/static/javascript/admin/article.js"></script>
    <script type="text/javascript" src="/static/libs/ckeditor/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            CKEDITOR.replace('text',
                    {
                        customConfig: '/static/libs/ckeditor/produx_custom_config/produx_config.js',
                        height: '800px'
                    }
            );
        };
    </script>
</c:if>

<c:if test="${companyform}">
    <script type="text/javascript" src="/static/javascript/admin/file-upload.js"></script>
</c:if>

<c:if test="${articlePage}">
    <script type="text/javascript" src="/static/javascript/admin/articlepage.js"></script>
</c:if>

<c:if test="${accountRequestsPage}">
    <script type="text/javascript" src="/static/javascript/sysadmin/accountrequests.js"></script>
</c:if>
