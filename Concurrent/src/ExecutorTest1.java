import java.util.Calendar;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest1 {
    /**
     * author:dulei
     * date :2017-10-31
     * desc:该测试用来验证循环创建新的线程池覆盖之前的引用，是否会中断上一次线程池中的线程
     * conclusion:不会，线程池引用被覆盖并不能中断内存中已经创建的线程池的正常运行，这样失去显式引用的
     * 线程池就会驻留内存，造成内存泄露
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor =  null;
        for (int i = 0; i < 2; i++) {
            int num =i;
            executor  = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, new LinkedBlockingDeque<>());
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while ( !Thread.currentThread().isInterrupted()){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("****************executor id is: "+num+" [Time]:"+ Calendar.getInstance().getTime()+"**************");
                        System.out.println(String.format("thread %d say hello,executor is %d",this.hashCode(),num));
                    }
                }
            });
        }
        executor.shutdown();
    }
}
