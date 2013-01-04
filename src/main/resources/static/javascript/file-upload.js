$(document).ready(function() {

    $('a.lightbox').click(function(e) {

        $('body').css('overflow-y', 'hidden');

        $('<div id="overlay"></div>')
            .css('top', $(document).scrollTop())
            .css('opacity', 0)
            .animate({'opacity' : '0.5'}, 'slow')
            .appendTo('body');

        $('<div id="lightbox"></div>')
            .hide()
            .appendTo('body');

        $('<iframe src="/manage/logo/upload" scrolling="no" frameborder="0"/>').appendTo('#lightbox');

        var top = ($(window).height() - $('#lightbox').height()) / 2;
        var left = ($(window).width() - $('#lightbox').width()) / 2;

        $('#lightbox').css({
            'top' : top + $(document).scrollTop(),
            'left': left
        }).fadeIn();

        return false;
    });


    $('body').bind('replaceLogo', function(event) {
        $('#lightbox').remove();
        $('#companyLogoImg').remove();
        $('#overlay').remove();


        $.get("/company/getcurrent", function(company) {


            $.get("/logo/" + company.id, function(logoJsonData) {



                if(logoJsonData){
                    $("<img/>")
                    .attr('src', 'data:' + logoJsonData.dataTypeString + ';base64,' + logoJsonData.base64EncodedData)
                    .attr('id', 'companyLogoImg')
                    .appendTo($('#companyLogo'));
                }

                // trigger a redraw by getting the offsetHeight (edo inspired trick)
                var triggerRedraw = $('#companyLogoImg').offsetHeight;

            });

        });


    });

    $('body').trigger('replaceLogo');

});

