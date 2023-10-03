/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.services;

import com.slh.pojo.DocGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DocGiaService {

    public List<DocGia> getDocGia(String kw) throws SQLException {
        List<DocGia> bks = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM docgia";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE name like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String so_dien_thoai = rs.getString("so_dien_thoai");
                String dia_chi = rs.getString("dia_chi");
                bks.add(new DocGia(id, name, email, so_dien_thoai, dia_chi));
            }
        }
        return bks;
    }
    public DocGia getDocGia(int id) throws SQLException {
        String ids = String.valueOf(id);
        DocGia bks = new DocGia();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM docgia";
            if (ids != null && !ids.isEmpty()) {
                sql += " WHERE id = ?";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (ids != null && !ids.isEmpty()) {
                stm.setString(1, ids);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String so_dien_thoai = rs.getString("so_dien_thoai");
                String dia_chi = rs.getString("dia_chi");
                bks = new DocGia(id, name, email, so_dien_thoai, dia_chi);
            }
        }
        return bks;
    }
    public List<DocGia> getDocgia() throws SQLException {
        List<DocGia> bk = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM docgia");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String so_dien_thoai = rs.getString("so_dien_thoai");
                String dia_chi = rs.getString("dia_chi");
                bk.add(new DocGia(id, name, email, so_dien_thoai, dia_chi));
            }
        }
        
        return bk;
    }

    public boolean addUser(DocGia dg) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String query = "INSERT into docgia (name, email,so_dien_thoai,dia_chi) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, dg.getName());
            stm.setString(2, dg.getEmail());
            stm.setString(3, dg.getSo_dien_thoai());
            stm.setString(4, dg.getDia_chi());
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

    public boolean updateDocGia(int id, String name, String diachi, String email, String soDT) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "update docgia\n"
                    + "set name=?,dia_chi=?,email=?,so_dien_thoai=?\n"
                    + "where id=?\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, name);
            stm.setString(2, diachi);
            stm.setString(3, email);
            stm.setString(4, soDT);
            stm.setInt(5, id);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean deleteTheDocGia(String id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM thetv WHERE id=?;\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean deleteDocGia(String id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM docgia WHERE id=?;\n";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
            return stm.executeUpdate() > 0;
        }
    }

//    public boolean KTThe(String id) throws SQLException {
//        try (Connection conn = JdbcUtils.getConn()) {
//            String sql = "DELETE FROM docgia WHERE id=?";
//            PreparedStatement stm = conn.prepareCall(sql);
//            stm.setString(1, id);
//
//            return stm.executeUpdate() > 0;
//        }
//    }
}
