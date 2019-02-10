package top.douruanliang.xsan.core.ui.recycler;


import java.util.ArrayList;

/**
 * 数据转换
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();
    //设置数据

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    //获取数据
    public String getJsonData(){
        if (mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("Data is nullk");
        }
        return mJsonData;
    }
}
