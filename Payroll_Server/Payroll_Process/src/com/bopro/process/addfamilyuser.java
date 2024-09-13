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
public class addfamilyuser {

    private static Logger log = Logger.getLogger(addfamilyuser.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.addfamilyuser(
                input.get("name").toString(),
                input.get("birth_place").toString(),
                input.get("birth_date").toString(),
                input.get("gender").toString(),
                input.get("religion").toString(),
                input.get("relation").toString(),
                input.get("nik").toString(),
                input.get("employee_id").toString(),
                input.get("company_id").toString()
        );
        return status;
    }
}
