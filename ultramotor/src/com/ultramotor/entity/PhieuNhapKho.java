/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.entity;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PhieuNhapKho extends Entity{

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

     public static SQLServerDataTable getDataServerTable() throws SQLServerException {
        SQLServerDataTable dataTable = new SQLServerDataTable();
        dataTable.addColumnMetadata("id_PN", Types.NVARCHAR);
        dataTable.addColumnMetadata("ngayNhap", Types.DATE);
        dataTable.addColumnMetadata("id_NV", Types.NVARCHAR);
        return dataTable;
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

    

    @Override
    public String toString() {
        return "com.ultramotor.entity.PhieuNhapKho[ idPN=" + idPN + " ]";
    }

    public double getTongTien() {
        double sum = 0;
        if (chiTietNhapKhoList != null && !chiTietNhapKhoList.isEmpty()) {
            sum = chiTietNhapKhoList.stream().map(ct -> ct.getGiaNhap()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        }
        return sum;
    }
}
