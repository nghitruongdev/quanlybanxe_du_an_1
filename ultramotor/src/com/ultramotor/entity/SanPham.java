/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nghipc
 */
@Entity
@Table(name = "SanPham")
@NamedQueries({
    @NamedQuery(name = "SanPham.findAll", query = "SELECT s FROM SanPham s")})
public class SanPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SKU")
    private String sku;
    @Basic(optional = false)
    @Column(name = "tenSP")
    private String tenSP;
    @Column(name = "hinh")
    private String hinh;
    @Basic(optional = false)
    @Column(name = "mauSac")
    private String mauSac;
    @Basic(optional = false)
    @Column(name = "phanKhoi")
    private String phanKhoi;
    @Column(name = "thoiGianBH")
    private Integer thoiGianBH;
    @Basic(optional = false)
    @Column(name = "DiaChiSX")
    private String diaChiSX;
    @Basic(optional = false)
    @Column(name = "giaTien")
    private double giaTien;
    @Column(name = "moTa")
    private String moTa;
    @OneToMany(mappedBy = "sanPham")
    private List<ChiTietHoaDon> chiTietHoaDonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sanPham")
    private List<ChiTietXuatKho> chiTietXuatKhoList;
    @JoinColumn(name = "id_Model", referencedColumnName = "id_Model")
    @ManyToOne(optional = false)
    private ModelSanPham modelSanPham;
    @JoinColumn(name = "id_NV", referencedColumnName = "id_NV")
    @ManyToOne(optional = false)
    private NhanVien nhanVien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sanPham")
    private List<ChiTietNhapKho> chiTietNhapKhoList;

    public SanPham() {
    }

    public SanPham(String sku) {
        this.sku = sku;
    }

    public SanPham(String sku, String tenSP, String mauSac, String phanKhoi, String diaChiSX, double giaTien) {
        this.sku = sku;
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.phanKhoi = phanKhoi;
        this.diaChiSX = diaChiSX;
        this.giaTien = giaTien;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getPhanKhoi() {
        return phanKhoi;
    }

    public void setPhanKhoi(String phanKhoi) {
        this.phanKhoi = phanKhoi;
    }

    public Integer getThoiGianBH() {
        return thoiGianBH;
    }

    public void setThoiGianBH(Integer thoiGianBH) {
        this.thoiGianBH = thoiGianBH;
    }

    public String getDiaChiSX() {
        return diaChiSX;
    }

    public void setDiaChiSX(String diaChiSX) {
        this.diaChiSX = diaChiSX;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    public List<ChiTietXuatKho> getChiTietXuatKhoList() {
        return chiTietXuatKhoList;
    }

    public void setChiTietXuatKhoList(List<ChiTietXuatKho> chiTietXuatKhoList) {
        this.chiTietXuatKhoList = chiTietXuatKhoList;
    }

    public ModelSanPham getModelSanPham() {
        return modelSanPham;
    }

    public void setModelSanPham(ModelSanPham modelSanPham) {
        this.modelSanPham = modelSanPham;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<ChiTietNhapKho> getChiTietNhapKhoList() {
        return chiTietNhapKhoList;
    }

    public void setChiTietNhapKhoList(List<ChiTietNhapKho> chiTietNhapKhoList) {
        this.chiTietNhapKhoList = chiTietNhapKhoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sku != null ? sku.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SanPham)) {
            return false;
        }
        SanPham other = (SanPham) object;
        if ((this.sku == null && other.sku != null) || (this.sku != null && !this.sku.equals(other.sku))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.SanPham[ sku=" + sku + " ]";
    }
    
}
