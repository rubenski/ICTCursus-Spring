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
            wordt.
        </p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_requests') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.requests"/></h2>

        <p>Bij <a href="/admin/courserequests">aanvragen</a> vindt u de informatie-aanvragen die vanaf de site verstuurd zijn door bezoekers.</p>
    </div>
</sec:authorize>


<sec:authorize access="hasRole('ROLE_PERM_edit_company_articles') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.articles"/></h2>

        <p>U kunt bij ICT Cursus zelf <a href="/admin/articles">artikelen</a> publiceren. Onder uw artikelen verschijnt uw bedrijfslogo en een
            lijst
            met uw cursussen. Door zelf
            artikelen
            te schrijven trekt u meer bezoekers via de zoekmachines en u laat zien welke kennis un in huis heeft. Artikelen zijn een effectieve en
            gratis methode
            om uw cursussen extra onder de aandacht te brengen. U kunt in de sectie 'artikelen' een voorstel indienen voor een artikel. Wij zullen contact met u opnemen om uw voorstel te
            bespreken.</p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_users') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.users"/></h2>

        <p>U kunt collega's binnen uw bedrijf ook toegang geven tot ICT Cursus, zodat u uw account met meerdere mensen kunt beheren.
            Bij <a href="/admin/users">gebruikersbeheer</a> beheert u de gebruikers die binnen uw bedrijf toegang hebben tot ICT Cursus. </p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_users') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="admin.sections.invitations"/></h2>

        <p>Bij <a href="/admin/invitations">collega's uitnodigen</a> kunt u collega's binnen uw bedrijf toegang geven tot ICT Cursus, zodat u gezamelijk uw ICT Cursus account kunt beheren.</p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_PERM_edit_company_settings') or hasRole('ROLE_PERM_edit_everything')">
    <div class="default-block">
        <h2><spring:message code="company.settings"/></h2>

        <p>Bij <a href="/admin/settings">instellingen</a> kunt u instellingen doen met betrekking tot uw account bij ICT Cursus. Zo kunt u
            een automatische waarschuwing laten versturen als er een bepaald bedrag overschreden is en u kunt uw cursussen tijdelijk depubliceren.
            Bij
            instellingen
            kunt u ook instellen naar welk emailadres aanvragen verstuurd worden.</p>
    </div>
</sec:authorize>

