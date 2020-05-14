package com.example.ecommerce_java_room;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce_java_room.data.OrderDAO;
import com.example.ecommerce_java_room.data.ProductDAO;

import java.text.DecimalFormat;
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
    //minimizing the decimal length of a double
    private DecimalFormat df = new DecimalFormat();
    //database stuff
    private OrderDAO orderDAO;
    private ProductDAO productDAO;


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

    @NonNull
    @Override //this method is responsible for inflating the view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(orderContext).asBitmap().load(orderImages.get(position)).into(holder.orderImage); //product image
        holder.orderId.setText(orderIds.get(position) + ""); //order id
        holder.productId.setText(orderProductIds.get(position) + ""); //order product id
        holder.orderQuantity.setText(orderProductQuantity.get(position) + ""); //order quantity
        holder.totalPrice.setText(df.format(orderTotalPrice.get(position)) + "$"); //cancel order button
        holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //canceling the order whenever user clicks cancel
                //making the specific order invisible
                holder.orderImage.setVisibility(View.INVISIBLE);
                holder.orderId.setVisibility(View.INVISIBLE);
                holder.productId.setVisibility(View.INVISIBLE);
                holder.orderQuantity.setVisibility(View.INVISIBLE);
                holder.totalPrice.setVisibility(View.INVISIBLE);
                holder.cancelOrder.setVisibility(View.INVISIBLE);
                //deleting the order from the database and updating the product quantity again
                orderDAO.deleteOrder(orderIds.get(position));
                productDAO.updateQuantity(orderProductIds.get(position), orderProductQuantity.get(position));
                //success message
                Toast.makeText(orderContext, "Order Canceled!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderImages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //array lists for all the fields
        //declaring all the widgets
        CircleImageView orderImage;
        TextView orderId;
        TextView productId;
        TextView orderQuantity;
        TextView totalPrice;
        ImageView cancelOrder;
        private Context orderContext;
        //minimizing the decimal length of a double
        private DecimalFormat df = new DecimalFormat();
        //database stuff
        private OrderDAO orderDAO;
        private ProductDAO productDAO;

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
