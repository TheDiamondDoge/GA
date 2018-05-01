package algorithms.DIPLOMA.util.creators;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class LimitsCreatorDailyTest {
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
        LimitsCreatorDaily limitsCreatorDaily = new LimitsCreatorDaily(parsedStrings);
//        when(limitsCreatorDaily.parseLimitsFromFile(anyString())).thenReturn(parsedStrings);
//        when(limitsCreatorDaily.createLimitsFromList()).thenCallRealMethod();

//        Map<String, Map<Integer, Integer>> limits = limitsCreatorDaily.createLimitsFromList();
//        assertEquals(1, limits.get("1а").intValue());
//        assertEquals(2, limits.get("2а").intValue());
//        assertEquals(5, limits.get("3а").intValue());

    }
}