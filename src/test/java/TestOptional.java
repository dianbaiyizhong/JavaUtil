
import java.util.Optional;

public class TestOptional {


    static class SchoolInfo {
        String score;
    }


    static class User {
        String name;
        SchoolInfo schoolInfo;
    }

    public static void main(String[] args) {
        User user = new User();

        try {
            String score = user.schoolInfo.score;
            System.out.println(score);
        } catch (Exception e) {
            System.out.println("此用法会引起空指针异常...");
        }


        String score2 = Optional.ofNullable(user).map(m -> m.schoolInfo).map(m -> m.score).orElse("分数不存在");

        System.out.println(score2);


    }
}
