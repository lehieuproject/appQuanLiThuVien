package com.slh.quanlythuvienapp;

import com.slh.pojo.DocGia;
import com.slh.pojo.MuonTra;
import com.slh.pojo.NhanVien;
import com.slh.pojo.Sach;
import com.slh.services.DocGiaService;
import com.slh.services.MuonTraService;
import com.slh.services.NhanVienService;
import com.slh.services.SachServices;
import com.slh.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    static DocGiaService s = new DocGiaService();
    @FXML
    private ComboBox cbkey;
    ObservableList<String> list = FXCollections.observableArrayList("khach", "sach");
    @FXML
    private TableView<MuonTra> tbMuonTra;
    @FXML

    private TextField tfMDG;
    @FXML
    private TextField tfMDGName;
    @FXML
    private TextField tfMS;
    @FXML
    private TextField tfMSName;
    @FXML
    private DatePicker dpNM;
    @FXML
    private DatePicker dpNT;
    @FXML
    private TextArea tatong;
    @FXML
    private TextField tfs;
    @FXML
    private TextField tfs2;

    @FXML
    private TableView<DocGia> tbDocGia;
    @FXML
    private TextField txtKeyWord;
    @FXML
    private TextField txtTKNV;
    @FXML
    private TextField txtTen;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSdt;
    @FXML
    private TextField txtDiaChi;

    @FXML
    private ComboBox cbTinhTrang;
    ObservableList<String> lists = FXCollections.observableArrayList("con", "het");

    @FXML
    private ComboBox cbLoaiNhanVien;

    @FXML
    private TableView<Sach> tbSach;
    @FXML
    private TextField txtTimKiem;

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
    private Button btnDangXuat;
    @FXML
    private Button btThongKe;
    @FXML
    private TableView<NhanVien> tbNhanVien;
    @FXML
    private TextField txtHoNV;
    @FXML
    private TextField txtTenNV;
    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private PasswordField pfMatKhau;
    @FXML
    private TextField txtInfor;
    @FXML
    private TextField txtidNV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbkey.setItems(list);
        cbkey.setValue("khach");
        dpNM.setValue(LocalDate.now());
        dpNT.setValue(LocalDate.now().plusDays(30));
        cbLoaiNhanVien.getItems().addAll("Nhân Viên", "Quản Lý");
