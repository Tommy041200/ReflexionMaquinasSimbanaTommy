/*import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void ejecutarFigura(String tipo, Figura fg) {
        fg.setDefaultParams();
        System.out.println("[" + tipo + "] P=" + fg.perimetro());
        System.out.println("[" + tipo + "] A=" + fg.area());
    }

    public static void main(String[] args){
        List<String> todasLasClases = new ArrayList<>();
        todasLasClases.add("Cuadrado");
        //todasLasClases.add("Circulo");

        //... todas las clases


        //Leer archivos de la carpeta de scrips y agregar al array
       /* try {
            for(String nombreClase : todasLasClases){
                //Verificar si es clase nativa
                Class clazz = Class.forName(nombreClase);
                //Arrancar motor de grovvy
                //Compilar clase en tiempo de ejecución
                Figura fg = (Figura) clazz.newInstance();
                fg.setDefaultParams();
                System.out.println(clazz.getName());
                System.out.println("P="+fg.perimetro());
                System.out.println("A="+fg.area());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            for(String nombreClase : todasLasClases){
                Figura fg = null;

                try {
                    // PASO 1: Intentar cargar como clase nativa (Reflexión)
                    Class<?> clazz = Class.forName(nombreClase);
                    fg = (Figura) clazz.getDeclaredConstructor().newInstance();
                    System.out.println("Cargada como clase nativa: " + clazz.getName());
                } catch (ClassNotFoundException e) {
                    // PASO 2: Si no existe, cargar con Groovy (Punto clave del deber)
                    System.out.println("Buscando script externo para: " + nombreClase);

                    // Aquí simulas el código que vendría de un archivo o servicio externo
                    String scriptExterno = "class " + nombreClase + " implements Figura { " +
                            "  double lado = 5; " +
                            "  double area() { return lado * lado * 1.72; } " + // Ejemplo para Pentágono
                            "  double perimetro() { return lado * 5; } " +
                            "  void setDefaultParams() { } " +
                            "}";

                    groovy.lang.GroovyClassLoader loader = new groovy.lang.GroovyClassLoader();
                    Class<?> dynamicClass = loader.parseClass(scriptExterno);
                    fg = (Figura) dynamicClass.getDeclaredConstructor().newInstance();
                    System.out.println("Cargada dinámicamente con Groovy: " + nombreClase);
                }

                if (fg != null) {
                    fg.setDefaultParams();
                    System.out.println("P = " + fg.perimetro());
                    System.out.println("A = " + fg.area());
                    System.out.println("--------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> nombres = List.of("Cuadrado", "Pentagono", "Triangulo", "Circulo");

        for (String nombre : nombres) {
            try {
                //  Cargamos la figura usando el Loader
                Figura fg = FiguraLoader.cargar(nombre);

                //  Preparamos los parámetros dinámicos
                Map<String, Object> params = prepararParametros(nombre);

                //  Ejecutamos
                fg.setParametros(params);
                System.out.println("RESULTADO -> " + nombre + ": Area=" + fg.area() + " P=" + fg.perimetro());
                System.out.println("---------------------------------------------------");

            } catch (Exception e) {
                System.err.println("Error procesando " + nombre + ": " + e.getMessage());
            }
        }
    }

    private static Map<String, Object> prepararParametros(String nombre) {
        Map<String, Object> p = new HashMap<>();
        switch (nombre) {
            case "Cuadrado", "Pentagono" -> p.put("lado", 10.0);
            case "Triangulo" -> { p.put("base", 10.0); p.put("altura", 5.0); }
            case "Circulo" -> p.put("radio", 5.0);
        }
        return p;
    }
}