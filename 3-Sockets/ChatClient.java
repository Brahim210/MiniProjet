import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_ADDRESS = "127.0.0.1"; // Adresse IP du serveur
    private static final int SERVER_PORT = 12345; // Port du serveur

    public static void main(String[] args) {
        try {
            // Connexion au serveur
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server.");

            // Flux pour lire l'entrée utilisateur depuis la console
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            // Flux pour envoyer des messages au serveur
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Demande à l'utilisateur de saisir son nom d'utilisateur
            System.out.print("Enter your username: ");
            String username = consoleInput.readLine();
            out.println(username); // Envoi du nom d'utilisateur au serveur

            // Thread pour recevoir les messages du serveur
            Thread receiveThread = new Thread(() -> {
                try {
                    // Flux pour lire les messages du serveur
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) { // Boucle pour lire les messages du serveur
                        // Vérification si le message ne provient pas de l'utilisateur actuel
                        if (!message.startsWith(username + ":")) {
                            // Affichage du message reçu
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start(); // Démarrage du thread pour la réception des messages

            String userInput;
            // Boucle pour lire l'entrée de l'utilisateur et envoyer les messages au serveur
            while ((userInput = consoleInput.readLine()) != null) {
                out.println(userInput); // Envoi du message au serveur
            }

            // Fermeture de la connexion avec le serveur
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
