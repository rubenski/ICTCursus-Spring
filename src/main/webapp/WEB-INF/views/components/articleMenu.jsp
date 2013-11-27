<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="roundedbox">
    <span class="header"><spring:message code="generic.message.paginas"/></span>
    <jsp:include page="articlePageList.jsp">
        <jsp:param name="viewType" value="menu" />
    </jsp:include>
</nav>
