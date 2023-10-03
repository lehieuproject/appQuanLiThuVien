/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slh.quanlythuvienapp;

import com.slh.pojo.MuonTra;
import com.slh.services.MuonTraService;
import com.slh.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class ThongKe implements Initializable {

    MuonTraService s = new MuonTraService();
    int[] thang = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    @FXML
    private Button btnDangXuat;
    @FXML
    private BarChart barChart;
    @FXML
    private Axis X;
    @FXML
    private Axis Y;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series sr = new XYChart.Series();
        XYChart.Series sr2 = new XYChart.Series();
        MuonTraService s = new MuonTraService();
        List<Integer> NM = new ArrayList<>();
        List<Integer> NT = new ArrayList<>();

        sr.setName("Tình Hình Mượn Sách");
        sr2.setName("Tình Hình Trả Sách");

        try {
            this.gan(NM, NT);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < thang.length; i++) {
            int sum = 0;
            for (int nm : NM) {
                if (nm == thang[i]) {
                    sum += 1;
                }
                
            }
            sr.getData().add(new XYChart.Data("Tháng " + String.valueOf(thang[i]), sum));
            sum = 0;
            for(int nm : NT)
            {
                if(nm == thang[i])
                {
                    sum +=1;
                }
                sr2.getData().add(new XYChart.Data("Tháng " + String.valueOf(thang[i]), sum));
            }
        }
//        sr.getData().add(new XYChart.Data("Tháng 1 ", 46));
//        sr2.getData().add(new XYChart.Data("Tháng 1 ", 46));

        barChart.getData().addAll(sr, sr2);
    }
    public void gan(List<Integer> a, List<Integer> b) throws SQLException {
        List<MuonTra> mt = s.getMuonTra(null, null);
        for (MuonTra m : mt) {
            int lcdm = m.getNgaymuon().toLocalDate().getMonthValue();
            int lcdt = m.getNgaytra().toLocalDate().getMonthValue();
            a.add(lcdm);
            b.add(lcdt);
        }
    }
//    public void DangXuat(ActionEvent evt) throws IOException {
//        String nf = "DangNhap.fxml";
//        Parent form = FXMLLoader.load(getClass().getResource(nf));
//        Scene formScene = new Scene(form);
//        Stage formStage = new Stage();
//        formStage.setScene(formScene);
//        formStage.setTitle("Trang Đăng Nhập");
//        formStage.show();
//        Stage oldStage = (Stage) btnDangXuat.getScene().getWindow();
//        oldStage.close();
//    }
}
