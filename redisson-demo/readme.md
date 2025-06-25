## redisson通过延迟队列实现订单超时取消

### 原理
- Redisson提供延迟队列（DelayedQueue），它是基于Redis的pub/sub机制和zset实现，本身用于处理需要延迟执行的任务
- Redisson将任务按照预定的执行时间储存在 zset 中，任务的执行时间作为 score，任务本身序列化后存在zset中
- 同时Redisson会在后台监控这个zset，一旦发现有符合执行条件（当前时间>=score）的任务，就会将这些任务转移到RQueue（Redisson提供的队列）
- 应用程序只需要从RQueue中取出任务执行


**该方案能达到毫秒级的延迟精度，吞吐量5w+，属于最终一致性方案，适合中等规模项目。**
### 实现流程

## redisson分布式锁实现秒杀场景

### 原理
- watchdog机制，实现业务执行过程中的锁续期操作
- redisson提供trylock的无阻塞方法
### 实现流程
- 用setnx命令原子加锁，同时设置过期时间
- 获取库存，用Atomic属性方法实现原子递减扣库存
- 扣除成功释放锁，先判断是否为当前线程持有然后再释放（lua脚本实现原子操作）
