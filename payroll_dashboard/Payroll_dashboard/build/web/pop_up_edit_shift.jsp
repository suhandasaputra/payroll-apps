<%-- 
    Document   : pop_up_edit_shift
    Created on : Feb 20, 2023, 11:08:24 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_user.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_shift.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Edit Shift</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Id Shift</label>
                    <input disabled type="text" id="shift_id_edit" name="shift_id_edit" required="" placeholder="Code Shift">                   
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Shift Name</label>
                    <input type="text" id="shift_name_edit" name="shift_name_edit" required="" placeholder="Shift Name">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Start</label>
                    <input type="time" id="start_time_edit" name="start_time_edit" required="">                   
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Finish</label>
                    <input type="time" id="end_time_edit" name="end_time_edit" required="">                                       
                </form>
            </div>
        </div>
        <div id="buttonon_edit_user">
            <div id="btn_delete" onclick="sub_del_shift()">Delete</div>
            <div id="btn_edit_submit_user" onclick="sub_edt_shift()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
