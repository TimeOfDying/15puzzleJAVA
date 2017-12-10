import java.util.Arrays;
import java.util.Scanner;

public class TestGame {

    public static void Printing(Integer[][] matrix, int Dimensions)
    {
        for(int i=0; i<Dimensions; i++) {
            for (int j = 0; j < Dimensions; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args)
    {
        /*Game game = new Game(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});
        game.Randomize();
        System.out.println(game.Dimensions());
        Printing(game.Field, game.Dimensions());*/
        Scanner in = new Scanner(System.in);
        Game2 game2 = new Game2(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});
        game2.Randomize();

        Printing(game2.Field, game2.Dimensions());
        int shifting = in.nextInt();
        game2.Shift(shifting);

        System.out.println(game2.TurnsHistory.get(0));
        Printing(game2.Field, game2.Dimensions());
    }

}
