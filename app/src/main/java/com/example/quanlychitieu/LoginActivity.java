package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtTenDangNhap;
    private EditText edtMatKhau;
    private TextView tvThongBaoLoi;

    private final String TEN_DANG_NHAP = "admin";
    private final String MAT_KHAU = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        tvThongBaoLoi = findViewById(R.id.tvThongBaoLoi);

        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(v -> dangNhap());
    }

    private void dangNhap() {
        String tenDangNhap =
                edtTenDangNhap.getText().toString().trim();

        String matKhau =
                edtMatKhau.getText().toString();

        if (tenDangNhap.isEmpty() || matKhau.trim().isEmpty()) {
            tvThongBaoLoi.setText(
                    "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu"
            );
            tvThongBaoLoi.setVisibility(View.VISIBLE);
            return;
        }

        if (tenDangNhap.equals(TEN_DANG_NHAP)
                && matKhau.equals(MAT_KHAU)) {

            Intent intent = new Intent(
                    LoginActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();

        } else {
            tvThongBaoLoi.setText(
                    "Sai tên đăng nhập hoặc mật khẩu"
            );
            tvThongBaoLoi.setVisibility(View.VISIBLE);
        }
    }
}