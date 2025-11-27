public class MonitorMapa extends Thread {

    private char[][] mapa;
    private boolean terminar;

    public MonitorMapa(char[][] m) {
        this.mapa = m;
        terminar = false;
    }

    public synchronized void actualizarMapa(char[][] nuevo) {
        this.mapa = nuevo;
    }

    public synchronized void mostrarMapa() {
        notify();
    }

    public void terminar() {
        terminar = true;
        synchronized(this) { notify(); }
    }

    public void imprimir() {
        int filas = mapa.length;
        int columnas = mapa[0].length;

        System.out.println("========================");
        for(int r = 0; r < filas; r++) {
            for(int c = 0; c < columnas; c++) {
                System.out.printf("|%2c", mapa[r][c]);
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    public void run() {
        while(!terminar) {
            synchronized(this) {
                try { wait(); } catch(Exception e) {}
            }
            imprimir();
        }
    }
}