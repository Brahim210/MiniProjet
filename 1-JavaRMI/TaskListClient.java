import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class TaskListClient {
    public static void main(String[] args) {
        try {
            // Récupération du registre RMI à partir de localhost
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            // Recherche du service TaskListService dans le registre
            TaskListInterface taskList = (TaskListInterface) registry.lookup("TaskListService");

            // Initialisation du scanner pour la saisie utilisateur
            Scanner scanner = new Scanner(System.in);
            // Initialisation d'un booléen pour contrôler la boucle principale
            boolean exit = false;

            // Boucle principale du menu
            while (!exit) {
                // Affichage du menu
                System.out.println("Menu:");
                System.out.println("1. Ajouter une tâche");
                System.out.println("2. Supprimer une tâche");
                System.out.println("3. Récupérer la liste des tâches");
                System.out.println("4. Quitter");
                System.out.print("Choix: ");
                // Lecture du choix de l'utilisateur
                int choice = scanner.nextInt();

                // Traitement du choix de l'utilisateur
                switch (choice) {
                    case 1:
                        // Ajout d'une tâche
                        System.out.print("Entrez la tâche à ajouter: ");
                        scanner.nextLine(); // pour consommer le retour chariot
                        String taskToAdd = scanner.nextLine();
                        taskList.addTask(taskToAdd);
                        break;
                    case 2:
                        // Suppression d'une tâche
                        System.out.print("Entrez l'index de la tâche à supprimer: ");
                        int indexToRemove = scanner.nextInt();
                        taskList.removeTask(indexToRemove - 1); // index utilisateur commence à 1
                        break;
                    case 3:
                        // Récupération et affichage de la liste des tâches
                        List<String> tasks = taskList.getTaskList();
                        System.out.println("Liste des tâches:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        break;
                    case 4:
                        // Sortie de la boucle et du programme
                        exit = true;
                        break;
                    default:
                        // Gestion d'un choix invalide
                        System.out.println("Choix invalide.");
                }
            }

            // Message de déconnexion
            System.out.println("Déconnexion...");
        } catch (Exception e) {
            // Gestion des exceptions
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}