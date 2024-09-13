<%-- 
    Document   : report
    Created on : Feb 24, 2020, 4:38:43 PM
    Author     : suhanda
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
            <%@include file="/mycss/user.css"%>
        </style>
        
        <script type="text/javascript" src="myjs/user.js"></script>

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
                                                        User
                                                    </p>
                                                </div>
                                            </div>
                                            <div id="pushtext2">
                                            </div>
                                            <div class="row">
                                                <div class="tab-content">
                                                    <div id="user" class="tab-pane fade in active show">
                                                        <div class="container" id="ref_tabel_item">    
                                                            <label id="add_user"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Create User</label>
                                                        </div>
                                                        <br>
                                                        <table class="table" id="table_user" style="width: 100%; font-size: 12px;">
                                                            <thead>  
                                                                <tr>
                                                                    <th>User_id</th>
                                                                    <th>Name</th>                                                                    
                                                                    <th>User Level</th>
                                                                    <th>Create Date</th>
                                                                </tr>
                                                            </thead>  
                                                        </table>
                                                        <%@include file='pop_up_add_user.jsp'%>
                                                        <%@include file='pop_up_edit_user.jsp'%>
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