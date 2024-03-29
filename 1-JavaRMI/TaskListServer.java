import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskListServer extends UnicastRemoteObject implements TaskListInterface {
    private List<String> tasks; // Liste des tâches

    // Constructeur du serveur
    protected TaskListServer() throws RemoteException {
        super(); // Appel au constructeur de la classe mère UnicastRemoteObject
        tasks = new ArrayList<>(); // Initialisation de la liste des tâches
    }

    // Méthode pour ajouter une tâche
    @Override
    public synchronized void addTask(String task) throws RemoteException {
        tasks.add(task); // Ajout de la tâche à la liste
        System.out.println("Task added: " + task); // Affichage de la tâche ajoutée
    }

    // Méthode pour supprimer une tâche
    @Override
    public synchronized void removeTask(int index) throws RemoteException {
        // Vérification de l'index
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.remove(index); // Suppression de la tâche
            System.out.println("Task removed: " + removedTask); // Affichage de la tâche supprimée
        } else {
            System.out.println("Invalid task index."); // Index de tâche invalide
        }
    }

    // Méthode pour récupérer la liste des tâches
    @Override
    public synchronized List<String> getTaskList() throws RemoteException {
        return new ArrayList<>(tasks); // Retourne une copie de la liste des tâches
    }

    // Méthode principale du serveur
    public static void main(String[] args) {
        try {
            // Création d'une instance du serveur
            TaskListServer server = new TaskListServer();
            // Création du registre RMI sur le port 1099
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            // Récupération du registre RMI
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry();
            // Enregistrement du serveur dans le registre sous le nom "TaskListService"
            registry.rebind("TaskListService", server);
            System.out.println("Server started."); // Message de démarrage du serveur
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString()); // Gestion des exceptions
            e.printStackTrace();
        }
    }
}