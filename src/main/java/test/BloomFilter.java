package test;

import java.util.BitSet;

/**
 * @Auther: zls
 * @Date: 2022/3/3 11:13
 * @Description: 布隆过滤器
 */
public class BloomFilter {

    private static int DEFAULT_SIZE = 256 << 22;

    //使用加法hash算法，所以定义了一个8个元素的质数数组
    private static final int[] primes = new int[]{5, 7, 11, 13, 31, 37, 61, 67};

    //用八个不同的质数，相当于构建8个不同算法
    private static Hash[] hashList = new Hash[primes.length];

    //创建一个长度为10亿的比特位
    private static BitSet bits = new BitSet(DEFAULT_SIZE);

    public BloomFilter() {
        for (int i = 0; i < primes.length; i ++) {
            //使用8个质数，创建八种算法
            hashList[i] = new Hash(DEFAULT_SIZE, primes[i]);
        }
    }

    //添加元素
    public void add(String value) {
        for (Hash f : hashList) {
            //算出8个信息指纹，对应到2的32次方个比特位上
            bits.set(f.hash(value), true);
        }
    }

    //判断是否在布隆过滤器中
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (Hash f : hashList) {
            //查看8个比特位上的值
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    //加法hash算法
    private static class Hash {

        private int cap;
        private int seed;

        public Hash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i ++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }

    }

    public static void main(String[] args) {

        BloomFilter bloomFilter = new BloomFilter();
        System.out.println(bloomFilter.contains("5324512515"));
        bloomFilter.add("5324512515");

        //维护1亿个在线用户
        for (int i = 1 ; i < 100000000 ; i ++) {
            bloomFilter.add(String.valueOf(i));
        }

        long begin = System.currentTimeMillis();
        System.out.println(bloomFilter.contains("5324512515"));
        System.out.println(bloomFilter.contains("5324512513"));
        long end = System.currentTimeMillis();
        System.out.println("判断5324512515是否在线使用了:" + (end - begin));
    }

}
