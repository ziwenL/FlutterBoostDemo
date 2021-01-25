package com.ziwenl.androiddemo

import android.content.Intent
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import com.idlefish.flutterboost.interfaces.IFlutterViewContainer

/**
 * PackageName : com.ziwenl.androiddemo
 * Author : Ziwen Lan
 * Date : 2021/1/18
 * Time : 17:12
 * Introduction :
 */
class FlutterPageActivity : BoostFlutterActivity() {

    /**
     *
     * 返回需要传递给 FlutterPage 的数据
     */
    override fun getContainerUrlParams(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["extra_key_test"] = "传参"
        return map
    }

    /**
     * 返回目标 Flutter 页面 url ,用来指定 FlutterPage
     */
    override fun getContainerUrl(): String {
        return "router_path_flutter_page"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val rlt =
                data.getSerializableExtra(IFlutterViewContainer.RESULT_KEY)
            if (rlt is Map<*, *>) {
                //得到的返回数据
                var result = rlt as Map<String, Any>?
            }
        }
    }
}