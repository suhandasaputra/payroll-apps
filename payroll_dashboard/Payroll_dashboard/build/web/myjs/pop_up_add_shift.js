jQuery(document).ready(function ($) {
    //open popup provide password
    $('#add_user').on('click', function () {

        $('.cd-popup-add-user').addClass('is-visible');

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
    var shift_id = document.getElementById("shift_id").value;
    var shift_name = document.getElementById("shift_name").value;
    var start_time = document.getElementById("start_time").value;
    var end_time = document.getElementById("end_time").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (shift_id == "") {
        push.innerHTML = "<p>id shift must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (shift_id != "") {
        if (shift_name == "") {
            push.innerHTML = "<p>shift name must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (shift_name != "") {
            if (start_time == "") {
                push.innerHTML = "<p>start shift must be filled</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (start_time != "") {
                if (end_time == "") {
                    push.innerHTML = "<p>finish shift must be filled</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (end_time != "") {
                    var datjson = new Object();
                    datjson.company_id = window.localStorage.getItem('company_id');
                    datjson.user_id = window.localStorage.getItem('user_id');
                    datjson.shift_id = shift_id;
                    datjson.shift_name = shift_name;
                    datjson.start_time = start_time;
                    datjson.end_time = end_time;
                    $.ajax({
                        dataType: "json",
                        url: "AddShift",
                        data: JSON.stringify(datjson),
                        type: 'post',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                loaddata();
                                alert('success add shift');
                                $('.cd-popup-add-user').removeClass('is-visible');
                                $("#form_box4")[0].reset();
                            } else {
                                alert('Failed add shift : ' + response.resp_desc);
                            }
                        }
                    });
                }
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

