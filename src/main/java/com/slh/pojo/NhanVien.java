/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.pojo;

/**
 *
 * @author PC
 */
public class NhanVien {
    private int maNhanVien;
    private String hoNV;
    private String tenNV;
    private String taiKhoan;
    private String matKhau;
    private boolean loaiNV;
    private String idinfor;

    public NhanVien(int maNhanVien, String hoNV, String tenNV, String taiKhoan, String matKhau, Boolean loaiNV, String idinfor) {
        this.maNhanVien = maNhanVien;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.loaiNV = loaiNV;
        this.idinfor = idinfor;
    }
     public NhanVien(String hoNV, String tenNV, String taiKhoan, String matKhau, Boolean loaiNV, String idinfor) {
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.loaiNV = loaiNV;
        this.idinfor = idinfor;
    }

    

    @Override
    public String toString() {
        return this.hoNV + this.tenNV;
    }

    
    /**
     * @return the maNhanVien
     */
    public int getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the hoNV
     */
    public String getHoNV() {
        return hoNV;
    }

    /**
     * @param hoNV the hoNV to set
     */
    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the loaiNV
     */
    public boolean isLoaiNV() {
        return loaiNV;
    }

    /**
     * @param loaiNV the loaiNV to set
     */
    public void setLoaiNV(boolean loaiNV) {
        this.loaiNV = loaiNV;
    }

    /**
     * @return the idinfor
     */
    public String getIdinfor() {
        return idinfor;
    }

    /**
     * @param idinfor the idinfor to set
     */
    public void setIdinfor(String idinfor) {
        this.idinfor = idinfor;
    }

    /**
     * @return the loaiNV
     */
 

    /**
     * @return the loaiNV
     */
}
