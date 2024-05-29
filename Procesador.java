package TPEProg3;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int anio_procesamiento;
    private List<Tarea> listTareas;
    private int tareasCriticas;


    public Procesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, int anio_procesamiento) {
        this.id_procesador = id_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_procesamiento = anio_procesamiento;
        this.listTareas = new ArrayList<>();
        this.tareasCriticas = 0;
    }

    public String getId_procesador() {
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

    public void addTarea(Tarea t){  //chequear la cantidad de criticas
        this.listTareas.add(t);
        if(t.isCritica()){ 
            this.tareasCriticas++; //incremento tareasCriticas si es critica la tarea
        }
    }

    public void removeTarea(Tarea t){
        if(t.isCritica()){
            this.tareasCriticas--;
        }
        this.listTareas.remove(t);//chequear si la tarea es critica
    }

    public boolean cumpleCondicion(Tarea tarea, int tiempoX) {
        // Verifico si añadir esta tarea supera el límite de tiempo de tareas no refrigeradas
        int tiempoTotal = getTiempoTotal();
        if (!isEsta_refrigerado() && (tiempoTotal + tarea.getTiempo() > tiempoX)) { //preguntar si esta bien
            return false;
        }

        // Verifico si añadir esta tarea crítica supera el límite de 2 tareas críticas
        else if (tarea.isCritica() && (tareasCriticas >= 2)) {
            return false;
        }

        return true;
    }

    public int getTiempoTotal() {
        int tiempoTotal = 0;
        for (Tarea tarea : listTareas) {
            tiempoTotal += tarea.getTiempo();
        }
        return tiempoTotal;
    }

    //hacer una copia de procesador para ingresar solo la copia

    @Override
    public String toString() {
        return "Procesador [id_procesador=" + id_procesador +
                ", esta_refrigerado=" + esta_refrigerado +
                ", listTareas=" + listTareas + "]";
    }

    
}
