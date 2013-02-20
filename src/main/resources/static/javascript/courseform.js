$(document).ready(function() {

    (function() {
        $('#tagSelection').keyup(tagboxTypingHandler)
    })();

    (function() {
        $('#tagSelection').keydown(tagSelectionKeyHandler);
    })();

    (function() {
        var addTagButton = $('#addTag');

        var regex = new RegExp("^[a-zA-Z0-9#@ -]{2,20}$");
        var errorContainer = $('#tagError');
        var selectionTable = $('#selectedTags table');
        var inputField = $('#tagSelection');

        addTagButton.click(function(event) {
            addSelectedValueClickHandler(event, 'tags', regex, inputField, errorContainer, selectionTable)
        });
    })();

    (function() {
        var addDateButton = $('#addDate');

        var regex = new RegExp("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        var errorContainer = $('#dateError');
        var selectionTable = $('#selectedDates table');
        var inputField = $('#dateSelection');

        addDateButton.click(function(event) {
            addSelectedValueClickHandler(event, 'dates', regex, inputField, errorContainer, selectionTable)
        });
    })();


    function addSelectedValueClickHandler(event, valueTypeName, regex, inputField, errorContainer, selectionTable) {
        event.preventDefault();

        var value = inputField.val();

        if (!regex.test(value)) {
            alert(errorContainer.text());
            return;
        }

        var selectedValues = selectionTable.find(".selectedValue");

        var add = true;
        selectedValues.each(function() {
            var existingValue = $(this).text();
            if (value == existingValue) {
                alert(value + " is al toegevoegd aan de lijst");
                add = false;
            }
        });

        if (add) {
            // Add new value to screen
            selectionTable.append("<tr><td class='selectedValue'>" + value + "</td><td><a href='#' id='" + value + "'>verwijder</a></td></tr>");

            // Set a remove click handler on the 'verwijder' link
            selectionTable.find("[id='" + value + "']").click(function(event) {
                event.preventDefault();

                $(this).closest("tr").remove();

                // remove the hidden field
                $("input[value='" + value + "']").remove();

                var selectedValues = selectionTable.find(".selectedValue");

                if (selectedValues.length < 5) {
                    inputField.removeAttr('disabled');
                    inputField.css('background-color', '#ffffff');
                }
            });

            // Add hidden field
            $('#courseForm').append("<input type='hidden' name='" + valueTypeName + "' value='" + value + "'/>");

            if (selectedValues.length + 1 == 5) {
                inputField.attr('disabled', 'disabled');
                inputField.css('background-color', '#e4e4e4');
                inputField.val('');
            }
        }
    }

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

    (function() {
        $("#selectAllRegions").click(function(event) {
            event.preventDefault();
            $("input[name='regions']").attr('checked', true);
        })
    })();

    $(function() {
        $("#courseDates").datepicker({
            showOn: "button",
            buttonImage: "/static/img/calendar.gif",
            buttonImageOnly: true
        });
    });

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
                alert("Deze tag heeft u reeds geselecteerd");
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


    function addTagToScreen(name) {
        $('#selectedTags').append("<div class='selectedTag'><span>" + name + "</span><a href='#' title='" + name + "'><img src='/static/img/remove.png' width='10' height='10'/></a></div>");
        $("[title='" + name + "']").click(removeTagClickHandler);
    }

    function addDateToScreen(name) {
        $('#selectedDates').append("<div class='selectedDate'><span>" + name + "</span><a href='#' title='" + name + "'><img src='/static/img/remove.png' width='10' height='10'/></a></div>");
        $("[title='" + name + "']").click(removeDateClickHandler);
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

    function removeDateClickHandler(event) {
        event.preventDefault();

        var selectedDateElement = $(this.parentNode);
        var selectedDate = selectedDateElement.children().filter(':first').text();

        // remove the hidden field
        $("[value='" + selectedDate + "']").remove();
        // Remove the tag
        $(this.parentNode).remove();

        updateSelectedDatesHeader();

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