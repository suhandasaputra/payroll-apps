//open pop up detail request absence
$('#table_requestreimbursement').on('click', 'td', function () {
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
        url: "GetDetailRequestReimbursement",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("reference_detail_request").value = resp.reference;
                document.getElementById("employee_id_detail_request").value = resp.employee_id;
                document.getElementById("expense_date_detail_request").value = resp.tanggal_pengeluaran;
                document.getElementById("reimbursement_type_detail_request").value = resp.reimbursement_type_desc;
                document.getElementById("date_request_detail_request").value = resp.date_request;
                document.getElementById("amount_detail_request").value = resp.nominal_pengajuan;
                document.getElementById("desc_detail_request").value = resp.description;
                document.getElementById("messages_head_detail_request").value = resp.approve_command_head;
                document.getElementById("messages_admin_detail_request").value = resp.approve_command_admin;
                var img = resp.bukti_pengajuan;

                var fieldButton = document.getElementById("imagebutton");

                // Add a click event listener to the button
                fieldButton.addEventListener("click", function (event) {
                    // Prevent the default form submission behavior
                    event.preventDefault();
                    var popup = document.getElementById('popup');
                    popup.style.display = 'block';
                    var base64Image = img;
                    var buktiPengajuan = document.getElementById('bukti_pengajuan');
                    buktiPengajuan.src = base64Image;

                });
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
                    url: "RequestReimbursementApproveAdmin",
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


function openPopup() {
    var popup = document.getElementById('popup');
    popup.style.display = 'block';
//  var base64Image = "data:image/png;base64,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"; // Ganti dengan kode base64 gambar Anda
    var buktiPengajuan = document.getElementById('bukti_pengajuan');
//  buktiPengajuan.src = base64Image;
}

function closePopup() {
    var popup = document.getElementById('popup');
    popup.style.display = 'none';
}

