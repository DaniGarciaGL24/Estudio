/* ProcessGenerator.java */
/**
 ** Hecho por: Juan Perez
 ** Carnet: 14007891
 ** Seccion: A
**/

package scheduler;

import scheduler.processing.*;
import scheduler.scheduling.policies.Policy;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que genera procesos aleatoriamente y los encola en la política seleccionada.
 */
public class ProcessGenerator {
    private Policy policy;
    private AtomicInteger idCounter;
    private double arithTime;
    private double ioTime;
    private double condTime;
    private double loopTime;

    public ProcessGenerator(Policy policy, double arithTime, double ioTime, double condTime, double loopTime) {
        this.policy = policy;
        this.idCounter = new AtomicInteger(1);
        this.arithTime = arithTime;
        this.ioTime = ioTime;
        this.condTime = condTime;
        this.loopTime = loopTime;
    }

    public void startGenerating(int rangeMin, int rangeMax) {
        Timer timer = new Timer();
        Random random = new Random();
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int id = idCounter.getAndIncrement();
                SimpleProcess process;
                double randomType = random.nextDouble();

                if (randomType < 0.25) {
                    process = new ArithmeticProcess(arithTime);
                } else if (randomType < 0.5) {
                    process = new IOProcess(ioTime);
                } else if (randomType < 0.75) {
                    process = new ConditionalProcess(condTime);
                } else {
                    process = new LoopProcess(loopTime);
                }

                policy.add(process);
                System.out.println("Generated and enqueued: " + process);
            }
        };

        int delay = random.nextInt((rangeMax - rangeMin) + 1) + rangeMin;
        timer.schedule(task, 0, delay * 1000);
    }
}
