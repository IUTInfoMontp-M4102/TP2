public class Ex2Tache implements Runnable {
    private int id;
    private int delai;
    private int nbIterations;
    private String signature;

    public Ex2Tache(int id, int delai, int nbIterations) {
        this.id = id;
        this.delai = delai;
        this.nbIterations = nbIterations;
        this.signature = " ".repeat(id) + "x";
    }

    @Override
    public void run() {
        for (int i = 1; i < nbIterations; i++) {
            System.out.printf("T%d\t%s\n", id, signature);
            try {
                Thread.sleep(delai);
            } catch (InterruptedException ignored) {}
        }
        System.out.printf("Fin de la tâche T%d\n", id);
    }
}
