package TPE;

public class Main {

	public static void main(String[] args) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
               servicios.addTarea("T1", "Limpieza", 3, true, 33);
               servicios.addTarea("T2", "Fumigacion", 1, false, 99);
               servicios.addTarea("T3", "Abonar", 0, true, 1);
               servicios.addTarea("T4", "Inquisicion", 5, true, 70);
               servicios.addTarea("T5", "Cocinar", 2, true, 41);
               servicios.addTarea("T6", "Facturar", 2, false, 56);
               System.out.println(servicios.servicio1("T1"));
               System.out.println(servicios.servicio2(false));
               System.out.println(servicios.servicio3(0, 99));
               
               
	}
	
}
