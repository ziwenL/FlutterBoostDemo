package com.ziwenl.androiddemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import com.ziwenl.androiddemo.router.PageRouter
import com.ziwenl.androiddemo.router.RouterKey
import com.ziwenl.androiddemo.router.RouterPath
import kotlinx.android.synthetic.main.activity_native.*

/**
 * PackageName : com.ziwenl.androiddemo
 * Author : Ziwen Lan
 * Date : 2021/1/21
 * Time : 10:25
 * Introduction : 带回调的原生页面
 */
class NativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)
        supportActionBar?.setTitle("AndroidActivity")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null) {
            val requestParams =
                (intent.extras?.get(PageRouter.EXTRA_PARAMS) as BoostFlutterActivity.SerializableMap).map
            val text = requestParams[RouterKey.Test.HasRequestParams.EXTRA_KEY_NATIVE].toString()
            tv_content.text = String.format("This is flutter page request params：\n$text")
        }

        btn_close.setOnClickListener {
            val params = mutableMapOf<String, Any>()
            params[RouterKey.Test.HasResultActivity.EXTRA_KEY_AGE] = 18
            params[RouterKey.Test.HasResultActivity.EXTRA_KEY_NAME] = "陆先生"
            PageRouter.finishAndResult(this, params)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}