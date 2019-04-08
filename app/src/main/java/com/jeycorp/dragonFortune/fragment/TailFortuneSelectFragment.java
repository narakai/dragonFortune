package com.jeycorp.dragonFortune.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.activity.MainActivity;
import com.jeycorp.dragonFortune.activity.ResultActivity;
import com.jeycorp.dragonFortune.util.PreferenceManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TailFortuneSelectFragment extends Fragment implements View.OnClickListener {
    private View view;
    private PreferenceManager pref;
    private MainActivity activity;
    private FirebaseAnalytics mFirebaseAnalytics;
    public String clickedItem;
    public String clickable_item;


    public TailFortuneSelectFragment() {


        // Required empty public constructor
    }

    public TailFortuneSelectFragment(MainActivity activity) {
        this.activity = activity;


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (activity != null) {
            activity.showBackButton();
        }
        view = inflater.inflate(R.layout.fragment_tail_fortune_select, null);
        // Inflate the layout for this fragment

        pref = new PreferenceManager(getContext());

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());


        LinearLayout mouse = view.findViewById(R.id.mouse);
        mouse.setOnClickListener(this);

        LinearLayout cow = view.findViewById(R.id.cow);
        cow.setOnClickListener(this);

        LinearLayout tiger = view.findViewById(R.id.tiger);
        tiger.setOnClickListener(this);

        LinearLayout rabbit = view.findViewById(R.id.rabbit);
        rabbit.setOnClickListener(this);

        LinearLayout dragon = view.findViewById(R.id.dragon);
        dragon.setOnClickListener(this);

        LinearLayout snake = view.findViewById(R.id.snake);
        snake.setOnClickListener(this);

        LinearLayout horse = view.findViewById(R.id.horse);
        horse.setOnClickListener(this);

        LinearLayout sheep = view.findViewById(R.id.sheep);
        sheep.setOnClickListener(this);

        LinearLayout monkey = view.findViewById(R.id.monkey);
        monkey.setOnClickListener(this);

        LinearLayout chicken = view.findViewById(R.id.chicken);
        chicken.setOnClickListener(this);

        LinearLayout dog = view.findViewById(R.id.dog);
        dog.setOnClickListener(this);

        LinearLayout pig = view.findViewById(R.id.pig);
        pig.setOnClickListener(this);


        return view;
    }

    @Override
    public void onDestroy() {
        if (activity != null) {
            activity.hideBackButton();
        }
        super.onDestroy();
    }

    public void track_item_clicked() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, clickedItem);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, clickable_item);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

//        Toast.makeText(getContext(), "clickedItem에 "+ "\""+ clickedItem + "\""+ " 1회 증가", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        pref.setIsBack(false);
        switch (v.getId()) {
            case R.id.mouse: {
                clickedItem = "띠선택 쥐띠 클릭"; clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("쥐띠", "쥐띠");
            }
            break;
            case R.id.cow: {
                clickedItem = "띠선택 소띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("소띠", "소띠");
            }
            break;
            case R.id.tiger: {
                clickedItem = "띠선택 호랑이띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("호랑이띠", "호랑이띠");
            }
            break;
            case R.id.rabbit: {
                clickedItem = "띠선택 토끼띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("토끼띠", "토끼띠");
            }
            break;
            case R.id.dragon: {
                clickedItem = "띠선택 용띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("용띠", "용띠");
            }
            break;
            case R.id.snake: {
                clickedItem = "띠선택 뱀띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("뱀띠", "뱀띠");
            }
            break;
            case R.id.horse: {
                clickedItem = "띠선택 말띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("말띠", "말띠");
            }
            break;
            case R.id.sheep: {
                clickedItem = "띠선택 양띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("양띠", "양띠");
            }
            break;
            case R.id.monkey: {
                clickedItem = "띠선택 원숭이띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("원숭이띠", "원숭이띠");
            }
            break;
            case R.id.chicken: {
                clickedItem = "띠선택 닭띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("닭띠", "닭띠");
            }
            break;
            case R.id.dog: {
                clickedItem = "띠선택 개띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("개띠", "개띠");
            }
            break;
            case R.id.pig: {
                clickedItem = "띠선택 돼지띠 클릭";clickable_item="clickable_item";
                track_item_clicked();
                intent.putExtra("돼지띠", "돼지띠");
            }
            break;
        }
        intent.putExtra("띠별운세", "띠별운세");
        startActivity(intent);
    }
}
