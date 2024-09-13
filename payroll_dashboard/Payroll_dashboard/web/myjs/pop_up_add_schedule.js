jQuery(document).ready(function ($) {
    
    
    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin') {
        document.getElementById('add_schedule').style.visibility = 'hidden';
        document.getElementById('add_schedule').style.display = 'none';
    }
    
    //open popup add schedule
    $('#add_schedule').on('click', function () {

        var company_id = window.localStorage.getItem('company_id');
        var datjson = new Object();
        datjson.company_id = company_id;

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
                    $("select#schedule_employee").html(options);
                    $.ajax({
                        dataType: "json",
                        url: "OptionShift",
                        data: JSON.stringify(datjson),
                        type: 'POST',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                var ff = new Object();
                                ff = response.list;
                                var options = '<option selected disabled value="">Select Shift</option>';
                                for (var i = 0; i < ff.length; i++) {
                                    options += '<option value="' + ff[i].shift_id + '">' + ff[i].shift_name + '</option>';
                                }
                                $("select#schedule_shift").html(options);
                                $('.cd-popup-add-user').addClass('is-visible');

                            } else {
                                alert('Failed get shift : ' + response.resp_desc);
                            }
                        }
                    });

                } else {
                    alert('Failed get employee : ' + response.resp_desc);
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
    var employee_id = document.getElementById("schedule_employee").value;
    var shift_id = document.getElementById("schedule_shift").value;
    var date = document.getElementById("schedule_date").value;

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
        if (employee_id == "") {
            push.innerHTML = "<p>employee must be filled</p>";
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
                push.innerHTML = "<p>date must be filled</p>";
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
                datjson.shift_id = shift_id;
                datjson.employee_id = employee_id;
                datjson.date = date;
                $.ajax({
                    dataType: "json",
                    url: "AddSchedule",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_schedule_from').val();
                           
                            if (start === "") {
                                loaddata();
                                alert('success add schedule');
                                $('.cd-popup-add-user').removeClass('is-visible');
                              
                                $("#form_box4")[0].reset();
                                
                            } else if (start !== "") {
                                
                                loaddatabydate();
                                alert('success add schedule');
                                $('.cd-popup-add-user').removeClass('is-visible');
                               
                                $("#form_box4")[0].reset();
                              
                            }
                        } else {
                            alert('Failed add schedule : ' + response.resp_desc);
                        }
                    }
                });
            }


        }
    }
}

