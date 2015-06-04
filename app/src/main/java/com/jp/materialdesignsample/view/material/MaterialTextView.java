package com.jp.materialdesignsample.view.material;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.utils.FontUtils;

public class MaterialTextView extends TextView {
    private static final String FONT_PATH_ROBOTO_LIGHT = "fonts/roboto_light.ttf";
    private static final String FONT_PATH_ROBOTO_MEDIUM = "fonts/roboto_medium.ttf";
    private static final String FONT_PATH_ROBOTO_REGULAR = "fonts/roboto_regular.ttf";

    private static final int DISPLAY_TYPE_DISPLAY_4 = 1;
    private static final int DISPLAY_TYPE_DISPLAY_3 = 2;
    private static final int DISPLAY_TYPE_DISPLAY_2 = 3;
    private static final int DISPLAY_TYPE_DISPLAY_1 = 4;
    private static final int DISPLAY_TYPE_HEADLINE = 5;
    private static final int DISPLAY_TYPE_TITLE = 6;
    private static final int DISPLAY_TYPE_SUBHEAD = 7;
    private static final int DISPLAY_TYPE_BODY_2 = 8;
    private static final int DISPLAY_TYPE_BODY_1 = 9;
    private static final int DISPLAY_TYPE_CAPTION = 10;

    private Context mContext;

    public MaterialTextView(Context context) {
        super(context);
        initView(context);
    }

    public MaterialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        loadFont(context, attrs);
    }

    public MaterialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        loadFont(context, attrs);
    }

    protected void initView(Context context) {
        mContext = context;
    }

    private void loadFont(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MaterialTextView);
        int displayType = a.getInt(R.styleable.MaterialTextView_displayType, 0);
        a.recycle();

        setTypeface(getFontByDisplayType(displayType));
        if (displayType != 0) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, getFontSizeByDisplayType(displayType));
        }
    }

    private Typeface getFontByDisplayType(int displayType) {
        switch (displayType) {
            case DISPLAY_TYPE_DISPLAY_4:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_LIGHT);
            case DISPLAY_TYPE_DISPLAY_3:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_DISPLAY_2:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_DISPLAY_1:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_HEADLINE:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_TITLE:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_MEDIUM);
            case DISPLAY_TYPE_SUBHEAD:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_BODY_2:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_MEDIUM);
            case DISPLAY_TYPE_BODY_1:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_CAPTION:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            default:
                return FontUtils.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
        }
    }

    private float getFontSizeByDisplayType(int displayType) {
        switch (displayType) {
            case DISPLAY_TYPE_DISPLAY_4:
                return 112f;
            case DISPLAY_TYPE_DISPLAY_3:
                return 56f;
            case DISPLAY_TYPE_DISPLAY_2:
                return 45f;
            case DISPLAY_TYPE_DISPLAY_1:
                return 34f;
            case DISPLAY_TYPE_HEADLINE:
                return 24f;
            case DISPLAY_TYPE_TITLE:
                return 20f;
            case DISPLAY_TYPE_SUBHEAD:
                return 16f;
            case DISPLAY_TYPE_BODY_2:
                return 14f;
            case DISPLAY_TYPE_BODY_1:
                return 14f;
            case DISPLAY_TYPE_CAPTION:
                return 12f;
            default:
                return 14f;
        }
    }
}
