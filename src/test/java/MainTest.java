import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {
    @Disabled
    @Test
    public void mainTimeout() {
        assertTimeout(
                Duration.ofSeconds(22),
                () -> Main.main(new String[]{})
        );
    }
}
