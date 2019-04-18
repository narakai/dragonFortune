package com.jeycorp.dragonFortune.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.jeycorp.dragonFortune.R;

import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTodayFortuneTellingResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;
import com.jeycorp.dragonFortune.util.Typewriter;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import believe.cht.fadeintextview.TextViewListener;

public class RegisterActivity extends AppCompatActivity {
    EditText txtBirthday,txtBirthTime,txtUserName;
    Calendar myCalendar;
    RadioGroup radioGroup;
    DatePickerDialog.OnDateSetListener date;
    private PreferenceManager pref;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;






    CharSequence[] oItems =
            {"모름", "子 (23:30) ~ (01:29)", "丑 (01:30) ~ (03:29)", "寅 (03:30) ~ (05:29)", "卯 (05:30) ~ (07:29)","辰 (07:30) ~ (09:29)",
                    "巳 (09:30) ~ (11:29)","午 (11:30) ~ (13:29)","未 (13:30) ~ (15:29)","申 (15:30) ~ (17:29)","酉 (17:30) ~ (19:29)","戌 (19:30) ~ (21:29)","亥 (21:30) ~ (23:29)"
            };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pref = new PreferenceManager(this);

        mProgressDialog = new ProgressDialog(RegisterActivity.this,R.style.MyAlertDialogStyle);
        mProgressDialog.setMessage("잠시만 기다려주세요");










        //생년월일
        myCalendar = Calendar.getInstance();
        txtBirthday= (EditText) findViewById(R.id.birthday);
        txtBirthTime= (EditText) findViewById(R.id.birthTime);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        radioGroup=findViewById(R.id.gender);
        textIN();

        txtUserName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(txtUserName.getWindowToken(),0);
                    return true;
                }

                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(),0);

            }
        });
//        TextView txtTitle= findViewById(R.id.txtTitle);
        txtBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("txt1","txt1");


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("txt2","txt2");
                final AlertDialog.Builder oDialog = new AlertDialog.Builder(RegisterActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                oDialog.setTitle("태어난 시를 선택해주세요.")
                        .setItems(oItems, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBirthTime.setText(oItems[which]);
                            }
                        })
                        .setCancelable(false)
                        .show();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });



//       fadeintextview.TextView

        LinearLayout contaienr = findViewById(R.id.registerContainer);
        contaienr.setVisibility(View.GONE);

            believe.cht.fadeintextview.TextView txtTitle = (believe.cht.fadeintextview.TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(R.string.registerString);
        txtTitle.isAnimating();
        txtTitle.setLetterDuration(90);



        txtTitle.setListener(new TextViewListener() {
            @Override
            public void onTextStart() {

            }

            @Override
            public void onTextFinish() {
                AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(1000);

                LinearLayout contaienr = findViewById(R.id.registerContainer);
                contaienr.setVisibility(View.VISIBLE);
                contaienr.setAnimation(animation);

            }
        });






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
//        txtBirthday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, R.style.DialogTheme, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH));
//
//                datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
//                datePickerDialog.show();
//            }
//        });


        final EditText et = new EditText(RegisterActivity.this);
        final EditText et2 = new EditText(RegisterActivity.this);
        final EditText et3 = new EditText(RegisterActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_addmember,null);


        txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setView(dialogView);
                builder.setTitle("생년월일을 입력하세요.");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pref.getMUrl())));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);

                final AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#14A2F6"));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#A2A2A2"));
                    }
                });
                dialog.show();

            }
        });



        //태어난시

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
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

//        setText(getString(R.string.registerString));





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



        Log.e("와",""+putScoreParam.getName());
        Log.e("와",""+putScoreParam.getSex());
        Log.e("와",""+putScoreParam.getSolunar());
        Log.e("와",""+putScoreParam.getYear());
        Log.e("와",""+putScoreParam.getMonth());
        Log.e("와",""+putScoreParam.getDay());
        Log.e("와",""+putScoreParam.getHour());
        Log.e("와",""+putScoreParam.getMin());



        VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelper = new VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult>(this);
        setScoreHelper.request(UrlDefine.API_SET_TODAY_FORTUNE_OUTLINE, putScoreParam, GetTodayFortuneTellingResult.class, setScoreHelperListener, false, false, false);

    }

    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            mProgressDialog.show();
            Log.e("리지스터쪽","IntroActivity -> setScoreHelperListener");


            Log.e("리절트코드",""+getTodayFortuneTellingResult.getResultCode());


            if(getTodayFortuneTellingResult.getResultCode()==1){
                Toast.makeText(getApplicationContext(), getTodayFortuneTellingResult.getResultMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();

            }else {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);




                finish();
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



    //달력
    private void updateLabel() {

        String myFormat = "yyyy. MM. dd";
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final CharSequence[] oItems = {" 양력", " 음력"};
        AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
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

    public void textIN(){

        txtBirthday= (EditText) findViewById(R.id.birthday);
        txtBirthTime= (EditText)findViewById(R.id.birthTime);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
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
        Button nextButton = findViewById(R.id.nextButton);
        boolean inputCheck;


        if (name.length() > 1 && birth_date.length() > 1 && birth_time.length() > 1) {
            nextButton.setBackgroundResource(R.drawable.arrow_blue);
            inputCheck = true;
        } else {
            nextButton.setBackgroundResource(R.drawable.arrow);
            inputCheck = false;
        }
    }

//다음화면






    public void nextTab(View view){
        radioGroup=findViewById(R.id.gender);

            if(txtUserName.length()==0 || txtBirthday.length()==0 || txtBirthTime.length()==0 || radioGroup.getCheckedRadioButtonId() == -1){

                Toast.makeText(getApplicationContext(), "모든 사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
            }else {
                if(txtUserName.length()<2){
                    Toast.makeText(getApplicationContext(), "이름을 두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    RadioButton rd = findViewById(radioGroup.getCheckedRadioButtonId());
                    pref.setName(txtUserName.getText().toString());
                    pref.setSex(rd.getText().toString());
                    pref.setYear(txtBirthday.getText().toString().substring(0,4));
                    pref.setMonth(txtBirthday.getText().toString().substring(6,8));
                    pref.setDay(txtBirthday.getText().toString().substring(10,12));

                    Log.e("뭐냐고",""+txtBirthday.getText().toString().substring(13,15));

                    if(txtBirthday.getText().toString().substring(13,15).equals("양력")){
                        pref.setSolunar("solar");
                    }else {
                        pref.setSolunar("lunar");
                    }

                    String time =txtBirthTime.getText().toString();

                    switch (time){
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
                    Log.e("하1",""+pref.getName());
                    Log.e("하2",""+pref.getSex());
                    Log.e("하3",""+pref.getSolunar());
                    Log.e("하4",""+pref.getYear());
                    Log.e("하5",""+pref.getMonth());
                    Log.e("하6",""+pref.getDay());
                    Log.e("하7",""+pref.getHour());
                    Log.e("하8",""+pref.getMin());

                    pref.setOneLine(txtBirthday.getText().toString());
                    pref.setOneLine2(txtBirthTime.getText().toString());


                    setScore();






                }


            }
    }



}



//

























