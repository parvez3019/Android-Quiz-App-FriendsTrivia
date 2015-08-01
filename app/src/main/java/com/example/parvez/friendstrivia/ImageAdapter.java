package com.example.parvez.friendstrivia;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;






    public class ImageAdapter extends PagerAdapter {
    Context context;
    public static int character;



    private int[] GalImages = new int[] {
            R.drawable.chandler,
            R.drawable.joey,
            R.drawable.monica,
            R.drawable.phoebe,
            R.drawable.rachel,
            R.drawable.ross
    };
    ImageAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_padding_material);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(GalImages[position]);

        ((ViewPager) container).addView(imageView, 0);



            final int cPos = position;

            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    switch (cPos) {
                        case 0:
                            character = 1;
                            MainActivity.next();
                            Log.d("Tag", "1");
                            break;

                        case 1:
                            character = 2;
                            MainActivity.next();
                            Log.d("Tag", "2");
                            break;

                        case 2:
                            character = 3;
                            MainActivity.next();
                            Log.d("Tag", "3");
                            break;

                        case 3:
                            character = 4;
                            MainActivity.next();
                            Log.d("Tag", "4");
                            break;

                        case 4:
                            character = 5;
                            MainActivity.next();
                            Log.d("Tag", "5");
                            break;

                        case 5:
                            character = 6;
                            MainActivity.next();
                            Log.d("Tag", "6");
                            break;
                    }

                }

            });

            return imageView;

        }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}