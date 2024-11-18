/* ArithmeticProcess.java */
/**
 ** Hecho por: Juan Perez
 ** Carnet: 14007891
 ** Seccion: A
**/

package scheduler.processing;

public class ArithmeticProcess extends SimpleProcess {
    private final double serviceTime;
    private static int idCounter = 1;

    public ArithmeticProcess(double serviceTime) {
        super(idCounter++);
        this.serviceTime = serviceTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[id:" + id + " Type:Arithmetic Process Time:" + serviceTime + "]";
    }
}
