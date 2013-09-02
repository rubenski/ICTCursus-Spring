$(document).ready(function () {

        $('a.lightbox').click(function (e) {
            e.preventDefault();
            showUploadPanel();
        });

        $('a#close-panel').click(function (e) {
            e.preventDefault();
            removeUploadPanel();
        });

        (function () {
            updateLogo();
        })();

        /**
         * Get contents of hidden iframe after upload form submission and either show
         * an error or remove the form and reload the logo though Ajax.
         */
        (function () {
            $("#upload-result-frame").load(function () {
                var frameContent = $("#upload-result-frame").contents().find("body").text();
                if (frameContent.trim().length > 0) {
                    alert(frameContent);
                } else {
                    $("#lightbox").fadeOut(200);
                    $("#lightbox-panel").fadeOut(200);
                    updateLogo();
                    updateCourseInSolr();
                }
            });
        })();


        function updateLogo() {

            var companyId = $("#id").val();

            var hasLogoUrl = "/admin/logo/has/" + companyId;
            $.post(hasLogoUrl, function (data) {
                    if (data == true) {

                        var logoUrl = "/logo/normal/" + companyId + "-cursusbedrijf.png";
                        $.ajax(logoUrl,
                            {
                                statusCode: {
                                    200: function () {
                                        $("<img/>")
                                            .attr('src', logoUrl)
                                            .attr('id', 'companyLogoImg')
                                            .appendTo($('#companyLogo'));
                                    }
                                }
                            });

                        var removeLink = $(".removelogo");
                        if (removeLink.length == 0) {
                            $(".lightbox").after("&nbsp;&nbsp;<a href='#' class='removelogo'>Verwijder logo</a>");
                            $(".removelogo").click(function () {
                                $.post("/admin/logo/remove/" + companyId,
                                    {
                                        success: function () {
                                            removeLogoImage();
                                            removeLogoRemovalLink();
                                            updateCourseInSolr();
                                        }
                                    });
                            });
                        }
                    }
                }
            );
        }


        function showUploadPanel() {
            $("#lightbox, #lightbox-panel").fadeIn(300);
            $("#loginFormError").css('display', 'none');
        }

        function removeUploadPanel() {
            $("#lightbox, #lightbox-panel").fadeOut(200);
        }

        function removeLogoImage() {
            $("#companyLogoImg").remove();
        }

        function removeLogoRemovalLink() {
            $(".removelogo").remove();
        }

        function updateCourseInSolr(){
            var companyId = $("#id").val();
            var url = "/admin/solr/updatecompany/" + companyId;
            $.post(url, function(result){
                alert(result);
            });
        }

    }
);





