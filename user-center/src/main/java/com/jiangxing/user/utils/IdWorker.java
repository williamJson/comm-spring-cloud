package com.jiangxing.user.utils;

/**
 * 分布式id生成器
 * 参考twitter的雪花算法实现分布式id生成器
 * 生成器生成的id是long类型的64位二进制数
 * [1] bit 不使用，符号位
 * [2 bit -42bit] 共41bit: 存储时间戳 ，精确到毫秒
 * [43bit - 52bit] 共10bit: 存储机器码+业务码，这块保留10bit,使用时可以自由分配，本文实现采用 5bit业务码+5bit机器码
 * [53bit - 64bit] 共12bit: 毫秒内id自增计数器
 * 使用：
 * 标注有【可自行修改】的参数，可根据实际需求更改，其他参数不建议修改
 * 注意：
 * 在linux系统中会有时钟回拨机制，所以在使用该方案时，建议关闭时钟回拨
 *
 * @author wyq
 */
public class IdWorker {

    /**
     * 【可自行修改】
     * 初始化时间戳，一经定义不可修改
     */
    private static final long INIT_TIME_MILLIS = 1578887301963L;


    /**
     * 用于保存时间的只有41bit
     * 时间戳最大值
     */
    private static final long MAX_TIME_MILLIS = 1L << 41 - 1;
    /**
     * 【可自行修改】
     * 机器id/进程id 取值范围 0 - 32 [0,2^5-1 ]
     */
    private static final long PID = 1L;

    /**
     * 最大机器/进程id
     */
    private static final int MAX_PID = 1 << 5;

    /**
     * 【可自行修改】
     * 业务id 取值范围 0 - 32 [0,2^5-1 ]
     */
    private static final long BUS_ID = 1L;

    /**
     * 最大业务id
     */
    private static final int MAX_BUS_ID = 1 << 5;


    /**
     * 时间偏移量
     */
    private static final int TIME_OFFER_SET = 22;

    /**
     * bid 偏移量
     */
    private static final int BID_OFFER_SET = 17;

    /**
     * pid偏移量
     */
    private static final int PID_OFFER_SET = 12;

    /**
     * 业务id偏移量
     */
    private static final int COUNT_MAX_LENGTH = 12;
    /**
     * 单位毫秒内id自增计数器 取值范围0-4095 [0,2^12-1]
     */
    private static volatile long count = 0;

    /**
     * 能生成最大id数
     */
    private static final int MAX_COUNT_SIZE = 1 << COUNT_MAX_LENGTH - 1;


    /**
     * 当前系统时间-初始化时间
     */
    private static volatile long currentTimeMillis = getCurrentTimeMillis();

    private static long lastTimeMillis = currentTimeMillis;


    /**
     * 获取id
     *
     * @return
     */
    public static synchronized long nextId() {
        if (currentTimeMillis > MAX_TIME_MILLIS) {
            throw new RuntimeException("time out of range...");
        }
        if (BUS_ID < 0 || BUS_ID > MAX_BUS_ID) {
            throw new RuntimeException("bid out of range...");
        }
        if (PID < 0 || PID > MAX_PID) {
            throw new RuntimeException("pid out of range...");
        }
        long currentCount = count++;
        //判断当前生成的id达到上限
        if (currentCount >= MAX_COUNT_SIZE) {
            while (currentTimeMillis == lastTimeMillis) {
                //循环重置初始时间，直到下一毫秒为止
                currentTimeMillis = getCurrentTimeMillis();
            }
            //解决时钟回拨问题,比较简单粗暴，使用起来还是没有问题的
            if (currentTimeMillis < lastTimeMillis) {
                long refusedSeconds = lastTimeMillis - currentTimeMillis;
                throw new RuntimeException("Clock moved backwards. Refusing for" + refusedSeconds);
            }
            lastTimeMillis = currentTimeMillis;
            count = 0;
            currentCount = count++;
        }
        return ((currentTimeMillis << TIME_OFFER_SET) | (BUS_ID << BID_OFFER_SET) | (PID << PID_OFFER_SET) | currentCount);
    }


    /**
     * 获取当前时间与起始定义时间差值
     *
     * @return
     */
    private static long getCurrentTimeMillis() {
        return System.currentTimeMillis() - INIT_TIME_MILLIS;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 1000L;
        int num = 0;
        while (System.currentTimeMillis() <= endTime) {
//            System.out.println(nextId());
            nextId();
            num++;
        }
        System.out.println("===>" + num);
        //System.out.println((1L << 41));
        //结论：这样的雪花算法，经过测试单线程情况下，每秒大概能生产出2000K左右id,完全能满足生产条件下使用
    }
}
