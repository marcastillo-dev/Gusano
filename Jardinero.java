public class Jardinero extends Thread {

    private char[][] tierra;
    private int filas;
    private int columnas;
    private boolean terminar = false;

    public Jardinero(char[][] m) {
        actualizarMapa(m);
    }

    public synchronized void actualizarMapa(char[][] nuevo) {
        this.tierra = nuevo;
        filas = nuevo.length;
        columnas = nuevo[0].length;
    }

    public synchronized void removerTierra() { }

    public void terminar() {
        terminar = true;
    }

    // Método para ver que el jardinero sí trabajó
    private void imprimirTierra() {
        System.out.println("=== JARDINERO LIMPIÓ ===");
        for(int r = 0; r < filas; r++) {
            for(int c = 0; c < columnas; c++) {
                System.out.printf("|%2c", tierra[r][c]);
            }
            System.out.println("|");
        }
        System.out.println("===========================");
    }

    public void run() {
        while(!terminar) {
            synchronized(tierra) {
                try {
                    // 1er cambio
                    //Cambiamos sleep por wait para que cuando el gusano fianlice lo depierte con notify
                    //se quita el if (limpiar) ya que el proceso ya se hace solo 
                    tierra.wait(); 
                    
                    // Jardinero hace su tarea
                    System.out.println("¡Jardinero despertó! Limpiando...");
                    
                    for(int r = 0; r < filas; r++) {
                        for(int c = 0; c < columnas; c++) {
                            tierra[r][c] = 'T'; // Limpia
                        }
                    }
                    //mapa del trabajo del jardinero
                    imprimirTierra(); 
                    sleep(2000);

                    System.out.println("Jardinero termino 'Despertando al Gusano' ");

                    // 4. AVISA al gusano que termino y el despierta de su wait (tierra.notify();)
                    //quitamos esto ya que se hacia un ciclo infinito

                } catch (InterruptedException e) {
                    System.out.println("Interrupción Jardinero");
                }
            }
        }
    }
}