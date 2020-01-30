package com.yanl.reference;

/**
 * @Author: YanL
 * @Time: 18:06 2020/1/29
 * @Email: imyanl.dt@gmail.com
 * @Description: 测试引用计数的缺点
 *
 * 引用计数算法：
 *      在对象中添加一个引用计数器，每当有引用时，该计数器加1；引用失效时，计数器减一；
 *      任何时刻计数器为零的对象就是不可能再被使用的
 *      -----------------------------
 *      但是如果对象之间存在相互引用，则其计数器永远不会为零
 *
 */
public class ReferenceCounting {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 该成员属性占内存  验证是否被回收
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCounting objA = new ReferenceCounting();
        ReferenceCounting objB = new ReferenceCounting();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

}
