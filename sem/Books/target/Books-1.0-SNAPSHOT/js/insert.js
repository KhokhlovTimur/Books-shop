
$('.button-auth').click(function (e) {
    let year = $('#authorYear').val();
    if (!isNaN(year) || year < 0) {
        return true;
    } else {
        e.preventDefault();
        $('#authorYear').val("");
        $('#authorYear').css('border-bottom', '3px solid orangered')
    }
});


$('#saveBook').click(function (e) {
    let id = $('#authorId').val();
    let year = $('#bookYear').val();
    let price = $('#price').val();
    if (!isNaN(id) && !isNaN(year) && !isNaN(price)) {
        return true;
    } else {
        e.preventDefault();
        if (isNaN(id) || id < 1) {
            $('#authorId').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(year) || year < 1000) {
            $('#bookYear').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(price) || price < 0) {
            $('#price').val("").css('border-bottom', '3px solid orangered');
        }
    }
});


$('.hide-author').hide();
$('.insert-author-form').hide();

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