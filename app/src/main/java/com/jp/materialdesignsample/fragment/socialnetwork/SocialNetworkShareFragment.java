package com.jp.materialdesignsample.fragment.socialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jp.materialdesignsample.R;
import com.jp.materialdesignsample.activity.navigationdrawer.base.BaseNavigationDrawerFragment;

public class SocialNetworkShareFragment extends BaseNavigationDrawerFragment implements View.OnClickListener {
    private TextView mImagePathText;
    private ImageView mSelectedImageView;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_social_network_share;
    }

    @Override
    protected void bindView(View rootView) {
        mImagePathText = (TextView) rootView.findViewById(R.id.image_path_text);
        mSelectedImageView = (ImageView) rootView.findViewById(R.id.selected_image_view);

        Button btnChooseImage = (Button) rootView.findViewById(R.id.choose_image_button);
        btnChooseImage.setOnClickListener(this);

        Button btnShareFacebook = (Button) rootView.findViewById(R.id.share_facebook_button);
        btnShareFacebook.setOnClickListener(this);

        Button btnShareTwitter = (Button) rootView.findViewById(R.id.share_twitter_button);
        btnShareTwitter.setOnClickListener(this);

        Button btnShareInstagram = (Button) rootView.findViewById(R.id.share_instagram_button);
        btnShareInstagram.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_image_button:
                showGallerySelection();
                break;
            case R.id.share_facebook_button:
                break;
            case R.id.share_twitter_button:
                break;
            case R.id.share_instagram_button:
                break;
            default:
                break;
        }
    }

    private void shareTwitter() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 101 && resultCode == Activity.RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                mImagePathText.setText(selectedImage.toString());
                mSelectedImageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void showGallerySelection() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 101);
    }
}
