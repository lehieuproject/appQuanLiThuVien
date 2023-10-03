/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.muontra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test.test;


/**
 *
 * @author sang
 */
public class PassWord {
    @ParameterizedTest
    @CsvSource({"$$$$$$$,true"})
    public void passwordValidation(String password, boolean isValid) {
        Assertions.assertEquals(test.isPasswordValid(password), isValid);
    }
    
}
