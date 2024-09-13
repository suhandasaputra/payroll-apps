/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.matapos.servlet;

import com.bo.function.JsonProcess;
import com.bo.function.MessageProcess;
import com.bo.function.SendHttpProcess;
import com.bo.function.impl.MessageProcessImpl;
import com.bo.parameter.FieldParameterMatapos;
import com.bo.parameter.ProcessingCode;
import com.matapos.parameter.StaticParameter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suhan
 */
@WebServlet(name = "EditEmployee", urlPatterns = {"/EditEmployee"})
public class EditEmployee extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
//        processRequest(request, response);
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        HashMap req = new HashMap();
        HashMap resp = new HashMap();
//        MessageProcess mp = new MessageProcessImpl();
        SendHttpProcess http = new SendHttpProcess();

        try {
            String json = "";
            json = br.readLine();
            HashMap newJson = JsonProcess.decodeJson(json);

            String company_id = newJson.get("company_id").toString();
            String user_id = newJson.get("user_id").toString();
            String employee_id = newJson.get("employee_id").toString();
            String employee_name = newJson.get("employee_name").toString();
            String birth_place = newJson.get("birth_place").toString();
            String birth_date = newJson.get("birth_date").toString();
            String ktp_address = newJson.get("ktp_address").toString();
            String phonenumber = newJson.get("phonenumber").toString();
            String email = newJson.get("email").toString();
            String join_date = newJson.get("join_date").toString();
            String nik = newJson.get("nik").toString();
            String npwp = newJson.get("npwp").toString();
            String npwp_address = newJson.get("npwp_address").toString();
            String kpp = newJson.get("kpp").toString();
            String efin = newJson.get("efin").toString();
            String bpjs_tk_number = newJson.get("bpjs_tk_number").toString();
            String religion = newJson.get("religion").toString();
            String employee_status = newJson.get("employee_status").toString();
            String marital_status = newJson.get("marital_status").toString();
            String position = newJson.get("position").toString();
            String level = newJson.get("level").toString();
            String last_education = newJson.get("last_education").toString();
            String school = newJson.get("school").toString();
            String majority = newJson.get("majority").toString();
            String gender = newJson.get("gender").toString();
            String code_pos = newJson.get("code_pos").toString();
            String blood_type = newJson.get("blood_type").toString();
            String emergency_contact_name_1 = newJson.get("emergency_contact_name_1").toString();
            String emergency_contact_phone_1 = newJson.get("emergency_contact_phone_1").toString();
            String emergency_contact_relation_1 = newJson.get("emergency_contact_relation_1").toString();
            String emergency_contact_name_2 = newJson.get("emergency_contact_name_2").toString();
            String emergency_contact_phone_2 = newJson.get("emergency_contact_phone_2").toString();
            String emergency_contact_relation_2 = newJson.get("emergency_contact_relation_2").toString();
            String trv_plafon = newJson.get("trv_plafon").toString();
            String edu_plafon = newJson.get("edu_plafon").toString();
            String tol_plafon = newJson.get("tol_plafon").toString();
            String med_plafon = newJson.get("med_plafon").toString();
            String oth_plafon = newJson.get("oth_plafon").toString();
            String trv_sisa = newJson.get("trv_sisa").toString();
            String edu_sisa = newJson.get("edu_sisa").toString();
            String tol_sisa = newJson.get("tol_sisa").toString();
            String med_sisa = newJson.get("med_sisa").toString();
            String oth_sisa = newJson.get("oth_sisa").toString();
            String trv_terpakai = newJson.get("trv_terpakai").toString();
            String edu_terpakai = newJson.get("edu_terpakai").toString();
            String tol_terpakai = newJson.get("tol_terpakai").toString();
            String med_terpakai = newJson.get("med_terpakai").toString();
            String oth_terpakai = newJson.get("oth_terpakai").toString();

            req.put(FieldParameterMatapos.proccode, ProcessingCode.editemployee);
            req.put("company_id", company_id);
            req.put("user_id", user_id);
            req.put("employee_id", employee_id);
            req.put("employee_name", employee_name);
            req.put("birth_place", birth_place);
            req.put("birth_date", birth_date);
            req.put("ktp_address", ktp_address);
            req.put("phonenumber", phonenumber);
            req.put("email", email);
            req.put("join_date", join_date);
            req.put("nik", nik);
            req.put("npwp", npwp);
            req.put("npwp_address", npwp_address);
            req.put("kpp", kpp);
            req.put("efin", efin);
            req.put("bpjs_tk_number", bpjs_tk_number);
            req.put("religion", religion);
            req.put("employee_status", employee_status);
            req.put("marital_status", marital_status);
            req.put("position", position);
            req.put("level", level);
            req.put("last_education", last_education);
            req.put("school", school);
            req.put("majority", majority);
            req.put("gender", gender);
            req.put("code_pos", code_pos);
            req.put("blood_type", blood_type);
            req.put("emergency_contact_name_1", emergency_contact_name_1);
            req.put("emergency_contact_phone_1", emergency_contact_phone_1);
            req.put("emergency_contact_relation_1", emergency_contact_relation_1);
            req.put("emergency_contact_name_2", emergency_contact_name_2);
            req.put("emergency_contact_phone_2", emergency_contact_phone_2);
            req.put("emergency_contact_relation_2", emergency_contact_relation_2);
            req.put("trv_plafon", trv_plafon);
            req.put("edu_plafon", edu_plafon);
            req.put("tol_plafon", tol_plafon);
            req.put("med_plafon", med_plafon);
            req.put("oth_plafon", oth_plafon);
            req.put("trv_sisa", trv_sisa);
            req.put("edu_sisa", edu_sisa);
            req.put("tol_sisa", tol_sisa);
            req.put("med_sisa", med_sisa);
            req.put("oth_sisa", oth_sisa);
            req.put("trv_terpakai", trv_terpakai);
            req.put("edu_terpakai", edu_terpakai);
            req.put("tol_terpakai", tol_terpakai);
            req.put("med_terpakai", med_terpakai);
            req.put("oth_terpakai", oth_terpakai);

//            String reqMsg = mp.encryptMessage(req);
            String reqMsg = JsonProcess.generateJson(req);
            String responseWeb = http.sendHttpRequest(StaticParameter.urlBOServer, reqMsg);
//            HashMap resp = mp.decryptMessage(responseWeb);
            resp = JsonProcess.decodeJson(responseWeb);

            System.out.println("ini response nnya add : " + resp);
            response.getWriter().print(resp);
            response.setContentType("application/json");
        } catch (IOException e) {
            System.out.println("error : " + e);
            response.setContentType("application/json");
            response.getWriter().print("error");
        } finally {
            br = null;
            req = null;
            resp = null;
//            mp = null;
            http = null;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
