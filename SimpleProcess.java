/* SimpleProcess.java */
/**
 ** Hecho por: Juan Perez
 ** Carnet: 14007891
 ** Seccion: A
**/

package scheduler.processing;

public abstract class SimpleProcess {
    protected int id;

    public SimpleProcess(int id) {
        this.id = id;
    }

    public final int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[id:" + this.id + "]";
    }

    public abstract double getServiceTime();
}
