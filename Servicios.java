package TPEProg3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private int tiempoTotal;
	private HashMap<String,Tarea> mapaTareas;
	private List<Tarea> ListaCritica ;
	private List<Tarea> ListaNoCritica;
	private HashMap<Procesador, List<Tarea>> mapProcesadores;
	//private List<Tarea> visitados;

	public Servicios(String pathProcesadores, String pathTareas){
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.mapaTareas = new HashMap();
		this.mapProcesadores = new HashMap<>();
		this.ListaCritica = new ArrayList<>();
		this.ListaNoCritica = new ArrayList<>();
		this.tiempoTotal = 0;
	}
	
	/*
     * O(1) donde 1 es cada Tarea
     */
	
	public void addTarea(String ID,String nombre, Integer tiempo,boolean critica,Integer prioridad) {
		if(!contieneTarea(ID)) {
			Tarea t = new Tarea(ID,nombre, tiempo, critica, prioridad);
			mapaTareas.put(ID, t);
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

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	//preguntar que devuelve
	public void asignacionTareas(int tiempo){
		//crear ListActual<Tarea> add, remove, probando todas las posibilidades posibles
		//si el tiempoTotal < tiempo;
		//llamar al privado backtracking();
		//return hashMap?
			
		List<Tarea> listActual = new ArrayList<>();
		backAsignacionTareas(tiempo, listActual);
		
	}
									//10, [];
	private void backAsignacionTareas(int tiempo, List<Tarea> listActual){
		//condicion de corte, quedarnos sin tareas de nuestro hashMap
		
		if(tiempo == 0 && !hayMasTareas(listActual)){ 
			System.out.println(listActual);
		} else{

			for (Map.Entry<String, Tarea> entry : mapaTareas.entrySet()) {
				String id = entry.getKey(); //devuelve Procesador1
				Tarea t = entry.getValue();
				
				listActual.add(t);
				backAsignacionTareas((tiempo-t.getTiempo()), listActual);
				listActual.remove(listActual.size()-1);
				
			}
			//tomo una tarea de mi mapTareas y al mismo la agrego a mi lista visitados.
		}
	}

	private boolean hayMasTareas(List<Tarea>listActual){
		return this.mapaTareas.keySet().size() >= listActual.size();
	}

	//tomo una tarea de mi mapTareas y al mismo la agrego a mi lista visitados. lista no crece mas cuando listVisitados.size == List(hashmap.values)

	public void addProcesador(int id_procesador, String codigo_procesador, boolean esta_refrigerado, int anio_procesamiento){
		Procesador procesador = new Procesador(id_procesador, codigo_procesador, esta_refrigerado, anio_procesamiento);
		this.mapProcesadores.put(procesador, new ArrayList<>());
	}

	private int calcularTiempoTotal() {
        tiempoTotal = 0;
        for (Tarea tarea : mapaTareas.values()) {
            tiempoTotal += tarea.getTiempo(); // Suponiendo que Tarea tiene un método getTiempo() que devuelve el tiempo de la tarea
        }
        return tiempoTotal;
    }
}