import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        if (L.isEmpty()) return 0;
        int sum = 0;
        for (Integer i : L) {
            sum += i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evens = new ArrayList<>();
        for (Integer i : L) {
            if (i % 2 == 0) evens.add(i);
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>(L1); // 把 L1 放进 Set，便于快速查找

        for (Integer num : L2) {
            if (set.contains(num) && !result.contains(num)) {
                result.add(num); // 避免重复添加
            }
        }

        return result;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
