package com.jeycorp.dragonFortune.activity;



import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.fragment.CompatibilityOutlineFragment;
import com.jeycorp.dragonFortune.fragment.GoldFortuneOutlineFragment;
import com.jeycorp.dragonFortune.fragment.TailFortuneOutlineFragment;
import com.jeycorp.dragonFortune.fragment.TodayBusinessFortuneFragment;
import com.jeycorp.dragonFortune.fragment.TodayFortuneOutlineFragment;
import com.jeycorp.dragonFortune.fragment.TodayGoldFortuneFragment;
import com.jeycorp.dragonFortune.fragment.TodayLoveFortuneFragment;
import com.jeycorp.dragonFortune.fragment.TojungOutlineFragment;
import com.jeycorp.dragonFortune.fragment.YearFortuneOutlineFragment;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTodayFortuneTellingResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

public class ResultActivity extends BaseActivity implements View.OnClickListener {
    private PreferenceManager pref;
    private TextView up1,up2,up3,up4,down1,down2,down3,down4;
    InterstitialAd interstitialAd;
    boolean isback =false;
    String resultComment;
    ResultActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        pref = new PreferenceManager(this);

        MobileAds.initialize(this, getString(R.string.appID));
        banner();
        initAD();


        activity=this;






        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);



        up1=findViewById(R.id.up1);
        up2=findViewById(R.id.up2);
        up3=findViewById(R.id.up3);
        up4=findViewById(R.id.up4);
        down1=findViewById(R.id.down1);
        down2=findViewById(R.id.down2);
        down3=findViewById(R.id.down3);
        down4=findViewById(R.id.down4);

        initView();
        setText();

    }

    @Override
    protected void onDestroy() {
        //궁합 끝날때만 메인 초기화, 사용자 목록 초기화 안됨.

        super.onDestroy();
    }

    public void initAD(){
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.initAD));
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void banner(){
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
//        adView.setAdSize(AdSize.SMART_BANNER);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded"); //광고가 켜질때
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad"); //광고 로딩이 실패
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened"); //광고 클릭
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication"); //앱이 잠시 멈출때
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed"); // 광고를 닫을때
            }
        });

    }

    public void initView(){
        TextView txtUserName =findViewById(R.id.txtUserName);

        txtUserName.setText(pref.getName());
        final LinearLayout container_confirm = findViewById(R.id.container_confirm);
        container_confirm.setVisibility(View.GONE);

        final ImageView iv = findViewById(R.id.gif);
//        Glide.with(this).load(R.drawable.sample).into(new GlideDrawableImageViewTarget(iv));
        AnimationDrawable ani = (AnimationDrawable)iv.getBackground();
        ani.start();

        final TextView txt_result = findViewById(R.id.txt_result);
        final TextView txt_result2 = findViewById(R.id.txt_result2);


        //코멘트 변경

        Intent intent = getIntent();

        if(intent.getStringExtra("오늘")!=null){
            if(intent.getStringExtra("오늘").equals("오늘")) {
                txt_result.setText("오늘의 운세를\n풀이 중 입니다.");
               resultComment="오늘의 운세";
            }
        }
        if(intent.getStringExtra("오늘애정")!=null){
            if(intent.getStringExtra("오늘애정").equals("오늘애정")) {
                txt_result.setText("오늘의 애정운을\n풀이 중 입니다.");
                resultComment="오늘의 애정운";
            }
        }
        if(intent.getStringExtra("오늘사업")!=null){
            if(intent.getStringExtra("오늘사업").equals("오늘사업")) {
                txt_result.setText("오늘의 사업운을\n풀이 중 입니다.");
                resultComment="오늘의 사업운";
            }
        }
        if(intent.getStringExtra("오늘금전")!=null){
            if(intent.getStringExtra("오늘금전").equals("오늘금전")) {
                txt_result.setText("오늘의 금전운을\n풀이 중 입니다.");
                resultComment="오늘의 금전운";
            }
        }


        if(intent.getStringExtra("신년")!=null){
//                String newYear =intent.getStringExtra("신년");
            if(intent.getStringExtra("신년").equals("신년")) {
                txt_result.setText("2019 신년운세를\n풀이 중 입니다.");
                resultComment="2019 신년운세";


            }
        }

        //토정
        if(intent.getStringExtra("토정")!=null){
            if(intent.getStringExtra("토정").equals("토정")){
                txt_result.setText("2019 토정비결을\n풀이 중 입니다.");
                resultComment="2019 토정비결";
            }
        }
        //재물

        if(intent.getStringExtra("재물")!=null){
            if(intent.getStringExtra("재물").equals("재물")) {
                txt_result.setText("재물운을\n풀이 중 입니다.");
                resultComment="재물운";
            }
       }

        //궁합

        if(intent.getStringExtra("pref6")!=null){
            if(intent.getStringExtra("pref6").equals("pref6")) {
                txt_result.setText("궁합을\n풀이 중 입니다.");
                resultComment="궁합";
            }
        }

        if(intent.getStringExtra("띠별운세")!=null){
            if(intent.getStringExtra("띠별운세").equals("띠별운세")) {
                txt_result.setText("오늘의 띠별운세를\n풀이 중 입니다.");
                resultComment="오늘의 띠별운세";
            }
        }










         new Handler().postDelayed(new Runnable(){
            @Override

            public void run(){
                container_confirm.setVisibility(View.VISIBLE);
                txt_result.setText(resultComment+" 풀이가\n완료되었습니다.");
                txt_result2.setText("아래, 확인하기 버튼을 터치하세요.");
                iv.setVisibility(View.GONE);
            }

        }, 2000);

    }
    @Override
    public void onBackPressed() {
        if(pref.getIsBack()){

            super.onBackPressed();

            Intent intent = getIntent();
            intent.getStringExtra("pref6");
            Log.e("kang","ResultActivity -> onDestroy "+  intent.getExtras());
            if( intent.getStringExtra("pref6")!=null){
                if(intent.getStringExtra("pref6").equals("pref6")){
                    pref.setRefresh("1");
                }
            }

        }else{

        }

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


        VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult> setTextHelper = new VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult>(this);
        setTextHelper.request(UrlDefine.API_SET_TODAY_FORTUNE_OUTLINE, putScoreParam, GetTodayFortuneTellingResult.class, setTextHelperListener, false, false, false);

    }

    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult> setTextHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            up1.setText(getTodayFortuneTellingResult.getUp1());
            up2.setText(getTodayFortuneTellingResult.getUp2());
            up3.setText(getTodayFortuneTellingResult.getUp3());
            up4.setText(getTodayFortuneTellingResult.getUp4());
            down1.setText(getTodayFortuneTellingResult.getDown1());
            down2.setText(getTodayFortuneTellingResult.getDown2());
            down3.setText(getTodayFortuneTellingResult.getDown3());
            down4.setText(getTodayFortuneTellingResult.getDown4());



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
        switch(v.getId()){


            case R.id.confirmButton:
                LinearLayout container_confirm = findViewById(R.id.container_confirm);
                container_confirm.setVisibility(View.GONE);



                Intent intent = getIntent();
                pref.setIsBack(true);


            //오늘의 운세 선택지f
                if(intent.getStringExtra("오늘")!=null){
                  if(intent.getStringExtra("오늘").equals("오늘")) {
                      clickedItem = "운세총론 확인 클릭";clickable_item="clickable_item";
                      track_item_clicked();
                       TodayFortuneOutlineFragment tfof = new TodayFortuneOutlineFragment(activity);
                       getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfof).commit();
                    }
                }
                if(intent.getStringExtra("오늘애정")!=null){

                    if(intent.getStringExtra("오늘애정").equals("오늘애정")) {
                        clickedItem = "애정운 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TodayLoveFortuneFragment tfof = new TodayLoveFortuneFragment(activity);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfof).commit();
                    }
                }
                if(intent.getStringExtra("오늘사업")!=null){
                    if(intent.getStringExtra("오늘사업").equals("오늘사업")) {
                        clickedItem = "사업운 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TodayBusinessFortuneFragment tfof = new TodayBusinessFortuneFragment(activity);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfof).commit();
                    }
                }
                if(intent.getStringExtra("오늘금전")!=null){
                    if(intent.getStringExtra("오늘금전").equals("오늘금전")) {
                        clickedItem = "금전운 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TodayGoldFortuneFragment tfof = new TodayGoldFortuneFragment(activity);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfof).commit();
                    }
                }


            if(intent.getStringExtra("신년")!=null){


                if(intent.getStringExtra("신년").equals("신년")) {
                    clickedItem = "신년운 확인 클릭";clickable_item="clickable_item";
                    track_item_clicked();
                    YearFortuneOutlineFragment yof = new YearFortuneOutlineFragment(activity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, yof).commit();


                }
            }

                //토정
                if(intent.getStringExtra("토정")!=null){

                    if(intent.getStringExtra("토정").equals("토정")){
                        clickedItem = "토정 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TojungOutlineFragment tof = new TojungOutlineFragment(activity);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain,tof).commit();
                    }
                }
                //재물

                if(intent.getStringExtra("재물")!=null){

                    if(intent.getStringExtra("재물").equals("재물")) {
                        clickedItem = "재물운 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        GoldFortuneOutlineFragment gof = new GoldFortuneOutlineFragment(activity);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, gof).commit();
                    }
                }

                //궁합

                if(intent.getStringExtra("pref6")!=null){
                    if(intent.getStringExtra("pref6").equals("pref6")) {
                        clickedItem = "궁합 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        CompatibilityOutlineFragment tfo = new CompatibilityOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 6);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }

                //여기서부터 띠별
                if(intent.getStringExtra("쥐띠")!=null){

                    if(intent.getStringExtra("쥐띠").equals("쥐띠")) {
                        clickedItem = "쥐띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 1);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }
                if(intent.getStringExtra("소띠")!=null){
                    if(intent.getStringExtra("소띠").equals("소띠")) {
                        clickedItem = "소띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 2);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }

                if(intent.getStringExtra("호랑이띠")!=null){
                    if(intent.getStringExtra("호랑이띠").equals("호랑이띠")) {
                        clickedItem = "호랑이띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();
                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 3);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }
                if(intent.getStringExtra("토끼띠")!=null){
                    if(intent.getStringExtra("토끼띠").equals("토끼띠")) {
                        clickedItem = "토끼띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();

                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 4);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }
                if(intent.getStringExtra("용띠")!=null){
                    if(intent.getStringExtra("용띠").equals("용띠")) {

                        clickedItem = "용띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();

                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 5);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }
                if(intent.getStringExtra("뱀띠")!=null){
                    if(intent.getStringExtra("뱀띠").equals("뱀띠")) {

                        clickedItem = "뱀띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();

                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 6);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }  if(intent.getStringExtra("말띠")!=null){
                if(intent.getStringExtra("말띠").equals("말띠")) {

                    clickedItem = "말띠 확인 클릭";clickable_item="clickable_item";
                    track_item_clicked();

                    TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 7);
                    tfo.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                }
            }  if(intent.getStringExtra("양띠")!=null){
                if(intent.getStringExtra("양띠").equals("양띠")) {

                    clickedItem = "양띠 확인 클릭";clickable_item="clickable_item";
                    track_item_clicked();

                    TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 8);
                    tfo.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                }
            }  if(intent.getStringExtra("원숭이띠")!=null){
                if(intent.getStringExtra("원숭이띠").equals("원숭이띠")) {

                    clickedItem = "원숭이띠 확인 클릭";clickable_item="clickable_item";
                    track_item_clicked();

                    TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 9);
                    tfo.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                }
            }
                if(intent.getStringExtra("닭띠")!=null){
                    if(intent.getStringExtra("닭띠").equals("닭띠")) {

                        clickedItem = "닭띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();

                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 10);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }  if(intent.getStringExtra("개띠")!=null){
                if(intent.getStringExtra("개띠").equals("개띠")) {

                    clickedItem = "개띠 확인 클릭";clickable_item="clickable_item";
                    track_item_clicked();

                    TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 11);
                    tfo.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                }
            }
                if(intent.getStringExtra("돼지띠")!=null){
                    if(intent.getStringExtra("돼지띠").equals("돼지띠")) {

                        clickedItem = "돼지띠 확인 클릭";clickable_item="clickable_item";
                        track_item_clicked();

                        TailFortuneOutlineFragment tfo = new TailFortuneOutlineFragment(activity);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", 12);
                        tfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.topContainerMain, tfo).commit();
                    }
                }

//

                interstitialAd.show();
        }



    }
}
