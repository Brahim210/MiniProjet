import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaskListInterface extends Remote {
    void addTask(String task) throws RemoteException;
    void removeTask(int index) throws RemoteException;
    List<String> getTaskList() throws RemoteException;
}