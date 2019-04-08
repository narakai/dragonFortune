package com.jeycorp.dragonFortune.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.activity.CompatibilityActivity;
import com.jeycorp.dragonFortune.activity.PopActivity;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;

import java.util.ArrayList;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
    private PreferenceManager2 pref2;
    private PreferenceManager3 pref3;
    private PreferenceManager4 pref4;
    private PreferenceManager5 pref5;

    public Activity mActivity;
    private ArrayList<String> items;

    public SelectAdapter(Activity activity, ArrayList<String> items) {
        this.mActivity = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.user_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        pref2 = new PreferenceManager2(mActivity);
        pref3 = new PreferenceManager3(mActivity);
        pref4 = new PreferenceManager4(mActivity);
        pref5 = new PreferenceManager5(mActivity);

        viewHolder.tv_select_user.setText(items.get(position));

        if(items.get(position).equals(pref2.getName())){
            int year = Integer.parseInt(pref2.getYear());
            int tail = year % 12;
            switch (tail) {
                case 4:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_01);
                    break;
                case 5:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_02);
                    break;
                case 6:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_03);
                    break;
                case 7:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_04);
                    break;
                case 8:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_05);
                    break;
                case 9:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_06);
                    break;
                case 10:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_07);
                    break;
                case 11:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_08);
                    break;
                case 0:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_09);
                    break;
                case 1:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_10);
                    break;
                case 2:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_11);
                    break;
                case 3:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_12);
                    break;

            }

        }

        if(items.get(position).equals(pref3.getName())){
            int year = Integer.parseInt(pref3.getYear());
            int tail = year % 12;
            switch (tail) {
                case 4:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_01);
                    break;
                case 5:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_02);
                    break;
                case 6:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_03);
                    break;
                case 7:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_04);
                    break;
                case 8:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_05);
                    break;
                case 9:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_06);
                    break;
                case 10:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_07);
                    break;
                case 11:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_08);
                    break;
                case 0:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_09);
                    break;
                case 1:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_10);
                    break;
                case 2:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_11);
                    break;
                case 3:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_12);
                    break;

            }

        }

        if(items.get(position).equals(pref4.getName())){
            int year = Integer.parseInt(pref4.getYear());
            int tail = year % 12;
            switch (tail) {
                case 4:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_01);
                    break;
                case 5:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_02);
                    break;
                case 6:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_03);
                    break;
                case 7:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_04);
                    break;
                case 8:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_05);
                    break;
                case 9:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_06);
                    break;
                case 10:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_07);
                    break;
                case 11:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_08);
                    break;
                case 0:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_09);
                    break;
                case 1:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_10);
                    break;
                case 2:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_11);
                    break;
                case 3:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_12);
                    break;

            }

        }

        if(items.get(position).equals(pref5.getName())){
            int year = Integer.parseInt(pref5.getYear());
            int tail = year % 12;
            switch (tail) {
                case 4:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_01);
                    break;
                case 5:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_02);
                    break;
                case 6:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_03);
                    break;
                case 7:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_04);
                    break;
                case 8:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_05);
                    break;
                case 9:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_06);
                    break;
                case 10:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_07);
                    break;
                case 11:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_08);
                    break;
                case 0:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_09);
                    break;
                case 1:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_10);
                    break;
                case 2:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_11);
                    break;
                case 3:
                    viewHolder.btn_select_user.setBackgroundResource(R.drawable.f_12_12);
                    break;

            }

        }

        if(items.get(position).equals("직접입력")){
            viewHolder.btn_select_user.setBackgroundResource(R.drawable.add);


        }



        viewHolder.layout_select_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(mActivity, "" + items.get(position), Toast.LENGTH_SHORT).show();
                    Intent t = new Intent(mActivity, CompatibilityActivity.class);
                    t.putExtra("isInput", items.get(position));
                    t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mActivity.startActivity(t);








            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("!!!!!", "size : " + items.size());
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button btn_select_user;
        TextView tv_select_user;
        LinearLayout layout_select_user;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_select_user = (Button) itemView.findViewById(R.id.btn_select_user);
            tv_select_user = (TextView) itemView.findViewById(R.id.tv_select_user);
            layout_select_user = (LinearLayout) itemView.findViewById(R.id.layout_select_user);

        }
    }
}
