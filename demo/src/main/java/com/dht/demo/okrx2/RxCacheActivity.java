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
package com.dht.demo.okrx2;

import android.os.Bundle;
import android.view.View;

import com.dht.demo.R;
import com.dht.demo.base.BaseRxDetailActivity;
import com.dht.demo.utils.Urls;
import com.dht.okgo.OkGo;
import com.dht.okgo.cache.CacheMode;
import com.dht.okgo.convert.StringConvert;
import com.dht.okgo.model.Response;
import com.dht.okrx2.adapter.ObservableResponse;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：dhtyogor（dht）Github地址：https://github.com/dhtyogor
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class RxCacheActivity extends BaseRxDetailActivity {

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rx_cache);
        ButterKnife.bind(this);
        setTitle("使用缓存");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        dispose();
    }

    @OnClick(R.id.cache)
    public void cache(View view) {

        // 详细看文档： https://github.com/dhtyogor/okhttp-OkGo/wiki/OkRx

        OkGo.<String>post(Urls.URL_METHOD)//
                .headers("aaa", "111")//
                .params("bbb", "222")//
                .cacheKey("rx_cache")              //这里完全同okgo的配置一样
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //这里完全同okgo的配置一样
                .converter(new StringConvert())//
                .adapt(new ObservableResponse<String>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }
}
