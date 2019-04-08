package com.jeycorp.dragonFortune.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jeycorp.dragonFortune.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    public String clickedItem;
    public String clickable_item;

    public ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }


    //애널리틱스
    public void track_item_clicked(){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, clickedItem);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, clickable_item);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

//        Toast.makeText(getApplicationContext(), "clickedItem에 "+ "\""+ clickedItem + "\""+ " 1회 증가", Toast.LENGTH_LONG).show();an
    }

    //공유하기
    public void share(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                Bitmap bitmap = getBitmapFromView(scrollView, scrollView.getChildAt(0).getHeight(), scrollView.getChildAt(0).getWidth());
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/capture.jpeg");
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "공유하기", Toast.LENGTH_SHORT).show();

                Intent t = new Intent(Intent.ACTION_SEND);
                t.setType("image/jpeg");
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().toString() + "/capture.jpeg");
                t.setPackage("com.kakao.talk");
                t.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                t.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(t, "Share팝업"));
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("운세 공유하기를 위해 스토리지 권한이 필요합니다.")
//                        .setDeniedMessage("거부됨")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }






}
