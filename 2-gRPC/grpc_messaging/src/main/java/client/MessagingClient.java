package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import com.brahim.grpc.Messaging.*;
import com.brahim.grpc.MessagingServiceGrpc;

import java.util.Scanner;

public class MessagingClient {
    // Canal gRPC pour la communication avec le serveur
    private final ManagedChannel channel;
    // Stub pour appeler les méthodes du service de messagerie
    private final MessagingServiceGrpc.MessagingServiceStub stub;

    // Constructeur du client de messagerie
    public MessagingClient(String host, int port) {
        // Création du canal gRPC
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        // Création du stub à partir du canal
        stub = MessagingServiceGrpc.newStub(channel);
    }

    // Méthode pour envoyer un message au serveur
    public void sendMessage(String sender, String recipient, String message) {
        // Création de la requête de message
        MessageRequest request = MessageRequest.newBuilder()
                .setSender(sender)
                .setRecipient(recipient)
                .setMessage(message)
                .build();

        // Appel de la méthode sendMessage du stub avec la requête et un StreamObserver pour gérer la réponse
        stub.sendMessage(request, new StreamObserver<MessageResponse>() {
            @Override
            public void onNext(MessageResponse value) {
                // Action lors de la réception d'une réponse
                System.out.println(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                // Gestion des erreurs
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Action lorsque l'envoi du message est terminé
                System.out.println("Message sent successfully");
            }
        });
    }

    // Méthode principale du client
    public static void main(String[] args) {
        // Création du client de messagerie avec l'hôte et le port
        MessagingClient client = new MessagingClient("localhost", 9090);

        // Demande à l'utilisateur de saisir son nom
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String sender = scanner.nextLine();

        // Création d'un StreamObserver pour recevoir les messages entrants du serveur
        StreamObserver<MessageRequest> observer = client.stub.receiveMessage(new StreamObserver<MessageResponse>() {
            @Override
            public void onNext(MessageResponse value) {
                // Action lors de la réception d'un message du serveur
                System.out.println(value.getSender() + ": " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                // Gestion des erreurs
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Action lorsque la réception des messages est terminée
                System.out.println("Server ended stream");
            }
        });

        // Envoie au serveur une demande pour recevoir les messages destinés à cet utilisateur
        observer.onNext(MessageRequest.newBuilder().setSender(sender).build());

        // Boucle pour envoyer des messages
        System.out.println("Type 'quit' to exit");
        while (true) {
            // Demande à l'utilisateur de saisir le destinataire et le message
            System.out.print("Recipient: ");
            String recipient = scanner.nextLine();
            // Vérifie si l'utilisateur veut quitter
            if (recipient.equalsIgnoreCase("quit")) {
                // Termine la réception de messages du serveur
                observer.onCompleted();
                break;
            }
            // Demande à l'utilisateur de saisir le message
            System.out.print("Message: ");
            String message = scanner.nextLine();
            // Envoie le message au serveur
            client.sendMessage(sender, recipient, message);
        }

        // Arrête le canal gRPC
        client.channel.shutdown();
    }
}
