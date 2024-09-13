
var level_desc = window.localStorage.getItem('level_desc');
if (level_desc !== 'admin') {
/// can't open popup edit schedule
} else if (level_desc === 'admin') {
//open popup edit schedule
    $('#table_schedule').on('click', 'td', function () {
        var $row = $(this).closest("tr");
        var employee_id = $row.find(".employee_id").text();
        var date = $row.find(".date").text();
        var company_id = window.localStorage.getItem('company_id');

        var datjson = new Object();
        datjson.employee_id = employee_id;
        datjson.date = date;
        datjson.company_id = company_id;
        $.ajax({
            dataType: "json",
            url: "GetDetailSchedule",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (resp) {
                if (resp.resp_code == 0000) {
                    document.getElementById("schedule_date_edit").value = resp.date;

                    $.ajax({
                        dataType: "json",
                        url: "OptionEmployee",
                        data: JSON.stringify(datjson),
                        type: 'POST',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                var aa = new Object();
                                aa = response.list;
                                var options = '<option selected disabled value="' + resp.employee_id + '">' + resp.employee_name + '</option>';
                                for (var i = 0; i < aa.length; i++) {
                                    options += '<option value="' + aa[i].employee_id + '">' + aa[i].employee_name + '</option>';
                                }
                                $("select#schedule_employee_edit").html(options);
                                $.ajax({
                                    dataType: "json",
                                    url: "OptionShift",
                                    data: JSON.stringify(datjson),
                                    type: 'POST',
                                    success: function (response) {
                                        if (response.resp_code == 0000) {
                                            var ff = new Object();
                                            ff = response.list;
                                            var options = '<option selected disabled value="' + resp.shift_id + '">' + resp.shift_name + '</option>';
                                            for (var i = 0; i < ff.length; i++) {
                                                options += '<option value="' + ff[i].shift_id + '">' + ff[i].shift_name + '</option>';
                                            }
                                            $("select#schedule_shift_edit").html(options);
                                            $('.cd-popup-edit-user').addClass('is-visible');

                                        } else {
                                            alert('Failed get majority : ' + response.resp_desc);
                                        }
                                    }
                                });

                            } else {
                                alert('Failed get religion : ' + response.resp_desc);
                            }
                        }
                    });

                } else {
                    alert('Failed get Account Level : ' + response.resp_desc);
                }
            }
        });
    });
}

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



function sub_edt_sched() {
    var employee_id = document.getElementById("schedule_employee_edit").value;
    var shift_id = document.getElementById("schedule_shift_edit").value;
    var date = document.getElementById("schedule_date_edit").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (employee_id == "") {
        push.innerHTML = "<p>email must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id != "") {
        if (shift_id == "") {
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
        } else if (shift_id != "") {
            if (date == "") {
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
            } else if (date != "") {

                var datjson = new Object();
                datjson.company_id = window.localStorage.getItem('company_id');
                datjson.user_id = window.localStorage.getItem('user_id');
                datjson.employee_id = employee_id;
                datjson.shift_id = shift_id;
                datjson.date = date;
                $.ajax({
                    dataType: "json",
                    url: "EditSchedule",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_schedule_from').val();
                            if (start !== "") {
                                loaddatabydate();
                                alert('success edit schedule');
                                $('.cd-popup-edit-user').removeClass('is-visible');

                                $("#form_box4")[0].reset();

                            } else if (start === "") {
                                loaddata();
                                alert('success edit schedule');
                                $('.cd-popup-edit-user').removeClass('is-visible');

                                $("#form_box4")[0].reset();

                            }
                        } else {
                            alert('Failed edit schedule : ' + response.resp_desc);
                        }
                    }
                });

            }
        }
    }



}

function sub_del_sched() {
    var employee_id = document.getElementById("schedule_employee_edit").value;
    var date = document.getElementById("schedule_date_edit").value;

    if (employee_id == "") {
        push.innerHTML = "<p>Outlet name must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id != "") {
        if (date == "") {
            push.innerHTML = "<p>Outlet name must be fill</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (date != "") {
            var datjson = new Object();
            datjson.employee_id = employee_id;
            datjson.date = date;
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            $.ajax({
                dataType: "json",
                url: "DeleteSchedule",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        alert('Success Delete Schedule');
                        location.reload();
                    } else {
                        alert('Failed Delete Schedule');
                    }
                }
            });
        }
    }
}