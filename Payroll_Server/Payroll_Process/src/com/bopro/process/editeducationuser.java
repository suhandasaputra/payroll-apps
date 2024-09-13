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
public class editeducationuser {

    private static Logger log = Logger.getLogger(editeducationuser.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.editeducationuser(
                input.get("education_level").toString(),
                input.get("agency_name").toString(),
                input.get("certificate_number").toString(),
                input.get("graduation_year").toString(),
                input.get("formality_status").toString(),
                input.get("file_ijazah").toString(),
                input.get("reference").toString(),
                input.get("employee_id").toString(),
                input.get("company_id").toString()
                
        );
        return status;
    }
}
