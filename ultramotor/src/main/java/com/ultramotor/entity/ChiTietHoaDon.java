package com.ultramotor.entity;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Types;

public class ChiTietHoaDon extends Entity {

    private Integer idCTHD;
    private String idHD;
    private String SKU;
    private double donGia = 0;
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public ChiTietHoaDon(String idHD, String SKU) {
        this(null, idHD, SKU);
    }

    public ChiTietHoaDon(Integer idCTHD, String idHD, String SKU) {
        this.idCTHD = idCTHD;
        this.idHD = idHD;
        this.SKU = SKU;
    }

    public static SQLServerDataTable getDataServerTable() throws SQLServerException {
        SQLServerDataTable dataTable = new SQLServerDataTable();
        dataTable.addColumnMetadata("id_CTHD", Types.INTEGER);
        dataTable.addColumnMetadata("SKU", Types.VARCHAR);
        dataTable.addColumnMetadata("idHD", Types.VARCHAR);
        return dataTable;
    }

    public Integer getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(Integer idCTHD) {
        this.idCTHD = idCTHD;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCTHD != null ? idCTHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietHoaDon)) {
            return false;
        }
        ChiTietHoaDon other = (ChiTietHoaDon) object;
        if ((this.idCTHD == null && other.idCTHD != null) || (this.idCTHD != null && !this.idCTHD.equals(other.idCTHD))) {
            return false;
        }
        return true;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    
    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietHoaDon[ idCTHD=" + idCTHD + " ]";
    }

}
