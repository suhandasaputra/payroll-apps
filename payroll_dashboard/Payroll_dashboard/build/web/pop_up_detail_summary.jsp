<%-- 
    Document   : pop_up_detail_attendance
    Created on : Mar 3, 2023, 10:01:33 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_detail_summary.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_detail_summary.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Detail Summary</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box1_edit_user">
                <form id="form_box_1_edit_user">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Reference</b></label><input type="text" id="summary_id" name="summary_id" required="" disabled="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Employee Id</b></label><input type="text" id="employee_id" name="employee_id" required="" disabled="">                                       
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Year</b></label><input type="text" id="tahun" name="tahun" required="" disabled="">                    


                </form>
            </div>
            <div id="box2_edit_user">
                <form id="form_box_2_edit_user">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Attend</b></label><input type="text" id="hadir" name="hadir" required="" disabled="">                 
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Permission</b></label><input type="text" id="izin" name="izin" required="" disabled>
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Sick</b></label><input type="text" id="sakit" name="sakit" required="" disabled="">

                </form>
            </div>
            <div id="box3_edit_user">
                <form id="form_box_3_edit_user">
                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Absence</b></label><input type="text" id="absence" name="absence" required="" disabled="">                 

                </form>
            </div>

        </div>
        <div>
            <table id="myTable">
                <!-- The table will be created dynamically using JavaScript -->
            </table>

        </div>

        <div id="buttonon_edit_user">            
            <div id="btn_close">Close</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
