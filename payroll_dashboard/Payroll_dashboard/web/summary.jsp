<%-- 
    Document   : summary
    Created on : Apr 12, 2023, 9:58:59 AM
    Author     : azzam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>payroll</title>

        <style>
            <%@include file="/mycss/summary.css"%>
        </style>
        <!-- Year Picker CSS -->
        <link rel="stylesheet" href="jquery-year-picker/css/yearpicker.css" />

        <!-- Year Picker Js -->
        <script src="jquery-year-picker/js/yearpicker.js"></script>
        <script type="text/javascript" src="myjs/summary.js"></script>


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
                                                        Summary
                                                    </p>
                                                </div>
                                            </div>
                                            <div id="pushtext2">
                                            </div>
                                            <div class="row">

                                                <div class="tab-content">
                                                    <div id="attendance" class="tab-pane fade in active show">                                                        
                                                        <div id="bar_item" style="display: flex; color: #8915e1; ">                                                            
                                                            <label style="font-weight: 100; color: black; margin-left: 5px; margin-right: 5px;">Select Year :</label>                                                                                                                                                     
                                                            <input style="width: 300px; height: 30px;" type="text" class="yearpicker form-control" onfocus="(this.type = 'date')" onblur="(this.type = 'text')" id="tanggal_summary_from" name="tanggal_item_from" />

                                                            <i class="icon fa fa-search" style="margin-left: 10px; margin-right: 20px; margin-top: 5px; cursor: pointer;" onclick="loaddatabydate()"></i>
                                                        </div>
                                                        <br>                                                        
                                                        <table class="table" id="table_summary" style="width: 100%; font-size: 12px;">
                                                            <thead>  
                                                                <tr>
                                                                    <th>Ref</th> 
                                                                    <th>Employee ID</th> 
                                                                    <th>Year</th>
                                                                    <th>Attend</th>
                                                                    <th>Timeoff List</th>
                                                                    <th>Timeoff Day</th>
                                                                    <th>Timeoff Used</th>
                                                                    <th>Timeoff Remaining</th>                                                                    
                                                                    <th>Absence</th>
                                                                    <th>Permission</th>                                                                    
                                                                    <th>Sick</th>                                                                    
                                                                </tr>
                                                            </thead>  
                                                        </table>                                                       
                                                        <%@include file='pop_up_detail_summary.jsp'%>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->

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

