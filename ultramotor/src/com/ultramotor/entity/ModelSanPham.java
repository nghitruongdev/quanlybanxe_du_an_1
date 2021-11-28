package com.ultramotor.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelSanPham {

    private String id_dongSP;
    private String tenDongSP;
    private String phanKhoi;
    private String tenLH;
    private String tenNSX;
    private String diachiSX;
    private int doiXe;
    private int thoigianBH;
    private double giaTien;
    private int soLuongBan;
    private List<SanPham> sanPhamList;

    public ModelSanPham() {
    }

    public ModelSanPham(String id_dongSP, String tenDongSP, String phanKhoi, String tenLH, String tenNSX, String diachiSX, int doiXe, int thoigianBH, double giaTien, int soLuongBan) {
        this.id_dongSP = id_dongSP;
        this.tenDongSP = tenDongSP;
        this.phanKhoi = phanKhoi;
        this.tenLH = tenLH;
        this.tenNSX = tenNSX;
        this.diachiSX = diachiSX;
        this.doiXe = doiXe;
        this.thoigianBH = thoigianBH;
        this.giaTien = giaTien;
        this.soLuongBan = soLuongBan;
    }

    public String getId_dongSP() {
        return id_dongSP;
    }

    public String getPhanKhoi() {
        return phanKhoi;
    }

    public int getDoiXe() {
        return doiXe;
    }

    public String getTenDongSP() {
        return tenDongSP;
    }

    public void setTenDongSP(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {
        this.tenLH = tenLH;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getDiachiSX() {
        return diachiSX;
    }

    public void setDiachiSX(String diachiSX) {
        this.diachiSX = diachiSX;
    }

    public int getThoigianBH() {
        return thoigianBH;
    }

    public void setThoigianBH(int thoigianBH) {
        this.thoigianBH = thoigianBH;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public void setId_dongSP(String id_dongSP) {
        this.id_dongSP = id_dongSP;
    }

    public void setPhanKhoi(String phanKhoi) {
        this.phanKhoi = phanKhoi;
    }

    public void setDoiXe(int doiXe) {
        this.doiXe = doiXe;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public String getMauSac(){
        Set<String> colors = new HashSet<>();
        for (SanPham sp : sanPhamList) {
            colors.add(sp.getMauSac());
        }
        return colors.toString().replaceAll("[\\[\\]]", "");
    }
    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", tenNSX, tenDongSP, doiXe);
    }

    public String getInfo() {
        return String.format("%s %s %s %s %s", toString(), diachiSX, tenLH, phanKhoi, getMauSac().replaceAll(",", " "));
    }

}
