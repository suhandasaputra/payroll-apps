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
public class addtimeoff {

    private static Logger log = Logger.getLogger(addtimeoff.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap status = dp.addtimeoff(
                input.get("timeoff_code").toString(),
                input.get("timeoff_desc").toString(),                
                input.get("company_id").toString(),
                input.get("user_id").toString(),
                input.get("absence_type").toString(),
                input.get("bucket_cuti").toString()
        );
        return status;
    }
}
