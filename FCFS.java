/* FCFS.java */
/**
 ** Hecho por: Cesar Hernández
 ** Carnet: 24000224
 ** Seccion: c
**/

package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Esta clase hereda de la clase Policy para implementar la política First-Come-First-Served que funciona como una queue o cola.
 */
public class FCFS extends Policy {
    private ConcurrentLinkedQueue<SimpleProcess> cola;

    public FCFS() {
        super();
        cola = new ConcurrentLinkedQueue<>();
    }
/* add */
/**
 * Este método añade el proceso p a la cola y aumenta el contador de tamaño y procesos totales
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
 * Este método atiende el primer elemento en la cola y decrementa los contadores de tamaño y proceos totales
 * No devuelve nada
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
 * Este método devuelve el primer elemento de la cola sin eliminarlo
 * No recibe nada
 */
    @Override
    public SimpleProcess next() {
        return cola.peek();
    }
}
