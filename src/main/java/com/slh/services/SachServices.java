/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.services;

import com.slh.pojo.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sang
 */
public class SachServices {

    public List<Sach> getSach(String kw) throws SQLException {
        List<Sach> sachs = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sach\n"
                    + "inner join tacgia on sach.tac_gia_id = tacgia.id\n"
                    + "inner join theloai on sach.the_loai_id = theloai.id\n";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE sach_name like concat('%',?,'%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach s = new Sach(rs.getInt("id"),
                        rs.getString("sach_name"),
                        rs.getString("tac_gia_name"),
                        rs.getInt("tac_gia_id"),
                        rs.getInt("the_loai_id"),
                        rs.getString("the_loai_name"),
                        rs.getString("so_luong"),
                        rs.getString("trang_thai"));
                sachs.add(s);

            }

        }
        return sachs;
    }

    public List<Sach> getSach(String kw, String kw2, String kw3) throws SQLException {
        List<Sach> sachs = new ArrayList<>();
        int i = 0;
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sach\n"
                    + "inner join tacgia on sach.tac_gia_id = tacgia.id\n"
                    + "inner join theloai on sach.the_loai_id = theloai.id\n";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE sach_name like concat('%',?,'%')\n";
                i=0;
            }
            if (kw2 != null && !kw2.isEmpty()) {
                if (kw != null && !kw.isEmpty()) {
                    sql += "and tac_gia_name like concat('%',?,'%')\n";
                    i=1;
                } else {
                    sql += " WHERE tac_gia_name like concat('%',?,'%')\n";
                    i=0;
                }
            }
            if (kw3 != null && !kw3.isEmpty()) {
                if (kw2 != null && !kw2.isEmpty()||i==0) {
                    sql += "and the_loai_name like concat('%',?,'%')\n";
                } else {
                    sql += " WHERE the_loai_name like concat('%',?,'%')\n";
                }
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            i =1;
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
                i++;
            }
            if (kw2 != null && !kw2.isEmpty()) {
                stm.setString(i, kw2);
                i++;
            }
            if (kw3 != null && !kw3.isEmpty()) {
                stm.setString(i, kw3);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach s = new Sach(rs.getInt("id"),
                        rs.getString("sach_name"),
                        rs.getString("tac_gia_name"),
                        rs.getInt("tac_gia_id"),
                        rs.getInt("the_loai_id"),
                        rs.getString("the_loai_name"),
                        rs.getString("so_luong"),
                        rs.getString("trang_thai"));
                sachs.add(s);

            }

        }
        return sachs;
    }
    

    public boolean addSach(Sach s) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String query = "INSERT into sach (sach_name,the_loai_id,tac_gia_id,so_luong,trang_thai) VALUES (?, ?, ?, ?,?)";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, s.getSach_name());
            stm.setInt(2, s.getThe_loai_id());
            stm.setInt(3, s.getTac_gia_id());
            stm.setString(4, s.getSo_luong());
            stm.setString(5, s.getTrang_thai());

            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }

    public boolean deleteSach(String id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM sach WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateSach(int id, String tensach, int tacgiaid, int theloaiid, String soLuong, String tinhTrang) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "update sach\n"
                    + "set sach_name=?,tac_gia_id=?,the_loai_id=?,so_luong=?,trang_thai=?\n"
                    + "where id=?\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, tensach);
            stm.setInt(2, tacgiaid);
            stm.setInt(3, theloaiid);
            stm.setString(4, soLuong);
            stm.setString(5, tinhTrang);
            stm.setInt(6, id);
            return stm.executeUpdate() > 0;
        }
    }

}
