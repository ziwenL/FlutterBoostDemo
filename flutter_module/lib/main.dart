import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/router/RouterKey.dart';
import 'router/RouterPath.dart';

void main() => runApp(new FlutterPage());

class FlutterPage extends StatefulWidget {
  @override
  createState() => _FlutterPageSate();
}

class _FlutterPageSate extends State<FlutterPage> {
  @override
  void initState() {
    super.initState();
    //注册 FlutterBoost
    FlutterBoost.singleton.registerPageBuilders({
      //注册 Flutter 页面路由 url
      Main.PAGE_MAIN: (pageUrl, params, _) {
        return MainWidget();
      },
      Test.PAGE_HAS_RESULT: (pageUrl, params, _) {
        return HasResultWidget();
      },
      Test.PAGE_HAS_PARAMS: (pageName, params, _) {
        return HasParamsWidget(params);
      },
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'FlutterPage',
      //初始化 FlutterBoost
      builder: FlutterBoost.init(postPush: onRoutePushed),
      home: Container(),
    );
  }

  void onRoutePushed(String pageName, String uniqueId, Map params, Route route,
      Future future) {
    debugPrint("onRoutePushed pageName:$pageName,uniqueId:$uniqueId");
  }
}

//主页面
class MainWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MainWidgetState();
  }
}

class _MainWidgetState extends State<MainWidget> {
  String resultText = "result data :";

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('FlutterMainActivity'),
          leading: IconButton(
            icon: Icon(Icons.arrow_back, color: Colors.white),
            onPressed: () {
              //FlutterBoost 关闭当前页面
              FlutterBoost.singleton.closeCurrent();
            },
          ),
        ),
        body: Column(children: <Widget>[
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text('This is main flutter activity',
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          ),
          Container(
              margin: const EdgeInsets.only(top: 10.0),
              alignment: AlignmentDirectional.center,
              child: RaisedButton(
                child: Text('Open flutter page get result'),
                color: Colors.yellow,
                onPressed: () {
                  //启动 Flutter 页面带回调数据
                  FlutterBoost.singleton
                      .open(Test.PAGE_HAS_RESULT)
                      .then((Map<dynamic, dynamic> value) {
                    //回调监听
                    setState(() {
                      resultText = 'Open flutter page result data :\n $value';
                    });
                  });
                },
              )),
          Container(
              margin: const EdgeInsets.only(top: 10.0),
              alignment: AlignmentDirectional.center,
              child: RaisedButton(
                child: Text('Open native page get result'),
                color: Colors.yellow,
                onPressed: () {
                  //启动 Native 页面带回调数据
                  FlutterBoost.singleton
                      .open(Test.PAGE_NATIVE_HAS_RESULT)
                      .then((Map<dynamic, dynamic> value) {
                    //回调监听
                    setState(() {
                      resultText = 'Open flutter page result data :\n $value';
                    });
                  });
                },
              )),
          Container(
              margin: const EdgeInsets.only(top: 10.0),
              alignment: AlignmentDirectional.center,
              child: RaisedButton(
                child: Text('Open native page has request params'),
                color: Colors.yellow,
                onPressed: () {
                  //启动 Native 页面带请求参数和回调数据
                  Map<String, dynamic> requestParams = Map();
                  requestParams[HasRequestParamsNative.EXTRA_KEY_NATIVE] = "Hello native";
                  FlutterBoost.singleton
                      .open(Test.PAGE_NATIVE_HAS_RESULT,
                          urlParams: requestParams)
                      .then((Map<dynamic, dynamic> value) {
                    //回调监听
                    setState(() {
                      resultText = 'Open flutter page result data :\n $value';
                    });
                  });
                },
              )),
          Container(
            margin: const EdgeInsets.only(top: 10.0),
            alignment: AlignmentDirectional.center,
            child: Text(resultText),
          ),
        ]));
  }
}

//普通页面（有回调）
class HasResultWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('HasResultActivity'),
          leading: IconButton(
            icon: Icon(Icons.arrow_back, color: Colors.white),
            onPressed: () {
              //FlutterBoost 关闭当前页面
              FlutterBoost.singleton.closeCurrent();
            },
          ),
        ),
        body: Column(children: <Widget>[
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text('This is a flutter activity',
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          ),
          Container(
              margin: const EdgeInsets.only(top: 10.0),
              alignment: AlignmentDirectional.center,
              child: RaisedButton(
                child: Text('Close and result data'),
                color: Colors.yellow,
                onPressed: () {
                  // FlutterBoost 关闭当前页面并回调数据给原生页面
                  var resultMap = Map<String, dynamic>();
                  resultMap[HasResult.EXTRA_KEY_NAME] = "张先生";
                  resultMap[HasResult.EXTRA_KEY_AGE] = 26;
                  FlutterBoost.singleton.closeCurrent(result: resultMap);
                },
              )),
        ]));
  }
}

//普通页面（有传参）
class HasParamsWidget extends StatelessWidget {
  final Map params;

  HasParamsWidget(this.params);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('HasParamsActivity'),
          leading: IconButton(
            icon: Icon(Icons.arrow_back, color: Colors.white),
            onPressed: () {
              FlutterBoost.singleton.closeCurrent();
            },
          ),
        ),
        body: Column(children: <Widget>[
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text('This is a flutter activity',
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          ),
          Container(
            margin: const EdgeInsets.only(top: 40.0),
            child: Text(
                'Native request params :\n ID = ' +
                    params[HasParams.EXTRA_KEY_ID].toString() +
                    '  GROUP = ' +
                    params[HasParams.EXTRA_KEY_GROUP].toString(),
                style: TextStyle(fontSize: 28.0, color: Colors.blue)),
            alignment: AlignmentDirectional.center,
          )
        ]));
  }
}
