$('.button-auth').click(function (e) {
    let year = $('#authorYear').val();
    if (!isNaN(year) || year < 0 || year.toString().length < 10) {
        return true;
    } else {
        e.preventDefault();
        $('#authorYear').val("").css('border-bottom', '3px solid orangered');
    }
});

$('#updateAuthor').click(function (e) {
    let year = $('#authorYearUpdate').val();
    let id = $('#upd-id').val();
    if (!isNaN(id) && id > 0 && !isNaN(year) && year > 0 && id.toString().length < 18 && year.toString().length < 10) {
        return true;
    } else {
        e.preventDefault();
        if (isNaN(id) || id.toString().length > 18) {
            $('#authorUpdateId').val("").css('border-bottom', '3px solid orangered');
        } else {
            $('#authorYearUpdate').val("").css('border-bottom', '3px solid orangered');
        }
    }
})


$('#saveBook').click(function (e) {
    let id = $('#authorId').val();
    let year = $('#bookYear').val();
    let price = $('#price').val();
    if (!isNaN(id) && !isNaN(year) && !isNaN(price) && id.toString().length < 18 && year.toString().length < 10
            && price.toString().length < 10) {
        return true;
    } else {
        e.preventDefault();
        if (isNaN(id) || id < 1 || year.toString().length > 18) {
            $('#authorId').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(year) || year < 1000 || year.toString().length > 10) {
            $('#bookYear').val("").css('border-bottom', '3px solid orangered');
        }
        if (isNaN(price) || price < 0 || price.toString().length > 10) {
            $('#price').val("").css('border-bottom', '3px solid orangered');
        }
    }
});


$('.hide-author').hide();
$('.insert-author-form').hide();

function showAuthor() {
    $('.update-author-form').hide(750, function () {
        $('.hide-author-update').hide();
        $('.show-author-update').show();
        $('.insert-author-form').show(1000, function () {
            $('.show-author').hide();
            $('.hide-author').show();
            $('.hide-author').click(function () {
                $('.hide-author').hide();
                $('.show-author').show();
                $('.insert-author-form').hide(1000);
            })
        })
    })
}

$('.update-author-form').hide();
$('.hide-author-update').hide();
$('.show-author-update').click(function () {
    $('.show-author-update').hide();
    $('.insert-author-form').hide(750, function () {
        $('.update-author-form').show(750);
        $('.hide-author').hide();
        $('.show-author').show();
    });
    $('.hide-author-update').show().click(function () {
        $('.update-author-form').hide();
        $('.hide-author-update').hide();
        $('.show-author-update').show();
    })
});
