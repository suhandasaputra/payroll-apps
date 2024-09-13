jQuery(document).ready(function ($) {
    //open popup provide password
    $('#add_position').on('click', function () {
        $('.cd-popup-add-status').addClass('is-visible');
    });

    //close popup provide password
    $('.cd-popup-add-status').on('click', function (event) {
        if ($(event.target).is('.cd-popup-add-status-close') || $(event.target).is('.cd-popup-add-status') || $(event.target).is('#btn_cancel')) {
            event.preventDefault();
            $(this).removeClass('is-visible');
        }
    });
    //close popup when clicking the esc keyboard button provide password
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $('.cd-popup-add-status').removeClass('is-visible');
        }
    });
});


function sub_out_pos() {
    var position_id = document.getElementById("position_id").value;
    var position_desc = document.getElementById("position_desc").value;

    var modal = document.getElementById("myModal_add_status");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_status");


    if (position_id == "") {
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
    } else if (position_id != "") {
        if (position_desc == "") {
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
        } else if (position_desc != "") {

            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.position_id = position_id;
            datjson.position_desc = position_desc;           
            $.ajax({
                dataType: "json",
                url: "AddPosition",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success add position');
                        $('.cd-popup-add-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed add position : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

