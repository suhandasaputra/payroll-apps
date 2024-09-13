/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author azzam
 */
public class cancelpengajuanreimbursementuser {

    private static Logger log = Logger.getLogger(cancelpengajuanreimbursementuser.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.cancelpengajuanreimbursementuser(
                input.get("reference").toString(), 
                input.get("employee_id").toString(), 
                input.get("company_id").toString(), 
                input.get("user_id").toString());
        return status;
    }
}
