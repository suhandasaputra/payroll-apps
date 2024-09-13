<%--
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    .cd-buttons-add-po
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace-add-po {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-add-po {

        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        width: 100%;
        background-color: rgba(94, 110, 141, 0.9);
        opacity: 0;
        visibility: hidden;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        transition: opacity 0.3s 0s, visibility 0s 0.3s;
    }
    .cd-popup-add-po.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-add-po-container {

        position: relative;
        /*width: 90%;*/
        height: 450px;
        max-width: 550px;
        margin: 4em auto;
        background: #FFF;
        border-radius: .25em .25em .4em .4em;
        text-align: center;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        -webkit-transform: translateY(-40px);
        -moz-transform: translateY(-40px);
        -ms-transform: translateY(-40px);
        -o-transform: translateY(-40px);
        transform: translateY(-40px);
        /* Force Hardware Acceleration in WebKit */
        -webkit-backface-visibility: hidden;
        -webkit-transition-property: -webkit-transform;
        -moz-transition-property: -moz-transform;
        transition-property: transform;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        transition-duration: 0.3s;
    }

    .cd-popup-add-po-container .cd-buttons-add-po:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-add-po-container .cd-buttons-add-po li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-add-po-container .cd-buttons-add-po div {
        cursor: pointer;
        display: block;
        height: 60px;
        line-height: 60px;
        text-transform: uppercase;
        color: #FFF;
        -webkit-transition: background-color 0.2s;
        -moz-transition: background-color 0.2s;
        transition: background-color 0.2s;
    }
    .cd-popup-add-po-container .cd-buttons-add-po li:first-child div {
        background: #8915e1;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-add-po-container .cd-buttons-add-po li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-add-po-container .cd-buttons-add-po li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-add-po-container .cd-buttons-add-po li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-add-po-container .cd-buttons-add-po li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-add-po-container .cd-buttons-add-po li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-add-po-container .cd-popup-add-po-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-add-po-container .cd-popup-add-po-close::before, .cd-popup-add-po-container .cd-popup-add-po-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-add-po-container .cd-popup-add-po-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-add-po-container .cd-popup-add-po-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-add-po-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-add-po-container {
            margin: 8em auto;
        }
    }
    #label_add_discount {
        width: 100%;
        margin: 5px;
        text-align: left;
    }
    #boxx_add_discount{
        display: flex;
        justify-content: center;
        align-content: center;
    }


    input {
        padding-left: 10px;
        border: 1px solid #CCC;
        border-radius: 3px;
        width: 100%;
        margin-bottom: 5px;
    }

    #box1_stock {
        width: 215px;
        height: auto;
        margin-left: 5px;
    }




    #table_item_add_po th {
        background-color: #ACACAC;
        color: white;
        font-weight: 100;
    }
    #table_item_add_po {
        margin-top: 15px;
        border-collapse: collapse;
        width: 100%;
        border: 1px solid #ddd;
        font-size: 12px;
    }
    #table_item_add_po th {
        text-align: left;
        /*padding: 12px;*/
    }
    #table_item_add_po td {
        text-align: left;
        /*padding: 12px;*/
        cursor: pointer;
        border: 1px #DDD solid;
    }





    #buttonon_add_po {
        margin-left: 12px;
        float: left;
        color: white;
        margin-top: 10px;
        display: flex;
    }
    #btn_cancel_add_po {
        cursor: pointer;
        width: 85px;
        height: 35px;
        background-color: #a7a0a0;
        border: 1px solid #c52d18;
        margin-right: 10px;
        border-radius: 3px;
        color: #c52d18;

    }
    #btn_save_add_po {
        cursor: pointer;
        width: 85px;
        height: 35px;
        background-color: #8915e1;
        border: 1px solid #CCC;
        margin-left: 10px;
        border-radius: 3px;
    }

    #btn_cancel_add_po:hover {
        background-color: #c52d18;
        color: white;
    }
    #btn_save_add_po:hover {
        background-color: #c52d18;
        color: white;
    }
    #product_add_po{
        margin-bottom: 0px;
        font-size: 12px;
        float: left;
        font-weight: 100;
        width: 215px;
        height: 25px;
    }
    #customer_add_po{
        margin-bottom: 0px;
        font-size: 12px;
        float: left;
        font-weight: 100;
        width: 215px;
        height: 25px;
    }

    #box2_add_po {
        width: 300px;
        height: auto;
        margin-left: 5px;
        /*background-color: yellow;*/
        border-radius: 5px;
    }



    @media only screen and (max-width: 400px) {
        #box1_stock {
            width: 210px;
            height: auto;
            margin-left: 5px;
            /*background-color: blue;*/
        }

        #product_add_po{
            margin-bottom: 0px;
            font-size: 12px;
            float: left;
            font-weight: 100;
            width: 210px;
            height: 25px;
        }
        #customer_add_po{
            margin-bottom: 0px;
            font-size: 12px;
            float: left;
            font-weight: 100;
            width: 210px;
            height: 25px;
        }
        #btn_save_add_po {
            cursor: pointer;
            width: 85px;
            height: 35px;
            background-color: #8915e1;
            border: 1px solid #CCC;
            margin-left: 20px;
            border-radius: 3px;
        }
    }