//        cbLoaiNhanVien.setValue("nv");
        this.loadTableColumns();
        try {
            String m = tfs2.getText();
            this.loadTableDataMuonTra(null, m);
            this.loadTableDataDocGia(null);
            this.loadSach(null);
            this.loaddataNhanVien();

        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfs2.setText("khach");
        this.tfs.textProperty().addListener(e -> {
            try {
                String n = tfs2.getText();
                tfs2.setText(n);
                this.loadTableDataMuonTra(this.tfs.getText(), n);
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.dpNM.valueProperty().addListener(ev -> {
            if (dpNM.getValue() != null) {
                dpNT.setValue(dpNM.getValue().plusDays(30));
            }
        });
        this.txtKeyWord.textProperty().addListener(e -> {
            try {
                this.loadTableDataDocGia(this.txtKeyWord.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtTKNV.textProperty().addListener(e -> {
            try {
                this.loadTableDataNhanVien(this.txtTKNV.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.txtTimKiem.textProperty().addListener((evt) -> {
            try {
                this.loadSach(this.txtTimKiem.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void loadTableColumns() {
//        TableColumn colId = new TableColumn("STT");
//        colId.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colMuon = new TableColumn("Ngay Muon");
        colMuon.setCellValueFactory(new PropertyValueFactory("ngaymuon"));

        TableColumn colTra = new TableColumn("Ngay Tra");
        colTra.setCellValueFactory(new PropertyValueFactory("ngaytra"));

        TableColumn colCustomer = new TableColumn("khach");
        colCustomer.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn colSach = new TableColumn("sach");
        colSach.setCellValueFactory(new PropertyValueFactory("sach_name"));

        this.tbMuonTra.getColumns().addAll(colMuon, colCustomer, colSach, colTra);

        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colDocGia = new TableColumn("Tên Đọc Giả");
        colDocGia.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn colSDT = new TableColumn("Số điện thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("so_dien_thoai"));

        TableColumn colDiaChi = new TableColumn("Địa Chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("dia_chi"));

        this.tbDocGia.getColumns().addAll(colId, colDocGia, colEmail, colSDT, colDiaChi);

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

        TableColumn colIdNV = new TableColumn("Id");
        colIdNV.setCellValueFactory(new PropertyValueFactory("maNhanVien"));

        TableColumn colHoNV = new TableColumn("Họ NV");
        colHoNV.setCellValueFactory(new PropertyValueFactory("hoNV"));

        TableColumn colTenNV = new TableColumn("Tên NV");
        colTenNV.setCellValueFactory(new PropertyValueFactory("tenNV"));

        TableColumn colTaiKhoan = new TableColumn("Tài Khoản");
        colTaiKhoan.setCellValueFactory(new PropertyValueFactory("taiKhoan"));

        TableColumn colMatKhau = new TableColumn("Mật Khẩu");
        colMatKhau.setCellValueFactory(new PropertyValueFactory("matKhau"));

        TableColumn colLoaiNV = new TableColumn("Loại Nhân Viên");
        colLoaiNV.setCellValueFactory(new PropertyValueFactory("loaiNV"));

        TableColumn colInFor = new TableColumn("Id_Infor");
        colInFor.setCellValueFactory(new PropertyValueFactory("idinfor"));

//        private int maNhanVien;
//    private String hoNV;
//    private String tenNV;
//    private String taiKhoan;
//    private String matKhau;
//    private boolean loaiNV;
//    private String idinfor;
        this.tbNhanVien.getColumns().addAll(colIdNV, colHoNV, colTenNV, colTaiKhoan, colMatKhau, colLoaiNV, colInFor);
    }

    public void loadTableDataMuonTra(String kw, String key) throws SQLException {
        MuonTraService mt = new MuonTraService();
        this.tbMuonTra.setItems(FXCollections.observableList(mt.getMuonTra(kw, key)));
    }

    private void loadTableDataDocGia(String kw) throws SQLException {
        DocGiaService dg = new DocGiaService();
        this.tbDocGia.setItems(FXCollections.observableArrayList(dg.getDocGia(kw)));
    }
    private void loadTableDataNhanVien(String kw) throws SQLException {
        NhanVienService nv = new NhanVienService();
        this.tbNhanVien.setItems(FXCollections.observableArrayList(nv.getTenNhanVien(kw)));
    }

    public void loaddataNhanVien() throws SQLException {
        NhanVienService nvs = new NhanVienService();
        List<NhanVien> nv = nvs.getNhanVien();

        this.tbNhanVien.getItems().clear();
        this.tbNhanVien.setItems(FXCollections.observableList(nv));

    }

    public void loadSach(String kw) throws SQLException {
        SachServices s = new SachServices();
        this.tbSach.setItems(FXCollections.observableList(s.getSach(kw)));
    }

    public void change(ActionEvent evt) {
        String k = String.valueOf(cbkey.getValue());
        tfs2.setText(k);
    }

    public void tbMuoseClick(MouseEvent evt) {
        MuonTra current = tbMuonTra.getSelectionModel().getSelectedItem();
        tfMDG.setText(String.valueOf(current.getDocgia_id()));
        tfMDGName.setText(current.getName());
        tfMS.setText(String.valueOf(current.getSach_id()));
        tfMSName.setText(current.getSach_name());
        dpNM.setValue(current.getNgaymuon().toLocalDate());
        dpNT.setValue(current.getNgaytra().toLocalDate());
        long time = current.getNgaytra().getTime() - current.getNgaymuon().getTime();
        if (TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS) == 2) {
            long s = NoteDK();
            if (s > 0) {
                tatong.setText("Còn " + s + " ngày là đến hạn nhận sách");
            } else {
                tatong.setText("Đã quá hạn nhận sách");
            }
        } else {
            long han = Note();
            if (han > 0) {
                tatong.setText("Chưa đến hạn trả sách");

            } else {
                tatong.setText("Quá hạn trả sách, bị phạt " + Math.abs(han * 5000) + " VNĐ");
            }
        }

    }

    public long NoteDK() {
        MuonTra current = tbMuonTra.getSelectionModel().getSelectedItem();
        long timeDiff = (current.getNgaytra().getTime() - Date.valueOf(LocalDate.now()).getTime());
        long h = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return h;
    }

    public long Note() {
        MuonTra current = tbMuonTra.getSelectionModel().getSelectedItem();
        long timeDiff = (current.getNgaytra().getTime() - Date.valueOf(LocalDate.now()).getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return daysDiff;
    }

    public void deleteMuonTra(ActionEvent event) throws SQLException {
        MuonTraService mt = new MuonTraService();
        MuonTra current = tbMuonTra.getSelectionModel().getSelectedItem();
        mt.deleteMuonTra(String.valueOf(current.getId()));
        clearinf();
        this.loadTableDataMuonTra(null, "khach");
    }

    public void upadateMuonTra(ActionEvent evt) throws SQLException {
        MuonTraService mt = new MuonTraService();
        MuonTra current = tbMuonTra.getSelectionModel().getSelectedItem();
        if (String.valueOf(dpNM.getValue()).isEmpty() || String.valueOf(dpNT.getValue()).isEmpty() || tfMDG.getText().isEmpty() || tfMS.getText().isEmpty()) {
            MessageBox.getBox("Lỗi sửa", "Hãy thêm dữ liệu vào ô trống", Alert.AlertType.ERROR).show();
        } else {
            mt.updateMuonTra(String.valueOf(current.getId()), String.valueOf(dpNM.getValue()), String.valueOf(dpNT.getValue()), tfMDG.getText(), tfMS.getText());
            MessageBox.getBox("Thành công", "Đã sửa dữ liệu", Alert.AlertType.INFORMATION).show();
        }
        clearinf();
        this.loadTableDataMuonTra(null, tfs2.getText());
    }

    public void addMuonTra(ActionEvent event) throws SQLException {
        MuonTraService mt = new MuonTraService();
        int MaDG = Integer.parseInt(tfMDG.getText());
        int MaS = Integer.parseInt(tfMS.getText());
        Date NM = Date.valueOf(dpNM.getValue());
        Date NT = Date.valueOf(dpNT.getValue().plusDays(30));
        if (String.valueOf(dpNM.getValue()).isEmpty() || String.valueOf(dpNT.getValue()).isEmpty() || tfMDG.getText().isEmpty() || tfMS.getText().isEmpty()) {
            MessageBox.getBox("Lỗi thêm", "Hãy thêm dữ liệu vào ô trống", Alert.AlertType.ERROR).show();
        } else {
            MuonTra m = new MuonTra(0, null, null, NM, NT, MaDG, MaS);
            mt.addMuonTra(m);
            clearinf();
            MessageBox.getBox("Thành công", "Đã thêm dữ liệu", Alert.AlertType.INFORMATION).show();
        }
        this.loadTableDataMuonTra(null, tfs2.getText());
    }

    public void addDocGia(ActionEvent evt) throws SQLException {
        if (this.txtTen.getText().isEmpty() || this.txtEmail.getText().isEmpty() || this.txtSdt.getText().isEmpty() || this.txtDiaChi.getText().isEmpty()) {
            MessageBox.getBox("Đọc giả", "Add failed", Alert.AlertType.ERROR).show();
        } else {
            DocGia dg = new DocGia(this.txtTen.getText(), this.txtEmail.getText(), this.txtSdt.getText(), this.txtDiaChi.getText());
            s.addUser(dg);
            MessageBox.getBox("Đọc giả", "Add successful", Alert.AlertType.INFORMATION).show();
        }
    }

    public void hienThi_Click(MouseEvent evt) {
        DocGia current = tbDocGia.getSelectionModel().getSelectedItem();
        txtTen.setText(current.getName());
        txtDiaChi.setText(current.getDia_chi());
        txtEmail.setText(current.getEmail());
        txtSdt.setText(current.getSo_dien_thoai());
    }

    public void delete_Click(ActionEvent event) throws SQLException {
        DocGia current = tbDocGia.getSelectionModel().getSelectedItem();
        s.deleteDocGia(String.valueOf(current.getId()));
        s.deleteTheDocGia(String.valueOf(current.getId()));
        clearinf();
        this.loadTableDataDocGia(null);
    }

    public void upadate_click(ActionEvent evt) throws SQLException {
        DocGia current = tbDocGia.getSelectionModel().getSelectedItem();
        if (txtTen.getText().isEmpty() || txtDiaChi.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSdt.getText().isEmpty()) {
            MessageBox.getBox("Lỗi sửa", "Hãy thêm dữ liệu vào ô trống", Alert.AlertType.ERROR).show();
        } else {
            s.updateDocGia(current.getId(), txtTen.getText(), txtDiaChi.getText(), txtEmail.getText(), txtSdt.getText());
            MessageBox.getBox("Thành công", "Đã sửa dữ liệu", Alert.AlertType.INFORMATION).show();
        }
        clearinf();
        this.loadTableDataDocGia(null);
    }

    public void ClickSua(MouseEvent evt) {
        Sach current = tbSach.getSelectionModel().getSelectedItem();
        txtTenSach.setText(String.valueOf(current.getSach_name()));
        txttgid.setText(String.valueOf(current.getTac_gia_id()));
        txtTacGia.setText(String.valueOf(current.getTac_gia_name()));
        txttlid.setText(String.valueOf(current.getThe_loai_id()));
        txtTheLoai.setText(String.valueOf(current.getThe_loai_name()));
        txtSoLuong.setText(String.valueOf(current.getSo_luong()));
        cbTinhTrang.setValue(String.valueOf(current.getTrang_thai()));
        // cbTinhTrang.setValue(String.valueOf(current.getTrang_thai()));

    }

    public void add_Sach(ActionEvent evt) throws SQLException {
        SachServices s = new SachServices();
        if (this.txtTenSach.getText().isEmpty() || this.txtTacGia.getText().isEmpty() || this.txttgid.getText().isEmpty() || this.txttlid.getText().isEmpty() || this.txtTheLoai.getText().isEmpty() || this.txtSoLuong.getText().isEmpty() || String.valueOf(cbTinhTrang.getValue()).isEmpty()) {
            MessageBox.getBox("Lỗi thêm", "Hãy thêm dữ liệu vào ô trống", Alert.AlertType.ERROR).show();
        } else {
            Sach sv = new Sach(0, this.txtTenSach.getText(), this.txtTacGia.getText(), Integer.parseInt(txttgid.getText()), Integer.parseInt(txttlid.getText()), this.txtTheLoai.getText(), this.txtSoLuong.getText(), String.valueOf(cbTinhTrang.getValue()));
            s.addSach(sv);
            this.loadSach(null);
            MessageBox.getBox("Thành công", "Đã thêm dữ liệu", Alert.AlertType.INFORMATION).show();
        }
    }

    public void delete_ClickSach(ActionEvent event) throws SQLException {
        SachServices s = new SachServices();
        Sach current = tbSach.getSelectionModel().getSelectedItem();
        s.deleteSach(String.valueOf(current.getId()));
        clearinf();
        this.loadSach(null);
    }

    public void upadate_clickSach(ActionEvent evt) throws SQLException {
        SachServices s = new SachServices();
        Sach current = tbSach.getSelectionModel().getSelectedItem();
        if (this.txtTenSach.getText().isEmpty() || this.txttgid.getText().isEmpty() || this.txttlid.getText().isEmpty() || this.txtSoLuong.getText().isEmpty() || String.valueOf(cbTinhTrang.getValue()).isEmpty()) {
            MessageBox.getBox("Lỗi sửa", "Hãy thêm dữ liệu vào ô trống", Alert.AlertType.ERROR).show();
        } else {
            s.updateSach(current.getId(), txtTenSach.getText(), Integer.parseInt(txttgid.getText()), Integer.parseInt(txttlid.getText()), String.valueOf(txtSoLuong.getText()), String.valueOf(cbTinhTrang.getValue()));
            this.loadSach(null);
            MessageBox.getBox("Thành công", "Đã sửa dữ liệu", Alert.AlertType.INFORMATION).show();
        }
        clearinf();
        this.loadSach(null);
    }

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

    public void GoThongKe(ActionEvent evt) throws IOException {
        String nf1 = "thongke1.fxml";
        Parent form1 = FXMLLoader.load(getClass().getResource(nf1));
        Scene formScene = new Scene(form1);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        //formStage.setTitle("Trang Đăng Nhập");
        formStage.show();
        Stage oldStage = (Stage) btThongKe.getScene().getWindow();
        oldStage.close();
    }

    public void addNhanVien(ActionEvent evt) throws SQLException {
        NhanVienService ds = new NhanVienService();
        if (this.txtHoNV.getText().isEmpty() || this.txtTenNV.getText().isEmpty() || this.txtTaiKhoan.getText().isEmpty() || this.pfMatKhau.getText().isEmpty() || this.cbLoaiNhanVien.getValue() != null || this.txtInfor.getText().isEmpty()) {
            int idnv = Integer.parseInt(this.txtidNV.getText());
            String hoNV = this.txtHoNV.getText();
            String tenNV = this.txtTenNV.getText();
            String taiKhoan = this.txtTaiKhoan.getText();
            String matKhau = this.pfMatKhau.getText();
            String id_infor = this.txtInfor.getText();
            String nhanVien = (String) this.cbLoaiNhanVien.getValue();
            boolean loaiNhanVien = true;
            if (nhanVien.equals("Nhân Viên")) {
                loaiNhanVien = false;
            }
            if (nhanVien.equals("Quản Lý")) {
                loaiNhanVien = true;
            }
            NhanVien nv = new NhanVien(idnv, hoNV, tenNV, taiKhoan, matKhau, loaiNhanVien, id_infor);
            ds.addNhanVien(nv);
            this.loaddataNhanVien();
            MessageBox.getBox("Nhân Viên", "Add successful", Alert.AlertType.INFORMATION).show();
        } else {
//            
            MessageBox.getBox("Nhân Viên", "Add successful", Alert.AlertType.INFORMATION).show();
        }
    }

    public void hienThiNhanVien_Click(MouseEvent evt) {
        NhanVien current = tbNhanVien.getSelectionModel().getSelectedItem();
        txtidNV.setText(current.getMaNhanVien() + "");
        txtHoNV.setText(current.getHoNV());
        txtTenNV.setText(current.getTenNV());
        txtTaiKhoan.setText(current.getTaiKhoan());
        pfMatKhau.setText(current.getMatKhau());
        txtInfor.setText(current.getIdinfor());
    }

    public void deleteNV_Click(ActionEvent event) throws SQLException {
        NhanVienService nv = new NhanVienService();
        NhanVien current = tbNhanVien.getSelectionModel().getSelectedItem();
        nv.deleteNhanVien(String.valueOf(current.getMaNhanVien()));
        nv.deleteNhanVien(String.valueOf(current.getMaNhanVien()));
        clearinf();
        this.loaddataNhanVien();
    }

    public void upadateNV_click(ActionEvent evt) throws SQLException {
        if (!this.txtHoNV.getText().isEmpty() && !this.txtTenNV.getText().isEmpty() && cbLoaiNhanVien.getValue() != null && !this.txtTaiKhoan.getText().isEmpty() && !this.pfMatKhau.getText().isEmpty()) {
            int idnv = Integer.parseInt(this.txtidNV.getText());
            String hoNV = this.txtHoNV.getText();
            String tenNV = this.txtTenNV.getText();
            String taiKhoan = this.txtTaiKhoan.getText();
            String matKhau = this.pfMatKhau.getText();
            String id_infor = this.txtInfor.getText();
            String nhanVien = (String) this.cbLoaiNhanVien.getValue();
            boolean loaiNhanVien = true;
            if (nhanVien.equals("Nhân Viên")) {
                loaiNhanVien = false;
            }
             if (nhanVien.equals("Quản Lý")) {
                loaiNhanVien = true;
            }
            NhanVienService nv = new NhanVienService();
            NhanVien n = new NhanVien(idnv, hoNV, tenNV, taiKhoan, matKhau, loaiNhanVien, id_infor);
            nv.updateNhanVien(n);
            this.loaddataNhanVien();
        } else {
            Alert b = MessageBox.getBox("Cập nhật nhân viên", "vui lòng nhập đầy đủ thông tin!!!", Alert.AlertType.WARNING);
            b.show();
        }
    }

    public void clearinf() {
        tfMDG.clear();
        tfMDGName.clear();
        tfMS.clear();
        tfMSName.clear();
        dpNM.setValue(null);
        dpNT.setValue(null);
    }
}
