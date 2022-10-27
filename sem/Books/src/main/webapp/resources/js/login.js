$(".log-in").click(function () {
    $('.signIn').addClass("active-dx");
    $('.signUp').addClass("inactive-sx");
    $('.signUp').removeClass("active-sx");
    $('.signIn').removeClass("inactive-dx");
});

$(".back").click(function () {
    $(".signUp").addClass("active-sx");
    $(".signIn").addClass("inactive-dx");
    $(".signIn").removeClass("active-dx");
    $(".signUp").removeClass("inactive-sx");
});

$('#reg').click(function (e) {
    let psw1 = $('#password1').val();
    let psw2= $('#password2').val();
    if (psw1 === psw2 && psw2.length > 0) {
        return true;
    } else {
        e.preventDefault();
        $('#password2').val("").css('border-bottom', '3px solid orangered');
    }
});