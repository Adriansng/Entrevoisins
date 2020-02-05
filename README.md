# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.#Entrevoisins

 ## Activity app:
 
   *ListNeighbourActivity*
  
      Main activity contenant:

       -Deux fragments: Neighbours et ses favories

       -Un bouton d'ajout
  
       -Un recycler view avec des views d'item (Avatar, nom et un bouton delete)


   *DetailNeighbourActivity*
  
      Activity qui se lance avec le click sur un item du recycler view du main activity qui contient:

       -Toolbar avec un bouton retour à la precedent activity avec l'avatar affiché grace à Glide et son nom

       -Un bouton favorie qui change de color au click et qui renvois le statut favorie à la donnée

       -Deux Cards qui affiche son nom avec ses données personnels et sa description.
       
 ## Class:
 
  *Neighbour*
   
     Paramètre:
      -Int id
      -String name
      -String avatarUrl
      -Boolean favorite

 ## Methode Principale:
 
   *getNeighbours()*
      
      -Récupère la liste des Neighbours
   
   *getFavoriteNeighbours()*
    
      -Mettre un Neighbour en favorie et actualise la liste 
    
   *deleteNeighbour(Neighbour neighbour)*
      
      -Supprime un Neighbour
    
   *getNeighbours(int id)*
      
      -Récupère l'ID du Neighbour
    
   *addRandomNeighbour(Neighbour randomNeighbour)*
      
      -Ajoute aléatoirement un Neighbour dans la liste
    
