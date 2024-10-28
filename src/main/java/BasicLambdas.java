package main.java;

import main.java.interfaces.Evaluate;
import main.java.interfaces.Functionable;
import main.java.interfaces.Printable;
import main.java.interfaces.Retrievable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {

  public static void main(String[] args) {
    consumer("Printable lambda");
    supplier();
    predicate();
    function();

    List<Person> listPeople = getPeople();
    sortAge(listPeople);
    sortName(listPeople);
    sortHeight(listPeople);
  }

  static void consumer(String s) {
    // lambda expression to define the print method
    Printable<String> printable = t -> System.out.println(t);
    printable.print(s);

    // method reference to define the print method
    Printable<String> printable2 = System.out::println;
    printable2.print(s);
  }

  static void supplier() {
    Retrievable<Integer> retrievable = () -> 11;
    System.out.println(retrievable.retrieve());

    // implement using a supplier
    Supplier<Integer> supplier = () -> 11;
    System.out.println(supplier.get());
  }

  static void predicate() {
    Evaluate<Integer> evaluate = t -> t < 0 ? true : false;
    System.out.println(evaluate.isNegative(-1));
    System.out.println(evaluate.isNegative(1));

    // implement using a predicate
    Predicate<Integer> predicate = t -> t < 0 ? true : false;
    System.out.println(predicate.test(-1));
    System.out.println(predicate.test(1));

    Predicate<Integer> isEven = i -> i%2 == 0 ? true : false;
    System.out.println(check(2, isEven));

    Predicate<String> beginsWithMr = s -> s.startsWith("Mr.");
    System.out.println(check("Mr. Joe Bloggs", beginsWithMr));
    System.out.println(check("Ms. Ann Bloggs", beginsWithMr));

    Predicate<Person> isAdult = p -> p.age() >= 18;
    Person person = new Person("Mike", 33, 1.8);
    System.out.println(check(person, isAdult));
  }

  static void function() {
    Functionable<Integer, String> functionable = i -> STR."The number is: \{i}";
    System.out.println(functionable.applythis(25));

    Function<Integer, String> function = i -> STR."The number is: \{i}";
    System.out.println(function.apply(25));
  }

  static void sortAge(List<Person> listPeople) {
    listPeople.sort((p1, p2) -> p1.age() - p2.age());
//    listPeople.forEach(p -> System.out.println(p));
    listPeople.forEach(System.out::println);
  }

  static void sortHeight(List<Person> listPeople) {
    listPeople.sort((p1, p2) -> p1.height().intValue() - p2.height().intValue());
//    listPeople.forEach(p -> System.out.println(p));
    listPeople.forEach(System.out::println);
  }

  static void sortName(List<Person> listPeople) {
    listPeople.sort((p1, p2) -> p1.name().length() - p2.name().length());
//    listPeople.forEach(p -> System.out.println(p));
    listPeople.forEach(System.out::println);
  }
  private static List<Person> getPeople() {
    List<Person> result = new ArrayList<>();
    result.add(new Person("Mike", 33, 1.8));
    result.add(new Person("Mary", 25, 1.4));
    result.add(new Person("Alan", 34, 1.7));
    result.add(new Person("Zoe", 30, 1.5));
    return result;
  }
  private static <T> boolean check(T t, Predicate<T> predicate) {
    return predicate.test(t);
  }


}

