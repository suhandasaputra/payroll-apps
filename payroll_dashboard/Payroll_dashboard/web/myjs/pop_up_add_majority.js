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
    var majority_id = document.getElementById("majority_id").value;
    var majority_desc = document.getElementById("majority_desc").value;

    var modal = document.getElementById("myModal_add_user");
    var span = document.getElementsByClassName("close")[0];
    var push = document.getElementById("push_text_add_user");


    if (majority_id == "") {
        push.innerHTML = "<p>id majority must be filled</p>";
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    } else if (majority_id != "") {
        if (majority_desc == "") {
            push.innerHTML = "<p>majority name must be filled</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (majority_desc != "") {

            var datjson = new Object();
            datjson.company_id = window.localStorage.getItem('company_id');
            datjson.user_id = window.localStorage.getItem('user_id');
            datjson.majority_id = majority_id;
            datjson.majority_desc = majority_desc;           
            $.ajax({
                dataType: "json",
                url: "AddMajority",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        loaddata();
                        alert('success add majority');
                        $('.cd-popup-add-status').removeClass('is-visible');
                        $("#form_box4")[0].reset();
                    } else {
                        alert('Failed add majority : ' + response.resp_desc);
                    }
                }
            });



        }
    }
}

