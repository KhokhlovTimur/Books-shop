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
        <input name="loginReg" class="w100" placeholder="login" required autocomplete='off'/>
        <input name="passwordReg" type="password" placeholder="Insert Password" id="password1" required/>
        <input type="password" name="passwordReg2" placeholder="Verify Password" id="password2" required/>
        <button class="form-btn sx log-in" type="button" >Log In</button>
        <button class="form-btn dx" type="submit" name="button" value="reg" id="reg">Sign Up</button>
    </form>
    <form class="signIn" action="/login" method="post">
        <h3>Welcome</br>Back !</h3>
        <p>- log in -</p>
        <input name="loginLog" placeholder="login" autocomplete='off'/>
        <input name="passwordLog" type="password" placeholder="Insert Password"/>
        <button class="form-btn sx back" type="button">Back</button>
        <button class="form-btn dx" type="submit" name="button" value="log">Log In</button>
        <button class="fb" name="button" value="without">Without authorization</button>
    </form>
</div>

<style><%@include file="/resources/css/loginPage.css"%>
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>

</body>
</html>

