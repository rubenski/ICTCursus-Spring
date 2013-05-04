$(document).ready(function() {

    $(".toggleAccountRequestData").click(function(event) {
        event.preventDefault();

        var currentRow = $(this).closest('tr');
        var nextRow = currentRow.next();
        var isDataRowPresent = nextRow.hasClass("accountrequestdata") && !nextRow.hasClass('hidden');

        if (!isDataRowPresent) {
            nextRow.removeClass('hidden');
            $(this).text("Verberg...");
        } else {
            nextRow.addClass('hidden');
            $(this).text("Toon meer...");
        }



    });

});