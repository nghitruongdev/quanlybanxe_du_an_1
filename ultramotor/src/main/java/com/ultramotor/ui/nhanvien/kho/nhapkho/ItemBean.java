
package com.ultramotor.ui.nhanvien.kho.nhapkho;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class ItemBean {
    private String maSP;
    private String tenSP;
    private JRBeanCollectionDataSource source;
    public ItemBean(String maSP, String tenSP, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        List<String> list = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            list.add(maSP);
        }
        source = new JRBeanCollectionDataSource(list);
    }
    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

     public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    
    public JRBeanCollectionDataSource getSource() {
        return source;
    }

    public void setSource(JRBeanCollectionDataSource source) {
        this.source = source;
    }
}
