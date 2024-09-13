jQuery(document).ready(function ($) {

    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin') {
        document.getElementById('add_user').style.visibility = 'hidden';
        document.getElementById('add_user').style.display = 'none';
    }

    //open popup add timeoff
    $('#add_user').on('click', function () {
     
        $('.cd-popup-add-status').addClass('is-visible');
    });

    //close popup add timeoff
    $('.cd-popup-add-status').on('click', function (event) {
        if ($(event.target).is('.cd-popup-add-status-close') || $(event.target).is('.cd-popup-add-status') || $(event.target).is('#btn_cancel')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button add timeoff
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-add-status').removeClass('is-visible');
        }
    });
});


function sub_out() {
    var timeoff_code = document.getElementById("timeoff_code").value;
    var timeoff_desc = document.getElementById("timeoff_desc").value;
    var absence_type = document.getElementById("absence_type").value;
    var bucket_cuti = document.getElementById("timeoff_total_day").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


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
                    url: "AddTimeoff",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            loaddata();
                            alert('success add timeoff');
                            $('.cd-popup-add-status').removeClass('is-visible');
                            $("#form_box4")[0].reset();
                        } else {
                            alert('Failed add timeoff : ' + response.resp_desc);
                        }
                    }
                });

            }

        }
    }
}

function showTimeoffFields() {
    var absence_type = document.getElementById("absence_type");
    var label_timeoff_total_day = document.getElementById("label_timeoff_total_day");
    var timeoff_total_day = document.getElementById("timeoff_total_day");
    if (absence_type.value === "CUT") {
        label_timeoff_total_day.style.display = "block";
        timeoff_total_day.style.display = "block";
    } else {
        label_timeoff_total_day.style.display = "none";
        timeoff_total_day.style.display = "none";
    }
}


