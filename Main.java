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
            System.out.println("3. Ver estado actual (Monitor)");
            System.out.println("4. Estado de Gusano");
            System.out.println("5. Estado de Jardinero");
            System.out.println("6. Despertar gusano");
            System.out.println("7. Salir");
            System.out.println("=====================");
            
            if(scanner.hasNextInt()){
                menu = scanner.nextInt();
            } else {
                scanner.next();
                menu = 0;
            }

            switch(menu) {

                case 1:
                    System.out.print("Número de filas: ");
                    filas = scanner.nextInt();
                    System.out.print("Número de columnas: ");
                    columnas = scanner.nextInt();
                    
                    mapa = new char[filas][columnas];
                    for(int r = 0; r < filas; r++) {
                        for(int c = 0; c < columnas; c++) {
                            mapa[r][c] = '.';
                        }
                    }

                    gusano.actualizarMapa(mapa);
                    jardinero.actualizarMapa(mapa);
                    monitor.actualizarMapa(mapa);
                    
                    System.out.println("Mapa redimensionado correctamente.");
                    break;

                case 2:
                    System.out.print("Velocidad (ms): ");
                    velocidad = scanner.nextInt();
                    gusano.setVelocidad(velocidad);
                    break;

                case 3:
                    monitor.mostrarMapa();
                    break;

                case 4:
                    System.out.println("Estado de Gusano: " + gusano.getState());
                    break;
                    
                case 5:
                    System.out.println("Estado de Jardinero: " + jardinero.getState());
                    break;
                
                case 6:
                    if (jardinero.getState() == Thread.State.WAITING) {
                        gusano.despertar();
                        System.out.println("Gusano despertado con éxito.");
                        monitor.mostrarMapa();
                    } 
                    else {
                        System.out.println("El jardinero aún no termina.");
                        monitor.mostrarMapa();
                    }
                    break;
                    
                case 7:
                    monitor.terminar();
                    gusano.finalizar();
                    jardinero.terminar();
                    scanner.close();
                    System.out.println("Saliendo de la simulación...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while(menu != 7);
    }
}