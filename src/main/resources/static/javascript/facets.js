$(document).ready(function () {
    $("#facets input[type=checkbox]").change(function (e) {
        e.preventDefault();
        var id = $(this).attr("id");
        forward(id);
    });

    var pathName = window.location.pathname;
    var parts = pathName.split("/");
    var facetFilters = parts[2];

    if (typeof facetFilters !== 'undefined') {
        var filters = facetFilters.split("~");

        for (var i = 0; i < filters.length; i++) {
            var filter = filters[i];
            checkCheckBox(filter);
        }
    }

    uncheckCheckboxes();
    hideFilterLinksAfter("regions");
    hideFilterLinksAfter("tags");
});

function hideFilterLinksAfter(facet) {
    var child = 5;
    var filterLinks = $('.facet.' + facet + ' .facet-option:gt(' + child + ')');
    var lastLink = $('.facet.' + facet + ' .facet-option:nth-child(' + (child + 2) + ')');
    $('#less-' + facet).remove();

    var div = $('<div/>', {
        class: 'facet-option more',
        id: 'more-' + facet
    }).appendTo(lastLink);

    $('<a/>', {
        href: '#',
        text: 'Meer filters'
    }).click(function (e) {
            showFilterLinks(facet);
        })
        .appendTo(div);

    filterLinks.hide();
}


function showFilterLinks(facet) {
    $('.facet.' + facet + ' .facet-option').show();
    $("#more-" + facet).remove();

    var lastLink = $('.facet.' + facet + ' .facet-option:last-child');

    var div = $('<div/>', {
        class: 'facet-option more',
        id: 'less-' + facet
    }).appendTo(lastLink);

    $('<a/>', {
        href: '#',
        text: 'Minder filters'
    }).click(function (e) {
            hideFilterLinksAfter(facet);
        })
        .appendTo(div);
}

function checkCheckBox(id) {
    $("#" + escapeColon(id)).prop('checked', true);
}

function forward(id) {
    var url = $("#" + escapeColon(id)).val();
    window.location.href = url;
}

function escapeColon(id) {
    return id.replace(":", "\\:");
}

function uncheckCheckboxes() {
    $(".facet input[type='checkbox']").each(function () {
        if ($(this).prop('checked') && $(this).prop('disabled')) {
            $(this).prop('checked', false);
        }
    });
}



