package LibretaDeNotas;

import java.util.*;

// TODO Hacer más bonito el menú. [DONE]
// TODO Implementar validaciones.
// TODO Hacer funciones que busquen la nota mínima y máxima de un alumno.
// TODO Refactor: Cambiar el tipo de las notas a Double

public class LibretaDeNotas {
    /** Menú -
     *
     * Función que muestra en pantalla las diferentes acciones que puede realizar el cliente entorno a la libreta de notas.
     * */
    private static void displayMenu() {
        System.out.println("                                                                                                ");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("| ¿Que acción desea realizar con respecto a la libreta de notas del curso?                     |");
        System.out.println("| 1.- Mostrar el promedio de notas por estudiante.                                             |");
        System.out.println("| 2.- Mostrar el promedio de notas de determinado estudiante, junto con su mayor y menor nota. |");
        System.out.println("| 3.- Mostrar el libro de notas del curso.                                                     |");
        System.out.println("| 4.- Mostrar la evaluación correspondiente del alumno.                                        |");
        System.out.println("| 5.- Mostrar si el promedio final del alumno es inferior o superior al promedio del curso.    |");
        System.out.println("| 6.- Salir del menú.                                                                          |");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("                                                                                                ");
    }

    /** Promedio de notas -
     *
     * Función que calcula el promedio de cada alumno en base a sus notas.
     * @param libretaDeNotas Libro de las notas de cada alumno existente en el curso.
     * @param cantidadNotas Valor ingresado inicialmente el cual representa la cantidad de notas que tiene cada alumno.
     * @return HashMap que retorna pares alumno-promedio como clave-valor..*/
    private static Map<String, Double> promedioNotas(Map<String, List<Double>> libretaDeNotas, Integer cantidadNotas) {
        // HashMap en el cual se almacenara el nombre del alumno junto con su promedio de notas.
        Map<String, Double> promedioAlumno = new HashMap<>();

        // Por cada alumno existente en la libreta de notas.
        for (String nombreAlumno : libretaDeNotas.keySet()){
            List<Double> notas = libretaDeNotas.get(nombreAlumno);
            Double sumaNotas = 0.0;
            Double promedio;

            // Se calcula su promedio de notas correspondiente.
            for (Double nota : notas) {
                sumaNotas += nota;
            }
            promedio = (double) sumaNotas / cantidadNotas;

            // Se agrega par alumno-promedio como clave-valor.
            promedioAlumno.put(nombreAlumno, promedio);
        }

        return promedioAlumno;
    }

