package com.android.xmpp.notification;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class PushUtil {

    private static PushUtil xmppUtil;

    private String apiKey = "";

    private String useName = "";

    private String appName;

    private Intent defaultIntent;

    private int icon;

    private String ip = "";

    private int port = 0;

    private ServiceManager serviceManager;

    private OnPushListener onPushListener;

    private Context context;


    public int getPort() {
        return port;
    }

    public static PushUtil getInstance(Context context) {
        if (xmppUtil == null) {
            xmppUtil = new PushUtil();
            xmppUtil.context = context;
        }
        return xmppUtil;
    }

    public int getIcon() {
        return icon;
    }

    public String getAppName() {
        return appName;
    }

    public Intent getDefaultIntent() {
        return defaultIntent;
    }

    public PushUtil setDefaultIntentInfo(Activity intentActivty, int icon) {
        this.icon = icon;
        appName = getMyAppName(intentActivty);
        defaultIntent = intentActivty.getIntent();
        return this;
    }

    private String getMyAppName(Activity intentActivty) {
        String appName = "";
        try {
            ApplicationInfo applicationInfo = intentActivty.getPackageManager().getApplicationInfo(intentActivty.getPackageName(), 0);
            appName = intentActivty.getPackageManager().getApplicationLabel(applicationInfo).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appName;
    }

    private ApplicationInfo getApplicationInfo(Activity intentActivty) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = intentActivty.getPackageManager().getApplicationInfo(intentActivty.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo;
    }

    public String getApiKey() {
        return apiKey;
    }

    public PushUtil setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getUseName() {
        return useName;
    }

    public PushUtil setUseName(String useName) {
        this.useName = useName;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public PushUtil setIp(String ip, int port) {
        this.ip = ip;
        this.port = port;
        return this;
    }

    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public ServiceManager startService() {
        serviceManager = new ServiceManager(context);
        serviceManager.startService();
        return serviceManager;
    }

    public void stopService() {
        if (serviceManager != null) {
            serviceManager.stopService();
        }
    }

    public PushUtil setOnPushListener(OnPushListener onPushListener) {
        this.onPushListener = onPushListener;
        return this;
    }

    public OnPushListener getOnPushListener() {
        return onPushListener;
    }
}