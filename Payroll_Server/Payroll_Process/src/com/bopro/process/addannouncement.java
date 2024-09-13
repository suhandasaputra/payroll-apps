/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author azzam
 */
public class addannouncement {
    
 private static Logger log = Logger.getLogger(addannouncement.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.addannouncement(
                input.get("company_id").toString(),
                input.get("title").toString(),
                input.get("content").toString(),
                input.get("image").toString(),                
                input.get("user_id").toString(),                
                input.get("tanggal_berakhir").toString()                
        );
        return status;
    }

}
