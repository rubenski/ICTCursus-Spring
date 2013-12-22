<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<sec:authorize access="hasRole('ROLE_PERM_edit_company_courses') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.courses"/></h2>

        <p>In de categorie <a href="/admin/courses">cursussen</a> vindt u uw cursussen. U kunt hier cursussen aanmaken, publiceren, depubliceren,
            wijzigen en
            verwijderen.</p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_profile') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.companyinfo"/></h2>

        <p>Uw <a href="/admin/company">bedrijfsprofiel</a> bevat informatie over uw bedrijf, waaronder het logo dat naast uw cursussen getoond
            wordt als u bij 'Productinstellingen' voor de uitgebreide weergave van uw cursussen kiest. Ook kunt u bij 'uw bedrijf' diverse instellingen doen.
        </p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_requests') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.requests"/></h2>

        <p>Bij <a href="/admin/courserequests">aanvragen</a> vindt u de informatie-aanvragen die vanaf de site verstuurd zijn voor uw cursussen.</p>
    </div>
</sec:authorize>


<sec:authorize access="hasRole('ROLE_PERM_edit_company_articles') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.articles"/></h2>

        <p>U kunt bij ICT Cursus zelf <a href="/admin/articles">artikelen</a> publiceren. Onder uw artikelen verschijnt uw bedrijfslogo en een
            lijst met uw cursussen. Het plaatsen van artikelen is een gratis manier om de kennis die uw bedrijf heeft onder de aandacht te brengen.
            Voor artikelen gelden redactionele richtlijnen. Lees svp de FAQ over artikelen voor meer informatie.</p>
    </div>
</sec:authorize>


<sec:authorize access="hasRole('ROLE_PERM_edit_product_settings') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="product.settings.title"/></h2>

        <p>Bij <a href="/admin/productsettings">productinstellingen</a> kunt u de manier waarop uw cursussen en bedrijfsinformatie weergegeven worden aanpassen.</p>
    </div>
</sec:authorize>

<div class="default-block">
    <h2><spring:message code="user.edit.myprofile"/></h2>

    <p>Bij <a href="/admin/myprofile"><spring:message code="user.edit.myprofile"/></a> kunt u uw persoonlijke informatie aanpassen en uw wachtwoord wijzigen.</p>
</div>

