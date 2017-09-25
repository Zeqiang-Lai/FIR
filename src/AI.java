import java.util.HashMap;

public class AI {

    static final int hLineNum = 14;
    static final int vLineNum = 14;

    private static int[][] AIScore = new int[hLineNum + 2][vLineNum + 2];
    private static int[][] userScore = new int[hLineNum + 2][vLineNum + 2];

    // 分别为连5， 活4， 冲4， 活3， 眠3， 活2， 眠2 空 的分数
    private static int[] grade = {100000, 20000, 3500, 3000, 900, 2000, 500,1};

    private static int[][] board;

    private static int[][] patterns = {{1,11111}, {1,411114}, {5,21111,11112,11141,14111,11411}, {3,41114,1141,1411},
            {8,2111,1112,21141,14112,11441,14411,14141,2411142}, {3,441144,41414,1441}, {7,211444,444112,214144,2441412,14414,41441,14441}};


    public static HashMap<Integer, Integer> findBestPosition(int[][] chessBorad, int nowUser) {
        HashMap<Integer, Integer> ans = new HashMap<>();

        board = chessBorad;
        int ax = 7,ay = 7,ux = 7,uy = 7;

        for (int i = 0; i <= hLineNum; i++)
            for (int j = 0; j <= vLineNum; j++) {
                AIScore[i][j] = calScore(i, j, nowUser);
                userScore[i][j] = calScore(i, j, -nowUser);
            }

        int amaxn = 0, umaxn = 0;
        for (int i = 0; i <= hLineNum; i++)
            for (int j = 0; j <= vLineNum; j++) {
                if (AIScore[i][j] > amaxn) {
                    amaxn = AIScore[i][j];
                    ax = i;
                    ay = j;
                }
                if (userScore[i][j] > umaxn) {
                    umaxn = userScore[i][j];
                    ux = i;
                    uy = j;
                }
            }
        // attack
        if(amaxn > umaxn || umaxn < 4000){
            ans.put(1,ax);
            ans.put(2,ay);
            System.out.println("attack");

        }
        //defense
        else{
            ans.put(1,ux);
            ans.put(2,uy);
            System.out.println("defense");
        }
        return ans;
    }

    private static int calScore(int x, int y, int user) {

        if(board[x][y] != 0) return -100;
        board[x][y] = user;

        int score = 0;
        long state,t;
        int match;
        // four direction
        // horizontal
        state = 0;t=1;
        for(int i=5;i>=-5;i--){
            if(x+i > hLineNum || x+i < 0) state += 3*t;
            else if(board[x+i][y] == -user) state += 2*t;
            else if(board[x+i][y] == 0) state += 4*t;
            else state += 1*t;
            t *= 10;
        }
        match = matchPattern(state);
        score += grade[match];

        // vertical
        state = 0;t=1;
        for(int i=5;i>=-5;i--){
            if(y+i > hLineNum || y+i < 0) state += 3*t;
            else if(board[x][y+i] == -user) state += 2*t;
            else if(board[x][y+i] == 0) state += 4*t;
            else state += 1*t;
            t *= 10;
        }
        match = matchPattern(state);
        score += grade[match];

        // left diagonal
        state = 0;t=1;
        for(int i=5;i>=-5;i--){
            if(x+i > hLineNum || x+i < 0 || y+i > vLineNum || y+i < 0) state += 3*t;
            else if(board[x+i][y+i] == -user) state += 2*t;
            else if(board[x+i][y+i] == 0) state += 4*t;
            else state += 1*t;
            t *= 10;
        }
        match = matchPattern(state);
        score += grade[match];

        // right diagonal
        state = 0;t=1;
        for(int i=5;i>=-5;i--){
            if(x+i > hLineNum || x+i < 0 || y-i > vLineNum || y-i < 0) state += 3*t;
            else if(board[x+i][y-i] == -user) state += 2*t;
            else if(board[x+i][y-i] == 0) state += 4*t;
            else state += 1*t;
            t *= 10;
        }
        match = matchPattern(state);
        score += grade[match];

        board[x][y] = 0;
        return score;
    }

    private static int matchPattern(long state) {
        int match = 7;
        String sstate = String.valueOf(state);

        for(int i=0;i<7;i++) {
            boolean find = false;
            for (int j = 1; j <= patterns[i][0]; j++){
                String pattern = String.valueOf(patterns[i][j]);
                int index = sstate.indexOf(pattern);

                if(index == -1 || index > 5 || index + pattern.length() < 6) continue;
                if(pattern.charAt(5-index) == '1') {
                    find = true;
                    break;
                }
            }
            if(find) {
                match = i;
                break;
            }
        }
        return match;
    }
}
