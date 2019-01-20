package top.douruanliang.xsan.core.ui.refresh;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUNE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIFLDS  = new LinkedHashMap<>();
    //private final SoftReference<LinkedHashMap<Object,Object>> FIELDS = new SoftReference<LinkedHashMap<Object,Object>>();
    @Override
    public int getItemType() {
        return 0;
    }
}
