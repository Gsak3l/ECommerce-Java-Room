package com.example.ecommerce_java_room;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //minimizing the decimal length of a double
    DecimalFormat df = new DecimalFormat();

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mImageTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Integer> mQuantity = new ArrayList<>();
    private ArrayList<Double> mPrice = new ArrayList<>();
    private ArrayList<Integer> mCode = new ArrayList<>();
    private Context mContext;
    private String userType;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImages, ArrayList<String> mImageTitles,
                               ArrayList<Integer> mQuantity, ArrayList<Double> mPrice, ArrayList<Integer> mCode, String userType) {
        this.mContext = mContext;
        this.mImageTitles = mImageTitles;
        this.mImages = mImages;
        this.mQuantity = mQuantity;
        this.mPrice = mPrice;
        this.mCode = mCode;
        this.userType = userType;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //initializing all the elements
        ImageView recyclerViewProductImage;
        TextView recyclerViewProductPrice;
        TextView recyclerViewProductQuantity;
        TextView recyclerViewProductCode;
        RelativeLayout recyclerViewParentLayout;
        TextView recyclerViewProductTitle;

        //getting the xml elements
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
    @Override //not sure what this does
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_admin, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        df.setMinimumFractionDigits(2); //minimum number of decimal digits
        //loading the images
        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.recyclerViewProductImage);
        //giving values to the fields
        holder.recyclerViewProductTitle.setText(mImageTitles.get(position));
        holder.recyclerViewProductQuantity.setText("Pieces Available: " + mQuantity.get(position));
        holder.recyclerViewProductPrice.setText(df.format(mPrice.get(position)) + "$"); //two decimal digits
        holder.recyclerViewProductCode.setText("Product Code: " + mCode.get(position));

        if (userType.equals("user")) { //checking if the used who logged in is an admin or just a user
            //making both product quantity and product code disappear when the user type is used (visible only for admin)
            holder.recyclerViewProductQuantity.setVisibility(View.INVISIBLE);
            holder.recyclerViewProductQuantity.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 0);
            holder.recyclerViewProductCode.setVisibility(View.INVISIBLE);
            holder.recyclerViewProductCode.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 0);
            holder.recyclerViewParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onclick it does stuff
                    Toast.makeText(mContext, mImageTitles.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, UserProductBuy.class);
                    intent.putExtra("id", mCode.get(position));
                    mContext.startActivity(intent);
                }
            });
        } else if (userType.equals("admin")) {

            holder.recyclerViewParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onclick it does stuff
                    Toast.makeText(mContext, mImageTitles.get(position), Toast.LENGTH_SHORT).show();
                }
            });
            //editing or deleting an item
            holder.recyclerViewParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(mContext, AdminAddNewProductActivity.class);
                    intent.putExtra("id", mCode.get(position));
                    mContext.startActivity(intent);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImageTitles.size();
    }

}
