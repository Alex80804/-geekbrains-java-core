package lesson2hw;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int dimX, int dimY) {
        super("Некорректный размер массива: длина " + dimX + ", ширина " + dimY);
    }
}
