<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

</head>
<body>
<div class="container">
    <form class="signUp" action="/login" method="post">
        <h3>Create Account</h3>
        <p>Just enter your email address</br>
            and your password for join.
        </p>
        <input name="loginReg" class="w100" placeholder="login" reqired autocomplete='off'/>
        <input name="passwordReg" type="password" placeholder="Insert Password" reqired/>
        <input type="password" name="passwordReg2" placeholder="Verify Password" reqired/>
        <button class="form-btn sx log-in" type="button">Log In</button>
        <button class="form-btn dx" type="submit" name="button" value="reg">Sign Up</button>
    </form>
    <form class="signIn" action="/login" method="post">
        <h3>Welcome</br>Back !</h3>
        <button class="fb" type="button">Log In With Facebook</button>
        <p>- or -</p>
        <input name="loginLog" placeholder="login" autocomplete='off' reqired/>
        <input name="passwordLog" type="password" placeholder="Insert Password" reqired/>
        <button class="form-btn sx back" type="button">Back</button>
        <button class="form-btn dx" type="submit" name="button" value="log">Log In</button>
        <button class="form-btn" name="button" value="without">Without authorization</button>
    </form>
</div>
<style><%@include file="/css/loginPage.css"%>
    <%@include file="/css/menu.css" %>
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
</body>
</html>
<script>
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

</script>
