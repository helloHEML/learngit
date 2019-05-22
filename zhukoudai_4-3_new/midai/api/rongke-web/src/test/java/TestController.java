import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chengwenwen on 2016/11/21.
 * 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test.xml")
public class TestController {
    /*@Resource
    private RedisClusterCache redisClusterCache;

    @Test
    public void test() throws Exception{
        List<String> list = new ArrayList<String>();
        list.add("测试list");
        list.add("测试list2");
        redisClusterCache.putCache("testList","redis集群测试");

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("test*","测试数据");
        map.put("测试数据","啥的");
        map.put("listTest",list);
        redisClusterCache.putCache("testMap",map);

        redisClusterCache.putCache("testString","redis集群测试");
        Map resultMap = new HashMap();
        resultMap.put("testList",redisClusterCache.getCache("testList"));
        resultMap.put("testMap",redisClusterCache.getCache("testMap"));
        resultMap.put("testString",redisClusterCache.getCache("testString"));
        System.out.print(map);
    }*/
}
