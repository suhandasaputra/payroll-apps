//open pop up detail request absence
$('#table_attendance').on('click', 'td', function () {
    
    var $row = $(this).closest("tr");
    var employee_id = $row.find(".employee_id").text();
    var reference = $row.find(".reference").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.reference = reference;
    datjson.employee_id = employee_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailAttendance",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("descriptionin").value = resp.descriptionin;
                document.getElementById("shift").value = resp.shift;
                document.getElementById("early_out").value = resp.early_out;
                document.getElementById("reference").value = resp.reference;
                document.getElementById("schedule_working_hour").value = resp.schedule_working_hour;

                document.getElementById("locationout").value = resp.locationout;
                document.getElementById("absence_type").value = resp.absence_type;
                document.getElementById("checkin").value = resp.checkin;
                document.getElementById("date_requestout").value = resp.date_requestout;
                document.getElementById("overtime_duration_before").value = resp.overtime_duration_before;

                document.getElementById("checkout").value = resp.checkout;
                document.getElementById("late_in").value = resp.late_in;
                document.getElementById("schedule_out").value = resp.schedule_out;
                document.getElementById("schedule_in").value = resp.schedule_in;
                document.getElementById("account_id").value = resp.account_id;

                document.getElementById("overtime_duration_after").value = resp.overtime_duration_after;
                document.getElementById("employee_id").value = resp.employee_id;
                document.getElementById("actual_working_hour").value = resp.actual_working_hour;
                document.getElementById("descriptionout").value = resp.descriptionout;
                document.getElementById("real_working_hour").value = resp.real_working_hour;

                document.getElementById("date_requestin").value = resp.date_requestin;
                document.getElementById("locationin").value = resp.locationin;
                document.getElementById("date_absensi").value = resp.date_absensi;


                var level_desc = window.localStorage.getItem('level_desc');
                if (level_desc !== 'head') {


                    $('.cd-popup-edit-user').addClass('is-visible');

                } else if (level_desc == 'head') {
                    $('.cd-popup-edit-user').addClass('is-visible');
                }

            } else {
                alert('Failed get detail : ' + resp.resp_desc);
            }
        }
    });

});

jQuery(document).ready(function ($) {
    //close popup provide password
    $('.cd-popup-edit-user').on('click', function (event) {
        if ($(event.target).is('.cd-popup-edit-user-close') || $(event.target).is('.cd-popup-edit-user') || $(event.target).is('#btn_edit_submit_user')) {
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








