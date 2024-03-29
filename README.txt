#MiniProjet
------------------I- Java RMI:

	1) Définition de l'interface (TaskListInterface):
1.1- L'interface devra étendre l'interface java.rmi.Remote "\n"
1.2- Définir les méthodes qui seront exposées au travers de RMI 
1.3- les méthodes exposées devront être capables de propager une exception de type java.rmi.RemoteException

	2) Implémentation de l'interface:
Elle sera référencée par notre registry côté serveur

	3) Création du serveur (TaskListServer):
3.1- Instancier l'implémentation
3.2- Créer un skeleton qui sera publié grâce au registry

	4) Création du client (TaskListClient):
4.1- Utiliser RMI afin de récupérer une instance stub
4.2- Déléguer l'appel de la méthode au travers d'un socket

	5) Compiler le code (par javac)

	6) Test:
6.1- Démarrer le registre RMI
6.2- Démarrer le serveur RMI
6.3- Démarrer le client RMI
6.4- Vérifier le bon fonctionnement

------------------II- gRPC:

	1) Définition du service:
1.1- Création d'un fichier .proto (messaging.proto)
1.2- Définir un service (MessagingService) en spécifiant les méthodes rpc,un protocole réseau qui permet de réaliser des appels de procédures sur un ordinateur à distance:
		- rpc SendMessage(MessageRequest) returns (MessageResponse) => Un simple RPC
		- rpc ReceiveMessage(stream MessageRequest) returns (stream MessageResponse) => Un RPC de streaming bidirectionnel 
1.3- Définir les types de messages de tampon de protocole pour les requêtes et les réponses utilisés (MessageRequest, MessageResponse)

	2) Création du serveur (MessagingServer):
2.1- Mettre en œuvre toutes les méthodes de service: 
		- SendMessage: pour envoyer un message à un client spécifique
			a. Chercher le client recipient
			b. Construire et remplir un objet de réponse à renvoyer au client
			c. Renvoyer l'objet par la méthode onNext() de l'observateur de réponse
		- ReceiveMessage: pour recevoir des messages des clients
			Ajout du client à la liste des clients connectés
		- Terminer l'appel
2.2- Exécuter un serveur gRPC pour écouter les demandes des clients et renvoyer les réponses du service:
		- Créer une instance de la classe d'implémentation de service
		- Créer et démarrer un serveur RPC

	3) Création du client (MessagingClient):
3.1- Créer un canal gRPC pour le stub
3.2- Utiliser le canal pour créer le stub
3.3- Créer et remplir un objet tampon de protocole de requête
3.4- Appeler la méthode sendMessage du stub avec la requête et un StreamObserver pour gérer la réponse

	4) Tester l'application:
4.1- Ajouter les dépendance et les pluging dans le fichier pom.xml
4.2- Lancer le serveur et des clients
4.3- S'assurer que les appels sont traités de manière adéquate et que les réponses sont correctes

rq: La génération des interfaces client et serveur gRPC se fait à partir de définition de service .proto

------------------III- Sockets:

	1) Implémentation du socket serveur (ChatServer):
1.1- Instanciation d'un socket serveur moyennant moyenneant le constructeur du ServerSocket qui demande de spécifier un port
1.2- Attendre qu'un client se connecte
En cas de connexion,
1.3- Récupérons d'un objet socket et création d'un nouvel objet par une classe interne (ClientHandler) qui va démarrer un thread qui se chargera d'administrer la demande de connexion
1.4- Création des flux d'E/S pour faciliter la communication avec le client (BufferedReader in, PrintWriter out)
1.5- Faire le traitement (par la méthode "run"): Dans notre cas, il y aura une récupération du message du client, du flux d'entrée, puis une diffusion à tous les clients connectés (broadcast).

Rq: L'utilisation d'un thread permet au serveur de ne pas bloquer les demandes des autres clients en attendant que la demande en cours soit résolue.

	2) Création du client (ChatClient):
2.1- Se connecter au serveur via le constructeur du socket qui attend deux arguments: adresse IP et port
2.2- Création des deux flux d'entrée et de sortie du socket client pour les interactions avec le serveur
2.3- Dédinir le mode de communication entre le serveur et les clients.

	3) Test de l'application :
3.1- Lancer le serveur et plusieurs clients
3.2- Vérifier les connexions
3.3- vérifier que le serveur reçoit et redistribue correctement les messages du client à partir des sockets
