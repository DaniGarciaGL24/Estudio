/* PriorityPolicy.java */
/**
 ** Hecho por: Cesar Hernández
 ** Carnet: 24000224
 ** Seccion: C
**/

package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import scheduler.processing.IOProcess;
import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.LoopProcess;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Esta clase es la implementación de la politica Priority Policy que compara procesos para determinar cuál es prioritario.
 */
public class PriorityPolicy extends Policy {
    private ConcurrentLinkedQueue<SimpleProcess> ioQueue;
    private ConcurrentLinkedQueue<SimpleProcess> arithmeticQueue;
    private ConcurrentLinkedQueue<SimpleProcess> otherQueue;

    public PriorityPolicy() {
        super();
        ioQueue = new ConcurrentLinkedQueue<>();
        arithmeticQueue = new ConcurrentLinkedQueue<>();
        otherQueue = new ConcurrentLinkedQueue<>();
    }
/* add */
/**
 * Recibe un proceso de tipo SimpleProcess, verifica si es una instancia de algún proceso y lugo lo añade a la cola correspondiente basada en el tipo de proceso e incrementa los contadores tamaño y total de procesos
 * No devuelve nada
 */
    @Override
    public void add(SimpleProcess p) {
        if (p instanceof IOProcess) {
            ioQueue.add(p);
        } else if (p instanceof ArithmeticProcess) {
            arithmeticQueue.add(p);
        } else if (p instanceof ConditionalProcess || p instanceof LoopProcess) {
            otherQueue.add(p);
        }
        size++;
        totalProcesses++;
    }
/* remove */
/**
 * Este método atiende ordenadamente cada proceso dependiendo de la prioridad del proceso: primero los de I/O, luego los aritméticos y finalmente los condicionales e iterativos.
 * No recibe ni devuelve nada
 */
    @Override
    public void remove() {
        if (!ioQueue.isEmpty()) {
            ioQueue.poll();
        } else if (!arithmeticQueue.isEmpty()) {
            arithmeticQueue.poll();
        } else if (!otherQueue.isEmpty()) {
            otherQueue.poll();
        }
        size--;
    }
/* next */
/**
 * Este método devuelve el primer proceso de la cola correspondiente sin eliminarlo, basado en la prioridad de los procesos, si no hay procesos en ninguna de las colas, devuelve null.
 * No recibe nada
 */
    @Override
    public SimpleProcess next() {
        if (!ioQueue.isEmpty()) {
            return ioQueue.peek();
        } else if (!arithmeticQueue.isEmpty()) {
            return arithmeticQueue.peek();
        } else if (!otherQueue.isEmpty()) {
            return otherQueue.peek();
        }
        return null;
    }
}
