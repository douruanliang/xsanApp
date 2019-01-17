package top.douruanliang.xsan.core.delegate.bottom;

import java.util.LinkedHashMap;


/**
 * 简单的工场模式
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegete> ITERMS = new LinkedHashMap();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegete delegete) {
        ITERMS.put(bean, delegete);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegete> linkedHashMap) {
        ITERMS.putAll(linkedHashMap);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegete> build() {
        return ITERMS;
    }
}
