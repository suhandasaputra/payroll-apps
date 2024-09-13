/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author azzam
 */
public class rejectpengajuanreimbursementadmin {

private static Logger log = Logger.getLogger(rejectpengajuanreimbursementadmin.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.rejectpengajuanreimbursementadmin(
                input.get("reference").toString(), 
                input.get("employee_id").toString(), 
                input.get("company_id").toString(), 
                input.get("user_id").toString(),
                input.get("command").toString());
        return status;
    }
    
}
