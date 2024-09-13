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
    var religion_id = document.getElementById("religion_id").value;
    var religion_name = document.getElementById("religion_name").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (religion_id == "") {
        push.innerHTML = "<p>id religion must be filled</p>";
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
            push.innerHTML = "<p>religion name must be filled</p>";
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
                url: "AddReligion",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success add religion');
                        $('.cd-popup-add-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed add religion : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

