/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author azzam
 */
public class pengajuanreimbursement {

 private static Logger log = Logger.getLogger(pengajuanreimbursement.class);

    public HashMap process(HashMap input) throws ParseException {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap status = dp.pengajuanreimbursement(
                input.get("company_id").toString(),
                input.get("employee_id").toString(),                
                input.get("reimbursement_type").toString(),               
                input.get("description").toString(),
                input.get("user_id").toString(), 
                input.get("nominal_pengajuan").toString(),              
                input.get("tanggal_pengeluaran").toString(),              
                input.get("bukti_pengajuan").toString()              
        );
        return status;
    }
    
}
