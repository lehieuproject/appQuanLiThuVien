/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.pojo;

/**
 *
 * @author PC
 */
public class DocGia {
    private int id;
    private String name;
    private String email;
    private String so_dien_thoai;
    private String dia_chi;

    public DocGia(int id, String name, String email, String so_dien_thoai,String dia_chi) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.so_dien_thoai = so_dien_thoai;
        this.dia_chi = dia_chi;
    }
    public DocGia(){
        
    }
    public DocGia( String name, String email, String so_dien_thoai,String dia_chi) {
        this.name = name;
        this.email = email;
        this.so_dien_thoai = so_dien_thoai;
        this.dia_chi = dia_chi;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the so_dien_thoai
     */
    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    /**
     * @param so_dien_thoai the so_dien_thoai to set
     */
    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    /**
     * @return the dia_chi
     */
    public String getDia_chi() {
        return dia_chi;
    }

    /**
     * @param dia_chi the dia_chi to set
     */
    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
}


