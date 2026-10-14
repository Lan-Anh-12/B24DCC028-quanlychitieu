package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.adapter.ExpenseAdapter;
import com.example.quanlychitieu.data.ExpenseManager;
import com.example.quanlychitieu.model.Expense;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvTongTien;
    private ListView lvDanhSachChiTieu;
    private ExpenseAdapter adapter;
    private ArrayList<Expense> danhSachChiTieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTongTien = findViewById(R.id.tvTongTien);
        lvDanhSachChiTieu =
                findViewById(R.id.lvDanhSachChiTieu);

        Button btnThemKhoanChi =
                findViewById(R.id.btnThemKhoanChi);

        danhSachChiTieu = ExpenseManager.getDanhSach();

        adapter = new ExpenseAdapter(this, danhSachChiTieu);
        lvDanhSachChiTieu.setAdapter(adapter);

        btnThemKhoanChi.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddExpenseActivity.class
            );
            startActivity(intent);
        });

        lvDanhSachChiTieu.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Intent intent = new Intent(
                            MainActivity.this,
                            ExpenseDetailActivity.class
                    );

                    intent.putExtra("viTri", position);
                    startActivity(intent);
                }
        );

        capNhatTongTien();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
            capNhatTongTien();
        }
    }

    private void capNhatTongTien() {
        DecimalFormat dinhDangTien = new DecimalFormat("#,##0");

        tvTongTien.setText(
                dinhDangTien.format(
                        ExpenseManager.tinhTongTien()
                ) + " đ"
        );
    }
}