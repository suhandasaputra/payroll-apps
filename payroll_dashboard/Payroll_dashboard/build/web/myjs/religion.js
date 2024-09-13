/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
jQuery(document).ready(function ($) {
    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin') {
        window.location.href = "ho";
    }
    loaddata();
});
function loaddata() {
    $('#table_religion').dataTable().fnClearTable();
    $('#table_religion').dataTable().fnDestroy();
    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Religion",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                var datareligion = response.list;
                $('#table_religion').DataTable({
                    "scrollX": true,
                    "data": datareligion,
                    "columns": [
                        {data: "religion_id",
                            "className": 'religion religion_id'},
                        {data: "religion_name",
                            "className": 'religion religion_name'},
                    ],
                    dom: 'Bfrtip',
//                                buttons: [
//                                    {
//                                        extend: 'collection',
//                                        text: 'Export',
                    autoClose: true,
                    buttons:
                            [
                                {
                                    extend: "copyHtml5",
                                    title: "list religion",
//                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list religion",
//                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list religion",
//                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list religion",
//                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "print",
//                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                }
                            ]
//                                    }
//                                ]
                });
            } else {
                alert('failed retrieve data : ' + response.resp_desc);
            }
        }
    });
}

