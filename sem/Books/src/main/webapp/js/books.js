$('.show-form').hide();

$('#saveBook').click(function (e) {
    let authorId = $('#authorUpdateId').val();
    let id = $('#bookUpdateId').val();
    let year = $('#updateYear').val();
    let price = $('#updatePrice').val();
    if (!isNaN(id) && !isNaN(year) && !isNaN(authorId) && !isNaN(price)
        && id.toString().length < 18 && year.toString().length < 10 && price.toString().length < 10
        && authorId.toString().length < 18) {
        return true;
    } else {
        e.preventDefault();
        if (isNaN(id) || id < 1 || id.toString().length > 18) {
            $('#bookUpdateId').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(year) || year < 1000 || year.toString().length > 10) {
            $('#updateYear').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(price) || price < 0 || price.toString().length > 10) {
            $('#updatePrice').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(authorId) || authorId < 1 || authorId.toString().length > 18) {
            $('#authorUpdateId').val("").css('border-bottom', '3px solid orangered');
        }
    }
});

function clickHide() {
    $('.form').hide();
    $('.hide-form').hide();
    $('.show-form').show();
    $('.fx-table').animate({width: '+=435'}, 750);
    $('.delete-button').animate({width: '100%'}, 450)
}

function clickShow() {
    $('.fx-table').animate({width: '-=435'}, 750)
    $('.delete-button').animate({width: '65%'}, 450, function () {
        $('.show-form').hide();
        $('.hide-form').show();
        $('.form').show(750);
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
})

