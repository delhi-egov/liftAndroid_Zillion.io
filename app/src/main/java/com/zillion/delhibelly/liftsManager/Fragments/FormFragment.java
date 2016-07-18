package com.zillion.delhibelly.liftsManager.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.zillion.delhibelly.liftsManager.ListingActivity;
import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.Form;
import com.zillion.delhibelly.liftsManager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kohli on 12/07/16.
 */
public class FormFragment extends Fragment implements View.OnClickListener {
    ListingActivity main;
    public Button submit;
    RadioGroup ques3;
    RadioGroup ques14;
    EditText ans_one_1;
    EditText ans_one_2;
    EditText ans_two;
    EditText ans_three;
    EditText ans_four;
    EditText ans_five;
    EditText ans_six;
    EditText ans_seven;
    EditText ans_eight;
    EditText ans_nine;
    EditText ans_ten;
    EditText ans_eleven_one;
    EditText ans_eleven_two;
    EditText ans_twelve_one;
    EditText ans_twelve_two;
    EditText ans_thirteen;
    EditText ans_fifteen;


    public FormFragment() {
    }

    public static FormFragment newInstance() {
        return new FormFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.form_ir, container, false);

        main = (ListingActivity) getActivity();

        submit = (Button) v.findViewById(R.id.form_submit);

//        submit.setEnabled(false);


        ques3 = (RadioGroup) v.findViewById(R.id.radioGroup_item3);
        ques14 = (RadioGroup) v.findViewById(R.id.radioGroup_item14);

        ans_one_1 = (EditText) v.findViewById(R.id.form_item1_name);
        ans_one_2 = (EditText) v.findViewById(R.id.form_item1_addr);
        ans_two = (EditText) v.findViewById(R.id.form_item2_addr);
        ans_three = (EditText) v.findViewById(R.id.form_item3_license);
        ans_four = (EditText) v.findViewById(R.id.form_item4_company);
        ans_five = (EditText) v.findViewById(R.id.form_item5_type);
        ans_six = (EditText) v.findViewById(R.id.form_item6_persons);
        ans_seven = (EditText) v.findViewById(R.id.form_item7_opeining);
        ans_eight = (EditText) v.findViewById(R.id.form_item8_opeining);
        ans_nine = (EditText) v.findViewById(R.id.form_item9_clearance);
        ans_ten = (EditText) v.findViewById(R.id.form_item10_clearance);
        ans_eleven_one = (EditText) v.findViewById(R.id.form_item11_w);
        ans_eleven_two = (EditText) v.findViewById(R.id.form_item11_d);
        ans_twelve_one = (EditText) v.findViewById(R.id.form_item12_w);
        ans_twelve_two = (EditText) v.findViewById(R.id.form_item12_d);
        ans_thirteen = (EditText) v.findViewById(R.id.form_item13_observations);
        ans_fifteen = (EditText) v.findViewById(R.id.form_item15_observations);

      //  ans_one_1.addTextChangedListener(new CustomWatcher(ans_one_1,ans_one_2,ans_two,ans_three,ans_four,ans_five,ans_six,ans_seven,ans_eight,ans_nine,ans_ten,ans_eleven_one,ans_eleven_two,ans_twelve_one,ans_twelve_two));
        /*ans_one_2.addTextChangedListener(new CustomWatcher(ans_one_2));
        ans_two.addTextChangedListener(new CustomWatcher(ans_two));
        ans_three.addTextChangedListener(new CustomWatcher(ans_three));
        ans_four.addTextChangedListener(new CustomWatcher(ans_four));
        ans_five.addTextChangedListener(new CustomWatcher(ans_five));
        ans_six.addTextChangedListener(new CustomWatcher(ans_six));
        ans_seven.addTextChangedListener(new CustomWatcher(ans_seven));
        ans_eight.addTextChangedListener(new CustomWatcher(ans_eight));
        ans_nine.addTextChangedListener(new CustomWatcher(ans_nine));
        ans_ten.addTextChangedListener(new CustomWatcher(ans_ten));
        ans_eleven_one.addTextChangedListener(new CustomWatcher(ans_eleven_one));
        ans_eleven_two.addTextChangedListener(new CustomWatcher(ans_eleven_two));
        ans_twelve_one.addTextChangedListener(new CustomWatcher(ans_twelve_one));
        ans_twelve_two.addTextChangedListener(new CustomWatcher(ans_twelve_two));*/

        submit.setOnClickListener(this);
        ques3.setOnClickListener(this);
        ques14.setOnClickListener(this);

        return v;
    }

    public void submitForm() {

        final String ans_one_1 = this.ans_one_1.getText().toString();
        final String ans_one_2 = this.ans_one_2.getText().toString();
        final String ans_two = this.ans_two.getText().toString();
        final String ans_three = this.ans_three.getText().toString();
        final String ans_four = this.ans_four.getText().toString();
        final String ans_five = this.ans_five.getText().toString();
        final int ans_six = this.ans_six.getText().toString().isEmpty()?0:Integer.parseInt(this.ans_six.getText().toString());
        final int ans_seven = this.ans_seven.getText().toString().isEmpty()?0:Integer.parseInt(this.ans_seven.getText().toString());
        final float ans_eight = this.ans_eight.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_eight.getText().toString());
        final float ans_nine = this.ans_nine.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_nine.getText().toString());
        final float ans_ten = this.ans_ten.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_ten.getText().toString());
        final float ans_eleven_one = this.ans_eleven_one.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_eleven_one.getText().toString());
        final float ans_eleven_two = this.ans_eleven_two.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_eleven_two.getText().toString());
        final float ans_twelve_one = this.ans_twelve_one.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_twelve_one.getText().toString());
        final float ans_twelve_two = this.ans_twelve_two.getText().toString().isEmpty()?0:Float.parseFloat(this.ans_twelve_two.getText().toString());
        final String ans_thirteen = this.ans_thirteen.getText().toString();
        final String ans_fifteen = this.ans_fifteen.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final Date date_now = new Date();
        final String date = sdf.format(date_now);

        main.dialog = new ProgressDialog(main);
        main.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        main.dialog.setMessage("Loading...");
        main.dialog.show();
        Call<Form> call = main.userClient.submitForm(MainActivity.token, main.assignId, date, ans_one_1, ans_one_2, ans_two, true, ans_four, ans_five, ans_six, ans_seven, ans_eight, ans_nine, ans_ten, ans_eleven_one, ans_eleven_two, ans_twelve_one, ans_twelve_two, ans_thirteen, "");
        call.enqueue(new Callback<Form>() {
            @Override
            public void onResponse(Response<Form> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    main.dialog.dismiss();
                    main.finish();

                } else {
                    main.error = ErrorUtils.parseError(response, retrofit);
                    Log.d("Error", main.error.message());
                    main.dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                main.dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.form_submit:
                submitForm();
                break;
        }

    }

    public class CustomWatcher implements TextWatcher {

        private EditText view;
        public CustomWatcher(EditText view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
        public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            if (view.getText().toString().length() > 0) {
                submit.setEnabled(true);
            } else {
                submit.setEnabled(false);
            }
        }

        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch(view.getId()){
                /*case R.id.editText1:
                    doStuff(1);
                    break;
                case R.id.editText2:
                    doStuff(2);
                    break;*/
            }
        }
    }


}


