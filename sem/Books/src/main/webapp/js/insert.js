
$('.hide-author').hide();
$('.insert-author-form').hide();

$('.button-auth').click(function (e) {
    var id = $('#year').val();
    if (!isNaN(id)) {
        return true;
    } else {
        e.preventDefault();
        $('#year').val("");
        $('#year').css('border-bottom', '3px solid orangered')
    }
});

function showAuthor() {
    $('.insert-author-form').show(1000, function () {
        $('.show-author').hide();
        $('.hide-author').show();
        $('.hide-author').click(function () {
            $('.hide-author').hide();
            $('.show-author').show();
            $('.insert-author-form').hide(1000);
        })
    })
}

function hideClick() {
    $('.hide-book').click(function () {
        $('.hide-book').hide();
        $('.show-book').show();
        $('.authors-div').hide(1000, function () {
            $('.insert-book-form').hide(1000)
        })
    })
}