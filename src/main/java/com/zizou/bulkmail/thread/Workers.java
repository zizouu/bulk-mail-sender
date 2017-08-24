package com.zizou.bulkmail.thread;

import com.zizou.bulkmail.thread.job.SendJob;

import java.util.concurrent.*;

/**
 * Created by zizou on 2017-08-24.
 */
public class Workers {
    private final int CPU_CORE_CNT = Runtime.getRuntime().availableProcessors();
    private final int MAX_POOL_SIZE = CPU_CORE_CNT * 2;
    private final RejectedExecutionHandler rejectedExecutionHandler = new BlockPolicy();
    private ThreadPoolExecutor workerPoolExecutor = null;
    private BlockingQueue<Runnable> workQueue;
    private int corePoolSize;
    private int maximumPoolsize;
    private int terminateTimeout;
    private int keepAliveTime;
    private TimeUnit timeUnit;

    public Workers(){
        this.corePoolSize = CPU_CORE_CNT / 4;
        this.maximumPoolsize = MAX_POOL_SIZE;
        this.terminateTimeout = 3;
        this.keepAliveTime = 1;
        this.timeUnit = TimeUnit.SECONDS;
    }

    public void initialize(){
        if(this.workerPoolExecutor != null){
            throw new IllegalStateException("woker pool alread initialized");
        }else{
            ThreadFactory threadFactory = new WorkersFactory("TestWoker");
            this.workQueue = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
            this.workerPoolExecutor = new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolsize, (long)this.keepAliveTime, this.timeUnit, this.workQueue, threadFactory, this.rejectedExecutionHandler);
        }
    }

    public void execute(SendJob sendJob){
        this.workerPoolExecutor.execute(sendJob);
    }

    public boolean terminate() throws InterruptedException{
        if(this.workerPoolExecutor != null){
            this.workerPoolExecutor.shutdown();
            return this.workerPoolExecutor.awaitTermination((long)this.terminateTimeout, TimeUnit.SECONDS);
        }
        return true;
    }

    public boolean isRun() {
        if (this.workerPoolExecutor == null) {
            return false;
        } else if (this.workQueue.isEmpty()) {
            return this.workerPoolExecutor.getActiveCount() > 0;
        } else {
            return true;
        }
    }

    private class WorkersFactory implements ThreadFactory{
        private int count = 0;
        private String name;

        public WorkersFactory(String name){
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, this.name + " - " + ++this.count);
            return thread;
        }
    }

    private class BlockPolicy implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if(!executor.isShutdown()){
                try{
                    executor.getQueue().put(r);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
