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
import com.jeycorp.dragonFortune.activity.MainActivity;
import com.jeycorp.dragonFortune.activity.ResultActivity;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTailFortuneResult;
import com.jeycorp.dragonFortune.result.GetYearFortuneResult;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TailFortuneOutlineFragment extends Fragment implements View.OnClickListener{
   private View view;
    private PreferenceManager pref;
    private TextView txtOutline, txt59, txt71, txt83, txt95
    ,txtYear1,txtYear2,txtYear3,txtYear4;

    private ResultActivity activity;
    MainActivity mainActivity;

    public TailFortuneOutlineFragment(ResultActivity activity) {
        this.activity=activity;
        // Required empty public constructor
    }

    public TailFortuneOutlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tail_fortune_outline,null);
        pref = new PreferenceManager(getContext());

        LinearLayout backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("백키타냐?","탔냐");
                getActivity().onBackPressed();
            }
        });




        txtOutline = view.findViewById(R.id.txtOutline);
        txt59 = view.findViewById(R.id.txt59);
        txt71 = view.findViewById(R.id.txt71);
        txt83 = view.findViewById(R.id.txt83);
        txt95 = view.findViewById(R.id.txt95);
        txtYear1 = view.findViewById(R.id.txtYear1);
        txtYear2 = view.findViewById(R.id.txtYear2);
        txtYear3 = view.findViewById(R.id.txtYear3);
        txtYear4 = view.findViewById(R.id.txtYear4);

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
                activity.clickedItem="신년운세 공유하기 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                activity.share();
            }
        });

        setText();
