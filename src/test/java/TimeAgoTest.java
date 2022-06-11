import com.github.kevinsawicki.timeago.TimeAgo;

public class TimeAgoTest {
    public static void main(String[] args) {

        TimeAgo time = new TimeAgo();
        long current = System.currentTimeMillis();
        String minutes = time.timeAgo(current - (15 * 60 * 1000));	// returns "15 minutes ago"
        String hours = time.timeUntil(current - (6 * 60 * 60 * 1000));	// returns "6 hours from now"

    }
}
