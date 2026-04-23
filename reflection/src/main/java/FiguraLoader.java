import groovy.lang.GroovyClassLoader;
import java.util.Map;

public class FiguraLoader {

    public static Figura cargar(String nombre) throws Exception {
        try {
            // Intento Nativo (Reflexión pura)
            Class<?> clazz = Class.forName(nombre);
            System.out.println("[SISTEMA] Cargando clase NATIVA: " + nombre);
            return (Figura) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            // Intento Dinámico (Groovy Scripting)
            System.out.println("[SISTEMA] '" + nombre + "' no existe como .java. Usando GROOVY...");
            String script = obtenerCodigoGroovy(nombre);
            GroovyClassLoader loader = new GroovyClassLoader();
            Class<?> clazzDinamica = loader.parseClass(script);
            return (Figura) clazzDinamica.getDeclaredConstructor().newInstance();
        }
    }

    private static String obtenerCodigoGroovy(String nombre) {
        return switch (nombre) {
            case "Pentagono" -> """
                import java.util.Map;
                class Pentagono implements Figura {
                    double lado;
                    void setParametros(Map p) { this.lado = Double.parseDouble(p.lado.toString()) }
                    double area() { return 1.72 * lado * lado }
                    double perimetro() { return 5 * lado }
                    void setDefaultParams() {}
                }
                """;
            case "Triangulo" -> """
                import java.util.Map;
                class Triangulo implements Figura {
                    double base, altura;
                    void setParametros(Map p) { 
                        this.base = Double.parseDouble(p.base.toString());
                        this.altura = Double.parseDouble(p.altura.toString());
                    }
                    double area() { return (base * altura) / 2 }
                    double perimetro() { return base * 3 }
                    void setDefaultParams() {}
                }
                """;
            case "Circulo" -> """
                import java.util.Map;
                class Circulo implements Figura {
                    double radio;
                    void setParametros(Map p) { this.radio = Double.parseDouble(p.radio.toString()) }
                    double area() { return Math.PI * radio * radio }
                    double perimetro() { return 2 * Math.PI * radio }
                    void setDefaultParams() {}
                }
                """;
            default -> throw new RuntimeException("No hay script definido para " + nombre);
        };
    }
}