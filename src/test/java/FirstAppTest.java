/**
 * Created by decai.jiao on 15/09/2017.
 */
public class FirstAppTest {
    private FirstApp app = new FirstApp("one", "two");
    @org.junit.Test
    public void getName() throws Exception {
        assert(app.getName() == "one");
    }

    @org.junit.Test
    public void getType() throws Exception {
        assert(app.getType() == "two");
    }

}