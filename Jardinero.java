public class Jardinero extends Thread {

    private char[][] tierra;
    private int filas;
    private int columnas;
    protected boolean limpiar = false;
    private boolean terminar = false;
    private boolean fin = false;

    public Jardinero(char[][] m) {
        actualizarMapa(m);
    }

    public synchronized void actualizarMapa(char[][] nuevo) {
        this.tierra = nuevo;
        filas = nuevo.length;
        columnas = nuevo[0].length;
    }

    public synchronized void removerTierra() {
        limpiar = true;
        
    }

    public boolean getTerminar() {
        return terminar;
    }
    
    public void terminar() {
        terminar = true;
    }

    public void run() {
        while(!fin) {
            if(limpiar) {
                for(int r = 0; r < filas; r++) {
                    for(int c = 0; c < columnas; c++) {
                        tierra[r][c] = 'T';
                    }
                }
                limpiar = false;
                terminar = true;
            }
            try {
                sleep(1500);
            } 
            catch (InterruptedException e) {
                System.out.println("Interrupción Jardinero");
            }
            catch (IllegalMonitorStateException e) {
                System.out.println("Interrupción");
            }
        }
    }
}