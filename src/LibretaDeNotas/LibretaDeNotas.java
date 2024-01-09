package LibretaDeNotas;

import java.util.*;

public class LibretaDeNotas {
    /** Menú -
     *
     * Función que muestra en pantalla las diferentes acciones que puede realizar el cliente entorno a la libreta de notas.
     * */
    private static void displayMenu() {
        System.out.println("¿Que acción desea realizar con respecto a la libreta de notas del curso?");
        System.out.println("1.- Mostrar el promedio de notas por estudiante.");
        System.out.println("2.- Mostrar el libro de notas del curso.");
        System.out.println("3.- Mostrar la evaluación correspondiente del alumno.");
        System.out.println("4.- Mostrar si el promedio final del alumno es inferior o superior al promedio del curso.");
        System.out.println("5.- Salir del menú.");
    }

    /** Promedio de notas -
     *
     * Función que calcula el promedio de cada alumno en base a sus notas.
     * @return HashMap cuya clave es el nombre del alumno y su valor asociado es el promedio de este.*/
    private static Map<String, Double> promedioNotas(Map<String, List<Integer>> libretaDeNotas, Integer cantidadNotas) {
        // HashMap en el cual se almacenara el nombre del alumno junto con su promedio de notas.
        Map<String, Double> promedioAlumno = new HashMap<>();

        // Por cada alumno existente en la libreta de notas.
        for (String nombreAlumno : libretaDeNotas.keySet()){
            List<Integer> notas = libretaDeNotas.get(nombreAlumno);
            Integer sumaNotas = 0;
            Double promedio;

            // Se calcula su promedio de notas correspondiente.
            for (Integer nota : notas) {
                sumaNotas += nota;
            }
            promedio = (double) sumaNotas / cantidadNotas;

            // Se agrega par alumno-promedio como clave-valor.
            promedioAlumno.put(nombreAlumno, promedio);
        }

        // Muestra en pantalla los alumnos junto con sus respectivos promedios.
        for (Map.Entry<String, Double> entry : promedioAlumno.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return promedioAlumno;
    }

    /** Libreta de notas -
     *
     * Función que muestra en pantalla los datos existentes en la libreta de notas
     * */
    private static void libretaDeNotas(Map<String, List<Integer>> libretaDeNotas) {
        for (Map.Entry<String, List<Integer>> entry : libretaDeNotas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /** Evaluación del alumno -
     *
     * A partir del nombre del alumno ingresado se evalúa si su promedio es aprobado o reprobado
     */
    private static void evaluacion(String nombreAlumno, Map<String, Double> promedioAlumno) {

    }

    /** Promedio del Alumno -
     *
     * Obtiene el promedio del alumno dentro del curso.
     * @param promediosAlumnos HashMap del promedio de cada uno de los alumnos del curso.
     * @param alumno Nombre del alumno al cual se le quiere buscar su promedio.
     *
     * @return El promedio del alumno. En caso de no existir muestra en pantalla un mensaje de error y retorna null*/
    private static Double promedioAlumno(Map<String, Double> promediosAlumnos, String alumno) {
        Double promedio = promediosAlumnos.get(alumno);
        if (promedio != null) {
            return promedio;
        }
        else {
            System.out.println("El alumno " + alumno + "no existe en el curso.");
            return null;
        }
    }

    /** Main -
     *
     * Entrada al programa */
    public static void main(String[] args) {
        Integer cantidadAlumnos;
        Integer cantidadNotas;

        Map<String, List<Integer>> libretaDeNotas = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido a la Libreta de Notas del curso! Por favor, introduzca la cantidad de alumnos en este curso: ");
        cantidadAlumnos = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la cantidad de notas que cada alumno tiene: ");
        cantidadNotas = Integer.parseInt(scanner.nextLine());

        // Ingreso nombre del alumno 'i'
        for (int i = 0; i < cantidadAlumnos; i++) {
            String nombreAlumno;
            List<Integer> notas = new ArrayList<>();

            System.out.println("Ingrese el nombre del " + (i+1)+ "°" +" Alumno");
            nombreAlumno = scanner.nextLine();

            // Ingreso notas del alumno 'i' correspondiente
            for (int j = 0; j < cantidadNotas; j++) {
                Integer nota;

                System.out.println("Ingrese la " + (j + 1) +"° del Alumno "+ nombreAlumno + " : ");
                nota = Integer.parseInt(scanner.nextLine());
                notas.add(nota);
            }

            // Se agrega par alumno-notas como clave-valor.
            libretaDeNotas.put(nombreAlumno,notas);
        }

        /*
           Mientras la opción seleccionada por el usuario sea distinta de 5,
           el menú seguirá mostrándose después de mostrar en pantalla alguna de las opciones
        */
        int chosenOption;
        do {
            // Se llama a la función 'displayMenu()' para mostrar el menú correspondiente luego de haber ingresado los datos.
            displayMenu();
            chosenOption= Integer.parseInt(scanner.nextLine());

            switch (chosenOption) {
                case 1:
                    promedioNotas(libretaDeNotas, cantidadNotas);
                    break;
                case 2:
                    libretaDeNotas(libretaDeNotas);
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del Alumno: ");
                    String nombreAlumno = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\\ comparacion promedio curso //");

                    break;
                case 5:
                    System.out.println("¡Gracias por utilizar nuestro programa!," +
                            "¡Esperamos que encuentres la información útil y valiosa para el seguimiento académico del curso!");
                    System.exit(0);
                default:
                    System.out.println("La opción seleccionada no es válida. Por favor, elija nuevamente.");
            }
        } while (chosenOption != 5);


        scanner.close();

    }
}
