/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.services;

import com.slh.pojo.MuonTra;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Admin
 */
public class MuonTraService {

    public List<MuonTra> getMuonTra(String kw, String key) throws SQLException {
        List<MuonTra> muontra = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT muontra.id, muontra.docgia_id, muontra.sach_id, docgia.name, sach.sach_name, muontra.ngaymuon, muontra.ngaytra\n"
                    + "FROM muontra\n"
                    + "inner join docgia on  muontra.docgia_id = docgia.id\n"
                    + "inner join sach on muontra.sach_id = sach.id\n";
            if (kw != null && !kw.isEmpty()) {
                if ("khach".equals(key)) {
                    sql += "where name like concat('%', ?, '%')\n";
                }
                if ("sach".equals(key)) {
                    sql += "where sach_name like concat('%', ?, '%')\n";
                }
            }
            //  sql += "order by muontra.ngaymuon desc\n";
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MuonTra mt = new MuonTra(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sach_name"),
                        rs.getDate("ngaymuon"),
                        rs.getDate("ngaytra"),
                        rs.getInt("docgia_id"),
                        rs.getInt("sach_id"));
                muontra.add(mt);
            }
        }
        return muontra;
    }

    public int getHanThe(String id) throws SQLException {
        int i = 0;
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "select * from thetv\n"
                    + "where id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            if (id != null && !id.isEmpty()) {
                stm.setString(1, id);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Date ndk = rs.getDate("ngay_dk");
                Date nhh = rs.getDate("ngay_hh");
                long timeDiff = (nhh.getTime() - ndk.getTime());
                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                if (daysDiff > 0) {
                    i=0;
                } else {
                    i=1;
                }
            }
        }
        return i;
    }

    public boolean deleteMuonTra(String id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM muontra WHERE id=?\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
//            String sql1 = "ALTER TABLE muontra Drop id";
//            String sql2 = "ALTER TABLE muontra ADD id INT NOT NULL PRIMARY KEY AUTO_INCREMENT";
//            stm.executeUpdate(sql1);
//            stm.executeUpdate(sql2);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateMuonTra(String id, String ngaymuon, String ngaytra, String docgia_id, String sach_id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "update muontra\n"
                    + "set ngaymuon=?,ngaytra=?,docgia_id=?,sach_id=?\n"
                    + "where id=?\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ngaymuon);
            stm.setString(2, ngaytra);
            stm.setString(3, docgia_id);
            stm.setString(4, sach_id);
            stm.setString(5, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean addMuonTra(MuonTra mt) throws SQLException {
        String docgia_id = String.valueOf(mt.getDocgia_id());
        String sach_id = String.valueOf(mt.getSach_id());
        String ngaymuon = String.valueOf(mt.getNgaymuon());
        String ngaytra = String.valueOf(mt.getNgaytra());
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "INSERT INTO muontra(docgia_id,sach_id,ngaymuon,ngaytra) VALUES(?, ?,?,?)\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, docgia_id);
            stm.setString(2, sach_id);
            stm.setString(3, ngaymuon);
            stm.setString(4, ngaytra);

            return stm.executeUpdate() > 0;
        }
    }
}
