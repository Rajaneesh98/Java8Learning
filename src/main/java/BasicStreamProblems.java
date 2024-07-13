import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BasicStreamProblems {

    public record Person(String Name, Integer age){ };

    public static void main(String[] args) {
        //1.Sum of List
        List<Integer> integerList = Arrays.asList(3,11,4,2,13,5,8);
        int sum = integerList.stream().mapToInt(a -> a).sum();
        System.out.println("sum: "+sum);

        //2.largest num of list
        //1st way
        Comparator<Integer> customComparator = (o1, o2) -> o1-o2;
        Optional<Integer> max = integerList.stream().max(customComparator);
        System.out.println("1st way: "+max.get());
        //2nd way
        Optional<Integer> i = integerList.stream().max(Comparator.naturalOrder());
        System.out.println("2nd way: "+i.get());
        //3rd way
        OptionalInt max1 = integerList.stream().mapToInt(Integer::intValue).max();
        System.out.println("3rd way: "+max1.getAsInt());

        //3.Given a List of strings, write a program to count the number of strings that start with a specific character using streams.
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "coconut", "apple");
        long a1 = fruits.stream().filter(str -> str.startsWith("a")).count();
        System.out.println("Count of string starting with a: "+a1);
        long c1 = fruits.stream().filter(str -> str.charAt(0)=='c').count();
        System.out.println("Count of string starting with c: "+c1);

        //4.Write a program to convert a List of strings to uppercase using streams.
        fruits.stream().map(str->str.toUpperCase()).forEach(System.out::println);

        //5.Given a List of integers, write a program to filter out the even numbers using streams.
        long count = integerList.stream().filter(num -> num % 2 == 0).count();
        System.out.println("Count of even number: "+count);

        //6.Write a program to find the average of a List of floating-point numbers using streams.
        List<Double> numsFloat = Arrays.asList(1.3, 2.4, 3.1, 4.2, 5.4);
        OptionalDouble average = numsFloat.stream().mapToDouble(Double::doubleValue).average();
        System.out.println("Average: "+average.getAsDouble());

       //7.Given a List of strings, write a program to concatenate all the strings using streams.
        //1st way
        Optional<String> resStr1 = fruits.stream().reduce((s, s2) -> s+","+s2);
        System.out.println(resStr1.get());
        // check the difference between the above and below codes.
        String resStr1Alt = fruits.stream().reduce("",(s, s2) -> s+","+s2);
        System.out.println(resStr1Alt);
        //to correct the above code
        resStr1Alt = fruits.stream().reduce("", (s,s2)->s.isEmpty() ? s2: s+","+s2);
        System.out.println(resStr1Alt);
        //2nd way
        String resStr2 = fruits.stream().collect(Collectors.joining(","));
        System.out.println(resStr2);

        //8.Write a program to remove duplicate elements from a List using streams.
        fruits.stream().distinct().forEach(System.out::println);

        //9.Given a List of objects, write a program to sort the objects based on a specific attribute using streams.
        //For person class look at the Record Person method above Java 16 feature.
        List<Person> people = Arrays.asList(
                new Person("Alice", 21),
                new Person("Anna", 29),
                new Person("Bob", 30),
                new Person("Barbie", 56),
                new Person("Charlie", 20)
        );

        //Two ways to write the Comparator
        //people.stream().sorted((per1,per2)->per1.age()- per2.age()).forEach(System.out::println);
        people.stream().sorted(Comparator.comparingInt(Person::age)).forEach(System.out::println);

        //10.Write a program to check if all elements in a List satisfy a given condition using streams.
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        boolean b = numbers.stream().allMatch(num -> num % 2 == 0);
        System.out.println("All Numbers satisfy the condition: "+b);

    }
}


