package com.gembaboo.training.codingame.temperature;

import com.gembaboo.training.codingame.AbstractSolutionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class SolutionTest extends AbstractSolutionTest {


    public SolutionTest(String inputLines, String expected) {
        super(Solution.class, inputLines, expected);
    }


    @Parameterized.Parameters
    public static String[][] parameters() {
        return new String[][]{
                {"3\n1 5 2\n", "1"},
                {"3\n-1 5 2\n", "-1"},
                {"4\n1 -1 5 2\n", "1"}
        };
    }
}