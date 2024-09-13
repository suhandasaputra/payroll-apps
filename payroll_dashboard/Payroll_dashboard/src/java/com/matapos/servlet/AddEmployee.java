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
@WebServlet(name = "AddEmployee", urlPatterns = {"/AddEmployee"})
public class AddEmployee extends HttpServlet {

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
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");
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

            String emergency_contact_name = newJson.get("emergency_contact_name").toString();
            String emergency_contact_phone = newJson.get("emergency_contact_phone").toString();
            String emergency_contact_relationship = newJson.get("emergency_contact_relationship").toString();
            String emergency_contact_name2 = newJson.get("emergency_contact_name2").toString();
            String emergency_contact_phone2 = newJson.get("emergency_contact_phone2").toString();
            String emergency_contact_relationship2 = newJson.get("emergency_contact_relationship2").toString();

            req.put(FieldParameterMatapos.proccode, ProcessingCode.addemployee);
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
            req.put("blood_type", blood_type);
            req.put("code_pos", code_pos);

            req.put("emergency_contact_name", emergency_contact_name);
            req.put("emergency_contact_phone", emergency_contact_phone);
            req.put("emergency_contact_relationship", emergency_contact_relationship);
            req.put("emergency_contact_name2", emergency_contact_name2);
            req.put("emergency_contact_phone2", emergency_contact_phone2);
            req.put("emergency_contact_relationship2", emergency_contact_relationship2);

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
