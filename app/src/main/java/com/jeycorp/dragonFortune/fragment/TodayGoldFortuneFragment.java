package com.jeycorp.dragonFortune.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.activity.CompatibilityActivity;
import com.jeycorp.dragonFortune.activity.ResultActivity;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTodayFortuneTellingResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayGoldFortuneFragment extends Fragment implements View.OnClickListener {
    private View view;
    private PreferenceManager pref;
    private TextView txtUserName,txtUserBio,txtBirth,txtMoonSun,txtScore,txtToday,
            txtTodayFortune,txtLoveFortune,txtWishFortune,txtBusinessFortune,txtGoldFortune;
    private ResultActivity activity;

    public TodayGoldFortuneFragment(ResultActivity activity) {
        this.activity=activity;
        // Required empty public constructor
    }

    public TodayGoldFortuneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_today_gold_fortune,null);
        LinearLayout backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("백키타냐?","탔냐");
                getActivity().onBackPressed();
            }
        });



        pref = new PreferenceManager(getContext());

     ;
        txtGoldFortune=view.findViewById(R.id.txtGoldFortune);

        txtUserName=view.findViewById(R.id.txtUserName);
        txtBirth=view.findViewById(R.id.txtBirth);
        txtMoonSun=view.findViewById(R.id.txtMoonSun);
        txtUserBio=view.findViewById(R.id.txtUserBio);
        txtScore=view.findViewById(R.id.txtScore);
        txtToday=view.findViewById(R.id.txtToday);

        LinearLayout item_today_fortune = view.findViewById(R.id.item_today_fortune);
        item_today_fortune.setOnClickListener(this);

        LinearLayout item_year_fortune = view.findViewById(R.id.item_year_fortune);
        item_year_fortune.setOnClickListener(this);

        LinearLayout item_gold_fortune = view.findViewById(R.id.item_gold_fortune);
        item_gold_fortune.setOnClickListener(this);

        LinearLayout item_love_fortune = view.findViewById(R.id.item_love_fortune);
        item_love_fortune.setOnClickListener(this);

        LinearLayout item_tojung_fortune = view.findViewById(R.id.item_tojung_fortune);
        item_tojung_fortune.setOnClickListener(this);

        LinearLayout item_today_tail_fortune = view.findViewById(R.id.item_today_tail_fortune);
        item_today_tail_fortune.setOnClickListener(this);

        final ScrollView scrollView= view.findViewById(R.id.ScrollView1);
        LinearLayout sharebutton = view.findViewById(R.id.sharebutton);
        activity.scrollView= scrollView;
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickedItem="오늘의 금전운 공유하기 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                activity.share();
            }
        });

        setText();

        ImageView imgTail = view.findViewById(R.id.imgTail);
        int year = Integer.parseInt(pref.getYear());
        int tail = year%12;
        switch (tail){
            case 4:
                imgTail.setImageResource(R.drawable.m12_01);
                break;
            case 5:
                imgTail.setImageResource(R.drawable.m12_02);
                break;
            case 6:
                imgTail.setImageResource(R.drawable.m12_03);
                break;
            case 7:
                imgTail.setImageResource(R.drawable.m12_04);
                break;
            case 8:
                imgTail.setImageResource(R.drawable.m12_05);
                break;
            case 9:
                imgTail.setImageResource(R.drawable.m12_06);
                break;
            case 10:
                imgTail.setImageResource(R.drawable.m12_07);
                break;
            case 11:
                imgTail.setImageResource(R.drawable.m12_08);
                break;
            case 0:
                imgTail.setImageResource(R.drawable.m12_09);
                break;
            case 1:
                imgTail.setImageResource(R.drawable.m12_10);
                break;
            case 2:
                imgTail.setImageResource(R.drawable.m12_11);
                break;
            case 3:
                imgTail.setImageResource(R.drawable.m12_12);
                break;

        }


        return view;
    }




    public void setText() {
        PutScoreParam putScoreParam = new PutScoreParam();
        putScoreParam.setName(pref.getName());
        putScoreParam.setSex(pref.getSex());
        putScoreParam.setSolunar(pref.getSolunar());
        putScoreParam.setYear(pref.getYear());
        putScoreParam.setMonth(pref.getMonth());
        putScoreParam.setDay(pref.getDay());
        putScoreParam.setHour(pref.getHour());
        putScoreParam.setMin(pref.getMin());


        VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult> setTextHelper = new VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult>(getActivity());
        setTextHelper.request(UrlDefine.API_SET_TODAY_FORTUNE_OUTLINE, putScoreParam, GetTodayFortuneTellingResult.class, setTextHelperListener, false, false, false);

    }

    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult> setTextHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            txtUserName.setText(pref.getName());
            if(pref.getSex().equals("남")){
                txtUserBio.setText("男");
            }else {
                txtUserBio.setText("女");
            }

            txtBirth.setText(pref.getYear()+"."+pref.getMonth()+"."+pref.getDay());
            Log.e("뭐야",""+pref.getSolunar());
            if(pref.getSolunar().equals("solar")){
                txtMoonSun.setText("(양력)");
            }else {
                txtMoonSun.setText("(음력)");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
            String str_date = df.format(new Date());


            txtToday.setText(str_date);
            txtScore.setText(getTodayFortuneTellingResult.getLuck()+"0");

            txtGoldFortune.setText(getTodayFortuneTellingResult.getS092());
        }

        @Override
        public void onMessage(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getScoreResult) {

        }

        @Override
        public void onError(PutScoreParam putScoreParam, VolleyError error) {

        }
    };

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = new Intent(getContext(), ResultActivity.class);
        pref.setIsBack(false);

        switch (v.getId()) {
            case R.id.item_today_fortune:
                activity.clickedItem="하단바 오늘의 운세 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                pref.setIsBack(true);
                fragmentTransaction.add(R.id.topContainerMain, new TodayFortuneSelectFragment()).addToBackStack(null).commit();
                break;



            case R.id.item_year_fortune:
                activity.clickedItem="하단바 신년운세 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent3 = new Intent(getContext(), ResultActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent3.putExtra("신년","신년");
                startActivity(intent3);
                break;

            case R.id.item_gold_fortune:
                activity.clickedItem="하단바 재물운 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent4 = new Intent(getContext(), ResultActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent4.putExtra("재물","재물");
                startActivity(intent4);
                break;

            case R.id.item_love_fortune:
                activity.clickedItem="하단바 궁합 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent5 = new Intent(getContext(), CompatibilityActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent5);
                break;




            case R.id.item_tojung_fortune:
                activity.clickedItem="하단바 토정비결 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent2 = new Intent(getContext(), ResultActivity.class);
                intent2.putExtra("토정","토정");
                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                break;

            case R.id.item_today_tail_fortune:
                activity.clickedItem="하단바 띠별운세 클릭";activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                pref.setIsBack(true);
                fragmentTransaction.add(R.id.topContainerMain, new TailFortuneSelectFragment()).addToBackStack(null).commit();
                break;
        }

    }
}
