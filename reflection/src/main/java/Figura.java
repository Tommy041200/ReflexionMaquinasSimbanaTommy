import java.util.Map;

public interface Figura {
    void setParametros(Map<String, Object> params); // <-- Esto es lo que te falta
    double area();
    double perimetro();
    void setDefaultParams();
}