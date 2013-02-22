$(document).ready(function() {

    (function() {
        $('#tagSelection').keyup(tagboxTypingHandler)
    })();

    (function() {
        $('#tagSelection').keydown(tagSelectionKeyHandler);
    })();

    (function() {
        var regex = new RegExp("^[a-zA-Z0-9#@ -]{2,20}$");
        var errorContainer = $('#tagError');
        var selectionTable = $('#selectedTags table');
        var inputField = $('#tagSelection');
        var addButton = $('#addTag');

        addButton.click(function(event) {
            event.preventDefault();
            if(testValue(regex, inputField.val(), errorContainer)){
                addValueToScreen('tags', inputField, inputField.val(), selectionTable, addButton);
            }
        });
    })();

    (function() {
        var regex = new RegExp("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        var errorContainer = $('#dateError');
        var selectionTable = $('#selectedDates table');
        var inputField = $('#dateSelection');
        var addButton = $('#addDateButton');

        addButton.click(function(event) {
            event.preventDefault();
            if (testValue(regex, inputField.val(), errorContainer)) {
                addValueToScreen('dates', inputField, inputField.val(), selectionTable, addButton);
            }
        });
    })();


    /**
     * Load course dates and set them in the screen on load
     */
    (function() {
        var url = window.location.pathname;

        var regex = new RegExp("^/manage/course/([0-9]+)$");

        if (regex.test(url)) {
            var courseId = url.split("/")[3];
            var selectionTable = $('#selectedDates table');
            var inputField = $('#dateSelection');
            var addButton = $('#addDateButton');

            $.get("/manage/course/dates/" + courseId, function(data) {
                for (var i in data) {
                    var storedDate = data[i];
                    addValueToScreen('dates', inputField, storedDate, selectionTable, addButton);
                }
            });
        }
    })();

    function testValue(regex, value, errorContainer) {
        if (!regex.test(value)) {
            alert(errorContainer.text());
            return false;
        }
        return true;
    }

    function addValueToScreen(valueTypeName, inputField, value, selectionTable, addButton) {

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
            // Add hidden field
            $('#courseForm').append("<input type='hidden' name='" + valueTypeName + "' value='" + value + "'/>");

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
                    addButton.removeClass('disabled-button');
                }
            });

            if (selectedValues.length + 1 == 5) {
                inputField.attr('disabled', 'disabled');
                inputField.css('background-color', '#d0d0d0');
                inputField.val('');
                addButton.addClass('disabled-button');
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
        $("#dateSelection").datepicker({
            showOn: "button",
            buttonImage: "/static/img/calendar.gif",
            buttonImageOnly: true
        });
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
                $.get("/tag/search/" + substring, returnedTagsHandler);
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