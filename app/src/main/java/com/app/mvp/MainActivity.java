package com.app.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.app.Adapter.ProductsAdapter;
import com.app.Extras.ConstantData;
import com.app.Extras.MySingleton;
import com.app.Presenter.MainActPresenter;
import com.app.View.MainActView;
import org.json.JSONArray;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActView
{
    private ArrayList<String> crop_name_List;
    private RecyclerView products_rclv;
    private MainActPresenter mainActPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActPresenter = new MainActPresenter(this);

         /*
         *  Intialization of RecyclerView
         */

        products_rclv = (RecyclerView) findViewById(R.id.rclv_products);

         /*
         *  Intialization of ArrayList
         */

        crop_name_List = new ArrayList<>();

        getData();
    }

    private void getData()
    {
        String crops_Url = ConstantData.service_URL + "crops.json?login="+true+"&language="+"en";
        
        JsonArrayRequest mJsonObjectRequest = new JsonArrayRequest(Request.Method.GET, crops_Url, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                mainActPresenter.setAllData(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {}
        });
        mJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(mJsonObjectRequest);
    }

    @Override
    public void updateData(ArrayList<String> List)
    {
        crop_name_List = List;

        ProductsAdapter productsAdapter = new ProductsAdapter(MainActivity.this, crop_name_List);

        products_rclv.setAdapter(productsAdapter);

        products_rclv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
