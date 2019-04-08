package com.jeycorp.dragonFortune.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.activity.CompatibilityActivity;
import com.jeycorp.dragonFortune.activity.MainActivity;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTodayFortuneTellingResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyModifyFragment extends Fragment {
    EditText txtBirthday,txtBirthTime,txtUserName;
    Calendar myCalendar;
    RadioGroup radioGroup;
    DatePickerDialog.OnDateSetListener date;
    private PreferenceManager pref;
    View view;
    private MainActivity activity;

    CharSequence[] oItems =
            {"모름", "子 (23:30) ~ (01:29)", "丑 (01:30) ~ (03:29)", "寅 (03:30) ~ (05:29)", "卯 (05:30) ~ (07:29)","辰 (07:30) ~ (09:29)",
                    "巳 (09:30) ~ (11:29)","午 (11:30) ~ (13:29)","未 (13:30) ~ (15:29)","申 (15:30) ~ (17:29)","酉 (17:30) ~ (19:29)","戌 (19:30) ~ (21:29)","亥 (21:30) ~ (23:29)"
            };



    public MyModifyFragment() {
        // Required empty public constructor
    }

    public MyModifyFragment(MainActivity activity) {
        this.activity=activity;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        if(activity != null){
            activity.showBackButton();
            activity.hideHam();
        }
        view = inflater.inflate(R.layout.fragment_my_modify, null);
        pref = new PreferenceManager(getContext());

        Log.e("정보수정1",pref.getOneLine());
        Log.e("정보수정2",pref.getOneLine2());





        myCalendar = Calendar.getInstance();
        txtBirthday= (EditText) view.findViewById(R.id.birthday);
        txtBirthTime= (EditText) view.findViewById(R.id.birthTime);
        txtUserName = (EditText)view.findViewById(R.id.txtUserName);
        radioGroup=view.findViewById(R.id.gender);

        txtUserName.setText(pref.getName());
        txtBirthday.setText(pref.getOneLine());
        txtBirthTime.setText(pref.getOneLine2());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(),0);
            }
        });




  txtUserName.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {

          if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
              InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(txtUserName.getWindowToken(),0);
              return true;
          }


          return false;
      }
  });







        if(pref.getSex().equals("남")){
            RadioButton rb_male  = (RadioButton) view.findViewById(R.id.rb_male);
            rb_male.setChecked(true);
        }else {
            RadioButton rb_female  = (RadioButton) view.findViewById(R.id.rb_female);
            rb_female.setChecked(true);
        }

        textIN();


        nextTab();

        //생년월일




        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), R.style.DialogTheme,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //태어난시

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(getContext(),
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        txtBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oDialog.setTitle("태어난 시를 선택해주세요.")
                        .setItems(oItems, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                txtBirthTime.setText(oItems[which]);
                            }
                        })
                        .setCancelable(false)
                        .show();

            }
        });



        return view;
    }

    public void setScore() {
        PutScoreParam putScoreParam = new PutScoreParam();
        putScoreParam.setName(pref.getName());
        putScoreParam.setSex(pref.getSex());
        putScoreParam.setSolunar(pref.getSolunar());
        putScoreParam.setYear(pref.getYear());
        putScoreParam.setMonth(pref.getMonth());
        putScoreParam.setDay(pref.getDay());
        putScoreParam.setHour(pref.getHour());
        putScoreParam.setMin(pref.getMin());
        VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelper = new VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult>(getActivity());
        setScoreHelper.request(UrlDefine.API_SET_TODAY_FORTUNE_OUTLINE, putScoreParam, GetTodayFortuneTellingResult.class, setScoreHelperListener, false, false, false);

    }
    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {


            if(getTodayFortuneTellingResult.getResultCode()==1){

                Toast.makeText(getContext(), getTodayFortuneTellingResult.getResultMessage(), Toast.LENGTH_SHORT).show();


            }else {
                Toast.makeText(getContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(getContext(), MainActivity.class);
////                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
////                startActivity(intent);


//                onDestroyView();
                activity.onBackPressed();




            }






        }

        @Override
        public void onMessage(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            Log.e("kim6","s -> setScoreHelperListener");

        }

        @Override
        public void onError(PutScoreParam putScoreParam, VolleyError error) {
            Log.e("kim7","w -> setScoreHelperListener");
        }
    };




    @Override
    public void onDestroy() {
        if(activity != null){
            activity.hideBackButton();
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            activity.finish();
            startActivity(intent);



        }
        super.onDestroy();
    }

    public void textIN(){

        txtBirthday= (EditText) view.findViewById(R.id.birthday);
        txtBirthTime= (EditText) view.findViewById(R.id.birthTime);
        txtUserName = (EditText)view.findViewById(R.id.txtUserName);


        txtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkForm();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkForm();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtBirthTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkForm();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void checkForm() {
        String name = txtUserName.getText().toString();
        String birth_date = txtBirthday.getText().toString();
        String birth_time = txtBirthTime.getText().toString();
        Button nextButton = view.findViewById(R.id.nextButton);



        boolean inputCheck;


        if (name.length() > 1 && birth_date.length() > 1 && birth_time.length() > 1) {
            nextButton.setBackgroundResource(R.drawable.arrow_blue);
            inputCheck = true;
        } else {
            nextButton.setBackgroundResource(R.drawable.arrow);
            inputCheck = false;
        }
    }

    //달력
    private void updateLabel() {

        String myFormat = "yyyy. MM. dd";
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final CharSequence[] oItems = {" 양력", " 음력"};
        AlertDialog.Builder oDialog = new AlertDialog.Builder(getContext(),
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        oDialog.setTitle("")
                .setItems(oItems, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        txtBirthday.setText(sdf.format(myCalendar.getTime())+oItems[which]);
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void nextTab(){
        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUserName.length() == 0 || txtBirthday.length() == 0 || txtBirthTime.length() == 0 || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "모든 사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtUserName.length() < 2) {
                        Toast.makeText(getContext(), "이름을 두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else {
                        RadioButton rd = view.findViewById(radioGroup.getCheckedRadioButtonId());

                        pref.setName(txtUserName.getText().toString());
                        pref.setSex(rd.getText().toString());
                        pref.setYear(txtBirthday.getText().toString().substring(0, 4));
                        pref.setMonth(txtBirthday.getText().toString().substring(6, 8));
                        pref.setDay(txtBirthday.getText().toString().substring(10, 12));

                        Log.e("뭐냐고", "" + txtBirthday.getText().toString().substring(13, 15));

                        if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                            pref.setSolunar("solar");
                        } else {
                            pref.setSolunar("lunar");
                        }

                        String time = txtBirthTime.getText().toString();
                        switch (time) {
                            case "모름":
                                pref.setHour("00");
                                break;
                            case "子 (23:30) ~ (01:29)":
                                pref.setHour("01");
                                break;
                            case "丑 (01:30) ~ (03:29)":
                                pref.setHour("02");
                                break;
                            case "寅 (03:30) ~ (05:29)":
                                pref.setHour("04");
                                break;
                            case "卯 (05:30) ~ (07:29)":
                                pref.setHour("06");
                                break;
                            case "辰 (07:30) ~ (09:29)":
                                pref.setHour("08");
                                break;
                            case "巳 (09:30) ~ (11:29)":
                                pref.setHour("10");
                                break;
                            case "午 (11:30) ~ (13:29)":
                                pref.setHour("12");
                                break;
                            case "未 (13:30) ~ (15:29)":
                                pref.setHour("14");
                                break;
                            case "申 (15:30) ~ (17:29)":
                                pref.setHour("16");
                                break;
                            case "酉 (17:30) ~ (19:29)":
                                pref.setHour("18");
                                break;
                            case "戌 (19:30) ~ (21:29)":
                                pref.setHour("20");
                                break;
                            case "亥 (21:30) ~ (23:29)":
                                pref.setHour("22");
                                break;
                        }
                        pref.setMin("00");
                        Log.e("하1", "" + pref.getName());
                        Log.e("하2", "" + pref.getSex());
                        Log.e("하3", "" + pref.getSolunar());
                        Log.e("하4", "" + pref.getYear());
                        Log.e("하5", "" + pref.getMonth());
                        Log.e("하6", "" + pref.getDay());
                        Log.e("하7", "" + pref.getHour());
                        Log.e("하8", "" + pref.getMin());

                        pref.setOneLine(txtBirthday.getText().toString());
                        pref.setOneLine2(txtBirthTime.getText().toString());

                        setScore();







                    }


                }

            }
        });
    }





}
