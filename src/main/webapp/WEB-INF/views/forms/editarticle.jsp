<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="editArticleFormData">

    <form:hidden path="id"/>

    <%-- error / success feedback --%>
    <jsp:include page="submitmessage.jsp"/>

    <div class="default-block">
        <form:label path="published">
            <spring:message code="article.form.articleStatusheader"/>
            <span><spring:message code="article.form.articleStatusheader.helptext"/></span>
        </form:label>
        <spring:message code="admin.articles.published" var="label"/>
        <span class="inlineCheckBox"><form:checkbox path="published" label="${label}"/></span>
        <form:errors path="published" cssClass="form-error"/>
    </div>


    <div class="default-block">
        <form:label path="title"><spring:message code="article.form.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="100"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="teaser">
            <spring:message code="article.form.teaser"/>
            <span><spring:message code="article.shortdescription.helptext"/></span>
        </form:label>
        <form:textarea cols="75" rows="2" path="teaser" cssClass="form-input" cssErrorClass="form-input-error" size="50" maxlength="100"/>
        <form:errors path="teaser" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="category"><spring:message code="course.form.category"/></form:label>
        <form:select path="category" cssErrorClass="form-input-error">
            <form:option value="-1" label=""/>
            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="category" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="text">
            <spring:message code="article.firstpagetext"/>
            <span><spring:message code="article.firstpagetext.helptext"/></span>
        </form:label>
        <form:textarea path="text" cols="100" rows="50" cssClass="richtext" cssErrorClass="richtext form-input-error" maxlength="6000"/>
        <form:errors path="text" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="pages">
            <spring:message code="article.form.pages"/>
            <span><spring:message code="article.form.pages.helptext"/></span>
        </form:label>
        <table cellpadding="4" width="600" id="pagetable">
            <tr>
                <th><spring:message code="generic.message.title"/></th>
                <th colspan="2"><spring:message code="article.page.position"/></th>
            </tr>
            <c:choose>
                <c:when test="${numberOfPages > 0}">
                    <c:forEach items="${pages}" var="page" varStatus="status">
                        <tr class="pagerow" id="${page.id}">
                            <td>
                                <a href="/admin/articles/page/${page.id}">${page.title}</a>
                            </td>
                            <td>
                                <a href="#" class="pageup" title="<spring:message code="page.moveup"/> id="${page.id}">&#8679;</a>
                            </td>
                            <td class="downer">
                                <a href="#" class="pagedown" title="<spring:message code="page.movedown"/>" id="${page.id}">&#8681;</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="2"><spring:message code="admin.article.nopages"/></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <a href="/admin/article/${editArticleFormData.id}/pages/add"><spring:message code="admin.article.add.page"/></a>

    </div>


    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>