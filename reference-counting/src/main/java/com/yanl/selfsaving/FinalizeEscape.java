package com.yanl.selfsaving;

/**
 * @Author: YanL
 * @Time: 11:19 2020/1/30
 * @Email: imyanl.dt@gmail.com
 * @Description:
 */
public class FinalizeEscape {

    public static FinalizeEscape SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive!");
    }

    /**
     *
     * finalize()是Object的protected方法，子类可以覆盖该方法以实现资源清理工作，GC在回收对象之前调用该方法。
     * 当对象变成(GC Roots)不可达时，GC会判断该对象是否覆盖了finalize方法，
     * 若未覆盖，则直接将其回收。否则，若对象未执行过finalize方法，将其放入F-Queue队列，
     * 由一低优先级线程执行该队列中对象的finalize方法。
     * 执行finalize方法完毕后，GC会再次判断该对象是否可达，若不可达，则进行回收，否则，对象“复活”。
     *
     * finalize方法
     *      回收时任何对象的finalize方法只会被系统自动调用一次
     *      下一次回收则finalize方法不会执行
     *
     *      不建议使用：不确定性大
     *
    * @throws Throwable 抛出异常
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscape.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscape();

        //对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalizer方法优先级很低，暂停500ms，等待
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead!");
        }

        //对象自救失败    finalize只能执行一次。
        SAVE_HOOK = null;
        System.gc();
        //因为finalizer方法优先级很低，暂停500ms，等待
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead!");
        }
    }
}
