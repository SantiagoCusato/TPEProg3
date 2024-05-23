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
	private List<Tarea> ListaCritica ;
	private List<Tarea> ListaNoCritica ;

	public Servicios(String pathProcesadores, String pathTareas){
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.MapaTareas=new HashMap();
		this.ListaCritica = new ArrayList<>();
		this.ListaNoCritica = new ArrayList<>();
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
		if(esCritica){
			return ListaCritica;
		}
		return ListaNoCritica;
	}

	public void addTareaCritica(Tarea t) {
		if(t.isCritica()) {
			ListaCritica.add(t); // pregungtar por collections sort, para q qeuden ordenadas para la 2da entrega
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
		for(Tarea t: MapaTareas.values()) {
			if(t.getPrioridad()<=prioridadSuperior && t.getPrioridad()>= prioridadInferior) {
				Resultado.add(t);
			}
		}
		return Resultado;
	}

}