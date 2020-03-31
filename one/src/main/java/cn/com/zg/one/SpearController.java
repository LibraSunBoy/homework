package cn.com.zg.one;


/**
 * <pre>
 * Filename      :  Spear
 * Package       :  cn.com.zg.one
 * Create Date   :  2020年01月2020/1/20日
 * </pre>
 *
 * @author : sunnice
 *
 *
 * 《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，
 * 它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 * 请实现上面的程序。将程序运行结果发送至 446106311@qq.com。邮件标题请写好 学号，作业标题
 *
 */
public class SpearController {

    private volatile static long bulletNum = 0L;

    private volatile static boolean flag = false;

    private static Object obj1 = new Object();

    private static Object obj2 = new Object();

    private static class SpearInThread extends Thread{

        private String name;

        public SpearInThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try{
//                System.out.println(name+"开始装填子弹！");
//                while (bulletNum<20L){
//                        bulletNum++;
//                        System.out.println(name+"正在装填子弹，已有"+bulletNum);
//                        sleep(500);
//                }
//                System.out.println(name+"子弹已装填完毕！");
                    System.out.println(name+"开始装填子弹！");
                    while (!flag){
                        synchronized(obj1) {
                            if (!flag){
                                if (bulletNum<20L) {
                                    bulletNum++;
                                    sleep(200);
                                    System.out.println(name + "正在装填子弹，已有" + bulletNum);
                                }else{
                                    flag=true;
                                }
                            }
                        }
                    }
                    System.out.println(name + "子弹已装填完毕！");
                    synchronized(obj2){
                        sleep(200);
                        obj2.notifyAll();
                    }
            }catch (Exception e){
                System.out.println("e:"+e);
            }
        }
    }

    private static class SpearOutThread extends Thread{

        private String name;

        private SpearInThread spearInThread;

        public SpearOutThread(String name){
            this.name = name;
        }

//        public SpearOutThread(String name,SpearInThread spearInThread){
//            this.name = name;
//            this.spearInThread = spearInThread;
//        }
        @Override
        public void run() {
            try{
//                spearInThread.join();
//                System.out.println(name+"开始发射子弹！");
//                while (bulletNum>0L){
//                    bulletNum--;
//                    System.out.println(name+"正在发射子弹，剩下"+bulletNum);
//                    sleep(500);
//                }
//                System.out.println(name+"子弹已发射完毕！");

                synchronized (obj2){
                    if (!flag) {
                        while (bulletNum < 20L) {
                            obj2.wait();
                        }
                    }
                }
                sleep(1000);
                System.out.println(name+"开始发射子弹！");
                while (bulletNum>0L){
                    synchronized (obj1) {
                        if (bulletNum>0L){
                            bulletNum--;
                            System.out.println(name + "正在发射子弹，剩下" + bulletNum);
                            sleep(500);
                        }
                    }
                }
                System.out.println("子弹已发射完毕！");
            }catch (Exception e){
                System.out.println("e:"+e);
            }
        }
    }

    public static void  main(String[] args)throws Exception{
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>();
        SpearInThread inThread1 = new SpearInThread("线程1");
        SpearInThread inThread2 = new SpearInThread("线程2");
        SpearOutThread outThread1 = new SpearOutThread("线程3");
        SpearOutThread outThread2 = new SpearOutThread("线程4");
        inThread1.start();
        inThread2.start();
        outThread1.start();
        outThread2.start();
    }
}
