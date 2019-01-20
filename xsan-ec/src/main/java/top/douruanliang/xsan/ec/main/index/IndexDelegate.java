package top.douruanliang.xsan.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import top.douruanliang.xsan.core.delegate.bottom.BottomItemDelegete;
import top.douruanliang.xsan.core.ui.refresh.RefreshHandler;
import top.douruanliang.xsan.ec.R;
import top.douruanliang.xsan.ec.R2;

public class IndexDelegate extends BottomItemDelegete {
    @BindView(R2.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatTextView etSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar tbIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout srlIndex;

    private RefreshHandler mRefreshHandler = null;
    private void initRefreshLayout() {
        srlIndex.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        srlIndex.setProgressViewOffset(true,120,300);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View view) {
       mRefreshHandler =new RefreshHandler(srlIndex);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}
