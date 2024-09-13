<%-- 
    Document   : employee
    Created on : Feb 16, 2023, 9:13:50 AM
    Author     : azzam
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>payroll</title>

        <style>
            <%@include file="/mycss/employee.css"%>
        </style>

        <script type="text/javascript" src="myjs/employee.js"></script>

    </head>
    <body class="hold-transition sidebar-mini">
        <div class="wrapper">
            <!-- Navbar -->
            <%@include file='header.jsp'%>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <%@include file='sidebar_left.jsp'%>
            <!--end sidebar-->
            <div class="content-wrapper">
                <div class="content-header">
                    <section class="content">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <p class="text-left" style="color: #8915e1; font-size: 20px;">
                                                        Employee
                                                    </p>
                                                </div>
                                            </div>
                                            <div id="pushtext2">
                                            </div>
                                            <div class="row">
                                                <div class="tab-content">
                                                    <div id="employee" class="tab-pane fade in active show">
                                                        <div class="container" id="ref_tabel_item">    
                                                            <label id="add_employee"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Create Employee</label>
                                                        </div>
                                                        <br>
                                                        <table class="table" id="table_employee" style="width: 100%; font-size: 12px;">
                                                            <thead>  
                                                                <tr>
                                                                    <th>Employee_id</th>
                                                                    <th>Name</th>
                                                                    <th>Birth_date</th>
                                                                    <th>Birth_place</th>
                                                                    <th>Phone_number</th>
                                                                    <th>Position</th>
                                                                    <th>Resign_Status</th>
                                                                </tr>
                                                            </thead>  
                                                        </table>
                                                        <%@include file='pop_up_add_employee.jsp'%>
                                                        <%@include file='pop_up_edit_employee.jsp'%>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->
                                            <div style="margin-top: 10px;">
                                                
                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: white; border: 1px solid #000; "></span> <a>Active</a>
                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: #ffda00; border: 1px solid #000; margin-left: 10px;"></span> <a>Resign</a>                                                

                                            </div>

                                        </div>                                                                                                                                                           
                                        <!-- ./card-body -->
                                    </div>
                                    <!-- /.card -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </section>
                </div>
            </div>
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>

    </body>
</html>
