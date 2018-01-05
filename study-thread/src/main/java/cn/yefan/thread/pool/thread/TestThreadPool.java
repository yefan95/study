package cn.yefan.thread.pool.thread;

import java.util.concurrent.*;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class TestThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1000L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

        threadPoolExecutor.submit(new MyTask());


        Future<Integer> future = threadPoolExecutor.submit(new MyCallableTask());
        try {
            Integer result = future.get();
            System.out.println("result= " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
    }

}
