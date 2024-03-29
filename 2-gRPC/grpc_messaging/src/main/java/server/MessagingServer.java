package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import com.brahim.grpc.Messaging.*;
import com.brahim.grpc.MessagingServiceGrpc;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessagingServer {
    // Port sur lequel le serveur va écouter les connexions
    private final int port;
    // Map pour stocker les clients connectés
    private final Map<String, StreamObserver<MessageResponse>> clients = new ConcurrentHashMap<>();

    // Constructeur du serveur de messagerie
    public MessagingServer(int port) {
        this.port = port;
    }

    // Méthode pour démarrer le serveur
    public void start() throws IOException {
        // Construction du serveur gRPC
        Server server = ServerBuilder.forPort(port)
                .addService(new MessagingServiceImpl())
                .build()
                .start();

        // Affichage du message de démarrage
        System.out.println("Server started, listening on " + port);

        // Ajout d'un hook pour gérer l'arrêt du serveur
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            server.shutdown();
            System.out.println("Server shut down");
        }));

        try {
            // Attente de terminaison du serveur
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Implémentation du service de messagerie
    private class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
        // Méthode pour envoyer un message à un client
        @Override
        public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            String recipient = request.getRecipient();
            StreamObserver<MessageResponse> client = clients.get(recipient);
            if (client != null) {
                // Envoi du message au client destinataire
                client.onNext(MessageResponse.newBuilder()
                        .setSender(request.getSender())
                        .setMessage(request.getMessage())
                        .build());
                // Envoi d'une confirmation au client émetteur
                responseObserver.onNext(MessageResponse.newBuilder().setMessage("Message sent to " + recipient).build());
            } else {
                // Gestion de l'erreur si le destinataire n'est pas trouvé
                responseObserver.onError(new RuntimeException("Recipient " + recipient + " not found."));
            }
            // Terminaison de l'appel
            responseObserver.onCompleted();
        }

        // Méthode pour recevoir des messages des clients
        @Override
        public StreamObserver<MessageRequest> receiveMessage(StreamObserver<MessageResponse> responseObserver) {
            return new StreamObserver<MessageRequest>() {
                @Override
                public void onNext(MessageRequest request) {
                    // Ajout du client à la liste des clients connectés
                    clients.put(request.getSender(), responseObserver);
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                }

                @Override
                public void onCompleted() {
                    // Terminaison de l'appel
                    responseObserver.onCompleted();
                }
            };
        }
    }

    // Méthode principale pour démarrer le serveur
    public static void main(String[] args) throws IOException {
        // Création et démarrage du serveur de messagerie sur le port 9090
        MessagingServer server = new MessagingServer(9090);
        server.start();
    }
}
