public class Operador {

    public static <T extends Comparable<T>> T[] criterioAscendente(T a[]) {

        T temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 1; j < a.length; j++) {
                if(a[j] != null && a[j-1] != null && (a[j].compareTo(a[j-1]) < 0)) {
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return a;
    }

    /*public static <T extends Comparable<T>> T criterioDescendente(T a[]) {

        T min = a[0];
        for (int i = 1; i < a.length; i++) {

            // implementamos compareTo en la clase Agency
            if (a[i].compareTo(min) >= 0) {
                min = a[i];
            }
        }
        return min;
    }*/

}
