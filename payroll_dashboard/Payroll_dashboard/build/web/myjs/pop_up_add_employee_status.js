jQuery(document).ready(function ($) {
    //open popup provide password
    $('#add_user').on('click', function () {
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


function sub_out() {
    var status_id = document.getElementById("status_id").value;
    var status_desc = document.getElementById("status_desc").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (status_id == "") {
        push.innerHTML = "<p>id shift must be filled</p>";
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
            push.innerHTML = "<p>shift name must be filled</p>";
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
                url: "AddEmployeeStatus",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success add employee status');
                        $('.cd-popup-add-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed add employee status : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

