<%-- 
    Document   : signup
    Created on : 20-Feb-2020, 20:57:35
    Author     : suhanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='defaultextend.jsp'%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            <%@include file="/mycss/index.css"%>
        </style>

        <script type="text/javascript" src="myjs/index.js"></script>        


        <title>login</title>
    </head>

    <body>
        <div id="isi">           
            <div id="box2">
                <div id="form" align="center">
                    <strong><label id="title_form">Payroll system</label></strong><br>
                    <label id="welcomeback">Please login to your account</label>
                    <br><br>
                    <form>                       
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <div id="push_text"></div>
                            </div>
                        </div>
                        <input id="user" name="user" type="text" required="" placeholder="Email">
                        <br>

                        <input id="pass" name="pass" type="password" required="" placeholder="Password" style="margin-left: 20px;">
                        <i class="fa fa-eye-slash" id="show_pass"></i>
                        <br>
                        <br>
                        <br>
                        <!--<div id="checkbox_remember">-->
                        <!--<input type="checkbox" value="remember" id="cb"><label id="remember">Remember me</label>-->
                        <!--<label id="forgot_password" onclick="forgot()">Forgot Password</label>-->
                        <!--</div>-->
                        <div id="button_depan">
                            <div id="login_button" onclick="login()">
                                Sign in
                            </div>
                            <a href="su">
                                <div id="signup">
                                    Sign up
                                </div>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
