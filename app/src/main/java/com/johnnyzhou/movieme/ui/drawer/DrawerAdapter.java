package com.johnnyzhou.movieme.ui.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.johnnyzhou.movieme.R;

//public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
//    private String titles[];
//    private int icons[];
//
//    public DrawerAdapter(String[] titles, int[] icons) {
//        this.titles = titles;
//        this.icons = icons;
//    }
//
//    @Override
//    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_drawer, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, final int position) {
//        holder.imageView.setImageResource(icons[position]);
//        holder.textView.setText(titles[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return titles.length;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
////        @Bind(R.id.drawerRowImageView)
//        ImageView imageView;
////        @Bind(R.id.drawerRowTextView)
//        TextView textView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            itemView.setClickable(true);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            // TODO
////            EventBus.getDefault().post(new DrawerItemClick(getAdapterPosition()));
//        }
//    }
//}