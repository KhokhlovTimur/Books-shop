$('.historyhide').hide();
$('.block2').hide();
$('.history').click(function () {
    $('.history').hide();
    $('.block2').show(1500);
    $('.historyhide').show().click(function () {
        $('.block2').hide(1500);
        $('.historyhide').hide();
        $('.history').show();
    })
});

$('.balance-input').hide();
$('.subm').hide();
$('.hh').hide();
$('.balance-button').click(function (){
    $('.balance-input').show();
    $('.subm').show();
    $('.hh').show();
    $('.balance-button').hide();
    $('.subm').click(function (){
        $('.hh').hide();
        $('.subm').hide(function (){
            $('.balance-input').hide(function (){
                $('.balance-button').show();
            })
        });
    })
});

$('.hh').click(function (e){
    let balance = $('.balance-input').val();
    if(!isNaN(balance)){
        return true;
    }
    else {
        e.preventDefault();
        $('.balance-input').val("").css('border', '1px solid #f69494');
    }
});