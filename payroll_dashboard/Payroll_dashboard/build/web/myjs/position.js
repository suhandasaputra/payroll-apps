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
    loaddata2();
});
function loaddata() {
    $('#table_position').dataTable().fnClearTable();
    $('#table_position').dataTable().fnDestroy();
    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Position",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                var dataposition = response.list;
                $('#table_position').DataTable({
                    "scrollX": true,
                    "data": dataposition,
                    "columns": [
                        {data: "position_id",
                            "className": 'position position_id'},
                        {data: "position_desc",
                            "className": 'position position_desc'},
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
                                    title: "list shift",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list shift",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list shift",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list shift",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "print",
                                    exportOptions: {columns: ':visible:not()'},
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

function loaddata2() {
    $('#table_level').dataTable().fnClearTable();
    $('#table_level').dataTable().fnDestroy();
    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "PositionLevel",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                var dataposition = response.list;
                $('#table_level').DataTable({
                    "scrollX": true,
                    "data": dataposition,
                    "columns": [
                        {data: "position_id",
                            "className": 'level position_id'},
                        {data: "position_level",
                            "className": 'level position_level'},
                        {data: "level_desc",
                            "className": 'level level_desc'},
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
                                    title: "list position",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list position",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list position",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list position",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "print",
                                    exportOptions: {columns: ':visible:not()'},
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

