package com.example.quanlychitieu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.data.ExpenseManager;
import com.example.quanlychitieu.model.Expense;

import java.text.DecimalFormat;

public class ExpenseDetailActivity extends AppCompatActivity {

    private int viTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        TextView tvChiTietTen =
                findViewById(R.id.tvChiTietTen);

        TextView tvChiTietTien =
                findViewById(R.id.tvChiTietTien);

        TextView tvChiTietDanhMuc =
                findViewById(R.id.tvChiTietDanhMuc);

        TextView tvChiTietNgay =
                findViewById(R.id.tvChiTietNgay);

        TextView tvChiTietGhiChu =
                findViewById(R.id.tvChiTietGhiChu);

        Button btnQuayLai = findViewById(R.id.btnQuayLai);
        Button btnXoaKhoanChi = findViewById(R.id.btnXoaKhoanChi);

        viTri = getIntent().getIntExtra("viTri", -1);

        if (viTri < 0
                || viTri >= ExpenseManager.getDanhSach().size()) {
            Toast.makeText(
                    this,
                    "Không tìm thấy khoản chi",
                    Toast.LENGTH_SHORT
            ).show();
            finish();
            return;
        }

        Expense expense =
                ExpenseManager.getDanhSach().get(viTri);

        DecimalFormat dinhDangTien =
                new DecimalFormat("#,##0");

        tvChiTietTen.setText(expense.getTenKhoanChi());

        tvChiTietTien.setText(
                dinhDangTien.format(expense.getSoTien()) + " đ"
        );

        tvChiTietDanhMuc.setText(expense.getDanhMuc());
        tvChiTietNgay.setText(expense.getNgayChi());

        String ghiChu = expense.getGhiChu();

        if (ghiChu == null || ghiChu.isEmpty()) {
            ghiChu = "Không có ghi chú";
        }

        tvChiTietGhiChu.setText(ghiChu);

        btnQuayLai.setOnClickListener(v -> finish());

        btnXoaKhoanChi.setOnClickListener(v -> {
            ExpenseManager.xoaKhoanChi(viTri);

            Toast.makeText(
                    this,
                    "Đã xóa khoản chi",
                    Toast.LENGTH_SHORT
            ).show();

            setResult(RESULT_OK);
            finish();
        });
    }
}