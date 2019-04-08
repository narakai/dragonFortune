package com.jeycorp.dragonFortune.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.jeycorp.dragonFortune.util.PreferenceManager6;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CompatibilityActivity extends BaseActivity{

    private EditText txtBirthday;
    private EditText txtBirthTime;
    private EditText txtUserName;
    private Calendar myCalendar;
    private RadioGroup radioGroup;
    private DatePickerDialog.OnDateSetListener date;
    private PreferenceManager pref;
    private PreferenceManager2 pref2;
    private PreferenceManager3 pref3;
    private PreferenceManager4 pref4;
    private PreferenceManager5 pref5;
    private PreferenceManager6 pref6;
    View view;
    private MainActivity activity;
    boolean isFocus = true;
    private String isInput = "";
    private ProgressDialog mProgressDialog;


    CharSequence[] oItems =
            {"모름", "子 (23:30) ~ (01:29)", "丑 (01:30) ~ (03:29)", "寅 (03:30) ~ (05:29)", "卯 (03:30) ~ (07:29)", "辰 (07:30) ~ (09:29)",
                    "巳 (09:30) ~ (11:29)", "午 (11:30) ~ (13:29)", "未 (13:30) ~ (15:29)", "申 (15:30) ~ (17:29)", "酉 (17:30) ~ (19:29)", "戌 (19:30) ~ (21:29)", "亥 (21:30) ~ (23:29)"
            };


    @Override
    protected void onResume() {
        checkForm();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compatibility);

        pref = new PreferenceManager(CompatibilityActivity.this);
        pref2 = new PreferenceManager2(CompatibilityActivity.this);
        pref3 = new PreferenceManager3(CompatibilityActivity.this);
        pref4 = new PreferenceManager4(CompatibilityActivity.this);
        pref5 = new PreferenceManager5(CompatibilityActivity.this);
        pref6 = new PreferenceManager6(CompatibilityActivity.this);


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //생년월일
        myCalendar = Calendar.getInstance();
        txtBirthday = (EditText) findViewById(R.id.et_birthday);
        txtBirthTime = (EditText) findViewById(R.id.et_birthTime);
        txtUserName = (EditText) findViewById(R.id.et_txtUserName);
        radioGroup = findViewById(R.id.gender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(),0);

            }
        });

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

        if(pref.getStringCheck().equals("0")){
            Log.e("스트링책",""+pref.getStringCheck());
            txtBirthday.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.e("txt1","txt1");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e("txt2","txt2");
                    final AlertDialog.Builder oDialog = new AlertDialog.Builder(CompatibilityActivity.this,
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
        }









        if (pref2.getName() != null) {
            Log.e("pref2.getName?", pref2.getName());
        }
        if (pref3.getName() != null) {
            Log.e("pref3.getName?", pref3.getName());
        }
        if (pref4.getName() != null) {
            Log.e("pref4.getName?", pref4.getName());
        }
        if (pref5.getName() != null) {
            Log.e("pref5.getName?", pref5.getName());
        }
        if (pref6.getName() != null) {
            Log.e("pref6.getName?", pref6.getName());
        }


        Intent t = getIntent();
        isInput = t.getStringExtra("isInput");

        if(isInput!=null){
            if(isInput.equals("직접입력")){
                Log.e("직접입력 타냐?","1");
                isFocus=false;
                Log.e("isFocus",""+isFocus);
             txtUserName.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {


                 }
             });
            }
        }
            Log.e("isFocus2",""+isFocus);

        if(isFocus){
            if (pref2.getName() != null || pref3.getName() != null || pref4.getName() != null || pref5.getName() != null) {
                txtUserName.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (MotionEvent.ACTION_UP == event.getAction()) {
                            pref.setStringCheck("1");

                            Intent intent = new Intent(CompatibilityActivity.this, PopActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);

                        }
                        return false;
                    }

                });


            }

        }






        if (isInput != null) {
            if (isInput.equals(pref2.getName())) {
                txtUserName.setText(pref2.getName());
                txtBirthday.setText(pref2.getOneLine());
                txtBirthTime.setText(pref2.getOneLine2());
                if (pref2.getSex().equals("남")) {
                    RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
                    rb_male.setChecked(true);
                } else {
                    RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
                    rb_female.setChecked(true);
                }
            }

            if (isInput.equals(pref3.getName())) {
                txtUserName.setText(pref3.getName());
                txtBirthday.setText(pref3.getOneLine());
                txtBirthTime.setText(pref3.getOneLine2());
                if (pref3.getSex().equals("남")) {
                    RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
                    rb_male.setChecked(true);
                } else {
                    RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
                    rb_female.setChecked(true);
                }
            }

            if (isInput.equals(pref4.getName())) {
                txtUserName.setText(pref4.getName());
                txtBirthday.setText(pref4.getOneLine());
                txtBirthTime.setText(pref4.getOneLine2());
                if (pref4.getSex().equals("남")) {
                    RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
                    rb_male.setChecked(true);
                } else {
                    RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
                    rb_female.setChecked(true);
                }
            }

            if (isInput.equals(pref5.getName())) {
                txtUserName.setText(pref5.getName());
                txtBirthday.setText(pref5.getOneLine());
                txtBirthTime.setText(pref5.getOneLine2());
                if (pref5.getSex().equals("남")) {
                    RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
                    rb_male.setChecked(true);
                } else {
                    RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
                    rb_female.setChecked(true);
                }
            }

            if (isInput.equals("직접입력")) {
                pref.setStringCheck("0");
                Log.e("스트링책2",""+pref.getStringCheck());
                txtBirthday.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        Log.e("txt1","txt1");
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.e("txt2","txt2");
                        final AlertDialog.Builder oDialog = new AlertDialog.Builder(CompatibilityActivity.this,
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

                isFocus=false;
            }


        }


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
                new DatePickerDialog(CompatibilityActivity.this, R.style.DialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //태어난시

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(CompatibilityActivity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        txtBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

        textIN();
        nextTab();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void textIN() {
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

    //달력
    private void updateLabel() {
        String myFormat = "yyyy. MM. dd";
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final CharSequence[] oItems = {" 양력", " 음력"};
        AlertDialog.Builder oDialog = new AlertDialog.Builder(CompatibilityActivity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        oDialog.setTitle("")
                .setItems(oItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        txtBirthday.setText(sdf.format(myCalendar.getTime()) + oItems[which]);
                    }
                })
                .setCancelable(true)
                .show();
    }


    public void setScore() {
        PutScoreParam putScoreParam = new PutScoreParam();

        putScoreParam.setName(pref6.getName());
//        putScoreParam.setSex(pref6.getSex());
        putScoreParam.setSolunar(pref6.getSolunar());
        putScoreParam.setYear(pref6.getYear());
        putScoreParam.setMonth(pref6.getMonth());
        putScoreParam.setDay(pref6.getDay());
        putScoreParam.setHour(pref6.getHour());
        putScoreParam.setMin(pref6.getMin());



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
            Log.e("궁합쪽","IntroActivity -> setScoreHelperListener");


            Log.e("리절트코드",""+getTodayFortuneTellingResult.getResultCode());


            if(getTodayFortuneTellingResult.getResultCode()==1){
                Toast.makeText(getApplicationContext(), getTodayFortuneTellingResult.getResultMessage(), Toast.LENGTH_SHORT).show();

            }else {
                Intent intent = new Intent(CompatibilityActivity.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("pref6","pref6");
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


    public void nextTab() {
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RadioButton rd = findViewById(radioGroup.getCheckedRadioButtonId());

                if (txtUserName.length() == 0 || txtBirthday.length() == 0 || txtBirthTime.length() == 0 || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(CompatibilityActivity.this, "모든 사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtUserName.length() < 2) {
                        Toast.makeText(CompatibilityActivity.this, "이름을 두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        pref6.setName(txtUserName.getText().toString());
                        pref6.setSex(rd.getText().toString());
                        pref6.setYear(txtBirthday.getText().toString().substring(0, 4));
                        pref6.setMonth(txtBirthday.getText().toString().substring(6, 8));
                        pref6.setDay(txtBirthday.getText().toString().substring(10, 12));
                        if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                            pref6.setSolunar("solar");
                        } else {
                            pref6.setSolunar("lunar");
                        }
                        String time = txtBirthTime.getText().toString();
                        switch (time) {
                            case "모름":
                                pref6.setHour("00");
                                break;
                            case "子 (23:30) ~ (01:29)":
                                pref6.setHour("01");
                                break;
                            case "丑 (01:30) ~ (03:29)":
                                pref6.setHour("02");
                                break;
                            case "寅 (03:30) ~ (05:29)":
                                pref6.setHour("04");
                                break;
                            case "卯 (05:30) ~ (07:29)":
                                pref6.setHour("06");
                                break;
                            case "辰 (07:30) ~ (09:29)":
                                pref6.setHour("08");
                                break;
                            case "巳 (09:30) ~ (11:29)":
                                pref6.setHour("10");
                                break;
                            case "午 (11:30) ~ (13:29)":
                                pref6.setHour("12");
                                break;
                            case "未 (13:30) ~ (15:29)":
                                pref6.setHour("14");
                                break;
                            case "申 (15:30) ~ (17:29)":
                                pref6.setHour("16");
                                break;
                            case "酉 (17:30) ~ (19:29)":
                                pref6.setHour("18");
                                break;
                            case "戌 (19:30) ~ (21:29)":
                                pref6.setHour("20");
                                break;
                            case "亥 (21:30) ~ (23:29)":
                                pref6.setHour("22");
                                break;
                        }
                        pref6.setMin("00");
                        pref6.setOneLine(txtBirthday.getText().toString());
                        pref6.setOneLine2(txtBirthTime.getText().toString());

                        if(pref2.getName() == null || pref3.getName() == null || pref4.getName() == null || pref5.getName() == null) {
                            if(!txtUserName.getText().toString().equals(pref2.getName())  && !txtUserName.getText().toString().equals(pref3.getName())
                                    && !txtUserName.getText().toString().equals(pref4.getName())
                                    && !txtUserName.getText().toString().equals(pref5.getName())){
                                new AlertDialog.Builder(CompatibilityActivity.this)
                                        .setTitle("사용자를 추가 하겠습니까?")
                                        .setMessage("추가된 사용자는 메뉴에서 수정 및 삭제가 가능합니다.")
                                        .setPositiveButton("추가", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/")));
                                                if (pref2.getName() == null) {
                                                    //2널
                                                    pref2.setName(txtUserName.getText().toString());
                                                    pref2.setSex(rd.getText().toString());
                                                    pref2.setYear(txtBirthday.getText().toString().substring(0, 4));
                                                    pref2.setMonth(txtBirthday.getText().toString().substring(6, 8));
                                                    pref2.setDay(txtBirthday.getText().toString().substring(10, 12));
                                                    if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                                                        pref2.setSolunar("solar");
                                                    } else {
                                                        pref2.setSolunar("lunar");
                                                    }
                                                    String time = txtBirthTime.getText().toString();
                                                    switch (time) {
                                                        case "모름":
                                                            pref2.setHour("00");
                                                            break;
                                                        case "子 (23:30) ~ (01:29)":
                                                            pref2.setHour("01");
                                                            break;
                                                        case "丑 (01:30) ~ (03:29)":
                                                            pref2.setHour("02");
                                                            break;
                                                        case "寅 (03:30) ~ (05:29)":
                                                            pref2.setHour("04");
                                                            break;
                                                        case "卯 (05:30) ~ (07:29)":
                                                            pref2.setHour("06");
                                                            break;
                                                        case "辰 (07:30) ~ (09:29)":
                                                            pref2.setHour("08");
                                                            break;
                                                        case "巳 (09:30) ~ (11:29)":
                                                            pref2.setHour("10");
                                                            break;
                                                        case "午 (11:30) ~ (13:29)":
                                                            pref2.setHour("12");
                                                            break;
                                                        case "未 (13:30) ~ (15:29)":
                                                            pref2.setHour("14");
                                                            break;
                                                        case "申 (15:30) ~ (17:29)":
                                                            pref2.setHour("16");
                                                            break;
                                                        case "酉 (17:30) ~ (19:29)":
                                                            pref2.setHour("18");
                                                            break;
                                                        case "戌 (19:30) ~ (21:29)":
                                                            pref2.setHour("20");
                                                            break;
                                                        case "亥 (21:30) ~ (23:29)":
                                                            pref2.setHour("22");
                                                            break;
                                                    }
                                                    pref2.setMin("00");
                                                    pref2.setOneLine(txtBirthday.getText().toString());
                                                    pref2.setOneLine2(txtBirthTime.getText().toString());
                                                    setScore();


                                                    Toast.makeText(CompatibilityActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    if (pref3.getName() == null) {
                                                        //3널
                                                        pref3.setName(txtUserName.getText().toString());
                                                        pref3.setSex(rd.getText().toString());
                                                        pref3.setYear(txtBirthday.getText().toString().substring(0, 4));
                                                        pref3.setMonth(txtBirthday.getText().toString().substring(6, 8));
                                                        pref3.setDay(txtBirthday.getText().toString().substring(10, 12));
                                                        if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                                                            pref3.setSolunar("solar");
                                                        } else {
                                                            pref3.setSolunar("lunar");
                                                        }
                                                        String time = txtBirthTime.getText().toString();
                                                        switch (time) {
                                                            case "모름":
                                                                pref3.setHour("00");
                                                                break;
                                                            case "子 (23:30) ~ (01:29)":
                                                                pref3.setHour("01");
                                                                break;
                                                            case "丑 (01:30) ~ (03:29)":
                                                                pref3.setHour("02");
                                                                break;
                                                            case "寅 (03:30) ~ (05:29)":
                                                                pref3.setHour("04");
                                                                break;
                                                            case "卯 (05:30) ~ (07:29)":
                                                                pref3.setHour("06");
                                                                break;
                                                            case "辰 (07:30) ~ (09:29)":
                                                                pref3.setHour("08");
                                                                break;
                                                            case "巳 (09:30) ~ (11:29)":
                                                                pref3.setHour("10");
                                                                break;
                                                            case "午 (11:30) ~ (13:29)":
                                                                pref3.setHour("12");
                                                                break;
                                                            case "未 (13:30) ~ (15:29)":
                                                                pref3.setHour("14");
                                                                break;
                                                            case "申 (15:30) ~ (17:29)":
                                                                pref3.setHour("16");
                                                                break;
                                                            case "酉 (17:30) ~ (19:29)":
                                                                pref3.setHour("18");
                                                                break;
                                                            case "戌 (19:30) ~ (21:29)":
                                                                pref3.setHour("20");
                                                                break;
                                                            case "亥 (21:30) ~ (23:29)":
                                                                pref3.setHour("22");
                                                                break;
                                                        }
                                                        pref3.setMin("00");
                                                        pref3.setOneLine(txtBirthday.getText().toString());
                                                        pref3.setOneLine2(txtBirthTime.getText().toString());
                                                        setScore();

                                                        Toast.makeText(CompatibilityActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();


                                                    } else {
                                                        if (pref4.getName() == null) {
                                                            //4널
                                                            pref4.setName(txtUserName.getText().toString());
                                                            pref4.setSex(rd.getText().toString());
                                                            pref4.setYear(txtBirthday.getText().toString().substring(0, 4));
                                                            pref4.setMonth(txtBirthday.getText().toString().substring(6, 8));
                                                            pref4.setDay(txtBirthday.getText().toString().substring(10, 12));
                                                            if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                                                                pref4.setSolunar("solar");
                                                            } else {
                                                                pref4.setSolunar("lunar");
                                                            }
                                                            String time = txtBirthTime.getText().toString();
                                                            switch (time) {
                                                                case "모름":
                                                                    pref4.setHour("00");
                                                                    break;
                                                                case "子 (23:30) ~ (01:29)":
                                                                    pref4.setHour("01");
                                                                    break;
                                                                case "丑 (01:30) ~ (03:29)":
                                                                    pref4.setHour("02");
                                                                    break;
                                                                case "寅 (03:30) ~ (05:29)":
                                                                    pref4.setHour("04");
                                                                    break;
                                                                case "卯 (05:30) ~ (07:29)":
                                                                    pref4.setHour("06");
                                                                    break;
                                                                case "辰 (07:30) ~ (09:29)":
                                                                    pref4.setHour("08");
                                                                    break;
                                                                case "巳 (09:30) ~ (11:29)":
                                                                    pref4.setHour("10");
                                                                    break;
                                                                case "午 (11:30) ~ (13:29)":
                                                                    pref4.setHour("12");
                                                                    break;
                                                                case "未 (13:30) ~ (15:29)":
                                                                    pref4.setHour("14");
                                                                    break;
                                                                case "申 (15:30) ~ (17:29)":
                                                                    pref4.setHour("16");
                                                                    break;
                                                                case "酉 (17:30) ~ (19:29)":
                                                                    pref4.setHour("18");
                                                                    break;
                                                                case "戌 (19:30) ~ (21:29)":
                                                                    pref4.setHour("20");
                                                                    break;
                                                                case "亥 (21:30) ~ (23:29)":
                                                                    pref4.setHour("22");
                                                                    break;
                                                            }
                                                            pref4.setMin("00");
                                                            pref4.setOneLine(txtBirthday.getText().toString());
                                                            pref4.setOneLine2(txtBirthTime.getText().toString());
                                                            setScore();

                                                            Toast.makeText(CompatibilityActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();


                                                        } else {
                                                            if (pref5.getName() == null) {
                                                                //5널
                                                                pref5.setName(txtUserName.getText().toString());
                                                                pref5.setSex(rd.getText().toString());
                                                                pref5.setYear(txtBirthday.getText().toString().substring(0, 4));
                                                                pref5.setMonth(txtBirthday.getText().toString().substring(6, 8));
                                                                pref5.setDay(txtBirthday.getText().toString().substring(10, 12));
                                                                if (txtBirthday.getText().toString().substring(13, 15).equals("양력")) {
                                                                    pref5.setSolunar("solar");
                                                                } else {
                                                                    pref5.setSolunar("lunar");
                                                                }
                                                                String time = txtBirthTime.getText().toString();
                                                                switch (time) {
                                                                    case "모름":
                                                                        pref5.setHour("00");
                                                                        break;
                                                                    case "子 (23:30) ~ (01:29)":
                                                                        pref5.setHour("01");
                                                                        break;
                                                                    case "丑 (01:30) ~ (03:29)":
                                                                        pref5.setHour("02");
                                                                        break;
                                                                    case "寅 (03:30) ~ (05:29)":
                                                                        pref5.setHour("04");
                                                                        break;
                                                                    case "卯 (05:30) ~ (07:29)":
                                                                        pref5.setHour("06");
                                                                        break;
                                                                    case "辰 (07:30) ~ (09:29)":
                                                                        pref5.setHour("08");
                                                                        break;
                                                                    case "巳 (09:30) ~ (11:29)":
                                                                        pref5.setHour("10");
                                                                        break;
                                                                    case "午 (11:30) ~ (13:29)":
                                                                        pref5.setHour("12");
                                                                        break;
                                                                    case "未 (13:30) ~ (15:29)":
                                                                        pref5.setHour("14");
                                                                        break;
                                                                    case "申 (15:30) ~ (17:29)":
                                                                        pref5.setHour("16");
                                                                        break;
                                                                    case "酉 (17:30) ~ (19:29)":
                                                                        pref5.setHour("18");
                                                                        break;
                                                                    case "戌 (19:30) ~ (21:29)":
                                                                        pref5.setHour("20");
                                                                        break;
                                                                    case "亥 (21:30) ~ (23:29)":
                                                                        pref5.setHour("22");
                                                                        break;
                                                                }
                                                                pref5.setMin("00");
                                                                pref5.setOneLine(txtBirthday.getText().toString());
                                                                pref5.setOneLine2(txtBirthTime.getText().toString());
                                                                setScore();

                                                                Toast.makeText(CompatibilityActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();


                                                            }
                                                        }

                                                    }
                                                }


                                            }

                                        })
                                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                setScore();


                                            }
                                        })
                                        .show();

                            }else {

                                setScore();


                            }
                            Log.e("뭐지??",""+pref2.getName());









                        }else {
                            Log.e("이게 널이 아니라고?", "" + pref2.getName());
                            setScore();


                        }


//                        Intent intent = new Intent(CompatibilityActivity.this, ResultActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra("pref6", "pref6");
//                        startActivity(intent);
//                        onDestroyView();


                    }
                }


                ////


            }
        });
    }

}
