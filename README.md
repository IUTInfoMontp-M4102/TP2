# TP2 - Gestionnaires de tâches

Dans ce TD, nous allons continuer l’étude des _Threads_ en Java et voir en particulier comment on peut utiliser des _gestionnaires_ spécifiques pour exécuter des tâches (les tâches elles-mêmes ne sont pas nécessairement des _threads_, mais les gestionnaires peuvent utliser les _threads_ pour les réaliser).

**Rappel de cours :** En Java les interfaces `Executor` et `ExecutorService` décrivent des « boîtes à outils » pour la création et la gestion de groupes de tâches. La gestion des tâches est déléguée à un `Executor` en lui passant la tâche par sa méthode `execute(Runnable command)`. Les tâches sont alors créées et exécutées par le gestionnaire (souvent sous forme de _threads_).

Plusieurs implémentations de ces interfaces existent. Certaines sont proposées par la classe `Executors`. Par exemple la méthode `static ExecutorService newFixedThreadPool(int nThreads)` de la fabrique `Executors` retourne un objet qui implémente l’interface `ExecutorService`. Ce gestionnaire permet d'exécuter et de gérer un ensemble de tâches avec une limitate sur le nombre de tâches actives. Le nombre de tâches est le nombre maximum d’exécutions simultanées. Les tâches supplémentaires sont placées dans une file d’attente et ne sont lancées que lorsqu'une tâche active se termine.

En général, pour pouvoir exécuter des tâches en utilisant un gestionnaire particulier existant, il faut :
- créer un gestionnaire de tâches (un objet qui implémente `Executor` ou `ExecutorService`) ;
- créer une instance pour chaque tâche à exécuter en implémentant l’interface `Runnable` ;
- lancer l'exécution de chacune des tâches en appelant la méthode `execute()` du gestionnaire de tâches.

**Attention :** c’est le gestionnaire qui s’occupe de la création des objets (_threads_) nécessaires et de l’exécution.


## Exercice 1 : Écriture d’un gestionnaire implémentant `Executor`

L’interface `Executor` est très simple. Ses implémentations permettent de prendre des objets qui implémentent l'interface `Runnable` (donc des objets avec une méthode `void run()`) pour créer et exécuter des `Thread`. Il est également possible d’ajouter différentes fonctionnalités à cette première version très simple. `ExecutorService` est aussi une interface qui enrichit les services par héritage (étudiez la documentation).

1. Définissez une classe `MyExecutor` qui implémente l'interface `Executor` avec un attribut `ArrayList<Thread> threads` initialement vide.

2. Ajoutez à cette classe une méthode `execute(Runnable command)` (requise par l'interface `Executor`) qui instancie et démarre l'exécution d'un `Thread` à partir de l’objet `Runnable` passé en argument.

3. Ajoutez une méthode `boolean isActive()` qui retourne `true` si au moins un des _threads_ lancés par l'_executor_ est actif et `false` sinon.
   
   **Indication :** Vous pouvez ajouter des attributs à la classe et faire les modifications nécessaires pour pouvoir suivre l'exécution des _threads_.

4. Écrivez une méthode `void joinAll()` qui attend la fin de l’exécution de tous les _threads_ lancés.

5. Testez ce gestionnaire avec une classe qui implémente `Runnable` de votre choix.


## Exercice 2 : Utilisation simple des gestionnaires fournis dans la classe `Executors`

On rappelle que la classe `Executors` est une fabrique qui peut retourner différentes instances de `Executor`.

1. Lisez le code des classes `Ex2Tache` et `Ex2Main`.

2. Exécutez le programme `Ex2Main`. Comment sont exécutées les trois tâches ? Pourquoi ?

3. Pourquoi la méthode main ne s’arrète pas ? Modifiez-la pour terminer son exécution.
   
   **Indication :** Cherchez dans la documentation de `ExecutorService`.

4. Utilisez un autre gestionnaire (également fourni par une méthode de `Executors`) pour permettre l'exécution de deux tâches simultanément. Exécutez le programme et observez le résultat.

5. Les comportements sont encore plus représentatifs quand la pause dans les tâches est aléatoire. Transformez la classe `Ex2Tache` pour que la durée de la pause exécutée à chaque itération soit choisie aléatoirement entre `delai` et `2 * delai`.

   **Indication :** Regardez la documentation de la classe `Random`.

6. Modifiez le programme pour exécuter les trois tâches simultanément. Relancez l’application plusieurs fois pour voir si les affichages diffèrent d'une fois sur l'autre.

7. Quelle est la différence entre les méthodes `shutdown()` et `shutdownNow()` de l'interface `ExecutorService` ? Proposez un programme qui illustre la différence de fonctionnement entre les deux.

