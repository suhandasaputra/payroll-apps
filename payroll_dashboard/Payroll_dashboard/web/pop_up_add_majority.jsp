<%-- 
    Document   : pop_up_add_shift
    Created on : Feb 20, 2023, 10:51:46 AM
    Author     : azzam
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_majority.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_majority.js"></script>

<div class="cd-popup-add-status" role="alert">
    <div class="cd-popup-add-status-container">
        <label id="label_add_status">Add Majority</label>
        <div id="boxx_add_status">
            <div id="myModal_add_status" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_status"></div>
                </div>
            </div>
            
            <div id="box4_add_status">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Id Majority</label>
                    <input type="text" id="majority_id" name="majority_id" required="" placeholder="ex. SMA02, SJN01, MAG03">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Majority Name</label>
                    <input type="text" id="majority_desc" name="majority_desc" required="" placeholder="ex. Manajemen, IPA, Akuntansi">                    
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
