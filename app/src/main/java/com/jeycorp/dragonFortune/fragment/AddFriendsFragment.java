package com.jeycorp.dragonFortune.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jeycorp.dragonFortune.R;

import com.jeycorp.dragonFortune.activity.CompatibilityActivity;
import com.jeycorp.dragonFortune.activity.MainActivity;
import com.jeycorp.dragonFortune.activity.ResultActivity;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;
import com.jeycorp.dragonFortune.util.PreferenceManager6;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendsFragment extends Fragment {
    EditText txtBirthday,txtBirthTime,txtUserName;
    Calendar myCalendar;
    RadioGroup radioGroup;
    DatePickerDialog.OnDateSetListener date;
    private PreferenceManager pref;
    private PreferenceManager2 pref2;
    private PreferenceManager3 pref3;
    private PreferenceManager4 pref4;
    private PreferenceManager5 pref5;
    private PreferenceManager6 pref6;
    View view;
    private MainActivity activity;

    CharSequence[] oItems =
            {"모름", "子 (23:30) ~ (01:29)", "丑 (01:30) ~ (03:29)", "寅 (03:30) ~ (05:29)", "卯 (03:30) ~ (07:29)","辰 (07:30) ~ (09:29)",
                    "巳 (09:30) ~ (11:29)","午 (11:30) ~ (13:29)","未 (13:30) ~ (15:29)","申 (15:30) ~ (17:29)","酉 (17:30) ~ (19:29)","戌 (19:30) ~ (21:29)","亥 (21:30) ~ (23:29)"
            };







    public AddFriendsFragment() {
        // Required empty public constructor
    }


    public AddFriendsFragment(MainActivity activity) {
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
        view = inflater.inflate(R.layout.fragment_add_friends, null);
        pref = new PreferenceManager(getContext());
        pref2 = new PreferenceManager2(getContext());
        pref3 = new PreferenceManager3(getContext());
        pref4 = new PreferenceManager4(getContext());
        pref5 = new PreferenceManager5(getContext());






        if(pref2.getName()!=null){
            Log.e("pref2.getName?",pref2.getName());
        }
        if(pref3.getName()!=null){
            Log.e("pref3.getName?",pref3.getName());
        }
        if(pref4.getName()!=null){
            Log.e("pref4.getName?",pref4.getName());
        }
        if(pref5.getName()!=null){
            Log.e("pref5.getName?",pref5.getName());
        }



;




        textIN();
        nextTab();

        //생년월일
        myCalendar = Calendar.getInstance();
        txtBirthday= (EditText) view.findViewById(R.id.birthday);
        txtBirthTime= (EditText) view.findViewById(R.id.birthTime);
        txtUserName = (EditText)view.findViewById(R.id.txtUserName);
        radioGroup=view.findViewById(R.id.gender);
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
        txtBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("txt1","txt1");


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("txt2","txt2");
                final AlertDialog.Builder oDialog = new AlertDialog.Builder(getContext(),
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                datePickerDialog.show();
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

        Bundle extra = getArguments();
        if( extra !=null) {
            int id = extra.getInt("id");

            switch (id) {
                case 2: {
                    txtUserName.setText(pref2.getName());
                    txtBirthday.setText(pref2.getOneLine());
                    txtBirthTime.setText(pref2.getOneLine2());



                }
            }
        }



if(pref2!=null || pref3!=null || pref4!=null || pref5!=null ){


}




//        if(pref2.getName()!=null || pref3.getName()!=null || pref4.getName()!=null || pref5.getName()!=null ){
//
//            final AlertDialog.Builder namePick = new AlertDialog.Builder(getContext(),
//                    android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
//
//
////            pref3.getName(),pref4.getName(),pref5.getName()
//            final CharSequence[] friends =
//                    {pref2.getName(), pref3.getName(), pref3.getName(),pref4.getName(),pref5.getName()
//                     };
//
//
//            txtUserName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    namePick.setItems(friends, new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//                            txtUserName.setText(friends[which]);
//                        }
//                    })
//                            .setCancelable(false)
//                            .show();
//
//                }
//            });
//
//
//
//        }





        return view;
    }


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
                .setCancelable(true)
                .show();
    }

    public void nextTab(){

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUserName.length()==0 || txtBirthday.length()==0 || txtBirthTime.length()==0 || radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "모든 사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    if(txtUserName.length()<2){
                        Toast.makeText(getContext(), "이름을 두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        RadioButton rd = view.findViewById(radioGroup.getCheckedRadioButtonId());
                        if( pref2.getName()!=null  && pref3.getName()!=null  && pref4.getName()!=null  && pref5.getName()!=null) {
                            Toast.makeText(getContext(), "더이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }

                        Log.e("이게 널이 아니라고?",""+pref2.getName());

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


                            activity.onBackPressed();
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

                                Toast.makeText(getContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
                                activity.onBackPressed();

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


                                    Toast.makeText(getContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
                                    activity.onBackPressed();

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


                                        activity.onBackPressed();

                                    }
                                }

                            }
                        }



                    }


                }







                //수정

              onDestroyView();

              ////
            }
        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
