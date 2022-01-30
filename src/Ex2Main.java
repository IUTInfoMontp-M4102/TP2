import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

public class Ex2Main {
    public static void main(String [] args) { Executor es;
        es = Executors.newSingleThreadExecutor();

        System.out.println(" debut tache principale ");
        es.execute(new Ex2Tache(1, 100, 10));
        es.execute(new Ex2Tache(2, 150, 10));
        es.execute(new Ex2Tache(3, 100, 15));
        System.out.println(" fin tache principale ");
    }
}