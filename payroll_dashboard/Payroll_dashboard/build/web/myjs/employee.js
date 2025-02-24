jQuery(document).ready(function ($) {
    var level_desc = window.localStorage.getItem('level_desc');
    if (level_desc !== 'admin' && level_desc !== 'head') {
        window.location.href = "ho";
    }
    loaddata();
});
function loaddata() {
    $('#table_employee').dataTable().fnClearTable();
    $('#table_employee').dataTable().fnDestroy();
    var datjson = new Object();
    datjson.company_id = window.localStorage.getItem('company_id');
    $.ajax({
        contentType: 'application/json',
        dataType: "json",
        url: "Employee",
        data: JSON.stringify(datjson),
        type: 'POST',
        success: function (response) {
            if (response.resp_code == 0000) {
                 if ($('#table_employee thead tr.filters').length === 0) {
                    // create the table header if it doesn't exist
                    $('#table_employee thead tr')
                            .clone(true)
                            .addClass('filters')
                            .appendTo('#table_employee thead');
                }
                var dataemployee = response.list;
                $('#table_employee').DataTable({
                    "scrollX": true,
                    "data": dataemployee,
                    "columns": [
                        {data: "employee_id",
                            "className": 'employee employee_id'},
                        {data: "employee_name",
                            "className": 'employee employee_name'},
                        {data: "birth_date",
                            "className": 'employee birth_date'},
                        {data: "birth_place",
                            "className": 'employee birth_place'},
                        {data: "phonenumber",
                            "className": 'employee phonenumber'},
                        {data: "position_desc",
                            "className": 'employee position_desc'},
                        {data: "resign_status",
                            "className": 'employee resign_status'}
                    ],
                    "createdRow": function (row, data, dataIndex) {
                        if (data.resign_status == '1') {
                            $(row).addClass('red');

                        }
                    },
                    dom: 'Bfrtip',
                    autoClose: true,
                    buttons:
                            [
                                {
                                    extend: "copyHtml5",
                                    title: "list employee",
                                    exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                    footer: true
                                },
                                {
                                    extend: "csvHtml5",
                                    title: "list employee",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "excelHtml5",
                                    title: "list employee",
                                    exportOptions: {columns: ':visible:not()'},
                                    footer: true
                                },
                                {
                                    extend: "pdfHtml5",
                                    title: "list employee",
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