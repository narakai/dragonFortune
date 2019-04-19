package com.jeycorp.dragonFortune.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jeycorp.dragonFortune.R;
import com.jeycorp.dragonFortune.define.UrlDefine;
import com.jeycorp.dragonFortune.fragment.AddFriendsFragment;
import com.jeycorp.dragonFortune.fragment.ModiFriendsFragment;
import com.jeycorp.dragonFortune.fragment.MyModifyFragment;
import com.jeycorp.dragonFortune.fragment.TailFortuneSelectFragment;
import com.jeycorp.dragonFortune.fragment.TodayFortuneSelectFragment;
import com.jeycorp.dragonFortune.param.PutScoreParam;
import com.jeycorp.dragonFortune.result.GetTodayFortuneTellingResult;
import com.jeycorp.dragonFortune.util.AlertReceiver;
import com.jeycorp.dragonFortune.util.PreferenceManager;
import com.jeycorp.dragonFortune.util.PreferenceManager2;
import com.jeycorp.dragonFortune.util.PreferenceManager3;
import com.jeycorp.dragonFortune.util.PreferenceManager4;
import com.jeycorp.dragonFortune.util.PreferenceManager5;
import com.jeycorp.dragonFortune.volley.VolleyJsonHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    protected ActionBarDrawerToggle drawerToggle;
    private PreferenceManager pref;
    private PreferenceManager2 pref2;
    private PreferenceManager3 pref3;
    private PreferenceManager4 pref4;
    private PreferenceManager5 pref5;
    private ProgressDialog mProgressDialog;


    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Switch sw;
    Context context;
    NavigationView navigationView;
    ArrayList<String> Names = new ArrayList<String>();
    private MainActivity activity;
    boolean isHam = true;
    Toolbar toolbar;
    DonutProgress donutProgress;
    TextView textScore;


    MenuItem button;

    RadioGroup rg_users;
    RadioButton rb_user1, rb_user2, rb_user3, rb_user4, rb_user5;
    FrameLayout modiFrame,modiFrame2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("kim", "MainAc -> onCre");
        activity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        context = this;

        modiFrame = findViewById(R.id.modiFrame);
        modiFrame.setVisibility(View.GONE);
        modiFrame2=findViewById(R.id.modiFrame2);

        modiFrame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation animation = new AlphaAnimation(0.5f, 0f);
                animation.setDuration(250);
                modiFrame.setAnimation(animation);
                modiFrame.setVisibility(View.GONE);

                modiFrame2.setAnimation(animation);
                modiFrame2.setVisibility(View.GONE);


            }
        });


        initView();
        modiFrame();
        pref.setIsBack(false);

        //광고
        MobileAds.initialize(MainActivity.this, getString(R.string.appID));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        //노티
        Log.e("noti1", "" + getIntent().getStringExtra("noti"));
        String str = getIntent().getStringExtra("noti");
        if (str != null) {
            Log.e("noti2", "" + getIntent().getStringExtra("noti"));
            if (str.equals("noti")) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new TodayFortuneSelectFragment(activity), "TodayFortuneSelectFragment").addToBackStack(null)
//                        .commit();
            }
        }


        View header = navigationView.getHeaderView(0);
        pref = new PreferenceManager(this);
        pref2 = new PreferenceManager2(this);
        pref3 = new PreferenceManager3(this);
        pref4 = new PreferenceManager4(this);
        pref5 = new PreferenceManager5(this);

        rb_user1 = header.findViewById(R.id.rb_user1);
        rb_user2 = header.findViewById(R.id.rb_user2);
        rb_user3 = header.findViewById(R.id.rb_user3);
        rb_user4 = header.findViewById(R.id.rb_user4);
        rb_user5 = header.findViewById(R.id.rb_user5);

        rb_user1.setChecked(true);


        //평가
        LinearLayout asses = header.findViewById(R.id.asses);
        asses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("용한운세 평가하기");
                builder.setMessage("용한운세를 응원해주세요.\n좋은운세로 보답하겠습니다.\n지금바로 용한운세를 평가하시겠습니까?");
                builder.setPositiveButton("지금, 평가할게요!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pref.getMUrl())));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("나중에 할게요.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);

                final AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#14A2F6"));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#A2A2A2"));
                    }
                });
                dialog.show();


            }
        });


