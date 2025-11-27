public class HiloGusano extends Thread {

    private char[][] jardin;
    private int filas;
    private int columnas;
    private int velocidad = 1000;
    private boolean terminar = false;
    private boolean fin = false;

    public HiloGusano(char[][] j) {
        actualizarMapa(j);
    }

    public synchronized void actualizarMapa(char[][] nuevo) {
        this.jardin = nuevo;
        filas = nuevo.length;
        columnas = nuevo[0].length;
    }

    public void caminaRenglon(int r) {
        for(int x = 0; x < columnas; x++) {
            jardin[r][x] = 'W';
        }
    }

    public void caminaColumna(int c) {
        for(int x = 0; x < filas; x++) {
            jardin[x][c] = 'W';
        }
    }

    public void setVelocidad(int v) {
        velocidad = v;
    }

    public boolean getTerminar() {
        return terminar;
    }
    
    public void finalizar() {
        fin = true;
    }

    public void despertar() {
        synchronized(jardin) {
            jardin.notify();
        }
    }

    public void run() {
        while(!fin) {
            try {
                synchronized(jardin) {
    
                    for (int r = 0; r < filas; r++) {
                        caminaRenglon(r);
                        sleep(velocidad);
                    }
    
                    for (int c = 0; c < columnas; c++) {
                        caminaColumna(c);
                        sleep(velocidad);
                    }
    
                    terminar = true;
                    jardin.wait(); // espera al jardinero
                }
            } 
            catch (InterruptedException a) {
                System.out.println("Interrupción");
            }
            catch (IllegalMonitorStateException e) {
                System.out.println("Interrupción");
            }
        }
    }
}