//
//        ImageView imgProfile = view.findViewById(R.id.imgProfile);
//        int year = Integer.parseInt(pref.getYear());
//        int tail = year%12;
//        switch (tail){
//            case 4:
//                imgProfile.setImageResource(R.drawable.b_12_01);
//                break;
//            case 5:
//                imgProfile.setImageResource(R.drawable.b_12_02);
//                break;
//            case 6:
//                imgProfile.setImageResource(R.drawable.b_12_03);
//                break;
//            case 7:
//                imgProfile.setImageResource(R.drawable.b_12_04);
//                break;
//            case 8:
//                imgProfile.setImageResource(R.drawable.b_12_05);
//                break;
//            case 9:
//                imgProfile.setImageResource(R.drawable.b_12_06);
//                break;
//            case 10:
//                imgProfile.setImageResource(R.drawable.b_12_07);
//                break;
//            case 11:
//                imgProfile.setImageResource(R.drawable.b_12_08);
//                break;
//            case 0:
//                imgProfile.setImageResource(R.drawable.b_12_09);
//                break;
//            case 1:
//                imgProfile.setImageResource(R.drawable.b_12_10);
//                break;
//            case 2:
//                imgProfile.setImageResource(R.drawable.b_12_11);
//                break;
//            case 3:
//                imgProfile.setImageResource(R.drawable.b_12_12);
//                break;
//
//        }

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


        VolleyJsonHelper<PutScoreParam, GetTailFortuneResult> setTextHelper = new VolleyJsonHelper<PutScoreParam, GetTailFortuneResult>(getActivity());
        setTextHelper.request(UrlDefine.API_SET_TAIL_FORTUNE_OUTLINE, putScoreParam, GetTailFortuneResult.class, setTextHelperListener, false, false, false);

    }

    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTailFortuneResult> setTextHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTailFortuneResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTailFortuneResult getTailFortuneResult) {
            ImageView imgTail = view.findViewById(R.id.imgTail);

            Bundle extra = getArguments();

            if( extra !=null){
                int id = extra.getInt("id");

              switch (id){
                  case 1: {
                      imgTail.setImageResource(R.drawable.d12_01);
                      String rat = getTailFortuneResult.getRat();
                      String ft[] = rat.split(":");
                      //총론
                      int index = rat.indexOf(":");
                      String ratOutline = rat.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                      break;
                  case 2:
                  {
                      imgTail.setImageResource(R.drawable.d12_02);
                      String cow = getTailFortuneResult.getCow();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));

                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 3:
                  {
                      imgTail.setImageResource(R.drawable.d12_03);
                      String cow = getTailFortuneResult.getTiger();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 4:
                  {
                      imgTail.setImageResource(R.drawable.d12_04);
                      String cow = getTailFortuneResult.getRabbit();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 5:
                  {
                      imgTail.setImageResource(R.drawable.d12_05);
                      String cow = getTailFortuneResult.getDragon();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 6:
                  {
                      imgTail.setImageResource(R.drawable.d12_06);
                      String cow = getTailFortuneResult.getSnake();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 7:
                  {
                      imgTail.setImageResource(R.drawable.d12_07);
                      String cow = getTailFortuneResult.getHorse();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 8:
                  {
                      imgTail.setImageResource(R.drawable.d12_08);
                      String cow = getTailFortuneResult.getSheep();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 9:
                  {
                      imgTail.setImageResource(R.drawable.d12_09);
                      String cow = getTailFortuneResult.getMonkey();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 10:
                  {
                      imgTail.setImageResource(R.drawable.d12_10);
                      String cow = getTailFortuneResult.getChicken();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 11:
                  {
                      imgTail.setImageResource(R.drawable.d12_11);
                      String cow = getTailFortuneResult.getDog();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;
                  case 12:
                  {
                      imgTail.setImageResource(R.drawable.d12_12);
                      String cow = getTailFortuneResult.getPig();
                      String ft[] = cow.split(":");
                      //총론
                      int index = cow.indexOf(":");
                      String ratOutline = cow.substring(0, (index - 6));
                      txtOutline.setText(ratOutline);
                      //첫번째 년생 타이틀
                      Log.e("모야이거", "" + ft[0]);
                      Log.e("모야이거", "" + ft[1]);
                      int indexA = ft[0].indexOf("년생");
                      txtYear1.setText(ft[0].substring(indexA - 2, indexA + 2));
                      //두번째
                      int indexB = ft[1].indexOf("년생");
                      txtYear2.setText(ft[1].substring(indexB - 2, indexB + 2));

                      int indexC = ft[2].indexOf("년생");
                      txtYear3.setText(ft[2].substring(indexC - 2, indexC + 2));

                      int indexD = ft[3].indexOf("년생");
                      txtYear4.setText(ft[3].substring(indexD - 2, indexD + 2));



                      //첫번째 년생 내용
                      int index3 = ft[1].indexOf("년생");
                      String contents = ft[1].substring(0, index3 - 2);
                      txt59.setText(contents);
                      //두번째
                      int index4 = ft[2].indexOf("년생");
                      String contents2 = ft[2].substring(0, index4 - 2);
                      txt71.setText(contents2);
                      //세번째
                      int index5 = ft[3].indexOf("년생");
                      String contents3 = ft[3].substring(0, index5 - 2);
                      txt83.setText(contents3);
                      //네번째
                      txt95.setText(ft[4]);//
                  }
                  break;

              }


            }







        }

        @Override
        public void onMessage(PutScoreParam putScoreParam, GetTailFortuneResult getTailFortuneResult) {
            Log.e("또왜안되노3","아");

        }

        @Override
        public void onError(PutScoreParam putScoreParam, VolleyError error) {
            Log.e("또왜안되노4","나");

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
                activity.clickedItem="하단바 오늘의 운세 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                pref.setIsBack(true);
                fragmentTransaction.add(R.id.topContainerMain, new TodayFortuneSelectFragment()).addToBackStack(null).commit();
                break;



            case R.id.item_year_fortune:
                activity.clickedItem="하단바 신년운세 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent3 = new Intent(getContext(), ResultActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent3.putExtra("신년","신년");
                startActivity(intent3);
                break;

            case R.id.item_gold_fortune:
                activity.clickedItem="하단바 재물운 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent4 = new Intent(getContext(), ResultActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent4.putExtra("재물","재물");
                startActivity(intent4);
                break;

            case R.id.item_love_fortune:
                activity.clickedItem="하단바 궁합 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent5 = new Intent(getContext(), CompatibilityActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent5);
                break;




            case R.id.item_tojung_fortune:
                activity.clickedItem="하단바 토정비결 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                Intent intent2 = new Intent(getContext(), ResultActivity.class);
                intent2.putExtra("토정","토정");
                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                break;

            case R.id.item_today_tail_fortune:
                activity.clickedItem="하단바 띠별운세 클릭";
                activity.clickable_item="clickable_item";
                activity.track_item_clicked();
                pref.setIsBack(true);
                fragmentTransaction.add(R.id.topContainerMain, new TailFortuneSelectFragment()).addToBackStack(null).commit();
                break;
        }

    }
}
