/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class editemployee {

    private static Logger log = Logger.getLogger(editemployee.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.editemployee(
                input.get("company_id").toString(),
                input.get("employee_id").toString(),
                input.get("employee_name").toString(),
                input.get("birth_place").toString(),
                input.get("birth_date").toString(),
                input.get("ktp_address").toString(),
                input.get("phonenumber").toString(),
                input.get("email").toString(),
                input.get("join_date").toString(),
                input.get("nik").toString(),
                input.get("npwp").toString(),
                input.get("npwp_address").toString(),
                input.get("kpp").toString(),
                input.get("efin").toString(),
                input.get("bpjs_tk_number").toString(),
                input.get("religion").toString(),
                input.get("employee_status").toString(),
                input.get("marital_status").toString(),
                input.get("position").toString(),
                input.get("level").toString(),
                input.get("last_education").toString(),
                input.get("school").toString(),
                input.get("majority").toString(),
                input.get("gender").toString(),
                input.get("code_pos").toString(),
                input.get("blood_type").toString(),
                input.get("emergency_contact_name_1").toString(),
                input.get("emergency_contact_phone_1").toString(),
                input.get("emergency_contact_relation_1").toString(),
                input.get("emergency_contact_name_2").toString(),
                input.get("emergency_contact_phone_2").toString(),
                input.get("emergency_contact_relation_2").toString(),
                input.get("trv_plafon").toString(),
                input.get("edu_plafon").toString(),
                input.get("tol_plafon").toString(),
                input.get("med_plafon").toString(),
                input.get("oth_plafon").toString(),
                input.get("trv_sisa").toString(),
                input.get("edu_sisa").toString(),
                input.get("tol_sisa").toString(),
                input.get("med_sisa").toString(),
                input.get("oth_sisa").toString(),
                input.get("trv_terpakai").toString(),
                input.get("edu_terpakai").toString(),
                input.get("tol_terpakai").toString(),
                input.get("med_terpakai").toString(),
                input.get("oth_terpakai").toString()
        );
        return status;
    }
}
