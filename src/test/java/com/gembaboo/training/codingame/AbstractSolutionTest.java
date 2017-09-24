package com.gembaboo.training.codingame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Base class for all CodingGame unit tests. It invokes the main method of a solution's class.
 * It passes the inputLines to the std in and checks if the expected output equals the provided std out.
 */

public abstract class AbstractSolutionTest {


    private String expected;
    private String inputLines;
    private Class solutionClass;


    protected ByteArrayOutputStream testOutput;
    private PrintStream previous;

    public AbstractSolutionTest(Class solutionClass, String inputLines, String expected) {
        this.solutionClass = solutionClass;
        this.expected = expected;
        this.inputLines = inputLines;
    }


    @Before
    public void setUp() {
        enhanceOutputStream();
        launchSolution();
    }


    @Test
    public void testMain() throws Exception {
        assertEquals(expected, getTestOutput());
    }


    protected Thread createTestThread() {
        return new Thread(() -> {
            try {
                Method main = solutionClass.getMethod("main", String[].class);
                String[] params = null;
                main.invoke(null, (Object) params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }


    protected void enhanceOutputStream() {
        previous = System.out;
        testOutput = new ByteArrayOutputStream();

        OutputStream outputStreamCombiner =
                new OutputStreamCombiner(Arrays.asList(previous, testOutput));
        PrintStream custom = new PrintStream(outputStreamCombiner);

        System.setOut(custom);
    }

    protected void launchSolution() {
        try {
            Thread mainThread = createTestThread();
            InputStream testInput = new ByteArrayInputStream(inputLines.getBytes("UTF-8"));
            System.setIn(testInput);
            mainThread.start();
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @After
    public void tearDown() {
        System.setOut(previous);
        try {
            testOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getTestOutput() {
        return testOutput.toString().trim();
    }


    private static class OutputStreamCombiner extends OutputStream {
        private List<OutputStream> outputStreams;

        public OutputStreamCombiner(List<OutputStream> outputStreams) {
            this.outputStreams = outputStreams;
        }

        public void write(int b) throws IOException {
            for (OutputStream os : outputStreams) {
                os.write(b);
            }
        }

        public void flush() throws IOException {
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        }

        public void close() throws IOException {
            for (OutputStream os : outputStreams) {
                os.close();
            }
        }
    }
}

