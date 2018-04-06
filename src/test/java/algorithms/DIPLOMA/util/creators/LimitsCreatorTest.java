package algorithms.DIPLOMA.util.creators;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class LimitsCreatorTest {
    private static List<String> parsedStrings;

    @BeforeClass
    public static void setUp(){
        parsedStrings = new ArrayList<>();
        parsedStrings.add("1а;1");
        parsedStrings.add("2а;2");
        parsedStrings.add("3а;5");
    }

    @Test
    public void createLimitsFromList() {
        LimitsCreator limitsCreator = mock(LimitsCreator.class);
        when(limitsCreator.parseLimitsFromFile()).thenReturn(parsedStrings);
        when(limitsCreator.createLimitsFromList()).thenCallRealMethod();

        Map<String, Integer> limits = limitsCreator.createLimitsFromList();
        assertEquals(1, limits.get("1а").intValue());
        assertEquals(2, limits.get("2а").intValue());
        assertEquals(5, limits.get("3а").intValue());

    }
}