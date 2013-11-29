$(document).ready(function() {

    $('.lightbox-trigger').click(function(e) {

        e.preventDefault();

        var category = $(this).attr("id");

        if ($('#lightbox').length == 0) {
            $.ajax({
                url: "/admin/highlightcourses/" + category,
                cache: false
            }).done(function(html) {
                    $(html).hide().appendTo($("body")).fadeIn(400);
                    setIframeHeight($("#highlight-iframe"));
                });
        }
    });

    $("#formsubmit").click(function(e) {
        e.preventDefault();
    });
});

function setIframeHeight(iframe) {
    if (iframe) {
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
}

function closePanel() {
    $('#lightbox').fadeOut(200, function(){
        $(this).remove();
    });
}

