$(document).ready(function() {

    (function() {
        $('#tagSelection').keyup(function(e) {

                var substring = $('#tagSelection').val();

                if (substring.length > 1) {

                    $.get("http://localhost:8080/tag/" + substring, function(returnedData) {

                            // If there is no suggest box yet, draw it
                            if ($('#suggestBox').length == 0) {
                                $('#tagSelection').after("<div id=\"suggestBox\"></div>");
                            }

                            $('#suggestBox').show();

                            // Clear the existing contents of the suggest box
                            $('#suggestBox').empty();

                            for (var i in returnedData) {
                                var tag = returnedData[i];
                                $('#suggestBox').append("<a href='#'>" + tag.name + "</a>");
                            }

                            $('#suggestBox a').click(function(event){
                                event.preventDefault();
                            })

                        }
                    );
                }
            }
        )
    })();


    $(document).mouseup(function (e) {
        var container = $('#suggestBox');

        if (container.has(e.target).length === 0) {
            container.hide();
        }
    });

});