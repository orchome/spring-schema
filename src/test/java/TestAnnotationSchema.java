import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotationSchema {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:SpringAnnotaionContext.xml");
    }
}