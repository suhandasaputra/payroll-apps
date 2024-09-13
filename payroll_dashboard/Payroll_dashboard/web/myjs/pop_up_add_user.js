jQuery(document).ready(function ($) {
    //open popup provide password
    $('#add_user').on('click', function () {
        var company_id = window.localStorage.getItem('company_id');
        var datjson = new Object();
        datjson.company_id = company_id;

        $.ajax({
            dataType: "json",
            url: "OptionAccountLevel",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (response) {
                if (response.resp_code == 0000) {
                    var aa = new Object();
                    aa = response.list;
                    var options = '<option selected disabled value="">Select Account Level</option>';
                    for (var i = 0; i < aa.length; i++) {
                        options += '<option value="' + aa[i].id_level + '">' + aa[i].level_desc + '</option>';
                    }
                    $("select#account_level").html(options);
                    $.ajax({
                        dataType: "json",
                        url: "OptionEmployee",
                        data: JSON.stringify(datjson),
                        type: 'POST',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                var aa = new Object();
                                aa = response.list;
                                var options = '<option selected disabled value="">Select Employee</option>';
                                for (var i = 0; i < aa.length; i++) {
                                    options += '<option value="' + aa[i].employee_id + '">' + aa[i].employee_name + '</option>';
                                }
                                $("select#account_id").html(options);
                                $('.cd-popup-add-user').addClass('is-visible');

                            } else {
                                alert('Failed get account level : ' + response.resp_desc);
                            }
                        }
                    });

                } else {
                    alert('Failed get account level : ' + response.resp_desc);
                }
            }
        });
    });

    //close popup provide password
    $('.cd-popup-add-user').on('click', function (event) {
        if ($(event.target).is('.cd-popup-add-user-close') || $(event.target).is('.cd-popup-add-user') || $(event.target).is('#btn_cancel')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-add-user').removeClass('is-visible');
        }
    });
});


function sub_out() {
    var account_id = document.getElementById("account_id").value;
    var account_name = document.getElementById("account_name").value;
    var account_level = document.getElementById("account_level").value;
    var password = document.getElementById("password").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (account_id == "") {
        push.innerHTML = "<p>account_id must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (account_id != "") {
        if (account_name == "") {
            push.innerHTML = "<p>user name must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (account_name != "") {
            if (account_level == "") {
                push.innerHTML = "<p>user level must be filled</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (account_level != "") {
                if (password == "") {
                    push.innerHTML = "<p>password must be filled</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (password != "") {
//                            var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
//                            if (email.match(mailformat)) {
                    var datjson = new Object();
                    datjson.company_id = window.localStorage.getItem('company_id');
                    datjson.user_id = window.localStorage.getItem('user_id');
                    datjson.account_id = account_id;
                    datjson.account_name = account_name;
                    datjson.account_level = account_level;
                    datjson.password = password;
                    $.ajax({
                        dataType: "json",
                        url: "AddUser",
                        data: JSON.stringify(datjson),
                        type: 'post',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                loaddata();
                                alert('success add user');
                                $('.cd-popup-add-user').removeClass('is-visible');
                                $("#form_box4")[0].reset();
                            } else {
                                alert('Failed add user : ' + response.resp_desc);
                            }
                        }
                    });
//                            } else {
//                                push.innerHTML = "<p>Email not valid</p>";
//                                modal.style.display = "block";
//                                span.onclick = function () {
//                                    modal.style.display = "none";
//                                };
//                                window.onclick = function (event) {
//                                    if (event.target == modal) {
//                                        modal.style.display = "none";
//                                    }
//                                }
//                            }
                }
            }
        }
    }
}

