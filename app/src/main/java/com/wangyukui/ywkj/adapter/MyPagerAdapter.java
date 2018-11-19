package com.wangyukui.ywkj.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangyukui.R;
import com.wangyukui.ywkj.content.ContentUrl;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2017/4/12.
 */
public class MyPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<String> imageArray;
    String urlImage;

    public MyPagerAdapter(Context context, List<String> imageArray) {
        this.context = context;
        this.imageArray = imageArray;
    }

    @Override
    public int getCount() {
        if (imageArray.size() == 0) {
            return 3;
        }
        return imageArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final ImageView imageView = (ImageView) View.inflate(context, R.layout.viewpager_item, null);
        if (imageArray.size() == 0) {

        } else {
            urlImage = (String) imageArray.get(position);
        }
        Picasso.with(context).load(ContentUrl.BASE_ICON_URL + urlImage).placeholder(R.mipmap.banner).fit()
                .error(R.mipmap.banner).into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
