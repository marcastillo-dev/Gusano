public class MonitorMapa extends Thread {
    
    private char[][] mapa;
    private boolean terminar;
    
    public MonitorMapa(char[][] m) {
        this.mapa = m;
        terminar = false;
    }
    
    public void terminar() {
        terminar = true;
    }
    
    public void run() {
        int r, c;
        int filas = mapa.length;
        int columnas = mapa[0].length;
        
        while(!terminar) {
           try{
               System.out.println("================================================");
               for(r = 0; r < filas; r++) {
                   for(c = 0; c < columnas; c++) {
                       System.out.printf("|%2c", mapa[r][r]);
                   }
                   System.out.printf("\n");
                }
                sleep(1500);
           }
           catch(InterruptedException e) {
               System.out.println("Interrupción");
           }
        }
    }
    
}