    /** Libreta de promedios -
     *
     * Función que muestra en pantalla el nombre de los alumnos junto con su respectivo promedio.
     * @param promedioCurso HashMap cuyos pares clave-valor corresponden a alumno-promedio.*/
    private static void libretaDePromedios(Map<String, Double> promedioCurso) {
        // Muestra en pantalla los alumnos junto con sus respectivos promedios.
        for (Map.Entry<String, Double> entry : promedioCurso.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /** Libreta de notas -
     *
     * Función que muestra en pantalla los datos existentes en la libreta de notas
     * @param libretaDeNotas Libro de notas del curso actual.
     * */
    private static void libretaDeNotas(Map<String, List<Double>> libretaDeNotas) {
        for (Map.Entry<String, List<Double>> entry : libretaDeNotas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /** Evaluación del alumno -
     *
     * A partir del nombre del alumno ingresado se evalúa si su promedio es aprobado o reprobado
     * @param promedioCurso HashMap cuyo pares clave-valor corresponden a alumno-promedio.
     */
    private static void evaluacion(Map<String, Double> promedioCurso) {
        Scanner scannerAlumno = new Scanner(System.in);

        System.out.println("Ingrese el nombre del alumno: ");
        String nombreAlumno = scannerAlumno.nextLine();

        Double promedioAlumno = promedioAlumno(promedioCurso, nombreAlumno);

        if (promedioAlumno >= 0 && promedioAlumno <= 3) {
            System.out.println("La calificación final del alumno " + nombreAlumno +" es " + promedioAlumno +
                    ", por lo que su nota final es 'No aprobado'.");
        }
        else if (promedioAlumno > 3 && promedioAlumno <= 5) {
            System.out.println("La calificación final del alumno " + nombreAlumno +" es " + promedioAlumno +
                    ", por lo que su nota final es 'Insuficiente'.");
        }
        else if (promedioAlumno > 5 && promedioAlumno <= 8) {
            System.out.println("La calificación final del alumno " + nombreAlumno +" es " + promedioAlumno +
                    ", por lo que su nota final es 'Aceptable'.");
        }
        else if (promedioAlumno > 8 && promedioAlumno < 10) {
            System.out.println("La calificación final del alumno " + nombreAlumno +" es " + promedioAlumno +
                    ", por lo que su nota final es 'Sobresaliente'.");
        }
        else if (promedioAlumno == 10) {
            System.out.println("La calificación final del alumno " + nombreAlumno +" es " + promedioAlumno +
                    ", por lo que su nota final es 'Excelente'.");
        }
        else {
            System.out.println("Entrada inválida");
        }
    }

    /** Promedio del Alumno -
     *
     * Obtiene el promedio del alumno dentro del curso.
     * @param promediosAlumnos HashMap del promedio de cada uno de los alumnos del curso.
     * @param alumno Nombre del alumno al cual se le quiere buscar su promedio.
     *
     * @return El promedio del alumno. En caso de no existir muestra en pantalla un mensaje de error y retorna null.*/
    private static Double promedioAlumno(Map<String, Double> promediosAlumnos, String alumno) {
        Double promedio = promediosAlumnos.get(alumno);
        if (promedio != null) {
            return promedio;
        }
        else {
            System.out.println("El alumno " + alumno + " no existe en el curso.");
            return null;
        }
    }

    /** Nota mínima del alumno -
     *
     * Función que busca la menor nota del alumno dentro de su conjunto de notas.
     * @param libroDeNotas Libro de las notas de cada alumno existente en el curso.
     * @param alumno Nombre del alumno al cual se le quiere buscar su menor nota.
     * @return La nota mínima del alumno.
     * */
    private static Double notaMinima(Map<String, List<Double>>  libroDeNotas, String alumno) {
        List<Double> notas = libroDeNotas.get(alumno);
        Double notaMinima =  notas.get(0);

        for (int i = 1; i < notas.size(); i++) {
            if (notas.get(i) < notaMinima) {
                notaMinima = notas.get(i);
            }
        }

        return notaMinima;
    }

    /** Nota máxima del alumno -
     *
     * Función que busca la mayor nota del alumno dentro de su conjunto de notas.
     * @param libroDeNotas Libro de las notas de cada alumno existente en el curso.
     * @param alumno Nombre del alumno al cual se le quiere buscar su mayor nota.
     * @return La nota máxima del alumno.
     * */
    private static Double notaMaxima(Map<String, List<Double>>  libroDeNotas, String alumno) {
        List<Double> notas = libroDeNotas.get(alumno);
        Double notaMaxima =  notas.get(0);

        for (int i = 1; i < notas.size(); i++) {
            if (notas.get(i) > notaMaxima) {
                notaMaxima = notas.get(i);
            }
        }

        return notaMaxima;
    }

    /** Datos del alumno
     * //TODO documentation
     * */
    private static void datosAlumno(Map<String, List<Double>> libretaDeNotas, Map<String, Double> promedioCurso) {
        Scanner scannerAlumno = new Scanner(System.in);

        System.out.println("Ingrese el nombre del alumno: ");
        String nombreAlumno = scannerAlumno.nextLine();

        Double promedioAlumno = promedioAlumno(promedioCurso, nombreAlumno);
        Double notaMinima = notaMinima(libretaDeNotas, nombreAlumno);
        Double notaMaxima = notaMaxima(libretaDeNotas, nombreAlumno);

        System.out.println("El promedio final del alumno " + nombreAlumno + " es " + promedioAlumno + ". Su mayor nota fue " +
                notaMaxima + " mientras que su menor nota fue " + notaMinima + ".");

    }
    /** Promedio del curso -
     *
     * Función que calcula el promedio actual del curso.
     * @param promediosAlumnos HashMap del promedio de cada uno de los alumnos del curso.
     * @param cantidadAlumnos Valor ingresado inicialmente el cual representa la cantidad de alumnos presentes en el curso.
     * */
    protected static Double promedioCurso(Map<String, Double> promediosAlumnos, Integer cantidadAlumnos) {
        Double promedio, sumaNotas = 0.0;

        for (String nombreAlumno : promediosAlumnos.keySet()){
            sumaNotas += promediosAlumnos.get(nombreAlumno);
        }
        promedio = sumaNotas / cantidadAlumnos;

        return promedio;
    }

    /** Comparar el promedio del alumno con el promedio del curso -
     * TODO add documentation.
     * */
    protected static void compararNota(Map<String, Double> promedioCurso, Integer cantidadAlumnos){
        Scanner scannerAlumno = new Scanner(System.in);

        System.out.println("Ingrese el nombre del alumno: ");
        String nombreAlumno = scannerAlumno.nextLine();

        Double promedioAlumno = promedioAlumno(promedioCurso, nombreAlumno);
        Double promedioClase = promedioCurso(promedioCurso, cantidadAlumnos);

        if (promedioAlumno > promedioClase) {
            System.out.println("El promedio del alumno " + nombreAlumno + " es superior al promedio del curso.");
        }
        else if (promedioAlumno == promedioClase) {
            System.out.println("El promedio del alumno " + nombreAlumno + " es igual al promedio del curso.");
        }
        else {
            System.out.println("El promedio del alumno " + nombreAlumno + " es inferior al promedio del curso.");
        }
    }

    /** Main -
     *
     * Entrada al programa. */
    public static void main(String[] args) {
        Integer cantidadAlumnos;
        Integer cantidadNotas;

        Map<String, List<Double>> libretaDeNotas = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido a la Libreta de Notas del curso! Por favor, introduzca la cantidad de alumnos en este curso: ");
        cantidadAlumnos = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la cantidad de notas que cada alumno tiene: ");
        cantidadNotas = Integer.parseInt(scanner.nextLine());

        // Ingreso nombre del alumno 'i'
        for (int i = 0; i < cantidadAlumnos; i++) {
            String nombreAlumno;
            List<Double> notas = new ArrayList<>();

            System.out.println("Ingrese el nombre del " + (i+1)+ "°" +" Alumno");
            nombreAlumno = scanner.nextLine();

            // Ingreso notas del alumno 'i' correspondiente
            for (int j = 0; j < cantidadNotas; j++) {
                Double nota;

                System.out.println("Ingrese la " + (j + 1) +"° del Alumno "+ nombreAlumno + " : ");
                nota = Double.parseDouble(scanner.nextLine());
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

            Map<String, Double> promedioCurso = promedioNotas(libretaDeNotas, cantidadNotas);

            switch (chosenOption) {
                case 1:
                    libretaDePromedios(promedioCurso);
                    break;
                case 2:
                    datosAlumno(libretaDeNotas, promedioCurso);
                    break;
                case 3:
                    libretaDeNotas(libretaDeNotas);
                    break;
                case 4:
                    evaluacion(promedioCurso);
                    break;
                case 5:
                    compararNota(promedioCurso, cantidadAlumnos);
                    break;
                case 6:
                    System.out.println("¡Gracias por utilizar nuestro programa!," +
                            "¡Esperamos que encuentres la información útil y valiosa para el seguimiento académico del curso!");
                    System.exit(0);
                default:
                    System.out.println("La opción seleccionada no es válida. Por favor, elija nuevamente.");
            }
        } while (chosenOption != 6);


        scanner.close();
    }
}
