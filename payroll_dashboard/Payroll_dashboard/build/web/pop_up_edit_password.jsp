<%-- 
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    .cd-buttons-add-category
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace-edit-contact {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-edit-contact {
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
    .cd-popup-edit-contact.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-edit-contact-container {
        position: relative;
        width: 90%;
        height: 300px;
        max-width: 500px;
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

    .cd-popup-edit-contact-container .cd-buttons-add-category:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-edit-contact-container .cd-buttons-add-category li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-edit-contact-container .cd-buttons-add-category div {
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
    .cd-popup-edit-contact-container .cd-buttons-add-category li:first-child div {
        background: #8915e1;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-edit-contact-container .cd-buttons-add-category li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-edit-contact-container .cd-buttons-add-category li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-edit-contact-container .cd-buttons-add-category li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-edit-contact-container .cd-buttons-add-category li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-edit-contact-container .cd-buttons-add-category li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-edit-contact-container .cd-popup-edit-contact-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-edit-contact-container .cd-popup-edit-contact-close::before, .cd-popup-edit-contact-container .cd-popup-edit-contact-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-edit-contact-container .cd-popup-edit-contact-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-edit-contact-container .cd-popup-edit-contact-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-edit-contact-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-edit-contact-container {
            margin: 8em auto;
        }
    }
    #label_edit_contact {
        width: 100%;
        margin: 20px;
        text-align: left;
    }
    #boxx_contact{
        display: flex;
        justify-content: center;
        align-content: center;
    }
    #box1_contact {
        cursor: pointer;
        width: 215px;
        height: 200px;
        margin-right: 5px;
        /*background-color: #68f3e9;*/
        border-radius: 5px;
    }
    #box2_contact {
        width: 215px;
        height: 200px;
        /*margin-left: 5px;*/
        /*background-color: yellow;*/
    }
    input {
        padding-left: 10px;
        border: 1px solid #CCC; 
        border-radius: 3px;
        width: 200px;
        margin-bottom: 5px;
    }
    textarea {
        padding-left: 10px;
        border-radius: 3px;
        width: 100%;
        margin-top: 10px;
        height: 115px;
    }
    #buttonon {
        color: white;
        margin-top: 10px;
        display: flex;
    }
    #btn_cancel {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_pass {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel:hover {
        background-color: #CCC;
    }
    #btn_save_pass:hover {
        background-color: #CCC;
    }
    .image-upload > input {
        visibility:hidden;
        width:0;
        height:0
    }
    #panel_img {
        margin: 15px;
        height: 300px;
        width: 430px;
        background-color: white;
    }
    #blah {
        width: 430px;;
        height: 300px;
    }
    #btn_upl {
        width: 100px;
    }
    #img_filechooser_category {
        width: 215px;
        height: 200px;
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
</style>


<script>
    jQuery(document).ready(function ($) {
        //open popup edit contact
        $('#edit_password').on('click', function (event) {
            event.preventDefault();
            $('.cd-popup-edit-contact').addClass('is-visible');
        });

        //close popup edit contact
        $('.cd-popup-edit-contact').on('click', function (event) {
            if ($(event.target).is('.cd-popup-edit-contact-close') || $(event.target).is('.cd-popup-edit-contact') || $(event.target).is('#btn_cancel')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
                $("#formho_pass")[0].reset();

            }
        });
        //close popup when clicking the esc keyboard button edit contact
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-edit-contact').removeClass('is-visible');
            }
        });
    });
