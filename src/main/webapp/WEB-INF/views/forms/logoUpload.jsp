<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/static/style/style.css" rel="stylesheet">
    <script type="text/javascript" src="/static/javascript/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            if ($("#uploaded").length > 0) {
                 window.parent.$('body').trigger('replaceLogo');
            }
        });
    </script>
</head>
<body>
<div id="lightboxContent">
    <c:if test="${uploaded == 1}">
        <span id="uploaded"/>
    </c:if>
    <form:form method="post" id="uploadForm" enctype="multipart/form-data" action="/admin/logo/upload" modelAttribute="bindableFileUpload">
        <div class="default-block">
            <form:label path="fileData"><spring:message code="company.upload.logo"/></form:label>
            <form:input type="file" path="fileData"/>
            <form:errors path="fileData" cssClass="form-error"/>
        </div>
        <div class="default-block">
            <input type="submit" value="upload"/>
        </div>
    </form:form>
    <iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe>
</div>
</body>
</html>

