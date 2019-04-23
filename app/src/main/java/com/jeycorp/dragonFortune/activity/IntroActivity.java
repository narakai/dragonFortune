package com.jeycorp.dragonFortune.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.param.BaseParam;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetScoreResult;
import com.jeycorp.dragonFortune.result.GetUpdateCheckResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.util.ArrayList;


public class IntroActivity extends AppCompatActivity {
    private PreferenceManager pref;
    static final int SMS_RECEIVE_PERMISSON = 1;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;
    Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        pref = new PreferenceManager(this);


        mProgressDialog = new ProgressDialog(IntroActivity.this,R.style.MyAlertDialogStyle);
        mProgressDialog.setMessage("잠시만 기다려주세요");

         c=this;

        initView();


    }


    public void initView() {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1500);
        ImageView dragonImage = findViewById(R.id.dragonImageView);
        dragonImage.setAnimation(animation);




        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                mProgressDialog.show();
                getIntroUrl();

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        animation.setAnimationListener(listener);
    }

    public void initPage() {
//
//        Intent intent = new Intent(this, RegisterActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);
//        finish();

        if (pref.getName().equals("")) {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
//            mProgressDialog.show();
            finish();
        } else {
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
//            mProgressDialog.show();
            finish();
            Log.e("뭐지?","?");
        }

    }


    private void getIntroUrl() {
        BaseParam baseParam = new BaseParam();

        Log.e("baseParam.getQue()", "" + baseParam.getQue());
        VolleyJsonHelper<BaseParam, GetUpdateCheckResult> getIntroHelper = new VolleyJsonHelper<BaseParam, GetUpdateCheckResult>(this);
        getIntroHelper.request(UrlDefine.API_CEHCK_UPDATE, baseParam, GetUpdateCheckResult.class, getIntroHelperListener, false, true, true);
    }


    private VolleyJsonHelper.VolleyJsonHelperListener<BaseParam, GetUpdateCheckResult> getIntroHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<BaseParam, GetUpdateCheckResult>() {
        @Override
        public void onSuccess(BaseParam getUserParam, GetUpdateCheckResult getUpdateCheckResult) {
            Log.e("updateUrl", "통신 성공");

            try {
                PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
                int appVersionThis = pInfo.versionCode;
                pref.setAppVersion(appVersionThis);
                Log.e("앱버전3",""+pInfo.versionCode);
                 final String marketUrl = getUpdateCheckResult.getSettings().get(0).getValue();
                 pref.setMUrl(marketUrl);


                int force_updateVersion = Integer.parseInt(getUpdateCheckResult.getSettings().get(1).getValue());
                pref.setUpVersion(force_updateVersion);
                 int pass_updateVersion = Integer.parseInt(getUpdateCheckResult.getSettings().get(2).getValue());
                 Log.e("포스버전",""+force_updateVersion);
                Log.e("앱스토어 주소",""+marketUrl);
                Log.e("루즈버전",""+pass_updateVersion);

              ;
                 if(appVersionThis<force_updateVersion){
                     new AlertDialog.Builder(IntroActivity.this)
                             .setTitle("용한운세")
                             .setCancelable(false)
                             .setMessage("새로운 버전이 출시 되었습니다. 업데이트 하시겠습니까?")
                             .setPositiveButton("네", new DialogInterface.OnClickListener()
                             {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
//                                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/")));
                                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marketUrl)));
                                     finish();
                                 }

                             })
                             .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {


                                     mProgressDialog.show();

                                     initPage();
                                 }
                             })
                             .show();
                 }else{
                     initPage();

                 }
            } catch (PackageManager.NameNotFoundException e) {

            }



        }

        @Override
        public void onMessage(BaseParam getUserParam, GetUpdateCheckResult getUpdateCheckResult) {
            Log.e("updateUrl", "통신 실패");

        }

        @Override
        public void onError(BaseParam getUserParam, VolleyError error) {
            Log.e("updateUrl", "통신 실패");
        }
    };
}
