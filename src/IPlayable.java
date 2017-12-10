public interface IPlayable {

    int Dimensions();
    boolean Solved();
    void Randomize();
    void Shift(int value);
    int GetValueByIndexes(int x, int y);

}
