$(document).ready(function() {

    if ($("#scrollhere").length > 0) {
        $('html, body').animate({
            scrollTop: $("#scrollhere").offset().top
        }, 750);
    }

});

