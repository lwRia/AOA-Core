package com.app.aoacore.services

import android.content.Context
import android.content.pm.PackageManager

class CoreService {
    companion object {
        @JvmStatic
        fun getAppId(context: Context): String {
            return try {
                val appInfo = context.packageManager.getApplicationInfo(
                    context.packageName, PackageManager.GET_META_DATA
                )
                val bundle = appInfo.metaData
                if (bundle != null) {
                    bundle.getString("appId") ?: ""
                } else {
                    ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }
}