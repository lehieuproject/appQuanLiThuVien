package com.slh.quanlythuvienapp;

import com.slh.pojo.DocGia;
import com.slh.pojo.MuonTra;
import com.slh.pojo.NhanVien;
import com.slh.pojo.Sach;
import com.slh.services.DocGiaService;
import com.slh.services.MuonTraService;
import com.slh.services.SachServices;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {

    @FXML
    private ComboBox cbTinhTrang;
    ObservableList<String> lists = FXCollections.observableArrayList("con", "het");
    @FXML
    private TableView<Sach> tbSach;
    @FXML
    private TableView<Sach> tbDangKi;
    @FXML
    private TextField txtTimKiem;

    @FXML
    private TextField txtSachId;
    @FXML
    private TextField txtTenSach;
    @FXML
    private TextField txtTacGia;
    @FXML
    private TextField txttgid;
    @FXML
    private TextField txtTheLoai;
    @FXML
    private TextField txttlid;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private Label tat;
    @FXML
    private Button btnDangXuat;
    int idnow = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableColumns();
        try {
            this.loadSach(null, null, null);
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TimSach(ActionEvent e) throws SQLException {
        this.loadSach(this.txtTenSach.getText(), this.txtTacGia.getText(), this.txtTheLoai.getText());
    }

    public void ResetSach(ActionEvent e) throws SQLException {
        txtTenSach.clear();
        txtTacGia.clear();
        txtTheLoai.clear();
        this.loadSach(null, null, null);
    }

    public void loadTableColumns() {

        TableColumn colIds = new TableColumn("id");
        colIds.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colName = new TableColumn("name");
        colName.setCellValueFactory(new PropertyValueFactory("sach_name"));

        TableColumn colTheLoai = new TableColumn("The Loai");
        colTheLoai.setCellValueFactory(new PropertyValueFactory("the_loai_name"));

        TableColumn colTacGia = new TableColumn("Tac Gia");
        colTacGia.setCellValueFactory(new PropertyValueFactory("tac_gia_name"));

        TableColumn colSoLuong = new TableColumn("So Luong");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("so_luong"));

        TableColumn colTinhTrang = new TableColumn("Tinh Trang");
        colTinhTrang.setCellValueFactory(new PropertyValueFactory("trang_thai"));

        this.tbSach.getColumns().addAll(colIds, colName, colTheLoai, colTacGia, colSoLuong, colTinhTrang);

        TableColumn colIddk = new TableColumn("id");
        colIddk.setCellValueFactory(new PropertyValueFactory("id"));
        colIddk.setPrefWidth(30);

        TableColumn colNamedk = new TableColumn("name");
        colNamedk.setCellValueFactory(new PropertyValueFactory("sach_name"));
        colNamedk.setPrefWidth(150);

        TableColumn colTheLoaidk = new TableColumn("The Loai");
        colTheLoaidk.setCellValueFactory(new PropertyValueFactory("the_loai_name"));

        TableColumn colTacGiadk = new TableColumn("Tac Gia");
        colTacGiadk.setCellValueFactory(new PropertyValueFactory("tac_gia_name"));

        this.tbDangKi.getColumns().addAll(colIddk, colNamedk, colTheLoaidk, colTacGiadk);
    }

    public void loadSach(String kw, String kw2, String kw3) throws SQLException {
        SachServices s = new SachServices();
        this.tbSach.setItems(FXCollections.observableList(s.getSach(kw, kw2, kw3)));
    }

    public void ClickDK(ActionEvent evt) throws SQLException {
        MuonTraService mt = new MuonTraService();
        if (mt.getHanThe(String.valueOf(idnow)) == 0) {
            if (tbDangKi.getItems().size() + 1 <= 5) {
                Sach current = tbSach.getSelectionModel().getSelectedItem();
                this.tbDangKi.getItems().add(current);
            } else {
                MessageBox.getBox("Lỗi thêm sách", "Mỗi lần chỉ được mươn tối da 5 quyển", Alert.AlertType.ERROR).show();
            }
        } else {
            MessageBox.getBox("Gia hạn thẻ", "Thẻ thư viện đã hết hạn", Alert.AlertType.ERROR).show();
        }
        
        DocGiaService s = new DocGiaService();
        Node node = (Node) evt.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        NhanVien nv = (NhanVien) stage.getUserData();
        idnow = Integer.parseInt(nv.getIdinfor());
        DocGia dg = s.getDocGia(idnow);
        String Tendg = dg.getName();
        tat.setText(Tendg);
    }

    public void ClearDK(ActionEvent evt) throws SQLException {
        this.tbDangKi.getItems().clear();
    }

    public void addifMuonTra(ActionEvent evt) throws SQLException {
        MuonTraService mt = new MuonTraService();
        MuonTra m = new MuonTra();
        for (int i = 0; i < tbDangKi.getItems().size(); i++) {
            Sach a = tbDangKi.getItems().get(i);
            m.setDocgia_id(idnow);
            m.setSach_id(a.getId());
            m.setNgaymuon(Date.valueOf(LocalDate.now()));
            m.setNgaytra(Date.valueOf(LocalDate.now().plusDays(2)));
            mt.addMuonTra(m);
        }
        this.tbDangKi.getItems().clear();
    }

    public void ClickSua(MouseEvent evt) {
        Sach current = tbSach.getSelectionModel().getSelectedItem();
        txtTenSach.setText(String.valueOf(current.getSach_name()));
        txtTacGia.setText(String.valueOf(current.getTac_gia_name()));
        txtTheLoai.setText(String.valueOf(current.getThe_loai_name()));
    }

//    public void NhanData(MouseEvent evt) throws SQLException{
//        DocGiaService s = new DocGiaService();
//        Node node = (Node) evt.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        NhanVien nv = (NhanVien) stage.getUserData();
//        idnow = Integer.parseInt(nv.getIdinfor());
//        DocGia dg = s.getDocGia(idnow);
//        String Tendg = dg.getName();
//        tat.setText(Tendg);
//    }
    public void DangXuat(ActionEvent evt) throws IOException {
        String nf = "DangNhap.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Trang Đăng Nhập");
        formStage.show();
        Stage oldStage = (Stage) btnDangXuat.getScene().getWindow();
        oldStage.close();
    }
}
