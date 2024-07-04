import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateStreamProblems {
    public static void main(String[] args) {

        //1. Given a sentence, find and print the frequency of each word.
        String sentence = "Java is a language programming language. Java is versatile.";
        Map<String, Long> wordFrequencies = Stream.of(sentence.split("\\s+"))
                .collect(Collectors.groupingBy(str -> str.toLowerCase(), Collectors.counting()));

        System.out.println(wordFrequencies);

        //2. Given a list of integers, find out all the numbers starting with 1.
        List<Integer> nums = Arrays.asList(12, 13, 18, 21, 90, 11);

        List<Integer> numbersStartingWithOne = nums.stream().map(num -> num.toString()).filter(str -> str.startsWith("1"))
                .map(str -> Integer.valueOf(str)).collect(Collectors.toList());
        System.out.println(numbersStartingWithOne);
        //shorter way for the above

        numbersStartingWithOne= nums.stream()
                .filter(num -> String.valueOf(num).startsWith("1")).collect(Collectors.toList());
        System.out.println(numbersStartingWithOne);


        //3. Given a list of names, group them by their first letter, and then count the number of names in each group.

        String[] names = {"Alice", "Bob", "Charlie", "Amy", "Bill", "Anna"};
        Map<Character, Long> namesMap = Arrays.stream(names).collect(Collectors.groupingBy(str->str.charAt(0),Collectors.counting()));
        System.out.println(namesMap);

        // 4. Find and print duplicate numbers in an array if it contains multiple duplicates?
        int[] arr = {2,4,2,3,1,5, 5,78,3,1,5};

//        Arrays.stream(arr).boxed()
//                .collect(Collectors.groupingBy(num->num,Collectors.counting()))
//                .forEach();
    }
}
