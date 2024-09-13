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
    const today = new Date();
    const currentYear = today.getFullYear();

    $('#table_summary').dataTable().fnClearTable();
    $('#table_summary').dataTable().fnDestroy();


    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    datjson.tahun = currentYear;

    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Summary",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                if ($('#table_summary thead tr.filters').length === 0) {
                    // create the table header if it doesn't exist
                    $('#table_summary thead tr')
                            .clone(true)
                            .addClass('filters')
                            .appendTo('#table_summary thead');
                }
                var datasum = response.list;
                $('#table_summary').DataTable({
                    "scrollX": true,
                    "data": datasum,
                    "columns": [
                        {data: "summary_id",
                            "className": 'summary summary_id'},
                        {data: "employee_id",
                            "className": 'summary employee_id'},
                        {data: "tahun",
                            "className": 'summary tahun'},
                        {data: "hadir",
                            "className": 'summary hadir'},
                        {data: "cuti_list",
                            "className": 'summary cuti_list'},
                        {data: "cuti_bucket",
                            "className": 'summary cuti_bucket'},
                        {data: "cuti_terpakai",
                            "className": 'summary cuti_terpakai'},
                        {data: "cuti_sisa",
                            "className": 'summary cuti_sisa'},
                        {data: "absence",
                            "className": 'summary absence'},
                        {data: "izin",
                            "className": 'summary izin'},
                        {data: "sakit",
                            "className": 'summary sakit'},
                    ],
                    "createdRow": function (row, data, dataIndex) {

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
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list summary",
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

    $('#table_summary').dataTable().fnClearTable();
    $('#table_summary').dataTable().fnDestroy();


    var datjson2 = new Object();
    datjson2.company_id = window.localStorage.getItem('company_id');
    datjson2.tahun = $('#tanggal_summary_from').val();

    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Summary",
        data: JSON.stringify(datjson2),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                if ($('#table_summary thead tr.filters').length === 0) {
                    // create the table header if it doesn't exist
                    $('#table_summary thead tr')
                            .clone(true)
                            .addClass('filters')
                            .appendTo('#table_summary thead');
                }
                var datasum = response.list;
                $('#table_summary').DataTable({
                    "scrollX": true,
                    "data": datasum,
                    "columns": [
                        {data: "summary_id",
                            "className": 'summary summary_id'},
                        {data: "employee_id",
                            "className": 'summary employee_id'},
                        {data: "tahun",
                            "className": 'summary tahun'},
                        {data: "hadir",
                            "className": 'summary hadir'},
                        {data: "cuti_list",
                            "className": 'summary cuti_list'},
                        {data: "cuti_bucket",
                            "className": 'summary cuti_bucket'},
                        {data: "cuti_terpakai",
                            "className": 'summary cuti_terpakai'},
                        {data: "cuti_sisa",
                            "className": 'summary cuti_sisa'},
                        {data: "absence",
                            "className": 'summary absence'},
                        {data: "izin",
                            "className": 'summary izin'},
                        {data: "sakit",
                            "className": 'summary sakit'},
                    ],
                    "createdRow": function (row, data, dataIndex) {

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
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list summary",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list summary",
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

$(document).ready(function () {
    const today = new Date();
    const currentYear = today.getFullYear();
    $(".yearpicker").yearpicker({
        year: currentYear,
        startYear: 2019,
        endYear: 2050,
    });
});