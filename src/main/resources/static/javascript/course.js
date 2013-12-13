$(document).ready(function () {

    if ($("#scrollhere").length > 0) {
        $('html, body').animate({
            scrollTop: $("#scrollhere").offset().top
        }, 750);
    }

    $("#linktosite").click(function (e) {
        e.preventDefault();
        var courseUrl = $(this).attr('href');


    var courseId = $("#courseId").val();
    var url = $("#linktosite").attr('href');

    $.post("/externallinkclicked",
        {
            courseId: courseId,
            url: url
        },
        function (data, status) {
            window.location = url;
        });
    });
});

