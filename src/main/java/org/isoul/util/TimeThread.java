package org.isoul.util;

public class TimeThread extends Thread {

    /**
     * 最近活跃的时间
     */
    public Long lastActiveTime;
    /**
     * 未活跃的超时时间
     */
    public long remindTime;
    /**
     * 外部实现的方法
     * 计时结束后的执行函数
     */
    public TimeInterface timeInterface;

    /**
     * 构造方法
     *
     * @param time       未活跃超时时间
     * @param timeMethod 计时结束后执行的方法类
     */
    public TimeThread(long time, TimeInterface timeMethod) {
        remindTime = time;
        timeInterface = timeMethod;
        lastActiveTime = System.currentTimeMillis();
    }

    /**
     * 重新设置最近活跃时间
     */
    public void call() {
        synchronized (lastActiveTime) {
            lastActiveTime = System.currentTimeMillis();
        }
    }

    /**
     * 不断地计时并执行方法
     */
    @Override
    public void run() {
        lastActiveTime = System.currentTimeMillis();
        while (true) {
            synchronized (lastActiveTime) {
                if (System.currentTimeMillis() - lastActiveTime > remindTime) {
//                    System.out.println("时间超出执行方法");
                    TimeMethod();
//                    break;
                } else {
//                    System.out.println("时间未超出");
                }
            }
            try {
                Thread.sleep(remindTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 外部实现方法的接口
     */
    public interface TimeInterface {
        void TimeMethod();
    }

    /**
     * 执行外部实现的方法
     */
    private void TimeMethod() {
        if (timeInterface != null) {
            timeInterface.TimeMethod();
        }
    }

}
