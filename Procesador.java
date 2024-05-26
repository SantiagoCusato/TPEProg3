package TPEProg3;

public class Procesador {
    private int id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int anio_procesamiento;

    public Procesador(int id_procesador, String codigo_procesador, boolean esta_refrigerado, int anio_procesamiento) {
        this.id_procesador = id_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_procesamiento = anio_procesamiento;
    }

    public int getId_procesador() {
        return id_procesador;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public int getAnio_procesamiento() {
        return anio_procesamiento;
    }

    public String getCodigo_procesador() {
        return codigo_procesador;
    }
}
