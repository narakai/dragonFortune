package com.jeycorp.dragonFortune.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.adapter.SelectAdapter;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;

import java.util.ArrayList;


public class PopActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SelectAdapter adapter;
    private PreferenceManager pref;
    private PreferenceManager2 pref2;
    private PreferenceManager3 pref3;
    private PreferenceManager4 pref4;
    private PreferenceManager5 pref5;
//    private PreferenceManager6 pref6;

    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        items = new ArrayList<String>();

        pref = new PreferenceManager(PopActivity.this);
        pref2 = new PreferenceManager2(PopActivity.this);
        pref3 = new PreferenceManager3(PopActivity.this);
        pref4 = new PreferenceManager4(PopActivity.this);
        pref5 = new PreferenceManager5(PopActivity.this);
//        pref6 = new PreferenceManager6(PopActivity.this);

        if(pref2.getName()!=null){
            items.add(pref2.getName());
        }
        if(pref3.getName()!=null){
            items.add(pref3.getName());
        }
        if(pref4.getName()!=null){
            items.add(pref4.getName());
        }
        if(pref5.getName()!=null){
            items.add(pref5.getName());
        }

        items.add("직접입력");

        //각각의 아이템 배치결정, 뷰 관리
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new SelectAdapter(this, items);
        recyclerView.setAdapter(adapter);

//

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }
}
