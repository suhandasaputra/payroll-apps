<%-- 
    Document   : pop_up_edit_religion
    Created on : Feb 20, 2023, 11:08:24 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_religion.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_religion.js"></script>

<div class="cd-popup-edit-status" role="alert">
    <div class="cd-popup-edit-status-container" style="color: #8915e1;">
        <label id="label_edit_status">Edit Religion</label>
        <div id="boxx_edit_status">
            <div id="myModal_edit_status" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_status"></div>
                </div>
            </div>
            <div id="box4_add_status">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Id Religion</label>
                    <input disabled type="text" id="religion_id_edit" name="religion_id_edit" required="" placeholder="Id Religion">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Religion Name</label>
                    <input type="text" id="religion_name_edit" name="religion_name_edit" required="" placeholder="Religion Name">
                                                         
                </form>
            </div>
        </div>
        <div id="buttonon_edit_status">
            <div id="btn_delete" onclick="sub_del_status()">Delete</div>
            <div id="btn_edit_submit_status" onclick="sub_edt_status()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-status-close img-replace-edit-customer">Close</a>
    </div>
</div>
