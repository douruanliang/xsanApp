package top.douruanliang.xsan.core.util.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
      if (mITimerListener!=null){
          mITimerListener.onTimer();
      }
    }
}
