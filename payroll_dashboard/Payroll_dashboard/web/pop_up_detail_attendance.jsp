<%-- 
    Document   : pop_up_detail_attendance
    Created on : Mar 3, 2023, 10:01:33 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_detail_attendance.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_detail_attendance.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Detail Attendance</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box1_edit_user">
                <form id="form_box_1_edit_user">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Reference</b></label><input type="text" id="reference" name="reference" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Employee Id</b></label><input type="text" id="employee_id" name="employee_id" required="" disabled="">                                       
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Account Id</b></label><input type="text" id="account_id" name="account_id" required="" disabled="">                    
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Absence Type</b></label><input type="text" id="absence_type" name="absence_type" required="" disabled="">                 
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Shift</b></label><input type="text" id="shift" name="shift" required="" disabled>
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Schedule Working Hour</b></label><input type="text" id="schedule_working_hour" name="schedule_working_hour" required="" disabled="">

                </form>
            </div>
            <div id="box2_edit_user">
                <form id="form_box_2_edit_user">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Schedule In</b></label><input type="text" id="schedule_in" name="schedule_in" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Schedule Out</b></label><input type="text" id="schedule_out" name="schedule_out" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Check In</b></label><input type="text" id="checkin" name="checkin" required="" disabled=""> 
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Description In</b></label><input type="text" id="descriptionin" name="descriptionin" required="" disabled>
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Check Out</b></label><input type="text" id="checkout" name="checkout" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Description Out</b></label><input type="text" id="descriptionout" name="descriptionout" required="" disabled="">
                </form>
            </div>
            <div id="box3_edit_user">
                <form id="form_box3">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Early Out</b></label><input type="text" id="early_out" name="early_out" required="" disabled=""> 
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Date Request Out</b></label><input type="text" id="date_requestout" name="date_requestout" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Overtime Duration Before</b></label><input type="text" id="overtime_duration_before" name="overtime_duration_before" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Overtime Duration After</b></label><input type="text" id="overtime_duration_after" name="overtime_duration_after" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Actual Working Hour</b></label><input type="text" id="actual_working_hour" name="actual_working_hour" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Location Out</b></label><input type="text" id="locationout" name="locationout" required="" readonly="" disabled="">

                </form>
            </div>
            <div id="box4_edit_user">
                <form id="form_box4">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Late In</b></label><input type="text" id="late_in" name="late_in" required="" disabled=""> 
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Real Working Hour</b></label><input type="text" id="real_working_hour" name="real_working_hour" required="" disabled="">                    
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Date Request In</b></label><input type="text" id="date_requestin" name="date_requestin" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Location In</b></label><input type="text" id="locationin" name="locationin" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Date Absensi</b></label><input type="text" id="date_absensi" name="date_absensi" required="" disabled="">                                                                                                 
                    
                </form>
            </div>            
        </div>

        <div id="buttonon_edit_user">            
            <div id="btn_edit_submit_user">Close</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
