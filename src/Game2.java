import java.util.ArrayList;
import java.util.List;

public class Game2 extends Game implements IPlayable {

    public List<Integer> TurnsHistory;
    public Game2(Integer[] array)
        {
            super(array);
            TurnsHistory = new ArrayList<>();
        }

    @Override
    public void Shift(int value) {
        super.Shift(value);
        TurnsHistory.add(value);
    }

    public void RollBack(int value)
    {
        if(TurnsHistory.size()>=value)
        {
            for (int i=0; i< value; i++)
            {
                int a = TurnsHistory.get(TurnsHistory.size() -1);
                TurnsHistory.remove(TurnsHistory.size() - 1);
                super.Shift(a);
            }
        }
        else throw new IllegalArgumentException("История ходов пуста");
    }
}
