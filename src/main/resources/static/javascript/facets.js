$(document).ready(function() {



    $("#facets input[type=checkbox]").change(function(e){
        e.preventDefault();
        var id = $(this).attr("id");
        forward(id);
    });

});

function forward(id){
    var url = $("#" + id).val();
    window.location.href =  url;
}

