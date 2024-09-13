
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>payroll</title>
        
        <script type="text/javascript" src="myjs/account.js"></script>
        
        <style>
            <%@include file="/mycss/account.css"%>
        </style>
        
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
                                                        Account
                                                    </p>
                                                </div>
                                            </div>
                                            <!--                                            <div class="row">
                                                                                            <ul class="nav nav-tabs topnav" id="myTab">
                                                                                                <li><a data-toggle="tab" href="#profile" class="active">Profile</a></li>
                                                                                            </ul>
                                                                                        </div>-->
                                            <div class="row">
                                                <div id="account_body" class="tab-content" style="width: 100%">
                                                    <div id="account_right">
                                                        <div id="account_biodata">
                                                            Biodata<br>
                                                            <label style="color: #CCC; font-size: 16px; font-weight: 500">Full Name</label>
                                                            <input type="text" id="full_name" readonly="" disabled="">
<!--                                                            <label id="add_account_biodata">Edit</label>-->
                                                            <br>
                                                        </div>
                                                        <div id="account_contact">
                                                            Contact<br>
                                                            <label style="color: #CCC;  font-size: 16px; font-weight: 500">Email</label>
                                                            <input type="text" id="email" readonly="" disabled=""><br>
<!--                                                            <label style="color: #CCC;  font-size: 16px; font-weight: 500">Phone</label>-->
<!--                                                            <input type="text" id="phone" readonly="" disabled="">
                                                            <label id="edit_account_phone">Edit</label><br>-->
                                                        </div>
                                                        <label id="edit_password" style="cursor: pointer">Change Password</label>
                                                    </div>
                                                    <%--<%@include file='pop_up_edit_biodata.jsp'%>--%>
                                                    <%--<%@include file='pop_up_edit_phone.jsp'%>--%>
                                                    <%@include file='pop_up_edit_password.jsp'%>
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
