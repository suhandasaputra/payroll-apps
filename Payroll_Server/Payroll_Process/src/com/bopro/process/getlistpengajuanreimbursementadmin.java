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
public class getlistpengajuanreimbursementadmin {
    private static Logger log = Logger.getLogger(getlistpengajuanreimbursementadmin.class);

    public HashMap process(HashMap input) throws ParseException {

        BackendDBProcess dp = new BackendDBProcess();
        HashMap result = dp.getlistpengajuanreimbursementadmin(
                input.get("company_id").toString(),
                input.get("start_date").toString(),
                input.get("end_date").toString()
        );
        dp = null;
        return result;
    }
}
