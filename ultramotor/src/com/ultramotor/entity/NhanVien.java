/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nghipc
 */
public class NhanVien implements Serializable {

//    private static final long serialVersionUID = 1L;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<PhieuXuatKho> phieuXuatKhoList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<ChiTietBaoHanh> chiTietBaoHanhList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<ChiTietBaoDuong> chiTietBaoDuongList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<HoaDon> hoaDonList;
//    @OneToMany(mappedBy = "nhanVien")
//    private List<KhachHang> khachHangList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<DichVu> dichVuList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<PhieuNhapKho> phieuNhapKhoList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanVien")
//    private List<SanPham> sanPhamList;

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

//    public List<PhieuXuatKho> getPhieuXuatKhoList() {
//        return phieuXuatKhoList;
//    }
//
//    public void setPhieuXuatKhoList(List<PhieuXuatKho> phieuXuatKhoList) {
//        this.phieuXuatKhoList = phieuXuatKhoList;
//    }
//
//    public List<ChiTietBaoHanh> getChiTietBaoHanhList() {
//        return chiTietBaoHanhList;
//    }
//
//    public void setChiTietBaoHanhList(List<ChiTietBaoHanh> chiTietBaoHanhList) {
//        this.chiTietBaoHanhList = chiTietBaoHanhList;
//    }
//
//    public List<ChiTietBaoDuong> getChiTietBaoDuongList() {
//        return chiTietBaoDuongList;
//    }
//
//    public void setChiTietBaoDuongList(List<ChiTietBaoDuong> chiTietBaoDuongList) {
//        this.chiTietBaoDuongList = chiTietBaoDuongList;
//    }
//
//    public List<HoaDon> getHoaDonList() {
//        return hoaDonList;
//    }
//
//    public void setHoaDonList(List<HoaDon> hoaDonList) {
//        this.hoaDonList = hoaDonList;
//    }
//
//    public List<KhachHang> getKhachHangList() {
//        return khachHangList;
//    }
//
//    public void setKhachHangList(List<KhachHang> khachHangList) {
//        this.khachHangList = khachHangList;
//    }
//
//    public List<DichVu> getDichVuList() {
//        return dichVuList;
//    }
//
//    public void setDichVuList(List<DichVu> dichVuList) {
//        this.dichVuList = dichVuList;
//    }
//
//    public List<PhieuNhapKho> getPhieuNhapKhoList() {
//        return phieuNhapKhoList;
//    }
//
//    public void setPhieuNhapKhoList(List<PhieuNhapKho> phieuNhapKhoList) {
//        this.phieuNhapKhoList = phieuNhapKhoList;
//    }
//
//    public List<SanPham> getSanPhamList() {
//        return sanPhamList;
//    }
//
//    public void setSanPhamList(List<SanPham> sanPhamList) {
//        this.sanPhamList = sanPhamList;
//    }

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
