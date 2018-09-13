package com.crumet.khata.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crumet.khata.R;
import com.crumet.khata.database.model.Record;
import com.crumet.khata.obj.Category;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    Context context;
    List<Record> recordList;

    public HomeRecyclerAdapter(Context context, List<Record> recordList) {
        this.context = context;
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder h, int position) {
        Record record = recordList.get(position);
        //h.imageView.setImageResource(record.getImg());

        SimpleDateFormat curFormater = new SimpleDateFormat("MMM dd");
        String showDate = curFormater.format(record.getTimestamp().getTime());
        h.txtCategory.setText(record.getCategory());
        h.txtAmount.setText("Rs. " + record.getAmount());
        h.txtDate.setText(showDate);
        h.txtDesc.setText(record.getNote());

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtCategory, txtDesc, txtAmount, txtDate;

        public HomeViewHolder(@NonNull View v) {
            super(v);
            imageView = v.findViewById(R.id.img_category);
            txtCategory = v.findViewById(R.id.txt_category);
            txtDesc = v.findViewById(R.id.txt_desc);
            txtAmount = v.findViewById(R.id.txt_amount);
            txtDate = v.findViewById(R.id.txt_date);
        }
    }

}
