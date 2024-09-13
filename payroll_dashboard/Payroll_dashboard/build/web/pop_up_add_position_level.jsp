<%-- 
    Document   : pop_up_add_position
    Created on : Feb 20, 2023, 10:51:46 AM
    Author     : azzam
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_position_level.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_position_level.js"></script>

<div class="cd-popup-add-level" role="alert">
    <div class="cd-popup-add-level-container">
        <label id="label_add_level">Add Position Level</label>
        <div id="boxx_add_level">
            <div id="myModal_add_level" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_level"></div>
                </div>
            </div>

            <div id="box4_add_level">
                <form id="form_box4_add">
                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Position</label>
                    <select id="add_position_id" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="add_position_id">
                    </select>
                    <label style="margin-bottom: 0px;  font-size: 10px; float: left; font-weight: 100">Id Level</label>
                    <input type="text" id="position_level" name="position_level" required="" placeholder="ex. ADM01, MNJ02, DIR03">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Name</label>
                    <input type="text" id="level_desc" name="level_desc" required="" placeholder="ex. Admin Gudang, Direktur Utama">                    
                </form>
            </div>
        </div>
        <div id="buttonon_add_level">
            <div id="btn_cancel">Cancel</div>
            <div id="btn_submit_level" onclick="sub_out()">Submit</div>
        </div>
        <a href="#0" class="cd-popup-add-level-close img-replace-add-level">Close</a>
    </div>
</div>
