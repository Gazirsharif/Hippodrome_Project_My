import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    public void nullNameException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "  "})
    public void blankNameException(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1d, -10 / 2d, -4 / 3, -2.0d, -0.0001d})
    public void negativeSpeedException(double speed) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Gans", speed, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1d, -10 / 2d, -4 / 3, -2.0d, -0.0001d})
    public void negativeDistanceException(double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Gans", 1, distance));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Gans, Ivan, Amir, Alim"})
    public void getNameTest(String name) {
        assertEquals(name, new Horse(name, 1, 1).getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 10 / 2d, 4 / 3, 2.0d, 0.0001d})
    public void getSpeedTest(double speed) {
        assertEquals(speed, new Horse("Gans", speed, 1).getSpeed());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 10 / 2d, 4 / 3, 2.0d, 0.0001d})
    public void getDistanceTest(double distance) {
        assertEquals(distance, new Horse("Gans", 1, distance).getDistance());
        assertEquals(0, new Horse("Gans", 1).getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 10 / 2d, 4 / 3, 2.0d, 0.0001d})
    public void moveTest(double randomDistance) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDistance);

            Horse horse = new Horse("Gans", 1);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(horse.getSpeed() * randomDistance, horse.getDistance());
        }
    }
}