//        버전
        LinearLayout version = header.findViewById(R.id.version);
        final int appVersion = (pref.getAppVersion());
        final int upVersion = pref.getUpVersion();
        TextView txtUpdate = header.findViewById(R.id.txtUpdate);
        TextView txtThisVersion = header.findViewById(R.id.txtThisVersion);

        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            txtThisVersion.setText(info.versionName);

        } catch (PackageManager.NameNotFoundException e) {

        }


        if (appVersion >= upVersion) {
            txtUpdate.setVisibility(View.INVISIBLE);
        } else {
            txtUpdate.setVisibility(View.VISIBLE);
        }

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appVersion < upVersion) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("용한운세")
                            .setCancelable(false)
                            .setMessage("새로운 버전이 출시 되었습니다. 업데이트 하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/")));
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pref.getMUrl())));
                                    finish();
                                }

                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            })
                            .show();

                } else {

                    Toast.makeText(getApplicationContext(), "최신 버전입니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });
        final String name = pref.getName();
        final String year = pref.getYear();
        final String month = pref.getMonth();
        final String day = pref.getDay();
        final String hour = pref.getHour();
        final String min = pref.getMin();
        final String oneLine = pref.getOneLine();
        final String onLine2 = pref.getOneLine2();
        final String sex = pref.getSex();
        final String solunar = pref.getSolunar();

        //가족 연인추가
        if (pref.getName() != null) {
            Log.e("pref", "pref.getName() : " + pref.getName());
            LinearLayout containerModi2 = header.findViewById(R.id.containerModi2);
            TextView txtUser1 = header.findViewById(R.id.txtUser1);
            TextView txtBirth1 = header.findViewById(R.id.txtBirth1);
            TextView txtSolar1 = header.findViewById(R.id.txtSolar1);
//            TextView txtModifyButton1 = header.findViewById(R.id.txtModifyButton1);


            txtUser1.setText(pref.getName());
            txtBirth1.setText(pref.getOneLine().substring(0, 12));
            if (pref.getSolunar().equals("solar")) {
                txtSolar1.setText("(양력)");
            } else {
                txtSolar1.setText("(음력)");
            }

//            txtModifyButton1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    txtModify(v);
//
//                }
//            });
        }

        if (pref2.getName() != null) {
            LinearLayout containerFriend = header.findViewById(R.id.containerFriend);
            containerFriend.setVisibility(View.VISIBLE);

            LinearLayout containerModi1 = header.findViewById(R.id.containerModi1);
            LinearLayout containerModi2 = header.findViewById(R.id.containerModi2);

            TextView txtUser1 = header.findViewById(R.id.txtUser1);
            TextView txtBirth1 = header.findViewById(R.id.txtBirth1);
            TextView txtSolar1 = header.findViewById(R.id.txtSolar1);
//            TextView txtModifyButton1 = header.findViewById(R.id.txtModifyButton1);
            RadioButton radioButton1 = header.findViewById(R.id.rb_user2);

//            txtModifyButton1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    txtModify(v);
//
//                }
//            });


            TextView txtUser2 = header.findViewById(R.id.txtUser2);
            TextView txtBirth2 = header.findViewById(R.id.txtBirth2);
            TextView txtSolar2 = header.findViewById(R.id.txtSolar2);
//            TextView txtModifyButton2 = header.findViewById(R.id.txtModifyButton2);

//            txtModifyButton2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ModiFriendsFragment tfo = new ModiFriendsFragment(activity);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", 2);
//                    tfo.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, tfo, "ModiFriendsFragment").addToBackStack(null).commit();
//                    drawer.closeDrawers();
//                }
//            });

            txtUser1.setText(pref.getName());
            txtBirth1.setText(pref.getOneLine().substring(0, 12));
            if (pref.getSolunar().equals("solar")) {
                txtSolar1.setText("(양력)");
            } else {
                txtSolar1.setText("(음력)");
            }

            txtUser2.setText(pref2.getName());
            txtBirth2.setText(pref2.getOneLine().substring(0, 12));
            if (pref2.getSolunar().equals("solar")) {
                txtSolar2.setText("(양력)");
            } else {
                txtSolar2.setText("(음력)");
            }
            containerModi1.setVisibility(View.VISIBLE);
            containerModi2.setVisibility(View.VISIBLE);

            containerModi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    rb_user2.setChecked(true);
                    pref.setName(pref2.getName());
                    pref.setYear(pref2.getYear());
                    pref.setMonth(pref2.getMonth());
                    pref.setDay(pref2.getDay());
                    pref.setHour(pref2.getHour());
                    pref.setMin(pref2.getMin());
                    pref.setOneLine(pref2.getOneLine());
                    pref.setOneLine2(pref2.getOneLine2());
                    pref.setSex(pref2.getSex());
                    pref.setSolunar(pref2.getSolunar());
                    pref2.setName(name);
                    pref2.setYear(year);
                    pref2.setMonth(month);
                    pref2.setDay(day);
                    pref2.setHour(hour);
                    pref2.setMin(min);
                    pref2.setOneLine(oneLine);
                    pref2.setOneLine2(onLine2);
                    pref2.setSex(sex);
                    pref2.setSolunar(solunar);
                    finish();
                    startActivity(getIntent());
                    drawer.closeDrawers();
//                    setScore();
                    Toast.makeText(MainActivity.this, "기본 사용자가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });


            Log.e("pref2", "" + pref2.getName());
        }

        if (pref3.getName() != null) {
            LinearLayout containerModi1 = header.findViewById(R.id.containerModi1);
            containerModi1.setVisibility(View.VISIBLE);
            LinearLayout containerFriend = header.findViewById(R.id.containerFriend);
            containerFriend.setVisibility(View.VISIBLE);
            LinearLayout containerModi3 = header.findViewById(R.id.containerModi3);
            Log.e("pref3", "" + pref3.getName());
            TextView txtUser3 = header.findViewById(R.id.txtUser3);
            TextView txtBirth3 = header.findViewById(R.id.txtBirth3);
            TextView txtSolar3 = header.findViewById(R.id.txtSolar3);
//            TextView txtModifyButton3 = header.findViewById(R.id.txtModifyButton3);

            containerModi3.setVisibility(View.VISIBLE);

            txtUser3.setText(pref3.getName());
            txtBirth3.setText(pref3.getOneLine().substring(0, 12));
            if (pref3.getSolunar().equals("solar")) {
                txtSolar3.setText("(양력)");
            } else {
                txtSolar3.setText("(음력)");
            }

//            txtModifyButton3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ModiFriendsFragment tfo = new ModiFriendsFragment(activity);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", 3);
//                    tfo.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, tfo, "ModiFriendsFragment").addToBackStack(null).commit();
//                    drawer.closeDrawers();
//                }
//            });


            containerModi3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    rb_user3.setChecked(true);
                    pref.setName(pref3.getName());
                    pref.setYear(pref3.getYear());
                    pref.setMonth(pref3.getMonth());
                    pref.setDay(pref3.getDay());
                    pref.setHour(pref3.getHour());
                    pref.setMin(pref3.getMin());
                    pref.setOneLine(pref3.getOneLine());
                    pref.setOneLine2(pref3.getOneLine2());
                    pref.setSex(pref3.getSex());
                    pref.setSolunar(pref3.getSolunar());
                    pref3.setName(name);
                    pref3.setYear(year);
                    pref3.setMonth(month);
                    pref3.setDay(day);
                    pref3.setHour(hour);
                    pref3.setMin(min);
                    pref3.setOneLine(oneLine);
                    pref3.setOneLine2(onLine2);
                    pref3.setSex(sex);
                    pref3.setSolunar(solunar);
                    finish();
                    startActivity(getIntent());
//                    drawer.closeDrawers();
//                    setScore();
                    Toast.makeText(MainActivity.this, "기본 사용자가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });


        }


        if (pref4.getName() != null) {
            LinearLayout containerModi1 = header.findViewById(R.id.containerModi1);
            containerModi1.setVisibility(View.VISIBLE);
            LinearLayout containerFriend = header.findViewById(R.id.containerFriend);
            containerFriend.setVisibility(View.VISIBLE);
            LinearLayout containerModi4 = header.findViewById(R.id.containerModi4);

            TextView txtUser4 = header.findViewById(R.id.txtUser4);
            TextView txtBirth4 = header.findViewById(R.id.txtBirth4);
            TextView txtSolar4 = header.findViewById(R.id.txtSolar4);
//            TextView txtModifyButton4 = header.findViewById(R.id.txtModifyButton4);
            Log.e("pref4", "" + pref4.getName());
            containerModi4.setVisibility(View.VISIBLE);

            txtUser4.setText(pref4.getName());
            txtBirth4.setText(pref4.getOneLine().substring(0, 12));
            if (pref4.getSolunar().equals("solar")) {
                txtSolar4.setText("(양력)");
            } else {
                txtSolar4.setText("(음력)");
            }

//            txtModifyButton4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ModiFriendsFragment tfo = new ModiFriendsFragment(activity);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", 4);
//                    tfo.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, tfo, "ModiFriendsFragment").addToBackStack(null).commit();
//                    drawer.closeDrawers();
//                }
//            });

            containerModi4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    rb_user4.setChecked(true);
                    pref.setName(pref4.getName());
                    pref.setYear(pref4.getYear());
                    pref.setMonth(pref4.getMonth());
                    pref.setDay(pref4.getDay());
                    pref.setHour(pref4.getHour());
                    pref.setMin(pref4.getMin());
                    pref.setOneLine(pref4.getOneLine());
                    pref.setOneLine2(pref4.getOneLine2());
                    pref.setSex(pref4.getSex());
                    pref.setSolunar(pref4.getSolunar());
                    pref4.setName(name);
                    pref4.setYear(year);
                    pref4.setMonth(month);
                    pref4.setDay(day);
                    pref4.setHour(hour);
                    pref4.setMin(min);
                    pref4.setOneLine(oneLine);
                    pref4.setOneLine2(onLine2);
                    pref4.setSex(sex);
                    pref4.setSolunar(solunar);
                    finish();
                    startActivity(getIntent());
//                    drawer.closeDrawers();
//                    setScore();
                    Toast.makeText(MainActivity.this, "기본 사용자가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (pref5.getName() != null) {
            LinearLayout containerModi1 = header.findViewById(R.id.containerModi1);
            containerModi1.setVisibility(View.VISIBLE);
            LinearLayout containerFriend = header.findViewById(R.id.containerFriend);
            containerFriend.setVisibility(View.VISIBLE);
            LinearLayout containerModi5 = header.findViewById(R.id.containerModi5);
            containerModi5.setVisibility(View.VISIBLE);

            TextView txtUser5 = header.findViewById(R.id.txtUser5);
            TextView txtBirth5 = header.findViewById(R.id.txtBirth5);
            TextView txtSolar5 = header.findViewById(R.id.txtSolar5);
//            TextView txtModifyButton5 = header.findViewById(R.id.txtModifyButton5);

            txtUser5.setText(pref5.getName());
            txtBirth5.setText(pref5.getOneLine().substring(0, 12));
            if (pref5.getSolunar().equals("solar")) {
                txtSolar5.setText("(양력)");
            } else {
                txtSolar5.setText("(음력)");
            }

//            txtModifyButton5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ModiFriendsFragment tfo = new ModiFriendsFragment(activity);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", 5);
//                    tfo.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, tfo, "ModiFriendsFragment").addToBackStack(null).commit();
//                    drawer.closeDrawers();
//                }
//            });

            containerModi5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rb_user5.setChecked(true);

                    pref.setName(pref5.getName());
                    pref.setYear(pref5.getYear());
                    pref.setMonth(pref5.getMonth());
                    pref.setDay(pref5.getDay());
                    pref.setHour(pref5.getHour());
                    pref.setMin(pref5.getMin());
                    pref.setOneLine(pref5.getOneLine());
                    pref.setOneLine2(pref5.getOneLine2());
                    pref.setSex(pref5.getSex());
                    pref.setSolunar(pref5.getSolunar());
                    pref5.setName(name);
                    pref5.setYear(year);
                    pref5.setMonth(month);
                    pref5.setDay(day);
                    pref5.setHour(hour);
                    pref5.setMin(min);
                    pref5.setOneLine(oneLine);
                    pref5.setOneLine2(onLine2);
                    pref5.setSex(sex);
                    pref5.setSolunar(solunar);
                    drawer.closeDrawers();
//                    setScore();

                    finish();
                    startActivity(getIntent());
                    Toast.makeText(MainActivity.this, "기본 사용자가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });


            Log.e("pref5", "" + pref5.getName());
        }
        //띠사진변경
        ImageView imgProfile = header.findViewById(R.id.imgProfile);
        TextView txtBirth = header.findViewById(R.id.txtBirth);
        TextView txtUser = header.findViewById(R.id.txtUser);
        TextView txtSolar = header.findViewById(R.id.txtSolar);
        txtBirth.setText(pref.getYear() + "." + pref.getMonth() + "." + pref.getDay());
        txtUser.setText(pref.getName());
        if (pref.getSolunar().equals("solar")) {
            txtSolar.setText("(양력)");
        } else {
            txtSolar.setText("(음력)");
        }


        int year2 = Integer.parseInt(pref.getYear());
        int tail = year2 % 12;
        switch (tail) {
            case 4:
                imgProfile.setImageResource(R.drawable.b_12_01);
                break;
            case 5:
                imgProfile.setImageResource(R.drawable.b_12_02);
                break;
            case 6:
                imgProfile.setImageResource(R.drawable.b_12_03);
                break;
            case 7:
                imgProfile.setImageResource(R.drawable.b_12_04);
                break;
            case 8:
                imgProfile.setImageResource(R.drawable.b_12_05);
                break;
            case 9:
                imgProfile.setImageResource(R.drawable.b_12_06);
                break;
            case 10:
                imgProfile.setImageResource(R.drawable.b_12_07);
                break;
            case 11:
                imgProfile.setImageResource(R.drawable.b_12_08);
                break;
            case 0:
                imgProfile.setImageResource(R.drawable.b_12_09);
                break;
            case 1:
                imgProfile.setImageResource(R.drawable.b_12_10);
                break;
            case 2:
                imgProfile.setImageResource(R.drawable.b_12_11);
                break;
            case 3:
                imgProfile.setImageResource(R.drawable.b_12_12);
                break;

        }


        //스위치 값 체크
        sw = header.findViewById(R.id.switch1);
        if (pref.getSwitch()) {
            sw.setChecked(true);
        } else {
            sw.setChecked(false);
        }
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                     calendar에 시간 셋팅
                    onTimeSet();
                    Toast.makeText(getApplicationContext(), "오전 8시에 오늘의 운세가 도착합니다.", Toast.LENGTH_SHORT).show();
                    pref.setSwitch(true);

                } else {
                    cancelAlarm();
                    pref.setSwitch(false);
                    Toast.makeText(getApplicationContext(), "알람 설정이 해제되었습니다.", Toast.LENGTH_SHORT).show();

                }
            }

        });
        if (sw.isChecked()) {
            onTimeSet();
        }
        //네비게이션 우측정렬
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                } else {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });
    }

    public void txtModify(View v) {
        //프로필수정
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new MyModifyFragment(this), "MyModifyFragment").addToBackStack(null).commit();
        drawer.closeDrawers();

    }


    @Override
    protected void onResume() {

        Log.e("kang", "MainActivity -> onResume  " + pref.getRefresh());
        if (pref.getRefresh().equals("1")) {
            finish();
            startActivity(getIntent());
        }

        pref.setRefresh("0");

        super.onResume();

    }



    public void modiFrame(){
        LinearLayout friend_add_container =findViewById(R.id.friend_add_container);
        friend_add_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new AddFriendsFragment(MainActivity.this), "AddFriendsFragment").addToBackStack(null).commit();
                drawer.closeDrawers();
                close_ModiFrame();


            }
        });
    }

    public void close_ModiFrame(){
        AlphaAnimation animation = new AlphaAnimation(0.5f, 0f);
        animation.setDuration(250);
        modiFrame.setAnimation(animation);
        modiFrame.setVisibility(View.GONE);

        modiFrame2.setAnimation(animation);
        modiFrame2.setVisibility(View.GONE);

    }



    public void addFriends(View v) {


        modiFrame.setVisibility(View.VISIBLE);
        modiFrame2.setVisibility(View.VISIBLE);
        modiFrame.setBackgroundResource(R.color.black);
        modiFrame.setAlpha(0.5f);
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(250);
        modiFrame.setAnimation(animation);
        modiFrame2.setAnimation(animation);
        modiFrame.setClickable(true);
        modiFrame2.setClickable(true);


    }



    public void initView() {
        Log.e("kim", "MainActivity -> initView");
        LinearLayout item_today_fortune = findViewById(R.id.item_today_fortune);
        item_today_fortune.setOnClickListener(this);
        LinearLayout item_year_fortune = findViewById(R.id.item_year_fortune);
        item_year_fortune.setOnClickListener(this);
        LinearLayout item_gold_fortune = findViewById(R.id.item_gold_fortune);
        item_gold_fortune.setOnClickListener(this);
        LinearLayout item_love_fortune = findViewById(R.id.item_love_fortune);
        item_love_fortune.setOnClickListener(this);
        LinearLayout item_tojung_fortune = findViewById(R.id.item_tojung_fortune);
        item_tojung_fortune.setOnClickListener(this);
        LinearLayout item_today_tail_fortune = findViewById(R.id.item_today_tail_fortune);
        item_today_tail_fortune.setOnClickListener(this);

        donutProgress = findViewById(R.id.progressBar);
        textScore = findViewById(R.id.score);
        pref = new PreferenceManager(this);
        setScore();


        TextView imgMonth = findViewById(R.id.imgMonth);
        SimpleDateFormat df = new SimpleDateFormat("M", Locale.KOREA);
        String str_date = df.format(new Date());

        imgMonth.setText(str_date);

        final ScrollView scrollView = findViewById(R.id.scrollView1);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, 0);
            }
        });
    }

    public void setScore() {
        PutScoreParam putScoreParam = new PutScoreParam();

        putScoreParam.setName(pref.getName());
        putScoreParam.setSex(pref.getSex());
        putScoreParam.setSolunar(pref.getSolunar());
        putScoreParam.setYear(pref.getYear());
        putScoreParam.setMonth(pref.getMonth());
        putScoreParam.setDay(pref.getDay());
        putScoreParam.setHour(pref.getHour());
        putScoreParam.setMin(pref.getMin());


        Log.e("와", "" + putScoreParam.getName());
        Log.e("와", "" + putScoreParam.getSex());
        Log.e("와", "" + putScoreParam.getSolunar());
        Log.e("와", "" + putScoreParam.getYear());
        Log.e("와", "" + putScoreParam.getMonth());
        Log.e("와", "" + putScoreParam.getDay());
        Log.e("와", "" + putScoreParam.getHour());
        Log.e("와", "" + putScoreParam.getMin());


        VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelper = new VolleyJsonHelper<PutScoreParam, GetTodayFortuneTellingResult>(this);
        setScoreHelper.request(UrlDefine.API_SET_TODAY_FORTUNE_OUTLINE, putScoreParam, GetTodayFortuneTellingResult.class, setScoreHelperListener, false, false, false);

    }

    private VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult> setScoreHelperListener = new VolleyJsonHelper.VolleyJsonHelperListener<PutScoreParam, GetTodayFortuneTellingResult>() {
        @Override
        public void onSuccess(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            if (getTodayFortuneTellingResult.getLuck() != null) {

                TextView txtUserName = findViewById(R.id.txtUserName);
                txtUserName.setText(pref.getName());
                Log.e("kim4", "IntroActivity -> setScoreHelperListener");
                textScore.setText(getTodayFortuneTellingResult.getLuck() + "0");
                Log.e("점수머야", getTodayFortuneTellingResult.getLuck());

                Button imgprogress = findViewById(R.id.sun);
                TextView txtTodayOutline = findViewById(R.id.txtTodayOutline);
                donutProgress.setMax(100);
                final int progress = Integer.parseInt(getTodayFortuneTellingResult.getLuck() + "0");
                Log.e("프로그래스 숫자", "" + progress);


                if (progress <= 20) {
                    imgprogress.setBackground(getResources().getDrawable(R.drawable.weather_05));
                    txtTodayOutline.setText("오늘 하루 주위를 경계하세요.\n조심해서 나쁠것 없는 하루입니다.");
                }

                if (progress >= 30 && progress < 50) {
                    imgprogress.setBackground(getResources().getDrawable(R.drawable.weather_04));
                    txtTodayOutline.setText("자칫 마찰이 생길 수 있어요.\n주위를 둘러보는 하루가 되세요.");

                }

                if (progress >= 50 && progress < 70) {
                    imgprogress.setBackground(getResources().getDrawable(R.drawable.weather_03));
                    txtTodayOutline.setText("불행과 좋은일이 함께하는 하루입니다.\n행복한 하루 되세요.");

                }

                if (progress >= 70 && progress < 90) {
                    imgprogress.setBackground(getResources().getDrawable(R.drawable.weather_02));
                    txtTodayOutline.setText("오늘은 좋은일이 있을거 같아요.\n행복한 하루되세요.");

                }

                if (progress >= 90 && progress < 100) {
                    imgprogress.setBackground(getResources().getDrawable(R.drawable.weather_01));
                    txtTodayOutline.setText("오늘은 매우 운이 좋은날입니다.\n행복한 하루되세요.");

                }

                if (this == null)
                    return;

                runOnUiThread(new Runnable() {
                    public void run() {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(donutProgress, "progress", 0, progress);
                        anim.setInterpolator(new DecelerateInterpolator());
                        anim.setDuration(2000);
                        anim.start();


                        anim.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {

                                if (progress >= 100) {
                                    //do something :)
                                }
                            }

                        });

                    }
                });

            }


        }

        @Override
        public void onMessage(PutScoreParam putScoreParam, GetTodayFortuneTellingResult getTodayFortuneTellingResult) {
            Log.e("kim6", "s -> setScoreHelperListener");

        }

        @Override
        public void onError(PutScoreParam putScoreParam, VolleyError error) {
            Log.e("kim7", "w -> setScoreHelperListener");
        }
    };


    @Override
    public void onBackPressed() {

        if(modiFrame.getVisibility()==View.VISIBLE){
            AlphaAnimation animation = new AlphaAnimation(0.5f, 0f);
            animation.setDuration(250);
            modiFrame.setAnimation(animation);
            modiFrame.setVisibility(View.GONE);
            modiFrame2.setVisibility(View.GONE);
            modiFrame2.setAnimation(animation);
            modiFrame.setClickable(false);
            modiFrame2.setClickable(false);
        }else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                TodayFortuneSelectFragment todayFortuneSelectFragment = (TodayFortuneSelectFragment) getSupportFragmentManager().findFragmentByTag("TodayFortuneSelectFragment");
                TailFortuneSelectFragment tailFortuneSelectFragment = (TailFortuneSelectFragment) getSupportFragmentManager().findFragmentByTag("TailFortuneSelectFragment");
                AddFriendsFragment addFriendsFragment = (AddFriendsFragment) getSupportFragmentManager().findFragmentByTag("AddFriendsFragment");
                MyModifyFragment myModifyFragment = (MyModifyFragment) getSupportFragmentManager().findFragmentByTag("MyModifyFragment");
                ModiFriendsFragment modiFriendsFragment = (ModiFriendsFragment) getSupportFragmentManager().findFragmentByTag("ModiFriendsFragment");

                if (
                        todayFortuneSelectFragment != null && todayFortuneSelectFragment.isVisible()
                                || tailFortuneSelectFragment != null && tailFortuneSelectFragment.isVisible()
                                || addFriendsFragment != null && addFriendsFragment.isVisible()
                                || myModifyFragment != null && myModifyFragment.isVisible()
                                || modiFriendsFragment != null && modiFriendsFragment.isVisible()
                ) {
                    super.onBackPressed();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("용한운세")
                            .setMessage("앱을 닫으시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }

                            })
                            .setNegativeButton("아니오", null)
                            .show();
                }

            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("kim", "MainAc -> onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        button = menu.findItem(R.id.action_settings);
        button.setVisible(false);

        Log.e("noti1", "" + getIntent().getStringExtra("noti"));
        String str = getIntent().getStringExtra("noti");
        if (str != null) {
            Log.e("noti2", "" + getIntent().getStringExtra("noti"));
            if (str.equals("noti")) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new TodayFortuneSelectFragment(activity), "TodayFortuneSelectFragment").addToBackStack(null)
                        .commit();
            }
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //노티코드
    public void onTimeSet() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 8);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 0);
        startAlarm(c);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }


    public void hideBackButton() {
        Log.e("kim", "MainActivity -> hideBackButton");
        button.setVisible(false);
    }

    public void showBackButton() {
        Log.e("kim", "MainActivity -> showBackButton");
        button.setVisible(true);
    }

    public void hideHam() {
        toolbar.setNavigationIcon(null);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


    }

    public void showHam() {
//        toolbar.setNavigationIcon(R.drawable.ic_menu_camera);


    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = new Intent(this, ResultActivity.class);

        switch (v.getId()) {
            case R.id.item_today_fortune:
                clickedItem = "메인 오늘의운세 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new TodayFortuneSelectFragment(activity), "TodayFortuneSelectFragment").addToBackStack(null)
                        .commit();
                break;

            case R.id.item_today_tail_fortune:
                clickedItem = "메인 띠별운세 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out).add(R.id.containerMain, new TailFortuneSelectFragment(activity), "TailFortuneSelectFragment").addToBackStack(null).commit();

                break;

            case R.id.item_year_fortune:
                clickedItem = "메인 신년운세 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                pref.setIsBack(false);
                Intent intent3 = new Intent(this, ResultActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent3.putExtra("신년", "신년");
                startActivity(intent3);
                break;

            case R.id.item_gold_fortune:
                clickedItem = "메인 재물운 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                pref.setIsBack(false);
                Intent intent4 = new Intent(this, ResultActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent4.putExtra("재물", "재물");
                startActivity(intent4);
                break;

            case R.id.item_love_fortune:
                clickedItem = "메인 궁합 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                pref.setStringCheck("0");
                Intent t = new Intent(this, CompatibilityActivity.class);
                t.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(t);
//                fragmentTransaction.setCustomAnimations(R.anim.left_in,R.anim.right_out).add(R.id.containerMain, new CompatibilityFragment(activity)).addToBackStack(null).commit();

                break;


            case R.id.item_tojung_fortune:
                clickedItem = "메인 토정비결 클릭";
                clickable_item = "clickable_item";
                track_item_clicked();
                pref.setIsBack(false);
                Intent intent2 = new Intent(this, ResultActivity.class);
                intent2.putExtra("토정", "토정");
                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                break;
        }

    }
}
