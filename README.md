# Book Management Project

## Description
Ce projet consiste en la gestion d'une application permettant de réserver des livres. Il comprend des fonctionnalités de base telles que l'ajout, la récupération, et la réservation de livres. L'application expose des **endpoints REST** et utilise **Spring Boot** avec **Kotlin** pour la logique métier.

### Fonctionnalités principales
- **Réservation d'un livre** : Ajout d'un champ `isReserved` dans la base de données pour chaque livre. La fonctionnalité de réservation empêche de réserver un livre déjà réservé.
- **API REST** : Plusieurs endpoints sont disponibles pour récupérer les livres et réserver un livre spécifique.
- **Tests unitaires et d'intégration** : Des tests sont écrits pour s'assurer de la bonne fonctionnalité de l'application, y compris des tests d'intégration BDD avec une base de données PostgreSQL via **Testcontainers**.

## Group Members
- **MONDAUT Florian**
- **DALAMEL DE BOURNET Victor**
- **GILLET Lucas**

## Prérequis
- **Java 21** ou version supérieure.
- **Kotlin** : La version utilisée est la 2.0.21.
- **Gradle** : Utilisé pour la gestion du projet.

## Installation

1. Clonez le dépôt dans votre machine locale :
    ```bash
    git clone https://github.com/Dalifo/M-thodoTestM1.git
    cd M-thodoTestM1
    ```

2. Assurez-vous d'avoir **Gradle** installé sur votre machine.

3. Installez les dépendances avec Gradle :
    ```bash
    ./gradlew build
    ```

4. Exécutez l'application :
    ```bash
    ./gradlew bootRun
    ```

## Tests
- Pour exécuter les tests unitaires et d'intégration, utilisez la commande suivante :
    ```bash
    ./gradlew test
    ```

Les résultats des tests seront visibles dans le dossier `build/reports/tests/test/index.html` ou via la ligne de commande.

## Endpoints

### 1. **GET /books**
Retourne la liste des livres avec leur statut de réservation.

### 2. **POST /books**
Ajoute un nouveau livre à la base de données.

### 3. **POST /books/{title}/reserve**
Réserve un livre spécifié par son titre.

## Conclusion
Ce projet est destiné à démontrer l'implémentation d'une application de gestion de livres avec des tests unitaires, d'intégration, et BDD. Nous avons utilisé **Kotlin**, **Spring Boot**, et **Gradle** pour la gestion du projet.

## Contact
- **Email du professeur** : jeanclement.sabourin@ynov.com
