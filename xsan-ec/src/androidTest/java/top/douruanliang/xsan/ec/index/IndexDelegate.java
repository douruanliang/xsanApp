package top.douruanliang.xsan.ec.index;



import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.douruanliang.xsan.core.net.RestCreator;

public class IndexDelegate {

    //TODO :测试方法
    void onCallRxGet(){
        final String url = "index.php";

        final WeakHashMap<String,Object> params = new WeakHashMap<>();
        final Observable<String> observable  = RestCreator.getRxRestservice().get(url,params);

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
