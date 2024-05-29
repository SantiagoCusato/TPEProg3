package TPEProg3;

import java.util.HashMap;


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
    }

    public boolean esLaMejorSolucion(){
        int tiempoActual = calcularTiempoMaximo(asignacion_tareas);
        //int mejorTiempo = calcularTiempoMaximo(mejor_asignacion);

        return tiempoActual<=mejorTiempoTotal;
    }

    protected int calcularTiempoMaximo(HashMap<String, Procesador> asignacion) {
        int tiempoTotal = 0;
        for (Procesador procesador : asignacion.values()) {
            if(procesador.getTiempoTotal()>tiempoTotal)
                tiempoTotal = procesador.getTiempoTotal();
        }
        return tiempoTotal;
    }

    public int calcularTiempo(){
        int tiempoTotal = 0;
        for (Procesador procesador : mejor_asignacion.values()) {
            if(procesador.getTiempoTotal()>tiempoTotal)
                tiempoTotal = procesador.getTiempoTotal();
        }
        return tiempoTotal;
    }

    public void actualizarSolucion(){ // ver como hacer pero es esa la idea
        this.mejor_asignacion.clear();
        
        this.mejor_asignacion.putAll(new HashMap<>(asignacion_tareas));
        //preguntar si actualizar el tiempo total
        this.mejorTiempoTotal = calcularTiempoMaximo(mejor_asignacion);
    }

    public void addTarea(Tarea tarea, Procesador proc){
        String id = proc.getId_procesador();
        if(asignacion_tareas.containsKey(id)){
            this.asignacion_tareas.get(id).addTarea(tarea);
        } else {
            proc.addTarea(tarea);
            this.asignacion_tareas.put(id, proc);
        }
            
    }

    public void removeTarea(Tarea tarea, Procesador proc){
        String id = proc.getId_procesador();
        this.asignacion_tareas.get(id).removeTarea(tarea);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado [mejor_asignacion={\n");
        for (String id : mejor_asignacion.keySet()) {
            sb.append("    ").append(mejor_asignacion.get(id)).append(",\n");
        }
        sb.append("}, cont_estado=").append(cont_estado).append("]");
        return sb.toString();
    }

    public HashMap<String, Procesador> getAsignacionTareas() {
        return asignacion_tareas;
    }
    
    public HashMap<String, Procesador> getMejorAsignacion() {
        return mejor_asignacion;
    }


}
