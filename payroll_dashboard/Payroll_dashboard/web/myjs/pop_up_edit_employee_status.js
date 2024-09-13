//open popup provide password
$('#table_employee_status').on('click', 'td', function () {
    var $row = $(this).closest("tr");
    var status_id = $row.find(".status_id").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.status_id = status_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailEmployeeStatus",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("status_id_edit").value = resp.status_id;
                document.getElementById("status_desc_edit").value = resp.status_desc;

                $('.cd-popup-edit-status').addClass('is-visible');

            } else {
                alert('Failed get status detail : ' + response.resp_desc);
            }
        }
    });
});

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

    var status_id = document.getElementById("status_id_edit").value;
    var status_desc = document.getElementById("status_desc_edit").value;

    var modal = document.getElementById("myModal_edit_status");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_status");


    if (status_id == "") {
        push.innerHTML = "<p>status_id must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (status_id != "") {
        if (status_desc == "") {
            push.innerHTML = "<p>status_desc must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (status_desc != "") {

            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.status_id = status_id;
            datjson.status_desc = status_desc;
            $.ajax({
                dataType: "json",
                url: "EditEmployeeStatus",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success edit employee status');
                        $('.cd-popup-edit-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed edit employee status : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

function sub_del_status() {
    var status_id = document.getElementById("status_id_edit").value;


    if (status_id == "") {
        push.innerHTML = "<p>status_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (status_id != "") {
        var datjson = new Object();
        datjson.status_id = status_id;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "DeleteEmployeeStatus",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Delete Status');
                    location.reload();
                } else {
                    alert('Failed Delete Status');
                }
            }
        });
    }
}