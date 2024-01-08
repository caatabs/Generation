package LibretaDeNotas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibretaDeNotas {
    /** Menú -
     *
     * Función que muestra en pantalla las diferentes acciones que puede realizar el cliente entorno a la libreta de notas.
     * */
    private static void displayMenu() {
        System.out.println("¿Que desea realizar con respecto a la libreta de notas del curso?");
        System.out.println("1.- Mostrar el promedio de notas por estudiante.");
        System.out.println("2.- Mostrar la evaluación correspondiente del alumno.");
        System.out.println("3.- Mostrar si el promedio final del alumno es inferior o superior al promedio del curso.");
        System.out.println("4.- Salir del menú.");
    }

    /** Promedio de notas -
     *
     * Función que calcula el promedio de cada alumno en base a sus notas.
     * @return HashMap cuya clave es el nombre del alumno y su valor asociado es el promedio de este.*/
    private static Map promedioNotas(Map<String, List<Integer>> libretaDeNotas, Integer cantidadNotas) {
        // HashMap en el cual se almacenara el nombre del alumno junto con su promedio de notas.
        Map<String, Integer> promedioAlumno = new HashMap<>();

        for (String nombreAlumno : libretaDeNotas.keySet()){
            List<Integer> notas = libretaDeNotas.get(nombreAlumno);
            Integer sumaNotas = 0, promedio;

            for (Integer nota : notas) {
                sumaNotas += nota;
            }
            promedio = sumaNotas / cantidadNotas;
            promedioAlumno.put(nombreAlumno, promedio);
        }

        return promedioAlumno;
    }

    public static void main(String[] args) {

    }
}