</style>

<script>
    $('#table_production3').on('click', 'td', function () {
        $('#table_production_done').dataTable().fnClearTable();
        $('#table_production_done').dataTable().fnDestroy();
        var user_id = window.localStorage.getItem('user_id');
        var $row = $(this).closest("tr");
        var tanggal = $row.find(".tanggal").text();
        var batch = $row.find(".batch").text();
        var sts = $row.find(".status_desc").text();

        if (sts == 'on process') {
            document.getElementById("tanggal").value = tanggal;
            document.getElementById("batch").value = batch;


            var datjson = new Object();
            datjson.user_id = user_id;
            datjson.tanggal = tanggal;
            datjson.batch = batch;

            $.ajax({
                dataType: "json",
                url: "getspeprocess",
                data: JSON.stringify(datjson),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        var datauser1 = response.list_process;
                        $('#table_production_done').DataTable({
                            searching: false,
                            paging: false,
                            info: false,
                            "data": datauser1,
                            "columns": [
                                {data: "product", className: 'product'},
                                {data: "jumlah", className: 'jumlah'}
                            ],
                            "order": [1, 'desc']
                        });
                    } else {
                        alert('failed retrieve data : ' + response.resp_desc);
                    }
                }
            });

//            $('#table_production_done').DataTable({
//                searching: false,
//                paging: false,
//                info: false,
//                "data": datauser1,
//                "columns": [
//                    {data: "product", className: 'product'},
//                    {data: "jumlah", className: 'jumlah'}
//                ],
//                "order": [1, 'desc']
//
//            });



            $('.cd-popup-add-po').addClass('is-visible');
        }
    });


    jQuery(document).ready(function ($) {
        //close popup provide password
        $('.cd-popup-add-po').on('click', function (event) {
            if ($(event.target).is('.cd-popup-add-po-close') || $(event.target).is('.cd-popup-add-po') || $(event.target).is('#btn_cancel_add_po')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
                $("#form_add_po")[0].reset();
            }
        });
        //close popup when clicking the esc keyboard button provide password
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-add-po').removeClass('is-visible');
            }
        });
    });
</script>

<div class="cd-popup-add-po" role="alert">
    <div class="cd-popup-add-po-container" style="color: #8915e1;">
        <label id="label_add_discount">Production Process</label>
        <br><br>
        <div id="boxx_add_discount">
            <div id="box1_stock">
                <form id="form_add_po">
                    <label style="font-size: 12px; font-weight: 100; float: left; margin-bottom: 0px; margin-left: 10px;">Date</label>
                    <input type="text" style="width: auto;" id="tanggal" name="tanggal" readonly="" disabled="">

                    <label style="font-size: 12px; font-weight: 100; float: left; margin-bottom: 0px; margin-left: 10px;">Batch</label>
                    <input type="text" style="width: auto;" id="batch" name="batch" readonly="" disabled=""><br>
                </form>
                <br>
                <div id="buttonon_add_po">
                    <div id="btn_cancel_add_po">Cancel</div>
                    <div id="btn_save_add_po" onclick="sub_done_process()">Create</div>
                </div>
                <div id="msgbox"></div>
            </div>
            <div id="box2_add_po">
                <div class="container" id="ref_tabel_item" style="height: 350px; overflow-y: auto;">        
                    <table class="table" id="table_production_done">
                        <thead style="top: 0; position: sticky">  
                            <tr>
                                <th>product</th>
                                <th>jumlah</th>
                            </tr>
                        </thead> 
                        <tbody id="tbody_create_po">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-add-po-close img-replace-add-po">Close</a>
    </div>
</div>
<script>
    function sub_done_process() {
        var user_id = window.localStorage.getItem('user_id');

        var tanggal = document.getElementById("tanggal").value;
        var batch = document.getElementById("batch").value;

        var modal = document.getElementById("myModal_add_po");
        var span = document.getElementsByClassName("close_add_po")[0];
        var push = document.getElementById("push_text_add_po");
        ;

        if (tanggal == "") {
            push.innerHTML = "<p>tanggal must be fill</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (tanggal != "") {
            if (batch == "") {
                push.innerHTML = "<p>batch must be fill</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (batch != "") {
                var datjson = new Object();
                datjson.user_id = user_id;
                datjson.tanggal = tanggal;
                datjson.batch = batch;
                $.ajax({
                    dataType: "json",
                    url: "app_prod",
                    data: JSON.stringify(datjson),
                    type: 'post',
                    success: function (response) {
                        if (response.resp_code == 0000) {
                            alert('success create Batch');
                            $("#form_add_po")[0].reset();
                            $('.cd-popup-add-po').removeClass('is-visible');

                            loaddata();
                        } else {
                            alert('failed approve production : ' + response.resp_desc);
                        }
                    }
                });
            }
        }
    }
</script>