<%-- 
    Document   : forgot_password
    Created on : 20-Feb-2020, 22:15:38
    Author     : matajari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='defaultextend.jsp'%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>forgot_password</title>
        <style>
            #isi {
                align-content: center;
                height: 605px;
                display: flex;
                justify-content: center;
            }
            #box1 {
                background-color: white;
                width: 40%;
                height: 100%;
            }

            #box2 {
                background-color: white;
                width: 40%;
                height: 100%;
            }
            #form {
                /*border: 1px solid #ccc;*/
                padding-top: 180px;
                font-family: Open Sans,sans-serif;
                background-color: white;
                margin: 250px auto;
                margin-top: 100px;
                padding: 10px;
                width: 330px;
                text-align: center;
                height: -webkit-fill-available;
            }
            #title_form {
                color: #8915e1;
                font-family: Open Sans,sans-serif;
                letter-spacing: 6px;
                font-size: 25px;
                font-weight: 600;
            }
            #welcomeback {
                font-family: Open Sans,sans-serif;
                letter-spacing: 0;
                color: #4D4D4D;
                /*opacity: 0.5;*/
                font-weight: 100;
                font-size: 12px;
                display: block;
                margin-top: 10px;
            }
            input[type="email"]{
                margin-top: 10px;
                width: 85%;
                letter-spacing: 0;
                color: #4D4D4D;
                /*opacity: 0.5;*/
                font-weight: 100;
                font-size: 11px;
                padding-top: 15px;
                border: 0;
                outline: 0;
                background: transparent;
                border-bottom: 1px solid #4d4d4d57;
            }



            #login_button {
                width: 120px;
                height: 30px;
                background: #8915e1;
                border-radius: 4px;
                /*opacity: 1;*/
                font-weight: 100;
                font-size: 12px;
                color: white;
                padding-top: 7px;
                cursor:pointer;
                display: inline-block;
            }
            #login_button:hover {
                cursor:pointer;
                background: #8915e1;
            }

            #footer {
                font-family: Open Sans,sans-serif;
                letter-spacing: 0;
                color: #8915e1;
                font-weight: 100;
                font-size: 9px;
                position: absolute;
                bottom: 10px;
                margin-left: 80px;
                cursor: pointer;
            }
            #all_dot {
                margin-top: -30px;
            }






            * {box-sizing: border-box}
            .mySlides {display: none}
            img {vertical-align: middle}

            /* Slideshow container */
            .slideshow-container {
                max-width: 1000px;
                position: relative;
                margin: auto;
                /*overflow:hidden;*/
            }

            /* Next & previous buttons */
            .prev, .next {
                cursor: pointer;
                position: absolute;
                top: 50%;
                width: auto;
                padding: 16px;
                margin-top: -22px;
                color: white;
                font-weight: bold;
                font-size: 18px;
                transition: 0.6s ease;
                border-radius: 0 3px 3px 0;
                user-select: none;
            }

            /* Position the "next button" to the right */
            .next {
                right: 0;
                border-radius: 3px 0 0 3px;
            }

            /* On hover, add a black background color with a little bit see-through */
            .prev:hover, .next:hover {
                background-color: rgba(0,0,0,0.8);
            }
            /* The dots/bullets/indicators */
            .dot {
                cursor: pointer;
                height: 15px;
                width: 15px;
                margin: 0 2px;
                background-color: #a8b5cf;
                border-radius: 50%;
                display: inline-block;
                transition: background-color 0.6s ease;
            }

            .active, .dot:hover {
                background-color: white;
            }

            /* Fading animation */
            .fade {
                -webkit-animation-name: fade;
                -webkit-animation-duration: 1.5s;
                animation-name: fade;
                animation-duration: 1.5s;
            }

            @-webkit-keyframes fade {
                from {opacity: .4} 
                to {opacity: 1}
            }

            @keyframes fade {
                from {opacity: .4} 
                to {opacity: 1}
            }



            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */

            }

            /* Modal Content */
            .modal-content {

                display: inline-block;
                background-color: #8915e1;
                margin-left: 140px;
                margin-top: 80px;
                position: fixed;
                padding: 20px;
                border: 1px solid white;
                width: 20%;
                height: 5%;
                padding-top: 4px;
                padding-left: -70px;
                font-family: Open Sans,sans-serif;
                letter-spacing: 0;
                color: white;
                font-weight: 100;
                font-size: 14px;    
            }

            /* The Close Button */
            .close {
                padding-top: -10px;
                color: white;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }


            /*----------------------------------------------------------------------------*/            


            /* On smaller screens, decrease text size */
            @media only screen and (max-width: 400px) {

                #isi {
                    align-content: center;
                    height: -webkit-fill-available;
                    display: flex;
                    justify-content: center;
                }
                #box1 {
                    display: none
