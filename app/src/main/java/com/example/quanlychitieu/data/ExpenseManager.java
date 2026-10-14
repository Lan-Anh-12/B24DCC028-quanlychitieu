package com.example.quanlychitieu.data;

import com.example.quanlychitieu.model.Expense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ExpenseManager {

    private static final ArrayList<Expense> danhSachChiTieu =
            new ArrayList<>();

    private static boolean daKhoiTao = false;

    public static ArrayList<Expense> getDanhSach() {
        khoiTaoDuLieuMau();
        return danhSachChiTieu;
    }

    public static void khoiTaoDuLieuMau() {
        if (daKhoiTao) {
            return;
        }

        String ngayHienTai = new SimpleDateFormat(
                "dd/MM/yyyy", Locale.getDefault()
        ).format(new Date());

        danhSachChiTieu.add(new Expense(
                "Ăn sáng", 30000, "Ăn uống",
                ngayHienTai, ""
        ));

        danhSachChiTieu.add(new Expense(
                "Đổ xăng", 50000, "Đi lại",
                ngayHienTai, ""
        ));

        danhSachChiTieu.add(new Expense(
                "Mua sách", 120000, "Học tập",
                ngayHienTai, ""
        ));

        daKhoiTao = true;
    }

    public static void themKhoanChi(Expense expense) {
        getDanhSach().add(0, expense);
    }

    public static void xoaKhoanChi(int viTri) {
        if (viTri >= 0 && viTri < getDanhSach().size()) {
            getDanhSach().remove(viTri);
        }
    }

    public static double tinhTongTien() {
        double tong = 0;

        for (Expense expense : getDanhSach()) {
            tong += expense.getSoTien();
        }

        return tong;
    }
}