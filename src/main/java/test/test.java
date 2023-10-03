/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author sang
 */
public class test {
    public static boolean isPasswordValid(String password) {
        // (?=.*[0-9]) ==> a digit must occur at least once
        // (?=.*[a-z]) ==> a lower case character must occur at least once
        // (?=.*[0-9]) ==> a upper case character must occur at least once
        // .{5,10}     ==> password length [min:5, max:10]
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,10}";
        return password.matches(pattern);
    }
    
}
