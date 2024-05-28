package TPEProg3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private int tiempoTotal;
	private HashMap<String, Tarea> mapaTareas;
	private HashMap<String, Procesador> mapProcesadores;
	private List<Tarea> ListaCritica ;
	private List<Tarea> ListaNoCritica;
	private List<Tarea> listTareas;
	//private List<Tarea> visitados;

	public Servicios(String pathProcesadores, String pathTareas){
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.mapaTareas = new HashMap<>();
		this.mapProcesadores = new HashMap<>();
		this.ListaCritica = new ArrayList<>();
		this.ListaNoCritica = new ArrayList<>();
		this.listTareas = new ArrayList<>();
		this.tiempoTotal = 0;
	}
	
	/*
     * O(1) donde 1 es cada Tarea
     */
	
	public void addTarea(String ID,String nombre, Integer tiempo,boolean critica,Integer prioridad) {
		if(!contieneTarea(ID)) {
			Tarea t = new Tarea(ID,nombre, tiempo, critica, prioridad);
			mapaTareas.put(ID, t);
			listTareas.add(t);
			addTareaCritica(t);
		}
	}

	private boolean contieneTarea(String id) {
		return this.mapaTareas.containsKey(id);
		}
	
	public Tarea servicio1(String ID) {
		return mapaTareas.get(ID);
	}
    
    /*
     * O(n) donde n son las iteraciones posibles
     * preguntar !
     */
	
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica){
			return ListaCritica;
		}
		return ListaNoCritica;
	}

	private void addTareaCritica(Tarea t) {
		if(t.isCritica()) {
			ListaCritica.add(t); 
		   }
		   else{
			   ListaNoCritica.add(t);
		   }
	}	
	
    /*
     *El siguiente metodo es O(n), aunque entendemos que lo podriamos realizar con arbol binario de busqueda y en el peor de los casos su complejidad seria O(n).
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> Resultado= new ArrayList<>();
		for(Tarea t: mapaTareas.values()) {
			if(t.getPrioridad()<=prioridadSuperior && t.getPrioridad()>= prioridadInferior) {
				Resultado.add(t);
			}
		}
		return Resultado;
	}


	private int calcularTiempoTotal(HashMap<String, Procesador> asignacion) {
		int tiempoTotal = 0;
		for (Procesador procesador : asignacion.values()) {
			tiempoTotal += procesador.getTiempoTotal();
		}
		return tiempoTotal;
	}

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	
	public void asignacionTareas(int tiempo){
		Estado estado = new Estado();			
		backAsignacionTareas(tiempo, estado, listTareas);
		System.out.println(estado.toString());
	}
									
	private void backAsignacionTareas(int tiempo, Estado estado, List<Tarea> listTareas){
		//condicion de corte, quedarnos sin tareas de nuestro hashMap
		if(listTareas.isEmpty()){ //primer condicion, que no haya mas tareas
			estado.incrementarEstado();
			if(estado.esLaMejorSolucion()){ //segunda condicion que el estado tenga mejor solucion que la guardada como mejor
				estado.actualizarSolucion();
			}
		}
		else{
			Tarea tarea = listTareas.remove(0); //obtiene el primer elemento de listTareas y lo elimina
			Iterator<Procesador> itProcesador = obtProcesadores();
			while(itProcesador.hasNext()){
				Procesador proc = itProcesador.next();
				if(proc.cumpleCondicion(tarea, tiempo)){
					//proc.addTarea(tarea); //preguntar
					estado.addTarea(tarea, proc);
					backAsignacionTareas(tiempo, estado, listTareas);
					estado.removeTarea(tarea, proc);
					//proc.removeTarea(tarea);
				}
			}
			listTareas.add(0, tarea);
		}
	}

	public void addProcesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, int anio_procesamiento){
		Procesador procesador = new Procesador(id_procesador, codigo_procesador, esta_refrigerado, anio_procesamiento);
		this.mapProcesadores.put(id_procesador, procesador);
	}

	public Iterator<Procesador> obtProcesadores(){
		return this.mapProcesadores.values().iterator();
	}
}