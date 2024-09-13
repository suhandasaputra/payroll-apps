/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
jQuery(document).ready(function ($) {
    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin' && level_desc !== 'head') {
        window.location.href = "ho";
    }
    loaddata();
});
function loaddata() {
    var date = new Date();
    var dateString = new Date(date.getTime() - (date.getTimezoneOffset() * 60000))
            .toISOString()
            .split("T")[0];
    
    $('#table_request').dataTable().fnClearTable();
    $('#table_request').dataTable().fnDestroy();
    

    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    datjson.start_date = dateString;
    datjson.end_date = dateString;
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Request",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                var datauser = response.list;
                $('#table_request').DataTable({
                    "scrollX": true,
                    "data": datauser,
                    "columns": [
                        {data: "reference",
                            "className": 'request reference'},
                        {data: "employee_id",
                            "className": 'request employee_id'},
                        {data: "date_absensi",
                            "className": 'request date_absensi'},                        
                        {data: "checkin",
                            "className": 'request checkin'},
                        {data: "checkout",
                            "className": 'request checkout'},
                        {data: "description",
                            "className": 'request description'},
                        {data: "schema",
                            "className": 'request schema'},
                        {data: "date_request",
                            "className": 'request date_request'},
                        {data: "account_id",
                            "className": 'request account_id'},
                        {data: "absence_type",
                            "className": 'request absence_type'},
                        {data: "approve_by_admin",
                            "className": 'request approve_by_admin'},
                        {data: "approve_time_admin",
                            "className": 'request approve_time_admin'},
                        {data: "approve_status_admin",
                            "className": 'request approve_status_admin'},
                        {data: "approve_by_head",
                            "className": 'request approve_by_head'},
                        {data: "approve_time_head",
                            "className": 'request approve_time_head'},
                        {data: "approve_status_head",
                            "className": 'request approve_status_head'}
                    ],
                    "createdRow": function (row, data, dataIndex) {

                        if (data.absence_type == 'REQ' && data.approve_status_head == 'APPROVED') {
                            $(row).addClass('green');
                        } else if (data.absence_type == 'REQ' && data.approve_status_head == 'REJECTED') {
                            $(row).addClass('red');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == 'APPROVED') {
                            $(row).addClass('yellow');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == 'REJECTED') {
                            $(row).addClass('red');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == null) {
                            $(row).addClass('yellow');
                        }
                    },
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
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list request",
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

function loaddatabydate() {

    $('#table_request').dataTable().fnClearTable();
    $('#table_request').dataTable().fnDestroy();


    var datjson2 = new Object();
    datjson2.company_id = window.localStorage.getItem('company_id');
    datjson2.start_date = $('#tanggal_request_from').val();
    datjson2.end_date = $('#tanggal_request_to').val();
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Request",
        data: JSON.stringify(datjson2),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                var datarequest = response.list;
                $('#table_request').DataTable({
                    "scrollX": true,
                    "data": datarequest,
                    "columns": [
                        {data: "reference",
                            "className": 'request reference'},
                        {data: "employee_id",
                            "className": 'request employee_id'},
                        {data: "date_absensi",
                            "className": 'request date_absensi'},                        
                        {data: "checkin",
                            "className": 'request checkin'},
                        {data: "checkout",
                            "className": 'request checkout'},
                        {data: "description",
                            "className": 'request description'},
                        {data: "schema",
                            "className": 'request schema'},
                        {data: "date_request",
                            "className": 'request date_request'},
                        {data: "account_id",
                            "className": 'request account_id'},
                        {data: "absence_type",
                            "className": 'request absence_type'},
                        {data: "approve_by_admin",
                            "className": 'request approve_by_admin'},
                        {data: "approve_time_admin",
                            "className": 'request approve_time_admin'},
                        {data: "approve_status_admin",
                            "className": 'request approve_status_admin'},
                        {data: "approve_by_head",
                            "className": 'request approve_by_head'},
                        {data: "approve_time_head",
                            "className": 'request approve_time_head'},
                        {data: "approve_status_head",
                            "className": 'request approve_status_head'}

                    ],
                    "createdRow": function (row, data, dataIndex) {
                        if (data.absence_type == 'REQ' && data.approve_status_head == 'APPROVED') {
                            $(row).addClass('green');
                        } else if (data.absence_type == 'REQ' && data.approve_status_head == 'REJECTED') {
                            $(row).addClass('red');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == 'APPROVED') {
                            $(row).addClass('yellow');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == 'REJECTED') {
                            $(row).addClass('red');
                        } else if (data.absence_type == 'REQ' && data.approve_status_admin == null) {
                            $(row).addClass('yellow');
                        }
                    },
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
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list request",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list request",
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