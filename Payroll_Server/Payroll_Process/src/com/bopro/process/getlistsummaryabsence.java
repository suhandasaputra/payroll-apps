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
public class getlistsummaryabsence {

    private static Logger log = Logger.getLogger(getlistsummaryabsence.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap result = dp.getlistsummaryabsence(
                input.get("company_id").toString(),
                input.get("tahun").toString()
        );
        dp = null;
        return result;
    }
}
