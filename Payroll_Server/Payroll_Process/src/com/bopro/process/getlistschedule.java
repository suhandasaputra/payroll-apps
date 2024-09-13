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
 * @author suhanda
 */
public class getlistschedule {

    private static Logger log = Logger.getLogger(getlistschedule.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap result = dp.getlistschedule(
                input.get("company_id").toString(),
                input.get("start_date").toString(),
                input.get("end_date").toString());
        dp = null;
        return result;
    }
}
