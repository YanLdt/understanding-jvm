package com.yanl.reference;

/**
 * @Author: YanL
 * @Time: 18:06 2020/1/29
 * @Email: imyanl.dt@gmail.com
 * @Description: 测试引用计数的缺点
 */
public class ReferenceCounting {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 占内存  验证是否被回收
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
