import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;

import static java.util.Objects.isNull;

public class RmiServer
{
    public static void main(String[] args)
    {
        if(isNull(System.getSecurityManager()))
        {
            System.setSecurityManager(new SecurityManager());
            System.setProperty("java.security.policy","file://.java.policy");
        }

        MyRemoteInterface remoteObject = new MyRemoteObject();

        try
        {
            MyRemoteInterface stub = (MyRemoteInterface) UnicastRemoteObject.exportObject(remoteObject,0);
            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("myRemoteObj",stub);

            System.out.println("bound 'myRemoteObj'");
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
        catch(AlreadyBoundException e)
        {
            e.printStackTrace();
        }


    }
}

interface MyRemoteInterface extends Remote
{
    Integer getIncreasedNumber(Integer num) throws RemoteException;
}

class MyRemoteObject implements MyRemoteInterface
{

    @Override
    public Integer getIncreasedNumber(final Integer num) throws RemoteException
    {
        System.out.println("REMOTE INVOCATION WITH PARAM : " + num);
        return num + 1;
    }
}
