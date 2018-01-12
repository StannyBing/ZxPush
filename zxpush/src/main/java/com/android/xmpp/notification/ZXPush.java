package com.android.xmpp.notification;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

public class ZXPush {

    public static ZXPush xmppUtil;

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

    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    @SuppressLint("MissingPermission")
    private void setDeviceId() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            this.deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public static ZXPush getInstance(Context context) {
        if (xmppUtil == null) {
            xmppUtil = new ZXPush();
            xmppUtil.context = context;
            xmppUtil.setDeviceId();
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

    public ZXPush setDefaultIntentInfo(Activity intentActivty, int icon) {
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

    public ZXPush setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getUseName() {
        return useName;
    }

    public ZXPush setUseName(String useName) {
        this.useName = useName;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public ZXPush setIp(String ip, int port) {
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

    public ZXPush setOnPushListener(OnPushListener onPushListener) {
        this.onPushListener = onPushListener;
        return this;
    }

    public OnPushListener getOnPushListener() {
        return onPushListener;
    }
}
