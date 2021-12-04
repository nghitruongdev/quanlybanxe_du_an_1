package com.ultramotor.entity;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Types;

public class ChiTietNhapKho extends Entity {

    private int idCTNK;
    private String SKU;
    private int soLuong;
    private double giaNhap;
    private String idPN;

    public ChiTietNhapKho(int idCTNK) {
        this.idCTNK = idCTNK;
    }

    public ChiTietNhapKho(int soLuong, double giaNhap, String idPN, String SKU) {
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.idPN = idPN;
        this.SKU = SKU;
    }

    public ChiTietNhapKho(int idCTNK, String SKU, int soLuong, double giaNhap, String idPN) {
        this.idCTNK = idCTNK;
        this.SKU = SKU;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.idPN = idPN;
    }

  public static SQLServerDataTable getDataServerTable() throws SQLServerException {
        SQLServerDataTable dataTable = new SQLServerDataTable();
        dataTable.addColumnMetadata("id_CTNK", Types.INTEGER);
        dataTable.addColumnMetadata("SKU", Types.NVARCHAR);
        dataTable.addColumnMetadata("soLuong", Types.INTEGER);
        dataTable.addColumnMetadata("giaNhap", Types.FLOAT);
        dataTable.addColumnMetadata("id_PN", Types.NVARCHAR);
        return dataTable;
    }

    public int getIdCTNK() {
        return idCTNK;
    }

    public void setIdCTNK(int idCTNK) {
        this.idCTNK = idCTNK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietNhapKho[ idCTNK=" + idCTNK + " ]";
    }

}
