$(document).ready(function() {

    $("#facets input[type=checkbox]").change(function(e){
        e.preventDefault();
        var id = $(this).attr("id");
        forward(id);
    });

    var pathName = window.location.pathname;
    var parts = pathName.split("/");
    var facetFilters = parts[2];

    var filters = facetFilters.split("~");

    for (var i = 0; i < filters.length; i++) {
        var filter = filters[i];
        checkCheckBox(filter);
    }

    uncheckCheckboxes();
});

function checkCheckBox(id){
    $("#" + escapeColon(id)).prop('checked', true);
}

function forward(id){
    var url = $("#" + escapeColon(id)).val();
    window.location.href =  url;
}

function escapeColon(id){
    return id.replace(":", "\\:");
}

function uncheckCheckboxes(){
    $(".facet input[type='checkbox']").each(function(){
        if($(this).prop('checked') && $(this).prop('disabled')){
            $(this).prop('checked', false);
        }
    });
}



