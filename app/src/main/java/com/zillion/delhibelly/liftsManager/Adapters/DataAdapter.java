package com.zillion.delhibelly.liftsManager.Adapters;


import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zillion.delhibelly.liftsManager.ListingActivity;
import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;
import com.zillion.delhibelly.liftsManager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    MainActivity main;
    Fragment fragment;
    private List<Listing> assigned_data;
    private int item_position;
    private int position;
    private int selected_position;
    private String number;
    private ApiError error;
    String time;
    int id;
    Calendar calendar;
    ViewHolder holder;
    private List<Listing> listings = new ArrayList<>();
    private ProgressDialog dialog;
    private ServiceGeneratorMain.UserClient userClient;


    public DataAdapter(List<Listing> data, MainActivity main) {
        notifyDataSetChanged();
        this.assigned_data = data;
        this.main = main;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(com.zillion.delhibelly.liftsManager.R.layout.upcoming_item, parent, false);

        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);


        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        try {
            final ViewHolder h = (ViewHolder) viewHolder;
            h.formId = assigned_data.get(position).getAssocForm().getId();
            h.assignId = assigned_data.get(position).getId();
            h.address.setText(assigned_data.get(position).getAssocForm().getApplicantAddress());
            h.customer_name.setText(assigned_data.get(position).getAssocForm().getContactPersonName());
            h.mobile_no = assigned_data.get(position).getAssocForm().getContactPersonNumber();
            h.customer_pref_date.setText(formatDate(assigned_data.get(position).getCreatedAt()));
            h.call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    telephonyCaller(h);
                }
            });
            h.customer_pref_time.setText(assigned_data.get(position).getScheduledDate());
            h.reschedule.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    openTimePickerDialog(h.assignId, false);
                    rescheduleTime(h, position);
                }
            });
            h.more.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    nextPage(h.formId);
                }

            });
        } catch (NullPointerException e) {
            Toast.makeText(main, "Server Error", Toast.LENGTH_SHORT).show();
        }

    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return assigned_data.size();
    }

    public void nextPage(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("formId", id);
        Intent intent = new Intent(main, ListingActivity.class);
        intent.putExtras(bundle);
        main.startActivity(intent);
    }

    public String formatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMMM d");
        try {

            Date d = sdf.parse(date);
            String formattedDate = sdf2.format(d);
            return formattedDate;
        } catch (ParseException e) {
            return date;
        }
    }

    private void openTimePickerDialog(int id, boolean is24r) {
        this.id = id;
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(
                        main,
                        onTimeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        is24r);

        timePickerDialog.setTitle("Set time");
        timePickerDialog.show();
    }


    TimePickerDialog.OnTimeSetListener onTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // Do something with the time chosen by the user
                    int currentHour;
                    String aMpM = "AM";
                    if (hourOfDay > 11) {
                        aMpM = "PM";
                        currentHour = hourOfDay;

                    }
                    //Make the 24 hour time format to 12 hour time format
                    if (hourOfDay > 11) {
                        currentHour = hourOfDay - 12;
                    } else {
                        currentHour = hourOfDay;
                    }
                    time = currentHour + ":" + minute + aMpM;
                    holder.customer_pref_time.setText(time);
                    setScheduledTime(id, time);
                    assigned_data.get(position).setScheduledDate(time);
                    notifyDataSetChanged();
                }
            };


    public void rescheduleTime(ViewHolder holder, int position) {
        this.holder = holder;
        this.position = position;
        //    main.upcoming_data.add(assigned_data.get(position));
        item_position += 1;
    }

    public void setScheduledTime(int id, String time) {
//        dialog = new ProgressDialog(main);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Loading...");
//        dialog.show();
        Call<Listing> call = userClient.schedule(MainActivity.token, id, time);
        call.enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Response<Listing> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    //    dialog.dismiss();
                } else {
                    error = ErrorUtils.parseError(response, retrofit);
                    Log.d("Error", error.message());
                    //   dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
            }
        });
    }

    public void telephonyCaller(ViewHolder holder) {

        final Intent callIntent = new Intent(Intent.ACTION_CALL);
        number = holder.mobile_no;

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:

                        callIntent.setData(Uri.parse("tel:+91" + number));
                        try {
                            main.startActivity(callIntent);
                        } catch (Exception e) {
                            Toast.makeText(main, R.string.permission_alert, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(main, R.style.dialog);
        builder.setTitle(main.getString(R.string.alert_call_title))
                .setIcon(R.drawable.ic_action_call)
                .setMessage(main.getString(R.string.alert_call_msg1) + " " + number + main.getString(R.string.alert_call_msg2))
                .setPositiveButton(main.getString(R.string.alert_call_yes), dialogClickListener)
                .setNegativeButton(main.getString(R.string.alert_call_no), dialogClickListener).show();


    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView customer_name, address, customer_pref_date, customer_pref_time;
        public ImageButton call, reschedule, more;
        public String mobile_no;
        public int assignId;
        public int formId;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            address = (TextView) itemLayoutView.findViewById(R.id.cust_adr);
            customer_name = (TextView) itemLayoutView.findViewById(R.id.cust_name);
            customer_pref_date = (TextView) itemLayoutView.findViewById(R.id.cust_pref_date);
            customer_pref_time = (TextView) itemLayoutView.findViewById(R.id.cust_pref_time);
            call = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_call_btn);
            reschedule = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_reschd_btn);
            more = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_more_btn);
        }
    }
}