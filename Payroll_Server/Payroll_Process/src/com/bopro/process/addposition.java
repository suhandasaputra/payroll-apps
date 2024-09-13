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
public class addposition {

    private static Logger log = Logger.getLogger(addposition.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap status = dp.addposition(
                input.get("position_id").toString(),
                input.get("position_desc").toString(),
                input.get("company_id").toString(),
                input.get("user_id").toString()
        );
        return status;
    }
}
