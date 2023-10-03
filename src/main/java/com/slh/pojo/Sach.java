/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.pojo;

/**
 *
 * @author sang
 */
public class Sach {

    private int id;
    private String sach_name;
    private int tac_gia_id;
    private String tac_gia_name;
    private int the_loai_id;
    private String the_loai_name;
    private String so_luong;
    private String trang_thai;

    public Sach(int id, String sach_name,String tac_gia_name,int tac_gia_id, int the_loai_id, String the_loai_name, String so_luong,String trang_thai) {
        this.id = id;
        this.sach_name = sach_name;
        this.tac_gia_id = tac_gia_id;
        this.tac_gia_name=tac_gia_name;
        this.the_loai_id = the_loai_id;
        this.the_loai_name = the_loai_name;
        this.so_luong = so_luong;
        this.trang_thai = trang_thai;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the sach_name
     */
    public String getSach_name() {
        return sach_name;
    }

    /**
     * @param sach_name the sach_name to set
     */
    public void setSach_name(String sach_name) {
        this.sach_name = sach_name;
    }

    /**
     * @return the tac_gia_id
     */
    public int getTac_gia_id() {
        return tac_gia_id;
    }

    /**
     * @param tac_gia_id the tac_gia_id to set
     */
    public void setTac_gia_id(int tac_gia_id) {
        this.tac_gia_id = tac_gia_id;
    }

    /**
     * @return the tac_gia_name
     */
    public String getTac_gia_name() {
        return tac_gia_name;
    }

    /**
     * @param tac_gia_name the tac_gia_name to set
     */
    public void setTac_gia_name(String tac_gia_name) {
        this.tac_gia_name = tac_gia_name;
    }

    /**
     * @return the the_loai_id
     */
    public int getThe_loai_id() {
        return the_loai_id;
    }

    /**
     * @param the_loai_id the the_loai_id to set
     */
    public void setThe_loai_id(int the_loai_id) {
        this.the_loai_id = the_loai_id;
    }

    /**
     * @return the the_loai_name
     */
    public String getThe_loai_name() {
        return the_loai_name;
    }

    /**
     * @param the_loai_name the the_loai_name to set
     */
    public void setThe_loai_name(String the_loai_name) {
        this.the_loai_name = the_loai_name;
    }

    /**
     * @return the so_luong
     */
    public String getSo_luong() {
        return so_luong;
    }

    /**
     * @param so_luong the so_luong to set
     */
    public void setSo_luong(String so_luong) {
        this.so_luong = so_luong;
    }

    /**
     * @return the trang_thai
     */
    public String getTrang_thai() {
        return trang_thai;
    }

    /**
     * @param trang_thai the trang_thai to set
     */
    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
}
