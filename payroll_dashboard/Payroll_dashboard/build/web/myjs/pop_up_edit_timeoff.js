
var level_desc = window.localStorage.getItem('level_desc');
if (level_desc !== 'admin') {
/// can't open popup edit timeoff
} else if (level_desc === 'admin') {
//open popup edit timeoff
    $('#table_timeoff').on('click', 'td', function () {
        var $row = $(this).closest("tr");
        var timeoff_code = $row.find(".timeoff_code").text();
        var company_id = localStorage.getItem('company_id');

        var datjson = new Object();
        datjson.company_id = company_id;
        datjson.timeoff_code = timeoff_code;
        $.ajax({
            dataType: "json",
            url: "GetDetailTimeoff",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (resp) {
                if (resp.resp_code == 0000) {
                    document.getElementById("timeoff_code_edit").value = resp.timeoff_code;
                    document.getElementById("timeoff_desc_edit").value = resp.timeoff_desc;
                    document.getElementById("timeoff_total_day_edit").value = resp.bucket_cuti;

                    var ff = new Object();
                    ff = resp.list;
                    var options = '<option selected disabled value="' + resp.absence_type + '">' + resp.absence_type + '</option>';
                    options += '<option value="SKT">SKT</option>';
                    options += '<option value="IZN">IZN</option>';
                    options += '<option value="CUT">CUT</option>';
                    $("select#absence_type_edit").html(options);

                    var absence_type = resp.absence_type;
                    var label_timeoff_total_day = document.getElementById("label_timeoff_total_day_edit");
                    var timeoff_total_day = document.getElementById("timeoff_total_day_edit");
                    
                    if (absence_type === "CUT") {
                        label_timeoff_total_day.style.display = "block";
                        timeoff_total_day.style.display = "block";
                        $('.cd-popup-edit-status').addClass('is-visible');
                    } else {
                        label_timeoff_total_day.style.display = "none";
                        timeoff_total_day.style.display = "none";
                        $('.cd-popup-edit-status').addClass('is-visible');
                    }
                    
                } else {
                    alert('Failed get timeoff detail : ' + resp.resp_desc);
                }
            }
        });
    });
}

jQuery(document).ready(function ($) {
    //close popup provide password
    $('.cd-popup-edit-status').on('click', function (event) {
        if ($(event.target).is('.cd-popup-edit-status-close') || $(event.target).is('.cd-popup-edit-status')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-edit-status').removeClass('is-visible');
        }
    });
});

function sub_edt_status() {

    var timeoff_code = document.getElementById("timeoff_code_edit").value;
    var timeoff_desc = document.getElementById("timeoff_desc_edit").value;
    var absence_type = document.getElementById("absence_type_edit").value;
    var bucket_cuti = document.getElementById("timeoff_total_day_edit").value;

    var modal = document.getElementById("myModal_edit_status");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_status");


    if (timeoff_code == "") {
        push.innerHTML = "<p>timeoff_code must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (timeoff_code != "") {
        if (timeoff_desc == "") {
            push.innerHTML = "<p>timeoff_desc must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (timeoff_desc != "") {
            if (absence_type == "") {
                push.innerHTML = "<p>timeoff_desc must be filled</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (absence_type != "") {

                var datjson = new Object();
                datjson.company_id = window.localStorage.getItem('company_id');
                datjson.user_id = window.localStorage.getItem('user_id');
                datjson.timeoff_code = timeoff_code;
                datjson.timeoff_desc = timeoff_desc;
                datjson.absence_type = absence_type;
                datjson.bucket_cuti = bucket_cuti;

                $.ajax({
                    dataType: "json",
                    url: "EditTimeoff",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            loaddata();
                            alert('success edit timeoff');
                            $('.cd-popup-edit-status').removeClass('is-visible');
                            $("#form_box4")[0].reset();
                        } else {
                            alert('Failed edit timeoff : ' + response.resp_desc);
                        }
                    }
                });

            }

        }
    }
}

function sub_del_status() {
    var timeoff_code = document.getElementById("timeoff_code_edit").value;


    if (timeoff_code == "") {
        push.innerHTML = "<p>timeoff_code must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (timeoff_code != "") {
        var datjson = new Object();
        datjson.timeoff_code = timeoff_code;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "DeleteTimeoff",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Delete timeoff');
                    location.reload();
                } else {
                    alert('Failed Delete timeoff');
                }
            }
        });
    }
}