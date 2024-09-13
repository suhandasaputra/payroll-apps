/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.database;

import com.bo.function.Calculatedate;
import com.bo.function.StringFunction;
import com.bopro.singleton.DatasourceEntryBackend;
import com.bo.parameter.FieldParameterMatapos;
import com.bo.parameter.RuleNameParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import org.apache.log4j.Logger;

public class BackendDBProcess {

    private static final Logger log = Logger.getLogger(BackendDBProcess.class);

    private void clearStatment(PreparedStatement stat) {
//        log.info("stat 2 : " + stat);
        if (stat != null) {
            try {
//                log.info("stat A");
                stat.clearBatch();
//                log.info("stat B");
                stat.clearParameters();
//                log.info("stat C");
                stat.close();
//                log.info("stat D");
                stat = null;
//                log.info("stat E");
            } catch (SQLException ex) {
//                log.error("clearStatment : " +ex.getMessage());
//                ex.printStackTrace();
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
//                log.error("clearDBConnection : "+ex.getMessage());
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
//                log.error("clearResultset : "+ex.getMessage());
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

    public boolean getEODDone() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT eoddone FROM configapp");
            rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("eoddone");
            }
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error("getMessageIncoming : " + ex.getMessage());

        } finally {
            clearResultset(rs);
            clearStatment(stat);
        }
        return false;
    }

    public boolean updateEODDone() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("UPDATE configapp SET eoddone=false");
            stat.executeUpdate();

        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error("updateEODDone : " + ex.getMessage());

        } finally {
            clearResultset(rs);
            clearStatment(stat);
        }
        return false;
    }

//    public String BrandSeq() {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("SELECT nextval('brand_seq'::regclass)");
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                return StringFunction.pad(rs.getString("nextval"), 2, "0", "Right");
//            }
//
//        } catch (SQLException ex) {
//            log.error("getListOrderTransactionPOS : " + ex.getMessage());
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return "00";
//    }
//    public void setNextStan() {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("SELECT count(*) as count from stanmanage where standate = current_date");
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                if (rs.getInt("count") == 0) {
//                    log.info("create next stan");
//                    clearStatment(stat);
//                    stat = conn.prepareStatement("INSERT INTO stanmanage(standate) VALUES (current_date)");
//                    stat.executeUpdate();
//                }
//            }
//        } catch (SQLException ex) {
//            log.error(ex.getMessage());
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//    }
//
//    public String getNextStan() {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        int currentStan = 1;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("SELECT stanno FROM stanmanage where standate = current_date");
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                currentStan = rs.getInt("stanno");
//            }
//            clearStatment(stat);
//            stat = conn.prepareStatement("UPDATE stanmanage SET stanno=? WHERE standate=current_date;");
//            stat.setInt(1, currentStan + 1);
//            stat.executeUpdate();
//        } catch (SQLException ex) {
//            log.error("getNextStan : " + ex.getMessage());
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return String.valueOf(currentStan);
//    }
    public HashMap signup(String bidang_usaha, String username, String jumlah, String companyname,
            String phonenumber, String email, String password) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_account where account_id = ?");
            stat.setString(1, email);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put(RuleNameParameter.resp_code, "0004");
                result.put(RuleNameParameter.resp_desc, "duplicate, already exist");
            } else {
                stat1 = conn.prepareStatement("insert into ops_account (account_id, account_level, account_name, password, company_id) values (?, ?, ?, ?, ?)");
                stat1.setString(1, email);
                stat1.setString(2, "1");
                stat1.setString(3, username);
                stat1.setString(4, password);
                stat1.setString(5, email);
                stat1.executeUpdate();
                stat1.close();

                stat1 = conn.prepareStatement("insert into int_company (company_id, company_name, phonenumber, bidang_usaha, jumlah_karyawan) values (?, ?, ?, ?, ?)");
                stat1.setString(1, email);
                stat1.setString(2, companyname);
                stat1.setInt(3, Integer.parseInt(phonenumber));
                stat1.setInt(4, Integer.parseInt(bidang_usaha));
                stat1.setInt(5, Integer.parseInt(jumlah));
                stat1.executeUpdate();

                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");

            }
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearStatment(stat1);
        }
        return result;
    }

    public HashMap validate(String user_id, String password) {
        HashMap result = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            result = new HashMap();
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.level_desc, c.company_name, d.employee_id, e.position_id, e.position_desc,  f.position_level, f.level_desc as position_level_desc from ops_account a \n"
                    + "left join account_level b on a.account_level = b.id_level::varchar \n"
                    + "left join int_company c on a.company_id = c.company_id \n"
                    + "left join ops_employee d on a.company_id = d.company_id and a.account_id = d.email \n"
                    + "left join crud_position e on d.position = e.position_id and a.company_id = e.company_id \n"
                    + "left join crud_position_level f on d.position_level = f.position_level and a.company_id = f.company_id and d.position = f.position_id \n"
                    + "where a.account_id = ? and a.account_status = '1'");
            stat.setString(1, user_id);
