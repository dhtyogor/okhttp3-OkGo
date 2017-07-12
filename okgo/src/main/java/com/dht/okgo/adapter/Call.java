/*
 * Copyright 2016 dhtyogor(dht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dht.okgo.adapter;

import com.dht.okgo.request.base.Request;
import com.dht.okgo.callback.Callback;
import com.dht.okgo.model.Response;

/**
 * ================================================
 * 作    者：dhtyogor（dht）Github地址：https://github.com/dhtyogor
 * 版    本：1.0
 * 创建日期：2016/9/11
 * 描    述：请求的包装类
 * 修订历史：
 * ================================================
 */
public interface Call<T> {
    /** 同步执行 */
    Response<T> execute() throws Exception;

    /** 异步回调执行 */
    void execute(Callback<T> callback);

    /** 是否已经执行 */
    boolean isExecuted();

    /** 取消 */
    void cancel();

    /** 是否取消 */
    boolean isCanceled();

    Call<T> clone();

    Request getRequest();
}
