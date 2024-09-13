
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_user.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_user.js"></script>

<div class="cd-popup-add-user" role="alert">
    <div class="cd-popup-add-user-container">
        <label id="label_add_user" style="color: #8915e1">Add User</label>
        <div id="boxx_add_user">
            <div id="myModal_add_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_user"></div>
                </div>
            </div>
            
            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 80px; font-size: 10px; float: left; font-weight: 100">Employee</label>  <select id="account_id" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="account_id">   </select>
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Full Name</label>  <input type="text" id="account_name" name="account_name" required="" placeholder="Full Name">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Account Level</label>  <select id="account_level" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="account_level">   </select>                    
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Password</label>  <input type="password" id="password" name="school" required="" placeholder="******">
                </form>
            </div>
        </div>
        <div id="buttonon_add_user">
            <div id="btn_cancel">Cancel</div>
            <div id="btn_submit_user" onclick="sub_out()">Submit</div>
        </div>
        <a href="#0" class="cd-popup-add-user-close img-replace-add-user">Close</a>
    </div>
</div>