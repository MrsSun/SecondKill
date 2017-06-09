package secondkill.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author sunxin08
 * @date 2017/6/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:WEB-INF/applicationContext.xml")
public class AuthFilterTest {

    private AuthFilter authFilter = new AuthFilter();

    @Before
    public void before() {
        ServletRequest request = new MockHttpServletRequest();
        ServletResponse response = new MockHttpServletResponse();
    }

    @Test
    public void testDoFilter() throws Exception {


    }

}