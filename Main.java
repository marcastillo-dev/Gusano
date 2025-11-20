public class Main {
	public static void main(String[] args) {
		
		char[][] mapa;
		mapa = new char[12][16];
		
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