$(document).ready(function() {

    $("input[name='remove']").click(function(event){
        event.preventDefault();
        var test = confirm("Wilt u de pagina verwijderen? Verwijderen kan niet ongedaan gemaakt worden!");

        if(test){
            $("#articlePageFormData").append("<input type='hidden' name='remove' value='1'/>");

            $("#articlePageFormData").submit();
        }
    });


});