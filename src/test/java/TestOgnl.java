import com.alibaba.fastjson.JSON;
import com.zhenmei.OgnlWrapper;
import org.junit.Test;

import java.util.Map;

public class TestOgnl {

    /**
     * 解析json
     */
    @Test
    public void parseJson() {
        String json = "{\"user\":{\"name\":\"123\",\"depart\":[1,2]}}";
        Map<String, Object> map = JSON.parseObject(json, Map.class);
        OgnlWrapper ognlWrapper = new OgnlWrapper(map);
        System.out.println((String) ognlWrapper.get("user.name"));
        System.out.println(ognlWrapper.getInt("user.depart.size"));
        System.out.println(ognlWrapper.getInt("user.depart[0]"));

    }
}
