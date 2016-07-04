package com.zillion.delhibelly.liftsManager.Networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class NewOrderScrap {

    ProgressDialog asyncdialog;

    private Fetched delegate;

    private Context context;


    private String phone;
    private String timeslot;
    private String house;
    private String street;
    private String city;

    public NewOrderScrap(Context context, String phone, String timeslot, String house, String street, String city, Fetched delegate) {
        this.phone = phone;
        this.timeslot = timeslot;
        this.house = house;
        this.street = street;
        this.city = city;
        this.context = context;
        this.delegate = delegate;
        asyncdialog = new ProgressDialog(context);
        asyncdialog.setMessage("Sending your Order Request.Please wait.....");
        asyncdialog.setCancelable(false);
        asyncdialog.setCanceledOnTouchOutside(false);
    }

    public void execute() {

        asyncdialog.show();
//        String baseUrlLocal="http://192.169.2.114:8080";
        String url = "http://instaspaces.in/91spring/validateslots.php";
        url = "http://ec2-54-179-137-10.ap-southeast-1.compute.amazonaws.com:8888/api/newOrder";

        /*
        * SAMPLE JSON
        * { "phoneNumber": "8431240688",
            "category": "Scrap",
            "timeSlot": "5 AM to 7 AM",
            "note": "hello how do u do",
            "weight": "5 kg",
            "pickUpAddress":{

                "house":"91Springboard",
                "area":"Sector 18",
                "locality":"Motherson sumi",
                "pincode":"122001",
                "city":"Gurgaon"
                }
            }
        */

        HashMap params = new HashMap();
        params.put("phoneNumber", phone);
        params.put("category", "Scrap");//
        params.put("timeSlot", timeslot);
        params.put("note", "Order Pickup From Mobile App");
        params.put("weight", "5 kgs");
        HashMap internal = new HashMap();
        internal.put("house", house);
        internal.put("area", street);
        internal.put("locality", city);
        internal.put("pincode", "122001");
        internal.put("city", "Gurgaon");//
        params.put("pickUpAddress", internal);

        JSONObject object = new JSONObject(params);
        try {
            Log.i("Seding JSON", object.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            delegate.Response(response.toString());
                            asyncdialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error);
                error.printStackTrace();
                asyncdialog.dismiss();
                delegate.Response(null);
            }

        });


/*        {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };*/
        req.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        Volley.newRequestQueue(context).add(req);
    }

    public interface Fetched {
        void Response(String response);
    }
}
