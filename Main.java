public class Main {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int opcion = 0, filas, columnas; 
	    
	    
	   do{
	       try{
	           System.out.println("MENÚ");
                System.out.println("1. TAMAÑO DEL TERRENO");
                System.out.println("2. VELOCIDAD DEL GUSANO");
                System.out.println("3. DESPERTAR GUSANO");
                opcion = scanner.nextInt();
                
            switch(opcion){
                case 1: 
                    System.out.println("NÚMERO DE FILAS: "); 
                    filas = scanner.nextInt(); 
                    System.out.println("NÚMERO DE COLUMNAS: ");
                    columnas = scanner.nextInt();
                    char[][] mapa;
		            mapa = new char[filas][columnas];
                    break; 
                case 2: 
                    break; 
                case 3: 
                    break; 
            }

	       }catch{
	           
	       }
	   }
		
		HiloGusano gusano = new HiloGusano(mapa);
		MonitorMapa monitor = new MonitorMapa(mapa);
		
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