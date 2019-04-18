package com.jeycorp.dragonFortune.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeycorp.dragonFortune.type.FortunePartner;

import java.util.ArrayList;
import java.util.List;

public class NewPreferenceManager {
    SharedPreferences Pref;
    SharedPreferences.Editor edit;
    Context mCon;

    private static final String CLASS_NAME = NewPreferenceManager.class.getName();
    private static final String KEY_USER = "fortune_user";
    private static final String KEY_OUR = "fortune_our";
    private static final String KEY_PARTNERS = "fortune_partners";



    public static boolean putPartners(Context context, List<FortunePartner> partners) {
      Gson gson = new Gson();
      String resultJson = gson.toJson(partners);

        SharedPreferences sharedPreferences = context.getSharedPreferences(CLASS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PARTNERS, resultJson);
       return editor.commit();


    }

    public static List<FortunePartner> getPartners(Context context) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(CLASS_NAME, Context.MODE_PRIVATE);

        String exchangeText = sharedPreferences.getString(KEY_PARTNERS, null);
        if (TextUtils.isEmpty(exchangeText)) {
            return null;
        } else {
            List<FortunePartner> partners = gson.fromJson(exchangeText, new TypeToken<ArrayList<FortunePartner>>() {
            }.getType());
            return partners;
        }
    }
}
