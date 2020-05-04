package Datastructure.array;

public class findNumInMatrix {
    public static void findNuminMatrix(int[][] matrx, int targ) {
        int row = matrx.length;
        int col = matrx[0].length;
        int i = col - 1;
        boolean found = false;
        while (i >= 0) {
            if (targ >= matrx[0][i]) {
                if (targ == matrx[0][i]) {
                    found = true;
                    System.out.println("Find it!");
                    return;
                }
                for (int j = 0; j < row; j++) {
                    if (targ == matrx[j][i]) {
                        found = true;
                        System.out.println("Find it!");
                        return;
                    }
                }
            }

            i--;
        }
        if (found == false) {
            System.out.println("Didn't find.");
        }
    }
}
