//UVM PC NOV,2025
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int menu, filas = 5, columnas = 5, velocidad;
        char[][] mapa = new char[filas][columnas];

        for(int r = 0; r < filas; r++) {
            for(int c = 0; c < columnas; c++) {
                mapa[r][c] = '.';
            }
        }

        HiloGusano gusano = new HiloGusano(mapa);
        Jardinero jardinero = new Jardinero(mapa);
        MonitorMapa monitor = new MonitorMapa(mapa);

        gusano.start();
        jardinero.start();
        monitor.start();

        do {
            System.out.println("\n======= MENÚ ========");
            System.out.println("1. Tamaño de terreno");
            System.out.println("2. Velocidad de gusano");
            System.out.println("3. Remover tierra");
            System.out.println("4. Despertar gusano");
            System.out.println("5. Estado de Gusano");
            System.out.println("6. Estado de Jardinero");
            System.out.println("7. Salir");
            System.out.println("=====================");
            menu = scanner.nextInt();

            switch(menu) {

                case 1:
                    System.out.print("Número de filas: ");
                    filas = scanner.nextInt();
                    System.out.print("Número de columnas: ");
                    columnas = scanner.nextInt();
                    System.out.print("Mapa actualizado.");
                    mapa = new char[filas][columnas];

                    for(int r = 0; r < filas; r++) {
                        for(int c = 0; c < columnas; c++) {
                            mapa[r][c] = '.';
                        }
                    }

                    // actualizar en cada hilo
                    gusano.actualizarMapa(mapa);
                    jardinero.actualizarMapa(mapa);
                    monitor.actualizarMapa(mapa);
                    monitor.mostrarMapa();
                    break;

                case 2:
                    System.out.print("Velocidad (ms): ");
                    velocidad = scanner.nextInt();
                    gusano.setVelocidad(velocidad);
                    break;

                case 3:
                    if(gusano.getTerminar()) {
                        jardinero.removerTierra();
                        System.out.println("Tierra removida exitosamente.");
                        monitor.mostrarMapa();
                    } 
                    else {
                        System.out.println("El gusano aún no termina.");
                        monitor.mostrarMapa();
                    }
                    break;

                case 4:
                    if(jardinero.getTerminar()) {
                        gusano.despertar();
                        System.out.println("Gusano despertado con éxito.");
                        monitor.mostrarMapa();
                    } 
                    else {
                        System.out.println("El jardinero aún no termina.");
                        monitor.mostrarMapa();
                    }
                    break;

                case 5:
                    System.out.println("Estado de Gusano: " + gusano.getState());
                    monitor.mostrarMapa();
                    break;
                    
                case 6:
                    System.out.println("Estado de Jardinero: " + jardinero.getState());
                    monitor.mostrarMapa();
                    break;
                
                case 7:
                    monitor.terminar();
                    gusano.finalizar();
                    jardinero.terminar();
					scanner.close();
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while(menu != 7);
    }
}