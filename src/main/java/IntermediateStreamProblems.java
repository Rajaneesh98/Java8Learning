import java.util.*;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateStreamProblems {

    public record Student(String Name, Integer marks){};

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

        Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(num->num,Collectors.counting()))
                .entrySet().stream().filter(a->a.getValue()>1).forEach(a-> System.out.println(a.getKey()));


        // 5. How are duplicates removed from a given array in Java?

        int[] newarr =    Arrays.stream(arr).distinct()
                            .toArray();
        System.out.println(Arrays.toString(newarr));

        //6. Given a list of words, filter and print the palindromes
        List<String> strings = List.of("level", "hello", "radar", "world", "deed");

        //Normal way
        Predicate<String> palindromePredicate1 = (str) -> {
            for (int i = 0; i < str.length() / 2; i++) {
                if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                    return false;
                }
            }
            return true;
        };

        //Stream way 😒
        Predicate<String> palindromePredicate = (str) -> {
           return Stream.iterate(0,a->a<(str.length()/2),a->a+1)
                   .allMatch(a->str.charAt(a)==str.charAt(str.length()-a-1));
        };
        strings.stream().filter(palindromePredicate).forEach(System.out::println);

        //Shorter way reverse method from StringBuilder class
        List<String> palindromeWords = strings.stream().
                filter(str -> str.equals(new StringBuilder(str).reverse().toString())).collect(Collectors.toList());

        System.out.println(palindromeWords);

        // 7. How do you merge two sorted arrays into a single sorted array?
        int[] array1 = {1, 3,32, 5, 7};
        int[] array2 = {2, 4, 6,62, 8};

        //this would require understanding of new methods and also it does not matter if the arrays are sorted or not.
        int[] sortedArray = IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).sorted().toArray();
        System.out.println(Arrays.toString(sortedArray));

        //8. Given two lists of strings, concatenate them and remove duplicates.

        List<String> list1 = List.of("apple", "banana", "orange");
        List<String> list2 = List.of("banana", "kiwi", "grape");

        List<String> uniqueWords= Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());
        System.out.println(uniqueWords);

        // 9. Student Grade Classification - 70 and above pass

        List<Student> students = List.of(
                new Student("Alice", 85),
                new Student("Bob", 60),
                new Student("Charlie", 75),
                new Student("David", 90)
        );

        students.stream().filter(student -> student.marks()>70).forEach(System.out::println);

        //10. Given a list of strings, sort them according to increasing order of their length.

        List<String> fruits = Arrays.asList("Mango","pear" ,"Apple", "Banana", "Pineapple", "Kiwi");

        fruits.stream().sorted((o1, o2) -> o1.length()-o2.length()).forEach(System.out::println);
        //other way to write Comparator-
        fruits.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
    }
}
