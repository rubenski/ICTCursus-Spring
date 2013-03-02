$(document).ready(function() {

    $('a.lightbox').click(function(e) {
        e.preventDefault();
        showUploadPanel();

    });

    function showUploadPanel() {
        $("#lightbox, #lightbox-panel").fadeIn(300);
        $("#loginFormError").css('display', 'none');
    }

    $('body').bind('replaceLogo', function(event) {
        $('#lightbox').remove();
        $('#companyLogoImg').remove();
        $('#overlay').remove();

        $.get("/company/getcurrent", function(company) {

            $.get("/logo/" + company.id, function(logoJsonData) {

                if (logoJsonData) {
                    $("<img/>")
                        .attr('src', 'data:' + logoJsonData.dataTypeString + ';base64,' + logoJsonData.base64EncodedData)
                        .attr('id', 'companyLogoImg')
                        .appendTo($('#companyLogo'));
                }

            });
        });
    });

    $('#companyLogo').trigger('replaceLogo');

});

