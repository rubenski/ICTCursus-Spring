<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="lightbox" style="display: none;"></div>


<div id="lightbox-panel" class="upload-lightbox">
    <form:form method="post" target="upload-result" id="uploadForm" enctype="multipart/form-data" action="<%=request.getParameter("postUrl")%>" modelAttribute="bindableFileUpload">
        <div class="default-block">
            <form:label path="fileData"><spring:message code="generic.message.upload"/></form:label>
            <form:input type="file" path="fileData"/>
            <form:errors path="fileData" cssClass="form-error"/>
            <br/><br/>
            <input type="submit" value="upload" class="subbutton" id="uploadbutton"/>
        </div>
    </form:form>

    <p align="center">
        <a id="close-panel" href="#">Sluiten</a>
    </p>
</div>

<iframe name="upload-result" id="upload-result-frame">

</iframe>