var currentTab = 0; // Current tab is set to be the first tab (0)

jQuery(document).ready(function ($) {


    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin') {
        document.getElementById('add_employee').style.visibility = 'hidden';
        document.getElementById('add_employee').style.display = 'none';
    }
    //open popup add employee
    $('#add_employee').on('click', function () {
        var company_id = window.localStorage.getItem('company_id');

        showTab(currentTab); // Display the current tab

        var datjson = new Object();
        datjson.company_id = company_id;
        $.ajax({
            dataType: "json",
            url: "OptionReligion",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (response) {
                if (response.resp_code == 0000) {
                    var aa = new Object();
                    aa = response.list;
                    var options = '<option selected disabled value="">Select Religion</option>';
                    for (var i = 0; i < aa.length; i++) {
                        options += '<option value="' + aa[i].religion_id + '">' + aa[i].religion_name + '</option>';
                    }
                    $("select#religion").html(options);
                    $.ajax({
                        dataType: "json",
                        url: "OptionEmployeeStatus",
                        data: JSON.stringify(datjson),
                        type: 'POST',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                var bb = new Object();
                                bb = response.list;
                                var options = '<option selected disabled value="">Select Employee Status</option>';
                                for (var i = 0; i < bb.length; i++) {
                                    options += '<option value="' + bb[i].status_id + '">' + bb[i].status_desc + '</option>';
                                }
                                $("select#employee_status").html(options);
                                $.ajax({
                                    dataType: "json",
                                    url: "OptionMarital",
                                    data: JSON.stringify(datjson),
                                    type: 'POST',
                                    success: function (response) {
                                        if (response.resp_code == 0000) {
                                            var cc = new Object();
                                            cc = response.list;
                                            var options = '<option selected disabled value="">Select Marital Status</option>';
                                            for (var i = 0; i < cc.length; i++) {
                                                options += '<option value="' + cc[i].marital_id + '">' + cc[i].marital_desc + '</option>';
                                            }
                                            $("select#marital_status").html(options);
                                            $.ajax({
                                                dataType: "json",
                                                url: "OptionPosition",
                                                data: JSON.stringify(datjson),
                                                type: 'POST',
                                                success: function (response) {
                                                    if (response.resp_code == 0000) {
                                                        var dd = new Object();
                                                        dd = response.list;
                                                        var options = '<option selected disabled value="">Select Position</option>';
                                                        for (var i = 0; i < dd.length; i++) {
                                                            options += '<option value="' + dd[i].position_id + '">' + dd[i].position_desc + '</option>';
                                                        }
                                                        $("select#position").html(options);
                                                        $.ajax({
                                                            dataType: "json",
                                                            url: "OptionEducation",
                                                            data: JSON.stringify(datjson),
                                                            type: 'POST',
                                                            success: function (response) {
                                                                if (response.resp_code == 0000) {
                                                                    var ee = new Object();
                                                                    ee = response.list;
                                                                    var options = '<option selected disabled value="">Select Last Education</option>';
                                                                    for (var i = 0; i < ee.length; i++) {
                                                                        options += '<option value="' + ee[i].education_id + '">' + ee[i].education_desc + '</option>';
                                                                    }
                                                                    $("select#last_education").html(options);
                                                                    $.ajax({
                                                                        dataType: "json",
                                                                        url: "OptionMajority",
                                                                        data: JSON.stringify(datjson),
                                                                        type: 'POST',
                                                                        success: function (response) {
                                                                            if (response.resp_code == 0000) {
                                                                                var ff = new Object();
                                                                                ff = response.list;
                                                                                var options = '<option selected disabled value="">Select Majority</option>';
                                                                                for (var i = 0; i < ff.length; i++) {
                                                                                    options += '<option value="' + ff[i].majority_id + '">' + ff[i].majority_desc + '</option>';
                                                                                }
                                                                                $("select#majority").html(options);
                                                                                $('.cd-popup-add-user').addClass('is-visible');

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
        $('select#position').change(function (event) {
            var position_id = $("select#position").val();
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
                        $("select#level").html(options);
                    } else {
                        alert('Failed get position : ' + response.resp_desc);
                    }
                }
            });
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


//
//function sub_out() {
//    var employee_id = document.getElementById("employee_id").value;
//    var employee_name = document.getElementById("employee_name").value;
//    var birth_place = document.getElementById("birth_place").value;
//    var birth_date = document.getElementById("birth_date").value;
//    var ktp_address = document.getElementById("ktp_address").value;
//    var phonenumber = document.getElementById("phonenumber").value;
//    var email = document.getElementById("email").value;
//    var join_date = document.getElementById("join_date").value;
//    var nik = document.getElementById("nik").value;
//    var npwp = document.getElementById("npwp").value;
//    var npwp_address = document.getElementById("npwp_address").value;
//    var kpp = document.getElementById("kpp").value;
//    var efin = document.getElementById("efin").value;
//    var bpjs_tk_number = document.getElementById("bpjs_tk_number").value;
//    var religion = document.getElementById("religion").value;
//    var employee_status = document.getElementById("employee_status").value;
//    var marital_status = document.getElementById("marital_status").value;
//    var position = document.getElementById("position").value;
//    var level = document.getElementById("level").value;
//    var last_education = document.getElementById("last_education").value;
//    var school = document.getElementById("school").value;
//    var majority = document.getElementById("majority").value;
//    var gender = document.getElementById("gender").value;
//    var code_pos = document.getElementById("code_pos").value;
//    var blood_type = document.getElementById("blood_type").value;
//
//    var modal = document.getElementById("myModal_add_user");
//    var span = document.getElementsByClassName("close")[0];
//    var push = document.getElementById("push_text_add_user");
//
//
//    if (employee_id == "") {
//        push.innerHTML = "<p>Id must be filled</p>";
//        modal.style.display = "block";
//        span.onclick = function () {
//            modal.style.display = "none";
//        }
//        window.onclick = function (event) {
//            if (event.target == modal) {
//                modal.style.display = "none";
//            }
//        }
//    } else if (employee_id != "") {
//        if (employee_name == "") {
//            push.innerHTML = "<p>fullname must be filled</p>";
//            modal.style.display = "block";
//            span.onclick = function () {
//                modal.style.display = "none";
//            }
//            window.onclick = function (event) {
//                if (event.target == modal) {
//                    modal.style.display = "none";
//                }
//            }
//        } else if (employee_name != "") {
//            if (birth_place == "") {
//                push.innerHTML = "<p>birthplace must be filled</p>";
//                modal.style.display = "block";
//                span.onclick = function () {
//                    modal.style.display = "none";
//                }
//                window.onclick = function (event) {
//                    if (event.target == modal) {
//                        modal.style.display = "none";
//                    }
//                }
//            } else if (birth_place != "") {
//                if (birth_date == "") {
//                    push.innerHTML = "<p>birthdate must be filled</p>";
//                    modal.style.display = "block";
//                    span.onclick = function () {
//                        modal.style.display = "none";
//                    }
//                    window.onclick = function (event) {
//                        if (event.target == modal) {
//                            modal.style.display = "none";
//                        }
//                    }
//                } else if (birth_date != "") {
//                    if (ktp_address == "") {
//                        push.innerHTML = "<p>ktp address must be filled</p>";
//                        modal.style.display = "block";
//                        span.onclick = function () {
//                            modal.style.display = "none";
//                        }
//                        window.onclick = function (event) {
//                            if (event.target == modal) {
//                                modal.style.display = "none";
//                            }
//                        }
//                    } else if (ktp_address != "") {
//                        if (phonenumber == "") {
//                            push.innerHTML = "<p>phonenumber must be filled</p>";
//                            modal.style.display = "block";
//                            span.onclick = function () {
//                                modal.style.display = "none";
//                            }
//                            window.onclick = function (event) {
//                                if (event.target == modal) {
//                                    modal.style.display = "none";
//                                }
//                            }
//                        } else if (phonenumber != "") {
//                            if (email == "") {
//                                push.innerHTML = "<p>email must be filled</p>";
//                                modal.style.display = "block";
//                                span.onclick = function () {
//                                    modal.style.display = "none";
//                                }
//                                window.onclick = function (event) {
//                                    if (event.target == modal) {
//                                        modal.style.display = "none";
//                                    }
//                                }
//                            } else if (email != "") {
//                                if (join_date == "") {
//                                    push.innerHTML = "<p>joindate must be filled</p>";
//                                    modal.style.display = "block";
//                                    span.onclick = function () {
//                                        modal.style.display = "none";
//                                    }
//                                    window.onclick = function (event) {
//                                        if (event.target == modal) {
//                                            modal.style.display = "none";
//                                        }
//                                    }
//                                } else if (join_date != "") {
//                                    if (nik == "") {
//                                        push.innerHTML = "<p>nik must be filled</p>";
//                                        modal.style.display = "block";
//                                        span.onclick = function () {
//                                            modal.style.display = "none";
//                                        }
//                                        window.onclick = function (event) {
//                                            if (event.target == modal) {
//                                                modal.style.display = "none";
//                                            }
//                                        }
//                                    } else if (nik != "") {
//                                        if (religion == "") {
//                                            push.innerHTML = "<p>religion must be filled</p>";
//                                            modal.style.display = "block";
//                                            span.onclick = function () {
//                                                modal.style.display = "none";
//                                            }
//                                            window.onclick = function (event) {
//                                                if (event.target == modal) {
//                                                    modal.style.display = "none";
//                                                }
//                                            }
//                                        } else if (religion != "") {
//                                            if (employee_status == "") {
//                                                push.innerHTML = "<p>employee status must be filled</p>";
//                                                modal.style.display = "block";
//                                                span.onclick = function () {
//                                                    modal.style.display = "none";
//                                                }
//                                                window.onclick = function (event) {
//                                                    if (event.target == modal) {
//                                                        modal.style.display = "none";
//                                                    }
//                                                }
//                                            } else if (employee_status != "") {
//                                                if (marital_status == "") {
//                                                    push.innerHTML = "<p>marital status must be filled</p>";
//                                                    modal.style.display = "block";
//                                                    span.onclick = function () {
//                                                        modal.style.display = "none";
//                                                    }
//                                                    window.onclick = function (event) {
//                                                        if (event.target == modal) {
//                                                            modal.style.display = "none";
//                                                        }
//                                                    }
//                                                } else if (marital_status != "") {
//                                                    if (position == "") {
//                                                        push.innerHTML = "<p>position must be filled</p>";
//                                                        modal.style.display = "block";
//                                                        span.onclick = function () {
//                                                            modal.style.display = "none";
//                                                        }
//                                                        window.onclick = function (event) {
//                                                            if (event.target == modal) {
//                                                                modal.style.display = "none";
//                                                            }
//                                                        }
//                                                    } else if (position != "") {
//                                                        if (level == "") {
//                                                            push.innerHTML = "<p>level must be filled</p>";
//                                                            modal.style.display = "block";
//                                                            span.onclick = function () {
//                                                                modal.style.display = "none";
//                                                            }
//                                                            window.onclick = function (event) {
//                                                                if (event.target == modal) {
//                                                                    modal.style.display = "none";
//                                                                }
//                                                            }
//                                                        } else if (level != "") {
//                                                            if (last_education == "") {
//                                                                push.innerHTML = "<p>last education must be filled</p>";
//                                                                modal.style.display = "block";
//                                                                span.onclick = function () {
//                                                                    modal.style.display = "none";
//                                                                }
//                                                                window.onclick = function (event) {
//                                                                    if (event.target == modal) {
//                                                                        modal.style.display = "none";
//                                                                    }
//                                                                }
//                                                            } else if (last_education != "") {
//                                                                if (school == "") {
//                                                                    push.innerHTML = "<p>school must be filled</p>";
//                                                                    modal.style.display = "block";
//                                                                    span.onclick = function () {
//                                                                        modal.style.display = "none";
//                                                                    }
//                                                                    window.onclick = function (event) {
//                                                                        if (event.target == modal) {
//                                                                            modal.style.display = "none";
//                                                                        }
//                                                                    }
//                                                                } else if (school != "") {
//                                                                    if (majority == "") {
//                                                                        push.innerHTML = "<p>majority must be filled</p>";
//                                                                        modal.style.display = "block";
//                                                                        span.onclick = function () {
//                                                                            modal.style.display = "none";
//                                                                        }
//                                                                        window.onclick = function (event) {
//                                                                            if (event.target == modal) {
//                                                                                modal.style.display = "none";
//                                                                            }
//                                                                        }
//                                                                    } else if (majority != "") {
//                                                                        var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
//                                                                        if (email.match(mailformat)) {
//                                                                            var datjson = new Object();
//                                                                            datjson.company_id = window.localStorage.getItem('company_id');
//                                                                            datjson.user_id = window.localStorage.getItem('user_id');
//                                                                            datjson.employee_id = employee_id;
//                                                                            datjson.employee_name = employee_name;
//                                                                            datjson.birth_place = birth_place;
//                                                                            datjson.birth_date = birth_date;
//                                                                            datjson.ktp_address = ktp_address;
//                                                                            datjson.phonenumber = phonenumber;
//                                                                            datjson.email = email;
//                                                                            datjson.join_date = join_date;
//                                                                            datjson.nik = nik;
//                                                                            datjson.npwp = npwp;
//                                                                            datjson.npwp_address = npwp_address;
//                                                                            datjson.kpp = kpp;
//                                                                            datjson.efin = efin;
//                                                                            datjson.bpjs_tk_number = bpjs_tk_number;
//                                                                            datjson.religion = religion;
//                                                                            datjson.employee_status = employee_status;
//                                                                            datjson.marital_status = marital_status;
//                                                                            datjson.position = position;
//                                                                            datjson.level = level;
//                                                                            datjson.last_education = last_education;
//                                                                            datjson.school = school;
//                                                                            datjson.majority = majority;
//                                                                            datjson.gender = gender;
//                                                                            datjson.blood_type = blood_type;
//                                                                            datjson.code_pos = code_pos;
//                                                                            $.ajax({
//                                                                                dataType: "json",
//                                                                                url: "AddEmployee",
//                                                                                data: JSON.stringify(datjson),
//                                                                                type: 'post',
//                                                                                success: function (response) {
//                                                                                    if (response.resp_code == 0000) {
//                                                                                        loaddata();
//                                                                                        alert('success add employee');
//                                                                                        $('.cd-popup-add-user').removeClass('is-visible');
//                                                                                        $("#form_box1")[0].reset();
//                                                                                        $("#form_box2")[0].reset();
//                                                                                        $("#form_box3")[0].reset();
//                                                                                        $("#form_box4")[0].reset();
//                                                                                    } else {
//                                                                                        alert('Failed add employee : ' + response.resp_desc);
//                                                                                    }
//                                                                                }
//                                                                            });
//                                                                        } else {
//                                                                            push.innerHTML = "<p>Email not valid</p>";
//                                                                            modal.style.display = "block";
//                                                                            span.onclick = function () {
//                                                                                modal.style.display = "none";
//                                                                            };
//                                                                            window.onclick = function (event) {
//                                                                                if (event.target == modal) {
//                                                                                    modal.style.display = "none";
//                                                                                }
//                                                                            };
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}



function showTab(n) {
    // This function will display the specified tab of the form ...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    // ... and fix the Previous/Next buttons:
    if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
    } else {
        document.getElementById("nextBtn").innerHTML = "Next";
    }
    // ... and run a function that displays the correct step indicator:
    fixStepIndicator(n)
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !validateForm())
        return false;
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= x.length) {
        //...the form gets submitted:
        var employee_id = document.getElementById("employee_id").value;
        var employee_name = document.getElementById("employee_name").value;
        var birth_place = document.getElementById("birth_place").value;
        var birth_date = document.getElementById("birth_date").value;
        var ktp_address = document.getElementById("ktp_address").value;
        var phonenumber = document.getElementById("phonenumber").value;
        var email = document.getElementById("email").value;
        var join_date = document.getElementById("join_date").value;
        var nik = document.getElementById("nik").value;
        var npwp = document.getElementById("npwp").value;
        var npwp_address = document.getElementById("npwp_address").value;
        var kpp = document.getElementById("kpp").value;
        var efin = document.getElementById("efin").value;
        var bpjs_tk_number = document.getElementById("bpjs_tk_number").value;
        var religion = document.getElementById("religion").value;
        var employee_status = document.getElementById("employee_status").value;
        var marital_status = document.getElementById("marital_status").value;
        var position = document.getElementById("position").value;
        var level = document.getElementById("level").value;
        var last_education = document.getElementById("last_education").value;
        var school = document.getElementById("school").value;
        var majority = document.getElementById("majority").value;
        var gender = document.getElementById("gender").value;
        var code_pos = document.getElementById("code_pos").value;
        var blood_type = document.getElementById("blood_type").value;
        
        var emergency_contact_name = document.getElementById("emergency_contact_name").value;
        var emergency_contact_phone = document.getElementById("emergency_contact_phone").value;
        var emergency_contact_relationship = document.getElementById("emergency_contact_relationship").value;
        var emergency_contact_name2 = document.getElementById("emergency_contact_name2").value;
        var emergency_contact_phone2 = document.getElementById("emergency_contact_phone2").value;
        var emergency_contact_relationship2 = document.getElementById("emergency_contact_relationship2").value;
       

        var modal = document.getElementById("myModal_add_user");
        var span = document.getElementsByClassName("close")[0];
        var push = document.getElementById("push_text_add_user");

        var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

        if (email.match(mailformat)) {
            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.employee_id = employee_id;
            datjson.employee_name = employee_name;
            datjson.birth_place = birth_place;
            datjson.birth_date = birth_date;
            datjson.ktp_address = ktp_address;
            datjson.phonenumber = phonenumber;
            datjson.email = email;
            datjson.join_date = join_date;
            datjson.nik = nik;
            datjson.npwp = npwp;
            datjson.npwp_address = npwp_address;
            datjson.kpp = kpp;
            datjson.efin = efin;
            datjson.bpjs_tk_number = bpjs_tk_number;
            datjson.religion = religion;
            datjson.employee_status = employee_status;
            datjson.marital_status = marital_status;
            datjson.position = position;
            datjson.level = level;
            datjson.last_education = last_education;
            datjson.school = school;
            datjson.majority = majority;
            datjson.gender = gender;
            datjson.blood_type = blood_type;
            datjson.code_pos = code_pos;
            
            datjson.emergency_contact_name = emergency_contact_name;
            datjson.emergency_contact_phone = emergency_contact_phone;
            datjson.emergency_contact_relationship = emergency_contact_relationship;
            datjson.emergency_contact_name2 = emergency_contact_name2;
            datjson.emergency_contact_phone2 = emergency_contact_phone2;
            datjson.emergency_contact_relationship2 = emergency_contact_relationship2;
            $.ajax({
                dataType: "json",
                url: "AddEmployee",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success add employee');
                        $('.cd-popup-add-user').removeClass('is-visible');
                        $("#form_box1")[0].reset();
                        $("#form_box2")[0].reset();
                        $("#form_box3")[0].reset();
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed add employee : ' + response.resp_desc);
                    }
                }
            });
        } else {
            alert('email not valid')
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
    // Otherwise, display the correct tab:
    showTab(currentTab);
}

function validateForm() {
    // This function deals with validation of the form fields
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].querySelectorAll("input, select");
    // A loop that checks every input and select field in the current tab:
    for (i = 0; i < y.length; i++) {
        // Skip validation for elements with the "no-validate" class
        if (y[i].classList.contains("no-validate")) {
            continue;
        }
        // If a field is empty...
        if (y[i].value == "" || (y[i].type == "email" && !validateEmail(y[i].value))) {
            // add an "invalid" class to the field:
            y[i].classList.add("invalid");
            // and set the current valid status to false:
            valid = false;
        }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
        document.getElementsByClassName("step")[currentTab].classList.add("finish");
    }
    return valid; // return the valid status
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class to the current step:
    x[n].className += " active";
}

function validateEmail(email) {
    // A simple email validation function using a regular expression
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}