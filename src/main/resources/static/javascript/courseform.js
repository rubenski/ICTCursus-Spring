$(document).ready(function() {

    var limitNumberOfDates = 30;
    var limitNumberOfTags = 5;

    (function() {
        $('#tagSelection').keyup(tagboxTypingHandler)
    })();

    (function() {
        $('#tagSelection').keydown(tagSelectionKeyHandler);
    })();

    /**
     * Check whether to show or hide the certificate name when the certificate checkbox is clicked
     */
    (function() {
        $("#certificate-checkbox").click(function() {
            showCertificateName();
        });
    })();

    /**
     * Check whether to show or hide the certificate name on page load
     */
    (function() {
        showCertificateName();
    })();

    /**
     * Checks whether to show or hide the certificate name field
     */
    function showCertificateName() {
        if (document.getElementById("certificate-checkbox")) {
            if (document.getElementById("certificate-checkbox").checked) {
                $("#certificate-name").css('display', 'block');
            } else {
                $("#certificate-name input").val("");
                $("#certificate-name").css('display', 'none');
            }
        }
    }


    /**
     * Add tags to the screen on page load
     */
    (function() {
        var regex = new RegExp("^[a-zA-Z0-9#@ -]{2,20}$");
        var errorContainer = $('#tagError');
        var selectionTable = $('#selectedTags table');
        var inputField = $('#tagSelection');
        var addButton = $('#addTag');

        addButton.click(function(event) {
            event.preventDefault();
            if (testValue(regex, inputField.val(), errorContainer)) {
                addValueAsHiddenField('tags', inputField, inputField.val(), selectionTable, addButton, limitNumberOfTags);
                reloadTags();
            }
        });
    })();


    /**
     * Add dates to the screen on page load
     */
    (function() {
        var regex = new RegExp("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        var errorContainer = $('#dateError');
        var selectionTable = $('#selectedDates table');
        var inputField = $('#dateSelection');
        var addButton = $('#addDateButton');

        addButton.click(function(event) {
            event.preventDefault();
            if (testValue(regex, inputField.val(), errorContainer)) {
                addValueAsHiddenField('dates', inputField, inputField.val(), selectionTable, addButton, limitNumberOfDates);
                reloadDates();
            }
        });
    })();


    /**
     *  Reload dates onload
     */
    (function() {
        reloadDates();
    })();


    /**
     * Reload tags
     */
    (function() {
        reloadTags();
    })();


    /**
     * Get course dates from dom and set them under the date field
     */
    function reloadDates() {

        var selectionTable = $('#selectedDates table');
        var inputField = $('#dateSelection');
        var addButton = $('#addDateButton');

        var dates = [];
        $("input[name='dates']").each(function() {

            var day = $(this).val().split("-")[0];
            var month = $(this).val().split("-")[1] - 1;  // month array is zero based in javascript
            var year = $(this).val().split("-")[2];

            var myDate = new Date();
            myDate.setFullYear(year);
            myDate.setMonth(month);
            myDate.setDate(day);
            dates.push(myDate);

            // Remove the value row from the screen. Rows will be sorted and re-added in the next step
            $("tr#" + createIdValue($(this).val())).remove();
        });

        dates.sort(date_sort_asc);

        for (var i = 0; i < dates.length; i++) {
            // addValueAsHiddenField("dates", inputField, formatDate("dd-mm-yy", dates[i]), selectionTable, addButton, limitNumberOfDates);
            addValueToScreen(inputField, formatDate("dd-mm-yy", dates[i]), selectionTable, addButton, limitNumberOfDates);
        }
    }


    /**
     *
     * Reload tags
     */
    function reloadTags() {

        var selectionTable = $('#selectedTags table');
        var inputField = $('#tagSelection');
        var addButton = $('#addTag');

        var tags = [];
        $("input[name='tags']").each(function() {
            tags.push($(this).val());
            // Remove the value row from the screen. Rows will be sorted and re-added in the next step
            $("tr#" + createIdValue($(this).val())).remove();
        });

        tags.sort();

        for (var i = 0; i < tags.length; i++) {
            addValueToScreen(inputField, tags[i], selectionTable, addButton, limitNumberOfDates);
        }
    }


    /**
     *
     * Ascending date sorter
     */
    function date_sort_asc(date1, date2) {
        if (date1 > date2) return 1;
        if (date1 < date2) return -1;
        return 0;
    }


    /**
     *
     * Format a date using the datepicker jquery ui plugin
     */
    function formatDate(dateFormat, datetime) {
        var formatted = $.datepicker.formatDate(dateFormat, datetime);
        return formatted;
    }


    /**
     *
     * Validate a value by regex
     */
    function testValue(regex, value, errorContainer) {
        if (!regex.test(value)) {
            alert(errorContainer.text());
            return false;
        }
        return true;
    }


    /**
     *
     * Add a value as hidden field
     */
    function addValueAsHiddenField(valueTypeName, inputField, value, selectionTable, addButton, limit) {

        var selectedValues = selectionTable.find(".selectedValue");

        var add = true;
        selectedValues.each(function() {
            var existingValue = $(this).text();
            if (value == existingValue) {
                alert(value + " is al toegevoegd");
                add = false;
            }
        });

        if (add) {
            // Add hidden field
            $('#courseForm').append("<input type='hidden' name='" + valueTypeName + "' value='" + value + "'/>");

            if (selectedValues.length + 1 == limit) {
                inputField.attr('disabled', 'disabled');
                inputField.css('background-color', '#d0d0d0');
                inputField.val('');
                addButton.addClass('disabled-button');
            }

            return true;
        }

        return false;
    }


    /**
     *
     * Creates an id value
     */
    function createIdValue(value) {
        return value.toLowerCase().replace(" ", "_");
    }


    /**
     *
     * Add a value to the screen
     */
    function addValueToScreen(inputField, value, selectionTable, addButton, limit) {

        var selectedValues = selectionTable.find(".selectedValue");

        var idValue = createIdValue(value);

        // Add new value to screen
        selectionTable.append("<tr class='selectedValueRow' id='" + idValue + "'><td class='selectedValue'>" + value + "</td><td><a href='#' id='" + value + "'>verwijder</a></td></tr>");

        // Set a remove click handler on the 'verwijder' link
        selectionTable.find("[id='" + value + "']").click(function(event) {
            event.preventDefault();

            $(this).closest("tr").remove();

            // Remove the hidden field
            $("input[value='" + value + "']").remove();

            var selectedValues = selectionTable.find(".selectedValue");

            if (selectedValues.length < limit) {
                inputField.removeAttr('disabled');
                inputField.css('background-color', '#ffffff');
                addButton.removeClass('disabled-button');
            }
        });

        if (selectedValues.length + 1 == limit) {
            inputField.attr('disabled', 'disabled');
            inputField.css('background-color', '#d0d0d0');
            inputField.val('');
            addButton.addClass('disabled-button');
        }
    }


    $(document).mouseup(function (e) {
        var container = $('#suggestBox');
        if (container.has(e.target).length === 0) {
            container.remove();
        }
    });


    /**
     * Selecting all regions
     */
    (function() {
        $("#selectAllRegions").click(function(event) {
            event.preventDefault();
            $("input[name='regions']").attr('checked', true);
        })
    })();


    /**
     * Initialize date picker
     */
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
            }
        }
    }


    /**
     *
     * Get tags from the server when typing
     */
    function tagboxTypingHandler(event) {
        if (event.keyCode != 40 && event.keyCode != 38 && event.keyCode != 13) {

            var substring = $('#tagSelection').val();
            if (substring.length > 1) {
                $.get("/tag/search/" + substring, returnedTagsHandler);
            }
        }
    }

    /**
     *
     * Shows the suggest box with tags
     */
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


    function tagSuggestionClickHandler(event) {
        var tagId = event.target.id;
        var tagName = $('#' + tagId).text();
        $('#tagSelection').val(tagName);
        $('#suggestBox').hide();
    }


});