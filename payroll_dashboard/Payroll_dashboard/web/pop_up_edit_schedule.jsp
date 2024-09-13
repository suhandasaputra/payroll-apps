
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_schedule.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_schedule.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Edit Schedule</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Employee Name</label>
                    <select disabled id="schedule_employee_edit" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="schedule_employee_edit">
                    </select>  
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Shift</label>
                    <select id="schedule_shift_edit" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="schedule_shift_edit">
                    </select>                    
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Date</label>
                    <input disabled type="date" id="schedule_date_edit" name="schedule_date_edit" required="" placeholder="Date">                                                           
                </form>
            </div>
        </div>
        <div id="buttonon_edit_user">
            <div id="btn_delete" onclick="sub_del_sched()">Delete</div>
            <div id="btn_edit_submit_user" onclick="sub_edt_sched()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
