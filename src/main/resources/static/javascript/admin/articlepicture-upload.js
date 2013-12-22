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
            updateArticlePicture();
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
                    removeUploadPanel();
                    updateArticlePicture();
                }
            });
        })();


        function updateArticlePicture() {

            var articleId = $("#id").val();

            var hasPicture = "/admin/article/haspicture/" + articleId;

            $.post(hasPicture, function (data) {

                    if (data == true) {

                        var logoUrl = "/article/picture/" + articleId + ".png";
                        $.ajax(logoUrl,
                            {
                                statusCode: {
                                    200: function () {

                                        // Remove a potential existing logo from the dom
                                        $('#companyLogo').empty();

                                        $("<img/>")
                                            .attr('src', logoUrl)
                                            .attr('id', 'companyLogoImg')
                                            .appendTo($('#companyLogo'));
                                    }
                                }
                            });

                        var removeLink = $(".removelogo");
                        if (removeLink.length == 0) {
                            $(".lightbox").after("&nbsp;&nbsp;<a href='#' class='removelogo'>Verwijder</a>");
                            $(".removelogo").click(function () {
                                $.post("/admin/article/removepicture/" + articleId,
                                    {
                                        success: function () {
                                            removeImage();
                                            removeRemovalLink();
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

        function removeImage() {
            $("#companyLogoImg").remove();
        }

        function removeRemovalLink() {
            $(".removelogo").remove();
        }
    }
);





