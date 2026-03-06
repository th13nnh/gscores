package com.goldenowl.gscores.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id // SBD is the primary key
    private String sbd;

    private Double toan;
    private Double nguVan;
    private Double ngoaiNgu;
    private Double vatLi;
    private Double hoaHoc;
    private Double sinhHoc;
    private Double lichSu;
    private Double diaLi;
    private Double gdcd;
    private String maNgoaiNgu;

    // --- Getters and Setters ---

    public String getSbd() { return sbd; }
    public void setSbd(String sbd) { this.sbd = sbd; }

    public Double getToan() { return toan; }
    public void setToan(Double toan) { this.toan = toan; }

    public Double getNguVan() { return nguVan; }
    public void setNguVan(Double nguVan) { this.nguVan = nguVan; }

    public Double getNgoaiNgu() { return ngoaiNgu; }
    public void setNgoaiNgu(Double ngoaiNgu) { this.ngoaiNgu = ngoaiNgu; }

    public Double getVatLi() { return vatLi; }
    public void setVatLi(Double vatLi) { this.vatLi = vatLi; }

    public Double getHoaHoc() { return hoaHoc; }
    public void setHoaHoc(Double hoaHoc) { this.hoaHoc = hoaHoc; }

    public Double getSinhHoc() { return sinhHoc; }
    public void setSinhHoc(Double sinhHoc) { this.sinhHoc = sinhHoc; }

    public Double getLichSu() { return lichSu; }
    public void setLichSu(Double lichSu) { this.lichSu = lichSu; }

    public Double getDiaLi() { return diaLi; }
    public void setDiaLi(Double diaLi) { this.diaLi = diaLi; }

    public Double getGdcd() { return gdcd; }
    public void setGdcd(Double gdcd) { this.gdcd = gdcd; }

    public String getMaNgoaiNgu() { return maNgoaiNgu; }
    public void setMaNgoaiNgu(String maNgoaiNgu) { this.maNgoaiNgu = maNgoaiNgu; }
}