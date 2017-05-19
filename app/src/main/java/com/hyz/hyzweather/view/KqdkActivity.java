package com.hyz.hyzweather.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.hyz.hyzweather.R;
import com.hyz.hyzweather.util.GlideImageLoader;
import com.hyz.hyzweather.util.ImagePickerAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class KqdkActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{

    private String TAG = KqdkActivity.class.getCanonicalName();


    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;

    private final int SEND_TIME = 99;
    private final int ADDRESS = 98;
    String mHour;
    String mMinute;
    String mSecond;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数
    ImagePickerAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private final int NEED_CAMERA = 200;
    private final int BAIDU_LBS = 100;

    @BindView(R.id.take_photo)
    ImageView btnPhoto;
    @BindView(R.id.image_pic)
    ImageView imagePic;

    String newTime;
    String picString = "";//图片地址

    @BindView(R.id.position_tv)
    TextView positionText;
    private String mCity, mDistrict, mStreet = "";
    String address;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_TIME:
                    setTime();
                    mHandler.postDelayed(runnable, 998);
                    break;
                case ADDRESS:
                    positionText.setText(address);
                    break;
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(SEND_TIME);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kqdk);

        initImagePicker();
        //初始化数据
        initView();


    }

    private void initView() {
        setTime();
        mHandler.postDelayed(runnable, 998);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case NEED_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    UploadPicHelper.showPicDialog(this);
                    selectImage();
                    toast("=======");
                } else {
                    toast("请打开相机权限");
                }
                break;
        }
    }
    public void selectImage( ) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(true);   //多选
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setSelectLimit(9);    //最多选择9张
        imagePicker.setCrop(false);       //不进行裁剪
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }

    @OnClick({R.id.take_photo, R.id.btn_tjkq})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.take_photo:
                //检测是否有相机和读写文件权限
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    mActivity.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, NEED_CAMERA);
                } else {
                   selectImage();
                }
                break;
            case R.id.btn_tjkq:
//                if ("".equals(etContent.getText().toString())) {
//                    toast("请输入备注");
//                    return;
//                } else if ("".equals(picString)) {
//                    toast("请拍摄照片");
//                    return;
//                }
                sendData();
                break;

        }

    }

    private void sendData() {

        HashMap<String, String> params = new HashMap<>();

//        params.put("start_memo", etContent.getText().toString());   //  备注
//        params.put("start_site", positionText.getText().toString());    //地址
        params.put("attendance_pic", picString);



    }





    public void setTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat(" HH:mm:ss  ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        newTime = formatter.format(curDate);
        Log.i(TAG, "str====" + newTime.toString());
        String[] stime = newTime.split(":", 3);

        mHour = stime[0].substring(stime[0].length() - 2, stime[0].length());
        mMinute = stime[1];
        mSecond = stime[2];

        tvDay.setText(stime[0].substring(0, stime[0].length() - 2));
        tvHour.setText(mHour);
        tvMinute.setText(mMinute);
        tvSecond.setText(mSecond);
    }


    @Override
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


}
