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
public class livecheckout {

    private static Logger log = Logger.getLogger(livecheckout.class);

    public HashMap process(HashMap input) throws ParseException {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap status = dp.livecheckout(
                input.get("company_id").toString(),
                input.get("employee_id").toString(),
                input.get("checkout").toString(),
                input.get("description").toString(),
                input.get("user_id").toString(),
                input.get("location").toString()
        );
        return status;
    }
}
