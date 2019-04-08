package com.jeycorp.dragonFortune.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;

import java.util.ArrayList;

public class AddFriendsAdapter extends BaseAdapter {
//    private PreferenceManager pref;
//    private PreferenceManager2 pref2;
//    private PreferenceManager3 pref3;
//    private PreferenceManager4 pref4;
//    private PreferenceManager5 pref5;


    ArrayList<String> mNames = new ArrayList<String>();
    Context context;

    public AddFriendsAdapter(Context context, ArrayList<String> names) {
        this.context=context;
        mNames.addAll(names);
    }

    @Override
    public int getCount() {
        Log.e("어레이사이즈", "" + mNames.size());
        return mNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.customlayout, parent,false);


        ViewHolder holder = new ViewHolder();

        holder.txtUser = convertView.findViewById(R.id.txtUser);
        holder.txtBirth = convertView.findViewById(R.id.txtBirth);
        holder.txtSolar = convertView.findViewById(R.id.txtSolar);
        holder.txtModifyButton = convertView.findViewById(R.id.txtModifyButton);
        holder.bluecircle = convertView.findViewById(R.id.bluecircle);

//        pref = new PreferenceManager(convertView.getContext());
//        pref2 = new PreferenceManager2(convertView.getContext());
//        pref3 = new PreferenceManager3(convertView.getContext());
//        pref4 = new PreferenceManager4(convertView.getContext());
//        pref5 = new PreferenceManager5(convertView.getContext());


        Log.d("test", position + " : " + mNames.get(position));
//
//        Log.e("testPref", "" + pref.getName());
//        Log.e("testPref", "" + pref2.getName());
//        Log.e("testPref", "" + pref3.getName());
//        Log.e("testPref", "" + pref4.getName());
//        Log.e("testPref", "" + pref5.getName());

        holder. txtUser.setText(mNames.get(position));

        notifyDataSetChanged();

        return convertView;
    }


    class ViewHolder{
        TextView txtUser;
        TextView txtBirth ;
        TextView txtSolar ;
        TextView txtModifyButton ;
        LinearLayout bluecircle ;
    }

}