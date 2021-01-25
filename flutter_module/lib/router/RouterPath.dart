//路由协议
class RouterPath {
  //指向 Native 页面
  static const PATH_TYPE_NATIVE = "native://";
  //指向 Flutter 页面
  static const PATH_TYPE_FLUTTER = "flutter://";
}

// 测试用页面
class Test {
  static const _TEST_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/test";
  static const _TEST_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/test";

  static const String PAGE_HAS_RESULT = "$_TEST_FLUTTER/has_result";
  static const String PAGE_HAS_PARAMS = "$_TEST_FLUTTER/has_params";

  // 带回调的 native 页面
  static const PAGE_NATIVE_HAS_RESULT = "$_TEST_NATIVE/has_result";
}

// 基础页面
class Base {
  static const _BASE_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/base";
  static const _BASE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/base";

  static const String PAGE_NORMAL = "$_BASE_FLUTTER/normal";
}

// 主页面
class Main {
  static const _MAIN_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/main";
  static const _MAIN_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/main";

  static const String PAGE_MAIN = "$_MAIN_FLUTTER/main";
}

// 首页
class Home {
  static const _HOME_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/home";
  static const _HOME_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/home";

  static const String PAGE_HOME = "$_HOME_FLUTTER/home";
}

// 我的
class Mine {
  static const _MINE_NATIVE = "${RouterPath.PATH_TYPE_NATIVE}/mine";
  static const _MINE_FLUTTER = "${RouterPath.PATH_TYPE_FLUTTER}/mine";

  static const String PAGE_MINE = "$_MINE_FLUTTER/mine";
}
