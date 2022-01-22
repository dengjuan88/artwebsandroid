package cn.artwebs.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.artwebs.UI.DataParseXML.DataFlag;
import cn.artwebs.utils.HttpDownloader;
import cn.artwebs.utils.Utils;


import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AppApplication extends Application {
    private final static String TAG = "AgentApplication";
    private List<Activity> activities = new ArrayList<Activity>();
    private String loginName = "";
    private String password = "";
    public static String loginKey = "";
    private static PackageInfo pkg;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) activities.remove(activity);
    }

    @Override
    public void onTerminate() {
        onTerminate(false);
    }

    public void onTerminate(boolean isExit) {
        super.onTerminate();

        for (Activity activity : activities) {
            try {
                activity.finish();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        if (isExit) System.exit(0);
    }

    public static String getAppName() {
        return AppApplication.getPKG().applicationInfo.packageName.substring(AppApplication.getPKG().applicationInfo.packageName.lastIndexOf(".") + 1);
    }

    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Are we using advanced debugging - locale?
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String p = pref.getString("set_locale", "");
        if (p != null && !p.equals("")) {
            Locale locale;
            // workaround due to region code
            if (p.startsWith("zh")) {
                locale = Locale.CHINA;
            } else {
                locale = new Locale(p);
            }
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        try {
            pkg = getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        instance = this;
    }

    /**
     * Called when the overall system is running low on memory
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.w(TAG, "System is running low on memory");

//        BitmapCache.getInstance().clear();
    }

    /**
     * @return the main context of the Application
     */
    public synchronized static Context getAppContext() {
        return instance;
    }

    /**
     * @return the main resources from the Application
     */
    public static Resources getAppResources() {
        return instance.getResources();
    }

    public static PackageInfo getPKG() {
        return pkg;
    }


    public static void showNotification(int id, int icon, String title, String head, String content, Class obj) {
        NotificationManager notificationManager = (NotificationManager) getAppContext().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, title, System.currentTimeMillis());
        Intent intent = new Intent();
        intent.setClass(getAppContext(), obj);
        PendingIntent contentIntent = PendingIntent.getActivity(getAppContext(), 0, intent, 0);
        notification.defaults = Notification.DEFAULT_SOUND;
        notification.setLatestEventInfo(getAppContext(), head, content, contentIntent);
        notificationManager.notify(id, notification);
    }


    public static void cancelNotification(int id) {
        NotificationManager notificationManager = (NotificationManager) getAppContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }


    public static void logD(String tag, String meg) {
        Log.d(tag, meg);
    }

    private static int currVolume;

    //打开扬声器
    public static void OpenSpeaker() {

        try {
            AudioManager audioManager = (AudioManager) getAppContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.ROUTE_SPEAKER);
            currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);

            if (!audioManager.isSpeakerphoneOn()) {
                audioManager.setSpeakerphoneOn(true);

                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
                        AudioManager.STREAM_VOICE_CALL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //关闭扬声器
    public static void CloseSpeaker() {

        try {
            AudioManager audioManager = (AudioManager) getAppContext().getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                if (audioManager.isSpeakerphoneOn()) {
                    audioManager.setSpeakerphoneOn(false);
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, currVolume,
                            AudioManager.STREAM_VOICE_CALL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String preferencestoRead(String perferencesName, String key) {
        SharedPreferences preferences = getAppContext().getSharedPreferences(perferencesName,
                Context.MODE_WORLD_READABLE);
        String result = preferences.getString(key, null);
        return result;
    }

    public static void preferencestoWrite(String perferencesName, String key,
                                          String value) {
        SharedPreferences preferences = getAppContext().getSharedPreferences(perferencesName,
                Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void preferencestoDelete(String perferencesName){
        SharedPreferences preferences = getAppContext().getSharedPreferences(perferencesName,
                Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }

}


