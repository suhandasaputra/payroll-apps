/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author azzam
 */
public class getlistannouncementuser {

    private static Logger log = Logger.getLogger(getlistannouncementuser.class);

    public HashMap process(HashMap input) {

        BackendDBProcess dp = new BackendDBProcess();
        HashMap result = dp.getlistannouncementuser(               
                input.get("company_id").toString()
        );
        dp = null;
        return result;
    }

}
