package com.ultramotor.entity;

public class ChiTietXuatKho  {

    private int idCtxk;
    private int soLuong;
    private String idPX;
    private String SKU;

    public ChiTietXuatKho() {
    }

    public ChiTietXuatKho(int idCtxk) {
        this.idCtxk = idCtxk;
    }

    public ChiTietXuatKho(int idCtxk, int soLuong, String idPX, String SKU) {
        this.idCtxk = idCtxk;
        this.soLuong = soLuong;
        this.idPX = idPX;
        this.SKU = SKU;
    }

    public int getIdCtxk() {
        return idCtxk;
    }

    public void setIdCtxk(int idCtxk) {
        this.idCtxk = idCtxk;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIdPX() {
        return idPX;
    }

    public void setIdPX(String idPX) {
        this.idPX = idPX;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.ChiTietXuatKho[ idCtxk=" + idCtxk + " ]";
    }
    
}
