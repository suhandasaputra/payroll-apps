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
            <%@include file="/mycss/signup.css"%>
        </style>

        <script type="text/javascript" src="myjs/signup.js"></script>
        
        <title>sign up</title>
    </head>

    <body>
        <div id="isi">
            <!--            <div id="box1">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img class="d-block w-100" src="image/1_1.png" alt="First slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100" src="image/2_1.png" alt="Second slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100" src="image/3_1.png" alt="Third slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block w-100" src="image/4_1.png" alt="Fourth slide">
                                    </div>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>-->
            <div id="box2">
                <div id="form" align="center" style="">
                    <strong><label id="title_form">H R M S</label></strong><br><br>
                    <label id="welcomeback">Please complete to create your account</label>
                    <br><br>
                    <form id="form_nya" style="">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <div id="push_text"></div>
                            </div>
                        </div>
                        <select id="bidang_usaha" name="bidang_usaha" style="font-size: 13px; width: 80%">
                        </select>
                        <input type="text" required="" placeholder="Full Name" id="username" name="username">
                        <!--<input type="text" required="" placeholder="Position" id="position" name="position">-->
                        <input type="text" required="" placeholder="Number of employee" id="jumlah" name="jumlah">

                        <input type="text" required="" placeholder="Company Name" id="companyname" name="companyname">
                        <input type="text" required="" placeholder="Phone number" id="phonenumber" name="phonenumber">
                        <input type="text" required="" placeholder="Email" id="email" name="email">
                        <input type="password" required="" placeholder="Create Password" id="pass" name="pass">
                        <input type="password" required="" placeholder="Confirm Password" id="cpass" name="cpass">

                        <br>
                        <br>
                        <!--                        <div id="checkbox_agree">
                                                    <input type="checkbox" value="" id="cb"><label id="agree">I agree with term and conditions</label>
                                                </div>-->
                        <div id="signup_button" onclick="signup()">
                            Sign Up
                        </div>
                    </form>
                    <a href="ho">
                        <div id="already" align="center">
                            Already have an account? <b style="font-weight: 600">Sign in</b>
                        </div>
                    </a>
                    <!--                    <div id="footer" align="center">
                                            Term of use. Privacy Policy
                                        </div>-->
                </div>
            </div>
        </div>

    </body>
</html>

