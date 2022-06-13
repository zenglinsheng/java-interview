package coding.segmentLock;

import org.testng.collections.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zls
 * @Date: 2022/6/13 15:26
 * @Description:
 */
public class SegmentLock {

    private static final Integer segments = 10;//默认分段数量

    private static final Map<Integer, Lock> lockMap = Maps.newConcurrentMap();

    static {
        init(segments, false);
    }

    public SegmentLock() {}

    /**
     * 分段锁初始化
     *
     * @param counts
     * @param fair
     */
    private static void init(Integer counts, boolean fair) {
        for (int i = 0; i < segments; i++) {
            lockMap.put(i, new ReentrantLock(fair));
        }
    }

    /**
     * 加锁
     *
     * @param key
     */
    public static void lock(Integer key) {
//        System.out.println(key % segments + "获取锁");
        ReentrantLock lock = (ReentrantLock) lockMap.get(key % segments);
        lock.lock();
    }

    /**
     * 解锁
     *
     * @param key
     */
    public static void unlock(Integer key) {
//        System.out.println(key % segments + "释放锁");
        ReentrantLock lock = (ReentrantLock) lockMap.get(key % segments);
        lock.unlock();
    }

}
