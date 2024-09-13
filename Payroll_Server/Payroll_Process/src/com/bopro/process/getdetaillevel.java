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
public class getdetaillevel {

    private static Logger log = Logger.getLogger(getdetaillevel.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.getdetaillevel(
                input.get("position_id").toString(),
                input.get("position_level").toString(),
                input.get("company_id").toString()
                );
        dp = null;
        return status;
    }
}
