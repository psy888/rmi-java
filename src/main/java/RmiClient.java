import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.util.Objects.isNull;

public class RmiClient
{
    public static void main(String[] args)
    {
        if(isNull(System.getSecurityManager())){
            System.setSecurityManager(new SecurityManager());
            System.setProperty("java.security.policy","file://.java.policy");
        }

        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            MyRemoteInterface stub = (MyRemoteInterface) registry.lookup("myRemoteObj");

            System.out.println("Increment number by one : " + stub.getIncreasedNumber(1));
            System.out.println("Increment number by one : " + stub.getIncreasedNumber(2));
            System.out.println("Increment number by one : " + stub.getIncreasedNumber(3));
            System.out.println("Increment number by one : " + stub.getIncreasedNumber(4));
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
        catch(NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}
