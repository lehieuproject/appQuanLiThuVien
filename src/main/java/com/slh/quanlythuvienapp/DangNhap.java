/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.quanlythuvienapp;

import com.slh.pojo.NhanVien;
import com.slh.services.NhanVienService;
import com.slh.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class DangNhap implements Initializable {

    NhanVienService n = new NhanVienService();
    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private PasswordField txtMatKhau;
    @FXML
    private Button btnDangNhap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    public void kiemTraDangNhap(ActionEvent evt) throws SQLException, IOException {
        String tk = this.txtTaiKhoan.getText();
        String pw = this.txtMatKhau.getText();
        List<NhanVien> c = n.getNhanVien();
        int kiemtra = 0;
        if (!tk.isEmpty() && !pw.isEmpty()) {
            for (NhanVien nv : c) {
                if (nv.getTaiKhoan().equals(tk) && nv.getMatKhau().equals(pw)) {
                    kiemtra++;

                    if (nv.isLoaiNV() == true) {
                        String nf = "primary.fxml";
                        Parent form = FXMLLoader.load(getClass().getResource(nf));
                        Scene formScene = new Scene(form);
                        Stage formStage = new Stage();
                        formStage.setScene(formScene);
                        formStage.setTitle("Trang Quan Li");
                        formScene.setUserData(nv);
                        formStage.show();                      
                        break;

                    } else if (nv.isLoaiNV() == false) {
                        Node node = (Node) evt.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        String nf = "secondary.fxml";
                        Parent form = FXMLLoader.load(getClass().getResource(nf));
                        stage.setUserData(nv);
                        Scene scene = new Scene(form);
                        stage.setScene(scene);
                        stage.show();

//                        String nf = "secondary.fxml";
//                        Parent form = FXMLLoader.load(getClass().getResource(nf));
//                        Scene formScene = new Scene(form);
//                        Stage formStage = new Stage();
//                        formStage.setScene(formScene);
//                        formStage.setTitle("Thu Vien");
//                        formStage.setUserData(nv);
//                        formScene.setUserData(nv);
//                        formStage.show();
//                        
//                        Stage oldStage = (Stage) btnDangNhap.getScene().getWindow();
//                        oldStage.close();
                        break;
                    }
                }
            }
            if (kiemtra == 0) {
                Alert a = MessageBox.getBox("Thông Báo", "Sai Tài Khoản Hoặc Mật Khẩu", Alert.AlertType.CONFIRMATION);
                a.show();
            }
        } else {
            Alert b = MessageBox.getBox("Cảnh Báo", "Vui lòng nhâp tài khoản và mật khẩu!!", Alert.AlertType.CONFIRMATION);
            b.show();
        }
    }
}
