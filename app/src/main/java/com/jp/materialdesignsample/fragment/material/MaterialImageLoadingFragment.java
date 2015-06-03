package com.jp.materialdesignsample.fragment.material;

import android.view.View;
import android.widget.Button;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;
import com.jp.materialdesignsample.view.material.MaterialImageView;

public class MaterialImageLoadingFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    private Button mLoadImageButton;
    private Button mClearImageButton;
    private MaterialImageView mMaterialImage;

    @Override
    protected String getToolbarTitle() {
        return "Image Loading Pattern";
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_material_image_loading;
    }

    @Override
    protected void bindView(View rootView) {
        mLoadImageButton = (Button) rootView.findViewById(R.id.load_image_button);
        mClearImageButton = (Button) rootView.findViewById(R.id.clear_image_button);
        mMaterialImage = (MaterialImageView) rootView.findViewById(R.id.material_loading_pattern_image);

        mLoadImageButton.setOnClickListener(this);
        mClearImageButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_image_button:
                mMaterialImage.setMaterialImageResource(R.drawable.image);
                break;
            case R.id.clear_image_button:
                mMaterialImage.setImageDrawable(null);
                break;
            default:
                break;
        }
    }
}
