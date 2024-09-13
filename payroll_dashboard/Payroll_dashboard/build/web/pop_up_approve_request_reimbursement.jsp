<%-- 
    Document   : pop_up_approve_request_reimbursement
    Created on : May 22, 2023, 10:01:33 AM
    Author     : azzam
--%>

<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_approve_request_reimbursement.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_approve_request_reimbursement.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Detail Request Reimbursement</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box3_edit_user">
                <form id="form_box3">

                    <label style="margin-bottom: 0px; margin-top: 40px; font-size: 10px; float: left; font-weight: 100"><b>Ref</b></label>
                    <input disabled type="text" id="reference_detail_request" name="reference_detail_request" required="" placeholder="Ref">                   
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Employee Id</b></label>
                    <input disabled type="text" id="employee_id_detail_request" name="employee_id_detail_request" required="" placeholder="Employee Id">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Expense Date</b></label>
                    <input disabled type="text" id="expense_date_detail_request" name="expense_date_detail_request" required="">                   
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Reimbursement Type</b></label>
                    <input disabled type="text" id="reimbursement_type_detail_request" name="reimbursement_type_detail_request" required="">                                       
                </form>
            </div>
            <div id="box4_edit_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 40px; font-size: 10px; float: left; font-weight: 100"><b>Request Time</b></label>
                    <input disabled type="text" id="date_request_detail_request" name="date_request_detail_request" required="">                   
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Amount</b></label>
                    <input disabled type="text" id="amount_detail_request" name="amount_detail_request" required="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Description</b></label>
                    <input disabled type="text" id="desc_detail_request" name="desc_detail_request" required="">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Expense Receipt</b></label>
                    <button id="imagebutton"><i class="nav-icon fas fa-image"></i> Image</button>                 
                    <div id="popup" class="popup">
                        <div class="popup-content">
                            <span class="close" onclick="closePopup()">&times;</span>
                            <h4>Expense Receipt</h4>
                            <img id="bukti_pengajuan" src="" alt="Expense Receipt">
                        </div>
                    </div>                                      
                </form>
            </div>            
        </div>
        <div style="margin-top: 5px; margin-bottom: 5px; align-items: flex-start;">
            <label style="margin-right: 50px; font-size: 12px;"><b>Head Notes :</b></label>
            <label style="margin-left: 90px; font-size: 12px;"><b>Admin Notes :</b></label>
        </div>
        <div style="margin-bottom: 10px; margin-top: 5px;">
            <form id="form_box5">                        
                <textarea id="messages_head_detail_request" style="width: 200px; height: 100px; margin-left: 20px; margin-right: 20px;" placeholder="Write Messages Here"></textarea>
                <textarea id="messages_admin_detail_request" style="width: 200px; height: 100px; margin-left: 20px; margin: auto;" placeholder="Write Messages Here"></textarea>                    
            </form>
        </div>
        <div id="buttonon_edit_user">
            <div id="btn_delete" onclick="sub_reject()">Reject</div>
            <div id="btn_edit_submit_user" onclick="sub_approve()">Approve</div>
            <div style="visibility: hidden; display: none;" id="btn_cancel">Close</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
