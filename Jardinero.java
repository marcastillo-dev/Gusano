public class Jardinero extends Thread {
   
    private char[][] tierra;
    private int filas;
    private int columnas;
 
    public Jardinero(char[][] m) {
        this.tierra = m;
        filas = tierra.length;
        columnas = tierra[0].length;
    }
 
    public void caminaRenglon(int r) {
        for(int x = 0; x < columnas; x++) {
            tierra[r][x] = 'T';
        }
    }
   
    public void caminaColumna(int c) {
        for(int x = 0; x < filas; x++) {
            tierra[x][c] = 'T';
        }
    }
 
    public boolean removerTierra() {
        return true;
    }
 
    public void run() {
        int i = 0;
        int j = 0;
 
        try{
            synchronized(tierra) {
                for(i = 0; i < filas; i++) {
                    for(j = 0; j < columnas; j++){
                        caminaRenglon(j);
                        sleep(1000);
                    }
                    caminaColumna(i);
                    sleep(500);
                }
            }    
            removerTierra();  
        }
        catch(InterruptedException e) {
            System.out.println("Interrupción");
        }
        catch(ArrayIndexOutOfBoundsException a) {
            i = 0;
        }
    }
 
}