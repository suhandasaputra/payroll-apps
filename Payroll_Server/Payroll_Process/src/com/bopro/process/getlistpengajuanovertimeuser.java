/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class getlistpengajuanovertimeuser {

    private static Logger log = Logger.getLogger(getlistpengajuanovertimeuser.class);

    public HashMap process(HashMap input) throws ParseException {

        BackendDBProcess dp = new BackendDBProcess();
        HashMap result = dp.getlistpengajuanovertimeuser(
                input.get("company_id").toString(),
                input.get("employee_id").toString(),
                input.get("start_date").toString(),
                input.get("end_date").toString()
        );
        dp = null;
        return result;
    }
}
