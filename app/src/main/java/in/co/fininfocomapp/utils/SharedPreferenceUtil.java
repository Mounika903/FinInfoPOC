package in.co.fininfocomapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {


    public static final String KEY_USER_LOGGED_IN_STATUS = "KEY_USER_LOGGED_IN_STATUS";

    public static Boolean getUserLoggedInStatus(Context context) {
        return getSharedPreferences(context)
                .getBoolean(KEY_USER_LOGGED_IN_STATUS, false);
    }

    public static void setUserLoggedInStatus(Context context, Boolean loginStatus) {
        getSharedPreferences(context).edit()
                .putBoolean(KEY_USER_LOGGED_IN_STATUS, loginStatus)
                .apply();
    }


    protected static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(getSharedPreferencesFilename(context),
                Context.MODE_PRIVATE);
    }

    public static String getSharedPreferencesFilename(Context context) {
        return context.getPackageName();
    }
}
