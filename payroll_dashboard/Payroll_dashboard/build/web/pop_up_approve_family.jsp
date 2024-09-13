<%-- 
    Document   : pop_up_approve_family
    Created on : May 30, 2023, 11:08:24 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_timeoff.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_timeoff.js"></script>

<div class="cd-popup-edit-family" role="alert">
    <div class="cd-popup-edit-family-container" style="color: #8915e1;">
        <label id="label_edit_family">Edit Timeoff</label>
        <div id="boxx_edit_family">
            <div id="myModal_edit_family" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_family"></div>
                </div>
            </div>
            <div id="box4_add_family">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100"><b>Code Timeoff</b></label>  <input disabled type="text" id="timeoff_code_edit" name="timeoff_code_edit" required="" placeholder="Code Timeoff">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Timeoff Description</b></label>  <input type="text" id="timeoff_desc_edit" name="timeoff_desc_edit" required="" placeholder="Timeoff Description">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Timeoff Type</b></label>  
                    <select disabled id="absence_type_edit" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="absence_type_edit">
                        <option value="SKT">Sakit</option>
                        <option value="IZN">Izin</option>                        
                        <option value="CUT">Cuti</option>                        
                    </select>
                    <label id="label_timeoff_total_day_edit" style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100;"><b>Timeoff Total Day</b></label>  
                    <input type="number" id="timeoff_total_day_edit" name="timeoff_total_day_edit" required="" placeholder="Timeoff Total Day">
                </form>
            </div>
        </div>
        <div id="buttonon_edit_family">
            <div id="btn_delete" onclick="sub_del_family()">Delete</div>
            <div id="btn_edit_submit_family" onclick="sub_edt_family()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-family-close img-replace-edit-customer">Close</a>
    </div>
</div>
