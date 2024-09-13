//open popup provide password
$('#table_religion').on('click', 'td', function () {
    var $row = $(this).closest("tr");
    var religion_id = $row.find(".religion_id").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.religion_id = religion_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailReligion",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("religion_id_edit").value = resp.religion_id;
                document.getElementById("religion_name_edit").value = resp.religion_name;

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

    var religion_id = document.getElementById("religion_id_edit").value;
    var religion_name = document.getElementById("religion_name_edit").value;

    var modal = document.getElementById("myModal_edit_status");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_status");


    if (religion_id == "") {
        push.innerHTML = "<p>religion_id must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (religion_id != "") {
        if (religion_name == "") {
            push.innerHTML = "<p>religion_name must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (religion_name != "") {

            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.religion_id = religion_id;
            datjson.religion_name = religion_name;
            $.ajax({
                dataType: "json",
                url: "EditReligion",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success edit religion');
                        $('.cd-popup-edit-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed edit religion : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

function sub_del_status() {
    var religion_id = document.getElementById("religion_id_edit").value;


    if (religion_id == "") {
        push.innerHTML = "<p>religion_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (religion_id != "") {
        var datjson = new Object();
        datjson.religion_id = religion_id;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "DeleteReligion",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Delete religion');
                    location.reload();
                } else {
                    alert('Failed Delete religion');
                }
            }
        });
    }
}