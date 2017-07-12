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
package com.dht.demo.supercache;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dht.demo.WebActivity;
import com.dht.demo.model.GankModel;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：dhtyogor（dht）Github地址：https://github.com/dhtyogor
 * 版    本：1.0
 * 创建日期：16/8/17
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NewsAdapter extends BaseQuickAdapter<GankModel> {

    public NewsAdapter(List<GankModel> data) {
        super(com.dht.demo.R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final GankModel model) {
        baseViewHolder.setText(com.dht.demo.R.id.title, model.desc)//
                .setText(com.dht.demo.R.id.desc, model.desc)//
                .setText(com.dht.demo.R.id.pubDate, model.publishedAt.toString())//
                .setText(com.dht.demo.R.id.source, model.source);

        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });

        NineGridView nineGrid = baseViewHolder.getView(com.dht.demo.R.id.nineGrid);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        if (model.images != null) {
            for (String image : model.images) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image);
                info.setBigImageUrl(image);
                imageInfo.add(info);
            }
        }
        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
    }
}
