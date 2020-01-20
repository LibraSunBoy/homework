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


    private static Object obj1 = new Object();

    private static Object obj2 = new Object();

    private static class SpearThread1 extends Thread{

        private String name;

        public SpearThread1(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try{
                while (!isInterrupted()){
                    if (bulletNum<20L){
                        synchronized (obj1){
                            bulletNum++;
                            System.out.println(name+"正在装填子弹，已有"+bulletNum);
                            sleep(500);
                        }
                    }else{
                        synchronized (obj1) {
                            interrupt();
                            notifyAll();
                            System.out.println("子弹已装填完毕！");
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("e:"+e);
            }
        }
    }

    private static class SpearThread2 extends Thread{
        private String name;

        public SpearThread2(String name){
            this.name = name;
        }
        @Override
        public void run() {
            try{
                synchronized (obj2) {
                    while (!isInterrupted()) {
                        obj2.wait();
                    }
                }
                while (bulletNum>0L){
                    synchronized (obj2){
                        bulletNum--;
                        System.out.println(name+"正在发射子弹，剩下"+bulletNum);
                        sleep(500);
                    }
                }
                System.out.println(name+"子弹已发射完毕！");
            }catch (Exception e){
                System.out.println("e:"+e);
            }
        }
    }

    public static void  main(String[] args)throws Exception{

        System.out.println("开始装填子弹！");
        new SpearThread1("线程1").start();
        new SpearThread1("线程2").start();
        System.out.println("开始发射子弹！");
        new SpearThread2("线程3").start();
        new SpearThread2("线程4").start();
    }
}
