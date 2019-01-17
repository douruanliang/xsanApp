package top.douruanliang.xsan.core.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import top.douruanliang.xsan.core.R;
import top.douruanliang.xsan.core.R2;
import top.douruanliang.xsan.core.delegate.XsanDelegate;

public abstract class BaseBottomDelegate extends XsanDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEAN = new ArrayList<>();
    private final ArrayList<BottomItemDelegete> ITEM_DELEGETE = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegete> ITEMS = new LinkedHashMap<>();

    Unbinder unbinder;


    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.GREEN;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;


    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegete> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegete> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegete> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegete value = item.getValue();
            TAB_BEAN.add(key);
            ITEM_DELEGETE.add(value);

        }
    }


    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View view) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_text_icon_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //每个ITEM点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //找到icon/text
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean itemBean = TAB_BEAN.get(i);
            //初始化数据
            itemIcon.setText(itemBean.getIcon());
            itemTitle.setText(itemBean.getTitle());

            if (i == mIndexDelegate){
                itemIcon.setTextColor(mClickedColor);
                        itemTitle.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray = ITEM_DELEGETE.toArray(new SupportFragment[size]);

        loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArray);
    }

    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for(int i =0;i<count;i++){
            final RelativeLayout item = (RelativeLayout)mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View v) {
        final int tag = (int)v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout)v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        //TODO
        showHideFragment(ITEM_DELEGETE.get(tag),ITEM_DELEGETE.get(mCurrentDelegate));
        mCurrentDelegate = tag;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
