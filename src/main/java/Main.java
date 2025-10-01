// Ha a package sort generálta az IntelliJ, hagyhatod is:
// package hu.bme.pt;  // <-- opcionális

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Két mátrix (régi és új) összehasonlítása.
 * Cél: a legkisebb téglalap (felülről x1,y1; alulról x2,y2), ami lefedi az ÖSSZES eltérő elemet.
 * Bemenet: projekt GYÖKERÉBEN lévő input.txt (R C; majd R sor régi; üres sor; R sor új).
 * Kimenet: 1-alapú koordináták: "x1 y1 x2 y2", vagy "A két kép AZONOS."
 */
public class Main {

    public static void main(String[] args) {
        // 1) input.txt megnyitása a projekt gyökeréből
        File f = new File("input.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("HIBA: Nem találom az input.txt-t a projekt gyökerében!");
            return;
        }

        // 2) Első sor: R (sorok), C (oszlopok)
        int R = sc.nextInt();
        int C = sc.nextInt();

        // 3) Régi és új mátrix beolvasása
        int[][] oldImg = readMatrix(sc, R, C);  // R sor régi
        skipBlankLine(sc);                      // üres sor átugrása (ha van)
        int[][] newImg = readMatrix(sc, R, C);  // R sor új

        // 4) Eltérő sorok/ oszlopok szélei (sentinel értékekkel)
        int top    = findFirstDifferentRow(oldImg, newImg, R, C);  // ha nincs eltérés: R
        int bottom = findLastDifferentRow(oldImg, newImg, R, C);   // ha nincs eltérés: -1
        int left   = findFirstDifferentCol(oldImg, newImg, R, C);  // ha nincs eltérés: C
        int right  = findLastDifferentCol(oldImg, newImg, R, C);   // ha nincs eltérés: -1

        // 5) Nincs eltérés?
        if (top > bottom || left > right) {
            System.out.println("A két kép AZONOS.");
            sc.close();
            return;
        }

        // 6) 1-alapúvá alakítjuk a koordinátákat (+1)
        int x1 = top + 1, y1 = left + 1, x2 = bottom + 1, y2 = right + 1;

        // 7) Kiírjuk a téglalapot egy sorban
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

        sc.close();
    }

    // ---------------- Segédfüggvények (nagyon kezdő barát) ----------------

    // Mátrix beolvasása: R sor * C oszlop
    private static int[][] readMatrix(Scanner sc, int R, int C) {
        int[][] m = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                m[r][c] = sc.nextInt();
            }
        }
        return m;
    }

    // Üres sort átugrunk, ha a következő token nincs azonnal (pl. sortörés volt)
    private static void skipBlankLine(Scanner sc) {
        if (sc.hasNextLine()) {
            sc.nextLine(); // befejezzük az aktuális sort
        }
        if (sc.hasNext("\n") || sc.hasNext("\r\n")) {
            sc.nextLine(); // ha tényleg üres sor jön, azt is átlépjük
        }
    }

    // Ellenőrizzük, hogy két adott sor azonos-e
    private static boolean equalRow(int[][] a, int[][] b, int row, int C) {
        for (int c = 0; c < C; c++) {
            if (a[row][c] != b[row][c]) return false;
        }
        return true;
    }

    // Ellenőrizzük, hogy két adott oszlop azonos-e
    private static boolean equalCol(int[][] a, int[][] b, int col, int R) {
        for (int r = 0; r < R; r++) {
            if (a[r][col] != b[r][col]) return false;
        }
        return true;
    }

    // Fentről az első eltérő sor sorszáma (vagy R, ha nincs)
    private static int findFirstDifferentRow(int[][] a, int[][] b, int R, int C) {
        for (int r = 0; r < R; r++) {
            if (!equalRow(a, b, r, C)) return r;
        }
        return R;
    }

    // Lentről az utolsó eltérő sor sorszáma (vagy -1, ha nincs)
    private static int findLastDifferentRow(int[][] a, int[][] b, int R, int C) {
        for (int r = R - 1; r >= 0; r--) {
            if (!equalRow(a, b, r, C)) return r;
        }
        return -1;
    }

    // Balról az első eltérő oszlop (vagy C, ha nincs)
    private static int findFirstDifferentCol(int[][] a, int[][] b, int R, int C) {
        for (int c = 0; c < C; c++) {
            if (!equalCol(a, b, c, R)) return c;
        }
        return C;
    }

    // Jobbról az utolsó eltérő oszlop (vagy -1, ha nincs)
    private static int findLastDifferentCol(int[][] a, int[][] b, int R, int C) {
        for (int c = C - 1; c >= 0; c--) {
            if (!equalCol(a, b, c, R)) return c;
        }
        return -1;
    }
}
