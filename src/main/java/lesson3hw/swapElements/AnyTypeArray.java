package lesson3hw.swapElements;

import java.util.Arrays;

public class AnyTypeArray<T> {
    private T[] array;

    public AnyTypeArray(T... array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "AnyTypeArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public void swapElements(int index1, int index2) {
        T buffer;
        if (index1 < 0 || index1 > array.length || index2 < 0 || index2 > array.length) {
            System.out.println("Некорректные индексы элементов для обмена");
            return;
        }
        buffer = array[index1];
        array[index1] = array[index2];
        array[index2] = buffer;
        System.out.println("Массив изменен успешно");
    }
}
