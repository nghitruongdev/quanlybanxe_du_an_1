
package com.ultramotor.entity;

import java.io.Serializable;
import java.util.List;

public class NhaSanXuat implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idNSX;
    private String tenNSX;
    private List<DongSanPham> dongSanPhamList;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String idNSX) {
        this.idNSX = idNSX;
    }

    public NhaSanXuat(String idNSX, String tenNSX) {
        this.idNSX = idNSX;
        this.tenNSX = tenNSX;
    }

    public String getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(String idNSX) {
        this.idNSX = idNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public List<DongSanPham> getDongSanPhamList() {
        return dongSanPhamList;
    }

    public void setDongSanPhamList(List<DongSanPham> dongSanPhamList) {
        this.dongSanPhamList = dongSanPhamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNSX != null ? idNSX.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhaSanXuat)) {
            return false;
        }
        NhaSanXuat other = (NhaSanXuat) object;
        if ((this.idNSX == null && other.idNSX != null) || (this.idNSX != null && !this.idNSX.equals(other.idNSX))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultramotor.entity.NhaSanXuat[ idNSX=" + idNSX + " ]";
    }
    
}
