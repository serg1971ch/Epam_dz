package test;

import main.Calculate;
import main.ParseRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

//@RunWith(Parameterized.class)
public class AppTest {

    @Before
    public void before() {
        ParseRecord parseRecord = new ParseRecord();
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void testAdd() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "1 + 1";
        List<String> list = parseRecord.parse(record);
        assertEquals(2.0, Calculate.calculate(list), 0.0);
    }

    @Test
    public void testMinus() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "4 - 1";
        List<String> list = parseRecord.parse(record);
        assertEquals(3.0, Calculate.calculate(list), 0.0);
    }

    @Test
    public void testMultyply() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "4.1 * 10.5";
        List<String> list = parseRecord.parse(record);
        assertEquals(43.05, Calculate.calculate(list), 0.0);
    }

    @Test
    public void testDevide() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "2.1 / 0.7";
        List<String> list = parseRecord.parse(record);
        assertEquals(3.0000000000000004, Calculate.calculate(list), 0.0);
    }

    @Test(expected = java.lang.ArithmeticException.class)
    public void testDevideNull() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "2.1 / 0";
        List<String> list = parseRecord.parse(record);
        assertEquals(0, Calculate.calculate(list), 0.0);
    }

    @Test
    public void testExpression() {
        ParseRecord parseRecord = new ParseRecord();
        String record = "2.0/23 + 3 * 9 - (26 -7.5)";
        List<String> list = parseRecord.parse(record);
        assertEquals(8.586956521739129, Calculate.calculate(list), 0.0);
    }
}

