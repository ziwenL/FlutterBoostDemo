package com.ziwenl.androiddemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idlefish.flutterboost.interfaces.IFlutterViewContainer
import com.ziwenl.androiddemo.router.PageRouter
import com.ziwenl.androiddemo.router.RouterKey
import com.ziwenl.androiddemo.router.RouterPath
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_FLUTTER = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_main.setOnClickListener {
            //启动 Flutter 页面
            PageRouter.openPageByUrl(this, RouterPath.Main.PAGE_MAIN)
            tv_result_data.text = ""
        }

        btn_start_has_result.setOnClickListener {
            //无传参启动 Flutter 页面，并接收 Flutter 页面回调数据
            PageRouter.openPageByUrl(
                this,
                RouterPath.Test.PAGE_HAS_RESULT,
                requestCode = REQUEST_CODE_FLUTTER
            )
            tv_result_data.text = ""
        }

        btn_start_has_params.setOnClickListener {
            //传参启动 Flutter 页面
            val params = mutableMapOf<String, Any>()
            params.put(RouterKey.Test.HasParamsActivity.EXTRA_KEY_ID, 452713L)
            params.put(RouterKey.Test.HasParamsActivity.EXTRA_KEY_GROUP, "小学生")
            PageRouter.openPageByUrl(
                this,
                RouterPath.Test.PAGE_HAS_PARAMS,
                requestParams = params
            )
            tv_result_data.text = ""
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_FLUTTER) {
            if (data != null && data.extras != null) {
                //FlutterBoost 返回的数据 key 固定是 IFlutterViewContainer.RESULT_KEY ，value 为 MutableMap
                val resultDataMap: MutableMap<Any, Any> =
                    data.extras?.get(IFlutterViewContainer.RESULT_KEY) as MutableMap<Any, Any>
                val name = resultDataMap[RouterKey.Test.HasResultActivity.EXTRA_KEY_NAME].toString()
                val age = resultDataMap[RouterKey.Test.HasResultActivity.EXTRA_KEY_AGE] as Int
                tv_result_data.text = String.format("Flutter 页面回调数据：姓名：$name，年龄：$age")
            }
        }
    }
}