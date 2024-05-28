package TPEProg3;

public class Main {

	public static void main(String[] args) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
            servicios.addTarea("T1", "Limpieza", 3, true, 33);
            servicios.addTarea("T2", "Fumigacion", 1, false, 99);
            servicios.addTarea("T3", "Abonar", 12, true, 1);
            servicios.addTarea("T4", "Inquisicion", 5, true, 70);
            /*
            servicios.addTarea("T5", "Cocinar", 2, true, 41);
            servicios.addTarea("T6", "Facturar", 2, false, 56);
            servicios.addTarea("T7", "Podar jardín", 4, false, 20);
            servicios.addTarea("T8", "Instalar luces", 3, true, 15);
            servicios.addTarea("T9", "Reparar techo", 6, true, 45);
            servicios.addTarea("T10", "Pintar paredes", 3, false, 30);
            servicios.addTarea("T11", "Limpiar ventanas", 1, true, 25);*/
            /*
            System.out.println(servicios.servicio1("T1"));
            System.out.println("----------------------------------");
            System.out.println(servicios.servicio2(true));
            System.out.println("----------------------------------");
            System.out.println(servicios.servicio3(0, 99));
            */

            System.out.println("SEGUNDA PARTE---");
            servicios.addProcesador("1HT", "COD1", true, 2019);
            servicios.addProcesador("2ER", "COD2", false, 2020);
            servicios.addProcesador("3AR", "COD3", true, 2018);
            servicios.addProcesador("1SDR", "COD3", true, 2013);
            servicios.addProcesador("7YR", "COD3", true, 2011);

            
            servicios.asignacionTareas(20); 
        }
	
}
