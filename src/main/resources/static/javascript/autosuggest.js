$(document).ready(function() {

    (function() {
        $('#tagSelection').keyup(tagboxTypingHandler)
    })();

    (function() {
        $('#tagSelection').keydown(tagSelectionKeyHandler);
    })();

    (function() {
        var addTagButton = $('#addTag');
        addTagButton.click(addTagClickHandler);
    })();

    $(document).mouseup(function (e) {
        var container = $('#suggestBox');
        if (container.has(e.target).length === 0) {
            container.remove();
        }
    });

    function tagSelectionKeyHandler(event) {
        if ($('#suggestBox').length > 0) {

            var selectedTag = $('.tagSelected');

            if (event.keyCode == 40) {
                if (selectedTag.length == 0) {
                    $('#suggestBox').children().filter(':first').addClass('tagSelected');
                } else {
                    if (selectedTag.next().length > 0) {
                        selectedTag.removeClass('tagSelected');
                        selectedTag.next().addClass('tagSelected');
                    }
                }
            } else if (event.keyCode == 38) {
                if (selectedTag.prev().length > 0) {
                    selectedTag.removeClass('tagSelected');
                    selectedTag.prev().addClass('tagSelected');
                }
            } else if (event.keyCode == 13) {
                event.preventDefault();
                alert("enter was pressed");
                return false;
            }
        }
    }

    function tagboxTypingHandler(event) {
        if (event.keyCode != 40 && event.keyCode != 38 && event.keyCode != 13) {

            var substring = $('#tagSelection').val();
            if (substring.length > 1) {
                $.get("http://localhost:8080/tag/search/" + substring, returnedTagsHandler);
            }
        }
    }

    function returnedTagsHandler(returnedTags) {
        // If there is no suggest box yet, draw it
        if ($('#suggestBox').length == 0) {
            $('#tagSelection').after("<div id=\"suggestBox\"></div>");
        }

        $('#suggestBox').show();

        // Clear the existing contents of the suggest box
        $('#suggestBox').empty();

        for (var i in returnedTags) {
            var tag = returnedTags[i];
            $('#suggestBox').append("<a href='#' id='" + tag.id + "'>" + tag.name + "</a>");
        }

        $('#suggestBox a').click(function(event) {
            event.preventDefault();
        })

        $('#suggestBox a').click(tagSuggestionClickHandler);


    }

    function addTagClickHandler(event) {
        event.preventDefault();

        var tagName = $('#tagSelection').val();
        var regex = new RegExp("^[a-zA-Z0-9#@ -]+$");

        if(!regex.test(tagName)){
            alert("wrong");
            return false;
        }

        // Do nothing and return if the tag is too short
        if (tagName.length < 2) {
            var error = $('#tagLengthError').text();
            alert(error);
            return;
        }

        // Test if the tag exists
        $.get("http://localhost:8080/tag/byname/" + tagName, function(tag) {

            var id = -1;
            var name = '';
            if (!tag) {
                name = $('#tagSelection').val();
            } else {
                id = tag.id;
                name = tag.name;
            }

            var dontAppend = false;
            $('.selectedTag span').each(function() {
                var existingName = $(this).text();
                if (name == existingName) {
                    dontAppend = true;
                }
            });

            if (!dontAppend) {
                $('#selectedTags').append("<div class='selectedTag' id='" + id + "'><span>" + name + "</span><a href='#'><img src='/static/img/remove.png' width='10' height='10'/></a></div>");
                // Add hidden fields to the form
                $('#courseForm').append("<input type='hidden' name='tags' value='" + name + "'/>");
                updateSelectedTagsHeader();
                $('#selectedTags a').click(removeTagClickHandler);
            }

            $('#tagSelection').val('');
        });
    }


    function updateSelectedTagsHeader() {
        if ($('.selectedTag').size() > 0 && $('#selectedTagsHeader').length == 0) {
            $('#selectedTags').prepend("<span id='selectedTagsHeader'>Geselecteerde tags</span>");
        }

        if ($('.selectedTag').size() == 0 && $('#selectedTagsHeader').length > 0) {
            $('#selectedTagsHeader').remove();
        }
    }

    function removeTagClickHandler(event) {
        event.preventDefault();

        $(this.parentNode).remove(); // parents('.selectedTag').remove();

        updateSelectedTagsHeader();
    }

    function tagSuggestionClickHandler(event) {
        var tagId = event.target.id;
        var tagName = $('#' + tagId).text();
        $('#tagSelection').val(tagName);
        hideSuggestBox();
    }

    function hideSuggestBox() {
        $('#suggestBox').hide();
    }


});