import java.util.Iterator;

import java.util.TreeSet;

public interface Strategy {
    public Grade metoda_aleasa(TreeSet<Grade> grades);
}
class BestPartialScore implements Strategy {
    @Override
    public Grade metoda_aleasa(TreeSet<Grade> grades) {
        Grade max = grades.first();
        Iterator<Grade> it = grades.iterator();
        while (it.hasNext()) {
            Grade g = it.next();
            if (g.getPartialScore() > max.getPartialScore()) {
                max = g;
            }
        }
        return max;
    }
}
class BestExamScore implements Strategy {
    public Grade metoda_aleasa(TreeSet<Grade> grades) {
        Grade max = grades.first();
        Iterator<Grade> it = grades.iterator();
        while (it.hasNext()) {
            Grade g = it.next();
            if (g.getExamScore() > max.getExamScore()) {
                max = g;
            }
        }
        return max;
    }
}
class BestTotalScore implements Strategy {
    @Override
    public Grade metoda_aleasa(TreeSet<Grade> grades) {
        Grade max = grades.first();
        Iterator<Grade> it = grades.iterator();
        while (it.hasNext()) {
            Grade g = it.next();
            if (g.getTotal() > max.getTotal()) {
                max = g;
            }
        }
        return max;
    }
}
