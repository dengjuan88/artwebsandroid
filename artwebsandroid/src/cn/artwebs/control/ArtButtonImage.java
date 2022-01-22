package cn.artwebs.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.artwebs.R;

/**
 * Created by rsmac on 15/1/29.
 */
public class ArtButtonImage extends LinearLayout  {
    private LinearLayout rootLayout;
    private ImageView icoImage;
    private TextView titleText;
    private Context context;

    public ImageView getIcoImage() {
        return icoImage;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public ArtButtonImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.artbuttonimage, this);
        rootLayout= (LinearLayout) findViewById(R.id.rootLayout);
        icoImage= (ImageView) findViewById(R.id.icoImage);
        titleText= (TextView) findViewById(R.id.titleText);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArtButtonImage);
        if(1==array.getInt(R.styleable.ArtButtonImage_orientation, 0)){
            rootLayout.setOrientation(LinearLayout.VERTICAL);
        }

        int mTextColor = array.getColor(R.styleable.ArtButtonImage_textColor,
                Color.BLACK);
        icoImage.setImageResource(array.getResourceId(R.styleable.ArtButtonImage_src,R.drawable.menu_default_icon));
        titleText.setTextColor(mTextColor);
        titleText.setText(array.getString(R.styleable.ArtButtonImage_text)==null?"名称":array.getString(R.styleable.ArtButtonImage_text));

        ViewGroup.LayoutParams imageParams=icoImage.getLayoutParams();
        imageParams.width=array.getDimensionPixelSize(R.styleable.ArtButtonImage_imageWidth, 30);
        imageParams.height=array.getDimensionPixelSize(R.styleable.ArtButtonImage_imageHeigth, 30);
        icoImage.setLayoutParams(imageParams);
        array.recycle();

    }



    public void setOnClickLinstener(OnClickListener listener){

        rootLayout.setOnClickListener(listener);
    }
}
