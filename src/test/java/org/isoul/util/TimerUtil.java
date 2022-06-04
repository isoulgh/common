package org.isoul.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 重新计时的计时器
 */
public class TimerUtil {

    /**
     * 最后调用的时间
     */
    private static Long lastTime;

    /**
     * 计时器间隔
     */
    private static Long interval;

    private static TimerMethod timerMethod;
    private static Timer timer;

    public static void start(Long delay, TimerMethod timerMethod1) {
        interval = delay;
        timerMethod = timerMethod1;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerMethod1.onTimeMakerStop();
                timer.cancel();
                System.out.println("开始一次");
                start(delay, timerMethod1);
            }
        }, interval);
    }

    public void call() {
        timer.cancel();
        start(interval, timerMethod);
    }

    /**
     * 给外部的接口
     */
    public interface TimerMethod {
        /**
         * 当计时完成时回调的方法
         */
        void onTimeMakerStop();
    }

    /**
     * 调用该方法进行计时
     *
     * @param delay    计时时长
     * @param listance 监听
     */
    public synchronized static void call(Long delay, TimerMethod listance) {
        timerMethod = listance;
        lastTime = System.currentTimeMillis();
//        LogUtil.i("任务调用" + System.currentTimeMillis());
        System.out.println("任务调用" + lastTime);
//        if(currenTime == null || currenTime == 0){
//            currenTime = System.currentTimeMillis();
//        }
        //判断如果当前时间 - 最后调用call方法的时间大于计时时长就直接进行计时
        if (System.currentTimeMillis() - lastTime > delay) {
            onTimeMakerStop();
        } else {
            //判断如果当前时间 - 最后调用call方法的时间小于等于计时时长先取消之前的计时，再开始新的计时
            if (timer != null) {
//                LogUtil.i("任务取消");
                System.out.printf("任务取消");
                timer.cancel();
            }
            exec(delay);
        }
    }

    /**
     * 计时器实际进行计时的方法
     *
     * @param delay 计时时长
     */
    private static void exec(Long delay) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                // 调用的是这个工具类中的方法
                onTimeMakerStop();
//                currenTime = System.currentTimeMillis();
                timer.cancel();
                exec(delay);
            }
        }, delay);
    }

    /**
     * 计时器计时完毕时回调onTimeMakerStop()的方法
     */
    private static void onTimeMakerStop() {
//        LogUtil.i("任务执行" + System.currentTimeMillis());
        System.out.println("任务执行" + System.currentTimeMillis());
        if (timerMethod != null) {
            // 调用子类方法
            timerMethod.onTimeMakerStop();
        }
    }
}



