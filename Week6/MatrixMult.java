import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMult extends Thread{
    private int[][] A;
    private int[][] B;
    private int index;
    private int gap;
    private int[][] result;
    private CountDownLatch countDownLatch;

    public MatrixMult(int[][] A, int[][] B, int index, int gap, int[][] result, CountDownLatch countDownLatch) {
        this.A = A;
        this.B = B;
        this.index = index;
        this.gap = gap;
        this.result = result;
        this.countDownLatch = countDownLatch;
    }

    // 计算特定范围内的结果
    public void run() {
        // TODO Auto-generated method stub
        for (int i = index * gap; i < (index + 1) * gap; i++)
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++)
                    result[i][j] += A[i][k] * B[k][j];
            }
        // 线程数减1
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        // 声明和初始化
        long startTime;
        long endTime;
        int size = 0;
        while(true) {
            size = size + 100;
            int row_A = size;
            int col_A = size;
            int col_B = size;
            int[][] A = new int[row_A][col_A];
            int[][] B = new int[col_A][col_B];
            //存放并行计算结果
            int[][] parallel_result = new int[A.length][B[0].length];
            //存放串行计算结果
            int[][] serial_result = new int[A.length][B[0].length];
            int[][] result = new int[A.length][B[0].length];
            //子线程数量
            int threadNum = 10;
            //子线程的分片计算间隔
            int gap = A.length / threadNum;
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            // 为A和B矩阵随机赋值
            for (int i = 0; i < row_A; i++)
                for (int j = 0; j < col_A; j++) {
                    A[i][j] = (int) (Math.random() * 100);
                }
            for (int i = 0; i < col_A; i++)
                for (int j = 0; j < col_B; j++) {
                    B[i][j] = (int) (Math.random() * 100);
                }
            // 并行计算
            startTime = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                MatrixMult ct = new MatrixMult(A, B, i, gap, parallel_result, countDownLatch);
                ct.start();
            }
            //MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            // memoryUsage = memoryMXBean.getHeapMemoryUsage();
            //long totalMemorySize = memoryUsage.getInit(); //初始的总内存


            //long usedMemorySize = memoryUsage.getUsed(); //已使用的内存

            countDownLatch.await();
            endTime = System.currentTimeMillis();
            //System.out.println("并行计算开始时刻:" + (startTime));
            //System.out.println("并行计算结束时刻:" + (endTime));
            System.out.println("矩阵大小:" + size);
            System.out.println("并行计算运行时间:" + (endTime - startTime));

            // 串行计算
            startTime = System.currentTimeMillis();
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int k = 0; k < A[0].length; k++)
                        serial_result[i][j] += A[i][k] * B[k][j];
                }
            }
            endTime = System.currentTimeMillis();
            //System.out.println("串行计算开始时刻:" + (startTime));
            //System.out.println("串行计算结束时刻:" + (endTime));
            System.out.println("串行计算运行时间:" + (endTime - startTime));
            startTime = System.currentTimeMillis();

            //建立四个工作线程
            Thread []poolThreads=new Thread[threadNum];
            for(int i=0;i<threadNum;i++)
                poolThreads[i]=new Thread(new MatrixMult(A, B, i, gap, result, countDownLatch));
            //建立线程池
            ExecutorService pool = Executors.newCachedThreadPool();
            long time5=System.currentTimeMillis();//记录开始时间
            for(int i=0;i<threadNum;i++)
                pool.execute(poolThreads[i]);//将四个工作线程放入线程池中执行
            pool.shutdown();//在线程池终止前允许执行以前提交的任务
            while (true) {
                if (pool.isTerminated()) {
                    break;
                }
            }
            endTime = System.currentTimeMillis();
            System.out.println("线程池计算运行时间:" + (endTime - startTime));
        }

    }


}
