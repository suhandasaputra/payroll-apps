<%-- 
    Document   : pop_up_add_announcement
    Created on : May 24, 2023, 10:51:46 AM
    Author     : azzam
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_announcement.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_announcement.js"></script>


<div class="cd-popup-add-status" role="alert">
    <div class="cd-popup-add-status-container">
        <label id="label_add_status" style="color: #8915e1">Add Announcement</label>
        <div id="boxx_add_status">
            <div id="myModal_add_status" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_status"></div>                   
                </div>
            </div>

            <div id="box4_add_status">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 30px; font-size: 14px; float: left; font-weight: 100"><b>Title</b></label>                      
                    <textarea style="height: 60px;" placeholder="Write Title Here"></textarea>
                    <label style="margin-top: 10px; margin-bottom: 0px; font-size: 14px; float: left; font-weight: 100"><b>Content Description</b></label>  
                    <textarea style="height: 180px;" placeholder="Write Content Here"></textarea>                   
                    <label style="margin-top: 10px; margin-bottom: 0px; font-size: 14px; float: left; font-weight: 100"><b>File (PDF)</b></label> 
                    <input type="file"/>
                  
                </form>
            </div>
        </div>
        <div id="buttonon_add_status" style="margin-top: 50px;">
            <div id="btn_cancel">Cancel</div>
            <div id="btn_submit_status" onclick="sub_out()">Submit</div>
        </div>
        <a href="#0" class="cd-popup-add-status-close img-replace-add-status">Close</a>
    </div>
</div>
