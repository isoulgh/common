package org.isoul.util;

import org.junit.Test;

public class TimeThreadTest implements TimeThread.TimeInterface {

//    @Test
    public void aTest() {
        TimeThread timeThread = new TimeThread(4000L, new TimeThreadTest());
        timeThread.start();
        while (true) {
            synchronized (timeThread.lastActiveTime) {
                timeThread.call();
                System.out.println("醒一次");
            }
            try {
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void TimeMethod() {
        System.out.println("计时一次");
    }

}
