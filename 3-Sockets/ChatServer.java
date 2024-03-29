import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private static final int PORT = 12345;
    private static final Map<String, PrintWriter> clients = new HashMap<>(); // Map pour stocker les noms d'utilisateur et leurs PrintWriter associés

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT); // Création d'un serveur socket sur le port spécifié
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Attente de connexion d'un client
                System.out.println("New client connected: " + clientSocket);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Flux d'entrée pour lire les messages du client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Flux de sortie pour envoyer des messages au client

                String username = in.readLine(); // Lecture du nom d'utilisateur envoyé par le client
                clients.put(username, out); // Stockage du nom d'utilisateur et de son PrintWriter associé

                Thread thread = new Thread(new ClientHandler(clientSocket, username)); // Création d'un thread pour gérer ce client
                thread.start(); // Démarrage du thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interne pour gérer les interactions avec un client
    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket, String username) throws IOException {
            this.clientSocket = socket;
            this.username = username;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initialisation du flux d'entrée pour lire les messages du client
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) { // Boucle pour lire les messages du client
                    System.out.println("Received message from " + username + ": " + message); // Affichage du message reçu du client
                    broadcast(username + ": " + message); // Diffusion du message à tous les clients connectés
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close(); // Fermeture de la connexion avec le client
                    clients.remove(username); // Suppression du client de la map lors de sa déconnexion
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Méthode pour diffuser un message à tous les clients connectés
        private void broadcast(String message) {
            for (PrintWriter out : clients.values()) { // Parcours de tous les PrintWriter des clients connectés
                out.println(message); // Envoi du message à chaque client
                out.flush(); // Vidage du tampon de sortie pour s'assurer que le message est envoyé immédiatement
            }
        }
    }
}
