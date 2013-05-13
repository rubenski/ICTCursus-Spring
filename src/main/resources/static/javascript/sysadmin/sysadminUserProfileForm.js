$(document).ready(function() {

    $("input[name='removeProfile']").click(function(event) {
        event.preventDefault();

        if (confirm('Weet je zeker dat je dit profiel wil verwijderen?')) {
            $("#remove").attr('value', '1');
            $("#userProfile").submit();
        }

    });

});