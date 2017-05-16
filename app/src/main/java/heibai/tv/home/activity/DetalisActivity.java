package heibai.tv.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import heibai.tv.R;

/**
 * Created by Ozawa on 2017/3/9.
 */
public class DetalisActivity extends Activity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        url="http://upfile.asqql.com/2009pasdfasdfic2009s305985-ts/2017-1/20171282142147675.gif";

        //anim_flag_greenland
        init();
    }

    private void init(){
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageview2);
        //加载网络图片 url=图片地址
        //Glide.with(DetalisActivity.this).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);

        Glide.with(this).load(url).asGif().into(imageView);

//        Glide.with(getBaseContext())
//                .load(R.mipmap.anim_flag_greenland)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.NONE )
//                .error(getResources().getDrawable(R.mipmap.anim_flag_greenland))
//                .into(new GlideDrawableImageViewTarget(imageView2,3).getView());

        //监听执行次数 GlideDrawableImageViewTarget（Imageview,执行次数）；
        Glide.with(this).load(R.mipmap.anim_flag_greenland).into(new GlideDrawableImageViewTarget(imageView2, 2));

    }
}