</script>
<div class="cd-popup-edit-contact" role="alert">
    <div class="cd-popup-edit-contact-container">
        <label id="label_edit_contact">Change Password</label>
        <div id="boxx_contact">
            <div id="box2_contact">
                <div id="input_contact">
                    <form style="width: 250px;" id="formho_pass">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <div id="push_text"></div>
                            </div>
                        </div>
                        <br>
                        <input type="password" id="oldpass" name="oldpass" required="" placeholder="Old Password">&nbsp<i class="fa fa-eye-slash" id="show_pass1" style="opacity: 0.5; color: black;"></i>
                        <input type="password" id="newpass" name="newpass" required="" placeholder="New Password">&nbsp<i class="fa fa-eye-slash" id="show_pass2" style="opacity: 0.5; color: black;"></i>
                        <input type="password" id="cnewpass" name="cnewpass" required="" placeholder="Confirm New Password">&nbsp<i class="fa fa-eye-slash" id="show_pass3" style="opacity: 0.5; color: black;"></i>
                    </form>
                    <div id="buttonon">
                        <div id="btn_cancel">Cancel</div>
                        <div id="btn_save_pass" onclick="res_pass()">Change</div>
                    </div>
                    <div id="msgbox"></div>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-edit-contact-close img-replace-edit-contact">Close</a>
    </div>
</div>
<%--<%@include file='pop_up_provide_password.jsp'%>--%>

<script>
    $("#show_pass1").on('click', function () {
        var lihat_dong = $("#oldpass");
        var lihat_key = $("#show_pass1");
        if (lihat_dong.attr("type") === "password") {
            lihat_dong.attr("type", "text");
            lihat_key.attr("class", "fa fa-eye");
        } else {
            lihat_dong.attr("type", "password");
            lihat_key.attr("class", "fa fa-eye-slash");
        }
    });
    $("#show_pass2").on('click', function () {
        var lihat_dong = $("#newpass");
        var lihat_key = $("#show_pass2");
        if (lihat_dong.attr("type") === "password") {
            lihat_dong.attr("type", "text");
            lihat_key.attr("class", "fa fa-eye");
        } else {
            lihat_dong.attr("type", "password");
            lihat_key.attr("class", "fa fa-eye-slash");
        }
    });
    $("#show_pass3").on('click', function () {
        var lihat_dong = $("#cnewpass");
        var lihat_key = $("#show_pass3");
        if (lihat_dong.attr("type") === "password") {
            lihat_dong.attr("type", "text");
            lihat_key.attr("class", "fa fa-eye");
        } else {
            lihat_dong.attr("type", "password");
            lihat_key.attr("class", "fa fa-eye-slash");
        }
    });

    jQuery(document).ready(function ($) {
        //open popup add category
        $('#box1_contact').on('click', function (event) {
            $('#file-input-contact').trigger('click');
        });
    });

    function res_pass() {
        var pass = document.getElementById("oldpass").value;
        var npass = document.getElementById("newpass").value;
        var ncpas = document.getElementById("cnewpass").value;

        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        var push = document.getElementById("push_text");
        ;
        if (pass == "") {
            push.innerHTML = "<p>Password must be fill</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (pass != "") {
            if (npass == "") {
                push.innerHTML = "<p>New Password must be fill</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (npass != "") {
                if (ncpas == "") {
                    push.innerHTML = "<p>Confirm new Password must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (ncpas != "") {
                    if (npass != ncpas) {
                        push.innerHTML = "<p>Confirm Password Not Match</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (npass == ncpas) {
                        if (pass == npass) {
                            push.innerHTML = "<p>New Password Can't Be The Same As Old Password</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (pass != npass) {
                            var user_id = window.localStorage.getItem('user_id');
                            var company_id = window.localStorage.getItem('company_id');
                            var datjson = new Object();
                            datjson.company_id = company_id;
                            datjson.user_id = user_id;
                            datjson.oldpass = pass;
                            datjson.newpass = npass;
                            $.ajax({
                                dataType: "json",
                                url: "ChangePassword",
                                data: JSON.stringify(datjson),
                                type: 'post',
                                success: function (response) {
                                    if (response.resp_code == 0000) {
                                        alert('success Change Password');
                                    location.reload();
//                                        window.localStorage.removeItem('brand_id');
//                                        window.localStorage.removeItem('userlevel');
//                                        window.localStorage.removeItem('outlet_store_id');
//                                        window.localStorage.removeItem('user_id');
//                                        window.localStorage.removeItem('activeTab');
//                                        window.localStorage.removeItem('user_name');
//                                        window.localStorage.removeItem('outlet_store_name');
//                                        window.location.href = "ho";
                                    } else {
                                        alert('Failed Change Password');
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }
</script>