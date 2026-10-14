package com.example.quanlychitieu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.data.ExpenseManager;
import com.example.quanlychitieu.model.Expense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText edtTenKhoanChi;
    private EditText edtSoTien;
    private EditText edtGhiChu;
    private Spinner spDanhMuc;
    private Button btnChonNgay;

    private final Calendar ngayDuocChon = Calendar.getInstance();

    private final SimpleDateFormat dinhDangNgay =
            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        edtTenKhoanChi = findViewById(R.id.edtTenKhoanChi);
        edtSoTien = findViewById(R.id.edtSoTien);
        edtGhiChu = findViewById(R.id.edtGhiChu);
        spDanhMuc = findViewById(R.id.spDanhMuc);
        btnChonNgay = findViewById(R.id.btnChonNgay);

        Button btnLuuKhoanChi =
                findViewById(R.id.btnLuuKhoanChi);

        String[] danhSachDanhMuc = {
                "Ăn uống", "Đi lại", "Học tập",
                "Giải trí", "Khác"
        };

        ArrayAdapter<String> danhMucAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        danhSachDanhMuc
                );

        danhMucAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spDanhMuc.setAdapter(danhMucAdapter);

        capNhatNutChonNgay();

        btnChonNgay.setOnClickListener(v -> {
            DatePickerDialog hopThoaiNgay = new DatePickerDialog(
                    AddExpenseActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        ngayDuocChon.set(
                                year, month, dayOfMonth
                        );
                        capNhatNutChonNgay();
                    },
                    ngayDuocChon.get(Calendar.YEAR),
                    ngayDuocChon.get(Calendar.MONTH),
                    ngayDuocChon.get(Calendar.DAY_OF_MONTH)
            );

            hopThoaiNgay.show();
        });

        btnLuuKhoanChi.setOnClickListener(v -> luuKhoanChi());
    }

    private void capNhatNutChonNgay() {
        btnChonNgay.setText(
                dinhDangNgay.format(ngayDuocChon.getTime())
        );
    }

    private void luuKhoanChi() {
        String tenKhoanChi =
                edtTenKhoanChi.getText().toString().trim();

        String chuoiSoTien =
                edtSoTien.getText().toString().trim();

        if (tenKhoanChi.isEmpty()) {
            edtTenKhoanChi.setError(
                    "Vui lòng nhập tên khoản chi"
            );
            edtTenKhoanChi.requestFocus();
            return;
        }

        if (chuoiSoTien.isEmpty()) {
            edtSoTien.setError("Vui lòng nhập số tiền");
            edtSoTien.requestFocus();
            return;
        }

        double soTien;

        try {
            soTien = Double.parseDouble(chuoiSoTien);
        } catch (NumberFormatException e) {
            edtSoTien.setError("Số tiền không hợp lệ");
            edtSoTien.requestFocus();
            return;
        }

        if (!Double.isFinite(soTien) || soTien <= 0) {
            edtSoTien.setError(
                    "Số tiền phải lớn hơn 0"
            );
            edtSoTien.requestFocus();
            return;
        }

        String danhMuc = spDanhMuc.getSelectedItem().toString();

        String ngayChi =
                dinhDangNgay.format(ngayDuocChon.getTime());

        String ghiChu =
                edtGhiChu.getText().toString().trim();

        Expense expense = new Expense(
                tenKhoanChi, soTien,
                danhMuc, ngayChi, ghiChu
        );

        ExpenseManager.themKhoanChi(expense);

        setResult(RESULT_OK);
        finish();
    }
}