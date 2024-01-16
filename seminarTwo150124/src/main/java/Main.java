import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw from seminar two");
        int[] array = new int[]{0, 1, 3, 4, 5, 0, 0};
        System.out.println(countEvenNumbers(array));
        System.out.println(differenceBetweenMaxAndMin(array));
        System.out.println(isTwoZerosNextToEachOther(array));
        printMatrixSpiralInt(10, 8);
    }

    /*
    1. Написать метод, возвращающий количество чётных элементов массива.
    countEvens([2, 1, 2, 3, 4]) → 3
    countEvens([2, 2, 0]) → 3
    countEvens([1, 3, 5]) → 0
    */
    public static int countEvenNumbers(int[] array) {
        return (int) Arrays.stream(array).filter(x -> x % 2 == 0).count();
    }

    /*
    2. Написать функцию, возвращающую разницу между самым большим
    и самым маленьким элементами переданного не пустого массива.
    */
    public static int differenceBetweenMaxAndMin(int[] array) {
        if (array.length == 0) {
            throw new RuntimeException("Array must not be empty");
        }
        int min = array[0];
        int max = array[0];
        for (int number : array) {
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }
        return max - min;
    }

    /*
    3. Написать функцию, возвращающую истину, если в переданном массиве есть два
    соседних элемента, с нулевым значением.
    */
    public static boolean isTwoZerosNextToEachOther(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == 0 && array[i + 1] == 0) {
                return true;
            }
        }
        return false;
    }

    /*
    4*. Требуется написать метод, принимающий на вход размеры двумерного массива
    и выводящий массив в виде инкременированной цепочки чисел, идущих по спирали.
    */
    public static void printMatrixSpiralInt(int columns, int rows) {
        int[][] matrix = new int[rows][columns];
        int value = 1;
        int rowIndex = 0;
        int columnIndex = 0;
        int countProcessedElement = 0;
        boolean toRight = true;
        boolean toDown = true;
        int allElements = rows * columns;

        while (countProcessedElement < allElements) {
            for (int i = 0; i < columns; i++) {
                matrix[rowIndex][columnIndex] = value;
                value++;
                countProcessedElement++;
                if (toRight && i != columns - 1) {
                    columnIndex++;
                }
                if (!toRight && i != columns - 1) {
                    columnIndex--;
                }
            }

            if (toRight) {
                rowIndex++;
                toRight = false;
            } else {
                rowIndex--;
                toRight = true;
            }
            rows--;

            for (int i = 0; i < rows; i++) {
                matrix[rowIndex][columnIndex] = value;
                value++;
                countProcessedElement++;
                if (toDown && i != rows - 1) {
                    rowIndex++;
                }
                if (!toDown && i != rows - 1) {
                    rowIndex--;
                }
            }

            if (toDown) {
                columnIndex--;
                toDown = false;
            } else {
                columnIndex++;
                toDown = true;
            }

            columns--;
        }

        for (int[] ints : matrix) {
            for (int number : ints) {
                System.out.print(number + "\t");
            }
            System.out.println();
        }
    }

}
