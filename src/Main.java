import datasource.ConcurrentVendingMachine;
import models.VendingMachine;
import utils.VendingMachineFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        VendingMachine vm = VendingMachineFactory.createVendingMachine();
        ConcurrentVendingMachine vms = ConcurrentVendingMachine.getInstance();
        vms.calculateChange(108);
    }
}