//            stat.setString(2, password);
            rs = stat.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    result.put("account_id", rs.getString("account_id"));
                    result.put("account_name", rs.getString("account_name"));
                    result.put("account_level", rs.getString("account_level"));
                    result.put("level_desc", rs.getString("level_desc"));
                    result.put("company_id", rs.getString("company_id"));
                    result.put("company_name", rs.getString("company_name"));
                    result.put("employee_id", rs.getString("employee_id"));
                    result.put("position_id", rs.getString("position_id"));
                    result.put("position_desc", rs.getString("position_desc"));
                    result.put("position_level", rs.getString("position_level"));
                    result.put("position_level_desc", rs.getString("position_level_desc"));
                    result.put(FieldParameterMatapos.resp_code, "0000");
                    result.put(FieldParameterMatapos.resp_desc, "Success");
                } else {
                    result.put(FieldParameterMatapos.resp_code, "0005");
                    result.put(FieldParameterMatapos.resp_desc, "password not match");
                }
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }

        } catch (SQLException e) {
            result.put(FieldParameterMatapos.resp_code, "0001");
            result.put(FieldParameterMatapos.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetreligion(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_religion where religion_status = '1' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("religion_id", rs.getString("religion_id"));
                ab.put("religion_name", rs.getString("religion_name"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetemployeestatus(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_employee_status where status = '1' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("status_id", rs.getString("status_id"));
                ab.put("status_desc", rs.getString("status_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetmarital(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from int_marital where marital_status = '1'");
//            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("marital_id", rs.getString("marital_id"));
                ab.put("marital_desc", rs.getString("marital_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetposition(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_position where position_status = '1' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("position_id", rs.getString("position_id"));
                ab.put("position_desc", rs.getString("position_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetlasteducation(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from int_education where education_status = '1'");
//            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("education_id", rs.getString("education_id"));
                ab.put("education_desc", rs.getString("education_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetmajority(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_majority where status = '1' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("majority_id", rs.getString("majority_id"));
                ab.put("majority_desc", rs.getString("majority_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetlevel(String id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_position_level where position_id = ? and company_id = ?");
            stat.setString(1, id);
            stat.setString(2, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("position_level", rs.getString("position_level"));
                ab.put("level_desc", rs.getString("level_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetbidangusaha() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from int_bidang_usaha where usaha_status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("id_usaha", rs.getString("id_usaha"));
                ab.put("usaha_desc", rs.getString("usaha_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetaccountlevel(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from account_level where status='1'");
//            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("id_level", rs.getString("id_level"));
                ab.put("level_desc", rs.getString("level_desc"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistemployee(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.position_desc from ops_employee a left join crud_position b on a.position = b.position_id and a.company_id = b.company_id where a.company_id = ? and a.active = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("employee_name", rs.getString("employee_name"));
                ab.put("birth_date", rs.getString("birth_date"));
                ab.put("birth_place", rs.getString("birth_place"));
                ab.put("phonenumber", rs.getString("phonenumber"));
                ab.put("position_desc", rs.getString("position_desc"));
                ab.put("resign_status", rs.getString("resign_status"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addemployee(String company_id, String employee_id, String employee_name, String birth_place,
            String birth_date, String ktp_address, String phonenumber, String email, String join_date, String nik,
            String npwp, String npwp_address, String kpp, String efin, String bpjs_tk_number, String religion,
            String employee_status, String marital_status, String position, String level, String last_education,
            String school, String majority, String user_id, String gender, String code_pos, String blood_type,
            String emergency_contact_name_1, String emergency_contact_phone_1, String emergency_contact_relation_1,
            String emergency_contact_name_2, String emergency_contact_phone_2, String emergency_contact_relation_2) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_employee (employee_id, employee_name, birth_place, birth_date, ktp_address, phonenumber, "
                    + "email, join_date, nik, npwp, npwp_address, kpp, efin, bpjs_tk_number, religion, employee_status, marital_status, position, "
                    + "position_level, last_education, school, majority, company_id, create_by, gender, code_pos, blood_type, "
                    + "emergency_contact_name_1, emergency_contact_phone_1, emergency_contact_relation_1, emergency_contact_name_2, emergency_contact_phone_2, emergency_contact_relation_2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, employee_id);
            stat.setString(2, employee_name);
            stat.setString(3, birth_place);
            stat.setString(4, birth_date);
            stat.setString(5, ktp_address);
            stat.setString(6, phonenumber);
            stat.setString(7, email);
            stat.setString(8, join_date);
            stat.setString(9, nik);
            stat.setString(10, npwp);
            stat.setString(11, npwp_address);
            stat.setString(12, kpp);
            stat.setString(13, efin);
            stat.setString(14, bpjs_tk_number);
            stat.setString(15, religion);
            stat.setString(16, employee_status);
            stat.setString(17, marital_status);
            stat.setString(18, position);
            stat.setString(19, level);
            stat.setString(20, last_education);
            stat.setString(21, school);
            stat.setString(22, majority);
            stat.setString(23, company_id);
            stat.setString(24, user_id);
            stat.setString(25, gender);
            stat.setString(26, code_pos);
            stat.setString(27, blood_type);

            stat.setString(28, emergency_contact_name_1);
            stat.setString(29, emergency_contact_phone_1);
            stat.setString(30, emergency_contact_relation_1);
            stat.setString(31, emergency_contact_name_2);
            stat.setString(32, emergency_contact_phone_2);
            stat.setString(33, emergency_contact_relation_2);

            stat.executeUpdate();

            stat.close();

            // warning , buatkan juga proses ini saat akhir tahun untuk membuar record baru
            stat = conn.prepareStatement("insert into ops_summary_daily "
                    + "(company_id, employee_id, tahun, summary_id) "
                    + "values "
                    + "(?,?,?,?)");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, String.valueOf(Year.now().getValue()));
            stat.setString(4, String.valueOf("sum_" + Year.now().getValue()) + "|" + company_id + "|" + employee_id);
            stat.executeUpdate();
            // warning

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editemployee(
            String company_id,
            String employee_id,
            String employee_name,
            String birth_place,
            String birth_date,
            String ktp_address,
            String phonenumber,
            String email,
            String join_date,
            String nik,
            String npwp,
            String npwp_address,
            String kpp,
            String efin,
            String bpjs_tk_number,
            String religion,
            String employee_status,
            String marital_status,
            String position,
            String level,
            String last_education,
            String school,
            String majority,
            String gender,
            String code_pos,
            String blood_type,
            String emergency_contact_name_1, String emergency_contact_phone_1, String emergency_contact_relation_1,
            String emergency_contact_name_2, String emergency_contact_phone_2, String emergency_contact_relation_2,
            String trv_plafon, String edu_plafon, String tol_plafon, String med_plafon, String oth_plafon,
            String trv_sisa, String edu_sisa, String tol_sisa, String med_sisa, String oth_sisa,
            String trv_terpakai, String edu_terpakai, String tol_terpakai, String med_terpakai, String oth_terpakai
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            int new_trv_plafon = Integer.parseInt(trv_plafon);
            int new_trv_terpakai = Integer.parseInt(trv_terpakai);
            int new_trv_sisa = new_trv_plafon - new_trv_terpakai;
            String hasil_trv_sisa = String.valueOf(new_trv_sisa);

            int new_tol_plafon = Integer.parseInt(tol_plafon);
            int new_tol_terpakai = Integer.parseInt(tol_terpakai);
            int new_tol_sisa = new_tol_plafon - new_tol_terpakai;
            String hasil_tol_sisa = String.valueOf(new_tol_sisa);

            int new_edu_plafon = Integer.parseInt(edu_plafon);
            int new_edu_terpakai = Integer.parseInt(edu_terpakai);
            int new_edu_sisa = new_edu_plafon - new_edu_terpakai;
            String hasil_edu_sisa = String.valueOf(new_edu_sisa);

            int new_med_plafon = Integer.parseInt(med_plafon);
            int new_med_terpakai = Integer.parseInt(med_terpakai);
            int new_med_sisa = new_med_plafon - new_med_terpakai;
            String hasil_med_sisa = String.valueOf(new_med_sisa);

            int new_oth_plafon = Integer.parseInt(oth_plafon);
            int new_oth_terpakai = Integer.parseInt(oth_terpakai);
            int new_oth_sisa = new_oth_plafon - new_oth_terpakai;
            String hasil_oth_sisa = String.valueOf(new_oth_sisa);

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_employee set "
                    + "employee_name = ?, "
                    + "birth_place = ?, "
                    + "birth_date = ?, "
                    + "ktp_address = ?, "
                    + "phonenumber = ?, "
                    + "email = ?, "
                    + "join_date = ?, "
                    //                    + "out_date = ?, "
                    + "npwp = ?, "
                    + "npwp_address = ?, "
                    + "kpp = ?, "
                    + "efin = ?, "
                    + "bpjs_tk_number = ?, "
                    + "nik = ?, "
                    + "school = ?, "
                    + "marital_status = ?, "
                    + "last_education = ?, "
                    + "majority = ?, "
                    + "employee_status = ?, "
                    + "religion = ?, "
                    + "position = ?, "
                    + "position_level = ?, "
                    + "gender = ?, "
                    + "code_pos = ?, "
                    + "blood_type = ?, "
                    + "emergency_contact_name_1 = ?, "
                    + "emergency_contact_phone_1 = ?, "
                    + "emergency_contact_relation_1 = ?, "
                    + "emergency_contact_name_2 = ?, "
                    + "emergency_contact_phone_2 = ?, "
                    + "emergency_contact_relation_2 = ?, "
                    + "trv_plafon = ?, "
                    + "edu_plafon = ?, "
                    + "tol_plafon = ?, "
                    + "med_plafon = ?, "
                    + "oth_plafon = ?, "
                    + "trv_sisa = ?, "
                    + "edu_sisa = ?, "
                    + "tol_sisa = ?, "
                    + "med_sisa = ?, "
                    + "oth_sisa = ?, "
                    + "trv_terpakai = ?, "
                    + "edu_terpakai = ?, "
                    + "tol_terpakai = ?, "
                    + "med_terpakai = ?, "
                    + "oth_terpakai = ? "
                    + "where company_id = ? and employee_id = ?; ");
            stat.setString(1, employee_name);
            stat.setString(2, birth_place);
            stat.setString(3, birth_date);
            stat.setString(4, ktp_address);
            stat.setString(5, phonenumber);
            stat.setString(6, email);
            stat.setString(7, join_date);
//            stat.setString(8, user_name);
            stat.setString(8, npwp);
            stat.setString(9, npwp_address);
            stat.setString(10, kpp);
            stat.setString(11, efin);
            stat.setString(12, bpjs_tk_number);
            stat.setString(13, nik);
            stat.setString(14, school);
            stat.setString(15, marital_status);
            stat.setString(16, last_education);
            stat.setString(17, majority);
            stat.setString(18, employee_status);
            stat.setString(19, religion);
            stat.setString(20, position);
            stat.setString(21, level);
            stat.setString(22, gender);
            stat.setString(23, code_pos);
            stat.setString(24, blood_type);
            stat.setString(25, emergency_contact_name_1);
            stat.setString(26, emergency_contact_phone_1);
            stat.setString(27, emergency_contact_relation_1);
            stat.setString(28, emergency_contact_name_2);
            stat.setString(29, emergency_contact_phone_2);
            stat.setString(30, emergency_contact_relation_2);
            stat.setString(31, trv_plafon);
            stat.setString(32, edu_plafon);
            stat.setString(33, tol_plafon);
            stat.setString(34, med_plafon);
            stat.setString(35, oth_plafon);
            stat.setString(36, hasil_trv_sisa);
            stat.setString(37, hasil_edu_sisa);
            stat.setString(38, hasil_tol_sisa);
            stat.setString(39, hasil_med_sisa);
            stat.setString(40, hasil_oth_sisa);
            stat.setString(41, trv_terpakai);
            stat.setString(42, edu_terpakai);
            stat.setString(43, tol_terpakai);
            stat.setString(44, med_terpakai);
            stat.setString(45, oth_terpakai);
            stat.setString(46, company_id);
            stat.setString(47, employee_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteemployee(String company_id, String employee_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_employee set active='0' where company_id=? and employee_id=?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailemployee(String employee_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.position_desc, c.marital_desc, d.education_desc, e.majority_desc, f.status_desc, g.religion_name, h.level_desc from ops_employee a \n"
                    + "left join crud_position b on a.position = b.position_id and a.company_id = b.company_id \n"
                    + "left join int_marital c on a.marital_status = c.marital_id \n"
                    + "left join int_education d on a.last_education = d.education_id \n"
                    + "left join crud_majority e on a.majority = e.majority_id and a.company_id = e.company_id \n"
                    + "left join crud_employee_status f on a.employee_status = f.status_id and a.company_id = f.company_id \n"
                    + "left join crud_religion g on a.religion = g.religion_id and a.company_id = g.company_id \n"
                    + "left join crud_position_level h on a.position_level = h.position_level and a.company_id = h.company_id and a.position = h.position_id \n"
                    + "where a.company_id = ? and a.employee_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            rs = stat.executeQuery();
            if (rs.next()) {

                int trv_plafon = Integer.parseInt(rs.getString("trv_plafon"));
                int edu_plafon = Integer.parseInt(rs.getString("edu_plafon"));
                int med_plafon = Integer.parseInt(rs.getString("med_plafon"));
                int tol_plafon = Integer.parseInt(rs.getString("tol_plafon"));
                int oth_plafon = Integer.parseInt(rs.getString("oth_plafon"));
                int total_plafon = trv_plafon + edu_plafon + med_plafon + tol_plafon + oth_plafon;
                String hasil_total_plafon = String.valueOf(total_plafon);

                result.put("total_plafon", hasil_total_plafon);
                result.put("employee_id", rs.getString("employee_id"));
                result.put("employee_name", rs.getString("employee_name"));
                result.put("birth_place", rs.getString("birth_place"));
                result.put("birth_date", rs.getString("birth_date"));
                result.put("ktp_address", rs.getString("ktp_address"));
                result.put("phonenumber", rs.getString("phonenumber"));
                result.put("email", rs.getString("email"));
                result.put("join_date", rs.getString("join_date"));
                result.put("out_date", rs.getString("out_date"));
                result.put("npwp", rs.getString("npwp"));
                result.put("npwp_address", rs.getString("npwp_address"));
                result.put("kpp", rs.getString("kpp"));
                result.put("efin", rs.getString("efin"));
                result.put("bpjs_tk_number", rs.getString("bpjs_tk_number"));
                result.put("nik", rs.getString("nik"));
                result.put("school", rs.getString("school"));
                result.put("marital_status", rs.getString("marital_status"));
                result.put("last_education", rs.getString("last_education"));
                result.put("majority", rs.getString("majority"));
                result.put("employee_status", rs.getString("employee_status"));
                result.put("religion", rs.getString("religion"));
                result.put("position", rs.getString("position"));
                result.put("position_level", rs.getString("position_level"));
                result.put("company_id", rs.getString("company_id"));
                result.put("create_date", rs.getString("create_date"));
                result.put("record_id", rs.getString("record_id"));
                result.put("position_desc", rs.getString("position_desc"));
                result.put("level_desc", rs.getString("level_desc"));
                result.put("marital_desc", rs.getString("marital_desc"));
                result.put("education_desc", rs.getString("education_desc"));
                result.put("majority_desc", rs.getString("majority_desc"));
                result.put("status_desc", rs.getString("status_desc"));
                result.put("religion_name", rs.getString("religion_name"));
                result.put("resign_status", rs.getString("resign_status"));
                result.put("length_of_work", rs.getString("length_of_work"));
                result.put("gender", rs.getString("gender"));
                result.put("blood_type", rs.getString("blood_type"));
                result.put("code_pos", rs.getString("code_pos"));

                result.put("emergency_contact_name_1", rs.getString("emergency_contact_name_1"));
                result.put("emergency_contact_phone_1", rs.getString("emergency_contact_phone_1"));
                result.put("emergency_contact_relation_1", rs.getString("emergency_contact_relation_1"));
                result.put("emergency_contact_name_2", rs.getString("emergency_contact_name_2"));
                result.put("emergency_contact_phone_2", rs.getString("emergency_contact_phone_2"));
                result.put("emergency_contact_relation_2", rs.getString("emergency_contact_relation_2"));

                result.put("trv_plafon", rs.getString("trv_plafon"));
                result.put("trv_sisa", rs.getString("trv_sisa"));
                result.put("trv_terpakai", rs.getString("trv_terpakai"));
                result.put("edu_plafon", rs.getString("edu_plafon"));
                result.put("edu_sisa", rs.getString("edu_sisa"));
                result.put("edu_terpakai", rs.getString("edu_terpakai"));
                result.put("tol_plafon", rs.getString("tol_plafon"));
                result.put("tol_sisa", rs.getString("tol_sisa"));
                result.put("tol_terpakai", rs.getString("tol_terpakai"));
                result.put("med_plafon", rs.getString("med_plafon"));
                result.put("med_sisa", rs.getString("med_sisa"));
                result.put("med_terpakai", rs.getString("med_terpakai"));
                result.put("oth_plafon", rs.getString("oth_plafon"));
                result.put("oth_sisa", rs.getString("oth_sisa"));
                result.put("oth_terpakai", rs.getString("oth_terpakai"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistuser(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.level_desc from ops_account a left join account_level b on a.account_level = b.id_level::varchar where a.account_status = '1' and a.company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("account_id", rs.getString("account_id"));
                ab.put("account_level", rs.getString("account_level"));
                ab.put("account_name", rs.getString("account_name"));
                ab.put("level_desc", rs.getString("level_desc"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap adduser(
            String company_id,
            String account_id,
            String user_id,
            String account_level,
            String account_name,
            String password) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_account (account_id,account_level,account_name,password,company_id,create_by) values ((select email from ops_employee where company_id = '" + company_id + "' and employee_id = '" + account_id + "'),?,?,?,?,?)");
//            stat.setString(1, account_id);
            stat.setString(1, account_level);
            stat.setString(2, account_name);
            stat.setString(3, password);
            stat.setString(4, company_id);
            stat.setString(5, user_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap edituser(
            String company_id,
            String account_id,
            String account_name,
            String account_level,
            String user_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_account set "
                    + "account_level = ?, "
                    + "account_name = ? "
                    + "where company_id = ? and account_id = ?");
            stat.setString(1, account_level);
            stat.setString(2, account_name);
            stat.setString(3, company_id);
            stat.setString(4, account_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteuser(String company_id, String account_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_account set account_status='0' where company_id=? and account_id=?");
            stat.setString(1, company_id);
            stat.setString(2, account_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailuser(String account_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.level_desc from ops_account a \n"
                    + "left join account_level b on a.account_level = b.id_level::varchar \n"
                    + "where company_id = ? and account_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, account_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("account_id", rs.getString("account_id"));
                result.put("account_level", rs.getString("account_level"));
                result.put("account_name", rs.getString("account_name"));
                result.put("level_desc", rs.getString("level_desc"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }

        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap closeresign(String company_id, String employee_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_employee set resign_status='1', out_date=current_date where company_id=? and employee_id=?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap openresign(String company_id, String employee_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_employee set resign_status='0', out_date='' where company_id=? and employee_id=?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistshift(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_shift where company_id = ? and shift_status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("shift_id", rs.getString("shift_id"));
                ab.put("shift_name", rs.getString("shift_name"));
                ab.put("start_time", rs.getString("start_time"));
                ab.put("end_time", rs.getString("end_time"));
                ab.put("create_by", rs.getString("create_by"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addshift(
            String shift_id,
            String shift_name,
            String start_time,
            String end_time,
            String company_id,
            String create_by) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_shift (shift_id,shift_name,start_time,end_time,company_id,create_by) values (?,?,?,?,?,?)");
            stat.setString(1, shift_id);
            stat.setString(2, shift_name);
            stat.setString(3, start_time);
            stat.setString(4, end_time);
            stat.setString(5, company_id);
            stat.setString(6, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editshift(
            String shift_id,
            String shift_name,
            String start_time,
            String end_time,
            String company_id,
            String user_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_shift set "
                    + "shift_name = ?, "
                    + "start_time = ?, "
                    + "end_time = ? "
                    + "where company_id = ? and shift_id = ?");
            stat.setString(1, shift_name);
            stat.setString(2, start_time);
            stat.setString(3, end_time);
            stat.setString(4, company_id);
            stat.setString(5, shift_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteshift(String company_id, String shift_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_shift set shift_status='0' where company_id=? and shift_id=?");
            stat.setString(1, company_id);
            stat.setString(2, shift_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailshift(String shift_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_shift where company_id = ? and  shift_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, shift_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("shift_id", rs.getString("shift_id"));
                result.put("shift_name", rs.getString("shift_name"));
                result.put("start_time", rs.getString("start_time"));
                result.put("end_time", rs.getString("end_time"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistemployeestatus(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_employee_status where company_id = ? and status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("status_id", rs.getString("status_id"));
                ab.put("status_desc", rs.getString("status_desc"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addemployeestatus(
            String status_id,
            String status_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_employee_status (status_id,status_desc,company_id,create_by) values (?,?,?,?)");
            stat.setString(1, status_id);
            stat.setString(2, status_desc);
            stat.setString(3, company_id);
            stat.setString(4, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editemployeestatus(
            String status_id,
            String status_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_employee_status set "
                    + "status_desc = ? "
                    + "where company_id = ? and status_id = ?");
            stat.setString(1, status_desc);
            stat.setString(2, company_id);
            stat.setString(3, status_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteemployeestatus(String company_id, String status_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_employee_status set status='0' where company_id=? and status_id=?");
            stat.setString(1, company_id);
            stat.setString(2, status_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailemployeestatus(String status_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_employee_status where company_id = ? and  status_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, status_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("status_id", rs.getString("status_id"));
                result.put("status_desc", rs.getString("status_desc"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistmajority(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_majority where company_id = ? and status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("majority_id", rs.getString("majority_id"));
                ab.put("majority_desc", rs.getString("majority_desc"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addmajority(
            String majority_id,
            String majority_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_majority (majority_id,majority_desc,company_id,create_by) values (?,?,?,?)");
            stat.setString(1, majority_id);
            stat.setString(2, majority_desc);
            stat.setString(3, company_id);
            stat.setString(4, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editmajority(
            String majority_id,
            String majority_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_majority set "
                    + "majority_desc = ? "
                    + "where company_id = ? and majority_id = ?");
            stat.setString(1, majority_desc);
            stat.setString(2, company_id);
            stat.setString(3, majority_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletemajority(String company_id, String majority_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_majority set status='0' where company_id=? and majority_id=?");
            stat.setString(1, company_id);
            stat.setString(2, majority_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailmajority(String majority_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_majority where company_id = ? and  majority_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, majority_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("majority_id", rs.getString("majority_id"));
                result.put("majority_desc", rs.getString("majority_desc"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistposition(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_position where company_id = ? and position_status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("position_id", rs.getString("position_id"));
                ab.put("position_desc", rs.getString("position_desc"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addposition(
            String position_id,
            String position_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_position (position_id,position_desc,company_id,create_by) values (?,?,?,?)");
            stat.setString(1, position_id);
            stat.setString(2, position_desc);
            stat.setString(3, company_id);
            stat.setString(4, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editposition(
            String position_id,
            String position_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_position set "
                    + "position_desc = ? "
                    + "where company_id = ? and position_id = ?");
            stat.setString(1, position_desc);
            stat.setString(2, company_id);
            stat.setString(3, position_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteposition(String company_id, String position_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_position set position_status='0' where company_id=? and position_id=?");
            stat.setString(1, company_id);
            stat.setString(2, position_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailposition(String position_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_position where company_id = ? and  position_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, position_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("position_id", rs.getString("position_id"));
                result.put("position_desc", rs.getString("position_desc"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistreligion(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_religion where company_id = ? and religion_status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("religion_id", rs.getString("religion_id"));
                ab.put("religion_name", rs.getString("religion_name"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addreligion(
            String religion_id,
            String religion_name,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_religion (religion_id,religion_name,company_id,create_by) values (?,?,?,?)");
            stat.setString(1, religion_id);
            stat.setString(2, religion_name);
            stat.setString(3, company_id);
            stat.setString(4, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editreligion(
            String religion_id,
            String religion_name,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_religion set "
                    + "religion_name = ? "
                    + "where company_id = ? and religion_id = ?");
            stat.setString(1, religion_name);
            stat.setString(2, company_id);
            stat.setString(3, religion_id);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletereligion(String company_id, String religion_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_religion set religion_status='0' where company_id=? and religion_id=?");
            stat.setString(1, company_id);
            stat.setString(2, religion_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailreligion(String religion_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_religion where company_id = ? and  religion_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, religion_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("religion_id", rs.getString("religion_id"));
                result.put("religion_name", rs.getString("religion_name"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistlevel(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.position_desc from crud_position_level a left join crud_position b on a.position_id = b.position_id where a.company_id = ? and a.level_status = '1' and b.company_id = ?");
            stat.setString(1, company_id);
            stat.setString(2, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("position_id", rs.getString("position_id"));
                ab.put("position_desc", rs.getString("position_desc"));
                ab.put("position_level", rs.getString("position_level"));
                ab.put("level_desc", rs.getString("level_desc"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addlevel(
            String position_id,
            String position_level,
            String level_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_position_level (position_id,position_level,level_desc,company_id,create_by) values (?,?,?,?,?)");
            stat.setString(1, position_id);
            stat.setString(2, position_level);
            stat.setString(3, level_desc);
            stat.setString(4, company_id);
            stat.setString(5, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editlevel(
            String position_id,
            String position_level,
            String level_desc,
            String company_id,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_position_level set "
                    + "level_desc = ? "
                    + "where company_id = ? and position_id = ? and position_level = ?");
            stat.setString(1, level_desc);
            stat.setString(2, company_id);
            stat.setString(3, position_id);
            stat.setString(4, position_level);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletelevel(String company_id, String position_id, String position_level) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_position_level set level_status='0' where company_id=? and position_id=? and position_level=?");
            stat.setString(1, company_id);
            stat.setString(2, position_id);
            stat.setString(3, position_level);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetaillevel(String position_id, String position_level, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.position_desc from crud_position_level a left join crud_position b on a.position_id = b.position_id and a.company_id = b.company_id \n"
                    + "where a.position_id = ? and a.position_level = ? and a.company_id = ?");
            stat.setString(1, position_id);
            stat.setString(2, position_level);
            stat.setString(3, company_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("position_id", rs.getString("position_id"));
                result.put("position_desc", rs.getString("position_desc"));
                result.put("position_level", rs.getString("position_level"));
                result.put("level_desc", rs.getString("level_desc"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlisttimeoff(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_timeoff where company_id = ? and timeoff_status = '1'");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("timeoff_code", rs.getString("timeoff_code"));
                ab.put("timeoff_desc", rs.getString("timeoff_desc"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                ab.put("absence_type", rs.getString("absence_type"));
                ab.put("bucket_cuti", rs.getString("bucket_cuti"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addtimeoff(
            String timeoff_code,
            String timeoff_desc,
            String company_id,
            String create_by,
            String absence_type,
            String bucket_cuti
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        PreparedStatement stat1 = null;
        ResultSet rs1 = null;

        StringJoiner joiner_kode_cuti = null;
        StringJoiner joiner_cuti_desc = null;
        StringJoiner joiner_cuti_bucket = null;
        StringJoiner joiner_cuti_terpakai = null;
        StringJoiner joiner_cuti_sisa = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into crud_timeoff (timeoff_code,timeoff_desc,company_id,create_by,absence_type,bucket_cuti) values (?,?,?,?,?,?)");
            stat.setString(1, timeoff_code);
            stat.setString(2, timeoff_desc);
            stat.setString(3, company_id);
            stat.setString(4, create_by);
            stat.setString(5, absence_type);

            if ("CUT".equals(absence_type)) {
                stat.setString(6, bucket_cuti);

                stat1 = conn.prepareStatement("select * from ops_summary_daily where company_id = ?");
                stat1.setString(1, company_id);
                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    
                    
                    
                    

                    joiner_kode_cuti = new StringJoiner("|");
                    joiner_cuti_desc = new StringJoiner("|");
                    joiner_cuti_bucket = new StringJoiner("|");
                    joiner_cuti_terpakai = new StringJoiner("|");
                    joiner_cuti_sisa = new StringJoiner("|");

                    joiner_kode_cuti.add(rs1.getString("cuti_list"));
                    joiner_kode_cuti.add(timeoff_code);
                    joiner_cuti_desc.add(rs1.getString("cuti_desc"));
                    joiner_cuti_desc.add(timeoff_desc);
                    joiner_cuti_bucket.add(rs1.getString("cuti_bucket"));
                    joiner_cuti_bucket.add(bucket_cuti);
                    joiner_cuti_terpakai.add(rs1.getString("cuti_terpakai"));
                    joiner_cuti_terpakai.add("0");
                    joiner_cuti_sisa.add(rs1.getString("cuti_sisa"));
                    joiner_cuti_sisa.add(bucket_cuti);

//                    stat1.close();
                    stat1 = null;

                    stat1 = conn.prepareStatement("update ops_summary_daily set "
                            + "cuti_list = ?,"
                            + "cuti_desc = ?,"
                            + "cuti_bucket = ?,"
                            + "cuti_terpakai = ?,"
                            + "cuti_sisa = ?"
                            + "where summary_id = ?");
                    stat1.setString(1, joiner_kode_cuti.toString());
                    stat1.setString(2, joiner_cuti_desc.toString());
                    stat1.setString(3, joiner_cuti_bucket.toString());
                    stat1.setString(4, joiner_cuti_terpakai.toString());
                    stat1.setString(5, joiner_cuti_sisa.toString());
                    stat1.setString(6, rs1.getString("summary_id"));
                    stat1.executeUpdate();
                    
                    
                    
                    
                    
                    
                    
                }
            } else {
                stat.setString(6, "0");
            }
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return result;
    }

    public HashMap edittimeoff(
            String timeoff_code,
            String timeoff_desc,
            String company_id,
            String create_by,
            String absence_type,
            String bucket_cuti
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        int index = 0;
        int new_bucket = Integer.parseInt(bucket_cuti);
        int new_cuti_sisa = 0;
        int new_cuti_terpakai = 0;

        int status_go = 1;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and tahun = ?");
            stat.setString(1, company_id);
            stat.setString(2, String.valueOf(Year.now().getValue()));
            rs = stat.executeQuery();
            while (rs.next()) {
                List<String> list_kodecuti = Arrays.asList(rs.getString("cuti_list").split("\\|"));
                List<String> list_terpakaicuti = Arrays.asList(rs.getString("cuti_terpakai").split("\\|"));
                for (int i = 0; i < list_kodecuti.size(); i++) {
                    if (list_kodecuti.get(i).equals(timeoff_code)) {
                        index = i;
                    }
                }
                System.out.println("ini adalah cuti terpakai : " + list_terpakaicuti.get(index));
                new_cuti_terpakai = Integer.parseInt(list_terpakaicuti.get(index));

                if (new_bucket < new_cuti_terpakai) {
                    status_go = 0;
                }
            }

            if (status_go == 0) {
                result.put(RuleNameParameter.resp_code, "0007");
                result.put(RuleNameParameter.resp_desc, "cuti updates cannot be smaller");
            } else {
                stat = null;
                rs = null;

                stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and tahun = ?");
                stat.setString(1, company_id);
                stat.setString(2, String.valueOf(Year.now().getValue()));
                rs = stat.executeQuery();
                while (rs.next()) {

                    List<String> list_kodecuti = Arrays.asList(rs.getString("cuti_list").split("\\|"));
                    List<String> list_desccuti = Arrays.asList(rs.getString("cuti_desc").split("\\|"));
                    List<String> list_bucketcuti = Arrays.asList(rs.getString("cuti_bucket").split("\\|"));
                    List<String> list_sisacuti = Arrays.asList(rs.getString("cuti_sisa").split("\\|"));
                    List<String> list_terpakaicuti = Arrays.asList(rs.getString("cuti_terpakai").split("\\|"));

                    new_cuti_terpakai = Integer.parseInt(list_terpakaicuti.get(index));

                    new_cuti_sisa = new_bucket - new_cuti_terpakai;

//                      update list
                    list_desccuti.set(index, String.valueOf(timeoff_desc));
                    list_bucketcuti.set(index, String.valueOf(bucket_cuti));
                    list_sisacuti.set(index, String.valueOf(new_cuti_sisa));

//                      membuat list delimiter
                    StringJoiner joindesccuti = new StringJoiner("|");
                    StringJoiner joinbucketcuti = new StringJoiner("|");
                    StringJoiner joinsisacuti = new StringJoiner("|");

                    for (int i = 0; i < list_kodecuti.size(); i++) {
                        joindesccuti.add(list_desccuti.get(i));
                        joinbucketcuti.add(list_bucketcuti.get(i));
                        joinsisacuti.add(list_sisacuti.get(i));
                    }

                    stat = null;

                    //update cuti saat approve request cuti
                    stat = conn.prepareStatement("update ops_summary_daily set "
                            + "cuti_desc = ?,"
                            + "cuti_bucket = ?,"
                            + "cuti_sisa = ?"
                            + "where summary_id = ?");
                    stat.setString(1, joindesccuti.toString());
                    stat.setString(2, joinbucketcuti.toString());
                    stat.setString(3, joinsisacuti.toString());
                    stat.setString(4, rs.getString("summary_id"));
                    stat.executeUpdate();

                    stat = null;

                    stat = conn.prepareStatement("update crud_timeoff set "
                            + "timeoff_desc = ?, "
                            + "bucket_cuti = ?, "
                            + "absence_type = ? "
                            + "where company_id = ? and timeoff_code = ?");
                    stat.setString(1, timeoff_desc);
                    stat.setString(2, bucket_cuti);
                    stat.setString(3, absence_type);
                    stat.setString(4, company_id);
                    stat.setString(5, timeoff_code);
                    stat.executeUpdate();

                }
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletetimeoff(String company_id, String timeoff_code) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update crud_timeoff set timeoff_status='0' where company_id=? and timeoff_code=?");
            stat.setString(1, company_id);
            stat.setString(2, timeoff_code);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailtimeoff(String timeoff_code, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from crud_timeoff where company_id = ? and  timeoff_code = ?");
            stat.setString(1, company_id);
            stat.setString(2, timeoff_code);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("timeoff_code", rs.getString("timeoff_code"));
                result.put("timeoff_desc", rs.getString("timeoff_desc"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("company_id", rs.getString("company_id"));
                result.put("absence_type", rs.getString("absence_type"));
                result.put("bucket_cuti", rs.getString("bucket_cuti"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistschedule(String company_id, String startdate, String enddate) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//              yang ini toong di cek lagi setelah azzam masuk, di tes dengan data yang banyak
//              stat = conn.prepareStatement("select * from ops_schedule where company_id = ? and date >= ? and date <= ? and schedule_status = '1'");
            stat = conn.prepareStatement("select a.*, b.employee_name, c.shift_name from ops_schedule a left join ops_employee b on a.company_id = b.company_id and a.employee_id = b.employee_id \n"
                    + "left join crud_shift c on a.shift_id = c.shift_id and a.company_id = c.company_id \n"
                    + "where a.company_id = ? and a.date >= ? and a.date <= ? and a.schedule_status = '1'");
            stat.setString(1, company_id);
            stat.setString(2, startdate);
            stat.setString(3, enddate);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();

                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("employee_name", rs.getString("employee_name"));
                ab.put("shift_id", rs.getString("shift_id"));
                ab.put("shift_name", rs.getString("shift_name"));
                ab.put("date", rs.getString("date"));
                ab.put("create_by", rs.getString("create_by"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addschedule(
            String company_id,
            String employee_id,
            String shift_id,
            String date,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_schedule (company_id,employee_id,shift_id,date,create_by) values (?,?,?,?,?)");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, shift_id);
            stat.setString(4, date);
            stat.setString(5, create_by);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editschedule(
            String company_id,
            String employee_id,
            String shift_id,
            String date,
            String create_by
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_schedule set "
                    + "shift_id = ? "
                    + "where company_id = ? and employee_id = ? and date = ?");
            stat.setString(1, shift_id);
            stat.setString(2, company_id);
            stat.setString(3, employee_id);
            stat.setString(4, date);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteschedule(String company_id, String employee_id, String date, String create_by) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_schedule set schedule_status='0' where company_id=? and employee_id=? and date=?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, date);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailschedule(String company_id, String employee_id, String date) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.employee_name, c.shift_name from ops_schedule a "
                    + "left join ops_employee b on a.employee_id = b.employee_id and a.company_id = b.company_id "
                    + "left join crud_shift c on a.shift_id = c.shift_id and a.company_id = c.company_id where a.company_id = ? and  a.employee_id = ? and a.date = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, date);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("employee_name", rs.getString("employee_name"));
                result.put("shift_id", rs.getString("shift_id"));
                result.put("shift_name", rs.getString("shift_name"));
                result.put("date", rs.getString("date"));
                result.put("create_by", rs.getString("create_by"));
                result.put("create_date", rs.getString("create_date"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetemployee(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select employee_id, employee_name from ops_employee where active = '1' and resign_status = '0' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("employee_name", rs.getString("employee_name"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap optiongetshift(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List l = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select shift_id, shift_name from crud_shift where shift_status = '1' and company_id = ?");
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("shift_id", rs.getString("shift_id"));
                ab.put("shift_name", rs.getString("shift_name"));
                l.add(ab);
                ab = null;
            }
            result.put("list", l);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            l = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap changepassword(String company_id, String user_id, String oldpass, String newpass) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select password from ops_account where company_id = ? and account_id = ? and password = ?");
            stat.setString(1, company_id);
            stat.setString(2, user_id);
            stat.setString(3, oldpass);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("update ops_account set password = ? where company_id = ? and account_id = ?");
                stat.setString(1, newpass);
                stat.setString(2, company_id);
                stat.setString(3, user_id);
                stat.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0002");
                result.put(RuleNameParameter.resp_desc, "not found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap generateabsence() {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;

        PreparedStatement stat2 = null;
        ResultSet rs2 = null;

        String mancap = "";

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = dateObj.format(formatter);

        String joindate = "";
        String tahunkerja = "";
        int thnkerjaold = 0;
        int tahunkerjanew = 0;

        HashMap bedanya = new HashMap();

        String joinedcuti_list = "";
        String joinedcuti_bucket = "";
        String joinedcuti_terpakai = "";
        String joinedcuti_sisa = "";
        String joinedcuti_desc = "";

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select employee_id, company_id, join_date, length_of_work from ops_employee where active = '1' and resign_status = '0'");
            rs = stat.executeQuery();
            while (rs.next()) {

                stat2 = conn.prepareStatement("select a.shift_id, b.shift_name, b.start_time, b.end_time from ops_schedule a "
                        + "left join crud_shift b on a.company_id = b.company_id and a.shift_id = b. shift_id \n"
                        + "where a.company_id = '" + rs.getString("company_id") + "' and a.employee_id = '" + rs.getString("employee_id") + "' and a.date::date = CURRENT_DATE");
                rs2 = stat2.executeQuery();
                while (rs2.next()) {

                    mancap = rs.getString("company_id") + "|" + rs.getString("employee_id") + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS();
                    joindate = rs.getString("join_date");
                    tahunkerja = rs.getString("length_of_work");

//                generate absen harian ke tabel realtime absen
                    stat = conn.prepareStatement("insert into ops_realtime_absence (reference, company_id, employee_id, shift, schedule_in, schedule_out) values (?, ?, ?, ?, ?, ?)");
                    stat.setString(1, mancap);
                    stat.setString(2, rs.getString("company_id"));
                    stat.setString(3, rs.getString("employee_id"));
                    stat.setString(4, rs2.getString("shift_id"));
                    stat.setString(5, rs2.getString("start_time"));
                    stat.setString(6, rs2.getString("end_time"));
                    stat.executeUpdate();

//                membuat perbandingan lama kerja
                    thnkerjaold = Integer.parseInt(tahunkerja);
                    bedanya = Calculatedate.calculatedate(joindate, today);
                    tahunkerjanew = Integer.parseInt(bedanya.get("differentyear").toString());

                    if (tahunkerjanew > thnkerjaold) {
//                    membuat list delimiter
                        StringJoiner cuti_list = new StringJoiner("|");
                        StringJoiner cuti_bucket = new StringJoiner("|");
                        StringJoiner cuti_terpakai = new StringJoiner("|");
                        StringJoiner cuti_sisa = new StringJoiner("|");
                        StringJoiner cuti_desc = new StringJoiner("|");

                        stat1 = conn.prepareStatement("select * from crud_timeoff where company_id = ? and absence_type = ? and timeoff_status = ?");
                        stat1.setString(1, rs.getString("company_id"));
                        stat1.setString(2, "CUT");
                        stat1.setString(3, "1");
                        rs1 = stat1.executeQuery();
                        while (rs1.next()) {
                            cuti_list.add(rs1.getString("timeoff_code"));
                            cuti_bucket.add(rs1.getString("bucket_cuti"));
                            cuti_terpakai.add("0");
                            cuti_sisa.add("0");
                            cuti_desc.add(rs1.getString("timeoff_desc"));
                        }

                        stat.close();
                        stat = null;

                        joinedcuti_list = cuti_list.toString();
                        joinedcuti_bucket = cuti_bucket.toString();
                        joinedcuti_terpakai = cuti_terpakai.toString();
                        joinedcuti_sisa = cuti_sisa.toString();
                        joinedcuti_desc = cuti_desc.toString();
//                  generate cuti saat sudah satu tahun atau tahun berikutnya
                        stat = conn.prepareStatement("update ops_summary_daily set "
                                + "cuti_list = ?,"
                                + "cuti_bucket = ?,"
                                + "cuti_terpakai = ?,"
                                + "cuti_sisa = ?,"
                                + "cuti_desc = ?"
                                + "where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, joinedcuti_list);
                        stat.setString(2, joinedcuti_bucket);
                        stat.setString(3, joinedcuti_terpakai);
                        stat.setString(4, joinedcuti_sisa);
                        stat.setString(5, joinedcuti_desc);
                        stat.setString(6, rs.getString("company_id"));
                        stat.setString(7, rs.getString("employee_id"));
                        stat.setString(8, String.valueOf(Year.now().getValue()));
                        stat.executeUpdate();

                        stat.close();
                        stat = null;

                        stat = conn.prepareStatement("update ops_employee set "
                                + "length_of_work = ?"
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, String.valueOf(tahunkerjanew));
                        stat.setString(2, rs.getString("company_id"));
                        stat.setString(3, rs.getString("employee_id"));

                        stat.executeUpdate();
                    }

                }

            }
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
            clearAllConnStatRS(conn, stat2, rs2);
        }
        return result;
    }

////////////////////////////////////////////////////////////////////////////////    
    public HashMap pengajuanabsensi(
            String company_id,
            String employee_id,
            String date_absensi,
            String checkin,
            String checkout,
            String description,
            String date_request,
            String user_id,
            String absence_type,
            String schema) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_schedule where company_id = ? and employee_id = ? and date = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, date_absensi);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("insert into ops_request_absence ("
                        + "reference,"
                        + "employee_id,"
                        + "company_id,"
                        + "date_absensi,"
                        + "checkin,"
                        + "checkout,"
                        + "description,"
                        + "account_id,"
                        + "absence_type,"
                        + "date_request,"
                        + "schema"
                        + ")"
                        + "values (?,?,?,?,?,?,?,?,?,?,?)");
                stat.setString(1, "req_" + company_id + "|" + employee_id + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS());
                stat.setString(2, employee_id);
                stat.setString(3, company_id);
                stat.setString(4, date_absensi);
                stat.setString(5, checkin);
                stat.setString(6, checkout);
                stat.setString(7, description);
                stat.setString(8, user_id);
                stat.setString(9, absence_type);
                stat.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
//            schema 1 for checkin only, 2 for checkout only, 3 for both
                stat.setString(11, schema);
                stat.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanabsensiadmin(String company_id, String start_date, String end_date) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from ops_realtime_absence where company_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat = conn.prepareStatement("select * from ops_request_absence where company_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat.setString(1, company_id);
            stat.setString(2, start_date);
            stat.setString(3, end_date);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_absensi", rs.getString("date_absensi"));
                ab.put("checkin", rs.getString("checkin"));
                ab.put("checkout", rs.getString("checkout"));

                ab.put("description", rs.getString("description"));
                ab.put("date_request", rs.getString("date_request"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("absence_type", rs.getString("absence_type"));
                ab.put("schema", rs.getString("schema"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanabsensiuser(String company_id, String employee_id, String start_date, String end_date) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from ops_realtime_absence where company_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat = conn.prepareStatement("select * from ops_request_absence where company_id = ? and employee_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, start_date);
            stat.setString(4, end_date);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_absensi", rs.getString("date_absensi"));
                ab.put("checkin", rs.getString("checkin"));
                ab.put("checkout", rs.getString("checkout"));

                ab.put("description", rs.getString("description"));
                ab.put("date_request", rs.getString("date_request"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("absence_type", rs.getString("absence_type"));
                ab.put("schema", rs.getString("schema"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailpengajuanabsensi(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_request_absence where company_id = ? and employee_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, reference);

            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("date_absensi", rs.getString("date_absensi"));
                result.put("checkin", rs.getString("checkin"));
                result.put("checkout", rs.getString("checkout"));
                result.put("description", rs.getString("description"));
                result.put("date_request", rs.getString("date_request"));
                result.put("account_id", rs.getString("account_id"));
                result.put("absence_type", rs.getString("absence_type"));
                result.put("schema", rs.getString("schema"));
                result.put("approve_status_admin", rs.getString("approve_status_admin"));
                result.put("approve_by_admin", rs.getString("approve_by_admin"));
                result.put("approve_time_admin", rs.getString("approve_time_admin"));
                result.put("approve_command_admin", rs.getString("approve_command_admin"));

                result.put("approve_status_head", rs.getString("approve_status_head"));
                result.put("approve_by_head", rs.getString("approve_by_head"));
                result.put("approve_time_head", rs.getString("approve_time_head"));
                result.put("approve_command_head", rs.getString("approve_command_head"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanabsensiadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_absence set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'APPROVED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanabsensiadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_off set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'REJECTED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanabsensihead(String reference, String employee_id, String company_id, String user_id, String command_head, String date_absensi, String schema) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String latein = "00:00";
        String early_out = "00:00";
        String schedule_working_hour = "00:00";
        String actual_working_hour = "00:00";
        String real_working_hour = "00:00";
        String overtime_duration_before = "00:00";
        String overtime_duration_after = "00:00";

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.start_time, b.end_time, c.*, d.absence_type as abtp, d.checkin as cekin_from_realtime from ops_schedule a \n"
                    + "left join crud_shift b on a.company_id = b.company_id and a.shift_id = b.shift_id \n"
                    + "left join ops_request_absence c on a.company_id = c.company_id and a.employee_id = c.employee_id \n"
                    + "left join ops_realtime_absence d on a.company_id = d.company_id and a.employee_id = d.employee_id and a.date = d.date_absensi\n"
                    + "where a.company_id = ? and a.employee_id = ? and a.date = ? and c.reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, date_absensi);
            stat.setString(4, reference);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("update ops_request_absence set approve_by_head = ?, "
                        + "approve_time_head = ?, "
                        + "approve_status_head = 'APPROVED', "
                        + "approve_command_head = ? "
                        + "where reference = ? and company_id = ? and employee_id=?");
                stat.setString(1, user_id);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                stat.setString(2, String.valueOf(timestamp));
                stat.setString(3, command_head);
                stat.setString(4, reference);
                stat.setString(5, company_id);
                stat.setString(6, employee_id);
                stat.executeUpdate();
//                stat.close();

                switch (schema) {
                    case "3": {
                        //                start base
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        Date start_time = simpleDateFormat.parse(rs.getString("start_time"));
                        Date end_time = simpleDateFormat.parse(rs.getString("end_time"));
                        Date cekin = simpleDateFormat.parse(rs.getString("checkin"));
                        Date cekout = simpleDateFormat.parse(rs.getString("checkout"));
                        //                end base

                        //latein
                        if (cekin.getTime() > start_time.getTime()) {
                            long MilliSecondsforlatein = cekin.getTime() - start_time.getTime();
                            long bedajamlatein = (MilliSecondsforlatein / (60 * 60 * 1000)) % 24;
                            long bedamenitlatein = (MilliSecondsforlatein / (60 * 1000)) % 60;
                            String jam_forlatein = String.valueOf(bedajamlatein);
                            String menit_forlatein = String.valueOf(bedamenitlatein);
                            if (jam_forlatein.length() == 1) {
                                jam_forlatein = 0 + jam_forlatein;
                            }
                            if (menit_forlatein.length() == 1) {
                                menit_forlatein = 0 + menit_forlatein;
                            }
                            latein = jam_forlatein + ":" + menit_forlatein;
                        }

                        //earlyout                
                        if (cekout.getTime() < end_time.getTime()) {
                            long MilliSecondsforearlyout = end_time.getTime() - cekout.getTime();
                            long bedajamearlyout = (MilliSecondsforearlyout / (60 * 60 * 1000)) % 24;
                            long bedamenitearlyout = (MilliSecondsforearlyout / (60 * 1000)) % 60;
                            String jam_forearlyout = String.valueOf(bedajamearlyout);
                            String menit_forearlyout = String.valueOf(bedamenitearlyout);
                            if (jam_forearlyout.length() == 1) {
                                jam_forearlyout = 0 + jam_forearlyout;
                            }
                            if (menit_forearlyout.length() == 1) {
                                menit_forearlyout = 0 + menit_forearlyout;
                            }
                            early_out = jam_forearlyout + ":" + menit_forearlyout;
                        }

                        //schedule working hour                
                        long MilliSecondsforswh = end_time.getTime() - start_time.getTime();
                        long bedajamswh = (MilliSecondsforswh / (60 * 60 * 1000)) % 24;
                        long bedamenitswh = (MilliSecondsforswh / (60 * 1000)) % 60;
                        String jam_forswh = String.valueOf(bedajamswh);
                        String menit_forswh = String.valueOf(bedamenitswh);
                        if (jam_forswh.length() == 1) {
                            jam_forswh = 0 + jam_forswh;
                        }
                        if (menit_forswh.length() == 1) {
                            menit_forswh = 0 + menit_forswh;
                        }
                        schedule_working_hour = jam_forswh + ":" + menit_forswh;

                        //actual working hour
                        long MilliSecondsforawh = 0;
                        if (cekin.getTime() == start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                        } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - cekin.getTime();
                        } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                        }
                        long bedajamawh = (MilliSecondsforawh / (60 * 60 * 1000)) % 24;
                        long bedamenitawh = (MilliSecondsforawh / (60 * 1000)) % 60;
                        String jam_forawh = String.valueOf(bedajamawh);
                        String menit_forawh = String.valueOf(bedamenitawh);
                        if (jam_forawh.length() == 1) {
                            jam_forawh = 0 + jam_forawh;
                        }
                        if (menit_forawh.length() == 1) {
                            menit_forawh = 0 + menit_forawh;
                        }
                        actual_working_hour = jam_forawh + ":" + menit_forawh;

//real working hour
                        long MilliSecondsforrwh = cekout.getTime() - cekin.getTime();
                        long bedajamrwh = (MilliSecondsforrwh / (60 * 60 * 1000)) % 24;
                        long bedamenitrwh = (MilliSecondsforrwh / (60 * 1000)) % 60;
                        String jam_forrwh = String.valueOf(bedajamrwh);
                        String menit_forrwh = String.valueOf(bedamenitrwh);
                        if (jam_forrwh.length() == 1) {
                            jam_forrwh = 0 + jam_forrwh;
                        }
                        if (menit_forrwh.length() == 1) {
                            menit_forrwh = 0 + menit_forrwh;
                        }
                        real_working_hour = jam_forrwh + ":" + menit_forrwh;

//overtime duration before
                        if (cekin.getTime() < start_time.getTime()) {
                            long MilliSecondsforodb = start_time.getTime() - cekin.getTime();
                            long bedajamodb = (MilliSecondsforodb / (60 * 60 * 1000)) % 24;
                            long bedamenitodb = (MilliSecondsforodb / (60 * 1000)) % 60;
                            String jam_forodb = String.valueOf(bedajamodb);
                            String menit_forodb = String.valueOf(bedamenitodb);
                            if (jam_forodb.length() == 1) {
                                jam_forodb = 0 + jam_forodb;
                            }
                            if (menit_forodb.length() == 1) {
                                menit_forodb = 0 + menit_forodb;
                            }
                            overtime_duration_before = jam_forodb + ":" + menit_forodb;
                        }

//overtime duration after
                        if (cekout.getTime() > end_time.getTime()) {
                            long MilliSecondsforoda = cekout.getTime() - end_time.getTime();
                            long bedajamoda = (MilliSecondsforoda / (60 * 60 * 1000)) % 24;
                            long bedamenitoda = (MilliSecondsforoda / (60 * 1000)) % 60;
                            String jam_foroda = String.valueOf(bedajamoda);
                            String menit_foroda = String.valueOf(bedamenitoda);
                            if (jam_foroda.length() == 1) {
                                jam_foroda = 0 + jam_foroda;
                            }
                            if (menit_foroda.length() == 1) {
                                menit_foroda = 0 + menit_foroda;
                            }
                            overtime_duration_after = jam_foroda + ":" + menit_foroda;
                        }
                        stat = conn.prepareStatement("update ops_realtime_absence set "
                                + "shift = ?, "
                                + "schedule_in = ?, "
                                + "schedule_out = ?, "
                                + "checkin = ?, "
                                + "checkout = ?, "
                                + "late_in = ?, "
                                + "early_out = ?, "
                                + "schedule_working_hour = ?, "
                                + "actual_working_hour = ?, "
                                + "real_working_hour = ?, "
                                + "overtime_duration_before = ?, "
                                + "overtime_duration_after = ?, "
                                + "descriptionin = ?, "
                                + "descriptionout = ?, "
                                + "date_requestin = ?, "
                                + "date_requestout = ?, "
                                + "account_id = ?, "
                                + "absence_type = ?, "
                                + "record_status = ?, "
                                + "reference_schema3 = ? "
                                + "where date_absensi = ? and employee_id = ?  and company_id = ?");
                        stat.setString(1, rs.getString("shift_id"));
                        stat.setString(2, rs.getString("start_time"));
                        stat.setString(3, rs.getString("end_time"));
                        stat.setString(4, rs.getString("checkin"));
                        stat.setString(5, rs.getString("checkout"));
                        stat.setString(6, latein);
                        stat.setString(7, early_out);
                        stat.setString(8, schedule_working_hour);
                        stat.setString(9, actual_working_hour);
                        stat.setString(10, real_working_hour);
                        stat.setString(11, overtime_duration_before);
                        stat.setString(12, overtime_duration_after);
                        stat.setString(13, rs.getString("description"));
                        stat.setString(14, rs.getString("description"));
                        stat.setTimestamp(15, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                        stat.setTimestamp(16, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
//                        stat.setTimestamp(15, Timestamp.valueOf(LocalDateTime.now()));
//                        stat.setTimestamp(16, Timestamp.valueOf(LocalDateTime.now()));
                        stat.setString(17, user_id);
                        stat.setString(18, rs.getString("absence_type") + "|" + rs.getString("absence_type"));
                        stat.setString(19, "1");
                        stat.setString(20, reference);
                        stat.setString(21, date_absensi);
                        stat.setString(22, employee_id);
                        stat.setString(23, company_id);
                        stat.executeUpdate();

                        stat.close();
                        stat = null;

                        rs.close();
                        rs = null;

                        stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, company_id);
                        stat.setString(2, employee_id);
                        stat.setString(3, date_absensi.substring(0, 4));
                        rs = stat.executeQuery();
                        if (rs.next()) {

                            int hdr = Integer.parseInt(rs.getString("hadir"));
                            stat.close();
                            stat = null;
//update kehadiran saat approve request case 3
                            stat = conn.prepareStatement("update ops_summary_daily set "
                                    + "hadir = ?,"
                                    + "where company_id = ? and employee_id = ? and tahun = ?");
                            stat.setString(1, String.valueOf(hdr + 1));
                            stat.setString(2, company_id);
                            stat.setString(3, employee_id);
                            stat.setString(4, String.valueOf(Year.now().getValue()));
                            stat.executeUpdate();

                        }
                        break;
                    }
                    case "2": {
                        //                start base
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        Date start_time = simpleDateFormat.parse(rs.getString("start_time"));
                        Date end_time = simpleDateFormat.parse(rs.getString("end_time"));
                        Date cekin = simpleDateFormat.parse(rs.getString("cekin_from_realtime"));
                        Date cekout = simpleDateFormat.parse(rs.getString("checkout"));
                        //                end base

                        //earlyout                
                        if (cekout.getTime() < end_time.getTime()) {
                            long MilliSecondsforearlyout = end_time.getTime() - cekout.getTime();
                            long bedajamearlyout = (MilliSecondsforearlyout / (60 * 60 * 1000)) % 24;
                            long bedamenitearlyout = (MilliSecondsforearlyout / (60 * 1000)) % 60;
                            String jam_forearlyout = String.valueOf(bedajamearlyout);
                            String menit_forearlyout = String.valueOf(bedamenitearlyout);
                            if (jam_forearlyout.length() == 1) {
                                jam_forearlyout = 0 + jam_forearlyout;
                            }
                            if (menit_forearlyout.length() == 1) {
                                menit_forearlyout = 0 + menit_forearlyout;
                            }
                            early_out = jam_forearlyout + ":" + menit_forearlyout;
                        }

                        //schedule working hour                
                        long MilliSecondsforswh = end_time.getTime() - start_time.getTime();
                        long bedajamswh = (MilliSecondsforswh / (60 * 60 * 1000)) % 24;
                        long bedamenitswh = (MilliSecondsforswh / (60 * 1000)) % 60;
                        String jam_forswh = String.valueOf(bedajamswh);
                        String menit_forswh = String.valueOf(bedamenitswh);
                        if (jam_forswh.length() == 1) {
                            jam_forswh = 0 + jam_forswh;
                        }
                        if (menit_forswh.length() == 1) {
                            menit_forswh = 0 + menit_forswh;
                        }
                        schedule_working_hour = jam_forswh + ":" + menit_forswh;

                        //actual working hour
                        long MilliSecondsforawh = 0;
                        if (cekin.getTime() == start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                        } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                            MilliSecondsforawh = cekout.getTime() - cekin.getTime();
                        } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                        } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                            MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                        }
                        long bedajamawh = (MilliSecondsforawh / (60 * 60 * 1000)) % 24;
                        long bedamenitawh = (MilliSecondsforawh / (60 * 1000)) % 60;
                        String jam_forawh = String.valueOf(bedajamawh);
                        String menit_forawh = String.valueOf(bedamenitawh);
                        if (jam_forawh.length() == 1) {
                            jam_forawh = 0 + jam_forawh;
                        }
                        if (menit_forawh.length() == 1) {
                            menit_forawh = 0 + menit_forawh;
                        }
                        actual_working_hour = jam_forawh + ":" + menit_forawh;

//real working hour
                        long MilliSecondsforrwh = cekout.getTime() - cekin.getTime();
                        long bedajamrwh = (MilliSecondsforrwh / (60 * 60 * 1000)) % 24;
                        long bedamenitrwh = (MilliSecondsforrwh / (60 * 1000)) % 60;
                        String jam_forrwh = String.valueOf(bedajamrwh);
                        String menit_forrwh = String.valueOf(bedamenitrwh);
                        if (jam_forrwh.length() == 1) {
                            jam_forrwh = 0 + jam_forrwh;
                        }
                        if (menit_forrwh.length() == 1) {
                            menit_forrwh = 0 + menit_forrwh;
                        }
                        real_working_hour = jam_forrwh + ":" + menit_forrwh;

//overtime duration after
                        if (cekout.getTime() > end_time.getTime()) {
                            long MilliSecondsforoda = cekout.getTime() - end_time.getTime();
                            long bedajamoda = (MilliSecondsforoda / (60 * 60 * 1000)) % 24;
                            long bedamenitoda = (MilliSecondsforoda / (60 * 1000)) % 60;
                            String jam_foroda = String.valueOf(bedajamoda);
                            String menit_foroda = String.valueOf(bedamenitoda);
                            if (jam_foroda.length() == 1) {
                                jam_foroda = 0 + jam_foroda;
                            }
                            if (menit_foroda.length() == 1) {
                                menit_foroda = 0 + menit_foroda;
                            }
                            overtime_duration_after = jam_foroda + ":" + menit_foroda;
                        }

                        if (rs.getString("cekin_from_realtime").equalsIgnoreCase("00:00")) {
                            stat = conn.prepareStatement("update ops_realtime_absence set "
                                    + "shift = ?,"
                                    + "schedule_in = ?,"
                                    + "schedule_out = ?,"
                                    + "checkout = ?, "
                                    + "early_out = ?, "
                                    + "schedule_working_hour = ?, "
                                    + "actual_working_hour = ?, "
                                    + "real_working_hour = ?, "
                                    + "overtime_duration_after = ?, "
                                    + "descriptionout = ?, "
                                    + "date_requestout = ?, "
                                    + "account_id = ?, "
                                    + "absence_type = ?, "
                                    + "reference_schema2 = ? "
                                    + "where company_id = ? and employee_id = ? and date_absensi = ?");
                            stat.setString(1, rs.getString("shift_id"));
                            stat.setString(2, rs.getString("start_time"));
                            stat.setString(3, rs.getString("end_time"));
                            stat.setString(4, rs.getString("checkout"));
                            stat.setString(5, early_out);
                            stat.setString(6, schedule_working_hour);
                            stat.setString(7, actual_working_hour);
                            stat.setString(8, real_working_hour);
                            stat.setString(9, overtime_duration_after);
                            stat.setString(10, rs.getString("description"));
                            stat.setTimestamp(11, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                            stat.setString(12, user_id);
                            stat.setString(13, rs.getString("abtp") + "|" + rs.getString("absence_type"));
                            stat.setString(14, reference);
                            stat.setString(15, company_id);
                            stat.setString(16, employee_id);
                            stat.setString(17, date_absensi);
                            stat.executeUpdate();
                        } else {
                            stat = conn.prepareStatement("update ops_realtime_absence set "
                                    + "shift = ?,"
                                    + "schedule_in = ?,"
                                    + "schedule_out = ?,"
                                    + "checkout = ?, "
                                    + "early_out = ?, "
                                    + "schedule_working_hour = ?, "
                                    + "actual_working_hour = ?, "
                                    + "real_working_hour = ?, "
                                    + "overtime_duration_after = ?, "
                                    + "descriptionout = ?, "
                                    + "date_requestout = ?, "
                                    + "account_id = ?, "
                                    + "absence_type = ?, "
                                    + "record_status = ?, "
                                    + "reference_schema2 = ? "
                                    + "where company_id = ? and employee_id = ? and date_absensi = ?");
                            stat.setString(1, rs.getString("shift_id"));
                            stat.setString(2, rs.getString("start_time"));
                            stat.setString(3, rs.getString("end_time"));
                            stat.setString(4, rs.getString("checkout"));
                            stat.setString(5, early_out);
                            stat.setString(6, schedule_working_hour);
                            stat.setString(7, actual_working_hour);
                            stat.setString(8, real_working_hour);
                            stat.setString(9, overtime_duration_after);
                            stat.setString(10, rs.getString("description"));
                            stat.setTimestamp(11, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                            stat.setString(12, user_id);
                            stat.setString(13, rs.getString("absence_type") + "|" + rs.getString("abtp"));
                            stat.setString(14, "1");
                            stat.setString(15, reference);
                            stat.setString(16, company_id);
                            stat.setString(17, employee_id);
                            stat.setString(18, date_absensi);
                            stat.executeUpdate();

                            stat.close();
                            stat = null;

                            rs.close();
                            rs = null;

                            stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and employee_id = ? and tahun = ?");
                            stat.setString(1, company_id);
                            stat.setString(2, employee_id);
                            stat.setString(3, date_absensi.substring(0, 4));
                            rs = stat.executeQuery();
                            if (rs.next()) {
                                int hdr = Integer.parseInt(rs.getString("hadir"));
                                stat.close();
                                stat = null;

//update kehadiran saat approve request case 2
                                stat = conn.prepareStatement("update ops_summary_daily set "
                                        + "hadir = ?"
                                        + "where company_id = ? and employee_id = ? and tahun = ?");
                                stat.setString(1, String.valueOf(hdr + 1));
                                stat.setString(2, company_id);
                                stat.setString(3, employee_id);
                                stat.setString(4, String.valueOf(Year.now().getValue()));
                                stat.executeUpdate();
                            }
                        }
                        break;
                    }
                    case "1": {
                        //start base
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        Date start_time = simpleDateFormat.parse(rs.getString("start_time"));
                        Date end_time = simpleDateFormat.parse(rs.getString("end_time"));
                        Date cekin = simpleDateFormat.parse(rs.getString("checkin"));
                        //Date cekout = simpleDateFormat.parse(rs.getString("checkout"));
                        //end base

                        //latein
                        if (cekin.getTime() > start_time.getTime()) {
                            long MilliSecondsforlatein = cekin.getTime() - start_time.getTime();
                            long bedajamlatein = (MilliSecondsforlatein / (60 * 60 * 1000)) % 24;
                            long bedamenitlatein = (MilliSecondsforlatein / (60 * 1000)) % 60;
                            String jam_forlatein = String.valueOf(bedajamlatein);
                            String menit_forlatein = String.valueOf(bedamenitlatein);
                            if (jam_forlatein.length() == 1) {
                                jam_forlatein = 0 + jam_forlatein;
                            }
                            if (menit_forlatein.length() == 1) {
                                menit_forlatein = 0 + menit_forlatein;
                            }
                            latein = jam_forlatein + ":" + menit_forlatein;
                        }

                        //schedule working hour                
                        long MilliSecondsforswh = end_time.getTime() - start_time.getTime();
                        long bedajamswh = (MilliSecondsforswh / (60 * 60 * 1000)) % 24;
                        long bedamenitswh = (MilliSecondsforswh / (60 * 1000)) % 60;
                        String jam_forswh = String.valueOf(bedajamswh);
                        String menit_forswh = String.valueOf(bedamenitswh);
                        if (jam_forswh.length() == 1) {
                            jam_forswh = 0 + jam_forswh;
                        }
                        if (menit_forswh.length() == 1) {
                            menit_forswh = 0 + menit_forswh;
                        }
                        schedule_working_hour = jam_forswh + ":" + menit_forswh;

                        //overtime duration before
                        if (cekin.getTime() < start_time.getTime()) {
                            long MilliSecondsforodb = start_time.getTime() - cekin.getTime();
                            long bedajamodb = (MilliSecondsforodb / (60 * 60 * 1000)) % 24;
                            long bedamenitodb = (MilliSecondsforodb / (60 * 1000)) % 60;
                            String jam_forodb = String.valueOf(bedajamodb);
                            String menit_forodb = String.valueOf(bedamenitodb);
                            if (jam_forodb.length() == 1) {
                                jam_forodb = 0 + jam_forodb;
                            }
                            if (menit_forodb.length() == 1) {
                                menit_forodb = 0 + menit_forodb;
                            }
                            overtime_duration_before = jam_forodb + ":" + menit_forodb;
                        }
                        stat = conn.prepareStatement("update ops_realtime_absence set "
                                + "shift = ?,"
                                + "schedule_in = ?,"
                                + "schedule_out = ?,"
                                + "checkin = ?,"
                                + "late_in = ?,"
                                + "schedule_working_hour = ?,"
                                + "overtime_duration_before = ?,"
                                + "descriptionin = ?,"
                                + "account_id = ?,"
                                + "absence_type = ?,"
                                + "date_requestin = ?,"
                                + "reference_schema1 = ?"
                                + "where company_id = ? and employee_id = ? and date_absensi = ?");
                        stat.setString(1, rs.getString("shift_id"));
                        stat.setString(2, rs.getString("start_time"));
                        stat.setString(3, rs.getString("end_time"));
                        stat.setString(4, rs.getString("checkin"));
                        stat.setString(5, latein);
                        stat.setString(6, schedule_working_hour);
                        stat.setString(7, overtime_duration_before);
                        stat.setString(8, rs.getString("description"));
                        stat.setString(9, user_id);
                        stat.setString(10, rs.getString("absence_type"));
                        stat.setTimestamp(11, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                        stat.setString(12, reference);
                        stat.setString(13, company_id);
                        stat.setString(14, employee_id);
                        stat.setString(15, date_absensi);

                        stat.executeUpdate();
                        break;
                    }
                    default:
                        break;
                }
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanabsensihead(String reference, String employee_id, String company_id, String user_id, String command_head) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_absence set approve_by_head = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_head = 'REJECTED', "
                    + "approve_command_head = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_head);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public HashMap livecheckin(
            String company_id,
            String employee_id,
            String checkin,
            String description,
            String user_id,
            String location) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String latein = "00:00";
        String schedule_working_hour = "00:00";
        String overtime_duration_before = "00:00";

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.start_time, b.end_time from ops_schedule a left join crud_shift b on a.company_id = b.company_id and a.shift_id = b.shift_id where a.company_id = '" + company_id + "' and a.employee_id = '" + employee_id + "' and a.date::date = current_date");
            rs = stat.executeQuery();
            if (rs.next()) {

//                start base                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date start_time = simpleDateFormat.parse(rs.getString("start_time"));
                Date end_time = simpleDateFormat.parse(rs.getString("end_time"));
                Date cekin = simpleDateFormat.parse(checkin);
//                end base

//latein
                if (cekin.getTime() > start_time.getTime()) {
                    long MilliSecondsforlatein = cekin.getTime() - start_time.getTime();
                    long bedajamlatein = (MilliSecondsforlatein / (60 * 60 * 1000)) % 24;
                    long bedamenitlatein = (MilliSecondsforlatein / (60 * 1000)) % 60;
                    String jam_forlatein = String.valueOf(bedajamlatein);
                    String menit_forlatein = String.valueOf(bedamenitlatein);
                    if (jam_forlatein.length() == 1) {
                        jam_forlatein = 0 + jam_forlatein;
                    }
                    if (menit_forlatein.length() == 1) {
                        menit_forlatein = 0 + menit_forlatein;
                    }
                    latein = jam_forlatein + ":" + menit_forlatein;
                }

//schedule working hour                
                long MilliSecondsforswh = end_time.getTime() - start_time.getTime();
                long bedajamswh = (MilliSecondsforswh / (60 * 60 * 1000)) % 24;
                long bedamenitswh = (MilliSecondsforswh / (60 * 1000)) % 60;
                String jam_forswh = String.valueOf(bedajamswh);
                String menit_forswh = String.valueOf(bedamenitswh);
                if (jam_forswh.length() == 1) {
                    jam_forswh = 0 + jam_forswh;
                }
                if (menit_forswh.length() == 1) {
                    menit_forswh = 0 + menit_forswh;
                }
                schedule_working_hour = jam_forswh + ":" + menit_forswh;

//overtime duration before
                if (cekin.getTime() < start_time.getTime()) {
                    long MilliSecondsforodb = start_time.getTime() - cekin.getTime();
                    long bedajamodb = (MilliSecondsforodb / (60 * 60 * 1000)) % 24;
                    long bedamenitodb = (MilliSecondsforodb / (60 * 1000)) % 60;
                    String jam_forodb = String.valueOf(bedajamodb);
                    String menit_forodb = String.valueOf(bedamenitodb);
                    if (jam_forodb.length() == 1) {
                        jam_forodb = 0 + jam_forodb;
                    }
                    if (menit_forodb.length() == 1) {
                        menit_forodb = 0 + menit_forodb;
                    }
                    overtime_duration_before = jam_forodb + ":" + menit_forodb;
                }

                stat = conn.prepareStatement("update ops_realtime_absence set "
                        + "shift = ?,"
                        + "schedule_in = ?,"
                        + "schedule_out = ?,"
                        + "checkin = ?,"
                        + "late_in = ?,"
                        + "schedule_working_hour = ?,"
                        + "overtime_duration_before = ?,"
                        + "descriptionin = ?,"
                        + "account_id = ?,"
                        + "absence_type = ?,"
                        + "date_requestin = ?,"
                        + "locationin = ?"
                        //                        + "record_status = ?"
                        + "where company_id = ? and employee_id = ? and date_absensi::date = current_date");
                stat.setString(1, rs.getString("shift_id"));
                stat.setString(2, rs.getString("start_time"));
                stat.setString(3, rs.getString("end_time"));
                stat.setString(4, checkin);
                stat.setString(5, latein);
                stat.setString(6, schedule_working_hour);
                stat.setString(7, overtime_duration_before);
                stat.setString(8, description);
                stat.setString(9, user_id);
                stat.setString(10, "LIVIN");
                stat.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
                stat.setString(12, location);
//                stat.setString(13, "1");
                stat.setString(13, company_id);
                stat.setString(14, employee_id);
                stat.executeUpdate();

                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap livecheckout(
            String company_id,
            String employee_id,
            String checkout,
            String description,
            String user_id,
            String location) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String early_out = "00:00";
        String actual_working_hour = "00:00";
        String real_working_hour = "00:00";
        String overtime_duration_after = "00:00";
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.start_time, b.end_time, c.checkin, c.date_absensi, c.absence_type from ops_schedule a \n"
                    + "left join crud_shift b on a.company_id = b.company_id and a.shift_id = b.shift_id \n"
                    + "left join ops_realtime_absence c on a.company_id = c.company_id and a.employee_id = c.employee_id and a.date = c.date_absensi\n"
                    + "where a.company_id = '" + company_id + "' and a.employee_id = '" + employee_id + "' and a.date::date = current_date");
            rs = stat.executeQuery();
            if (rs.next()) {
//                start base                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date start_time = simpleDateFormat.parse(rs.getString("start_time"));
                Date end_time = simpleDateFormat.parse(rs.getString("end_time"));
                Date cekout = simpleDateFormat.parse(checkout);
                Date cekin = simpleDateFormat.parse(rs.getString("checkin"));
//                end base

//earlyout                
                if (cekout.getTime() < end_time.getTime()) {
                    long MilliSecondsforearlyout = end_time.getTime() - cekout.getTime();
                    long bedajamearlyout = (MilliSecondsforearlyout / (60 * 60 * 1000)) % 24;
                    long bedamenitearlyout = (MilliSecondsforearlyout / (60 * 1000)) % 60;
                    String jam_forearlyout = String.valueOf(bedajamearlyout);
                    String menit_forearlyout = String.valueOf(bedamenitearlyout);
                    if (jam_forearlyout.length() == 1) {
                        jam_forearlyout = 0 + jam_forearlyout;
                    }
                    if (menit_forearlyout.length() == 1) {
                        menit_forearlyout = 0 + menit_forearlyout;
                    }
                    early_out = jam_forearlyout + ":" + menit_forearlyout;
                }

//actual working hour 
                long MilliSecondsforawh = 0;
                if (cekin.getTime() == start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() == end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                    MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                    MilliSecondsforawh = cekout.getTime() - start_time.getTime();
                } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() < end_time.getTime()) {
                    MilliSecondsforawh = cekout.getTime() - cekin.getTime();
                } else if (cekin.getTime() == start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                } else if (cekin.getTime() < start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - start_time.getTime();
                } else if (cekin.getTime() > start_time.getTime() && cekout.getTime() > end_time.getTime()) {
                    MilliSecondsforawh = end_time.getTime() - cekin.getTime();
                }

                long bedajamawh = (MilliSecondsforawh / (60 * 60 * 1000)) % 24;
                long bedamenitawh = (MilliSecondsforawh / (60 * 1000)) % 60;
                String jam_forawh = String.valueOf(bedajamawh);
                String menit_forawh = String.valueOf(bedamenitawh);
                if (jam_forawh.length() == 1) {
                    jam_forawh = 0 + jam_forawh;
                }
                if (menit_forawh.length() == 1) {
                    menit_forawh = 0 + menit_forawh;
                }
                actual_working_hour = jam_forawh + ":" + menit_forawh;

//real working hour
                long MilliSecondsforrwh = cekout.getTime() - cekin.getTime();
                long bedajamrwh = (MilliSecondsforrwh / (60 * 60 * 1000)) % 24;
                long bedamenitrwh = (MilliSecondsforrwh / (60 * 1000)) % 60;
                String jam_forrwh = String.valueOf(bedajamrwh);
                String menit_forrwh = String.valueOf(bedamenitrwh);
                if (jam_forrwh.length() == 1) {
                    jam_forrwh = 0 + jam_forrwh;
                }
                if (menit_forrwh.length() == 1) {
                    menit_forrwh = 0 + menit_forrwh;
                }
                real_working_hour = jam_forrwh + ":" + menit_forrwh;

//overtime duration after
                if (cekout.getTime() > end_time.getTime()) {
                    long MilliSecondsforoda = cekout.getTime() - end_time.getTime();
                    long bedajamoda = (MilliSecondsforoda / (60 * 60 * 1000)) % 24;
                    long bedamenitoda = (MilliSecondsforoda / (60 * 1000)) % 60;
                    String jam_foroda = String.valueOf(bedajamoda);
                    String menit_foroda = String.valueOf(bedamenitoda);
                    if (jam_foroda.length() == 1) {
                        jam_foroda = 0 + jam_foroda;
                    }
                    if (menit_foroda.length() == 1) {
                        menit_foroda = 0 + menit_foroda;
                    }
                    overtime_duration_after = jam_foroda + ":" + menit_foroda;
                }

                if (rs.getString("checkin").equalsIgnoreCase("00:00")) {
                    stat = conn.prepareStatement("update ops_realtime_absence set "
                            + "checkout = ?,"
                            + "early_out = ?,"
                            + "actual_working_hour = ?,"
                            + "real_working_hour = ?,"
                            + "overtime_duration_after = ?,"
                            + "descriptionout = ?,"
                            + "date_requestout = ?,"
                            + "absence_type = ?,"
                            //                            + "record_status = ?,"
                            + "locationout = ? "
                            + "where company_id = ? and employee_id = ? and date_absensi::date = current_date");
                    stat.setString(1, checkout);
                    stat.setString(2, early_out);
                    stat.setString(3, actual_working_hour);
                    stat.setString(4, real_working_hour);
                    stat.setString(5, overtime_duration_after);
                    stat.setString(6, description);
                    stat.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                    stat.setString(8, location);
                    stat.setString(9, "''|LIVOUT");
//                    stat.setString(10, "1");
                    stat.setString(10, company_id);
                    stat.setString(11, employee_id);
                    stat.executeUpdate();
                } else {
                    stat = conn.prepareStatement("update ops_realtime_absence set "
                            + "checkout = ?,"
                            + "early_out = ?,"
                            + "actual_working_hour = ?,"
                            + "real_working_hour = ?,"
                            + "overtime_duration_after = ?,"
                            + "descriptionout = ?,"
                            + "date_requestout = ?,"
                            + "record_status = ?,"
                            + "absence_type = ?,"
                            + "locationout = ? "
                            + "where company_id = ? and employee_id = ? and date_absensi::date = current_date");
                    stat.setString(1, checkout);
                    stat.setString(2, early_out);
                    stat.setString(3, actual_working_hour);
                    stat.setString(4, real_working_hour);
                    stat.setString(5, overtime_duration_after);
                    stat.setString(6, description);
                    stat.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                    stat.setString(8, "1");
                    stat.setString(9, rs.getString("absence_type") + "|LIVEOUT");
                    stat.setString(10, location);
                    stat.setString(11, company_id);
                    stat.setString(12, employee_id);
                    stat.executeUpdate();

                    stat.close();
                    stat = null;

                    rs.close();
                    rs = null;

                    stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and employee_id = ? and tahun = ?");
                    stat.setString(1, company_id);
                    stat.setString(2, employee_id);
                    stat.setString(3, String.valueOf(Year.now().getValue()));
                    rs = stat.executeQuery();
                    if (rs.next()) {

                        int hdr = Integer.parseInt(rs.getString("hadir"));
                        stat.close();
                        stat = null;

//update kehadiran saat live checkout
                        stat = conn.prepareStatement("update ops_summary_daily set "
                                + "hadir = ?"
                                + "where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, String.valueOf(hdr + 1));
                        stat.setString(2, company_id);
                        stat.setString(3, employee_id);
                        stat.setString(4, String.valueOf(Year.now().getValue()));
                        stat.executeUpdate();
                    }

                }
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistliveattendanceadmin(String company_id, String start_date, String end_date) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_realtime_absence where company_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat.setString(1, company_id);
            stat.setString(2, start_date);
            stat.setString(3, end_date);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_absensi", rs.getString("date_absensi"));
                ab.put("shift", rs.getString("shift"));
                ab.put("schedule_in", rs.getString("schedule_in"));
                ab.put("schedule_out", rs.getString("schedule_out"));
                ab.put("checkin", rs.getString("checkin"));
                ab.put("checkout", rs.getString("checkout"));
                ab.put("late_in", rs.getString("late_in"));
                ab.put("early_out", rs.getString("early_out"));
                ab.put("schedule_working_hour", rs.getString("schedule_working_hour"));
                ab.put("actual_working_hour", rs.getString("actual_working_hour"));
                ab.put("real_working_hour", rs.getString("real_working_hour"));
                ab.put("overtime_duration_before", rs.getString("overtime_duration_before"));
                ab.put("overtime_duration_after", rs.getString("overtime_duration_after"));
                ab.put("descriptionin", rs.getString("descriptionin"));
                ab.put("descriptionout", rs.getString("descriptionout"));
                ab.put("date_requestin", rs.getString("date_requestin"));
                ab.put("date_requestout", rs.getString("date_requestout"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("locationin", rs.getString("locationin"));
                ab.put("locationout", rs.getString("locationout"));
                ab.put("absence_type", rs.getString("absence_type"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistliveattendanceuser(String company_id, String employee_id, String start_date, String end_date) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.shift_name from ops_realtime_absence a left join crud_shift b on \n"
                    + "a.company_id = b.company_id and a.shift = b.shift_id \n"
                    + "where a.company_id = ? and a.employee_id = ? and a.date_absensi >= ? and a.date_absensi <= ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, start_date);
            stat.setString(4, end_date);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_absensi", rs.getString("date_absensi"));
                ab.put("shift", rs.getString("shift"));
                ab.put("schedule_in", rs.getString("schedule_in"));
                ab.put("schedule_out", rs.getString("schedule_out"));
                ab.put("checkin", rs.getString("checkin"));
                ab.put("checkout", rs.getString("checkout"));
                ab.put("late_in", rs.getString("late_in"));
                ab.put("early_out", rs.getString("early_out"));
                ab.put("schedule_working_hour", rs.getString("schedule_working_hour"));
                ab.put("actual_working_hour", rs.getString("actual_working_hour"));
                ab.put("real_working_hour", rs.getString("real_working_hour"));
                ab.put("overtime_duration_before", rs.getString("overtime_duration_before"));
                ab.put("overtime_duration_after", rs.getString("overtime_duration_after"));
                ab.put("descriptionin", rs.getString("descriptionin"));
                ab.put("descriptionout", rs.getString("descriptionout"));
                ab.put("date_requestin", rs.getString("date_requestin"));
                ab.put("date_requestout", rs.getString("date_requestout"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("locationin", rs.getString("locationin"));
                ab.put("locationout", rs.getString("locationout"));
                ab.put("absence_type", rs.getString("absence_type"));
                ab.put("shift_name", rs.getString("shift_name"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailliveattendance(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_realtime_absence where company_id = ? and employee_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, reference);

            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("date_absensi", rs.getString("date_absensi"));
                result.put("shift", rs.getString("shift"));
                result.put("schedule_in", rs.getString("schedule_in"));
                result.put("schedule_out", rs.getString("schedule_out"));
                result.put("checkin", rs.getString("checkin"));
                result.put("checkout", rs.getString("checkout"));
                result.put("late_in", rs.getString("late_in"));
                result.put("early_out", rs.getString("early_out"));
                result.put("schedule_working_hour", rs.getString("schedule_working_hour"));
                result.put("actual_working_hour", rs.getString("actual_working_hour"));
                result.put("real_working_hour", rs.getString("real_working_hour"));
                result.put("overtime_duration_before", rs.getString("overtime_duration_before"));
                result.put("overtime_duration_after", rs.getString("overtime_duration_after"));
                result.put("descriptionin", rs.getString("descriptionin"));
                result.put("descriptionout", rs.getString("descriptionout"));
                result.put("date_requestin", rs.getString("date_requestin"));
                result.put("date_requestout", rs.getString("date_requestout"));
                result.put("account_id", rs.getString("account_id"));
                result.put("locationin", rs.getString("locationin"));
                result.put("locationout", rs.getString("locationout"));
                result.put("absence_type", rs.getString("absence_type"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

////////////////////////////////////////////////////////////////////////////////
    public HashMap pengajuantimeoff(
            String company_id,
            String employee_id,
            String timeoff_code,
            String date_off,
            String description,
            String user_id,
            String absence_type,
            String delegation) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_request_off ("
                    + "reference,"
                    + "company_id,"
                    + "employee_id,"
                    + "timeoff_code,"
                    + "date_off,"
                    + "description,"
                    + "date_request,"
                    + "total_day,"
                    + "account_id,"
                    + "absence_type,"
                    + "approve_status_admin,"
                    + "approve_status_head,"
                    + "delegation"
                    + ")"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, "off_" + company_id + "|" + employee_id + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS());
            stat.setString(2, company_id);
            stat.setString(3, employee_id);
            stat.setString(4, timeoff_code);
            stat.setString(5, date_off);
            stat.setString(6, description);
            stat.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

            List<String> get_list = Arrays.asList(date_off.split(","));

            stat.setString(8, String.valueOf(get_list.size()));
            stat.setString(9, user_id);
            stat.setString(10, absence_type);
            stat.setString(11, "PENDING");
            stat.setString(12, "PENDING");
            stat.setString(13, delegation);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuantimeoffadmin(String company_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.timeoff_desc from ops_request_off a left join crud_timeoff b on \n"
                    + "a.company_id = b.company_id and a.timeoff_code = b.timeoff_code \n"
                    + "where a.company_id = ? and a.date_request >= ? and a.date_request <= ?");
            stat.setString(1, company_id);
            stat.setString(2, start_date);
            stat.setString(3, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_off", rs.getString("date_off"));

                ab.put("description", rs.getString("description"));
                ab.put("date_request", rs.getString("date_request"));
                ab.put("total_day", rs.getString("total_day"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("timeoff_code", rs.getString("timeoff_code"));
                ab.put("timeoff_desc", rs.getString("timeoff_desc"));
                ab.put("absence_type", rs.getString("absence_type"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuantimeoffuser(String company_id, String employee_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.timeoff_desc from ops_request_off a left join crud_timeoff b on \n"
                    + "a.company_id = b.company_id and a.timeoff_code = b.timeoff_code \n"
                    + "where a.company_id = ? and a.employee_id = ? and a.date_request >= ? and a.date_request <= ?");
//            stat = conn.prepareStatement("select * from ops_request_off where company_id = ? and employee_id = ? and date_absensi >= ? and date_absensi <= ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, start_date);
            stat.setString(4, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();

                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("date_off", rs.getString("date_off"));

                ab.put("description", rs.getString("description"));
                ab.put("date_request", rs.getString("date_request"));
                ab.put("total_day", rs.getString("total_day"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("timeoff_code", rs.getString("timeoff_code"));
                ab.put("timeoff_desc", rs.getString("timeoff_desc"));
                ab.put("absence_type", rs.getString("absence_type"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailpengajuantimeoff(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_request_off where company_id = ? and employee_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, reference);

            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("date_off", rs.getString("date_off"));
                result.put("description", rs.getString("description"));
                result.put("date_request", rs.getString("date_request"));
                result.put("total_day", rs.getString("total_day"));
                result.put("account_id", rs.getString("account_id"));
                result.put("timeoff_code", rs.getString("timeoff_code"));
                result.put("absence_type", rs.getString("absence_type"));
                result.put("approve_status_admin", rs.getString("approve_status_admin"));
                result.put("approve_by_admin", rs.getString("approve_by_admin"));
                result.put("approve_time_admin", rs.getString("approve_time_admin"));
                result.put("approve_command_admin", rs.getString("approve_command_admin"));
                result.put("approve_status_head", rs.getString("approve_status_head"));
                result.put("approve_by_head", rs.getString("approve_by_head"));
                result.put("approve_time_head", rs.getString("approve_time_head"));
                result.put("approve_command_head", rs.getString("approve_command_head"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuantimeoffadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_off set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'APPROVED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuantimeoffadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_off set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'REJECTED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuantimeoffhead(String reference, String employee_id, String company_id, String user_id, String command_head) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String tipeabsen = "";
        String datenya = "";
        String kode_cuti = "";

        String cuti_list = "";
        String cuti_bucket = "";
        String cuti_terpakai = "";
        String cuti_sisa = "";

        int totalday = 0;
        int index = 0;
        int sebelum_terpakai = 0;
        int sebelum_sisa = 0;
        int sesudah_terpakai = 0;
        int sesudah_sisa = 0;

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_request_off where reference = ?");
            stat.setString(1, reference);
            rs = stat.executeQuery();
            if (rs.next()) {

                tipeabsen = rs.getString("absence_type");
                totalday = Integer.parseInt(rs.getString("total_day"));
                kode_cuti = rs.getString("timeoff_code");

                stat = conn.prepareStatement("update ops_request_off set approve_by_head = ?, "
                        + "approve_time_head = ?, "
                        + "approve_status_head = 'APPROVED', "
                        + "approve_command_head = ? "
                        + "where reference = ? and company_id = ? and employee_id=?");
                stat.setString(1, user_id);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                stat.setString(2, String.valueOf(timestamp));
                stat.setString(3, command_head);
                stat.setString(4, reference);
                stat.setString(5, company_id);
                stat.setString(6, employee_id);
                stat.executeUpdate();
                //                stat.close();

                datenya = rs.getString("date_off");

                List<String> elephantList = Arrays.asList(datenya.split(","));
                for (int i = 0; i < elephantList.size(); i++) {
                    stat = conn.prepareStatement("update ops_realtime_absence set "
                            + "descriptionin = ?, "
                            + "descriptionout = ?, "
                            + "date_requestin = ?, "
                            + "date_requestout = ?, "
                            + "account_id = ?, "
                            + "absence_type = ?, "
                            + "reference_timeoff = ? "
                            + "where employee_id = ? and company_id = ? and date_absensi = ?");
                    stat.setString(1, rs.getString("description"));
                    stat.setString(2, rs.getString("description"));
                    stat.setTimestamp(3, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                    stat.setTimestamp(4, Timestamp.valueOf(rs.getString("date_request").substring(0, 23)));
                    stat.setString(5, user_id);
                    stat.setString(6, tipeabsen);
                    stat.setString(7, reference);
                    stat.setString(8, employee_id);
                    stat.setString(9, company_id);
                    stat.setString(10, elephantList.get(i));
                    stat.executeUpdate();
                }

                stat.close();
                stat = null;

                rs.close();
                rs = null;

                stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and employee_id = ? and tahun = ?");
                stat.setString(1, company_id);
                stat.setString(2, employee_id);
                stat.setString(3, datenya.substring(0, 4));
                rs = stat.executeQuery();
                if (rs.next()) {

                    cuti_list = rs.getString("cuti_list");
                    cuti_bucket = rs.getString("cuti_bucket");
                    cuti_terpakai = rs.getString("cuti_terpakai");
                    cuti_sisa = rs.getString("cuti_sisa");

                    if (tipeabsen.equalsIgnoreCase("SKT")) {
                        int skt = Integer.parseInt(rs.getString("sakit"));
                        stat.close();
                        stat = null;

//update sakit saat approve request sakit
                        stat = conn.prepareStatement("update ops_summary_daily set "
                                + "sakit = ?"
                                + "where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, String.valueOf(skt + totalday));
                        stat.setString(2, company_id);
                        stat.setString(3, employee_id);
                        stat.setString(4, String.valueOf(Year.now().getValue()));
                        stat.executeUpdate();
                    } else if (tipeabsen.equalsIgnoreCase("IZN")) {
                        int izn = Integer.parseInt(rs.getString("izin"));
                        stat.close();
                        stat = null;

//update izin saat approve request izin
                        stat = conn.prepareStatement("update ops_summary_daily set "
                                + "izin = ?"
                                + "where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, String.valueOf(izn + totalday));
                        stat.setString(2, company_id);
                        stat.setString(3, employee_id);
                        stat.setString(4, String.valueOf(Year.now().getValue()));
                        stat.executeUpdate();
                    } else if (tipeabsen.equalsIgnoreCase("CUT")) {
                        stat.close();
                        stat = null;

                        List<String> list_kodecuti = Arrays.asList(cuti_list.split("\\|"));
                        List<String> list_sisacuti = Arrays.asList(cuti_sisa.split("\\|"));
                        List<String> list_terpakaicuti = Arrays.asList(cuti_terpakai.split("\\|"));

                        for (int i = 0; i < list_kodecuti.size(); i++) {
                            if (list_kodecuti.get(i).equals(kode_cuti)) {
                                index = i;
                            }
                        }

                        sebelum_terpakai = Integer.parseInt(list_terpakaicuti.get(index));
                        sebelum_sisa = Integer.parseInt(list_sisacuti.get(index));

                        sesudah_terpakai = (sebelum_terpakai + totalday);
                        sesudah_sisa = (sebelum_sisa - totalday);

//                      update list
                        list_terpakaicuti.set(index, String.valueOf(sesudah_terpakai));
                        list_sisacuti.set(index, String.valueOf(sesudah_sisa));

//                      membuat list delimiter
                        StringJoiner joincutiterpakai = new StringJoiner("|");
                        StringJoiner joincutisisa = new StringJoiner("|");

                        for (int i = 0; i < list_terpakaicuti.size(); i++) {
                            joincutiterpakai.add(list_terpakaicuti.get(i));
                            joincutisisa.add(list_sisacuti.get(i));
                        }
                        String joinedcutiterpakai = joincutiterpakai.toString();
                        String joinedcutisisa = joincutisisa.toString();

//update cuti saat approve request cuti
                        stat = conn.prepareStatement("update ops_summary_daily set "
                                + "cuti_terpakai = ?,"
                                + "cuti_sisa = ?"
                                + "where company_id = ? and employee_id = ? and tahun = ?");
                        stat.setString(1, joinedcutiterpakai);
                        stat.setString(2, joinedcutisisa);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.setString(5, String.valueOf(Year.now().getValue()));
                        stat.executeUpdate();
                    }
                    result.put(RuleNameParameter.resp_code, "0000");
                    result.put(RuleNameParameter.resp_desc, "Success");
                } else {
                    result.put(RuleNameParameter.resp_code, "0006");
                    result.put(RuleNameParameter.resp_desc, "No summary data in " + datenya.substring(0, 4));
                }
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuantimeoffhead(String reference, String employee_id, String company_id, String user_id, String command_head) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_off set approve_by_head = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_head = 'REJECTED', "
                    + "approve_command_head = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_head);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap cancelpengajuantimeoffuser(String reference, String employee_id, String company_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_off set "
                    + "approve_time_admin = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_admin = 'CANCELED', "
                    + "approve_status_head = 'CANCELED' "
                    + "where reference = ? and company_id = ? and employee_id=?");

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stat.setString(1, String.valueOf(timestamp));
            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, reference);
            stat.setString(4, company_id);
            stat.setString(5, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////
    private static String format(long s) {
        if (s < 10) {
            return "0" + s;
        } else {
            return "" + s;
        }
    }

    public HashMap pengajuanovertime(
            String company_id,
            String employee_id,
            String user_id,
            String overtime_date,
            String overtime_before,
            String overtime_after,
            String description
    ) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String overtime_all = "00:00";
        String overtime_payload = "0";

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.shift_id, a.date, b.start_time, b.end_time from ops_schedule a left join crud_shift b on a.company_id = b.company_id and a.shift_id = b.shift_id where a.company_id = ? and a.employee_id = ? and a.date = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, overtime_date);
            rs = stat.executeQuery();
            if (rs.next()) {

                ArrayList<String> timestampsList = new ArrayList<String>();
                timestampsList.add(overtime_before);
                timestampsList.add(overtime_after);
                long tm = 0;
                for (String tmp : timestampsList) {
                    String[] arr = tmp.split(":");
                    tm += 60 * Integer.parseInt(arr[1]);
                    tm += 3600 * Integer.parseInt(arr[0]);
                }
                long hh = tm / 3600;
                tm %= 3600;
                long mm = tm / 60;

                overtime_all = format(hh) + ":" + format(mm);

                System.out.println(format(hh) + ":" + format(mm));

                stat = conn.prepareStatement("insert into ops_overtime ("
                        + "reference,"
                        + "company_id,"
                        + "employee_id,"
                        + "account_id,"
                        + "schedule_in,"
                        + "schedule_out,"
                        + "overtime_date,"
                        + "overtime_before,"
                        + "overtime_after,"
                        + "overtime_all,"
                        + "overtime_payload,"
                        + "description,"
                        + "approve_status_admin,"
                        + "approve_status_head"
                        + ")"
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stat.setString(1, "ovr_" + company_id + "|" + employee_id + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS());
                stat.setString(2, company_id);
                stat.setString(3, employee_id);
                stat.setString(4, user_id);
                stat.setString(5, rs.getString("start_time"));
                stat.setString(6, rs.getString("end_time"));
                stat.setString(7, overtime_date);
                stat.setString(8, overtime_before);
                stat.setString(9, overtime_after);
                stat.setString(10, overtime_all);
                stat.setString(11, overtime_payload);
                stat.setString(12, description);
                stat.setString(13, "PENDING");
                stat.setString(14, "PENDING");
                stat.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");

            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanovertimeadmin(String company_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_overtime where company_id = ? and date_request::varchar >= ? and date_request::varchar <= ?");
            stat.setString(1, company_id);
            stat.setString(2, start_date);
            stat.setString(3, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("schedule_in", rs.getString("schedule_in"));
                ab.put("schedule_out", rs.getString("schedule_out"));
                ab.put("overtime_date", rs.getString("overtime_date"));
                ab.put("overtime_before", rs.getString("overtime_before"));
                ab.put("overtime_after", rs.getString("overtime_after"));
                ab.put("overtime_all", rs.getString("overtime_all"));
                ab.put("overtime_payload", rs.getString("overtime_payload"));
                ab.put("description", rs.getString("description"));
                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));
                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));
                ab.put("date_request", rs.getString("date_request"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanovertimeuser(String company_id, String employee_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_overtime where company_id = ? and employee_id = ? and date_request::varchar >= ? and date_request::varchar <= ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, start_date);
            stat.setString(4, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();

                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("schedule_in", rs.getString("schedule_in"));
                ab.put("schedule_out", rs.getString("schedule_out"));
                ab.put("overtime_date", rs.getString("overtime_date"));
                ab.put("overtime_before", rs.getString("overtime_before"));
                ab.put("overtime_after", rs.getString("overtime_after"));
                ab.put("overtime_all", rs.getString("overtime_all"));
                ab.put("overtime_payload", rs.getString("overtime_payload"));
                ab.put("description", rs.getString("description"));
                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));
                ab.put("approve_command_admin", rs.getString("approve_command_admin"));
                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));
                ab.put("approve_command_head", rs.getString("approve_command_head"));
                ab.put("date_request", rs.getString("date_request"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailpengajuanovertime(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_overtime where company_id = ? and employee_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, reference);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("account_id", rs.getString("account_id"));
                result.put("schedule_in", rs.getString("schedule_in"));
                result.put("schedule_out", rs.getString("schedule_out"));
                result.put("overtime_date", rs.getString("overtime_date"));
                result.put("overtime_before", rs.getString("overtime_before"));
                result.put("overtime_after", rs.getString("overtime_after"));
                result.put("overtime_all", rs.getString("overtime_all"));
                result.put("overtime_payload", rs.getString("overtime_payload"));
                result.put("description", rs.getString("description"));
                result.put("approve_status_admin", rs.getString("approve_status_admin"));
                result.put("approve_by_admin", rs.getString("approve_by_admin"));
                result.put("approve_time_admin", rs.getString("approve_time_admin"));
                result.put("approve_command_admin", rs.getString("approve_command_admin"));
                result.put("approve_status_head", rs.getString("approve_status_head"));
                result.put("approve_by_head", rs.getString("approve_by_head"));
                result.put("approve_time_head", rs.getString("approve_time_head"));
                result.put("approve_command_head", rs.getString("approve_command_head"));
                result.put("date_request", rs.getString("date_request"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanovertimeadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_overtime set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'APPROVED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanovertimeadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_overtime set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'REJECTED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanovertimehead(String reference, String employee_id, String company_id, String user_id, String command_head) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();

            stat = conn.prepareStatement("update ops_overtime set approve_by_head = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_head = 'APPROVED', "
                    + "approve_command_head = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_head);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanovertimehead(String reference, String employee_id, String company_id, String user_id, String command_head) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_overtime set approve_by_head = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_head = 'REJECTED', "
                    + "approve_command_head = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_head);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap cancelpengajuanovertimeuser(String reference, String employee_id, String company_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_overtime set "
                    + "approve_time_admin = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_admin = 'CANCELED', "
                    + "approve_status_head = 'CANCELED' "
                    + "where reference = ? and company_id = ? and employee_id=?");

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stat.setString(1, String.valueOf(timestamp));
            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, reference);
            stat.setString(4, company_id);
            stat.setString(5, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailsummaryabsence(String company_id, String employee_id, String tahun) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and employee_id = ? and tahun = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, tahun);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("summary_id", rs.getString("summary_id"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("tahun", rs.getString("tahun"));
                result.put("hadir", rs.getString("hadir"));
                result.put("sakit", rs.getString("sakit"));
                result.put("izin", rs.getString("izin"));
                result.put("absence", rs.getString("absence"));
                result.put("cuti_list", rs.getString("cuti_list"));
                result.put("cuti_desc", rs.getString("cuti_desc"));
                result.put("cuti_bucket", rs.getString("cuti_bucket"));
                result.put("cuti_terpakai", rs.getString("cuti_terpakai"));
                result.put("cuti_sisa", rs.getString("cuti_sisa"));
                result.put("create_date", rs.getString("create_date"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistsummaryabsence(String company_id, String tahun) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_summary_daily where company_id = ? and tahun = ?");
            stat.setString(1, company_id);
            stat.setString(2, tahun);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("summary_id", rs.getString("summary_id"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("tahun", rs.getString("tahun"));
                ab.put("hadir", rs.getString("hadir"));
                ab.put("sakit", rs.getString("sakit"));
                ab.put("izin", rs.getString("izin"));
                ab.put("absence", rs.getString("absence"));
                ab.put("cuti_list", rs.getString("cuti_list"));
                ab.put("cuti_desc", rs.getString("cuti_desc"));
                ab.put("cuti_bucket", rs.getString("cuti_bucket"));
                ab.put("cuti_terpakai", rs.getString("cuti_terpakai"));
                ab.put("cuti_sisa", rs.getString("cuti_sisa"));
                ab.put("create_date", rs.getString("create_date"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getprivatedata(String user_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT employee_id, employee_name, email, gender, birth_place, birth_date, phonenumber, \n"
                    + "       ops_employee.marital_status, religion, nik, ktp_address, code_pos, blood_type, \n"
                    + "       int_marital.marital_desc, crud_religion.religion_name\n"
                    + "FROM ops_employee\n"
                    + "LEFT JOIN int_marital ON ops_employee.marital_status = int_marital.marital_id\n"
                    + "LEFT JOIN crud_religion ON ops_employee.religion = crud_religion.religion_id\n"
                    + "WHERE ops_employee.company_id = ? AND email = ?");
            stat.setString(1, company_id);
            stat.setString(2, user_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("employee_id", rs.getString("employee_id"));
                result.put("employee_name", rs.getString("employee_name"));
                result.put("email", rs.getString("email"));
                result.put("gender", rs.getString("gender"));
                result.put("birth_place", rs.getString("birth_place"));
                result.put("birth_date", rs.getString("birth_date"));
                result.put("phonenumber", rs.getString("phonenumber"));

                result.put("marital_status", rs.getString("marital_status"));
                result.put("religion", rs.getString("religion"));
                result.put("nik", rs.getString("nik"));
                result.put("ktp_address", rs.getString("ktp_address"));
                result.put("code_pos", rs.getString("code_pos"));
                result.put("blood_type", rs.getString("blood_type"));
                result.put("marital_desc", rs.getString("marital_desc"));
                result.put("religion_name", rs.getString("religion_name"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }

        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getjobdata(String user_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.length_of_work, a.join_date, a.employee_status, a.position, a.position_level, b.company_name, c.position_desc, d.level_desc from ops_employee a "
                    + "left join int_company b on a.company_id = b.company_id "
                    + "left join crud_position c on a.position = c.position_id and a.company_id = c.company_id "
                    + "left join crud_position_level d on a.position_level = d.position_level and a.company_id = d.company_id and a.position = d.position_id "
                    + "where a.company_id = ? and a.email = ?");
            stat.setString(1, company_id);
            stat.setString(2, user_id);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("company_name", rs.getString("company_name"));
                result.put("position_level", rs.getString("position_level"));
                result.put("level_desc", rs.getString("level_desc"));
                result.put("position", rs.getString("position"));
                result.put("position_desc", rs.getString("position_desc"));
                result.put("employee_status", rs.getString("employee_status"));
                result.put("join_date", rs.getString("join_date"));
                result.put("length_of_work", rs.getString("length_of_work"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }

        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getemergencycontact(String user_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select emergency_contact_name_1, emergency_contact_phone_1, emergency_contact_relation_1,"
                    + "emergency_contact_name_2, emergency_contact_phone_2, emergency_contact_relation_2 from ops_employee where company_id = ? and email = ?");
            stat.setString(1, company_id);
            stat.setString(2, user_id);
            rs = stat.executeQuery();
            if (rs.next()) {

                result.put("emergency_contact_name_1", rs.getString("emergency_contact_name_1"));
                result.put("emergency_contact_phone_1", rs.getString("emergency_contact_phone_1"));
                result.put("emergency_contact_relation_1", rs.getString("emergency_contact_relation_1"));
                result.put("emergency_contact_name_2", rs.getString("emergency_contact_name_2"));
                result.put("emergency_contact_phone_2", rs.getString("emergency_contact_phone_2"));
                result.put("emergency_contact_relation_2", rs.getString("emergency_contact_relation_2"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }

        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

//    public HashMap getfamilydata(String user_id, String employee_id, String company_id) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        HashMap result = new HashMap();
//        List list = new ArrayList();
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from emp_family where company_id = ? and employee_id = ? and status = '1'");
//            stat.setString(1, company_id);
//            stat.setString(2, employee_id);
//            rs = stat.executeQuery();
//            while (rs.next()) {
//
//                HashMap ab = new HashMap();
//                ab.put("name", rs.getString("name"));
//                ab.put("birth_place", rs.getString("birth_place"));
//                ab.put("birth_date", rs.getString("birth_date"));
//                ab.put("gender", rs.getString("gender"));
//                ab.put("religion", rs.getString("religion"));
//                ab.put("relation", rs.getString("relation"));
//                ab.put("nik", rs.getString("nik"));
//                ab.put("file_kk", rs.getString("file_kk"));
//                ab.put("status_approve", rs.getString("status_approve"));
//                ab.put("reference", rs.getString("reference"));
//                list.add(ab);
//                ab = null;
//
//            }
//            result.put("list", list);
//            result.put("resp_code", "0000");
//            result.put("resp_desc", "success");
//        } catch (SQLException e) {
//            result.put("resp_code", "0001");
//            result.put("resp_desc", "Error : " + e.getMessage());
//            System.out.println("error : " + e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return result;
//    }
//    ================================================================]
    public HashMap getlistfamilyuser(String employee_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_family where employee_id = ? and company_id = ? and status = '1'");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("name", rs.getString("name"));
                ab.put("birth_place", rs.getString("birth_place"));
                ab.put("birth_date", rs.getString("birth_date"));
                ab.put("gender", rs.getString("gender"));
                ab.put("religion", rs.getString("religion"));
                ab.put("relation", rs.getString("relation"));
                ab.put("nik", rs.getString("nik"));
                ab.put("status_approve", rs.getString("status_approve"));
                ab.put("reference", rs.getString("reference"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("create_date", rs.getString("create_date"));
//                ab.put("file_kk", rs.getString("file_kk"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailfamilyuser(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_family where employee_id = ? and  company_id = ? and reference = ?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            int referenceInt = Integer.parseInt(reference);
            stat.setInt(3, referenceInt);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("name", rs.getString("name"));
                result.put("birth_place", rs.getString("birth_place"));
                result.put("birth_date", rs.getString("birth_date"));
                result.put("gender", rs.getString("gender"));
                result.put("religion", rs.getString("religion"));
                result.put("relation", rs.getString("relation"));
                result.put("nik", rs.getString("nik"));
//                result.put("status_approve", rs.getString("status_approve"));
                result.put("reference", rs.getString("reference"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("create_date", rs.getString("create_date"));
                result.put("file_kk", rs.getString("file_kk"));
                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addfamilyuser(
            String name,
            String birth_place,
            String birth_date,
            String gender,
            String religion,
            String relation,
            String nik,
            String employee_id,
            String company_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into emp_family (name, birth_place, birth_date, gender, religion, relation, nik, employee_id, company_id) values (?,?,?,?,?,?,?,?,?)");
            stat.setString(1, name);
            stat.setString(2, birth_place);
            stat.setString(3, birth_date);
            stat.setString(4, gender);
            stat.setString(5, religion);
            stat.setString(6, relation);
            stat.setString(7, nik);
            stat.setString(8, employee_id);
            stat.setString(9, company_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editfamilyuser(
            String reference,
            String name,
            String birth_place,
            String birth_date,
            String gender,
            String religion,
            String relation,
            String nik,
            String employee_id,
            String company_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_family set "
                    + "name = ?, "
                    + "birth_place = ?, "
                    + "birth_date = ?, "
                    + "gender = ?, "
                    + "religion = ?, "
                    + "relation = ?, "
                    + "nik = ? "
                    + "where employee_id = ? and company_id = ? and reference = ?");
            stat.setString(1, name);
            stat.setString(2, birth_place);
            stat.setString(3, birth_date);
            stat.setString(4, gender);
            stat.setString(5, religion);
            stat.setString(6, relation);
            stat.setString(7, nik);
            stat.setString(8, employee_id);
            stat.setString(9, company_id);
            stat.setString(10, reference);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletefamilyuser(String employee_id, String company_id, String reference) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_family set status='0' where employee_id=? and company_id=? and reference=?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            stat.setString(2, reference);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlisteducationuser(String employee_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_education where employee_id = ? and company_id = ? and status = '1'");
            stat.setString(1, employee_id);
            stat.setString(1, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("education_level", rs.getString("education_level"));
                ab.put("agency_name", rs.getString("agency_name"));
                ab.put("certificate_number", rs.getString("certificate_number"));
                ab.put("graduation_year", rs.getString("graduation_year"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("reference", rs.getString("reference"));
                ab.put("formality_status", rs.getString("formality_status"));
                ab.put("create_date", rs.getString("create_date"));
                ab.put("file_ijazah", rs.getString("file_ijazah"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetaileducationuser(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_education where employee_id = ? and  company_id = ? and reference = ?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            stat.setString(3, reference);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("education_level", rs.getString("education_level"));
                result.put("agency_name", rs.getString("agency_name"));
                result.put("certificate_number", rs.getString("certificate_number"));
                result.put("graduation_year", rs.getString("graduation_year"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("company_id", rs.getString("company_id"));
                result.put("reference", rs.getString("reference"));
                result.put("formality_status", rs.getString("formality_status"));
                result.put("create_date", rs.getString("create_date"));
                result.put("file_ijazah", rs.getString("file_ijazah"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addeducationuser(
            String education_level,
            String agency_name,
            String certificate_number,
            String graduation_year,
            String formality_status,
            String file_ijazah,
            String employee_id,
            String company_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into emp_education (education_level,agency_name,certificate_number,graduation_year,formality_status,file_ijazah,employee_id,company_id) values (?,?,?,?,?,?,?,?)");
            stat.setString(1, education_level);
            stat.setString(2, agency_name);
            stat.setString(3, certificate_number);
            stat.setString(4, graduation_year);
            stat.setString(5, formality_status);
            stat.setString(6, file_ijazah);
            stat.setString(7, employee_id);
            stat.setString(8, company_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editeducationuser(
            String education_level,
            String agency_name,
            String certificate_number,
            String graduation_year,
            String formality_status,
            String file_ijazah,
            String reference,
            String employee_id,
            String company_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_education set "
                    + "education_level = ?, "
                    + "agency_name = ?, "
                    + "certificate_number = ?, "
                    + "graduation_year = ?, "
                    + "formality_status = ?, "
                    + "file_ijazah = ? "
                    + "where employee_id = ? and company_id = ? and reference = ?");
            stat.setString(1, education_level);
            stat.setString(2, agency_name);
            stat.setString(3, certificate_number);
            stat.setString(4, graduation_year);
            stat.setString(5, formality_status);
            stat.setString(6, file_ijazah);
            stat.setString(7, employee_id);
            stat.setString(8, company_id);
            stat.setString(9, reference);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteeducationuser(String employee_id, String company_id, String reference) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_education set status='0' where employee_id=? and company_id=? and reference=?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            stat.setString(2, reference);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistjobuser(String employee_id, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_job where employee_id = ? and company_id = ? and status = '1'");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("company_name", rs.getString("company_name"));
                ab.put("business_sector", rs.getString("business_sector"));
                ab.put("position", rs.getString("position"));
                ab.put("division", rs.getString("division"));
                ab.put("start_date", rs.getString("start_date"));
                ab.put("end_date", rs.getString("end_date"));
                ab.put("reference", rs.getString("reference"));
                ab.put("employee_id", rs.getString("employee_id"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("create_date", rs.getString("create_date"));
                ab.put("status_approve", rs.getString("status_approve"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailjobuser(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from emp_education where employee_id = ? and  company_id = ? and reference = ?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            stat.setString(3, reference);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("company_name", rs.getString("company_name"));
                result.put("business_sector", rs.getString("business_sector"));
                result.put("position", rs.getString("position"));
                result.put("division", rs.getString("division"));
                result.put("description", rs.getString("description"));
                result.put("start_date", rs.getString("start_date"));
                result.put("end_date", rs.getString("end_date"));
                result.put("reference", rs.getString("reference"));
                result.put("employee_id", rs.getString("employee_id"));
                result.put("company_id", rs.getString("company_id"));
                result.put("create_date", rs.getString("create_date"));
                result.put("file_paklaring", rs.getString("file_paklaring"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addjobuser(
            String company_name,
            String business_sector,
            String position,
            String division,
            String description,
            String start_date,
            String end_date,
            String employee_id,
            String company_id,
            String file_paklaring
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into emp_job (company_name,business_sector,position,division,description,start_date,end_date,employee_id,company_id,file_paklaring) values (?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, company_name);
            stat.setString(2, business_sector);
            stat.setString(3, position);
            stat.setString(4, division);
            stat.setString(5, description);
            stat.setString(6, start_date);
            stat.setString(7, end_date);
            stat.setString(9, employee_id);
            stat.setString(10, company_id);
            stat.setString(11, file_paklaring);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editjobuser(
            String company_name,
            String business_sector,
            String position,
            String division,
            String description,
            String start_date,
            String end_date,
            String reference,
            String file_paklaring,
            String employee_id,
            String company_id
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_job set "
                    + "company_name = ?, "
                    + "business_sector = ?, "
                    + "position = ?, "
                    + "division = ?, "
                    + "description = ?, "
                    + "start_date = ?, "
                    + "end_date = ?, "
                    + "file_paklaring = ? "
                    + "where employee_id = ? and company_id = ? and reference = ?");
            stat.setString(1, company_name);
            stat.setString(2, business_sector);
            stat.setString(3, position);
            stat.setString(4, division);
            stat.setString(5, description);
            stat.setString(6, start_date);
            stat.setString(7, end_date);
            stat.setString(8, file_paklaring);
            stat.setString(9, employee_id);
            stat.setString(10, company_id);
            stat.setString(11, reference);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletejobuser(String employee_id, String company_id, String reference) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_job set status='0' where employee_id=? and company_id=? and reference=?");
            stat.setString(1, employee_id);
            stat.setString(2, company_id);
            stat.setString(2, reference);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistcalendar(String date_off, String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT off.timeoff_code, emp1.employee_name AS employee_name, emp2.employee_name AS delegation_name, crud_timeoff.timeoff_desc\n"
                    + "FROM ops_request_off off\n"
                    + "LEFT JOIN ops_employee emp1 ON emp1.employee_id = off.employee_id\n"
                    + "LEFT JOIN ops_employee emp2 ON emp2.employee_id = off.delegation\n"
                    + "LEFT JOIN crud_timeoff ON off.timeoff_code = crud_timeoff.timeoff_code\n"
                    + "WHERE off.approve_status_head = 'APPROVED' \n"
                    + "  AND off.date_off = ? \n"
                    + "  AND off.company_id = ?;");
            stat.setString(1, date_off);
            stat.setString(2, company_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("timeoff_code", rs.getString("timeoff_code"));
                ab.put("timeoff_desc", rs.getString("timeoff_desc"));
                ab.put("employee_name", rs.getString("employee_name"));
                ab.put("delegation_name", rs.getString("delegation_name"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistreimbursementtype(String company_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from int_reimbursement_type where reimbursement_type_status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reimbursement_type_id", rs.getString("reimbursement_type_id"));
                ab.put("reimbursement_type_desc", rs.getString("reimbursement_type_desc"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

/////////////////////////////////////////////////////////////////
    public HashMap pengajuanreimbursement(
            String company_id,
            String employee_id,
            String reimbursement_type,
            String description,
            String user_id,
            String nominal_pengajuan,
            String tanggal_pengeluaran,
            String bukti_pengajuan
    ) throws ParseException {

        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_request_reimbursement ("
                    + "reference,"
                    + "company_id,"
                    + "employee_id,"
                    + "reimbursement_type,"
                    + "description,"
                    + "date_request,"
                    + "account_id,"
                    + "approve_status_admin,"
                    + "approve_status_head,"
                    + "nominal_pengajuan,"
                    + "tanggal_pengeluaran,"
                    + "bukti_pengajuan"
                    + ")"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, "reim_" + company_id + "|" + employee_id + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS());
            stat.setString(2, company_id);
            stat.setString(3, employee_id);
            stat.setString(4, reimbursement_type);
            stat.setString(5, description);
            stat.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(7, user_id);
            stat.setString(8, "PENDING");
            stat.setString(9, "PENDING");
            stat.setString(10, nominal_pengajuan);
            stat.setString(11, tanggal_pengeluaran);
            stat.setString(12, bukti_pengajuan);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanreimbursementadmin(String company_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.reimbursement_type_desc from ops_request_reimbursement a left join int_reimbursement_type b on\n"
                    + " a.reimbursement_type = b.reimbursement_type_id\n"
                    + " where a.company_id = ? and a.date_request >= ? and a.date_request <= ? and a.approve_status_head != 'CANCELED';");
            stat.setString(1, company_id);
            stat.setString(2, start_date);
            stat.setString(3, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));

                ab.put("description", rs.getString("description"));
                ab.put("date_request", rs.getString("date_request"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("reimbursement_type_desc", rs.getString("reimbursement_type_desc"));
                ab.put("reimbursement_type", rs.getString("reimbursement_type"));
                ab.put("nominal_pengajuan", rs.getString("nominal_pengajuan"));
                ab.put("tanggal_pengeluaran", rs.getString("tanggal_pengeluaran"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getdetailpengajuanreimbursement(String employee_id, String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.reimbursement_type_desc from ops_request_reimbursement a left join int_reimbursement_type b on\n"
                    + " a.reimbursement_type = b.reimbursement_type_id\n"
                    + " where a.company_id = ? and employee_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, reference);

            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("company_id", rs.getString("company_id"));
                result.put("employee_id", rs.getString("employee_id"));

                result.put("description", rs.getString("description"));
                result.put("date_request", rs.getString("date_request"));
                result.put("account_id", rs.getString("account_id"));
                result.put("reimbursement_type_desc", rs.getString("reimbursement_type_desc"));
                result.put("reimbursement_type", rs.getString("reimbursement_type"));
                result.put("nominal_pengajuan", rs.getString("nominal_pengajuan"));
                result.put("tanggal_pengeluaran", rs.getString("tanggal_pengeluaran"));
                result.put("bukti_pengajuan", rs.getString("bukti_pengajuan"));

                result.put("approve_status_admin", rs.getString("approve_status_admin"));
                result.put("approve_by_admin", rs.getString("approve_by_admin"));
                result.put("approve_time_admin", rs.getString("approve_time_admin"));
                result.put("approve_command_admin", rs.getString("approve_command_admin"));

                result.put("approve_status_head", rs.getString("approve_status_head"));
                result.put("approve_by_head", rs.getString("approve_by_head"));
                result.put("approve_time_head", rs.getString("approve_time_head"));
                result.put("approve_command_head", rs.getString("approve_command_head"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanreimbursementadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_reimbursement set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'APPROVED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanreimbursementadmin(String reference, String employee_id, String company_id, String user_id, String command_admin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_reimbursement set approve_by_admin = ?, "
                    + "approve_time_admin = ?, "
                    + "approve_status_admin = 'REJECTED', "
                    + "approve_command_admin = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_admin);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approvepengajuanreimbursementhead(String reference, String employee_id, String company_id, String user_id, String command_head) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        String reimbursement_type = "";
        String tanggal_pengeluaran = "";
        int nominal_pengajuan = 0;

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_request_reimbursement where reference = ?");
            stat.setString(1, reference);
            rs = stat.executeQuery();
            if (rs.next()) {

                reimbursement_type = rs.getString("reimbursement_type");
                nominal_pengajuan = Integer.parseInt(rs.getString("nominal_pengajuan"));

                stat = conn.prepareStatement("update ops_request_reimbursement set approve_by_head = ?, "
                        + "approve_time_head = ?, "
                        + "approve_status_head = 'APPROVED', "
                        + "approve_command_head = ? "
                        + "where reference = ? and company_id = ? and employee_id=?");
                stat.setString(1, user_id);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                stat.setString(2, String.valueOf(timestamp));
                stat.setString(3, command_head);
                stat.setString(4, reference);
                stat.setString(5, company_id);
                stat.setString(6, employee_id);
                stat.executeUpdate();
                //                stat.close();

                tanggal_pengeluaran = rs.getString("tanggal_pengeluaran");

                stat.close();
                stat = null;

                rs.close();
                rs = null;

                stat = conn.prepareStatement("select * from ops_employee where company_id = ? and employee_id = ?");
                stat.setString(1, company_id);
                stat.setString(2, employee_id);
                rs = stat.executeQuery();
                if (rs.next()) {

                    if (reimbursement_type.equalsIgnoreCase("TRV")) {
                        int trv_plafon = Integer.parseInt(rs.getString("trv_plafon"));
                        int trv_terpakai = Integer.parseInt(rs.getString("trv_terpakai"));

                        int new_trv_terpakai = trv_terpakai + nominal_pengajuan;
                        int new_trv_sisa = trv_plafon - new_trv_terpakai;

                        String hasil_trv_sisa = String.valueOf(new_trv_sisa);
                        String hasil_trv_terpakai = String.valueOf(new_trv_terpakai);
                        stat.close();
                        stat = null;

                        //update TRV saat approve request reimbursement TRV
                        stat = conn.prepareStatement("update ops_employee set "
                                + "trv_sisa = ?, "
                                + "trv_terpakai = ? "
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, hasil_trv_sisa);
                        stat.setString(2, hasil_trv_terpakai);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.executeUpdate();
                    } else if (reimbursement_type.equalsIgnoreCase("EDU")) {
                        int edu_plafon = Integer.parseInt(rs.getString("edu_plafon"));
                        int edu_terpakai = Integer.parseInt(rs.getString("edu_terpakai"));

                        int new_edu_terpakai = edu_terpakai + nominal_pengajuan;
                        int new_edu_sisa = edu_plafon - new_edu_terpakai;

                        String hasil_edu_sisa = String.valueOf(new_edu_sisa);
                        String hasil_edu_terpakai = String.valueOf(new_edu_terpakai);
                        stat.close();
                        stat = null;

                        //update EDU saat approve request reimbursement EDU
                        stat = conn.prepareStatement("update ops_employee set "
                                + "edu_sisa = ?, "
                                + "edu_terpakai = ? "
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, hasil_edu_sisa);
                        stat.setString(2, hasil_edu_terpakai);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.executeUpdate();
                    } else if (reimbursement_type.equalsIgnoreCase("TOL")) {
                        int tol_plafon = Integer.parseInt(rs.getString("tol_plafon"));
                        int tol_terpakai = Integer.parseInt(rs.getString("tol_terpakai"));

                        int new_tol_terpakai = tol_terpakai + nominal_pengajuan;
                        int new_tol_sisa = tol_plafon - new_tol_terpakai;

                        String hasil_tol_sisa = String.valueOf(new_tol_sisa);
                        String hasil_tol_terpakai = String.valueOf(new_tol_terpakai);
                        stat.close();
                        stat = null;

                        //update TOL saat approve request reimbursement TOL
                        stat = conn.prepareStatement("update ops_employee set "
                                + "tol_sisa = ?, "
                                + "tol_terpakai = ? "
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, hasil_tol_sisa);
                        stat.setString(2, hasil_tol_terpakai);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.executeUpdate();

                    } else if (reimbursement_type.equalsIgnoreCase("MED")) {
                        int med_plafon = Integer.parseInt(rs.getString("med_plafon"));
                        int med_terpakai = Integer.parseInt(rs.getString("med_terpakai"));

                        int new_med_terpakai = med_terpakai + nominal_pengajuan;
                        int new_med_sisa = med_plafon - new_med_terpakai;

                        String hasil_med_sisa = String.valueOf(new_med_sisa);
                        String hasil_med_terpakai = String.valueOf(new_med_terpakai);
                        stat.close();
                        stat = null;

                        //update MED saat approve request reimbursement MED
                        stat = conn.prepareStatement("update ops_employee set "
                                + "med_sisa = ?, "
                                + "med_terpakai = ? "
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, hasil_med_sisa);
                        stat.setString(2, hasil_med_terpakai);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.executeUpdate();

                    } else if (reimbursement_type.equalsIgnoreCase("OTH")) {
                        int oth_plafon = Integer.parseInt(rs.getString("oth_plafon"));
                        int oth_terpakai = Integer.parseInt(rs.getString("oth_terpakai"));

                        int new_oth_terpakai = oth_terpakai + nominal_pengajuan;
                        int new_oth_sisa = oth_plafon - new_oth_terpakai;

                        String hasil_oth_sisa = String.valueOf(new_oth_sisa);
                        String hasil_oth_terpakai = String.valueOf(new_oth_terpakai);
                        stat.close();
                        stat = null;

                        //update OTH saat approve request reimbursement OTH
                        stat = conn.prepareStatement("update ops_employee set "
                                + "oth_sisa = ?, "
                                + "oth_terpakai = ? "
                                + "where company_id = ? and employee_id = ?");
                        stat.setString(1, hasil_oth_sisa);
                        stat.setString(2, hasil_oth_terpakai);
                        stat.setString(3, company_id);
                        stat.setString(4, employee_id);
                        stat.executeUpdate();

                    }
                    result.put(RuleNameParameter.resp_code, "0000");
                    result.put(RuleNameParameter.resp_desc, "Success");
                } else {
                    result.put(RuleNameParameter.resp_code, "0006");
                    result.put(RuleNameParameter.resp_desc, "No data in " + tanggal_pengeluaran.substring(0, 4));
                }
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "No schedule for your ID, ask your admin to create");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap rejectpengajuanreimbursementhead(String reference, String employee_id, String company_id, String user_id, String command_head) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_reimbursement set approve_by_head = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_head = 'REJECTED', "
                    + "approve_command_head = ? "
                    + "where reference = ? and company_id = ? and employee_id=?");
            stat.setString(1, user_id);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, command_head);
            stat.setString(4, reference);
            stat.setString(5, company_id);
            stat.setString(6, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistpengajuanreimbursementuser(String company_id, String employee_id, String start_date, String end_date) throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(end_date));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String dateAfter = sdf.format(cal.getTime());

        try {

            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.reimbursement_type_desc from ops_request_reimbursement a left join int_reimbursement_type b on\n"
                    + " a.reimbursement_type = b.reimbursement_type_id\n"
                    + " where a.company_id = ? and a.employee_id = ? and a.date_request >= ? and a.date_request <= ?;");
            stat.setString(1, company_id);
            stat.setString(2, employee_id);
            stat.setString(3, start_date);
            stat.setString(4, dateAfter);
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("reference", rs.getString("reference"));
                ab.put("company_id", rs.getString("company_id"));
                ab.put("employee_id", rs.getString("employee_id"));

                ab.put("date_request", rs.getString("date_request"));
                ab.put("account_id", rs.getString("account_id"));
                ab.put("reimbursement_type_desc", rs.getString("reimbursement_type_desc"));
                ab.put("reimbursement_type", rs.getString("reimbursement_type"));
                ab.put("nominal_pengajuan", rs.getString("nominal_pengajuan"));
                ab.put("tanggal_pengeluaran", rs.getString("tanggal_pengeluaran"));

                ab.put("approve_status_admin", rs.getString("approve_status_admin"));
                ab.put("approve_by_admin", rs.getString("approve_by_admin"));
                ab.put("approve_time_admin", rs.getString("approve_time_admin"));

                ab.put("approve_status_head", rs.getString("approve_status_head"));
                ab.put("approve_by_head", rs.getString("approve_by_head"));
                ab.put("approve_time_head", rs.getString("approve_time_head"));

                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap cancelpengajuanreimbursementuser(String reference, String employee_id, String company_id, String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update ops_request_reimbursement set "
                    + "approve_time_admin = ?, "
                    + "approve_time_head = ?, "
                    + "approve_status_admin = 'CANCELED', "
                    + "approve_status_head = 'CANCELED' "
                    + "where reference = ? and company_id = ? and employee_id=?");

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stat.setString(1, String.valueOf(timestamp));
            stat.setString(2, String.valueOf(timestamp));
            stat.setString(3, reference);
            stat.setString(4, company_id);
            stat.setString(5, employee_id);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistannouncementadmin(String company_id) {
        Connection conn = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        HashMap<String, Object> result = new HashMap<>();
        List<HashMap<String, String>> list = new ArrayList<>();

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();

            // Update expired announcements
            stat1 = conn.prepareStatement("UPDATE ops_announcement SET status = 'EXPIRED' WHERE TO_DATE(tanggal_berakhir, 'YYYY-MM-DD') < CURRENT_DATE AND status != 'CANCELED' AND status != 'EXPIRED'");
            stat1.executeUpdate();

            // Retrieve announcements
            stat2 = conn.prepareStatement("SELECT * FROM ops_announcement WHERE company_id = ?");
            stat2.setString(1, company_id);
            rs2 = stat2.executeQuery();

            while (rs2.next()) {
                HashMap<String, String> ab = new HashMap<>();
                ab.put("reference", rs2.getString("reference"));
                ab.put("date_create", rs2.getString("date_create"));
                ab.put("title", rs2.getString("title"));
                ab.put("status", rs2.getString("status"));
                ab.put("tanggal_berakhir", rs2.getString("tanggal_berakhir"));
                list.add(ab);
            }

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error: " + e.getMessage());
            System.out.println("error: " + e);
        } finally {
            clearAllConnStatRS(conn, stat1, rs1);
            clearAllConnStatRS(null, stat2, rs2);
        }

        return result;
    }

    public HashMap getdetailannouncement(String company_id, String reference) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from ops_announcement where company_id = ? and reference = ?");
            stat.setString(1, company_id);
            stat.setString(2, reference);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put("reference", rs.getString("reference"));
                result.put("date_create", rs.getString("date_create"));
                result.put("title", rs.getString("title"));
                result.put("tanggal_berakhir", rs.getString("tanggal_berakhir"));
                result.put("content", rs.getString("content"));
                result.put("image", rs.getString("image"));
                result.put("created_by", rs.getString("created_by"));

                result.put("resp_code", "0000");
                result.put("resp_desc", "success");
            } else {
                result.put(FieldParameterMatapos.resp_code, "0002");
                result.put(FieldParameterMatapos.resp_desc, "not found");
            }
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error : " + e.getMessage());
            System.out.println("error : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap<String, Object> getlistannouncementuser(String company_id) {
        Connection conn = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        HashMap<String, Object> result = new HashMap<>();
        List<HashMap<String, String>> list = new ArrayList<>();

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();

            // Update expired announcements
            stat1 = conn.prepareStatement("UPDATE ops_announcement SET status = 'EXPIRED' WHERE TO_DATE(tanggal_berakhir, 'YYYY-MM-DD') < CURRENT_DATE AND status != 'CANCELED' AND status != 'EXPIRED'");
            stat1.executeUpdate();

            // Retrieve announcements
            stat2 = conn.prepareStatement("SELECT * FROM ops_announcement WHERE company_id = ? AND status != 'CANCELED'");
            stat2.setString(1, company_id);
            rs2 = stat2.executeQuery();

            while (rs2.next()) {
                HashMap<String, String> ab = new HashMap<>();
                ab.put("reference", rs2.getString("reference"));
                ab.put("date_create", rs2.getString("date_create"));
                ab.put("title", rs2.getString("title"));
                ab.put("status", rs2.getString("status"));
                ab.put("tanggal_berakhir", rs2.getString("tanggal_berakhir"));
                list.add(ab);
            }

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", "Error: " + e.getMessage());
            System.out.println("error: " + e);
        } finally {
            clearAllConnStatRS(conn, stat1, rs1);
            clearAllConnStatRS(null, stat2, rs2);
        }

        return result;
    }

    public HashMap addannouncement(
            String company_id,
            String title,
            String content,
            String image,
            String user_id,
            String tanggal_berakhir
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into ops_announcement(\n"
                    + "	reference, company_id, title, content, image, created_by, tanggal_berakhir)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?);");
            stat.setString(1, "announce_" + company_id + "|" + StringFunction.getCurrentDateYYYYMMDDHHMMSSSS());
            stat.setString(2, company_id);
            stat.setString(3, title);
            stat.setString(4, content);
            stat.setString(5, image);
            stat.setString(6, user_id);
            stat.setString(7, tanggal_berakhir);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editannouncement(
            String company_id,
            String title,
            String content,
            String image,
            String user_id,
            String tanggal_berakhir,
            String reference
    ) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update emp_job set "
                    + "title = ?, "
                    + "content = ?, "
                    + "image = ?, "
                    + "tanggal_berakhir = ? "
                    + "where company_id = ? and reference = ?");
            stat.setString(1, title);
            stat.setString(2, content);
            stat.setString(3, image);
            stat.setString(4, tanggal_berakhir);
            stat.setString(5, company_id);
            stat.setString(6, reference);

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Error : " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

}
