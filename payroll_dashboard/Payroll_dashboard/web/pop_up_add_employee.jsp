<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    <%@include file="/mycss/pop_up_add_employee.css"%>
</style>

<script type="text/javascript" src="myjs/pop_up_add_employee.js"></script>

<div class="cd-popup-add-user" role="alert">
    <div class="cd-popup-add-user-container">
        <label id="label_add_user" style="color: #8915e1">Add Employee</label>
        <div class="container" id="ref_tabel_item" style="margin-bottom: 0px">    
            <button onclick="window.location.href = 'empl_stat'" id="add_empl_stat"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Add Employee Status</button>
            <button onclick="window.location.href = 'religion'" id="add_religion"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Add Religion</button>
            <button onclick="window.location.href = 'majority'" id="add_majority"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Add Majority</button>            
            <button onclick="window.location.href = 'position'" id="add_position"><i class="icon fa fa-plus-circle" style="margin-right: 10px"></i>Add Position</button>            
        </div>        
        <hr>
        <hr>

        <form id="regForm" action="">            

            <!-- One "tab" for each step in the form: -->
            <div class="tab">
                <div style="background-color: #c780fa; color: white;">
                    <h4>Biodata</h4>                    
                </div>
                <div id="boxx_add_user">                     
                    <div id="form_box1" style="margin-right: 30px;">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Employee ID</label><input type="text" id="employee_id" name="employee_id" required="" placeholder="Employee ID" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Full name</label><input type="text" id="employee_name" name="employee_name" required="" placeholder="Full Name" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Birth place</label><input type="text" id="birth_place" name="birth_place" required="" placeholder="Birth Place" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Birth date</label><input type="date" id="birth_date" name="birth_date" required="" placeholder="Birth Date" oninput="this.className = ''">                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Phone number</label><input type="number" id="phonenumber" name="phonenumber" required="" placeholder="Phone Number" oninput="this.className = ''">                        
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Email </label>
                        <label id="email-error" style="color:red; font-size: 10px; float: left; font-weight: 100; display:none"><b>**Please enter a valid email</b></label><input type="email" id="email" name="email" required="" placeholder="Email" oninput="this.className = ''; ">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Join Date</label><input type="date" id="join_date" name="join_date" required="" placeholder="Join Date" oninput="this.className = ''">
                    </div>
                    <div id="form_box2">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">NIK</label><input type="text" id="nik" name="nik" required="" placeholder="NIK" oninput="this.className = ''">                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">KTP Address</label><input type="text" id="ktp_address" name="ktp_address" required="" placeholder="KTP Address" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Code Pos</label><input type="text" id="code_pos" name="code_pos" required="" placeholder="Code Pos" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Npwp</label><input class="no-validate" type="text" id="npwp" name="npwp" required="" placeholder="NPWP" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Npwp Address</label><input class="no-validate" type="text" id="npwp_address" name="npwp_address" required="" placeholder="NPWP Address" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">BPJS TK Number</label><input class="no-validate" type="text" id="bpjs_tk_number" name="bpjs_tk_number" required="" placeholder="BPJS TK Number" oninput="this.className = ''">

                    </div>
                </div>
            </div>

            <div class="tab">
                <div style="background-color: #c780fa; color: white; ">
                    <h4>Biodata</h4>                    
                </div>
                <div id="boxx_add_user2">                   
                    <div id="form_box3" style="margin-right: 30px;">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Efin</label><input class="no-validate" type="text" id="efin" name="efin" required="" placeholder="Efin" oninput="this.className = ''">                                            
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Kpp</label><input class="no-validate" type="text" id="kpp" name="kpp" required="" placeholder="KPP" oninput="this.className = ''">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Employee Status</label>   <select id="employee_status" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="employee_status" onchange="this.className = ''";> </select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Category</label>   <select id="position" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="position" onchange="this.className = ''";></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Level</label>  <select id="level" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="level" onchange="this.className = ''";> </select>                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Gender</label>
                        <select id="gender" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="gender" onchange="this.className = ''";>
                            <option selected disabled value="">Select Gender</option>
                            <option value="Laki-laki">Laki-laki</option>
                            <option value="Perempuan">Perempuan</option> 
                        </select>
                    </div>


                    <div id="form_box4" style="margin-bottom: 50px;">
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Religion</label>   <select id="religion" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="religion" onchange="this.className = ''";> </select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Marital Status</label>   <select id="marital_status" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="marital_status" onchange="this.className = ''";></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Blood Type</label>
                        <select id="blood_type" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="blood_type" onchange="this.className = ''";>
                            <option selected disabled value="">Select Blood Type</option>
                            <option value="A">A</option>
                            <option value="B">B</option>                        
                            <option value="O">O</option> 
                            <option value="AB">AB</option> 
                        </select>                    
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Last Education</label>  <select id="last_education" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="last_education" onchange="this.className = ''";></select>
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">School</label> <input type="text" id="school" name="school" required="" placeholder="School" oninput="this.className = ''">                
                        <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Majority</label>   <select id="majority" style="width: 350px; padding: 0px; margin-bottom: 5px;" name="majority" onchange="this.className = ''";></select>
                    </div>
                </div>

            </div>

            <div class="tab">
                <div style="background-color: #c780fa; color: white; ">
                    <h4 style="margin-bottom: 0px;">Emergency Contact</h4>                    
                </div>                
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100; margin-top: 10px;">Full Name (I)</label><input type="text" id="emergency_contact_name" name="emergency_contact_name" required="" placeholder="Full Name" oninput="this.className = ''">                    
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Phone Number (I)</label><input type="text" id="emergency_contact_phone" name="emergency_contact_phone" required="" placeholder="Phone Number" oninput="this.className = ''">
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Relationship Type (I)</label><input type="text" id="emergency_contact_relationship" name="emergency_contact_relationship" required="" placeholder="Relationship Type" oninput="this.className = ''">
                <hr>                
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Full Name (II)</label><input class="no-validate" type="text" id="emergency_contact_name2" name="emergency_contact_name2" required="" placeholder="Full Name" oninput="this.className = ''">                    
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Phone Number (II)</label><input class="no-validate" type="text" id="emergency_contact_phone2" name="emergency_contact_phone2" required="" placeholder="Phone Number" oninput="this.className = ''">
                <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Relationship Type (II)</label><input class="no-validate" type="text" style="margin-bottom: 20px;" id="emergency_contact_relationship2" name="emergency_contact_relationship2" required="" placeholder="Relationship Type" oninput="this.className = ''">

            </div>

            <div style="overflow:auto;">
                <div style="float:right;">
                    <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                    <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
                </div>
            </div>

            <!-- Circles which indicates the steps of the form: -->
            <div style="text-align:center;margin-top:40px;">
                <span class="step"></span>
                <span class="step"></span>
                <span class="step"></span>

            </div>

        </form>


        <!--        <div id="boxx_add_user">
                    <div id="myModal_add_user" class="modal">
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <div id="push_text_add_user"></div>
                        </div>
                    </div>
                    <div id="box1_add_user">
                        <form id="form_box1">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Employee ID</label><input type="text" id="employee_id" name="employee_id" required="" placeholder="employee_id">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Full name</label><input type="text" id="employee_name" name="employee_name" required="" placeholder="full_name">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Birth place</label><input type="text" id="birth_place" name="birth_place" required="" placeholder="birth_place">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Birth date</label><input type="date" id="birth_date" name="birth_date" required="" placeholder="birth_date">                    
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Phone number</label><input type="number" id="phonenumber" name="phonenumber" required="" placeholder="phonenumber">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Email</label><input type="email" id="email" name="email" required="" placeholder="email">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Join Date</label><input type="date" id="join_date" name="join_date" required="" placeholder="join_date">
        
                        </form>
                    </div>
                    <div id="box2_add_user">
                        <form id="form_box2">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">NIK</label><input type="text" id="nik" name="nik" required="" placeholder="nik">                    
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">KTP Address</label><input type="text" id="ktp_address" name="ktp_address" required="" placeholder="ktp_address">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Code Pos</label><input type="text" id="code_pos" name="code_pos" required="" placeholder="code_pos">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Npwp</label><input type="text" id="npwp" name="npwp" required="" placeholder="npwp">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Npwp Address</label><input type="text" id="npwp_address" name="npwp_address" required="" placeholder="npwp_address">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">BPJS TK Number</label><input type="text" id="bpjs_tk_number" name="bpjs_tk_number" required="" placeholder="bpjs_tk_number">
        
                        </form>
                    </div>
                    <div id="box3_add_user" style="display: none;">
                        <form id="form_box3">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Efin</label><input type="text" id="efin" name="efin" required="" placeholder="efin">                                            
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Kpp</label><input type="text" id="kpp" name="kpp" required="" placeholder="kpp">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Employee Status</label>   <select id="employee_status" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="employee_status"> </select>
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Category</label>   <select id="position" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="position"></select>
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Position Level</label>  <select id="level" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="level"> </select>                    
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Gender</label>
                            <select id="gender" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="gender">
                                <option selected disabled value="">Select Gender</option>
                                <option value="Laki-laki">Laki-laki</option>
                                <option value="Perempuan">Perempuan</option> 
                            </select>
                        </form>
                    </div>
                    <div id="box4_add_user" style="display: none;">
                        <form id="form_box4">
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Religion</label>   <select id="religion" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="religion"> </select>
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Marital Status</label>   <select id="marital_status" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="marital_status"></select>
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Blood Type</label>
                            <select id="blood_type" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="blood_type">
                                <option selected disabled value="">Select Blood Type</option>
                                <option value="A">A</option>
                                <option value="B">B</option>                        
                                <option value="O">O</option> 
                                <option value="AB">AB</option> 
                            </select>                    
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Last Education</label>  <select id="last_education" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="last_education"></select>
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">School</label> <input type="text" id="school" name="school" required="" placeholder="school">                
                            <label style="margin-bottom: 0px; font-size: 10px; float: left; font-weight: 100">Majority</label>   <select id="majority" style="width: 215px; padding: 0px; margin-bottom: 10px;" name="majority"></select>
                        </form>
                    </div>
                </div>-->

        <!--        <div id="buttonon_add_user">
                    <div id="btn_cancel">Cancel</div>
                    <div id="btn_submit_user" onclick="sub_out()">Submit</div>
                </div>-->
        <a href="#0" class="cd-popup-add-user-close img-replace-add-user">Close</a>
    </div>
</div>
