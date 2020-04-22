import com.zhujun.TransactionApp;
import com.zhujun.entity.SysUser;
import com.zhujun.service.SysUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = TransactionApp.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TransactionTest {



    @Resource
    private SysUserService sysUserService;

    //@Ignore("not ready yet")
    @Test
    public void testGetEntFileById(){
        SysUser user = sysUserService.queryById(88);
        System.out.println(user.toString());
    }

}
