# Documentation de l'API pour les Producteurs et les client

## Pour les Producteurs (rôle "PRODUCTEUR") :
  
 

### S'inscrire (Créer un compte) :
* [x] POST /api/auth/producteur/register

* ### S'authentifier  :
* [x] POST /api/auth/producteur/authenticate

### Se connecter / Se déconnecter :

* [ ] POST /api/producteur/logout

### Gérer le profil (mettre à jour les informations personnelles) :

* [x] PUT /api/producteur/profil




### Ajouter un produit :
* [x] POST /api/producteur/produit/add

### Mettre à jour un produit :
* [x] PUT /api/producteur/produit
* [x] DELETE /api/producteur/produit

### Gérer les commandes (accepter/refuser, mettre à jour le statut de la commande) :
* [ ] GET /api/producteur/commandes/{id}
* [ ] PUT /api/producteur/commandes

### Communiquer avec les clients :
* [ ] POST /api/conversations
* [ ] GET /api/conversations/{conversationId}/messages
* [ ] POST /api/conversations/{conversationId}/messages

### Gérer les avis et évaluations (voir/répondre aux avis) :
* [ ] GET /api/producteur/{id}/avis
* [ ] POST /api/producteur/{id}/avis

## Pour les client (rôle "CLIENT") :

### S'inscrire (Créer un compte) :
* [x] POST /api/auth/client/register

* ### S'authentifier  :
* [x] POST /api/auth/client/authenticate

### Se déconnecter :
* [ ] POST /api/client/logout

### Gérer le profil (mettre à jour les informations personnelles) :
* [X] PUT /api/client/profil/{id}


### Passer une commande (ajouter des produits au panier, option de livraison) :
* [X] POST /api/client/commande

### Suivre sa commande (notification pour chaque changement d'état) :
* [ ] GET /api/client/commande/{id}

### Afficher ses commandes (en cours et passées) :
* [ ] GET /api/client/commandes

### Communiquer avec les fournisseurs :
* [ ] POST /api/conversations
* [ ] GET /api/conversations/{conversationId}/messages
* [ ] POST /api/conversations/{conversationId}/messages

### Évaluer et laisser un avis :
* [ ] POST /api/client/{id}/avis

### Effectuer des paiements sécurisés :
* [ ] Intégration avec un service de paiement tiers (par exemple, Stripe, PayPal).


## En commun :

### Afficher produit :
* [x] Get /api/produit/id/{id}

### Consulter le profil :
* [x] POST /api/producteur/profil
* [x] POST /api/client/profil

### Rechercher des produits par catégorie, dates, prix, etc. :
* [ ] GET /api/produits?categorie={categorie}&date={date}&prix={prix}