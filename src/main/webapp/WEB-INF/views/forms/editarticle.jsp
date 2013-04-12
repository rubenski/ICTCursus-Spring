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
        <form:label path="title"><spring:message code="article.form.title"/></form:label>
        <form:input path="title" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="title" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="teaser"><spring:message code="article.form.teaser"/></form:label>
        <form:textarea cols="75" rows="4" path="teaser" cssClass="form-input" cssErrorClass="form-input-error" size="60" maxlength="60"/>
        <form:errors path="teaser" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="published">
            <spring:message code="article.form.articleStatusheader"/>
            <span><spring:message code="article.form.articleStatusheader.helptext"/></span>
        </form:label>
        <spring:message code="admin.articles.published" var="label"/>
        <span class="inlineCheckBox"><form:checkbox disabled="${publishingDisabled}" path="published" label="${label}" /></span>
        <form:errors path="published" cssClass="form-error"/>
    </div>

    <div class="default-block">
        <form:label path="pages"><spring:message code="article.form.pages"/></form:label>
        <table cellpadding="4" width="600">
            <tr>
                <th><spring:message code="admin.articles.pages"/></th>
            </tr>
            <c:choose>
                <c:when test="${numberOfPages > 0}">
                    <c:forEach items="${pages}" var="page">
                        <tr>
                            <td>
                                <a href="/admin/articles/page/${page.id}">${page.title}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><spring:message code="admin.article.nopages"/></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <a href="/admin/article/${id}/pages/add"><spring:message code="admin.article.add.page"/></a>

    </div>


    <div class="default-block">
        <input type="submit" class="submitbutton" value="<spring:message code="generic.message.save"/>"/>
    </div>

</form:form>