/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class MuonTra {
    private int id;
    private int docgia_id;
    private int sach_id;
    private String name;
    private String sach_name;
    private Date ngaymuon;
    private Date ngaytra;
    public MuonTra(){
    }

    public MuonTra(int id, String name, String sach_name, Date ngaymuon, Date ngaytra, int docgia_id, int sach_id) {
        this.id = id;
        this.name = name;
        this.sach_name = sach_name;
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
        this.docgia_id = docgia_id;
        this.sach_id = sach_id;
    }

    @Override
    public String toString() {
        return this.getSach_name();
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
     * @return the docgia_id
     */
    public int getDocgia_id() {
        return docgia_id;
    }

    /**
     * @param docgia_id the docgia_id to set
     */
    public void setDocgia_id(int docgia_id) {
        this.docgia_id = docgia_id;
    }

    /**
     * @return the sach_id
     */
    public int getSach_id() {
        return sach_id;
    }

    /**
     * @param sach_id the sach_id to set
     */
    public void setSach_id(int sach_id) {
        this.sach_id = sach_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the ngaymuon
     */
    public Date getNgaymuon() {
        return ngaymuon;
    }

    /**
     * @param ngaymuon the ngaymuon to set
     */
    public void setNgaymuon(Date ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    /**
     * @return the ngaytra
     */
    public Date getNgaytra() {
        return ngaytra;
    }

    /**
     * @param ngaytra the ngaytra to set
     */
    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }
   
}
