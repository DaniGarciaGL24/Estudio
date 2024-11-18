/* LCFS.java */
/**
 ** Hecho por: Cesar Hernández
 ** Carnet: 24000224
 ** Seccion: C
**/

package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.Stack;

/**
 * Clase que implementa la política Last-Come-First-Served que funciona como un stack o pila.
 */
public class LCFS extends Policy {
    private Stack<SimpleProcess> pila;

    public LCFS() {
        super();
        pila = new Stack<>();
    }
/* add */
/**
 * Este método recibe un proceso p de tipo SimpleProcess y lo añade a la pila y aumenta los contadores tamaño y procesos totales
 * No devuelve nada
 */
    @Override
    public void add(SimpleProcess p) {
        pila.push(p);
        size++;
        totalProcesses++;
    }
/* remove */
/**
 * Este método atiende el proceso que entró de último y decrementa el contador tamaño
 * No recibe ni devuelve nada
 */
    @Override
    public void remove() {
        if (size > 0) {
            pila.pop();
            size--;
        }
    }
/* next */
/**
 * Este método devuelve el proceso que entró de último sin eliminarlo
 * No recibe nada
 */
    @Override
    public SimpleProcess next() {
        return pila.peek();
    }
}
