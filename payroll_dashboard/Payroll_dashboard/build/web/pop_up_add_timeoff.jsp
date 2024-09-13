<%-- 
    Document   : pop_up_add_shift
    Created on : Feb 20, 2023, 10:51:46 AM
    Author     : azzam
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_timeoff.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_timeoff.js"></script>

<div class="cd-popup-add-status" role="alert">
    <div class="cd-popup-add-status-container">
        <label id="label_add_status" style="color: #8915e1">Add Timeoff</label>
        <div id="boxx_add_status">
            <div id="myModal_add_status" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_status"></div>
                </div>
            </div>
            
            <div id="box4_add_status">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Code Timeoff</label>  <input type="text" id="timeoff_code" name="timeoff_code" required="" placeholder="Code Timeoff">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Timeoff Description</label>  <input type="text" id="timeoff_desc" name="timeoff_desc" required="" placeholder="Timeoff Description">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Timeoff Type</label>  
                    <select id="absence_type" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="absence_type" onchange="showTimeoffFields()">
                        <option value="SKT">SKT</option>
                        <option value="IZN">IZN</option>                        
                        <option value="CUT">CUT</option>                        
                    </select>
                    <label id="label_timeoff_total_day" style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100; display: none;">Timeoff Total Day</label>  <input style="display: none;" type="number" id="timeoff_total_day" name="timeoff_total_day" required="" placeholder="Timeoff Total Day">
                </form>
            </div>
        </div>
        <div id="buttonon_add_status">
            <div id="btn_cancel">Cancel</div>
            <div id="btn_submit_status" onclick="sub_out()">Submit</div>
        </div>
        <a href="#0" class="cd-popup-add-status-close img-replace-add-status">Close</a>
    </div>
</div>
