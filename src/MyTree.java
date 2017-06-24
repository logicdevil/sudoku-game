import java.util.ArrayList;

/**
 * Created by daymond on 3/15/17.
 */
class MyTree {
    private SudokuGame game;
    private ArrayList<Integer> mainArray;
    private ArrayList<Integer> array_of_branches = new ArrayList<>();
    private int num;
    MyTree(SudokuGame game, int num) {
        this.game = game;
        this.mainArray = new ArrayList<>(game.getArray());
        this.num = num;
    }
    private MyTree(SudokuGame game, ArrayList<Integer> prevMainArray, int num){
        this.game = game;
        this.mainArray = new ArrayList<>(prevMainArray);
        this.num = num;
    }
    void start() {
        game.end = false;
        while(mainArray.get(num) != 0 && num != 80) num++;
        initArray();
        if (num == 80) {
            game.rightResult = true;
            game.end = true;
            if(array_of_branches.size() > 0) mainArray.set(num,array_of_branches.get(0));
            game.setArray(mainArray);
        }
        if(game.end) {
            return;
        }
        next_branch();
    }

    private void next_branch() {
        if(game.rightResult)
            return;
        for(int i = 0; i < array_of_branches.size(); i++) {
            mainArray.set(num, array_of_branches.get(i));
            MyTree branch = new MyTree(game, mainArray, num + 1);
            branch.start();
        }
    }
    private void initArray() {
            for (int i = 1; i <= 9; i++) {
                if(isAvailable(i))  // if true then add element in the array
                    array_of_branches.add(i);
            }
            if(array_of_branches.size() == 0) {
                game.end = true;
            }
    }
    private boolean isAvailable(int n) { //here we should to identify, can we use that number 'n' here
        int i;
        int j;
        int square;
        if(num < 9 ) {  //another complicated algorithm that should be updated
            j = num; i = 0;
        }
        else if(num < 18 ) {
            j = num - 9; i = 1;
        }
        else if(num < 27 ) {
            j = num - 18; i = 2;
        }
        else if(num < 36 ) {
            j = num - 27; i = 3;
        }
        else if(num < 45 ) {
            j = num - 36; i = 4;
        }
        else if(num < 54) {
            j = num - 45; i = 5;
        }
        else if(num < 63) {
            j = num - 54; i = 6;
        }
        else if(num < 72 ) {
            j = num - 63; i = 7;
        }
        else {
            j = num - 72; i = 8;
        }
        if(j < 3)
            if(i < 3)
                square = 0;
            else if (i < 6)
                square = 27;
            else square = 54;
        else if(j < 6)
            if(i < 3)
                square = 3;
            else if (i < 6)
                square = 30;
            else square = 57;
        else
            if(i < 3)
                square = 6;
            else if (i < 6)
                square = 33;
            else square = 60;


        for(int k = 0; k < 9; k++) {
            if(mainArray.get(k + (i * 9)) == n)
                return false;
            if(mainArray.get((j + (k * 9))) == n)
                return false;
        }
        for(int k = square; k <= square + 18; k+=9)
            for(int l = 0; l < 3; l++) {
                if(k + l == num)
                    continue;
                if(mainArray.get(k + l) == n)
                    return false;
            }
        return true;
    }
}
