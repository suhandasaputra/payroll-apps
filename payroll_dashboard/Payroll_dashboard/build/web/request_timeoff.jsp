<%-- 
    Document   : request
    Created on : Feb 21, 2023, 12:10:52 PM
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
            <%@include file="/mycss/request_timeoff.css"%>
        </style>

        <script type="text/javascript" src="myjs/request_timeoff.js"></script>

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
                                                        Request Timeoff
                                                    </p>
                                                </div>
                                            </div>
                                            <div id="pushtext2">
                                            </div>
                                            <div class="row">

                                                <div class="tab-content">
                                                    <div id="request" class="tab-pane fade in active show">                                                        
                                                        <div id="bar_item" style="display: inline; color: #8915e1; flex: ">                                                            
                                                            <label style="font-weight: 100; color: black; margin-left: 5px">from</label>
                                                            <input type="text" onfocus="(this.type = 'date')" onblur="(this.type = 'text')" id="tanggal_request_from" class="col-md-3" name="tanggal_item_from" placeholder="Today">
                                                            <label style="font-weight: 100; color: black; margin-left: 5px">to</label>
                                                            <input type="text" onfocus="(this.type = 'date')" onblur="(this.type = 'text')" id="tanggal_request_to" class="col-md-3" name="tanggal_item_to" placeholder="Today">                                                            
                                                            <i class="icon fa fa-search" style="margin-left: 10px; margin-right: 20px; cursor: pointer" onclick="loaddatabydate()"></i>
                                                        </div>
                                                        <br>
                                                        <br>
                                                        <table class="table" id="table_requesttimeoff" style="width: 100%; font-size: 12px;">
                                                            <thead>  
                                                                <tr>
                                                                    <th>Ref</th> 
                                                                    <th>Employee ID</th>                                                                     
                                                                    <th>Description</th>
                                                                    <th>Total Day</th>
                                                                    <th>Request Time</th>
                                                                    <th>Account Id</th>
                                                                    <th>Absence Type</th>
                                                                    <th>Approve By Admin</th>
                                                                    <th>Approve Time Admin</th>
                                                                    <th>Status Admin</th>
                                                                    <th>Approve By Head</th>
                                                                    <th>Approve Time Head</th>
                                                                    <th>Status Head</th>
                                                                </tr>
                                                            </thead>  
                                                        </table>                                                       
                                                        <%@include file='pop_up_approve_request_timeoff.jsp'%>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->

                                            <div style="margin-top: 10px;">
<!--                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: white; border: 1px solid #000;"></span> <a>Live</a>-->
                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: #ffda00; border: 1px solid #000; margin-left: 10px;"></span> <a>Request</a>                                                
                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: #33dd54; border: 1px solid #000; margin-left: 10px;"></span> <a>Approved</a>
                                                <span style="display: inline-block; width: 10px; height: 10px; background-color: #ff3e3e; border: 1px solid #000; margin-left: 10px;"></span> <a>Rejected</a>

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
