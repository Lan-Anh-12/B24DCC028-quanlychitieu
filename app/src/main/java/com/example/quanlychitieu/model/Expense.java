package com.example.quanlychitieu.model;

public class Expense {
    private String tenKhoanChi;
    private double soTien;
    private String danhMuc;
    private String ngayChi;
    private String ghiChu;

    public Expense(String tenKhoanChi, double soTien,
                   String danhMuc, String ngayChi, String ghiChu) {
        this.tenKhoanChi = tenKhoanChi;
        this.soTien = soTien;
        this.danhMuc = danhMuc;
        this.ngayChi = ngayChi;
        this.ghiChu = ghiChu;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public double getSoTien() {
        return soTien;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public String getNgayChi() {
        return ngayChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }
}