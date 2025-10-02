# Mátrix különbség program

Ez egy egyszerű **Java Maven** projekt, amely két n × n-es mátrix összehasonlításával foglalkozik.  
A program megkeresi a két mátrix közötti legkisebb téglalapot, ahol különböznek egymástól.

## Mit tud a program?

1. Beolvassa két mátrix adatait egy `input.txt` fájlból.
2. Megvizsgálja, hogy hol térnek el egymástól a mátrixok.
3. Meghatározza a legkisebb téglalapot, amely lefedi az összes eltérést.
4. Kiírja a téglalap koordinátáit (x1, y1, x2, y2) a konzolra.
    - Ha a mátrixok azonosak, akkor `-1 -1 -1 -1` az eredmény.

## Példa `input.txt`
2 2
1 2
3 4
1 9
3 4

## Első mátrix (régi): Második mátrix (új):
1 2 1 9
3 4 3 4

### Program futása
2 1 2 2
