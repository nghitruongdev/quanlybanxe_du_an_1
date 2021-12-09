/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import com.ultramotor.dao.SanPhamDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PhieuNhapKho extends Entity {

    private String idPN;
    private Date ngayNhap;
    private String idNV;
    private List<ChiTietNhapKho> chiTietNhapKhoList;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String idPN) {
        this.idPN = idPN;
    }

    public PhieuNhapKho(String idPN, Date ngayNhap, String idNV) {
        this.idPN = idPN;
        this.ngayNhap = ngayNhap;
        this.idNV = idNV;
        this.chiTietNhapKhoList = new ArrayList<>();
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public List<ChiTietNhapKho> getChiTietNhapKhoList() {
        return this.chiTietNhapKhoList;
    }

    public void setChiTietNhapKhoList(List<ChiTietNhapKho> chiTietNhapKhoList) {
        this.chiTietNhapKhoList = chiTietNhapKhoList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idPN);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhieuNhapKho other = (PhieuNhapKho) obj;
        if (!Objects.equals(this.idPN, other.idPN)) {
            return false;
        }
        return true;
    }

    public boolean isCanEdit() {
        SanPhamDAO dao = new SanPhamDAO();
        List<String> skus = new ArrayList<>();
        chiTietNhapKhoList.forEach(ct -> skus.add(ct.getSKU()));
        Map<String, Integer> map = dao.checkHangTonSP(skus.toArray(new String[skus.size()]));
        return !chiTietNhapKhoList.stream().anyMatch(ct -> map.get(ct.getSKU()) < ct.getSoLuong());
    }

    @Override
    public String toString() {
        return idPN;
    }

    public double getTongTien() {
        double sum = 0;
        if (chiTietNhapKhoList != null && !chiTietNhapKhoList.isEmpty()) {
            sum = chiTietNhapKhoList.stream().map(ct -> ct.getGiaNhap()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        }
        return sum;
    }
}
