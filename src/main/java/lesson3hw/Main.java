package lesson3hw;

import lesson3hw.swapElements.AnyTypeArray;
import lesson3hw.fruitBoxes.Box;
import lesson3hw.fruitBoxes.Orange;
import lesson3hw.fruitBoxes.Apple;

public class Main {
    public static void main(String[] args) {

        // Тестируем метод, который меняет два элемента массива местами

        AnyTypeArray<Integer> testIntArray = new AnyTypeArray<>(1, 2, 3, 4, 5);
        System.out.println(testIntArray);
        testIntArray.swapElements(-2, 10);
        System.out.println(testIntArray);

        AnyTypeArray<String> testStringArray = new AnyTypeArray<>("test1", "test2", "test3", "test4", "test5");
        System.out.println(testStringArray);
        testStringArray.swapElements(1, 3);
        System.out.println(testStringArray);


        // Тестируем коробки с фруктами

        // Коробки для сравнения

        Orange[] orangesForBox1 = new Orange[10];
        for (int i = 0; i < orangesForBox1.length; i++) {
            orangesForBox1[i] = new Orange();
        }
        Box<Orange> orangeBox1 = new Box<>(orangesForBox1);

        Apple[] applesForBox1 = new Apple[15];
        for (int i = 0; i < applesForBox1.length; i++) {
            applesForBox1[i] = new Apple();
        }
        Box<Apple> appleBox1 = new Box<>(applesForBox1);


        System.out.println(orangeBox1.getWeight());
        System.out.println(appleBox1.getWeight());

        System.out.println(orangeBox1.compare(appleBox1));
        System.out.println(orangeBox1.compare(orangeBox1));
        System.out.println(appleBox1.compare(appleBox1));


        // Коробки для пересыпания
        Orange[] orangesForBox2 = new Orange[5];
        for (int i = 0; i < orangesForBox2.length; i++) {
            orangesForBox2[i] = new Orange();
        }
        Box<Orange> orangeBox2 = new Box<>(orangesForBox2);

        Orange[] orangesForBox3 = new Orange[7];
        for (int i = 0; i < orangesForBox3.length; i++) {
            orangesForBox3[i] = new Orange();
        }
        Box<Orange> orangeBox3 = new Box<>(orangesForBox3);

        Apple[] applesForBox2 = new Apple[3];
        for (int i = 0; i < applesForBox2.length; i++) {
            applesForBox2[i] = new Apple();
        }
        Box<Apple> appleBox2 = new Box<>(applesForBox2);

        Apple[] applesForBox3 = new Apple[2];
        for (int i = 0; i < applesForBox3.length; i++) {
            applesForBox3[i] = new Apple();
        }
        Box<Apple> appleBox3 = new Box<>(applesForBox3);

        System.out.println(orangeBox2);
        System.out.println(orangeBox3);
        orangeBox2.moveBoxContent(orangeBox3);
        System.out.println(orangeBox2);
        System.out.println(orangeBox3);

        System.out.println();

        System.out.println(appleBox2);
        System.out.println(appleBox3);
        appleBox3.moveBoxContent(appleBox2);
        System.out.println(appleBox2);
        System.out.println(appleBox3);

    }
}
