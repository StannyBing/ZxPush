package com.stanny.zxpushdemo

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.android.xmpp.notification.PushUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var permissions = arrayOf(Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PushUtil.getInstance(this)
                .setApiKey("f84ca4bfd615132356a909b6d2cc26d7")
                .setUseName("xb")
                .setIp("118.178.194.75", 5222)
                .setDefaultIntentInfo(this, R.mipmap.ic_launcher_round)
//                .setOnPushListener({
//                    Toast.makeText(this, it.notificationMessage, Toast.LENGTH_SHORT).show()
//                })
                .startService()
        //setDefaultIntentInfo设置默认处理方式
        //setOnPushListener设置自行处理方式
        //setDefaultIntentInfo和setOnPushListener至少需要设置一个
        //都设置时，setOnPushListener优先
    }

    override fun onDestroy() {
        super.onDestroy()
        PushUtil.getInstance(this).stopService()
    }
}
