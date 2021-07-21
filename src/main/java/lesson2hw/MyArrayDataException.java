package lesson2hw;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int errCellX, int errCellY) {
        super("Не удалось преобразовать данные в ячейке: x=" + errCellX + ", y=" + errCellY);
    }
}
