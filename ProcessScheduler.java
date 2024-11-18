/* ProcessScheduler.java */
/**
 ** Hecho por: Alejandro Carrillo
 ** Carnet: 24000186
 ** Seccion: C
**/

import scheduler.processing.*;
import scheduler.scheduling.policies.*;
import scheduler.*;

import java.util.Random;
import java.util.Scanner;

public class ProcessScheduler {

    public static void main(String[] args) {
        if (args.length < 6 || (args[0].equals("-dual") && args.length < 7)) {
            System.out.println("Uso incorrecto. Por favor, proporciona los argumentos necesarios.");
            System.out.println("Ejemplo: java ProcessScheduler -fcfs 1-3 2 1 2.5 3");
            System.out.println("Para Round Robin: java ProcessScheduler -rr 1-3 2 1 2.5 3 1");
            System.out.println("Para uso de dos procesadores: java ProcessScheduler -dual -lcfs 1-2 2 2 2 2");
            return;
        }

        boolean dual = args[0].equals("-dual");
        String policyType = dual ? args[1] : args[0];
        String[] range = args[dual ? 2 : 1].split("-");
        int rangeMin = Integer.parseInt(range[0]);
        int rangeMax = Integer.parseInt(range[1]);
        double arithTime = Double.parseDouble(args[dual ? 3 : 2]);
        double ioTime = Double.parseDouble(args[dual ? 4 : 3]);
        double condTime = Double.parseDouble(args[dual ? 5 : 4]);
        double loopTime = Double.parseDouble(args[dual ? 6 : 5]);
        int quantum = dual && args.length == 8 ? Integer.parseInt(args[7]) : 0;

        Policy policy;
        switch (policyType) {
            case "-fcfs":
                policy = new FCFS();
                break;
            case "-lcfs":
                policy = new LCFS();
                break;
            case "-pp":
                policy = new PriorityPolicy();
                break;
            case "-rr":
                policy = new RoundRobin(quantum);
                break;
            default:
                System.out.println("Política no reconocida.");
                return;
        }

        ProcessGenerator generator = new ProcessGenerator(policy, arithTime, ioTime, condTime, loopTime);
        generator.startGenerating(rangeMin, rangeMax);

        Processor processor1 = new Processor(policy);
        Processor processor2 = dual ? new Processor(policy) : null;

        Thread processorThread1 = new Thread(processor1);
        processorThread1.start();

        if (dual) {
            Thread processorThread2 = new Thread(processor2);
            processorThread2.start();
        }


        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("q".equalsIgnoreCase(input.trim())) {
                    printSummary(policy, processor1.getProcessedCount() + (dual ? processor2.getProcessedCount() : 0), processor1.getTotalTimeProcessed() + (dual ? processor2.getTotalTimeProcessed() : 0));
                    scanner.close();
                    System.exit(0);
                }
            }
        }).start();
    }

    private static void printSummary(Policy policy, int processedCount, long totalTimeProcessed) {
        System.out.println("\n--- Resumen de la Simulación ---");
        System.out.println("Procesos atendidos: " + processedCount);
        System.out.println("Procesos en cola: " + policy.size());
        System.out.println("Tiempo promedio de atención por proceso: " + (processedCount > 0 ? (totalTimeProcessed / processedCount) : 0) + " ms");
        System.out.println("Política utilizada: " + policy.getClass().getSimpleName());
    }
}
