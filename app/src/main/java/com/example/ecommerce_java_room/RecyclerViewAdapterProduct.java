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

public class RecyclerViewAdapterProduct extends RecyclerView.Adapter<RecyclerViewAdapterProduct.ViewHolder> {

    //minimizing the decimal length of a double
    DecimalFormat df = new DecimalFormat();

    private ArrayList<String> productTitles = new ArrayList<>();
    private ArrayList<String> productImages = new ArrayList<>();
    private ArrayList<Integer> productQuantity = new ArrayList<>();
    private ArrayList<Double> productPrice = new ArrayList<>();
    private ArrayList<Integer> productCode = new ArrayList<>();
    private Context pContext;
    private String userType;
    private int userId;

    public RecyclerViewAdapterProduct(Context pContext, ArrayList<String> productImages, ArrayList<String> productTitles,
                                      ArrayList<Integer> productQuantity, ArrayList<Double> productPrice, ArrayList<Integer> productCode, String userType,
                                      int userId) {
        this.pContext = pContext;
        this.productTitles = productTitles;
        this.productImages = productImages;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productCode = productCode;
        this.userType = userType;
        this.userId = userId;
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
    @Override //this method is responsible for inflating the view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        df.setMinimumFractionDigits(2); //minimum number of decimal digits
        //loading the images
        Glide.with(pContext).asBitmap().load(productImages.get(position)).into(holder.recyclerViewProductImage);
        //giving values to the fields
        holder.recyclerViewProductTitle.setText(productTitles.get(position));
        holder.recyclerViewProductQuantity.setText("Pieces Available: " + productQuantity.get(position));
        holder.recyclerViewProductPrice.setText(df.format(productPrice.get(position)) + "$"); //two decimal digits
        holder.recyclerViewProductCode.setText("Product Code: " + productCode.get(position));

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
                    Toast.makeText(pContext, productTitles.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(pContext, UserProductBuy.class);
                    intent.putExtra("productId", productCode.get(position));
                    intent.putExtra("userId", userId);
                    pContext.startActivity(intent);
                }
            });
        } else if (userType.equals("admin")) {

            holder.recyclerViewParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onclick it does stuff
                    Toast.makeText(pContext, productTitles.get(position), Toast.LENGTH_SHORT).show();
                }
            });
            //editing or deleting an item
            holder.recyclerViewParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(pContext, AdminAddNewProductActivity.class);
                    intent.putExtra("productId", productCode.get(position));
                    intent.putExtra("userId", userId);
                    pContext.startActivity(intent);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productTitles.size();
    }

}
