$(document).ready(function() {

    $('.lightbox-trigger').click(function(e) {

        e.preventDefault();

        var category = $(this).attr("id");

        if ($('#lightbox').length == 0) {
            $.ajax({
                url: "/admin/highlightcourses/" + category,
                cache: false
            }).done(function(html) {
                    $("body").append(html);
                });
        }
    });

    $("#close-panel").click(function() {
        alert("test");
        $('#lightbox').hide();
    });

    $("#formsubmit").click(function(e){
        e.preventDefault();

    });
});

