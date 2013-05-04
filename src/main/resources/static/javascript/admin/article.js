$(document).ready(function() {

    updatePageOrder();

    $(".pageup, .pagedown").click(function(event) {

        event.preventDefault();

        var row = $(this).parents("tr:first");
        if ($(this).is(".pageup")) {
            row.insertBefore(row.prev());
        } else {
            row.insertAfter(row.next());
        }

        updatePageOrder();

    });

    function updatePageOrder() {
        var idOrder = "";
        var position = 1;
        var length = $('.pagerow').length;

        $("input[name='pageOrder']").remove();


        $(".pagerow").each(function() {

            var pageId = $(this).attr("id");
            var upper = $(this).find(".pageup");
            var downer = $(this).find(".pagedown");

            if (position == 1) {
                upper.css("display", "none");
            } else {
                upper.css("display", "inline");
            }

            if (position == length) {
                downer.css("display", "none");
            } else {
                downer.css("display", "inline");
            }

            $("#editArticleFormData").append("<input type='hidden' name='pageOrder' value='" + position + "-" + pageId + "'/>");

            position++;
        });
    }


});