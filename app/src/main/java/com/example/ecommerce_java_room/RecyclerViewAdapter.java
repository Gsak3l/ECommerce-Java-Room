package com.example.ecommerce_java_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerViewProductImage;
        TextView recyclerViewProductPrice;
        TextView recyclerViewProductQuantity;
        TextView recyclerViewProductCode;
        RelativeLayout recyclerViewParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewProductImage = itemView.findViewById(R.id.recycler_view_product_image);
            recyclerViewProductPrice = itemView.findViewById(R.id.recycler_view_product_price);
            recyclerViewProductQuantity = itemView.findViewById(R.id.recycler_view_product_quantity);
            recyclerViewProductCode = itemView.findViewById(R.id.recycler_view_product_code);
            recyclerViewParentLayout = itemView.findViewById(R.id.recycler_view_parent_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
