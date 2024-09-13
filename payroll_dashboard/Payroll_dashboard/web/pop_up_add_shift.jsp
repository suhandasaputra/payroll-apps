<%-- 
    Document   : pop_up_add_shift
    Created on : Feb 20, 2023, 10:51:46 AM
    Author     : azzam
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_shift.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_shift.js"></script>

<div class="cd-popup-add-user" role="alert">
    <div class="cd-popup-add-user-container">
        <label id="label_add_user">Add Shift</label>
        <div id="boxx_add_user">
            <div id="myModal_add_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_user"></div>
                </div>
            </div>
            
            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 80px; font-size: 10px; float: left; font-weight: 100">Id Shift</label>
                    <input type="text" id="shift_id" name="shift_id" required="" placeholder="ex. SHIFT01, SHIFT04">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Shift Name</label>
                    <input type="text" id="shift_name" name="shift_name" required="" placeholder="ex. Shift Pagi, Shift Malam">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Start</label>
                    <input type="time" id="start_time" name="start_time" required="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Finish</label>
                    <input type="time" id="end_time" name="end_time" required="">
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
