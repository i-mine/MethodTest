import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    /**
     * author:dulei
     * date :2017-10-31
     * desc:该测试用来验证线程池中的死循环线程是否会因为shutdown而中断
     * conclusion:线程池并不能中断陷入死循环的线程
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while ( !Thread.currentThread().isInterrupted()){
                        System.out.println(String.format("thread %d say hello",this.hashCode()));
                    }
                }
            });
        }
        executor.shutdown();
    }
}
