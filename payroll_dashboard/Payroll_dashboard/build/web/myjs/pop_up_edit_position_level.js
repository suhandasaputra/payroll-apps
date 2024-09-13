//open popup provide password
$('#table_level').on('click', 'td', function () {
    var $row = $(this).closest("tr");
    var position_level = $row.find(".position_level").text();
    var position_id = $row.find(".position_id").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.position_level = position_level;
    datjson.position_id = position_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailPositionLevel",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("add_position_id_edit").value = resp.position_id;
                document.getElementById("position_level_edit").value = resp.position_level;
                document.getElementById("level_desc_edit").value = resp.level_desc;

                $.ajax({
                    dataType: "json",
                    url: "OptionPosition",
                    data: JSON.stringify(datjson),
                    type: 'POST',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            var aa = new Object();
                            aa = response.list;
                            var options = '<option selected disabled value="' + resp.position_id + '">' + resp.position_desc + '</option>';
                            for (var i = 0; i < aa.length; i++) {
                                options += '<option value="' + aa[i].position_id + '">' + aa[i].position_desc + '</option>';
                            }
                            $("select#add_position_id_edit").html(options);
                            $('.cd-popup-edit-level').addClass('is-visible');

                        } else {
                            alert('Failed get religion : ' + response.resp_desc);
                        }
                    }
                });


            } else {
                alert('Failed get level detail : ' + response.resp_desc);
            }
        }
    });

});

jQuery(document).ready(function ($) {
    //close popup provide password
    $('.cd-popup-edit-level').on('click', function (event) {
        if ($(event.target).is('.cd-popup-edit-level-close') || $(event.target).is('.cd-popup-edit-level')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-edit-level').removeClass('is-visible');
        }
    });
});

function sub_edt_level() {

    var add_position_id = document.getElementById("add_position_id_edit").value;
    var position_level = document.getElementById("position_level_edit").value;
    var level_desc = document.getElementById("level_desc_edit").value;

    var modal = document.getElementById("myModal_edit_level");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_edit_level");


    if (add_position_id == "") {
        push.innerHTML = "<p>id position must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (add_position_id != "") {
        if (position_level == "") {
            push.innerHTML = "<p>position name must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (position_level != "") {

            if (level_desc == "") {
                push.innerHTML = "<p>position level must be filled</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (level_desc != "") {

                var datjson = new Object();
                datjson.company_id = window.localStorage.getItem('company_id');
                datjson.user_id = window.localStorage.getItem('user_id');
                datjson.add_position_id = add_position_id;
                datjson.position_level = position_level;
                datjson.level_desc = level_desc;
                $.ajax({
                    dataType: "json",
                    url: "EditPositionLevel",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            loaddata2();
                            alert('success edit level');
                            $('.cd-popup-edit-level').removeClass('is-visible');
                            $("#form_box4_edit")[0].reset();
                        } else {
                            alert('Failed edit level : ' + response.resp_desc);
                        }
                    }
                });

            }


        }
    }
}

function sub_del_level() {
    var position_id = document.getElementById("add_position_id_edit").value;
    var position_level = document.getElementById("position_level_edit").value;
    if (position_id == "") {
        push.innerHTML = "<p>position_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (position_id != "") {
        if (position_level == "") {
            push.innerHTML = "<p>position_level must be fill</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (position_level != "") {
            var datjson = new Object();
            datjson.position_id = position_id;
            datjson.position_level = position_level;
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            $.ajax({
                dataType: "json",
                url: "DeletePositionLevel",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        alert('Success Delete level');
                        location.reload();
                    } else {
                        alert('Failed Delete level');
                    }
                }
            });
        }
    }
}
