<%-- 
    Document   : header
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>

<style>
    #profile {
        color: #C780FA;
        margin-top: 5px;
        margin-left: 10px;
        cursor: pointer;
    }
    #profile_img {
        height: 30px;
        width: 30px;
        /*margin-top: 3px;*/
        /*margin-left: 7px;*/
        border-radius: 50px;
    }
    #seting {
        color: #8915e1;
        text-align: center;
        padding: 10px;
        cursor: pointer;
    }
    #seting:hover {
        background-color: #8915e1;
        color: white;
    }

    #role {
        color: #8915e1;
        text-align: center;
        padding: 10px;
        cursor: pointer;
    }
    #role:hover {
        background-color: #8915e1;
        color: white;
    }

    #logout {
        color: #8915e1;
        padding: 10px;
        text-align: center;
        cursor: pointer;
    }
    #logout:hover {
        background-color: red;
        opacity: 0.5;
        color: white;
    }

    .cd-buttons
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup {
        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        width: 100%;
        background-color: rgba(94, 110, 141, 0.9);
        opacity: 0;
        visibility: hidden;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        transition: opacity 0.3s 0s, visibility 0s 0.3s;
    }
    .cd-popup.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-container {
        position: relative;
        width: 90%;
        max-width: 400px;
        margin: 4em auto;
        background: #FFF;
        border-radius: .25em .25em .4em .4em;
        text-align: center;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        -webkit-transform: translateY(-40px);
        -moz-transform: translateY(-40px);
        -ms-transform: translateY(-40px);
        -o-transform: translateY(-40px);
        transform: translateY(-40px);
        /* Force Hardware Acceleration in WebKit */
        -webkit-backface-visibility: hidden;
        -webkit-transition-property: -webkit-transform;
        -moz-transition-property: -moz-transform;
        transition-property: transform;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        transition-duration: 0.3s;
    }
    .cd-popup-container p {
        padding: 3em 1em;
    }
    .cd-popup-container .cd-buttons:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-container .cd-buttons li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-container .cd-buttons div {
        cursor: pointer;
        display: block;
        height: 60px;
        line-height: 60px;
        text-transform: uppercase;
        color: #FFF;
        -webkit-transition: background-color 0.2s;
        -moz-transition: background-color 0.2s;
        transition: background-color 0.2s;
    }
    .cd-popup-container .cd-buttons li:first-child div {
        background: #8915e1;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-container .cd-buttons li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-container .cd-buttons li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-container .cd-buttons li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-container .cd-buttons li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-container .cd-buttons li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-container .cd-popup-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-container .cd-popup-close::before, .cd-popup-container .cd-popup-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-container .cd-popup-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-container .cd-popup-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-container {
            margin: 8em auto;
        }
    }
    #waktu {
        font-size: 8px;
    }
    #user_log {
        font-size: 10px;
        color: #C780FA;
    }
    #info_log {
        font-size: 12px;
        color: #C780FA;
    }
    #useryabes {
        height: 300px;
        overflow-x: hidden;
    }
    #accset {
        color: #8915e1;
    }
    #accset:hover {
        color: white;
    }





    /* The switch - the box around the slider */
    .switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 34px;
    }

    /* Hide default HTML checkbox */
    .switch input {
        opacity: 0;
        width: 0;
        height: 0;
    }

    /* The slider */
    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #ccc;
        -webkit-transition: .4s;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 26px;
        width: 26px;
        left: 4px;
        bottom: 4px;
        background-color: white;
        -webkit-transition: .4s;
        transition: .4s;
    }

    input:checked + .slider {
        background-color: #2196F3;
    }

    input:focus + .slider {
        box-shadow: 0 0 1px #2196F3;
    }

    input:checked + .slider:before {
        -webkit-transform: translateX(26px);
        -ms-transform: translateX(26px);
        transform: translateX(26px);
    }

    /* Rounded sliders */
    .slider.round {
        border-radius: 34px;
    }

    .slider.round:before {
        border-radius: 50%;
    }
</style>


