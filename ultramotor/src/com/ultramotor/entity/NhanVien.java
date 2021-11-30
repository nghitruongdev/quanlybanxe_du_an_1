package com.ultramotor.entity;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
//import java.sql.Date;
import java.sql.Types;
import java.util.Date;

public class NhanVien extends Entity {

    private String idNV;
    private String hoNV;
    private String tenNV;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private String sdt;
    private String email;
    private double luong;
    private String hinh;
    private String vaiTro;
    private String matKhau;
    private String ghiChu;

    public NhanVien() {
    }

    public NhanVien(String idNV) {
        this.idNV = idNV;
    }

    public NhanVien(String idNV, String hoNV, String tenNV, Date ngaySinh, boolean gioiTinh, String diaChi, String sdt, String email, double luong, String hinh, String vaiTro, String matKhau, String ghiChu) {
        this.idNV = idNV;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.luong = luong;
        this.hinh = hinh;
        this.vaiTro = vaiTro;
        this.matKhau = matKhau;
        this.ghiChu = ghiChu;
    }

    public static SQLServerDataTable getDataServerTable() throws SQLServerException {
        SQLServerDataTable dataTable = new SQLServerDataTable();
        dataTable.addColumnMetadata("id_NV", Types.NVARCHAR);
        dataTable.addColumnMetadata("HoNV", Types.NVARCHAR);
        dataTable.addColumnMetadata("TenNV", Types.NVARCHAR);
        dataTable.addColumnMetadata("NgaySinh", Types.VARCHAR);
        dataTable.addColumnMetadata("GioiTinh", Types.BIT);
        dataTable.addColumnMetadata("DiaChi", Types.NVARCHAR);
        dataTable.addColumnMetadata("SDT", Types.NVARCHAR);
        dataTable.addColumnMetadata("Email", Types.NVARCHAR);
        dataTable.addColumnMetadata("Luong", Types.FLOAT);
        dataTable.addColumnMetadata("Hinh", Types.NVARCHAR);
        dataTable.addColumnMetadata("VaiTro", Types.NVARCHAR);
        dataTable.addColumnMetadata("matKhau", Types.NVARCHAR);
        dataTable.addColumnMetadata("GhiChu", Types.NVARCHAR);
        return dataTable;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getHoNV() {
        return hoNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getHoTenNV() {
        return String.format("%s %s", hoNV, tenNV);
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNV != null ? idNV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhanVien)) {
            return false;
        }
        NhanVien other = (NhanVien) object;
        if ((this.idNV == null && other.idNV != null) || (this.idNV != null && !this.idNV.equals(other.idNV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.NhanVien[ idNV=" + idNV + " ]";
    }

}