/*                    background-color: white;
                    width: 40%;
                    height: 100%;*/
                }

                #box2 {
                    background-color: white;
                    width: -webkit-fill-available;
                    height: 100%;
                }
                #form {
                    /*border: 1px solid #ccc;*/
                    padding-top: 180px;
                    font-family: Open Sans,sans-serif;
                    background-color: white;
                    margin: 250px auto;
                    margin-top: 100px;
                    padding: 10px;
                    width: 330px;
                    text-align: center;
                    height: -webkit-fill-available;
                }
                #title_form {
                    color: #8915e1;
                    font-family: Open Sans,sans-serif;
                    letter-spacing: 6px;
                    font-size: 25px;
                    font-weight: 600;
                }
                #welcomeback {
                    font-family: Open Sans,sans-serif;
                    letter-spacing: 0;
                    color: #4D4D4D;
                    /*opacity: 0.5;*/
                    font-weight: 100;
                    font-size: 12px;
                    display: block;
                    margin-top: 10px;
                }
                input[type="email"]{
                    margin-top: 10px;
                    width: 85%;
                    letter-spacing: 0;
                    color: #4D4D4D;
                    /*opacity: 0.5;*/
                    font-weight: 100;
                    font-size: 11px;
                    padding-top: 15px;
                    border: 0;
                    outline: 0;
                    background: transparent;
                    border-bottom: 1px solid #4d4d4d57;
                }



                #login_button {
                    width: 120px;
                    height: 30px;
                    background: #8915e1;
                    border-radius: 4px;
                    /*opacity: 1;*/
                    font-weight: 100;
                    font-size: 12px;
                    color: white;
                    padding-top: 7px;
                    cursor:pointer;
                    display: inline-block;
                }
                #login_button:hover {
                    cursor:pointer;
                    background: #8915e1;
                }

                #footer {
                    font-family: Open Sans,sans-serif;
                    letter-spacing: 0;
                    color: #8915e1;
                    font-weight: 100;
                    font-size: 9px;
                    position: absolute;
                    bottom: 150px;
                    margin-left: 100px;
                    cursor: pointer;
                }
                /* The Modal (background) */
                .modal {
                    display: none;
                    position: fixed;
                    z-index: 1;
                    padding-top: 100px;
                    left: 0;
                    top: 0;
                    width: -webkit-fill-available; 
                    height: -webkit-fill-available; 
                    overflow: auto; 
                    background-color: rgb(0,0,0);
                    background-color: rgba(0,0,0,0.4);
    
                }
    
    
                .modal-content {
                    right: 60px;
                    display: inline-block;
                    background-color: #8915e1;
                    margin-left: 0px;
                    margin-top: 80px;
                    position: fixed;
                    padding: 0px;
                    border: 1px solid white;
                    width: 230px;
                    height: 5%;
                    padding-top: 4px;
                    padding-left: 0px;
                    font-family: Open Sans,sans-serif;
                    letter-spacing: 0;
                    color: white;
                    font-weight: 100;
                    font-size: 14px;    
                }
    
    
                .close {
                    padding-top: -10px;
                    color: white;
                    float: right;
                    font-size: 28px;
                    font-weight: bold;
                }
    
                .close:hover,
                .close:focus {
                    color: #000;
                    text-decoration: none;
                    cursor: pointer;
                }
            }
        </style>
    </head>

    <body>
        <div id="isi">
            <div id="box1">
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
            </div>
            <div id="box2">
                <div id="form" align="center">
                    <strong><label id="title_form">RIDAPOS</label></strong><br>
                    <label id="welcomeback">Enter your email and we send you a password reset link</label>
                    <br><br>
                    <form>
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <div id="push_text"></div>
                            </div>
                        </div>
                        <input type="email" required="" placeholder="Email" id="reset_pass">
                        <br>
                        <br>
                        <br>
                        <div id="login_button" onclick="reset_bro()">
                            Send Request
                        </div>
                    </form>
                    <div id="footer" align="center">
                        Term of use. Privacy Policy
                    </div>
                </div>
            </div>
        </div>

        <script>
            function reset_bro() {
                var reset_pass = document.getElementById("reset_pass").value;

                var modal = document.getElementById("myModal");
                var span = document.getElementsByClassName("close")[0];
                var push = document.getElementById("push_text");
                ;
                if (reset_pass == "") {
                    push.innerHTML = "<p>Email / Username must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (reset_pass != "") {
                    var datjson = new Object();
                    datjson.email_res = reset_pass;

                    $.ajax({
                        contentType: 'application/json',
                        dataType: "json",
                        url: "respas",
                        data: JSON.stringify(datjson),
                        type: 'post',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                alert('your password has been reset, please check your email and try to login');
                                window.location.href = "ho";
                            } else {
                                alert('failed reset password, ' + response.resp_desc);
                            }
                        }
                    });
                }
            }

        </script>
    </body>
</html>