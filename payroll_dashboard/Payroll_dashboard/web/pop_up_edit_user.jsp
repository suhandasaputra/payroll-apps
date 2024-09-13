
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_user.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_user.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1#8915e1;">
        <label id="label_edit_user" style="color: #8915e1">Edit User</label>
        <div id="boxx_edit_user">
            <div id="myModal_edit_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_edit_user"></div>
                </div>
            </div>
            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 100px; font-size: 10px; float: left; font-weight: 100">Account Id</label>
                    <input disabled type="text" id="account_id_edit_usr" name="account_id" required="" placeholder="email">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Full Name</label>
                    <input type="text" id="account_name_edit_usr" name="account_name" required="" placeholder="Full Name">
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Account Level</label>
                    <select id="account_level_edit_usr" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="account_level">
                    </select>                                        
                </form>
            </div>
        </div>
        <div id="buttonon_edit_user">
            <div id="btn_delete" onclick="sub_del_user()">Delete</div>
            <div id="btn_edit_submit_user" onclick="sub_edt_user()">Save</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>
