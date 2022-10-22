$('.show-form').hide();

function clickHide() {
    $('.form').hide();
    $('.hide-form').hide();
    $('.show-form').show();
    $('.fx-table').animate({width: '+=435'}, 750, function () {
        $('.delete-button').animate({width: '100%'}, 450)
    })
}

function clickShow() {
    $('.fx-table').animate({width: '-=435'}, 750, function () {
        $('.delete-button').animate({width: '65%'}, 450, function () {
            $('.show-form').hide();
            $('.hide-form').show();
            $('.form').show(500);
        })
    })
}

$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() !== 0) {
            $('#toTop').fadeIn();
        } else {
            $('#toTop').fadeOut();
        }
    });
    $('#toTop').click(function () {
        $('body,html').animate({scrollTop: 0}, 800);
    });
});