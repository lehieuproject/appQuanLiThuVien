/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.services;

import com.slh.pojo.NhanVien;
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
public class NhanVienService {

    public List<NhanVien> getNhanVien() throws SQLException {
        List<NhanVien> employ = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
            while (rs.next()) {
                int id = rs.getInt("MaNhanVien");
                String hoNV = rs.getString("hoNV");
                String tenNV = rs.getString("tenNV");
                String TaiKhoan = rs.getString("tai_khoan");
                String MatKhau = rs.getString("mat_khau");
                boolean LoaiNV = rs.getBoolean("loai_NV");
                String idinfor = rs.getString("id_infor");
                employ.add(new NhanVien(id, hoNV, tenNV, TaiKhoan, MatKhau, LoaiNV, String.valueOf(idinfor)));
            }
        }
        return employ;
    }

    public List<NhanVien> getNhanVienid(String id) throws SQLException {
        List<NhanVien> employ = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM nhanvien where id =?";
            PreparedStatement stm = conn.prepareCall(sql);

            if (id != null && !id.isEmpty()) {
                stm.setString(1, id);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = String.valueOf(rs.getInt("MaNhanVien"));
                String hoNV = rs.getString("hoNV");
                String tenNV = rs.getString("tenNV");
                String TaiKhoan = rs.getString("tai_khoan");
                String MatKhau = rs.getString("mat_khau");
                boolean LoaiNV = rs.getBoolean("loai_NV");
                String idinfor = rs.getString("id_infor");
                employ.add(new NhanVien(Integer.parseInt(id), hoNV, tenNV, TaiKhoan, MatKhau, LoaiNV, idinfor));
            }
        }
        return employ;
    }
   public List<NhanVien> getTenNhanVien(String ten) throws SQLException {
        List<NhanVien> employ = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
//                Statement stm = conn.createStatement();
            String sql = "SELECT * FROM nhanvien\n";
            if( ten!=null && !ten.isEmpty()){
                sql += " where TenNV LIKE CONCAT('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if( ten!=null && !ten.isEmpty()){
                stm.setString(1, ten);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaNhanVien");
                String hoNV = rs.getString("hoNV");
                String tenNV = rs.getString("tenNV");
                String TaiKhoan = rs.getString("tai_khoan");
                String MatKhau = rs.getString("mat_khau");
                boolean LoaiNV = rs.getBoolean("loai_NV");
                String idinfor = rs.getString("id_infor");
                employ.add(new NhanVien(id, hoNV, tenNV, TaiKhoan, MatKhau, LoaiNV, idinfor));
            }
        }
        return employ;
    }
   
     public boolean deleteNhanVien(String idnv) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM nhanvien WHERE MaNhanVien=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, idnv);
            return stm.executeUpdate() > 0;
        }

    }

    public boolean addNhanVien(NhanVien v) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO nhanvien( MaNhanVien,hoNV, tenNV, tai_khoan, mat_khau, loai_NV,id_infor) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, v.getMaNhanVien());
            stm.setString(2, v.getHoNV());
            stm.setString(3, v.getTenNV());
            stm.setString(4, v.getTaiKhoan());
            stm.setString(5, v.getMatKhau());
            stm.setBoolean(6, v.isLoaiNV());
             stm.setString(7, v.getIdinfor());
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

    public boolean updateNhanVien(NhanVien v) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE nhanvien set  hoNV=?, tenNV=?, tai_khoan=?, mat_khau=?, loai_NV=?,id_infor=?  WHERE MaNhanVien = ?";
            PreparedStatement stm = conn.prepareCall(sql);
              stm.setInt(7, v.getMaNhanVien());
            stm.setString(1, v.getHoNV());
            stm.setString(2, v.getTenNV());
            stm.setString(3, v.getTaiKhoan());
            stm.setString(4, v.getMatKhau());
            stm.setBoolean(5, v.isLoaiNV());
            stm.setString(6, v.getIdinfor());
          
            
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
    
    

}
