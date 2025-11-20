public class HiloGusano extends Thread {
    
    private char[][] jardin;
    private int filas;
    private int columnas;
    
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
    
    public void comerColumna(int c, int comer) {
        for(int x = 0; x < comer; x++) {
            jardin[x][c] = 'C'; 
        }
    }
    
    public void comerRenglon(int r, int comer) {
        for(int x = 0; x < comer; x++) {
            jardin[r][x] = 'C'; 
        }
    }
    
    public void run() {
        int i = 0;
        int j = 0;
        int vida = 100;
        while(vida != 0) {
            try{

                synchronized(jardin) {
                    for(i = 0; i < filas; i++) {
                        for(j = 0; j < columnas; j++){
                            caminaRenglon(j);
                            sleep(1000);
                        }
                        caminaColumna(i);
                        sleep(500);
                    }
                }

                
            }
            catch(InterruptedException e) {
                System.out.println("Interrupción");
            }
            catch(ArrayIndexOutOfBoundsException a) {
                i = 0;
            }
        }
    }
    
}
