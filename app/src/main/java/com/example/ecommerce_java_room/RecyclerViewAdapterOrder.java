package com.example.ecommerce_java_room;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterOrder extends RecyclerView.Adapter<RecyclerViewAdapterOrder.ViewHolder> {
    //array lists for all the fields
    private ArrayList<String> orderImages = new ArrayList<>();
    private ArrayList<Integer> orderIds = new ArrayList<>();
    private ArrayList<Integer> orderProductIds = new ArrayList<>();
    private ArrayList<Integer> orderProductQuantity = new ArrayList<>();
    private ArrayList<Double> orderTotalPrice = new ArrayList<>();
    private Context orderContext;


    //constructor
    public RecyclerViewAdapterOrder(Context orderContext, ArrayList<String> orderImages, ArrayList<Integer> orderIds, ArrayList<Integer> orderProductIds,
                                    ArrayList<Integer> orderProductQuantity, ArrayList<Double> orderTotalPrice) {
        this.orderContext = orderContext;
        this.orderImages = orderImages;
        this.orderIds = orderIds;
        this.orderProductIds = orderProductIds;
        this.orderProductQuantity = orderProductQuantity;
        this.orderTotalPrice = orderTotalPrice;
    }

    //implemented methods
    @NonNull
    @Override //this method is responsible for inflating the view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //declaring all the widgets
        CircleImageView orderImage;
        TextView orderId;
        TextView productId;
        TextView orderQuantity;
        TextView totalPrice;
        ImageView cancelOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //getting the elements of the order_layout_list_item.xml
            orderImage = itemView.findViewById(R.id.order_product_image);
            orderId = itemView.findViewById(R.id.order_id);
            productId = itemView.findViewById(R.id.order_product_id);
            orderQuantity = itemView.findViewById(R.id.order_product_quantity);
            totalPrice = itemView.findViewById(R.id.order_total_price);
            cancelOrder = itemView.findViewById(R.id.order_cancel_order);
        }
    }
}
