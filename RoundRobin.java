/* RoundRobin.java */
/**
 ** Hecho por: Cesar Hernández
 ** Carnet: 24000224
 ** Seccion: C
**/

package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Esta clase implementa la política Round Robin, que inicialmente funciona como la política FCFS. Si un proceso requiere más tiempo del asignado, es devuelto a la cola para ser atendido nuevamente el tiempo restante
 */
public class RoundRobin extends Policy {
    private ConcurrentLinkedQueue<SimpleProcess> cola;
    private int quantum;

    public RoundRobin(int quantum) {
        super();
        this.quantum = quantum;
        cola = new ConcurrentLinkedQueue<>();
    }
/* add */
/**
 * Este método añade el proceso p a la cola, incrementando los contadores tamaño y procesos totales.
 * No devuelve nada
 */
    @Override
    public void add(SimpleProcess p) {
        cola.add(p);
        size++;
        totalProcesses++;
    }
/* remove */
/**
 * Este método elimina el primer proceso de la cola si hay procesos presentes, y decrementa el contador size
 * No recibe ni devuelve nada
 */
    @Override
    public void remove() {
        if (size > 0) {
            cola.poll();
            size--;
        }
    }
/* next */
/**
 * Este método devuelve el siguiente proceso en la cola sin eliminarlo. Si la cola está vacía, devuelve null
 * No recibe nada
 */
    @Override
    public SimpleProcess next() {
        return cola.peek();
    }
/* requeue */
/**
 * Este método recibe un proceso que aún no se ha terminado de atender para devolverlo a la cola
 * No devuelve nada
 */
    public void requeue(SimpleProcess p) {
        cola.add(p);
        size++;
    }
}
