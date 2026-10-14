package com.example.quanlychitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.model.Expense;

import java.text.DecimalFormat;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense> {

    private final LayoutInflater inflater;

    public ExpenseAdapter(Context context, List<Expense> danhSach) {
        super(context, 0, danhSach);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = inflater.inflate(
                    R.layout.item_expense, parent, false
            );
        }

        Expense expense = getItem(position);

        TextView tvTenKhoanChi =
                view.findViewById(R.id.tvTenKhoanChi);

        TextView tvSoTien =
                view.findViewById(R.id.tvSoTien);

        TextView tvDanhMucNgay =
                view.findViewById(R.id.tvDanhMucNgay);

        if (expense != null) {
            DecimalFormat dinhDangTien =
                    new DecimalFormat("#,##0");

            tvTenKhoanChi.setText(expense.getTenKhoanChi());

            tvSoTien.setText(
                    dinhDangTien.format(expense.getSoTien()) + " đ"
            );

            tvDanhMucNgay.setText(
                    expense.getDanhMuc() + " | " + expense.getNgayChi()
            );
        }

        return view;
    }
}