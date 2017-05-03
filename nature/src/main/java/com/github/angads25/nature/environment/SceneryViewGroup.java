package com.github.angads25.nature.environment;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.github.angads25.nature.elements.LargeCloudView;
import com.github.angads25.nature.elements.MediumCloudView;
import com.github.angads25.nature.elements.SmallCloudView;
import com.github.angads25.nature.model.CloudView;

import java.util.Random;

/**
 * <p>
 * Created by Angad on 30-04-2017.
 * </p>
 */

public class SceneryViewGroup extends ViewGroup{
    private int width, height, minDim;
    private Rect mTmpChildRect;
    private Random rand;

    public SceneryViewGroup(Context context) {
        super(context);
        initViewGroup();
    }

    public SceneryViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewGroup();
    }

    private void initViewGroup() {
        mTmpChildRect = new Rect();
        rand = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        minDim = Math.min(width, height);
        setMeasuredDimension(width, height);
        int childs = getChildCount();
        for(int i = 0; i < childs; i++) {
            View child = getChildAt(i);
            if(child instanceof SkyViewGroup) {
                child.measure(MeasureSpec.makeMeasureSpec(width,
                        MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec((height/2) + (height/3),
                                MeasureSpec.EXACTLY));
            }
            else if (child instanceof MountainViewGroup) {
                child.measure(MeasureSpec.makeMeasureSpec(width,
                        MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec((height/2) + (height/3),
                                MeasureSpec.EXACTLY));
            }
            else if (child instanceof CloudView) {
                if(child instanceof SmallCloudView) {
                    child.measure(MeasureSpec.makeMeasureSpec(minDim/6,
                            MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(minDim/6,
                                    MeasureSpec.EXACTLY));
                }
                else if(child instanceof MediumCloudView) {
                    child.measure(MeasureSpec.makeMeasureSpec(minDim/5,
                            MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(minDim/5,
                                    MeasureSpec.EXACTLY));
                }
                else if(child instanceof LargeCloudView) {
                    child.measure(MeasureSpec.makeMeasureSpec(minDim/3,
                            MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(minDim/3,
                                    MeasureSpec.EXACTLY));
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childs = getChildCount();
        for(int i = 0; i<childs; i++) {
            View child = getChildAt(i);
            if(child instanceof SkyViewGroup) {
                child.layout(l, t, r, (height/2) + (height/3));
            }
            else if (child instanceof MountainViewGroup) {
                mTmpChildRect.left = l;
                mTmpChildRect.right = r;
                mTmpChildRect.top = t;
                mTmpChildRect.bottom = (height/2) + (height/3);
                child.layout(mTmpChildRect.left, mTmpChildRect.top, mTmpChildRect.right, mTmpChildRect.bottom);
            }
            else if (child instanceof CloudView) {
                //Corner case for above clouds
                int widgetPadding = minDim / 10;
                int limit = height / 20;
                int top = rand.nextInt(limit);
                if(child instanceof SmallCloudView) {
                    int smallCloudSize = minDim / 6;
                    int left = rand.nextInt(width - widgetPadding - smallCloudSize);
                    mTmpChildRect.left = left + widgetPadding;
                    mTmpChildRect.right = mTmpChildRect.left + smallCloudSize;
                    mTmpChildRect.top = top + widgetPadding;
                    mTmpChildRect.bottom = mTmpChildRect.top + smallCloudSize;
                    child.layout(mTmpChildRect.left, mTmpChildRect.top, mTmpChildRect.right, mTmpChildRect.bottom);
                }
                else if(child instanceof MediumCloudView) {
                    int mediumCloudSize = minDim / 5;
                    int left = rand.nextInt(width - widgetPadding - mediumCloudSize);
                    mTmpChildRect.left = left + widgetPadding;
                    mTmpChildRect.right = mTmpChildRect.left + mediumCloudSize;
                    mTmpChildRect.top = top + widgetPadding;
                    mTmpChildRect.bottom = mTmpChildRect.top + mediumCloudSize;
                    child.layout(mTmpChildRect.left, mTmpChildRect.top, mTmpChildRect.right, mTmpChildRect.bottom);
                }
                else if(child instanceof LargeCloudView) {
                    int largeCloudSize = minDim / 3;
                    int left = rand.nextInt(width - widgetPadding - largeCloudSize);
                    mTmpChildRect.left = left + widgetPadding;
                    mTmpChildRect.right = mTmpChildRect.left + largeCloudSize;
                    mTmpChildRect.top = top + widgetPadding;
                    mTmpChildRect.bottom = mTmpChildRect.top + largeCloudSize;
                    child.layout(mTmpChildRect.left, mTmpChildRect.top, mTmpChildRect.right, mTmpChildRect.bottom);
                }
            }
        }
    }
}
