jQuery(document).ready(function ($) {
    //open popup provide password
    $('#add_level').on('click', function () {
        
        var company_id = window.localStorage.getItem('company_id');        
        console.log(company_id);
        var datjson = new Object();        
        datjson.company_id = company_id;                      
        
        $.ajax({
            dataType: "json",
            url: "OptionPosition",
            data: JSON.stringify(datjson),
            type: 'POST',
            success: function (response) {
                if (response.resp_code == 0000) {
                    var aa = new Object();
                    aa = response.list;
                    var options = '<option selected disabled value="">Select Account Level</option>';
                    for (var i = 0; i < aa.length; i++) {
                        options += '<option value="' + aa[i].position_id + '">' + aa[i].position_desc + '</option>';
                    }
                    $("select#add_position_id").html(options);
                    $('.cd-popup-add-level').addClass('is-visible');

                } else {
                    alert('Failed get religion : ' + response.resp_desc);
                }
            }
        });
        
        
    });

    //close popup provide password
    $('.cd-popup-add-level').on('click', function (event) {
        if ($(event.target).is('.cd-popup-add-level-close') || $(event.target).is('.cd-popup-add-level') || $(event.target).is('#btn_cancel')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-add-level').removeClass('is-visible');
        }
    });
});


function sub_out() {
    var add_position_id = document.getElementById("add_position_id").value;
    var position_level = document.getElementById("position_level").value;
    var level_desc = document.getElementById("level_desc").value;

    var modal = document.getElementById("myModal_add_level");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_level");


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
        }else if (level_desc != "") {

            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.add_position_id = add_position_id;
            datjson.position_level = position_level;           
            datjson.level_desc = level_desc;           
            $.ajax({
                dataType: "json",
                url: "AddPositionLevel",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata2();
                        alert('success add level');
                        $('.cd-popup-add-level').removeClass('is-visible');
                        $("#form_box4_add")[0].reset();
                    } else {
                        alert('Failed add level : ' + response.resp_desc);
                    }
                }
            });

        }

        }
    }
}

