package by.ruslan.radevich;

import by.ruslan.radevich.model.Animal;
import by.ruslan.radevich.model.Car;
import by.ruslan.radevich.model.Examination;
import by.ruslan.radevich.model.Flower;
import by.ruslan.radevich.model.House;
import by.ruslan.radevich.model.Person;
import by.ruslan.radevich.model.Student;
import by.ruslan.radevich.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
        task13();
//        task14();
//        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static void task1() {

        int zooIndex = 3;
        int startIndex = zooIndex * 7;

        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.toList());


        List<Animal> animalsInThirdZoo = animals.stream()
                .skip(startIndex)
                .limit(7)
                .toList();

        System.out.println("Animals in the third zoo:");
        animalsInThirdZoo.forEach(System.out::println);
    }


    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()) && "Female".equals(animal.getGender()))
                .map(animal -> animal.getBread().toUpperCase())
                .forEach(System.out::println);
    }

    //Отобрать всех животных старше 30 лет и вывести все страны происхождения без дубликатов начинающиеся на "A"
    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(animal -> animal.startsWith("A"))
                .distinct()
                .forEach(animal -> System.out.println("task3: " + animal));

    }

    //    Подсчитать количество всех животных пола = Female. Вывести в консоль
    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        long female = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.print("Task4 " + female + " animals");

    }

    //    Взять всех животных возрастом 20 - 30 лет. Есть ли среди нах хоть один из страны Венгрия (Hungarian)? Ответ вывести в консоль
    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal
                        .getAge() <= 30 && animal.getOrigin().equals("Hungarian"))
                .forEach(animal -> System.out.println("Task5: " + animal));
    }

    //    Взять всех животных. Все ли они пола Male и Female ? Ответ вывести в консоль
    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean allHaveValidGender = animals.stream()
                .allMatch(animal -> animal.getOrigin().equals("Male") || animal.getOrigin().equals("Female"));
        System.out.println("Task6 " + allHaveValidGender);

    }

    //    Взять всех животных. Узнать что ни одно из них не имеет страну происхождения Oceania. Ответ вывести в консоль
    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean anyMatchOriginOceania = animals.stream()
                .anyMatch(animal -> animal.getOrigin().equals("Oceania"));
        System.out.println("Task7 " + anyMatchOriginOceania);
        if (anyMatchOriginOceania) {
            System.out.println("Task7" + " oceania");
        } else {
            System.out.println("Task7" + " No oceania");
        }
    }

    //    Взять всех животных. Отсортировать их породу в стандартном порядке и взять первые 100. Вывести в консоль возраст самого старого животного
    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(animal -> System.out.println("Task8 " + animal.getAge()));
    }

    //    Взять всех животных. Преобразовать их в породы, а породы в []char Вывести в консоль длину самого короткого массива
    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(animal -> animal.length)
                .min()
                .ifPresent(lengthAnimal -> System.out.println("Task9 " + lengthAnimal));

    }

    //    Взять всех животных. Подсчитать суммарный возраст всех животных. Вывести результат в консоль
    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .mapToInt(Animal::getAge)
                .reduce(Integer::sum)
                .ifPresent(sum -> System.out.println("Task10 " + sum));

    }

    //    Взять всех животных. Подсчитать средний возраст всех животных из индонезии (Indonesian).
//    Вывести результат в консоль
    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToDouble(Animal::getAge)
                .average()
                .ifPresent(animal -> System.out.println("Task11 " + animal + " average age animals from Indonesian"));
    }

    //    Во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины) возраст от 18 до 27 лет.
//    Преимущество отдаётся людям военной категории 1, на втором месте - военная категория 2,
//    и на третьем месте военная категория 3.
//    Отсортировать всех подходящих кандидатов в порядке их приоритета по военной категории.
//    Однако взять на обучение академия может только 200 человек. Вывести их в консоль.
    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(Main::filterForAgePerson)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(person -> System.out.println("Task12 " + person));
    }

    private static boolean filterForAgePerson(Person person) {
        LocalDate dateOfBirth = person.getDateOfBirth();
        int ageOfPerson = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return ageOfPerson >= 18 && ageOfPerson <= 27;
    }

    //    Надвигается цунами и в районе эвакуации требуется в первую очередь обойти дома и эвакуировать больных и раненых (из Hospital),
//    во вторую очередь детей и стариков (до 18 и пенсионного возраста) а после всех остальных.
//    В первый этап эвакуации мест в эвакуационном транспорте только 500.
//    Вывести всех людей попадающих в первый этап эвакуации в порядке приоритета (в консоль).
    public static void task13() {
        List<House> houses = Util.getHouses();
        List<Person> evacuees = houses.stream()
                .flatMap(house -> house.getPersonList().stream())
                .sorted((p1, p2) -> {
                    if ("Hospital".equals(p1.getOccupation()) && !"Hospital".equals(p2.getOccupation())) {
                        return -1;
                    } else if (!"Hospital".equals(p1.getOccupation()) && "Hospital".equals(p2.getOccupation())) {
                        return 1;
                    } else if ((filterForAge(p1) < 18 || filterForAge(p1) >= 65) && !(filterForAge(p2) < 18 || filterForAge(p2) >= 65)) {
                        return -1;
                    } else if (!(filterForAge(p1) < 18 || filterForAge(p1) >= 65) && (filterForAge(p2) < 18 || filterForAge(p2) >= 65)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .limit(500)
                .toList();

        evacuees.forEach(person -> System.out.println("task13: " + person));
    }

    private static int filterForAge(Person person) {
        return Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }


    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        students.stream() Продолжить ...
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

}
