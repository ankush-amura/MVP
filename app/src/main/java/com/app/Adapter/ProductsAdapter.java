package com.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.mvp.R;
import java.util.ArrayList;

/*
 * Created by Yash on 13/10/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>
{
    private ArrayList<String> crop_name_List;
    private LayoutInflater layoutInflater;

    public ProductsAdapter(Context context, ArrayList<String> crop_name_List)
    {
        /*
         * RecyclerViewAdapter Constructor to Initialize Data which we get from RecyclerViewFragment
         **/

        layoutInflater = LayoutInflater.from(context);
        this.crop_name_List = crop_name_List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        /*
         * LayoutInflater is used to Inflate the view
         * from fragment_listview_adapter
         * for showing data in RecyclerView
         **/

        View view = layoutInflater.inflate(R.layout.product_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.MyViewHolder holder, int position)
    {
        /*
         * onBindViewHolder is used to Set all the respective data
         * to Textview or Imagview form worldpopulation_pojoArrayList
         * ArrayList Object.
         **/

        if (!TextUtils.isEmpty(crop_name_List.get(position)))
        {
            String cropName = crop_name_List.get(position).substring(0,1).toUpperCase() +  crop_name_List.get(position).substring(1);

            holder.textview_product.setText(cropName);
        }
    }

    @Override
    public int getItemCount()
    {
        /*
         * getItemCount is used to get the size of respective worldpopulation_pojoArrayList ArrayList
         **/

        return crop_name_List.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textview_product;

        /**
         * MyViewHolder is used to Initializing the view.
         **/

        MyViewHolder(View itemView)
        {
            super(itemView);

            textview_product = itemView.findViewById(R.id.textview_product);


            itemView.setTag(itemView);
        }
    }
}
