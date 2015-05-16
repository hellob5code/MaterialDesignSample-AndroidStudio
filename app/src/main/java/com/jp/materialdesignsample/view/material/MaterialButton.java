package com.jp.materialdesignsample.view.material;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.jp.materialdesignsample.utils.FontUtils;

public class MaterialButton extends Button {
    private static final String FONT_PATH = "fonts/roboto_medium.ttf";

    public MaterialButton(Context context) {
        super(context);
        initView(context);
    }

    public MaterialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        Typeface typeface = FontUtils.loadFont(context, FONT_PATH);
        setTypeface(typeface);
    }
}
