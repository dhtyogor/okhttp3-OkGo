
Android Studio用户

> 一般来说，只需要添加第一个okgo的核心包即可，其余的三个库根据自己的需要选择添加！！！

```java
//必须使用
compile 'com.github.dhtyogor.okhttp3-OkGo:okgo:1.0'

//以下三个选择添加，okrx和okrx2不能同时使用
compile 'com.github.dhtyogor.okhttp3-OkGo:okrx:1.0'
compile 'com.github.dhtyogor.okhttp3-OkGo:okrx2:1.0'
compile 'com.github.dhtyogor.okhttp3-OkGo:okserver:1.0'
```

## 混淆
okgo, okrx, okrx2, okserver 所有代码均可以混淆,但是由于底层使用的是 okhttp,它不能混淆,所以只需要添加以下混淆代码就可以了
```java
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
```

当然如果你确实不需要混淆okgo的代码,可以继续添加以下代码
```java
#okgo
-dontwarn com.lzy.okgo.**
-keep class com.lzy.okgo.**{*;}

#okrx
-dontwarn com.lzy.okrx.**
-keep class com.lzy.okrx.**{*;}

#okrx2
-dontwarn com.lzy.okrx2.**
-keep class com.lzy.okrx2.**{*;}

#okserver
-dontwarn com.lzy.okserver.**
-keep class com.lzy.okserver.**{*;}
```

## Licenses
```
 Copyright 2016 dhtyogor(dht)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

