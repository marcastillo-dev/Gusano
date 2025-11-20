import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int opcion = 0, filas = 0, columnas = 0, velocidad = 0; 
	    
		char[][] mapa;
	    
	   do{
	       //try{
	           System.out.println("MENÚ");
                System.out.println("1. TAMAÑO DEL TERRENO");
                System.out.println("2. VELOCIDAD DEL GUSANO");
                System.out.println("3. DESPERTAR GUSANO");
				System.out.println("4. SALIR");
                opcion = scanner.nextInt();
                
            switch(opcion){
                case 1: 
                    System.out.println("NÚMERO DE FILAS: "); 
                    filas = scanner.nextInt(); 
                    System.out.println("NÚMERO DE COLUMNAS: ");
                    columnas = scanner.nextInt();
                    break; 
                case 2:
					System.out.println("VELOCIDAD DEL GUSANO: ");
					velocidad = scanner.nextInt();
                    break; 
                case 3: 
                    break; 
				case 4:
					break;
		        default: 
		        System.out.println("OPCIÓN NO DISPONIBLE");
				
            }
		   /*catch{
	           
	       }*/
	    }while(opcion!=4);
		
		mapa = new char[filas][columnas];
		HiloGusano gusano = new HiloGusano(mapa);
		MonitorMapa monitor = new MonitorMapa(mapa);
		Jardinero jardinero = new Jardinero(mapa);
		
		for(int r = 0; r < 12; r++) {
           for(int c = 0; c < 16; c++) {
               mapa[r][c] = '.';
           }
           System.out.printf("\n");
        }
		
		gusano.start();
		monitor.start();
		
		try{
		    gusano.join();
		}
		catch(InterruptedException e) {
		    System.out.println("");
		}
		monitor.terminar();
		
	}
}