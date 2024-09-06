package com.app.aoacore.services

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.app.aoacore.R

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
                    val appId = bundle.getString("appId") ?: ""
                    appId.ifEmpty {
                        Log.d(
                            "CoreService",
                            "AppsOnAirCore: " + context.getString(R.string.error_add_app_id)
                        )
                        ""
                    }
                } else {
                    Log.d(
                        "CoreService",
                        "AppsOnAirCore: " + context.getString(R.string.error_add_meta_data)
                    )
                    ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(
                    "CoreService",
                    "AppsOnAirCore: " + context.getString(R.string.error_something_wrong)
                )
                ""
            }
        }
    }
}