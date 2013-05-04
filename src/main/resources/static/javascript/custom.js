$(document).ready(function() {

    $('#advertising-overlay-link').click(function(e) {
        e.preventDefault();
        showAdvertisingOverlay();
    });

    function showAdvertisingOverlay() {
        $("#lightbox, #lightbox-panel").fadeIn(300);
        $("#loginFormError").css('display', 'none');
    }

});