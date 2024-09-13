/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boserverpos.servlet;

import com.bo.function.JsonProcess;
import com.bo.function.impl.MessageProcessImpl;
import com.bopro.process.loginbackend;
import com.bo.function.MessageProcess;
import com.bo.parameter.FieldParameterMatapos;
import com.bo.parameter.RuleNameParameter;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.bo.parameter.ProcessingCode;
import com.bopro.process.addannouncement;
import com.bopro.process.addeducationuser;
import com.bopro.process.addmajority;
import com.bopro.process.addemployee;
import com.bopro.process.addemployeestatus;
import com.bopro.process.addfamilyuser;
import com.bopro.process.addjobuser;
import com.bopro.process.addlevel;
import com.bopro.process.addposition;
import com.bopro.process.addreligion;
import com.bopro.process.addschedule;
import com.bopro.process.addshift;
import com.bopro.process.addtimeoff;
import com.bopro.process.adduser;
import com.bopro.process.approvepengajuanabsensiadmin;
import com.bopro.process.approvepengajuanabsensihead;
import com.bopro.process.approvepengajuanovertimeadmin;
import com.bopro.process.approvepengajuanovertimehead;
import com.bopro.process.approvepengajuanreimbursementadmin;
import com.bopro.process.approvepengajuanreimbursementhead;
import com.bopro.process.approvepengajuantimeoffadmin;
import com.bopro.process.approvepengajuantimeoffhead;
import com.bopro.process.cancelpengajuanovertimeuser;
import com.bopro.process.cancelpengajuanreimbursementuser;
import com.bopro.process.cancelpengajuantimeoffuser;
import com.bopro.process.changepassword;
import com.bopro.process.closeresign;
import com.bopro.process.deleteeducationuser;
import com.bopro.process.deleteemployee;
import com.bopro.process.deleteemployeestatus;
import com.bopro.process.deletefamilyuser;
import com.bopro.process.deletejobuser;
import com.bopro.process.deletelevel;
import com.bopro.process.deletemajority;
import com.bopro.process.deleteposition;
import com.bopro.process.deletereligion;
import com.bopro.process.deleteschedule;
import com.bopro.process.deleteshift;
import com.bopro.process.deletetimeoff;
import com.bopro.process.deleteuser;
import com.bopro.process.editeducationuser;
import com.bopro.process.editemployee;
import com.bopro.process.editemployeestatus;
import com.bopro.process.editfamilyuser;
import com.bopro.process.editjobuser;
import com.bopro.process.editlevel;
import com.bopro.process.editmajority;
import com.bopro.process.editposition;
import com.bopro.process.editreligion;
import com.bopro.process.editschedule;
import com.bopro.process.editshift;
import com.bopro.process.edittimeoff;
import com.bopro.process.edituser;
import com.bopro.process.generateabsence;
import com.bopro.process.getdetailannouncement;
import com.bopro.process.getdetaileducationuser;
import com.bopro.process.getdetailemployee;
import com.bopro.process.getdetailemployeestatus;
import com.bopro.process.getdetailfamilyuser;
import com.bopro.process.getdetailjobuser;
import com.bopro.process.getdetaillevel;
import com.bopro.process.getdetailliveattendance;
import com.bopro.process.getdetailmajority;
import com.bopro.process.getdetailpengajuanabsensi;
import com.bopro.process.getdetailpengajuanovertime;
import com.bopro.process.getdetailpengajuanreimbursement;
import com.bopro.process.getdetailpengajuantimeoff;
import com.bopro.process.getdetailposition;
import com.bopro.process.getdetailreligion;
import com.bopro.process.getdetailschedule;
import com.bopro.process.getdetailshift;
import com.bopro.process.getdetailsummaryabsence;
import com.bopro.process.getdetailtimeoff;
import com.bopro.process.getdetailuser;
import com.bopro.process.getemergencycontact;
import com.bopro.process.getjobdata;
import com.bopro.process.getlistannouncementadmin;
import com.bopro.process.getlistannouncementuser;
import com.bopro.process.getlistcalendar;
import com.bopro.process.getlisteducationuser;
import com.bopro.process.getlistemployee;
import com.bopro.process.getlistemployeestatus;
import com.bopro.process.getlistfamilyuser;
import com.bopro.process.getlistjobuser;
import com.bopro.process.getlistlevel;
import com.bopro.process.getlistliveattendanceadmin;
import com.bopro.process.getlistliveattendanceuser;
import com.bopro.process.getlistmajority;
import com.bopro.process.getlistpengajuanabsensiadmin;
import com.bopro.process.getlistpengajuanabsensiuser;
import com.bopro.process.getlistpengajuanovertimeadmin;
import com.bopro.process.getlistpengajuanovertimeuser;
import com.bopro.process.getlistpengajuanreimbursementadmin;
import com.bopro.process.getlistpengajuanreimbursementuser;
import com.bopro.process.getlistpengajuantimeoffadmin;
import com.bopro.process.getlistpengajuantimeoffuser;
import com.bopro.process.getlistposition;
import com.bopro.process.getlistreimbursementtype;
import com.bopro.process.getlistreligion;
import com.bopro.process.getlistschedule;
import com.bopro.process.getlistshift;
import com.bopro.process.getlistsummaryabsence;
import com.bopro.process.getlisttimeoff;
import com.bopro.process.getlistuser;
import com.bopro.process.getprivatedata;
import com.bopro.process.livecheckin;
import com.bopro.process.livecheckout;
import com.bopro.process.openresign;
import com.bopro.process.optiongetaccountlevel;
import com.bopro.process.optiongetbidangusaha;
import com.bopro.process.optiongetemployee;
import com.bopro.process.optiongetemployeestatus;
import com.bopro.process.optiongetlasteducation;
import com.bopro.process.optiongetlevel;
import com.bopro.process.optiongetmajority;
import com.bopro.process.optiongetmarital;
import com.bopro.process.optiongetposition;
import com.bopro.process.optiongetreligion;
import com.bopro.process.optiongetshift;
import com.bopro.process.pengajuanabsensi;
import com.bopro.process.pengajuanovertime;
import com.bopro.process.pengajuanreimbursement;
import com.bopro.process.pengajuantimeoff;
import com.bopro.process.rejectpengajuanabsensiadmin;
import com.bopro.process.rejectpengajuanabsensihead;
import com.bopro.process.rejectpengajuanovertimeadmin;
import com.bopro.process.rejectpengajuanovertimehead;
import com.bopro.process.rejectpengajuanreimbursementadmin;
import com.bopro.process.rejectpengajuantimeoffadmin;
import com.bopro.process.rejectpengajuantimeoffhead;
import com.bopro.process.signupowner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import org.json.simple.parser.ParseException;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class BackendProcess extends HttpServlet {

    private MessageProcess mp = new MessageProcessImpl();
    private static Logger log = Logger.getLogger(BackendProcess.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.simple.parser.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, java.text.ParseException {
        BufferedReader in = null;
        HashMap input = null;
        String inputString = "";
        String line = "";
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            input = new HashMap();
            while ((line = in.readLine()) != null) {
                inputString += line;
            }

//            matikan line ini saat pakaienkripsi
            inputString = inputString.replace("%20", " ");

            System.out.println("ini input string : " + inputString);
//            untuk pakai enkripsi
//            input = mp.decryptMessage(inputString);
            input = JsonProcess.decodeJson(inputString);
            log.info("\n" + "request Backend process message : " + input + " \n");
            System.out.println("\n" + "request Backend process message : " + input + " \n");
            if (ProcessingCode.loginbackend.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new loginbackend().process(input);
            } else if (ProcessingCode.signup.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new signupowner().process(input);
            } else if (ProcessingCode.optiongetreligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetreligion().process(input);
            } else if (ProcessingCode.optiongetemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetemployeestatus().process(input);
            } else if (ProcessingCode.optiongetmarital.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetmarital().process(input);
            } else if (ProcessingCode.optiongetposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetposition().process(input);
            } else if (ProcessingCode.optiongetlasteducation.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetlasteducation().process(input);
            } else if (ProcessingCode.optiongetmajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetmajority().process(input);
            } else if (ProcessingCode.optiongetlevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetlevel().process(input);
            } else if (ProcessingCode.optiongetbidangusaha.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetbidangusaha().process(input);
            } else if (ProcessingCode.optiongetaccountlevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetaccountlevel().process(input);
            } else if (ProcessingCode.optiongetemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetemployee().process(input);
            } else if (ProcessingCode.optiongetshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new optiongetshift().process(input);

            } else if (ProcessingCode.getdetailemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailemployee().process(input);
            } else if (ProcessingCode.getlistemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistemployee().process(input);
            } else if (ProcessingCode.addemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addemployee().process(input);
            } else if (ProcessingCode.editemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editemployee().process(input);
            } else if (ProcessingCode.deleteemployee.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteemployee().process(input);

            } else if (ProcessingCode.closeresign.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new closeresign().process(input);
            } else if (ProcessingCode.openresign.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new openresign().process(input);

            } else if (ProcessingCode.getdetailuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailuser().process(input);
            } else if (ProcessingCode.getlistuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistuser().process(input);
            } else if (ProcessingCode.adduser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new adduser().process(input);
            } else if (ProcessingCode.edituser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new edituser().process(input);
            } else if (ProcessingCode.deleteuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteuser().process(input);

            } else if (ProcessingCode.getdetailshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailshift().process(input);
            } else if (ProcessingCode.getlistshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistshift().process(input);
            } else if (ProcessingCode.addshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addshift().process(input);
            } else if (ProcessingCode.editshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editshift().process(input);
            } else if (ProcessingCode.deleteshift.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteshift().process(input);

            } else if (ProcessingCode.getdetailemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailemployeestatus().process(input);
            } else if (ProcessingCode.getlistemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistemployeestatus().process(input);
            } else if (ProcessingCode.addemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addemployeestatus().process(input);
            } else if (ProcessingCode.editemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editemployeestatus().process(input);
            } else if (ProcessingCode.deleteemployeestatus.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteemployeestatus().process(input);

            } else if (ProcessingCode.getdetailmajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailmajority().process(input);
            } else if (ProcessingCode.getlistmajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistmajority().process(input);
            } else if (ProcessingCode.addmajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addmajority().process(input);
            } else if (ProcessingCode.editmajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editmajority().process(input);
            } else if (ProcessingCode.deletemajority.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletemajority().process(input);

            } else if (ProcessingCode.getdetailposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailposition().process(input);
            } else if (ProcessingCode.getlistposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistposition().process(input);
            } else if (ProcessingCode.addposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addposition().process(input);
            } else if (ProcessingCode.editposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editposition().process(input);
            } else if (ProcessingCode.deleteposition.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteposition().process(input);

            } else if (ProcessingCode.getdetailreligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailreligion().process(input);
            } else if (ProcessingCode.getlistreligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistreligion().process(input);
            } else if (ProcessingCode.addreligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addreligion().process(input);
            } else if (ProcessingCode.editreligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editreligion().process(input);
            } else if (ProcessingCode.deletereligion.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletereligion().process(input);

            } else if (ProcessingCode.getdetaillevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetaillevel().process(input);
            } else if (ProcessingCode.getlistlevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistlevel().process(input);
            } else if (ProcessingCode.addlevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addlevel().process(input);
            } else if (ProcessingCode.editlevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editlevel().process(input);
            } else if (ProcessingCode.deletelevel.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletelevel().process(input);

            } else if (ProcessingCode.getdetailtimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailtimeoff().process(input);
            } else if (ProcessingCode.getlisttimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlisttimeoff().process(input);
            } else if (ProcessingCode.addtimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addtimeoff().process(input);
            } else if (ProcessingCode.edittimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new edittimeoff().process(input);
            } else if (ProcessingCode.deletetimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletetimeoff().process(input);

            } else if (ProcessingCode.getdetailschedule.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailschedule().process(input);
            } else if (ProcessingCode.getlistschedule.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistschedule().process(input);
            } else if (ProcessingCode.addschedule.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addschedule().process(input);
            } else if (ProcessingCode.editschedule.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editschedule().process(input);
            } else if (ProcessingCode.deleteschedule.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteschedule().process(input);

            } else if (ProcessingCode.pengajuanabsensi.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new pengajuanabsensi().process(input);
            } else if (ProcessingCode.getlistpengajuanabsensiadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuanabsensiadmin().process(input);
            } else if (ProcessingCode.getdetailpengajuanabsensi.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailpengajuanabsensi().process(input);
            } else if (ProcessingCode.approvepengajuanabsensiadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuanabsensiadmin().process(input);
            } else if (ProcessingCode.rejectpengajuanabsensiadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuanabsensiadmin().process(input);
            } else if (ProcessingCode.approvepengajuanabsensihead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuanabsensihead().process(input);
            } else if (ProcessingCode.rejectpengajuanabsensihead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuanabsensihead().process(input);
            } else if (ProcessingCode.changepassword.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new changepassword().process(input);
            } else if (ProcessingCode.generateabsence.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new generateabsence().process(input);
            } else if (ProcessingCode.livecheckin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new livecheckin().process(input);
            } else if (ProcessingCode.livecheckout.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new livecheckout().process(input);
            } else if (ProcessingCode.getlistliveattendanceadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistliveattendanceadmin().process(input);
            } else if (ProcessingCode.getdetailliveattendance.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailliveattendance().process(input);
            } else if (ProcessingCode.pengajuantimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new pengajuantimeoff().process(input);
            } else if (ProcessingCode.getlistpengajuantimeoffadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuantimeoffadmin().process(input);
            } else if (ProcessingCode.getdetailpengajuantimeoff.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailpengajuantimeoff().process(input);
            } else if (ProcessingCode.approvepengajuantimeoffadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuantimeoffadmin().process(input);
            } else if (ProcessingCode.rejectpengajuantimeoffadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuantimeoffadmin().process(input);
            } else if (ProcessingCode.approvepengajuantimeoffhead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuantimeoffhead().process(input);
            } else if (ProcessingCode.rejectpengajuantimeoffhead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuantimeoffhead().process(input);

            } else if (ProcessingCode.getlistpengajuanabsensiuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuanabsensiuser().process(input);
            } else if (ProcessingCode.getlistliveattendanceuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistliveattendanceuser().process(input);
            } else if (ProcessingCode.getlistpengajuantimeoffuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuantimeoffuser().process(input);
            } else if (ProcessingCode.cancelpengajuantimeoffuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new cancelpengajuantimeoffuser().process(input);

            } else if (ProcessingCode.pengajuanovertime.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new pengajuanovertime().process(input);
            } else if (ProcessingCode.getlistpengajuanovertimeadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuanovertimeadmin().process(input);
            } else if (ProcessingCode.getlistpengajuanovertimeuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistpengajuanovertimeuser().process(input);
            } else if (ProcessingCode.getdetailpengajuanovertime.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailpengajuanovertime().process(input);
            } else if (ProcessingCode.approvepengajuanovertimeadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuanovertimeadmin().process(input);
            } else if (ProcessingCode.rejectpengajuanovertimeadmin.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuanovertimeadmin().process(input);
            } else if (ProcessingCode.approvepengajuanovertimehead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new approvepengajuanovertimehead().process(input);
            } else if (ProcessingCode.rejectpengajuanovertimehead.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new rejectpengajuanovertimehead().process(input);
            } else if (ProcessingCode.cancelpengajuanovertimeuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new cancelpengajuanovertimeuser().process(input);

            } else if (ProcessingCode.getdetailsummaryabsence.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailsummaryabsence().process(input);
            } else if (ProcessingCode.getlistsummaryabsence.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistsummaryabsence().process(input);
            } //            else if (ProcessingCode.editsummaryabsence.equals(input.get(FieldParameterMatapos.proccode).toString())) {
            //                input = new editschedule().process(input);
            //            }
            else if (ProcessingCode.getprivatedata.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getprivatedata().process(input);
            } else if (ProcessingCode.getjobdata.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getjobdata().process(input);
            } else if (ProcessingCode.getemergencycontact.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getemergencycontact().process(input);
            }
            
            else if (ProcessingCode.getlistfamilyuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistfamilyuser().process(input);
            } else if (ProcessingCode.getdetailfamilyuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailfamilyuser().process(input);
            } else if (ProcessingCode.addfamilyuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addfamilyuser().process(input);
            } else if (ProcessingCode.editfamilyuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editfamilyuser().process(input);
            } else if (ProcessingCode.deletefamilyuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletefamilyuser().process(input);
            }
            
            else if (ProcessingCode.getlisteducationuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlisteducationuser().process(input);
            } else if (ProcessingCode.getdetaileducationuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetaileducationuser().process(input);
            } else if (ProcessingCode.addeducationuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addeducationuser().process(input);
            } else if (ProcessingCode.editeducationuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editeducationuser().process(input);
            } else if (ProcessingCode.deleteeducationuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deleteeducationuser().process(input);
            }
            
            else if (ProcessingCode.getlistjobuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getlistjobuser().process(input);
            } else if (ProcessingCode.getdetailjobuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new getdetailjobuser().process(input);
            } else if (ProcessingCode.addjobuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new addjobuser().process(input);
            } else if (ProcessingCode.editjobuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new editjobuser().process(input);
            } else if (ProcessingCode.deletejobuser.equals(input.get(FieldParameterMatapos.proccode).toString())) {
                input = new deletejobuser().process(input);
            } 

            else if (ProcessingCode.getlistcalendar.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistcalendar().process(input);
            }
            else if (ProcessingCode.getlistreimbursementtype.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistreimbursementtype().process(input);
            }

            else if (ProcessingCode.pengajuanreimbursement.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new pengajuanreimbursement().process(input);
            }else if (ProcessingCode.getlistpengajuanreimbursementadmin.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistpengajuanreimbursementadmin().process(input);
            }else if (ProcessingCode.getdetailpengajuanreimbursement.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getdetailpengajuanreimbursement().process(input);
            }else if (ProcessingCode.approvepengajuanreimbursementadmin.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new approvepengajuanreimbursementadmin().process(input);
            }else if (ProcessingCode.rejectpengajuanreimbursementadmin.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new rejectpengajuanreimbursementadmin().process(input);
            }else if (ProcessingCode.approvepengajuanreimbursementhead.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new approvepengajuanreimbursementhead().process(input);
            }else if (ProcessingCode.rejectpengajuanreimbursementhead.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new rejectpengajuanreimbursementadmin().process(input);
            }else if (ProcessingCode.getlistpengajuanreimbursementuser.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistpengajuanreimbursementuser().process(input);
            }else if (ProcessingCode.cancelpengajuanreimbursementuser.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new cancelpengajuanreimbursementuser().process(input);
            }

            else if (ProcessingCode.addannouncement.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new addannouncement().process(input);
            }else if (ProcessingCode.getlistannouncementuser.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistannouncementuser().process(input);
            }else if (ProcessingCode.getlistannouncementadmin.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getlistannouncementadmin().process(input);
            }else if (ProcessingCode.getdetailannouncement.equals(input.get(FieldParameterMatapos.proccode).toString())){
                input = new getdetailannouncement().process(input);
            }

        }catch (IOException e) {
            input.put(RuleNameParameter.resp_code, "0030");
            input.put(RuleNameParameter.resp_desc, "Error message request");
        }

//        untuk pakai enkripsi
//        response.getOutputStream().write(mp.encryptMessage(input).getBytes());
        response.getOutputStream().write(JsonProcess.generateJson(input).getBytes());
        response.getOutputStream().flush();
        System.out.println(
                "\n" + "response FROM Backend process message TO Web : " + input + " \n");
        log.info(
                "\n" + "response FROM Backend process message TO Web : " + input + " \n");

        in = null;
        input = null;
        inputString = "";
        line = "";
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException | java.text.ParseException ex) {
            java.util.logging.Logger.getLogger(BackendProcess.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException | java.text.ParseException ex) {
            java.util.logging.Logger.getLogger(BackendProcess.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
