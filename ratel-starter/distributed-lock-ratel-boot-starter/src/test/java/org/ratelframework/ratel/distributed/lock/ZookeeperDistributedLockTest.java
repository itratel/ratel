package org.ratelframework.ratel.distributed.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ratelframework.ratel.distributed.lock.annotation.ZkDistributedLock;
import org.ratelframework.ratel.distributed.lock.aspect.ZookeeperLockAspect;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperDistributedLockTest {

    public Integer getCount() {
        return count;
    }

    private Integer count = 10;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private CuratorFramework zkClient;

    /**
     * 不使用分布式锁，程序结束查看count的值是否为0
     */
    @Test
    public void test() throws InterruptedException {
        IntStream.range(0, 10000).forEach(i -> executorService.execute(this::doBuy));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", count);
    }

    /**
     * 测试AOP分布式锁
     */
    @Test
    public void testAopLock() throws InterruptedException {
        // 测试类中使用AOP需要手动代理
        ZookeeperDistributedLockTest target = new ZookeeperDistributedLockTest();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ZookeeperLockAspect aspect = new ZookeeperLockAspect(zkClient);
        factory.addAspect(aspect);
        ZookeeperDistributedLockTest proxy = factory.getProxy();
        IntStream.range(0, 10000).forEach(i -> executorService.execute(() -> proxy.aopBuy(i)));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", proxy.getCount());
    }

    @ZkDistributedLock(key = "buy", timeout = 1, timeUnit = TimeUnit.MINUTES)
    public void aopBuy(int userId) {
        log.info("{} 正在出库。。。", userId);
        doBuy();
        log.info("{} 扣库存成功。。。", userId);
    }

    public void doBuy() {
        count--;
        log.info("count值为{}", count);
    }

}

