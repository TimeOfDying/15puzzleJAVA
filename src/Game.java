import java.util.*;


public class Game implements IPlayable {
    public Integer[][] Field;
    public Map<Integer, Coordinate> CoordinatesOfValues;
    private int tmpI = 0, tmpJ = 0, _tmpI = 0, _tmpJ = 0;

    public Game()
    {
        Field = new Integer[5][5];
        CoordinatesOfValues = new HashMap<>();
        fillField(GetRandomSequence());
    }

    public Game(Integer[] array) {
        try {
            CorrectData(array);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        int dimension = (int) Math.sqrt(array.length);
        Field = new Integer[dimension][dimension];
        CoordinatesOfValues = new HashMap<>();
        fillField(array);
    }

    private void fillField(Integer[] array)
    {
        int count = 0;
        for (int i = 0; i < Dimensions(); i++) {
            for (int j = 0; j < Dimensions(); j++) {
                if (array[count] == 0) {
                    tmpI = i;
                    tmpJ = j;
                    Field[i][j] = array[count];
                    Coordinate coordinate = new Coordinate(i, j);
                    CoordinatesOfValues.put(Field[i][j], coordinate);
                    count++;
                } else {
                    Field[i][j] = array[count];
                    Coordinate coordinate = new Coordinate(i, j);
                    CoordinatesOfValues.put(Field[i][j], coordinate);
                    count++;
                }
            }
        }
    }

    private Boolean CorrectData(Integer[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || !Arrays.asList(array).contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int Dimensions() {
        return Field[0].length;
    }

    @Override
    public boolean Solved() {

        int orderValue = 1;

        for (int i = 0; i < Dimensions(); i++)
        {
            for (int j = 0; j < Dimensions(); j++)
            {
                if ((((i != Dimensions() - 1) || (j != Dimensions() - 1)) && (Field[i][j] != orderValue)) || (Field[Dimensions() - 1][Dimensions() - 1] != 0))
                {
                    return false;
                }
                orderValue++;
            }
        }
        return true;
    }

    public Integer[] GetRandomSequence()
    {
        List<Integer> list = new ArrayList<>();

        while(true) {
            list.clear();
            for (int i = 0; i < Dimensions() * Dimensions(); i++) {
                list.add(i);
            }
            Collections.shuffle(list);
            if(IsSolvablePuzzle(list))
            {
                break;
            }
        }

        Integer[] randomArray = null;
        randomArray = list.toArray(new Integer[list.size()]);

        return randomArray;
    }

    @Override
    public void Randomize()
    {

        CoordinatesOfValues.clear();
        fillField(GetRandomSequence());

    }

    private Boolean IsSolvablePuzzle(List<Integer> list)
    {

        int count = 0;
        for (int i = 0; i < list.size() - 1; i++)
            if (list.get(i) > list.get(i+1)) count++;
        return (count % 2 == 0);

    }

    @Override
    public void Shift(int value)
    {
        _tmpI = CoordinatesOfValues.get(value).X;
        _tmpJ = CoordinatesOfValues.get(value).Y;
        if (Math.abs(_tmpI - tmpI) + Math.abs(_tmpJ - tmpJ) == 1)
        {
            Field[tmpI][tmpJ] = Field[_tmpI][_tmpJ];
            CoordinatesOfValues.remove(0);
            CoordinatesOfValues.remove(value);
            Coordinate coordinateZero = new Coordinate(tmpI, tmpJ);
            Coordinate coordinateValue = new Coordinate(_tmpI, _tmpJ);
            CoordinatesOfValues.put(value, coordinateZero);
            CoordinatesOfValues.put(0, coordinateValue);
            tmpI = _tmpI;
            tmpJ = _tmpJ;
            Field[tmpI][tmpJ] = 0;
        }
        else
        {
            throw new IllegalArgumentException("Число нельзя передвинуть");
        }
    }

    @Override
    public int GetValueByIndexes(int x, int y)
    {
        return Field[x][y];
    }

}
