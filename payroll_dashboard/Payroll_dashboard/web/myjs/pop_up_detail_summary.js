//open pop up detail summary
$('#table_summary').on('click', 'td', function () {

    var $row = $(this).closest("tr");
    var employee_id = $row.find(".employee_id").text();
    var tahun = $row.find(".tahun").text();
    var company_id = localStorage.getItem('company_id');

    var datjson = new Object();
    datjson.company_id = company_id;
    datjson.tahun = tahun;
    datjson.employee_id = employee_id;
    $.ajax({
        dataType: "json",
        url: "GetDetailSummary",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (resp) {
            if (resp.resp_code == 0000) {
                document.getElementById("summary_id").value = resp.summary_id;
                document.getElementById("employee_id").value = resp.employee_id;
                document.getElementById("tahun").value = resp.tahun;
                document.getElementById("hadir").value = resp.hadir;
                document.getElementById("izin").value = resp.izin;
                document.getElementById("sakit").value = resp.sakit;
                document.getElementById("absence").value = resp.absence;

                // Get a reference to the table element in the HTML document
                var table = document.getElementById("myTable");

                // Clear the existing table
                table.innerHTML = "";

                var cuti_desc = resp.cuti_desc;
                var cuti_list = resp.cuti_list;
                var cuti_bucket = resp.cuti_bucket;
                var cuti_terpakai = resp.cuti_terpakai;
                var cuti_sisa = resp.cuti_sisa;
                const cuti_desc_arr = cuti_desc.split('|');
                const cuti_list_arr = cuti_list.split('|');
                const cuti_bucket_arr = cuti_bucket.split('|');
                const cuti_terpakai_arr = cuti_terpakai.split('|');
                const cuti_sisa_arr = cuti_sisa.split('|');
                
                const total_cols = cuti_bucket_arr.length;

                // Define the number of rows and columns
                var num_rows = 4;
                var num_cols = total_cols;

                // Define the headers for the rows and columns
                var row_headers = ["Timeoff Desc", "Timeoff Day", "Timeoff Used", "Timeoff Remaining"];
                var col_headers = cuti_list_arr;

                // Define the data to fill the cells with
                var data = [cuti_desc_arr,
                    cuti_bucket_arr,
                    cuti_terpakai_arr,
                    cuti_sisa_arr
                ];

                // Create the header row
                var header_row = "<tr><th></th>";

                // Loop through the column headers and add a header cell for each column
                for (var i = 0; i < num_cols; i++) {
                    header_row += "<th>" + col_headers[i] + "</th>";
                }

                header_row += "</tr>";

                // Add the header row to the table
                table.innerHTML += header_row;

                // Loop through the rows and create a new row for each row
                for (var i = 0; i < num_rows; i++) {
                    var row = "<tr><th>" + row_headers[i] + "</th>";

                    // Loop through the columns and add a new cell for each column
                    for (var j = 0; j < num_cols; j++) {
                        var value = data[i][j];
                        if (i === 1 || i === 0 || j >= cuti_list_arr.length) {
                            row += "<td>" + value + "</td>";
                        } else {
                            row += "<td contenteditable='true'>" + value + "</td>";
                        }
                    }

                    row += "</tr>";
                    // Add the row to the table
                    table.innerHTML += row;
                }

                var level_desc = window.localStorage.getItem('level_desc');
                if (level_desc !== 'head') {

                    $('.cd-popup-edit-user').addClass('is-visible');

                } else if (level_desc == 'head') {
                    $('.cd-popup-edit-user').addClass('is-visible');
                }

            } else {
                alert('Failed get detail : ' + resp.resp_desc);
            }
        }
    });

});

jQuery(document).ready(function ($) {
    //close popup provide password
    $('.cd-popup-edit-user').on('click', function (event) {
        if ($(event.target).is('.cd-popup-edit-user-close') || $(event.target).is('.cd-popup-edit-user') || $(event.target).is('#btn_close')) {
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

