package top.douruanliang.xsan.core.ui.recycler;


import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 数据的建造者
 */
public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();
    public  MultipleItemEntityBuilder(){
        FIELDS.clear();
    }

    //默认的
    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
        return  new MultipleItemEntity(FIELDS);
    }
}
