package com.lightheart.reslib.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.lightheart.reslib.R;
import com.lightheart.reslib.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author LiaoHui
 * @date 2018/12/20
 * @desc
 */
public class TestView extends RelativeLayout {
    private Context mContext;
    @BindView(R2.id.back_view)
    View imageView;

    public TestView(Context context) {
        super(context);
        initView(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.lib_res_view_image, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    @OnClick(R2.id.back_view)
    public void onViewClick(View view) {
        imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.design_default_color_primary));
    }
}
