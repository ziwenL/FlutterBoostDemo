package com.ziwenl.androiddemo.router

/**
 * PackageName : com.ziwenl.androiddemo.router
 * Author : Ziwen Lan
 * Date : 2021/1/20
 * Time : 17:39
 * Introduction :
 */
class RouterKey {
    class Test {
        object HasResultActivity {
            const val EXTRA_KEY_NAME = "name"
            const val EXTRA_KEY_AGE = "age"
        }

        object HasParamsActivity {
            const val EXTRA_KEY_ID = "id"
            const val EXTRA_KEY_GROUP = "group"
        }

        object HasRequestParams {
            const val EXTRA_KEY_NATIVE = "native"
        }
    }
}