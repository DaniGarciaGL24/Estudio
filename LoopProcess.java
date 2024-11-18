/* LoopProcess.java */
/**
 ** Hecho por: Juan Perez
 ** Carnet: 14007891
 ** Seccion: A
**/

package scheduler.processing;

public class LoopProcess extends SimpleProcess {
    private final double serviceTime;
    private static int idCounter = 1;

    public LoopProcess(double serviceTime) {
        super(idCounter++);
        this.serviceTime = serviceTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[id:" + id + " Type:Loop Process Time:" + serviceTime + "]";
    }
}
