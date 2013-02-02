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

    (function() {
        $("[name = 'tags']").each(function() {
            addTagToScreen($(this).val());
        });
    })();

    (function(){
        $("#selectAllRegions").click(function(event){
            event.preventDefault();
            $("input[name='regions']").attr('checked', true);
        })
    })();

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
                $.get("/tag/nl.codebasesoftware.produx.search/" + substring, returnedTagsHandler);
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
        });

        $('#suggestBox a').click(tagSuggestionClickHandler);

    }

    function addTagClickHandler(event) {
        event.preventDefault();

        var tagName = $('#tagSelection').val();
        var regex = new RegExp("^[a-zA-Z0-9#@ -]+$");

        if (!regex.test(tagName)) {
            alert($('#tagCharactersError').text());
            return;
        }

        // Do nothing and return if the tag is too short
        if (tagName.length < 2) {
            var error = $('#tagLengthError').text();
            alert(error);
            return;
        }

        var dontAppend = false;
        $('.selectedTag span').each(function() {
            var existingName = $(this).text();
            if (name == existingName) {
                alert("not appending");
                dontAppend = true;
            }
        });

        if (!dontAppend) {
            addTagToScreen(tagName);
            addTagHiddenElement(tagName);
            updateSelectedTagsHeader();
        }

        $('#tagSelection').val('');

        if ($('.selectedTag').length == 5) {
            $('#tagSelection').attr('disabled', 'disabled');
        }
    }

    function addTagToScreen(name) {
        $('#selectedTags').append("<div class='selectedTag'><span>" + name + "</span><a href='#' title='" + name + "'><img src='/static/img/remove.png' width='10' height='10'/></a></div>");
        $("[title='" + name + "']").click(removeTagClickHandler);
    }

    function addTagHiddenElement(name) {
        $('#courseForm').append("<input type='hidden' name='tags' value='" + name + "'/>");
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

        var selectedTagElement = $(this.parentNode);
        var selectedTagName = selectedTagElement.children().filter(':first').text();

        // remove the hidden field
        $("[value='" + selectedTagName + "']").remove();
        // Remove the tag
        $(this.parentNode).remove();

        updateSelectedTagsHeader();

        if ($('.selectedTag').length < 5) {
            $('#tagSelection').removeAttr('disabled');
        }
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