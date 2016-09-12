package com.yuyh.library.imgsel.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.yuyh.library.imgsel.R;
import com.yuyh.library.imgsel.bean.Image;
import com.yuyh.library.imgsel.common.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public class ImageListAdapter extends EasyRVAdapter<Image> {

    private boolean showCamera;
    private boolean mutiSelect;

    private ImgSelConfig config;
    private Context context;

    private List<Image> selectedImageList = new ArrayList<>();
    private OnItemClickListener listener;

    public ImageListAdapter(Context context, List<Image> list, ImgSelConfig config) {
        super(context, list, R.layout.item_img_sel, R.layout.item_img_sel_take_photo);
        this.context = context;
        this.config = config;
    }

    @Override
    protected void onBindData(final EasyRVHolder viewHolder, final int position, final Image item) {

        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int ret = listener.onClick(position, item);
                    if (ret == 1 && mutiSelect) { // 局部刷新
                        if (selectedImageList.contains(item)) {
                            viewHolder.setImageResource(R.id.ivPhotoCheaked, R.drawable.ic_checked);
                        } else {
                            viewHolder.setImageResource(R.id.ivPhotoCheaked, R.drawable.ic_uncheck);
                        }
                    }
                }
            }
        });

        if (position == 0 && showCamera) {
            ImageView iv = viewHolder.getView(R.id.ivTakePhoto);
            iv.setImageResource(R.drawable.ic_take_photo);
            return;
        }

        final ImageView iv = viewHolder.getView(R.id.ivImage);
        config.loader.displayImage(context, item.path, iv);

        if (mutiSelect) {
            viewHolder.setVisible(R.id.ivPhotoCheaked, true);
            if (selectedImageList.contains(item)) {
                viewHolder.setImageResource(R.id.ivPhotoCheaked, R.drawable.ic_checked);
            } else {
                viewHolder.setImageResource(R.id.ivPhotoCheaked, R.drawable.ic_uncheck);
            }
        } else {
            viewHolder.setVisible(R.id.ivPhotoCheaked, false);
        }
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    public void setMutiSelect(boolean mutiSelect) {
        this.mutiSelect = mutiSelect;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && showCamera) {
            return 1;
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public void select(Image image) {
        if (selectedImageList.contains(image)) {
            selectedImageList.remove(image);
        } else {
            selectedImageList.add(image);
        }
    }
}
