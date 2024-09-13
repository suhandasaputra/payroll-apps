<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_schedule.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_schedule.js"></script>

<div class="cd-popup-add-user" role="alert">
    <div class="cd-popup-add-user-container">
        <label id="label_add_user">Add Schedule</label>
        <div class="container" id="ref_tabel_item">    
            <button onclick="window.location.href = 'shift'" id="add_shift"><i class="icon fa fa-plus-circle" style="margin-right: 10px;"></i>Add Shift</button>
        </div>
        <div id="boxx_add_user">
            <div id="myModal_add_user" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="push_text_add_user"></div>
                </div>
            </div>

            <div id="box4_add_user">
                <form id="form_box4">

                    <label style="margin-bottom: 0px; margin-top: 20px; font-size: 10px; float: left; font-weight: 100">Employee Name</label>
                    <select id="schedule_employee" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="schedule_employee">
                    </select>  
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Shift</label>
                    <select id="schedule_shift" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="schedule_shift">
                    </select>                    
                    <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Date</label>
                    <input type="date" id="schedule_date" name="schedule_date" required="" placeholder="Date">
                </form>
            </div>
        </div>

        <div id="buttonon_add_user">
            <div id="btn_cancel">Cancel</div>
            <div id="btn_submit_user" onclick="sub_out()">Submit</div>
        </div>
        <a href="#0" class="cd-popup-add-user-close img-replace-add-user">Close</a>
    </div>
</div>