<script>
    jQuery(document).ready(function ($) {

//        var brand_id = window.localStorage.getItem('brand_id');
        var account_id = window.localStorage.getItem('account_id');
        var account_level = window.localStorage.getItem('account_level');
        var account_name = window.localStorage.getItem('account_name');
        var level_desc = window.localStorage.getItem('level_desc');

        document.getElementById("role").innerHTML = 'Role : ' + level_desc;
        document.getElementById("name_of_user").innerHTML = account_name;

        //open popup
        $('.cd-popup-trigger').on('click', function (event) {
            event.preventDefault();
            $('.cd-popup').addClass('is-visible');
        });

        //close popup
        $('.cd-popup').on('click', function (event) {
            if ($(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') || $(event.target).is('#no')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //close popup when clicking the esc keyboard button
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup').removeClass('is-visible');
            }
        });
        //action logout
        $('#yes').on('click', function (event) {
            window.localStorage.removeItem('user_id');
            window.localStorage.removeItem('company_id');
            window.localStorage.removeItem('account_name');
            window.localStorage.removeItem('side');
            window.localStorage.removeItem('account_level');
            window.localStorage.removeItem('level_desc');

            window.location.href = "ho";
        });

//        $.getJSON('usr_log', {
//            brand_id: brand_id
//        }, function (data) {
//            var useryabes = [];
//            var useryabessum = data.length;
//            var useryabessum1 = "You have " + data.length + " notification";
//            for (var i = 0; i < data.length; i++) {
//                useryabes += '<a href="#" class="dropdown-item" id="user_log"><i class="fas fa-envelope mr-2"></i>' + data[i].userid +
//                        '<span class="float-right text-muted" id="waktu">' + data[i].process_time + '</span><p id="info_log">' +
//                        data[i].detail_info + '</p></a><div class="dropdown-divider"></div>';
//            }
//            $("#useryabes").html(useryabes);
//            $("span#useryabessum").html(useryabessum);
//            $("span#useryabessum1").html(useryabessum1);
//        });
    });


</script>



<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
        </li>
    </ul>
    <!-- SEARCH FORM -->
    <!--    <form class="form-inline ml-3">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-navbarr" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
        </form>-->

    <!-- Rounded switch -->
    <label class="switch">
        <input id="cekk" type="checkbox">
        <span class="slider round"></span>
    </label>

    <script>

        function toggleDarkMode() {
            var checkBox = document.getElementById("cekk");
            var isChecked = checkBox.checked;
            if (isChecked) {
//                console.log("nyala");
                DarkReader.setFetchMethod(window.fetch)
                DarkReader.enable({
                    brightness: 100,
                    contrast: 100,

                });
            } else {
//                console.log("mati");
                DarkReader.disable();
            }
            // Save the toggle state to localStorage
            localStorage.setItem("dark-mode", isChecked);
        }

        // Load the toggle state from localStorage on page load
        var darkMode = localStorage.getItem("dark-mode");
        if (darkMode === "true") {
            var checkBox = document.getElementById("cekk");
            checkBox.checked = true;
            toggleDarkMode();
        }

        // Add a change event listener to the checkbox
        var checkBox = document.getElementById("cekk");
        checkBox.addEventListener("change", toggleDarkMode);


    </script>


    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <!-- Notifications Dropdown Menu -->
        <li class="nav-item dropdown" id="lonceng">
            <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="far fa-bell"></i>
                <span class="badge badge-warning navbar-badge" id="useryabessum"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <span class="dropdown-item dropdown-header" id="useryabessum1"></span>
                <div class="dropdown-divider"></div>
                <div id="useryabes"></div>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
            </div>
        </li>


        <!--start profile-->
        <li>
            <div class="dropdown">
                <div id="profile" data-toggle="dropdown">
                    <span class="hidden-xs" id="name_of_user"></span>
                    <img src="image/account.png" alt="User Image" id="profile_img"/>
                </div>
                <ul class="dropdown-menu dropdown-menu-right">

                    <li id="role"></li>
                    <li id="seting"><a href="acc" id="accset">Account Setting</a></li>
                    <li id="logout" class="cd-popup-trigger">Logout</li>
                </ul>
            </div>
        </li>
        <!--end profile-->
    </ul>
    <!--logout popup-->
    <div class="cd-popup" role="alert">
        <div class="cd-popup-container">
            <p>Are you sure want to logout?</p>
            <ul class="cd-buttons">
                <li><div id="yes">Yes</div></li>
                <li><div id="no">No</div></li>
            </ul>
            <a href="#0" class="cd-popup-close img-replace">Close</a>
        </div>
    </div>
    <!--logout popup end-->
</nav>

