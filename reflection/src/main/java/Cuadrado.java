import java.util.Map;

public class Cuadrado implements Figura {
    private double lado;

    @Override
    public void setParametros(Map<String, Object> params) {
        // Esto permite que el Main le pase el valor en tiempo de ejecución
        if (params.containsKey("lado")) {
            this.lado = Double.parseDouble(params.get("lado").toString());
        }
    }

    @Override
    public double area() { return lado * lado; }

    @Override
    public double perimetro() { return 4 * lado; }

    @Override
    public void setDefaultParams() { this.lado = 4.0; }
}