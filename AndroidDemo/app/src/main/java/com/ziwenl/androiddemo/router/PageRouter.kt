package com.ziwenl.androiddemo.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import com.idlefish.flutterboost.interfaces.IFlutterViewContainer
import com.ziwenl.androiddemo.NativeActivity
import java.io.Serializable


/**
 * PackageName : com.ziwenl.androiddemo.router
 * Author : Ziwen Lan
 * Date : 2021/1/18
 * Time : 14:38
 * Introduction : 基于 FlutterBoost 的 Flutter 路由管理工具类
 */
open class PageRouter {

    companion object {

        private val TAG = PageRouter::class.java.simpleName

        private const val DEFAULT_REQUEST_CODE = -1

        /**
         * 由 Flutter 页面发起的请求数据的统一 key
         * value ：BoostFlutterActivity.SerializableMap
         */
        const val EXTRA_PARAMS = "params"

        /**
         * 根据路由地址跳转 Native 或 Flutter 页面
         * 当上下文对象为 Activity 时，才能接收页面的回调数据
         * @param context : 上下文对象
         * @param url : 路由地址
         * @param requestParams : 传参
         * @param requestCode : 请求码
         */
        fun openPageByUrl(
            context: Context,
            url: String,
            requestParams: MutableMap<String, Any>? = null,
            requestCode: Int = DEFAULT_REQUEST_CODE
        ): Boolean {
            try {
                val pageType = url.split("://")[0]
                if (pageType == RouterPath.NATIVE) {
                    //跳转 Native 页面 -- 根据路由地址判断启动 Activity
                    if (url == RouterPath.Test.PAGE_NATIVE_HAS_RESULT) {
                        //TODO 配合阿里的 ARouter 来实现"基于路由地址跳转原生页面"效果更佳
                        val params = BoostFlutterActivity.SerializableMap()
                        params.map = requestParams ?: mutableMapOf()
                        val intent = Intent(context, NativeActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable(EXTRA_PARAMS, params)
                        intent.putExtras(bundle)
                        if (context is Activity) {
                            context.startActivityForResult(intent, requestCode)
                        } else {
                            context.startActivity(intent)
                        }
                    }
                } else if (pageType == RouterPath.FLUTTER) {
                    //跳转 Flutter 页面
                    val intent =
                        BoostFlutterActivity.withNewEngine().url(url)
                            .params(requestParams ?: mutableMapOf())
                            .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque)
                            .build(context)
                    if (context is Activity) {
                        context.startActivityForResult(intent, requestCode)
                    } else {
                        context.startActivity(intent)
                    }
                } else {
                    Log.e(TAG, "openPageByUrl Error !!! Router path type error !")
                    return false
                }
                return true
            } catch (e: Exception) {
                Log.e(TAG, "openPageByUrl Error !!! e.message = " + e.message)
                return false
            }
        }

        /**
         * 关闭当前页面并返回数据至 Flutter 页面
         * @param resultData 返回数据
         */
        fun finishAndResult(activity: Activity, resultData: MutableMap<String, Any>? = null) {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putSerializable(
                IFlutterViewContainer.RESULT_KEY,
                (resultData ?: mutableMapOf()) as Serializable
            )
            intent.putExtras(bundle)
            activity.setResult(0, intent)
            activity.finish()
        }
    }
}
