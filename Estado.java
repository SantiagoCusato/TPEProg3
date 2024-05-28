package TPEProg3;

import java.util.HashMap;
import java.util.Map;


public class Estado {
    private HashMap<String, Procesador> asignacion_tareas; //asignacion actual
    private HashMap<String , Procesador> mejor_asignacion; //asignacion final
    private int cont_estado;
    private int mejorTiempoTotal;


    public Estado() {
        this.asignacion_tareas = new HashMap<>();
        this.mejor_asignacion = new HashMap<>();
        this.cont_estado = 0;
        this.mejorTiempoTotal = Integer.MAX_VALUE; // Inicializamos con un valor alto
    }

    public void incrementarEstado(){
        this.cont_estado++;
        if(calcularTiempoTotal(mejor_asignacion) == 0){
            actualizarSolucion();
        }
    }

    public boolean esLaMejorSolucion(){
        int tiempoActual = calcularTiempoTotal(asignacion_tareas);
        //System.out.println(tiempoActual);
        int mejorTiempo = calcularTiempoTotal(mejor_asignacion);
        //System.out.println(mejorTiempo);
        return tiempoActual<=mejorTiempo;
    }

    private int calcularTiempoTotal(HashMap<String, Procesador> asignacion) {
        int tiempoTotal = 0;
        for (Procesador procesador : asignacion.values()) {
            tiempoTotal += procesador.getTiempoTotal();
        }
        return tiempoTotal;
    }

    public void actualizarSolucion(){ // ver como hacer pero es esa la idea
        this.mejor_asignacion.clear();
        this.mejor_asignacion.putAll(asignacion_tareas);
        //preguntar si actualizar el tiempo total
        this.mejorTiempoTotal = calcularTiempoTotal(mejor_asignacion);
    }

    public void addTarea(Tarea tarea, Procesador proc){
        String id = tarea.getId();
        proc.addTarea(tarea);
        this.asignacion_tareas.put(id, proc);

    }

    public void removeTarea(Tarea tarea, Procesador proc){
        String id = tarea.getId();
        proc.removeTarea(tarea);
        this.asignacion_tareas.remove(id);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado [mejor_asignacion={\n");
        for (Map.Entry<String, Procesador> entry : mejor_asignacion.entrySet()) {
            sb.append("    ").append(entry.getKey()).append("=").append(entry.getValue().toString()).append(",\n");
        }
        sb.append("}, cont_estado=").append(cont_estado).append("]");
        return sb.toString();
    }

    public HashMap<String, Procesador> getAsignacionTareas() {
        return asignacion_tareas;
    }
    
}
