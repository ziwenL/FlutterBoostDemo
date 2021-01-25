package com.ziwenl.androiddemo.router

/**
 * PackageName : com.ziwenl.androiddemo.router
 * Author : Ziwen Lan
 * Date : 2021/1/20
 * Time : 10:57
 * Introduction : Flutter / Native 页面路由地址
 * 路由地址应与 Flutter 模块中设定的一致
 * 按模块划分
 */
class RouterPath {
    companion object {
        const val NATIVE = "native"
        const val FLUTTER = "flutter"

        //路由协议
        /**
         * 指向 Native 页面
         */
        const val PATH_TYPE_NATIVE = "$NATIVE://";

        /**
         * 指向 Flutter 页面
         */
        const val PATH_TYPE_FLUTTER = "$FLUTTER://";
    }

    /**
     * 测试用页面
     */
    object Test {
        private const val TEST_NATIVE = "${PATH_TYPE_NATIVE}/test"
        private const val TEST_FLUTTER = "${PATH_TYPE_FLUTTER}/test"

        const val PAGE_HAS_RESULT = "$TEST_FLUTTER/has_result"

        const val PAGE_HAS_PARAMS = "$TEST_FLUTTER/has_params"

        /**
         * 带回调的 native 页面
         */
        const val PAGE_NATIVE_HAS_RESULT = "$TEST_NATIVE/has_result"
    }

    /**
     * 基础页面
     */
    object Base {
        private const val TEST_NATIVE = "${PATH_TYPE_NATIVE}/test"
        private const val TEST_FLUTTER = "${PATH_TYPE_FLUTTER}/test"

    }

    /**
     * 主页面
     */
    object Main {
        private const val MAIN_NATIVE = "${PATH_TYPE_NATIVE}/main"
        private const val MAIN_FLUTTER = "${PATH_TYPE_FLUTTER}/main"
        const val PAGE_MAIN = "$MAIN_FLUTTER/main"
    }

    /**
     * 首页
     */
    object Home {
        private const val HOME_NATIVE = "${PATH_TYPE_NATIVE}/home"
        private const val HOME_FLUTTER = "${PATH_TYPE_FLUTTER}/home"

    }

    /**
     * 我的
     */
    object Mine {
        private const val MINE_NATIVE = "${PATH_TYPE_NATIVE}/mine"
        private const val MINE_FLUTTER = "${PATH_TYPE_FLUTTER}/mine"

    }
}