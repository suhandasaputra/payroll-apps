
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_edit_employee.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_edit_employee.js"></script>

<div class="cd-popup-edit-user" role="alert">
    <div class="cd-popup-edit-user-container" style="color: #8915e1;">
        <label id="label_edit_user">Edit Employee (<span style="color: #C780FA;" id="employee_id_title"></span>)</label>


        <div class="tab-edit">
            <button class="tablinks" onclick="openCity(event, 'Personal')" id="defaultOpen">Personal</button>
            <button class="tablinks" onclick="openCity(event, 'Career')">Career</button>
            <button class="tablinks" onclick="openCity(event, 'Family')">Family</button>
            <button class="tablinks" onclick="openCity(event, 'Reimbursement')">Reimbursement</button>
            <button class="tablinks" onclick="openCity(event, 'Emergency_contact')">Emergency Contact</button>
        </div>

        <div id="Personal" class="tabcontent">
            <div id="boxx_edit_user">
                <div id="myModal_edit_user" class="modal">
                    <div class="modal-content">
                        <span class="close_edit_user">&times;</span>
                        <div id="push_text_edit_user"></div>
                    </div>
                </div>
                <div id="box1_edit_user">
                    <form id="form_box_1_edit_user">
                        <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Employee ID</b></label><input type="text" id="employee_id_edit_user" name="employee_id_edit_user" required="" placeholder="employee_id" disabled>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Full name</b></label><input type="text" id="employee_name_edit_user" name="employee_name_edit_user" required="" placeholder="full_name">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Birth place</b></label><input type="text" id="birth_place_edit_user" name="birth_place_edit_user" required="" placeholder="birth_place">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Birth date</b></label><input type="date" id="birth_date_edit_user" name="birth_date_edit_user" required="">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Phone number</b></label><input type="number" id="phonenumber_edit_user" name="phonenumber_edit_user" required="" placeholder="phonenumber">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Email</b></label><input type="email" id="email_edit_user" name="email_edit_user" required="" placeholder="email"  readonly="" disabled="">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Join Date</b></label><input type="date" id="join_date_edit_user" name="join_date_edit_user" required="" disabled>

                    </form>
                </div>
                <div id="box2_edit_user">
                    <form id="form_box_2_edit_user">
                        <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Length Of Work (Year)</b></label><input type="text" id="length_of_work_edit_user" name="length_of_work_edit_user" required="" placeholder="length of work" disabled>                                   
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>NIK</b></label><input type="text" id="nik_edit_user" name="nik_edit_user" required="" placeholder="nik">                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>KTP Address</b></label><input type="text" id="ktp_address_edit_user" name="ktp_address_edit_user" required="" placeholder="ktp_address">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Code Pos</label><input type="text" id="code_pos_edit_user" name="code_pos_edit_user" required="" placeholder="code_pos">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Npwp</b></label><input type="text" id="npwp_edit_user" name="npwp_edit_user" required="" placeholder="npwp">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Npwp Address</b></label><input type="text" id="npwp_address_edit_user" name="npwp_address_edit_user" required="" placeholder="npwp_address">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>BPJS TK Number</b></label><input type="text" id="bpjs_tk_number_edit_user" name="bpjs_tk_number_edit_user" required="" placeholder="bpjs_tk_number">


                    </form>
                </div>
                <div id="box3_edit_user">
                    <form id="form_box_3_edit_user">
                        <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Efin</b></label><input type="text" id="efin_edit_user" name="efin_edit_user" required="" placeholder="efin">      
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Kpp</b></label><input type="text" id="kpp_edit_user" name="kpp_edit_user" required="" placeholder="kpp">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Employee Status</b></label>  <select id="employee_status_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="employee_status_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Position Category</b></label>  <select id="position_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="position_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Position Level</b></label>  <select id="level_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="level_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Gender</label>
                        <select id="gender_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="gender_edit_user">                        
                            <option value="Laki-laki">Laki-laki</option>
                            <option value="Perempuan">Perempuan</option> 
                        </select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Blood Type</label>
                        <select id="blood_type_edit_user" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="blood_type_edit_user">                        
                            <option value="A">A</option>
                            <option value="B">B</option>                        
                            <option value="O">O</option> 
                            <option value="AB">AB</option> 
                        </select>
                    </form>
                </div>
                <div id="box4_edit_user">
                    <form id="form_box_4_edit_user">
                        <label style="margin-bottom: 0px; margin-top: 30px; font-size: 10px; float: left; font-weight: 100"><b>Religion</b></label>  <select id="religion_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="religion_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Last Education</b></label>  <select id="last_education_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="last_education_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>School</b></label> <input type="text" id="school_edit_user" name="school_edit_user" required="" placeholder="school">                
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Majority</b></label>  <select id="majority_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="majority_edit_user"></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Marital Status</b></label>  <select id="marital_status_edit_user" style="width: 215px; padding: 0px; margin-bottom: 5px;" name="marital_status_edit_user"></select>
                        <label id="resign_status_edit_user_label" style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Resign Status</b></label><input disabled type="text" id="resign_status_edit_user" name="resign_status_edit_user" required="" placeholder="resign_status">
                        <label id="out_date_edit_user_label" style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Out Date</b></label><input disabled type="date" id="out_date_edit_user" name="out_date_edit_user" required="" placeholder="out_date">

                    </form>
                </div>
            </div>
        </div>

        <div id="Career" class="tabcontent">
            <div style="height: 400px;">
                <table class="table" id="table_employee_career" style="width: 100%; font-size: 12px;">
                    <thead>  
                        <tr>
                            <th>Ref</th>
                            <th>Company Name</th>
                            <th>Business Sector</th>
                            <th>Position</th>
                            <th>Division</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Status</th>
                        </tr>
                    </thead>  
                </table>       
            </div>     
        </div>

        <div id="Family" class="tabcontent">
            <div style="height: 400px;">
                <table class="table" id="table_employee_family" style="width: 100%; font-size: 12px;">
                    <thead>  
                        <tr>
                            <th>Ref</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Birth Date</th>
                            <th>Relation</th>
                            <th>Status</th>                            
                        </tr>
                    </thead>  
                </table>       
            </div> 
        </div>

        <div id="Reimbursement" class="tabcontent">
            <div id="boxx_edit_user4">            
                <div id="box9_edit_user">

                    <form id="form_box_9_edit_user">
                        <!--judul--><label style="display: block; text-align: left; margin-top: 20px; font-size: 14;"><b>Business travel costs</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100"><b>Maximum Limit</b></label><input type="number" id="trv_plafon" name="trv_plafon" required="">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Used</b></label><input type="text" id="trv_terpakai" name="trv_terpakai" required="" disabled>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Remaining</b></label><input type="text" id="trv_sisa" name="trv_sisa" required="" disabled>
                        <!--judul--><label style="display: block; text-align: left; margin-top: 10px; font-size: 14;"><b>Education or training</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100;"><b>Maximum Limit</b></label><input type="number" id="edu_plafon" name="edu_plafon" required="">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Used</b></label><input type="text" id="edu_terpakai" name="edu_terpakai" required="" disabled>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Remaining</b></label><input type="text" id="edu_sisa" name="edu_sisa" required="" disabled>

                    </form>
                </div>
                <div id="box10_edit_user">
                    <form id="form_box_10_edit_user">
                        <!--judul--><label style="display: block; text-align: left; margin-top: 20px; font-size: 14;"><b>Business tools</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100"><b>Maximum Limit</b></label><input type="number" id="tol_plafon" name="tol_plafon" required="">                                   
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Used</b></label><input type="text" id="tol_terpakai" name="tol_terpakai" required="" disabled>                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Remaining</b></label><input type="text" id="tol_sisa" name="tol_sisa" required="" disabled>
                        <!--judul--><label style="display: block; text-align: left; margin-top: 10px; font-size: 14;"><b>Medical expense</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100;"><b>Maximum Limit</b></label><input type="number" id="med_plafon" name="med_plafon" required="">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Used</b></label><input type="text" id="med_terpakai" name="med_terpakai" required="" disabled>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Remaining</b></label><input type="text" id="med_sisa" name="med_sisa" required="" disabled>


                    </form>
                </div>   
                <div id="box11_edit_user">
                    <form id="form_box_11_edit_user">
                        <!--judul--><label style="display: block; text-align: left; margin-top: 20px; font-size: 14;"><b>Other</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100"><b>Maximum Limit</b></label><input type="number" id="oth_plafon" name="oth_plafon" required="">                                   
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Used</b></label><input type="text" id="oth_terpakai" name="oth_terpakai" required="" disabled>                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Remaining</b></label><input type="text" id="oth_sisa" name="oth_sisa" required="" disabled>                       

                    </form>
                </div>  
            </div>
        </div>
        <div id="Emergency_contact" class="tabcontent">
            <div id="boxx_edit_user5">            


                <div id="box12_edit_user">
                    <form id="form_box_12_edit_user">
                        <!--judul--><label style="display: block; text-align: left; margin-top: 20px; font-size: 14;"><b>Emergency Contact 1</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100"><b>Name</b></label><input type="text" id="emergency_contact_name_1" name="emergency_contact_name_1" required="">                                   
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Phone</b></label><input type="text" id="emergency_contact_phone_1" name="emergency_contact_phone_1" required="" >                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Relation</b></label><input type="text" id="emergency_contact_relation_1" name="emergency_contact_relation_1" required="" >                            

                    </form>
                </div>   
                <div id="box13_edit_user">
                    <form id="form_box_13_edit_user">
                        <!--judul--><label style="display: block; text-align: left; margin-top: 20px; font-size: 14;"><b>Emergency Contact 2</b></label>
                        <label style="margin-bottom: 0px; margin-top: 0px; font-size: 10px; float: left; font-weight: 100"><b>Name</b></label><input type="text" id="emergency_contact_name_2" name="emergency_contact_name_2" required="">                                   
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Phone</b></label><input type="text" id="emergency_contact_phone_2" name="emergency_contact_phone_2" required="" >                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100"><b>Relation</b></label><input type="text" id="emergency_contact_relation_2" name="emergency_contact_relation_2" required="" >                       

                    </form>
                </div>                     

            </div>
        </div>

        <div id="buttonon_edit_user">
            <div id="btn_delete" onclick="sub_del_user()">Delete</div>
            <div id="btn_edit_submit_user" onclick="sub_edt_user()">Save</div>

            <div id="btn_rsgn" onclick="sub_rsgn_user()">Resign</div>
            <div id="btn_open_rsgn" onclick="sub_open_rsgn_user()">Open</div>
        </div>
        <a href="#0" class="cd-popup-edit-user-close img-replace-edit-customer">Close</a>
    </div>
</div>