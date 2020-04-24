package com.example.ecommerce_java_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mImageTitles, ArrayList<String> mImages, Context mContext) {
        this.mImageTitles = mImageTitles;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerViewProductImage;
        TextView recyclerViewProductPrice;
        TextView recyclerViewProductQuantity;
        TextView recyclerViewProductCode;
        RelativeLayout recyclerViewParentLayout;
        TextView recyclerViewProductTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewProductImage = itemView.findViewById(R.id.recycler_view_product_image);
            recyclerViewProductPrice = itemView.findViewById(R.id.recycler_view_product_price);
            recyclerViewProductQuantity = itemView.findViewById(R.id.recycler_view_product_quantity);
            recyclerViewProductCode = itemView.findViewById(R.id.recycler_view_product_code);
            recyclerViewParentLayout = itemView.findViewById(R.id.recycler_view_parent_layout);
            recyclerViewProductTitle = itemView.findViewById(R.id.recycler_view_product_title);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.recyclerViewProductImage);
        holder.recyclerViewProductTitle.setText(mImageTitles.get(position));
        holder.recyclerViewParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mImageTitles.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageTitles.size();
    }

}
