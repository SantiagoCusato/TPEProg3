package tpe;

public class Tarea {
	private String id;
	private String nombre;
	private Integer tiempo;
	private boolean critica;
	private Integer prioridad;

	public Tarea(String id, String nombre, Integer tiempo, boolean critica, Integer prioridad) {

	this.id = id;
	this.nombre = nombre;
	this.tiempo = tiempo;
	this.critica = critica;
	this.prioridad = prioridad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	public boolean isCritica() {
		return critica;
	}
	public void setCritica(boolean critica) {
		this.critica = critica;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	public Tarea copiaTarea() {
		return new Tarea(this.id, this.nombre, this.tiempo, this.critica, this.prioridad);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
	return "Tarea [id=" + id ;
	}
}
