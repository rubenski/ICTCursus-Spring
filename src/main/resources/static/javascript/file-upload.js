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

        $('<iframe src="/logo/upload" scrolling="no" frameborder="0"/>').appendTo('#lightbox');

        var top = ($(window).height() - $('#lightbox').height()) / 2;
        var left = ($(window).width() - $('#lightbox').width()) / 2;

        $('#lightbox').css({
            'top' : top + $(document).scrollTop(),
            'left': left
        }).fadeIn();

        return false;
    });


    $('body').bind('logoUploaded', function(event, companyId) {
        $('#lightbox').remove();
        $('#companyLogoImg').remove();
        $('#overlay').remove();
        // var randomNumber = 1 + Math.floor(Math.random() * 100000);
        $.get("http://localhost:8080/logo/" + companyId, function(logoJsonData) {
            $("<img/>")
                .attr('src', 'data:' + logoJsonData.dataTypeString + ';base64,' + logoJsonData.base64EncodedData)
                .attr('id', 'companyLogoImg')
                .appendTo($('#companyLogo'));
        });
    });

    $('body').trigger('logoUploaded');

});

