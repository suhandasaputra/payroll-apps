/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
jQuery(document).ready(function ($) {
    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'head' && level_desc !== 'admin') {
        window.location.href = "ho";
    }
    loaddata();
});
function loaddata() {
    var date = new Date();
    var dateString = new Date(date.getTime() - (date.getTimezoneOffset() * 60000))
            .toISOString()
            .split("T")[0];

    $('#table_schedule').dataTable().fnClearTable();
    $('#table_schedule').dataTable().fnDestroy();


    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    datjson.start_date = dateString;
    datjson.end_date = dateString;
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Schedule",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                if ($('#table_schedule thead tr.filters').length === 0) {
                    // create the table header if it doesn't exist
                    $('#table_schedule thead tr')
                            .clone(true)
                            .addClass('filters')
                            .appendTo('#table_schedule thead');
                }
                var datauser = response.list;
                $('#table_schedule').DataTable({
                    "scrollX": true,
                    "data": datauser,
                    "columns": [
                        {data: "employee_id",
                            "className": 'schedule employee_id'},
                        {data: "employee_name",
                            "className": 'schedule employee_name'},
                        {data: "shift_id",
                            "className": 'schedule shift_id'},
                        {data: "shift_name",
                            "className": 'schedule shift_name'},
                        {data: "date",
                            "className": 'schedule date'}
                    ],
                    dom: 'Bfrtip',
                    autoClose: true,
                    buttons:
                            [
                                {
                                    extend: "copyHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "print",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                }
                            ],
                    orderCellsTop: true,
                    fixedHeader: true,

                    initComplete: function () {
                        var api = this.api();
                        var cursorPosition = 0; // Initialize cursorPosition to a default value

                        api.columns().eq(0).each(function (colIdx) {
                            var cell = $('.filters th').eq($(api.column(colIdx).header()).index());
                            var title = $(cell).text();
                            $(cell).html('<input type="text" placeholder="' + title + '" />');

                            $('input', $('.filters th').eq($(api.column(colIdx).header()).index()))
                                    .off('keyup change')
                                    .on('change', function (e) {
                                        $(this).attr('title', $(this).val());
                                        var regexr = '({search})';
                                        cursorPosition = this.selectionStart; // Assign the current cursor position to cursorPosition variable
                                        api.column(colIdx).search(
                                                this.value != '' ?
                                                regexr.replace('{search}', '(((' + this.value + ')))') :
                                                '',
                                                this.value != '',
                                                this.value == ''
                                                ).draw();
                                    })
                                    .on('keyup', function (e) {
                                        e.stopPropagation();

                                        $(this).trigger('change');
                                        $(this).focus()[0].setSelectionRange(cursorPosition, cursorPosition); // Use the cursorPosition variable here
                                    });
                        });
                    }
                });
            } else {
                alert('failed retrieve data : ' + response.resp_desc);
            }
        }
    });
}

function loaddatabydate() {

    $('#table_schedule').dataTable().fnClearTable();
    $('#table_schedule').dataTable().fnDestroy();

    var datjson2 = new Object();
    datjson2.company_id = window.localStorage.getItem('company_id');
    datjson2.start_date = $('#tanggal_schedule_from').val();
    datjson2.end_date = $('#tanggal_schedule_to').val();
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Schedule",
        data: JSON.stringify(datjson2),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                if ($('#table_schedule thead tr.filters').length === 0) {
                    // create the table header if it doesn't exist
                    $('#table_schedule thead tr')
                            .clone(true)
                            .addClass('filters')
                            .appendTo('#table_schedule thead');
                }
                var datauser = response.list;
                $('#table_schedule').DataTable({
                    "scrollX": true,
                    "data": datauser,
                    "columns": [
                        {data: "employee_id",
                            "className": 'schedule employee_id'},
                        {data: "employee_name",
                            "className": 'schedule employee_name'},
                        {data: "shift_id",
                            "className": 'schedule shift_id'},
                        {data: "shift_name",
                            "className": 'schedule shift_name'},
                        {data: "date",
                            "className": 'schedule date'}
                    ],
                    dom: 'Bfrtip',
                    autoClose: true,
                    buttons:
                            [
                                {
                                    extend: "copyHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list schedule",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "print",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                }
                            ],
                    orderCellsTop: true,
                    fixedHeader: true,

                    initComplete: function () {
                        var api = this.api();
                        var cursorPosition = 0; // Initialize cursorPosition to a default value

                        api.columns().eq(0).each(function (colIdx) {
                            var cell = $('.filters th').eq($(api.column(colIdx).header()).index());
                            var title = $(cell).text();
                            $(cell).html('<input type="text" placeholder="' + title + '" />');

                            $('input', $('.filters th').eq($(api.column(colIdx).header()).index()))
                                    .off('keyup change')
                                    .on('change', function (e) {
                                        $(this).attr('title', $(this).val());
                                        var regexr = '({search})';
                                        cursorPosition = this.selectionStart; // Assign the current cursor position to cursorPosition variable
                                        api.column(colIdx).search(
                                                this.value != '' ?
                                                regexr.replace('{search}', '(((' + this.value + ')))') :
                                                '',
                                                this.value != '',
                                                this.value == ''
                                                ).draw();
                                    })
                                    .on('keyup', function (e) {
                                        e.stopPropagation();

                                        $(this).trigger('change');
                                        $(this).focus()[0].setSelectionRange(cursorPosition, cursorPosition); // Use the cursorPosition variable here
                                    });
                        });
                    }
                });
            } else {
                alert('failed retrieve data : ' + response.resp_desc);
            }
        }
    });
}

