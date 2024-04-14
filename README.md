For swagger -> http://localhost:8080/swagger-ui/index.html#

###Pour la contribution 

1. **Cloner le projet depuis GitHub** :
   ```bash
   git clone https://github.com/Anassidbella/OptiDecision
   ```

2. **Se déplacer vers le répertoire du projet** :
   ```bash
   cd OptiDecision
   ```

3. **Créer une nouvelle branche pour chaque nouvelle tâche ou modification** :
   ```bash
   git checkout -b nom_de_la_branche
   ```
   Remplacez `nom_de_la_branche` par un nom descriptif de votre tâche ou modification.

4. **Effectuer les modifications nécessaires dans les fichiers du projet**.

5. **Ajouter les fichiers modifiés à l'index** :
   ```bash
   git add .
   ```

6. **Effectuer un commit avec un message significatif** :
   ```bash
   git commit -m "Description de la modification ou de la nouvelle tâche"
   ```

7. **Pousser la branche locale vers GitHub** :
   ```bash
   git push origin nom_de_la_branche
   ```
   Assurez-vous de remplacer `nom_de_la_branche` par le nom de la branche que vous avez créée.

8. **Créer une Pull Request (demande de tirage)** :
   - Rendez-vous sur la page du projet sur GitHub.
   - Cliquez sur le bouton "Pull Request".
   - Sélectionnez votre branche comme branche source et la branche principale (main) comme branche cible.
   - Fournissez une description claire de votre contribution.

9. **Attendez que votre Pull Request soit examinée et acceptée** par les autres membres de l'équipe.

10. **Après l'approbation, fusionner votre branche dans la branche principale (main)** :
    - Si cela est possible via l'interface GitHub, utilisez la fonction de fusion de la Pull Request.
    - Sinon, vous pouvez effectuer la fusion en local après avoir récupéré les dernières modifications de la branche principale :
      ```bash
      git checkout main
      git pull origin main
      git merge nom_de_la_branche
      git push origin main
      ```
      Assurez-vous de remplacer `nom_de_la_branche` par le nom de votre branche.

11 ** git pull : Récupère les modifications depuis un dépôt distant et les fusionne avec la branche locale. **
