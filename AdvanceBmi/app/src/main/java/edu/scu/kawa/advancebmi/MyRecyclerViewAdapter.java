package edu.scu.kawa.advancebmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mDateList;
    private ArrayList<String> mBmiList;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> dateList, ArrayList<String> bmiList) {
        this.mContext = context;
        this.mDateList = dateList;
        this.mBmiList = bmiList;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.my_recycler_view_cell, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.dateTextView = view.findViewById(R.id.dateTextView);
        holder.bmiTextView = view.findViewById(R.id.bmiTextView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String name = mDateList.get(position);
        String bmi = mBmiList.get(position);

        holder.dateTextView.setText(name);
        holder.bmiTextView.setText(bmi);
    }

    @Override
    public int getItemCount() {
        return this.mDateList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public TextView bmiTextView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
