1) on utilise forward() lorsque le login échoue pour afficher à nouveau la page de connexion avec un message d'erreur sans changer l'URL et en conservant les données de la requête 
on utilise sendRedirect() lorsque le login réussit pour rediriger l'utilisateur vers le tableau de bord pour créer une nouvelle requête HTTP et éviter de renvoyer le formulaire de connexion lors de l'actualisation de la page
2) a) si doPost() utilise forward() après l'ajout d'un produit lorsque l'utilisateur appuie sur F5 le navigateur peut renvoyer la même requête POST le produit peut être ajouté une 2 éme fois
 b)si doPost() utilise sendRedirect() le navigateur effectue une nouvelle requête GET vers la liste des produits lorsque l'utilisateur appuie sur F5  la requête GET est répétée et le produit n'est pas ajouté une deuxième fois
3) Avec ${produit.nom}: le navigateur peut interpréter le code JavaScript et afficher une alerte
Avec <c:out value="${produit.nom}"/> le code est affiché comme un simple texte et ne s'exécute pas
4) le filtre permet de vérifier l'authentification avant d'accéder aux pages protégées sans avoir besoin de répéter le même code dans chaque servlet
5) 
