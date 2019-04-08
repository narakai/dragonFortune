package com.jeycorp.dragonFortune.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.activity.BaseActivity;
import com.jeycorp.dragonFortune.activity.MainActivity;
import com.jeycorp.dragonFortune.activity.RegisterActivity;
import com.jeycorp.dragonFortune.activity.ResultActivity;
import com.jeycorp.dragonFortune.util.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFortuneSelectFragment extends Fragment  implements View.OnClickListener {
    private View view;
    private MainActivity activity;
    ResultActivity resultActivity;
    private FirebaseAnalytics mFirebaseAnalytics;
    public String clickedItem;

    PreferenceManager pref;



    public TodayFortuneSelectFragment() {
        // Required empty public constructor
    }

    public TodayFortuneSelectFragment(MainActivity activity)
    {
        this.activity = activity;
    }

    public TodayFortuneSelectFragment(ResultActivity resultActivity)
    {
        this.resultActivity = resultActivity;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("kim","TodayFortuneSelectFragment - > onCreateView");

        if(activity != null){

            activity.showBackButton();
        }




        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_today_fortune_select,null);
        pref = new PreferenceManager(getContext());

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());


        LinearLayout item_today_fortune_outline =view.findViewById(R.id.item_today_fortune_outline);
        item_today_fortune_outline.setOnClickListener(this);

        LinearLayout item_today_love_fortune =view.findViewById(R.id.item_today_love_fortune);
        item_today_love_fortune.setOnClickListener(this);

        LinearLayout item_business_fortune =view.findViewById(R.id.item_business_fortune);
        item_business_fortune.setOnClickListener(this);

        LinearLayout item_gold_fortune =view.findViewById(R.id.item_gold_fortune);
        item_gold_fortune.setOnClickListener(this);

        TextView imgMonth =view.findViewById(R.id.imgMonth);
        SimpleDateFormat df = new SimpleDateFormat("M", Locale.KOREA);
        String str_date = df.format(new Date());

        imgMonth.setText(str_date);


        return view;
    }

    public void track_item_clicked(){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, clickedItem);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

//        Toast.makeText(getContext(), "clickedItem에 "+ "\""+ clickedItem + "\""+ " 1회 증가", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        if(activity != null){
            activity.hideBackButton();
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = new Intent(getContext(), ResultActivity.class);
        pref.setIsBack(false);
        switch(v.getId()){
            case R.id.item_today_fortune_outline: {
                clickedItem="오늘의 운세총론 클릭";
                track_item_clicked();
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("오늘", "오늘");
                startActivity(intent);
                track_item_clicked();
            }
                break;
            case R.id.item_today_love_fortune: {
                clickedItem="오늘의 애정운 클릭";
                track_item_clicked();
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("오늘애정", "오늘애정");
                startActivity(intent);
            }
                break;

            case R.id.item_business_fortune: {
                clickedItem="오늘의 사업운 클릭";
                track_item_clicked();
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("오늘사업", "오늘사업");
                startActivity(intent);
            }
                break;
            case R.id.item_gold_fortune: {
                clickedItem="오늘의 금전운 클릭";
                track_item_clicked();
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("오늘금전", "오늘금전");
                startActivity(intent);
            }
                break;


        }

    }
}
