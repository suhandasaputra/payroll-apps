<%-- 
    Document   : verification
    Created on : 20-Feb-2020, 23:26:32
    Author     : matajari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>verification</title>
        <style>
            body{
               zoom: 80%;
            }
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
                opacity: 0.5;
                font-weight: 100;
                font-size: 12px;
                display: block;
                margin-top: 10px;
            }
            input[type="password"]{
                margin-top: 10px;
                width: 85%;
                letter-spacing: 0;
                color: #4D4D4D;
                opacity: 0.5;
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
                opacity: 1;
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

            /* On smaller screens, decrease text size */
            @media only screen and (max-width: 300px) {
                .prev, .next,.text {font-size: 11px}
            }
        </style>
    </head>

    <body>
        <div id="isi">
            <div id="box1">
                <div class="slideshow-container">

                    <div class="mySlides fade">
                        <img src="image/1_1.png" style="width:100%; height: 100%;">
                    </div>

                    <div class="mySlides fade">
                        <img src="image/2_1.png" style="width:100%; height: 100%;">
                    </div>

                    <div class="mySlides fade">
                        <img src="image/3_1.png" style="width:100%; height: 100%;">
                    </div>
                    <div class="mySlides fade">
                        <img src="image/4_1.png" style="width:100%; height: 100%;">
                    </div>

                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>
                    <div style="text-align:center" id="all_dot">
                        <span class="dot" onclick="currentSlide(1)"></span> 
                        <span class="dot" onclick="currentSlide(2)"></span> 
                        <span class="dot" onclick="currentSlide(3)"></span> 
                        <span class="dot" onclick="currentSlide(4)"></span>
                    </div>
                </div>
                <!--<br>-->


            </div>
            <div id="box2">
                <div id="form" align="center">
                    <strong><label id="title_form">RIDAPOS</label></strong><br>
                    <label id="welcomeback">Type verification code from email we sent</label>
                    <br><br>
                    <form id="form_nya">
                        <input type="password" required="" placeholder="Verification code">
                        <br>
                        <br>
                        <br>
                        <div id="login_button">
                            Submit
                        </div>
                    </form>
                    <div id="footer" align="center">
                        Term of use. Privacy Policy
                    </div>
                </div>
            </div>
        </div>

        <script>
            var slideIndex = 1;
            showSlides(slideIndex);

            function plusSlides(n) {
                showSlides(slideIndex += n);
            }

            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                var i;
                var slides = document.getElementsByClassName("mySlides");
                var dots = document.getElementsByClassName("dot");
                if (n > slides.length) {
                    slideIndex = 1
                }
                if (n < 1) {
                    slideIndex = slides.length
                }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";
            }
        </script>
    </body>
</html>