$(function () {
    $('.container').scroll(function () {
        if ($('.container').scrollTop() !== 0) {
            $('#top').fadeIn();
        } else {
            $('#top').fadeOut();
        }
    });
    $('#top').click(function () {
        $('.container').animate({scrollTop: 0}, 800);
    });
});