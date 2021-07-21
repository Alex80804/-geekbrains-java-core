package lesson2hw;

public class Main {
    public static void main(String[] args) {
        String[][] correctInput = new String[4][4];
        String[][] incorrectSizeInput = new String[3][5];
        String[][] incorrectDataInput = new String[4][4];

        // Заполняем массивы
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                correctInput[i][j] = String.valueOf((int) (Math.random() * 10));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                incorrectSizeInput[i][j] = String.valueOf((int) (Math.random() * 10));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                incorrectDataInput[i][j] = String.valueOf((int) (Math.random() * 10));
                if (i == 3 & j == 1) {
                    incorrectDataInput[i][j] = "incorrect value";
                }
            }
        }

        // Суммируем массивы
        try {
            System.out.println("Сумма первого массива = " + processArrays(correctInput));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Сумма второго массива = " + processArrays(incorrectSizeInput));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Сумма третьего массива = " + processArrays(incorrectDataInput));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int processArrays(String[][] inputArray) throws MyArraySizeException, MyArrayDataException {
        int totalSum = 0;
        // Определяем неправильные размерности
        if (inputArray.length != 4 | inputArray[0].length != 4) {
            throw new MyArraySizeException(inputArray.length, inputArray[0].length);
        }
        //
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[0].length; j++) {
                try {
                    int checkValue = Integer.parseInt(inputArray[i][j]);
                    totalSum += checkValue;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return totalSum;
    }
}

