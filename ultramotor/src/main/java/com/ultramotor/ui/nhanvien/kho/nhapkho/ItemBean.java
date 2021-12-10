
package com.ultramotor.ui.nhanvien.kho.nhapkho;

import java.util.ArrayList;
import java.util.List;


public class ItemBean {
    private String maSP;
    private List<String> list;

    public ItemBean(String maSP, int soLuong) {
        this.maSP = maSP;
        list = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            list.add(maSP);
        }
    }

    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    
    
}
