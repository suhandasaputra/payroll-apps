//open popup provide password
$('#table_shift').on('click', 'td', function () {
    var $row = $(this).closest("tr");
    var shift_id = $row.find(".shift_id").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.shift_id = shift_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailShift",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("shift_id_edit").value = resp.shift_id;
                document.getElementById("shift_name_edit").value = resp.shift_name;
                document.getElementById("start_time_edit").value = resp.start_time;
                document.getElementById("end_time_edit").value = resp.end_time;
                         
                $('.cd-popup-edit-user').addClass('is-visible');

            } else {
                alert('Failed get Shift detail : ' + response.resp_desc);
            }
        }
    });
});

jQuery(document).ready(function ($) {
    //close popup provide password
    $('.cd-popup-edit-user').on('click', function (event) {
        if ($(event.target).is('.cd-popup-edit-user-close') || $(event.target).is('.cd-popup-edit-user')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-edit-user').removeClass('is-visible');
        }
    });
});

function sub_edt_shift() {

    var shift_id = document.getElementById("shift_id_edit").value;
    var shift_name = document.getElementById("shift_name_edit").value;
    var start_time = document.getElementById("start_time_edit").value;
    var end_time = document.getElementById("end_time_edit").value;

    var modal = document.getElementById("myModal_edit_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_user");


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
                        url: "EditShift",
                        data: JSON.stringify(datjson),
                        type: 'post',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                loaddata();
                                alert('success edit shift');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box4")[0].reset();
                            } else {
                                alert('Failed edit shift : ' + response.resp_desc);
                            }
                        }
                    });
                }
            }
        }
    }
}

function sub_del_shift() {
    var shift_id = document.getElementById("shift_id_edit").value;


    if (shift_id == "") {
        push.innerHTML = "<p>Id Shift must be fill</p>";
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
        var datjson = new Object();
        datjson.shift_id = shift_id;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "DeleteShift",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Delete Shift');
                    location.reload();
                } else {
                    alert('Failed Delete Shift');
                }
            }
        });
    }
}