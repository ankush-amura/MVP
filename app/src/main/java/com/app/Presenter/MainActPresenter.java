package com.app.Presenter;

import com.app.View.MainActView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/*
 * Created by Yash on 13/10/17.
 */

public class MainActPresenter
{
    /**
     * ArrayList : products_arrayList
     */
    private ArrayList<String> crop_name_List;

    /**
     * View : MainActView
     */
    private MainActView mactView;

    /*
     * Instantiates a new MainActivity Presenter.
     *
     * @param mactView
     *     the mactView
     */

    public MainActPresenter(MainActView mactView)
    {
        this.mactView = mactView;
        crop_name_List = new ArrayList<>();
    }

    public void setAllData(JSONArray response)
    {
        try
        {
            if(response.length() >= 1)
            {
                for(int i = 0; i < response.length(); i++)
                {
                    JSONObject jsonObject_crop = response.getJSONObject(i);

                    if(jsonObject_crop.has("name"))
                    {
                        if (!(jsonObject_crop.isNull("name")))
                        {
                            crop_name_List.add(jsonObject_crop.getString("name"));
                        }
                    }
                }
            }

            mactView.updateData(crop_name_List);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
