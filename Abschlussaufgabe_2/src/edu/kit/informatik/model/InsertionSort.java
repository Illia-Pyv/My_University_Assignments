package edu.kit.informatik.model;

/**
 * This class represents the insertion sort sorting algorithm that was used and
 * shown in the lectures and is used to sort an array either filled with numbers
 * or strings.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class InsertionSort {

    /**
     * This method sorts a string array in alphabetical order. Thereby the big
     * letters come first and the small ones come later.
     * 
     * @param list the array of strings which should be sorted
     * @return Returns the sorted string array in alphabetical order
     */
    public String[] sortAscending(String[] list) {
        String[] result = new String[list.length];
        for (int i = 1; i < list.length; i++) {
            result = insertAscending(list, i);
        }
        return result;
    }

    /**
     * This method sorts a string array descending in alphabetical order with "z"
     * coming before "a". Thereby the big letters come after the small ones, and the
     * small ones come first.
     * 
     * @param list the array of strings which should be sorted
     * @return Returns the sorted string array in reversed alphabetical order
     */
    public String[] sortDescending(String[] list) {
        String[] result = new String[list.length];
        for (int i = 1; i < list.length; i++) {
            result = insertDescending(list, i);
        }
        return result;
    }

    /**
     * This method sorts a number array of {@code long} ascending in numerical order.
     * 
     * <p> Let x and y be two numbers. If x < y then x comes before y in the sorted list.
     * 
     * @param list the array of numbers of type {@code long} which should be sorted
     * @return Returns the sorted number array in numerical order
     */
    public long[] sortAscending(long[] list) {
        long[] result = new long[list.length];
        for (int i = 1; i < list.length; i++) {
            result = insertAscending(list, i);
        }
        return result;
    }

    /**
     * This method sorts a number array of {@code long} descending in numerical order.
     * 
     * <p> Let x and y be two numbers. If x < y then y comes before x in the sorted list.
     * 
     * @param list the array of numbers of type {@code long} which should be sorted
     * @return Returns the sorted number array in reversed numerical order
     */
    public long[] sortDescending(long[] list) {
        long[] result = new long[list.length];
        for (int i = 1; i < list.length; i++) {
            result = insertDescending(list, i);
        }
        return result;
    }

    private long[] insertAscending(long[] list, int index) {
        for (int i = index; i > 0 && list[i - 1] > list[i]; i--) {
            long tmp = list[i - 1];
            list[i - 1] = list[i];
            list[i] = tmp;
        }
        return list;
    }

    private long[] insertDescending(long[] list, int index) {
        for (int i = index; i > 0 && list[i - 1] < list[i]; i--) {
            long tmp = list[i - 1];
            list[i - 1] = list[i];
            list[i] = tmp;
        }
        return list;
    }

    private String[] insertAscending(String[] list, int index) {
        for (int i = index; i > 0 && stringToNumber(list[i - 1]) > stringToNumber(list[i]); i--) {
            String tmp = list[i - 1];
            list[i - 1] = list[i];
            list[i] = tmp;
        }
        return list;
    }

    private String[] insertDescending(String[] list, int index) {
        for (int i = index; i > 0 && stringToNumber(list[i - 1]) < stringToNumber(list[i]); i--) {
            String tmp = list[i - 1];
            list[i - 1] = list[i];
            list[i] = tmp;
        }
        return list;
    }

    private long stringToNumber(String text) {
        String result = "";
        int asciiNumber = 0;
        long resultNumber = 0;
        for (int i = 0; i < text.length(); i++) {
            asciiNumber = text.charAt(i);
            result += asciiNumber;
        }
        resultNumber = Long.parseLong(result);
        return resultNumber;
    }
}
