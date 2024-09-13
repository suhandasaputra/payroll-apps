//open pop up detail request absence
$('#table_requesttimeoff').on('click', 'td', function () {
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
        url: "GetDetailRequestTimeoff",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("reference_detail_request").value = resp.reference;
                document.getElementById("employee_id_detail_request").value = resp.employee_id;
                document.getElementById("date_off_detail_request").value = resp.date_off;
                document.getElementById("absence_type_detail_request").value = resp.absence_type;
                document.getElementById("date_request_detail_request").value = resp.date_request;
                document.getElementById("timeoff_code_detail_request").value = resp.timeoff_code;
                document.getElementById("total_day_detail_request").value = resp.total_day;
                document.getElementById("desc_detail_request").value = resp.description;
                document.getElementById("messages_head_detail_request").value = resp.approve_command_head;
                document.getElementById("messages_admin_detail_request").value = resp.approve_command_admin;

                var level_desc = window.localStorage.getItem('level_desc');
                if (level_desc !== 'head') {

                    document.getElementById('messages_head_detail_request').disabled = true;
                    if (resp.approve_by_head !== null) {
                        //hidden button approve/reject
                        document.getElementById('btn_cancel').style.visibility = 'visible';
                        document.getElementById('btn_cancel').style.display = 'block';
                        document.getElementById('btn_delete').style.visibility = 'hidden';
                        document.getElementById('btn_delete').style.display = 'none';
                        document.getElementById('btn_edit_submit_user').style.visibility = 'hidden';
                        document.getElementById('btn_edit_submit_user').style.display = 'none';
                        document.getElementById('messages_admin_detail_request').disabled = true;
                        $('.cd-popup-edit-user').addClass('is-visible');
                    } else if (resp.approve_by_head == null) {
                        document.getElementById('btn_cancel').style.visibility = 'hidden';
                        document.getElementById('btn_cancel').style.display = 'none';
                        document.getElementById('btn_delete').style.visibility = 'visible';
                        document.getElementById('btn_delete').style.display = 'block';
                        document.getElementById('btn_edit_submit_user').style.visibility = 'visible';
                        document.getElementById('btn_edit_submit_user').style.display = 'block';
                        $('.cd-popup-edit-user').addClass('is-visible');
                    }
                } else if (level_desc == 'head') {
                    
                    document.getElementById('messages_admin_detail_request').disabled = true;
                    $('.cd-popup-edit-user').addClass('is-visible')
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
        if ($(event.target).is('.cd-popup-edit-user-close') || $(event.target).is('.cd-popup-edit-user') || $(event.target).is('#btn_cancel')) {
            event.preventDefault();
            document.getElementById('messages_admin_detail_request').disabled = false;
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            document.getElementById('messages_admin_detail_request').disabled = false;
            $('.cd-popup-edit-user').removeClass('is-visible');
        }
    });
});

function sub_approve() {

    var reference = document.getElementById("reference_detail_request").value;
    var employee_id = document.getElementById("employee_id_detail_request").value;
    var messages_admin = document.getElementById("messages_admin_detail_request").value;
    var messages_head = document.getElementById("messages_head_detail_request").value;

    var modal = document.getElementById("myModal_edit_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_user");


    if (reference == "") {
        push.innerHTML = "<p>reference must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (reference != "") {
        if (employee_id == "") {
            push.innerHTML = "<p>employee id must be filled</p>";
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
            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.reference = reference;
            datjson.employee_id = employee_id;
            datjson.messages = messages_admin;

            var level_desc = window.localStorage.getItem('level_desc');
            if (level_desc !== 'head') {
                $.ajax({
                    dataType: "json",
                    url: "RequestTimeoffApproveAdmin",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_request_from').val();
                            if (start !== "") {
                                loaddatabydate();
                                alert('success approve request by admin');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            } else if (start == "") {
                                loaddata();
                                alert('success approve request by admin');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            }

                        } else {
                            alert('Failed to approve request by admin : ' + response.resp_desc);
                        }
                    }
                });
            } else if (level_desc == 'head') {
                var datjson2 = new Object();
                datjson2.company_id = window.localStorage.getItem('company_id');
                datjson2.user_id = window.localStorage.getItem('user_id');
                datjson2.reference = reference;
                datjson2.employee_id = employee_id;
                datjson2.messages = messages_head;

                $.ajax({
                    dataType: "json",
                    url: "RequestTimeoffApproveHead",
                    data: JSON.stringify(datjson2),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_request_from').val();
                            if (start !== "") {
                                loaddatabydate();
                                alert('success approve request by head');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            } else if (start == "") {
                                loaddata();
                                alert('success approve request by head');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            }
                        } else {
                            alert('Failed to approve request by head : ' + response.resp_desc);
                        }
                    }
                });
            }



        }
    }
}

function sub_reject() {
    var reference = document.getElementById("reference_detail_request").value;
    var employee_id = document.getElementById("employee_id_detail_request").value;
    var messages_admin = document.getElementById("messages_admin_detail_request").value;
    var messages_head = document.getElementById("messages_head_detail_request").value;

    var modal = document.getElementById("myModal_edit_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_user");


    if (reference === "") {
        push.innerHTML = "<p>reference must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        };
        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        };
    } else if (reference !== "") {
        if (employee_id === "") {
            push.innerHTML = "<p>employee id must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            };
            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            };
        } else if (employee_id !== "") {
            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.reference = reference;
            datjson.employee_id = employee_id;
            datjson.messages = messages_admin;


            var level_desc = window.localStorage.getItem('level_desc');
            if (level_desc !== 'head') {
                $.ajax({
                    dataType: "json",
                    url: "RequestTimeoffRejectAdmin",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_request_from').val();
                            if (start !== "") {
                                loaddatabydate();
                                alert('success reject request by admin');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            } else if (start == "") {
                                loaddata();
                                alert('success reject request by admin');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            }
                        } else {
                            alert('Failed to reject request by admin: ' + response.resp_desc);
                        }
                    }
                });
            } else if (level_desc == 'head') {
                var datjson2 = new Object();
                datjson2.company_id = window.localStorage.getItem('company_id');
                datjson2.user_id = window.localStorage.getItem('user_id');
                datjson2.reference = reference;
                datjson2.employee_id = employee_id;
                datjson2.messages = messages_head;

                $.ajax({
                    dataType: "json",
                    url: "RequestTimeoffRejectHead",
                    data: JSON.stringify(datjson2),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var start = $('#tanggal_request_from').val();
                            if (start !== "") {
                                loaddatabydate();
                                alert('success reject request by head');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            } else if (start == "") {
                                loaddata();
                                alert('success reject request by head');
                                $('.cd-popup-edit-user').removeClass('is-visible');
                                $("#form_box3")[0].reset();
                                $("#form_box4")[0].reset();
                                $("#form_box5")[0].reset();
                            }
                        } else {
                            alert('Failed to reject request by head: ' + response.resp_desc);
                        }
                    }
                });
            }



        }
    }
}