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
package com.dht.okrx.subscribe;

import com.dht.okgo.adapter.Call;
import com.dht.okgo.model.Progress;
import com.dht.okgo.request.base.Request;
import com.dht.okgo.callback.Callback;
import com.dht.okgo.model.Response;

import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/**
 * ================================================
 * 作    者：dhtyogor（dht）Github地址：https://github.com/dhtyogor
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public final class CallEnqueueOnSubscribe<T> implements OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    public CallEnqueueOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    @Override
    public void call(final Subscriber<? super Response<T>> subscriber) {
        // Since Call is a one-shot type, clone it for each new subscriber.
        Call<T> call = originalCall.clone();
        final CallArbiter<T> arbiter = new CallArbiter<>(call, subscriber);
        subscriber.add(arbiter);
        subscriber.setProducer(arbiter);

        call.execute(new Callback<T>() {
            @Override
            public T convertResponse(okhttp3.Response response) throws Throwable {
                // okrx 使用converter转换，不需要这个解析方法
                return null;
            }

            @Override
            public void onStart(Request<T, ? extends Request> request) {
            }

            @Override
            public void onSuccess(Response<T> response) {
                arbiter.emitNext(response);
            }

            @Override
            public void onCacheSuccess(Response<T> response) {
                arbiter.emitNext(response);
            }

            @Override
            public void onError(Response<T> response) {
                Throwable throwable = response.getException();
                Exceptions.throwIfFatal(throwable);
                arbiter.emitError(throwable);
            }

            @Override
            public void onFinish() {
                arbiter.emitComplete();
            }

            @Override
            public void uploadProgress(Progress progress) {
            }

            @Override
            public void downloadProgress(Progress progress) {
            }
        });
    }
}
