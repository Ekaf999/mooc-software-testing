package tudelft.sum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;


public class TwoNumbersSumTest {

    @Test
    public void simple(){
        TwoNumbersSum adder = new TwoNumbersSum();
        ArrayList<Integer> result = adder.addTwoNumbers(new ArrayList<Integer>(Arrays.asList(1,2,3)),
                                                  new ArrayList<Integer>(Arrays.asList(8,9)));
        Assertions.assertEquals(new ArrayList<Integer>(Arrays.asList(2,1,2)), result);
    }

    @Test
    public void nines(){
        TwoNumbersSum adder = new TwoNumbersSum();
        ArrayList<Integer> result = adder.addTwoNumbers(new ArrayList<Integer>(Arrays.asList(9,9,9)),
                new ArrayList<Integer>(Arrays.asList(9,9,9)));
        Assertions.assertEquals(new ArrayList<Integer>(Arrays.asList(1,9,9,8)), result);
    }

}
