/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var level_desc = window.localStorage.getItem('level_desc');
if (level_desc !== 'admin') {
/// can't open popup edit employee
} else if (level_desc === 'admin') {
    //open popup edit employee
    $('#table_employee').on('click', 'td', function () {
        var $row = $(this).closest("tr");

        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();

        var employee_id = $row.find(".employee_id").text();
        var company_id = localStorage.getItem('company_id');



//Membuat Table Untuk Tab Career

        $('#table_employee_career').dataTable().fnClearTable();
        $('#table_employee_career').dataTable().fnDestroy();

        var datjson = new Object();
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.employee_id = employee_id;
        $.ajax({
            contentType: 'application/json',
            dataType: "json",
            url: "GetListEmployeeCareer",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (response) {
                if (response.resp_code == 0000) {
                    var dataemployeecareer = response.list;
                    $('#table_employee_career').DataTable({
                        "scrollX": true,
                        "data": dataemployeecareer,
                        "columns": [
                            {data: "reference",
                                "className": 'employee_career reference'},
                            {data: "company_name",
                                "className": 'employee_career company_name'},
                            {data: "business_sector",
                                "className": 'employee_career business_sector'},
                            {data: "position",
                                "className": 'employee_career position'},
                            {data: "division",
                                "className": 'employee_career division'},
                            {data: "start_date",
                                "className": 'employee_career start_date'},
                            {data: "end_date",
                                "className": 'employee_career end_date'},
                            {data: "status_approve",
                                "className": 'employee_career status_approve'}
                        ],
                        "createdRow": function (row, data, dataIndex) {
                            if (data.status_approve == 'PENDING') {
                                $(row).addClass('red');

                            }
                        },
                        dom: 'Bfrtip',
                        "pageLength": 5,
                        autoClose: true,
                        "buttons": []


                    });
                } else {
                    alert('failed retrieve data : ' + response.resp_desc);
                }
            }
        });



        //Membuat Table Untuk Tab Family

        $('#table_employee_family').dataTable().fnClearTable();
        $('#table_employee_family').dataTable().fnDestroy();

        var datjson = new Object();
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.employee_id = employee_id;
        $.ajax({
            contentType: 'application/json',
            dataType: "json",
            url: "GetListEmployeeFamily",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (response) {
                if (response.resp_code == 0000) {
                    var dataemployeecareer = response.list;
                    $('#table_employee_family').DataTable({
                        "scrollX": true,
                        "data": dataemployeecareer,
                        "columns": [
                            {data: "reference",
                                "className": 'employee_family reference'},
                            {data: "name",
                                "className": 'employee_family name'},
                            {data: "gender",
                                "className": 'employee_family gender'},
                            {data: "birth_date",
                                "className": 'employee_family birth_date'},
                            {data: "relation",
                                "className": 'employee_family relation'},
                            {data: "status_approve",
                                "className": 'employee_career status_approve'}
                        ],
                        "createdRow": function (row, data, dataIndex) {
                            if (data.status_approve == 'PENDING') {
                                $(row).addClass('red');

                            }
                        },
                        dom: 'Bfrtip',
                        "pageLength": 5,
                        autoClose: true,
                        "buttons": []


                    });
                } else {
                    alert('failed retrieve data : ' + response.resp_desc);
                }
            }
        });



// GET DETAIL EMPLOYEE
        var datjson = new Object();
        datjson.employee_id = employee_id;
        datjson.company_id = company_id;
        $.ajax({
            dataType: "json",
            url: "GetDetailEmployee",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (resp) {
                if (resp.resp_code == 0000) {
                    document.getElementById("employee_id_edit_user").value = resp.employee_id;
                    document.getElementById("employee_id_title").textContent = resp.employee_id;
                    document.getElementById("employee_name_edit_user").value = resp.employee_name;
                    document.getElementById("birth_place_edit_user").value = resp.birth_place;
                    document.getElementById("birth_date_edit_user").value = resp.birth_date;
                    document.getElementById("ktp_address_edit_user").value = resp.ktp_address;
                    document.getElementById("phonenumber_edit_user").value = resp.phonenumber;
                    document.getElementById("email_edit_user").value = resp.email;
                    document.getElementById("join_date_edit_user").value = resp.join_date;
                    document.getElementById("nik_edit_user").value = resp.nik;
                    document.getElementById("npwp_edit_user").value = resp.npwp;
                    document.getElementById("npwp_address_edit_user").value = resp.npwp_address;
                    document.getElementById("kpp_edit_user").value = resp.kpp;
                    document.getElementById("efin_edit_user").value = resp.efin;
                    document.getElementById("bpjs_tk_number_edit_user").value = resp.bpjs_tk_number;
                    document.getElementById("level_edit_user").value = resp.level_desc;
                    document.getElementById("school_edit_user").value = resp.school;
                    document.getElementById("out_date_edit_user").value = resp.out_date;
                    document.getElementById("resign_status_edit_user").value = resp.resign_status;
                    document.getElementById("length_of_work_edit_user").value = resp.length_of_work;
                    document.getElementById("gender_edit_user").value = resp.gender;
                    document.getElementById("code_pos_edit_user").value = resp.code_pos;
                    document.getElementById("blood_type_edit_user").value = resp.blood_type;

                    document.getElementById("emergency_contact_name_1").value = resp.emergency_contact_name_1;
                    document.getElementById("emergency_contact_phone_1").value = resp.emergency_contact_phone_1;
                    document.getElementById("emergency_contact_relation_1").value = resp.emergency_contact_relation_1;
                    document.getElementById("emergency_contact_name_2").value = resp.emergency_contact_name_2;
                    document.getElementById("emergency_contact_phone_2").value = resp.emergency_contact_phone_2;
                    document.getElementById("emergency_contact_relation_2").value = resp.emergency_contact_relation_2;

                    document.getElementById("trv_plafon").value = resp.trv_plafon;
                    document.getElementById("trv_sisa").value = resp.trv_sisa;
                    document.getElementById("trv_terpakai").value = resp.trv_terpakai;
                    document.getElementById("edu_plafon").value = resp.edu_plafon;
                    document.getElementById("edu_sisa").value = resp.edu_sisa;
                    document.getElementById("edu_terpakai").value = resp.edu_terpakai;
                    document.getElementById("tol_plafon").value = resp.tol_plafon;
                    document.getElementById("tol_sisa").value = resp.tol_sisa;
                    document.getElementById("tol_terpakai").value = resp.tol_terpakai;
                    document.getElementById("med_plafon").value = resp.med_plafon;
                    document.getElementById("med_sisa").value = resp.med_sisa;
                    document.getElementById("med_terpakai").value = resp.med_terpakai;
                    document.getElementById("oth_plafon").value = resp.oth_plafon;
                    document.getElementById("oth_sisa").value = resp.oth_sisa;
                    document.getElementById("oth_terpakai").value = resp.oth_terpakai;

                    if (resp.resign_status == '1') {
                        document.getElementById('out_date_edit_user').style.visibility = 'visible';
                        document.getElementById('out_date_edit_user_label').style.visibility = 'visible';
                        document.getElementById('resign_status_edit_user').style.visibility = 'visible';
                        document.getElementById('resign_status_edit_user_label').style.visibility = 'visible';
                        document.getElementById('btn_rsgn').style.visibility = 'hidden';
                        document.getElementById('btn_rsgn').style.display = 'none';
                        document.getElementById('btn_open_rsgn').style.visibility = 'visible';
                        document.getElementById('btn_open_rsgn').style.display = 'block';
                    } else if (resp.resign_status == '0') {
                        document.getElementById('out_date_edit_user').style.visibility = 'hidden';
                        document.getElementById('out_date_edit_user_label').style.visibility = 'hidden';
                        document.getElementById('resign_status_edit_user').style.visibility = 'hidden';
                        document.getElementById('resign_status_edit_user_label').style.visibility = 'hidden';
                        document.getElementById('btn_rsgn').style.visibility = 'visible';
                        document.getElementById('btn_rsgn').style.display = 'block';
                        document.getElementById('btn_open_rsgn').style.visibility = 'hidden';
                        document.getElementById('btn_open_rsgn').style.display = 'none';
                    }

                    $.ajax({
                        dataType: "json",
                        url: "OptionReligion",
                        data: JSON.stringify(datjson),
                        type: 'POST',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                var aa = new Object();
                                aa = response.list;
                                var options = '<option selected disabled value="' + resp.religion + '">' + resp.religion_name + '</option>';
                                for (var i = 0; i < aa.length; i++) {
                                    options += '<option value="' + aa[i].religion_id + '">' + aa[i].religion_name + '</option>';
                                }
                                $("select#religion_edit_user").html(options);
                                $.ajax({
                                    dataType: "json",
                                    url: "OptionEmployeeStatus",
                                    data: JSON.stringify(datjson),
                                    type: 'POST',
                                    success: function (response) {
                                        if (response.resp_code == 0000) {
                                            var bb = new Object();
                                            bb = response.list;
                                            var options = '<option selected disabled value="' + resp.employee_status + '">' + resp.status_desc + '</option>';
                                            for (var i = 0; i < bb.length; i++) {
                                                options += '<option value="' + bb[i].status_id + '">' + bb[i].status_desc + '</option>';
                                            }
                                            $("select#employee_status_edit_user").html(options);
                                            $.ajax({
                                                dataType: "json",
                                                url: "OptionMarital",
                                                data: JSON.stringify(datjson),
                                                type: 'POST',
                                                success: function (response) {
                                                    if (response.resp_code == 0000) {
                                                        var cc = new Object();
                                                        cc = response.list;
                                                        var options = '<option selected disabled value="' + resp.marital_status + '">' + resp.marital_desc + '</option>';
                                                        for (var i = 0; i < cc.length; i++) {
                                                            options += '<option value="' + cc[i].marital_id + '">' + cc[i].marital_desc + '</option>';
                                                        }
                                                        $("select#marital_status_edit_user").html(options);
                                                        $.ajax({
                                                            dataType: "json",
                                                            url: "OptionPosition",
                                                            data: JSON.stringify(datjson),
                                                            type: 'POST',
                                                            success: function (response) {
                                                                if (response.resp_code == 0000) {
                                                                    var dd = new Object();
                                                                    dd = response.list;
                                                                    var options = '<option selected disabled value="' + resp.position + '">' + resp.position_desc + '</option>';
                                                                    for (var i = 0; i < dd.length; i++) {
                                                                        options += '<option value="' + dd[i].position_id + '">' + dd[i].position_desc + '</option>';
                                                                    }
                                                                    $("select#position_edit_user").html(options);
                                                                    var datjson = new Object();
                                                                    datjson.position_id = resp.position;
                                                                    datjson.company_id = resp.company_id;
                                                                    $.ajax({
                                                                        dataType: "json",
                                                                        url: "OptionLevel",
                                                                        data: JSON.stringify(datjson),
                                                                        type: 'POST',
                                                                        success: function (response) {
                                                                            if (response.resp_code == 0000) {
                                                                                var ff = new Object();
                                                                                ff = response.list;
                                                                                var options = '<option selected disabled value="' + resp.position_level + '">' + resp.level_desc + '</option>';
                                                                                for (var i = 0; i < ff.length; i++) {
                                                                                    options += '<option value="' + ff[i].position_level + '">' + ff[i].level_desc + '</option>';
                                                                                }
                                                                                $("select#level_edit_user").html(options);
                                                                                $.ajax({
                                                                                    dataType: "json",
                                                                                    url: "OptionEducation",
                                                                                    data: JSON.stringify(datjson),
                                                                                    type: 'POST',
                                                                                    success: function (response) {
                                                                                        if (response.resp_code == 0000) {
                                                                                            var ee = new Object();
                                                                                            ee = response.list;
                                                                                            var options = '<option selected disabled value="' + resp.last_education + '">' + resp.education_desc + '</option>';
                                                                                            for (var i = 0; i < ee.length; i++) {
                                                                                                options += '<option value="' + ee[i].education_id + '">' + ee[i].education_desc + '</option>';
                                                                                            }
                                                                                            $("select#last_education_edit_user").html(options);
                                                                                            $.ajax({
                                                                                                dataType: "json",
                                                                                                url: "OptionMajority",
                                                                                                data: JSON.stringify(datjson),
                                                                                                type: 'POST',
                                                                                                success: function (response) {
                                                                                                    if (response.resp_code == 0000) {
                                                                                                        var ff = new Object();
                                                                                                        ff = response.list;
                                                                                                        var options = '<option selected disabled value="' + resp.majority + '">' + resp.majority_desc + '</option>';
                                                                                                        for (var i = 0; i < ff.length; i++) {
                                                                                                            options += '<option value="' + ff[i].majority_id + '">' + ff[i].majority_desc + '</option>';
                                                                                                        }
                                                                                                        $("select#majority_edit_user").html(options);
                                                                                                        $('.cd-popup-edit-user').addClass('is-visible');

                                                                                                        /////////////////

                                                                                                        $('#table_employee_family').on('click', 'td', function () {
                                                                                                            var $row = $(this).closest("tr");

                                                                                                            // Get the element with id="defaultOpen" and click on it
                                                                                                            document.getElementById("defaultOpen").click();

                                                                                                            var reference = $row.find(".reference").text();                                                                                                            

                                                                                                            var datjson = new Object();
                                                                                                            datjson.reference = reference;
                                                                                                            datjson.company_id = company_id;
                                                                                                            datjson.employee_id = employee_id;

                                                                                                            $.ajax({
                                                                                                                dataType: "json",
                                                                                                                url: "GetDetailEmployeeFamily",
                                                                                                                data: JSON.stringify(datjson),
                                                                                                                type: 'POST',
                                                                                                                success: function (response) {
                                                                                                                    if (response.resp_code == 0000) {
                                                                                                                       
                                                                                                                       
                                                                                                                        console.log('berhasil');
                                                                                                                       
                                                                                                                    } else {
                                                                                                                        alert('Failed get detail family : ' + response.resp_desc);
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                        });

                                                                                                         /////////////////

                                                                                                    } else {
                                                                                                        alert('Failed get majority : ' + response.resp_desc);
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                        } else {
                                                                                            alert('Failed get last education : ' + response.resp_desc);
                                                                                        }
                                                                                    }
                                                                                });
                                                                            } else {
                                                                                alert('Failed get position : ' + response.resp_desc);
                                                                            }
                                                                        }
                                                                    });
                                                                } else {
                                                                    alert('Failed get position : ' + response.resp_desc);
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        alert('Failed get marital : ' + response.resp_desc);
                                                    }
                                                }
                                            });
                                        } else {
                                            alert('Failed get status : ' + response.resp_desc);
                                        }
                                    }
                                });
                            } else {
                                alert('Failed get religion : ' + response.resp_desc);
                            }
                        }
                    });
                    $('select#position_edit_user').change(function (event) {
                        var position_id = $("select#position_edit_user").val();
                        var datjson = new Object();
                        datjson.position_id = position_id;
                        datjson.company_id = company_id;

                        $.ajax({
                            dataType: "json",
                            url: "OptionLevel",
                            data: JSON.stringify(datjson),
                            type: 'POST',
                            success: function (response) {
                                if (response.resp_code == 0000) {
                                    var ff = new Object();
                                    ff = response.list;
                                    var options = '<option selected disabled value="">Select Level</option>';
                                    for (var i = 0; i < ff.length; i++) {
                                        options += '<option value="' + ff[i].position_level + '">' + ff[i].level_desc + '</option>';
                                    }
                                    $("select#level_edit_user").html(options);
                                } else {
                                    alert('Failed get position : ' + response.resp_desc);
                                }
                            }
                        });
                    });




                } else {
                    alert('Failed get religion : ' + response.resp_desc);
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




function sub_edt_user() {
    var employee_id_edit_user = document.getElementById("employee_id_edit_user").value;
    var employee_name_edit_user = document.getElementById("employee_name_edit_user").value;
    var birth_place_edit_user = document.getElementById("birth_place_edit_user").value;
    var birth_date_edit_user = document.getElementById("birth_date_edit_user").value;
    var ktp_address_edit_user = document.getElementById("ktp_address_edit_user").value;
    var phonenumber_edit_user = document.getElementById("phonenumber_edit_user").value;
    var email_edit_user = document.getElementById("email_edit_user").value;
    var join_date_edit_user = document.getElementById("join_date_edit_user").value;
    var nik_edit_user = document.getElementById("nik_edit_user").value;
    var npwp_edit_user = document.getElementById("npwp_edit_user").value;
    var npwp_address_edit_user = document.getElementById("npwp_address_edit_user").value;
    var kpp_edit_user = document.getElementById("kpp_edit_user").value;
    var efin_edit_user = document.getElementById("efin_edit_user").value;
    var bpjs_tk_number_edit_user = document.getElementById("bpjs_tk_number_edit_user").value;
    var religion_edit_user = document.getElementById("religion_edit_user").value;
    var employee_status_edit_user = document.getElementById("employee_status_edit_user").value;
    var marital_status_edit_user = document.getElementById("marital_status_edit_user").value;
    var position_edit_user = document.getElementById("position_edit_user").value;
    var level_edit_user = document.getElementById("level_edit_user").value;
    var last_education_edit_user = document.getElementById("last_education_edit_user").value;
    var school_edit_user = document.getElementById("school_edit_user").value;
    var majority_edit_user = document.getElementById("majority_edit_user").value;

    var emergency_contact_name_1 = document.getElementById("emergency_contact_name_1").value;
    var emergency_contact_phone_1 = document.getElementById("emergency_contact_phone_1").value;
    var emergency_contact_relation_1 = document.getElementById("emergency_contact_relation_1").value;
    var emergency_contact_name_2 = document.getElementById("emergency_contact_name_2").value;
    var emergency_contact_phone_2 = document.getElementById("emergency_contact_phone_2").value;
    var emergency_contact_relation_2 = document.getElementById("emergency_contact_relation_2").value;

    var blood_type = document.getElementById("blood_type_edit_user").value;
    var gender = document.getElementById("gender_edit_user").value;
    var code_pos = document.getElementById("code_pos_edit_user").value;

    var trv_plafon = document.getElementById("trv_plafon").value;
    var edu_plafon = document.getElementById("edu_plafon").value;
    var tol_plafon = document.getElementById("tol_plafon").value;
    var med_plafon = document.getElementById("med_plafon").value;
    var oth_plafon = document.getElementById("oth_plafon").value;

    var trv_sisa = document.getElementById("trv_sisa").value;
    var edu_sisa = document.getElementById("edu_sisa").value;
    var tol_sisa = document.getElementById("tol_sisa").value;
    var med_sisa = document.getElementById("med_sisa").value;
    var oth_sisa = document.getElementById("oth_sisa").value;

    var trv_terpakai = document.getElementById("trv_terpakai").value;
    var edu_terpakai = document.getElementById("edu_terpakai").value;
    var tol_terpakai = document.getElementById("tol_terpakai").value;
    var med_terpakai = document.getElementById("med_terpakai").value;
    var oth_terpakai = document.getElementById("oth_terpakai").value;


    var modal = document.getElementById("myModal_edit_user");
    var span = document.getElementsByClassName("close_edit_user")[0];
    var push = document.getElementById("push_text_edit_user");



    if (employee_id_edit_user == "") {
        push.innerHTML = "<p>Id must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id_edit_user != "") {
        if (employee_name_edit_user == "") {
            push.innerHTML = "<p>fullname must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (employee_name_edit_user != "") {
            if (birth_place_edit_user == "") {
                push.innerHTML = "<p>birthplace must be filled</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (birth_place_edit_user != "") {
                if (birth_date_edit_user == "") {
                    push.innerHTML = "<p>birthdate must be filled</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (birth_date_edit_user != "") {
                    if (ktp_address_edit_user == "") {
                        push.innerHTML = "<p>ktp address must be filled</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (ktp_address_edit_user != "") {
                        if (phonenumber_edit_user == "") {
                            push.innerHTML = "<p>phonenumber must be filled</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (phonenumber_edit_user != "") {
                            if (email_edit_user == "") {
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
                            } else if (email_edit_user != "") {
                                if (join_date_edit_user == "") {
                                    push.innerHTML = "<p>joindate must be filled</p>";
                                    modal.style.display = "block";
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
                                    window.onclick = function (event) {
                                        if (event.target == modal) {
                                            modal.style.display = "none";
                                        }
                                    }
                                } else if (join_date_edit_user != "") {
                                    if (nik_edit_user == "") {
                                        push.innerHTML = "<p>nik must be filled</p>";
                                        modal.style.display = "block";
                                        span.onclick = function () {
                                            modal.style.display = "none";
                                        }
                                        window.onclick = function (event) {
                                            if (event.target == modal) {
                                                modal.style.display = "none";
                                            }
                                        }
                                    } else if (nik_edit_user != "") {
//                                        if (npwp == "") {
//                                            push.innerHTML = "<p>npwp must be filled</p>";
//                                            modal.style.display = "block";
//                                            span.onclick = function () {
//                                                modal.style.display = "none";
//                                            }
//                                            window.onclick = function (event) {
//                                                if (event.target == modal) {
//                                                    modal.style.display = "none";
//                                                }
//                                            }
//                                        } else if (npwp != "") {
//                                            if (npwp_address == "") {
//                                                push.innerHTML = "<p>npwp address must be filled</p>";
//                                                modal.style.display = "block";
//                                                span.onclick = function () {
//                                                    modal.style.display = "none";
//                                                }
//                                                window.onclick = function (event) {
//                                                    if (event.target == modal) {
//                                                        modal.style.display = "none";
//                                                    }
//                                                }
//                                            } else if (npwp_address != "") {
//                                                if (kpp == "") {
//                                                    push.innerHTML = "<p>kpp must be filled</p>";
//                                                    modal.style.display = "block";
//                                                    span.onclick = function () {
//                                                        modal.style.display = "none";
//                                                    }
//                                                    window.onclick = function (event) {
//                                                        if (event.target == modal) {
//                                                            modal.style.display = "none";
//                                                        }
//                                                    }
//                                                } else if (kpp != "") {
//                                                    if (efin == "") {
//                                                        push.innerHTML = "<p>efin must be filled</p>";
//                                                        modal.style.display = "block";
//                                                        span.onclick = function () {
//                                                            modal.style.display = "none";
//                                                        }
//                                                        window.onclick = function (event) {
//                                                            if (event.target == modal) {
//                                                                modal.style.display = "none";
//                                                            }
//                                                        }
//                                                    } else if (efin != "") {
//                                                        if (bpjs_tk_number == "") {
//                                                            push.innerHTML = "<p>bpjs number must be filled</p>";
//                                                            modal.style.display = "block";
//                                                            span.onclick = function () {
//                                                                modal.style.display = "none";
//                                                            }
//                                                            window.onclick = function (event) {
//                                                                if (event.target == modal) {
//                                                                    modal.style.display = "none";
//                                                                }
//                                                            }
//                                                        } else if (bpjs_tk_number != "") {
                                        if (religion_edit_user == "") {
                                            push.innerHTML = "<p>religion must be filled</p>";
                                            modal.style.display = "block";
                                            span.onclick = function () {
                                                modal.style.display = "none";
                                            }
                                            window.onclick = function (event) {
                                                if (event.target == modal) {
                                                    modal.style.display = "none";
                                                }
                                            }
                                        } else if (religion_edit_user != "") {
                                            if (employee_status_edit_user == "") {
                                                push.innerHTML = "<p>employee status must be filled</p>";
                                                modal.style.display = "block";
                                                span.onclick = function () {
                                                    modal.style.display = "none";
                                                }
                                                window.onclick = function (event) {
                                                    if (event.target == modal) {
                                                        modal.style.display = "none";
                                                    }
                                                }
                                            } else if (employee_status_edit_user != "") {
                                                if (marital_status_edit_user == "") {
                                                    push.innerHTML = "<p>marital status must be filled</p>";
                                                    modal.style.display = "block";
                                                    span.onclick = function () {
                                                        modal.style.display = "none";
                                                    }
                                                    window.onclick = function (event) {
                                                        if (event.target == modal) {
                                                            modal.style.display = "none";
                                                        }
                                                    }
                                                } else if (marital_status_edit_user != "") {
                                                    if (position_edit_user == "") {
                                                        push.innerHTML = "<p>position must be filled</p>";
                                                        modal.style.display = "block";
                                                        span.onclick = function () {
                                                            modal.style.display = "none";
                                                        }
                                                        window.onclick = function (event) {
                                                            if (event.target == modal) {
                                                                modal.style.display = "none";
                                                            }
                                                        }
                                                    } else if (position_edit_user != "") {
                                                        if (level_edit_user == "") {
                                                            push.innerHTML = "<p>level must be filled</p>";
                                                            modal.style.display = "block";
                                                            span.onclick = function () {
                                                                modal.style.display = "none";
                                                            }
                                                            window.onclick = function (event) {
                                                                if (event.target == modal) {
                                                                    modal.style.display = "none";
                                                                }
                                                            }
                                                        } else if (level_edit_user != "") {
                                                            if (last_education_edit_user == "") {
                                                                push.innerHTML = "<p>last education must be filled</p>";
                                                                modal.style.display = "block";
                                                                span.onclick = function () {
                                                                    modal.style.display = "none";
                                                                }
                                                                window.onclick = function (event) {
                                                                    if (event.target == modal) {
                                                                        modal.style.display = "none";
                                                                    }
                                                                }
                                                            } else if (last_education_edit_user != "") {
                                                                if (school_edit_user == "") {
                                                                    push.innerHTML = "<p>school must be filled</p>";
                                                                    modal.style.display = "block";
                                                                    span.onclick = function () {
                                                                        modal.style.display = "none";
                                                                    }
                                                                    window.onclick = function (event) {
                                                                        if (event.target == modal) {
                                                                            modal.style.display = "none";
                                                                        }
                                                                    }
                                                                } else if (school_edit_user != "") {
                                                                    if (majority_edit_user == "") {
                                                                        push.innerHTML = "<p>majority must be filled</p>";
                                                                        modal.style.display = "block";
                                                                        span.onclick = function () {
                                                                            modal.style.display = "none";
                                                                        }
                                                                        window.onclick = function (event) {
                                                                            if (event.target == modal) {
                                                                                modal.style.display = "none";
                                                                            }
                                                                        }
                                                                    } else if (majority_edit_user != "") {
                                                                        var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
                                                                        if (email_edit_user.match(mailformat)) {
                                                                            var datjson = new Object();
                                                                            datjson.company_id = window.localStorage.getItem('company_id');
                                                                            datjson.user_id = window.localStorage.getItem('user_id');
                                                                            datjson.employee_id = employee_id_edit_user;
                                                                            datjson.employee_name = employee_name_edit_user;
                                                                            datjson.birth_place = birth_place_edit_user;
                                                                            datjson.birth_date = birth_date_edit_user;
                                                                            datjson.ktp_address = ktp_address_edit_user;
                                                                            datjson.phonenumber = phonenumber_edit_user;
                                                                            datjson.email = email_edit_user;
                                                                            datjson.join_date = join_date_edit_user;
                                                                            datjson.nik = nik_edit_user;
                                                                            datjson.npwp = npwp_edit_user;
                                                                            datjson.npwp_address = npwp_address_edit_user;
                                                                            datjson.kpp = kpp_edit_user;
                                                                            datjson.efin = efin_edit_user;
                                                                            datjson.bpjs_tk_number = bpjs_tk_number_edit_user;
                                                                            datjson.religion = religion_edit_user;
                                                                            datjson.employee_status = employee_status_edit_user;
                                                                            datjson.marital_status = marital_status_edit_user;
                                                                            datjson.position = position_edit_user;
                                                                            datjson.level = level_edit_user;
                                                                            datjson.last_education = last_education_edit_user;
                                                                            datjson.school = school_edit_user;
                                                                            datjson.majority = majority_edit_user;

                                                                            datjson.blood_type = blood_type;
                                                                            datjson.code_pos = code_pos;
                                                                            datjson.gender = gender;
                                                                            datjson.emergency_contact_name_1 = emergency_contact_name_1;
                                                                            datjson.emergency_contact_phone_1 = emergency_contact_phone_1;
                                                                            datjson.emergency_contact_relation_1 = emergency_contact_relation_1;
                                                                            datjson.emergency_contact_name_2 = emergency_contact_name_2;
                                                                            datjson.emergency_contact_phone_2 = emergency_contact_phone_2;
                                                                            datjson.emergency_contact_relation_2 = emergency_contact_relation_2;

                                                                            datjson.trv_plafon = trv_plafon;
                                                                            datjson.edu_plafon = edu_plafon;
                                                                            datjson.tol_plafon = tol_plafon;
                                                                            datjson.med_plafon = med_plafon;
                                                                            datjson.oth_plafon = oth_plafon;
                                                                            datjson.trv_sisa = trv_sisa;
                                                                            datjson.edu_sisa = edu_sisa;
                                                                            datjson.tol_sisa = tol_sisa;
                                                                            datjson.med_sisa = med_sisa;
                                                                            datjson.oth_sisa = oth_sisa;
                                                                            datjson.trv_terpakai = trv_terpakai;
                                                                            datjson.edu_terpakai = edu_terpakai;
                                                                            datjson.tol_terpakai = tol_terpakai;
                                                                            datjson.med_terpakai = med_terpakai;
                                                                            datjson.oth_terpakai = oth_terpakai;
                                                                            $.ajax({
                                                                                dataType: "json",
                                                                                url: "EditEmployee",
                                                                                data: JSON.stringify(datjson),
                                                                                type: 'post',
                                                                                success: function (response) {
                                                                                    if (response.resp_code == 0000) {
                                                                                        loaddata();
                                                                                        alert('success update employee');
                                                                                        $('.cd-popup-edit-user').removeClass('is-visible');
                                                                                        $("#form_box1_edit_user")[0].reset();
                                                                                        $("#form_box2_edit_user")[0].reset();
                                                                                        $("#form_box3_edit_user")[0].reset();
                                                                                        $("#form_box4_edit_user")[0].reset();
                                                                                    } else {
                                                                                        alert('Failed update employee : ' + response.resp_desc);
                                                                                    }
                                                                                }
                                                                            });
                                                                        } else {
                                                                            push.innerHTML = "<p>Email not valid</p>";
                                                                            modal.style.display = "block";
                                                                            span.onclick = function () {
                                                                                modal.style.display = "none";
                                                                            };
                                                                            window.onclick = function (event) {
                                                                                if (event.target == modal) {
                                                                                    modal.style.display = "none";
                                                                                }
                                                                            };
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }



}

function sub_del_user() {
    var employee_id_edit_user = document.getElementById("employee_id_edit_user").value;


    if (employee_id_edit_user == "") {
        push.innerHTML = "<p>employee_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id_edit_user != "") {
        var datjson = new Object();
        datjson.employee_id_edit_user = employee_id_edit_user;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "DeleteEmployee",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Delete Employee');
                    location.reload();
                } else {
                    alert('Failed Delete Employee');
                }
            }
        });
    }
}


function sub_rsgn_user() {
    var employee_id_edit_user = document.getElementById("employee_id_edit_user").value;


    if (employee_id_edit_user == "") {
        push.innerHTML = "<p>employee_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id_edit_user != "") {
        var datjson = new Object();
        datjson.employee_id_edit_user = employee_id_edit_user;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "ResignEmployee",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Resign');
                    location.reload();
                } else {
                    alert('Failed Resign');
                }
            }
        });
    }
}

function sub_open_rsgn_user() {
    var employee_id_edit_user = document.getElementById("employee_id_edit_user").value;


    if (employee_id_edit_user == "") {
        push.innerHTML = "<p>employee_id must be fill</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (employee_id_edit_user != "") {
        var datjson = new Object();
        datjson.employee_id_edit_user = employee_id_edit_user;
        datjson.company_id = window.localStorage.getItem('company_id');
        datjson.user_id = window.localStorage.getItem('user_id');
        $.ajax({
            dataType: "json",
            url: "ResignOpenEmployee",
            data: JSON.stringify(datjson),
            type: 'post',
            success: function (response) {
                if (response.resp_code == 0000) {
                    alert('Success Open Resign');
                    location.reload();
                } else {
                    alert('Failed Open Resign');
                }
            }
        });
    }
}

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}




function loaddatacareer() {
}