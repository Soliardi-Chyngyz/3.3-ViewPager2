 package com.example.viewpager2.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2.R;

import java.util.List;

public class RecyclerViewPagerAdapter  extends RecyclerView.Adapter<RecyclerViewPagerAdapter.ViewPagerHolder> {

    private List<String> list;

    public RecyclerViewPagerAdapter (List <String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {

        private TextView fragmentName = itemView.findViewById(R.id.vp_tv);

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind (String s){
            fragmentName.setText(s);
        }
    }
}
