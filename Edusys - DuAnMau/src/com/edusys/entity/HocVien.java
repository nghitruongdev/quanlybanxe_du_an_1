package com.edusys.entity;

import java.util.Objects;

/**
 *
 * @author nghipc
 */
public class HocVien {

    private Integer maHV;
    private Integer maKH;
    private String maNH;
    private Double diem;

    public HocVien() {
    }

    public HocVien(Integer maKH, String maNH) {
        this(null, maKH, maNH, null);
    }

    
    public HocVien(Integer maHV, Integer maKH, String maNH, Object diem) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
        setDiem(diem);
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Object diem) {
        try{
            if(diem!=null){
                this.diem = Double.valueOf(String.valueOf(diem));
            }
        }catch(NumberFormatException e){
//            System.out.println(e.getMessage());
        }
    }

    public Integer getMaHV() {
        return maHV;
    }

    public void setMaHV(Integer maHV) {
        this.maHV = maHV;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.maHV);
        hash = 53 * hash + Objects.hashCode(this.maKH);
        hash = 53 * hash + Objects.hashCode(this.maNH);
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
        final HocVien other = (HocVien) obj;
        if (!Objects.equals(this.maNH, other.maNH)) {
            return false;
        }
        if (!Objects.equals(this.maHV, other.maHV)) {
            return false;
        }
        if (!Objects.equals(this.maKH, other.maKH)) {
            return false;
        }
        return true;
    }
    
    

}
