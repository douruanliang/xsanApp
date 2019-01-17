package top.douruanliang.xsan.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import top.douruanliang.xsan.core.delegate.bottom.BaseBottomDelegate;
import top.douruanliang.xsan.core.delegate.bottom.BottomItemDelegete;
import top.douruanliang.xsan.core.delegate.bottom.BottomTabBean;
import top.douruanliang.xsan.core.delegate.bottom.ItemBuilder;
import top.douruanliang.xsan.ec.main.index.IndexDelegate;
import top.douruanliang.xsan.ec.main.sort.SortDelegate;

public class XsanBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegete> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegete > items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
