<%-- 
    Document   : pop_up_edit_shift
    Created on : Feb 20, 2023, 11:08:24 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_position_level.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_position_level.js"></script>

<div class="cd-popup-edit-level" role="alert">
    <div class="cd-popup-edit-level-container" style="color: #8915e1;">
        <label id="label_edit_level">Edit Level</label>
        <div id="boxx_edit_level">
            <div id="myModal_edit_level" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_level"></div>
                </div>
            </div>
            <div id="box4_add_level">
                <form id="form_box4_edit">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Position</label>
                    <select disabled id="add_position_id_edit" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="add_position_id">
                    </select>
                    <label style="margin-bottom: 0px;  font-size: 10px; float: left; font-weight: 100">Id Level</label>
                    <input disabled type="text" id="position_level_edit" name="position_level" required="" placeholder="Id Level">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Name</label>
                    <input type="text" id="level_desc_edit" name="level_desc" required="" placeholder="Level Name">
                                                         
                </form>
            </div>
        </div>
        <div id="buttonon_edit_level">
            <div id="btn_delete" onclick="sub_del_level()">Delete</div>
            <div id="btn_edit_submit_level" onclick="sub_edt_level()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-level-close img-replace-edit-customer">Close</a>
    </div>
</div>
