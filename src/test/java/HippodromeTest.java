import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    private final Random random = new Random();

    @Test
    public void nullHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses() {
        ArrayList<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Horse " + i, random.nextDouble() * 10, random.nextDouble() * 10));
        }

        Hippodrome hippodrome = new Hippodrome(new ArrayList<>(horseList));

//        horseList.remove(6);
//        horseList.add(new Horse("Horse Gans", 1));

        List<Horse> hippodromeHorses = hippodrome.getHorses();
        for (int i = 0; i < hippodromeHorses.size(); i++) {
            assertEquals(horseList.get(i), hippodromeHorses.get(i));
        }


    }

    @Test
    public void move() {
        ArrayList<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();

        for (Horse hors : hippodrome.getHorses()) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    public void getWinner() {
        ArrayList<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Horse " + i, random.nextDouble() * 10, random.nextDouble() * 10));
        }

        Horse maxHorse = horseList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Hippodrome hippodrome = new Hippodrome(new ArrayList<>(horseList));

        Horse winner = hippodrome.getWinner();
        assertEquals(maxHorse, winner);
    }
}
