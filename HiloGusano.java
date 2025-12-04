public class HiloGusano extends Thread {
    
    private char[][] jardin;
    private int filas;
    private int columnas;
    private int velocidad = 200; 
    boolean terminar = false;
    
    public HiloGusano(char[][] j) {
        this.jardin = j;
        filas = jardin.length;
        columnas = jardin[0].length;
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
    
    public void finalizar(){
        this.terminar = true;
    }
    
    public void actualizarMapa(char[][] j){
        this.jardin = j;
        filas = jardin.length;
        columnas = jardin[0].length;
    }
    //3er cambio
    // para evitar un ciclo infinito
    public void despertar(){
        synchronized(jardin){// se agrega synchronized para eviatr una excepción
        jardin.notify();
        }
    }

    private void imprimirJardin() {
        System.out.println("===  GUSANO AVANZANDO ===");
        for(int r = 0; r < filas; r++) {
            for(int c = 0; c < columnas; c++) {
                System.out.printf("|%2c", jardin[r][c]);
            }
            System.out.println("|");
        }
        System.out.println("===========================");
    }
    
    
    public void run() {
        int i = 0;
        int j = 0;
        while(!terminar) {
            try{
                //1er cambio
                // Solucionamos problema donde el jardinero no podia entrar a realizar su tarea
                //Sacamos for de synchronized para que el jardinero puidera trabajar cuando le tocara 
                for(i = 0; i < filas; i++) {
                    for(j = 0; j < columnas; j++){
                        caminaRenglon(j);
                        imprimirJardin(); 
                        sleep(velocidad);
                    }
                    caminaColumna(i);
                    imprimirJardin();
                    sleep(velocidad);
                }
                
                // 2do cambio
                //Agregamos notify para que el gusano avise al jardinero y el inicie su tarea
                synchronized(jardin) {
                    System.out.println("Gusano finalizo ¡AVISANDO AL JARDINERO!");
                    jardin.notify(); // notifica al jardinero 
                    jardin.wait();   // El gusano duerme
                }
            }
            catch(InterruptedException e) {
                System.out.println("Interrupción Gusano");
            }
            catch(ArrayIndexOutOfBoundsException a) {
                i = 0;
            }
        }     
    }
}
