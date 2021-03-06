package com.example.jingbin.cloudreader.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseActivity;
import com.example.jingbin.cloudreader.databinding.ActivityNavAboutBinding;
import com.example.jingbin.cloudreader.utils.PerfectClickListener;
import com.example.jingbin.cloudreader.utils.ToastUtil;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;

public class NavAboutActivity extends BaseActivity<ActivityNavAboutBinding> {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nav_about);
    showContentView();
    setTitle("关于云阅");

    // 直接写在布局文件里会很耗内存
    Glide.with(this).load(R.drawable.ic_cloudreader).into(bindingView.ivIcon);
    bindingView.tvGankio.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
    bindingView.tvGankio.getPaint().setAntiAlias(true); // 抗锯齿
    bindingView.tvDouban.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
    bindingView.tvDouban.getPaint().setAntiAlias(true); // 抗锯齿

    initListener();
  }

  private void initListener() {
    bindingView.tvGankio.setOnClickListener(listener);
    bindingView.tvDouban.setOnClickListener(listener);
    //        bindingView.tvAboutStar.setOnClickListener(listener);
    bindingView.tvAboutStar.setOnClickListener(
        new PerfectClickListener() {
          @Override
          protected void onNoDoubleClick(View v) {
            Uri issuesUrl = Uri.parse("https://github.com/gu0-kim/CloudReader-master");
            Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
            startActivity(intent);
          }
        });

    //        bindingView.tvFunction.setOnClickListener(listener);
    bindingView.tvNewVersion.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            ToastUtil.showToast("已是最新版本~");
          }
        });
  }

  private PerfectClickListener listener =
      new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
          String url = null;
          switch (v.getId()) {
            case R.id.tv_gankio:
              url = "http://gank.io/api";
              break;
            case R.id.tv_douban:
              url = "https://developers.douban.com/wiki/?title=terms";
              break;
            case R.id.tv_about_star:
              url = "https://github.com/gu0-kim/CloudReader-master";
              break;
          }
          WebViewActivity.loadUrl(v.getContext(), url, "加载中...");
        }
      };

  public static void start(Context mContext) {
    Intent intent = new Intent(mContext, NavAboutActivity.class);
    mContext.startActivity(intent);
  }
}
