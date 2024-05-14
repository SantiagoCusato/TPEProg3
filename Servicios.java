package TPE;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private HashMap<String,Tarea> MapaTareas;
	public Servicios(String pathProcesadores, String pathTareas){
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.MapaTareas=new HashMap();
	}
	
	/*
     * O(1) donde 1 es cada Tarea
     */
	
	public void addTarea(String ID,String nombre, Integer tiempo,boolean critica,Integer prioridad) {
		if(!contieneTarea(ID)) {
			MapaTareas.put(ID,new Tarea(ID,nombre, tiempo, critica, prioridad));
		}
	}
	public boolean contieneTarea(String id) {
		return this.MapaTareas.containsKey(id);
		}
	
	public Tarea servicio1(String ID) {
		if(contieneTarea(ID)) {
		   return MapaTareas.get(ID);
		}
		return null;
	}
    
    /*
     * O(n) donde n son las iteraciones posibles
     * preguntar !
     */
	
	public List<Tarea> servicio2(boolean esCritica) {
		List<Tarea> ListaResultado = new ArrayList<>();
		for(Tarea t : MapaTareas.values()) {
			if(esCritica && t.isCritica()) {
			 ListaResultado.add(t);
			}
			else if(!esCritica && !t.isCritica()){
				ListaResultado.add(t);
			}
				
		}
		return ListaResultado;
		
	}
	/* Preguntar cual es mas eficiente!
	 * 
	 */
	/*public List<Tarea> servicio2(boolean esCritica) {
		List<Tarea> list = new ...;
		Iterator<Tarea> it = obtenerTareas();
		if(it.hasNext()){
			Tarea tarea = it.next();
			if(tarea.isCritica && esCritica){
				list.add(Tarea);
	}	else if(!tarea.isCritica && !esCritica){
		list.add(Tarea);
	} else {
		return new List<>();
	}
	}*/

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> Resultado= new ArrayList<>();
		for(Tarea t: MapaTareas.values()) {
			if(t.getPrioridad()<=prioridadSuperior && t.getPrioridad()>= prioridadInferior) {
				Resultado.add(t);
			}
		}
		return Resultado;
	}

}