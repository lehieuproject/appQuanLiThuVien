/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.muontra;


import com.slh.pojo.DocGia;
import com.slh.services.DocGiaService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


/**
 *
 * @author sang
 */
public class DocGiaTest {
    private static DocGiaService s;
  
    @BeforeAll
    public static void beforeAll() {
        s = new DocGiaService();
    }
    
    @Test
    public void TestName() {
        try {
            List<DocGia> dg = s.getDocGia(null);
            long r = dg.stream().filter(c ->  c.getName().isEmpty()).count();
            Assertions.assertTrue(r == 0);
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
