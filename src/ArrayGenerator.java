import java.util.Arrays;
import java.util.Random;

public class ArrayGenerator {

    //константы, определяющие диапозон значений элементов массивов
    private static final int LOWER_VAL_OF_RANGE = -100;
    private static final int UPPER_VAL_OF_RANGE =  100;


    public static void main(String[] args) {

        ArrayGenerator arrayGenerator = null;
        int n = 0;
        int[][] arrays;

        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            System.out.println("wrong number of program environment variables!" + "\n" +
                                "please enter the environment variable " +
                                "from it the value for the variable n is taken");
            System.exit(1);
        }
        arrayGenerator = new ArrayGenerator();

        try {

            //вызов функции, реализующей алгоритм из задания
            arrays = arrayGenerator.createArraysOfRandomNumbers(n);

            arrayGenerator.printArrays(arrays);

        } catch (NegativeArraySizeException e) {
            e.printStackTrace();
        }
    }

    //функция, реализующая алгоритм из задания
    public int[][] createArraysOfRandomNumbers(int n) throws NegativeArraySizeException{

        if (n < 1) throw new NegativeArraySizeException("Input parameter is out of range! n = " + n);

        Random random = new Random();
        int[][] arrays = new int[n][];

        //создание и заполнение массивов
        for (int i = 0; i < arrays.length; i++) {

            int size;           //вновь сгенерированный случайный размер массива
            boolean matchFlag;  //true, если найдено совпадение вновь сгенерированного размера с предыдущими

            //генерация уникальных размеров массивов, путем простого перебора предыдущих значений
            while (true) {
                matchFlag = false;
                //установим границу диапазона случайных размеров массивов равную n*n
                size = random.nextInt(n * n) + 1;
                for (int j = 0; j < i; j++) {
                    if (size == arrays[j].length) {
                        matchFlag = true;
                        break;
                    }
                }
                if (matchFlag == false) break;
            }
            arrays[i] = new int[size];

            //заполнение каждого массива числами из диапазона
            for (int j = 0; j < arrays[i].length; j++) {
                arrays[i][j] = random.nextInt(UPPER_VAL_OF_RANGE - LOWER_VAL_OF_RANGE + 1) + LOWER_VAL_OF_RANGE;
            }
        }

        //сортировка
        for (int i = 0; i < arrays.length; i++) {
            if ((i % 2) == 0) {
                Arrays.sort(arrays[i]);
            } else {
                for (int j = 0; j < arrays[i].length; j++)
                    arrays[i][j] *= (-1);
                Arrays.sort(arrays[i]);
                for (int j = 0; j < arrays[i].length; j++)
                    arrays[i][j] *= (-1);
            }
        }

        return arrays;
    }

    public void printArrays(int[][] arrays) {

        for (int i = 0; i < arrays.length; i++) {
            System.out.printf("arrays[%6d] size = %6d  :  ", i, arrays[i].length);
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.printf("%6d", arrays[i][j]);
            }
            System.out.println();
        }
    }

}
