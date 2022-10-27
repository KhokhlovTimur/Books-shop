$('.historyhide').hide();
$('.block2').hide();

function showH() {
    $('.history').hide();
    $('.historyhide').show();
    $('.info-block').animate({'left': '-=200'}, 2000)
    $('.block2').show(1200);
}

function hideHis() {
    $('.historyhide').hide();
    $('.history').show();
    $('.info-block').animate({'left': '+=200'}, 650);
    $('.block2').hide(1800);
}

$('.balance-input').hide();
$('.subm').hide();
$('.hh').hide();
$('.balance-button').click(function () {
    $('.balance-input').show();
    $('.subm').show();
    $('.hh').show();
    $('.balance-button').hide();
    $('.subm').click(function () {
        $('.hh').hide();
        $('.subm').hide(function () {
            $('.balance-input').hide(function () {
                $('.balance-button').show();
            })
        });
    })
});

$('.hh').click(function (e) {
    let balance = $('#bb').val();
    if (!isNaN(balance) && balance.toString().length < 10) {
        return true;
    } else {
        e.preventDefault();
        $('.balance-input').val("").css('border', '1px solid #f69494');
    }
});

$('#email').hide();
$('.add-email2').hide();
$('.add-email').click(function () {
    $("#email").show(400);
    $('.add-email2').show();
    $('.add-email').hide();
    $('.add-email2').click(function () {
        $('#email').hide(400);
        $('.add-email2').hide();
        $('.add-email').show();
    })
})
