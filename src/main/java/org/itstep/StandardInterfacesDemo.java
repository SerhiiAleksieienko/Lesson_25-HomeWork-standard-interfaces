package org.itstep;

import java.util.*;

public class StandardInterfacesDemo {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Вася", "Пупкін", 23),
                new Student("Маша", "Дев'яткіна", 33),
                new Student("Рома", "Кавун", 24),
        };
        System.out.println("Arrays.toString(students) = " + Arrays.toString(students));
        Arrays.sort(students); //int i = students[0].compareTo(students[1]);
        System.out.println("Arrays.toString(students) = " + Arrays.toString(students));
        StudentComparatorByAge comparatorByAge = new StudentComparatorByAge();
        Arrays.sort(students, comparatorByAge); // int i = comparatorByAge.compare(students[0], students[1]);
        System.out.println("Arrays.toString(students) = " + Arrays.toString(students));



        System.out.println("__________________________________________");//удалить
        System.out.println("Проверка вывода через перечисление");//удалить

        for (Student student : students) {

            System.out.println(student);
        }

        Academy academy = new Academy("IT Step");
        academy.addStudent(students[0]);
        academy.addStudent(students[1]);
        academy.addStudent(students[2]);

        System.out.println("__________________________________________");//удалить
        System.out.println("Проверка вывода через перечисление!!!!!!!!!!");//удалить
        for (Object student : academy) {
            System.out.println(student);
        }

//        List list = new ArrayList();
//        for(Object o: list) {
//            System.out.println(o);
//        }
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Object o = iterator.next();
//            System.out.println(o);
//        }

        // Animal animal = new Dog();
        // (Cat(animal)).scratch(); <- ClassCastException
    }
}

class StudentComparatorByAge implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Student) o1).getAge() - ((Student) o2).getAge();
    }
}

class AcademyIterator implements Iterator {

    private final Student[] students;
    private int index;

    public AcademyIterator(Student[] students) {
        this.students = students;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return ++index < students.length;
    }

    @Override
    public Object next() {
        return students[index];
    }
}

class Academy implements Iterable {
    private final String name;
    private Student[] students = new Student[0];

    public Academy(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students = Arrays.copyOf(students, students.length + 1);
        students[students.length - 1] = student;
    }

    @Override
    public Iterator iterator() {
        return new AcademyIterator(students);
    }
}

class Student implements Comparable {
    private String firstName;
    private String lastName;
    private int age;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return lastName.compareTo(((Student) o).lastName);
    }
}
