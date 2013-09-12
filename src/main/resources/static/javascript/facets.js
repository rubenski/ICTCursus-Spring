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
        alert(filter);
    }
});

function forward(id){
    var url = $("#" + id).val();
    window.location.href =  url;
}



