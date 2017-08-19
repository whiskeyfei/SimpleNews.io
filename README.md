# SimpleNews.io
[![Build Status](https://travis-ci.org/whiskeyfei/SimpleNews.io.svg?branch=master)](https://travis-ci.org/whiskeyfei/SimpleNews.io)[![](https://jitpack.io/v/whiskeyfei/SimpleNews.io.svg)](https://jitpack.io/#whiskeyfei/SimpleNews.io)

SimpleNews.io - A news Android App,based on the Material Design and RxJava 、MVP.Due to changes in the data request logic larger.so,I searate pull out the demo to update, in the original basis to modify the use of RxJava and MVP to rewirete the code starcture.

Original app is [SimpleNews](https://github.com/liuling07/SimpleNews),thanks for provide!
Chinese readme version [README_CN.md](/README_CN.md)

Change as follows:

- [x] change the data interface.
- [x] change switch fragment mechanism.
- [x] change mvc mechanism separate ui and logic.
- [x] clear the useless code and liarary and ui resourses.
- [ ] change construction for Android Component & Module
- [x] add BaseActivity for eventBus.
- [x] clear toolBar code.
- [x] add blog json.


### UI display

<img src="screenshot/simple_1.png" width=216/><img src="screenshot/simple_2.png" width=216/><img src="screenshot/simple_3.png" width=216/><img src="screenshot/simple_4.png" width=216/>

### Use open source libraries

Name | About
------- | -------
[todo-mvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)  | Google googlesamples MVP
[RxJava](https://github.com/ReactiveX/RxJava) | RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
[RxAndroid](https://github.com/ReactiveX/RxAndroid) | RxJava bindings for Android.
[Gson](https://github.com/google/gson) | A Java serialization/deserialization library to convert Java Objects into JSON and back.
[okhttp](https://github.com/square/okhttp) |  An HTTP+HTTP/2 client for Android and Java applications.
[circleimageview](https://github.com/hdodenhof/CircleImageView) | A circular ImageView for Android.
[glide](https://github.com/bumptech/glide) | An image loading and caching library for Android focused on smooth scrolling.
[MultiType](https://github.com/drakeet/MultiType) | An Android library to create multiple item types list views easily and flexibly.

### Reference

* [NotRxJava懒人专用指南](http://www.devtf.cn/?p=323)
* [深入浅出RxJava(二：操作符)](https://github.com/lzyzsd/Awesome-RxJava?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io)
* [给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_1)
* [android-architecture](https://github.com/googlesamples/android-architecture) 
* [todo-mvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 
* [todo-mvp-rxjava](https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/)
* [android-architecture](https://github.com/googlesamples/android-architecture)
* [gank.io](http://gank.io/)

# License
Copyright 2015 liuling <lauren.liuling@gmail.com><br/>
Copyright 2015 whiskeyfei <whiskeyfei@gmail.com><br/>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
