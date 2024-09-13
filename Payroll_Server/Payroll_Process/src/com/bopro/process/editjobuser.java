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
public class editjobuser {

    private static Logger log = Logger.getLogger(editjobuser.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.editjobuser(
                input.get("company_name").toString(),
                input.get("business_sector").toString(),
                input.get("position").toString(),
                input.get("division").toString(),
                input.get("description").toString(),
                input.get("start_date").toString(),
                input.get("end_date").toString(),
                input.get("reference").toString(),
                input.get("file_paklaring").toString(),
                input.get("employee_id").toString(),
                input.get("company_id").toString()
        );
        return status;
    }
}
