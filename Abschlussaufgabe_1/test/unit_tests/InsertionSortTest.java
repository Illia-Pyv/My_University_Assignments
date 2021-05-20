package unit_tests;

import org.junit.Test;

import edu.kit.informatik.model.InsertionSort;

public class InsertionSortTest {
    
    InsertionSort sort = new InsertionSort();
    
    @Test
    public void sortByNumberTest() {
        long[] numbers = {5,9,4,2,3,784,1,655,8,45,12,35,458,59,264,61};
        sort.sortDescending(numbers);
        String result = "";
        for (long l : numbers) {
            result += l + ", ";
        }
        System.out.println(result);
    }

    @Test
    public void sortByStringTest() {
        String[] text = {"k","owie","ssid","abc","a","d","zzzzzz","sshd","aaaaaa"};
        sort.sortAscending(text);
        String result = "";
        for (String t : text) {
            result += t + ", ";
        }
        System.out.println(result);
    }
}
