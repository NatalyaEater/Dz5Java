import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;;

public class Li {
    public static int row=5;
    public static int col=5;
    public static int exitX=2;
    public static int exitY=3;
    public static int catX=0;
    public static int catY=0;


    public static void main(String[] args){
    
		var createMap= randomIJ(massiv(row, col));
        System.out.println("Построение рандомной карты : ");
        newMap(createMap,row,col);
		var mapToEnd = vesaLi(createMap, catX, catY, exitX, exitY);
		System.out.print("\n");
        System.out.println("Заполнение карты весами по волновому алгоритму: ");
        newMap(mapToEnd, row, col);
        System.out.print("\n");
        System.out.println("Координаты точек движения для кратчайшего пути : ");
        System.out.println("Координаты : х = "+exitX + "  у = "+exitY +"  ;  ");
        var fin=reverse(mapToEnd, createMap, catX, catY, exitX, exitY);
        newMap(fin,row, col);



		
    }
    
    public static int[][] massiv(int lenI,int lenJ) {
        int[][] ar1 = new int[lenI][lenJ];
        for (int i = 0; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                ar1[i][j] = 1;
            }
        }
        return ar1;
    }

public static int [][] randomIJ(int[][] args) {
    Random p = new Random();
    for (int y=0; y < 4; y++){
        var i=p.nextInt(4);
        var j=p.nextInt(4);
        if ((args[i][j] != 0) && (i != exitX)  && (j != exitY) && (i != catX) && (j != catY)){
			args[i][j]= 0;
		}
    }
    return args;
    }

public static void newMap(int[][] args,int lenI,int lenJ) {
    for (int i = 0; i < lenI; i++) {
         for (int j = 0; j < lenJ; j++) {
                System.out.print(args[i][j]+"  ");
         }
         System.out.print("\n");
        }
    }


    public static int [][] vesaLi(int [][]map,int x1,int x2,int y1,int y2){
        
        int[][] arVesa = new int[row][col];
        int ves=1;
        arVesa[x1][x2]=ves;

        while (arVesa[y1][y2]==0){
            for (int i=0;i<=row-1;i++){
                for (int j=0;j<=col-1;j++){
                    if (arVesa[i][j]==ves){
                        ves++;
                        if (i+1<=row-1 && j<=col-1){
                            if(i+1>=0 && j>=0){
                                if (arVesa[i+1][j] == 0){
                                    if (map[i+1][j] != 0){
                                        arVesa[i+1][j]=ves;
                                    }
                                }
                            }
                        }
                        if (i-1<=row-1 && j<=col-1){
                            if(i-1>=0 && j>=0){
                                if (arVesa[i-1][j] == 0){
                                    if (map[i-1][j] != 0){
                                        arVesa[i-1][j]=ves;
                                    }
                                }
                            }
                        }
                        if (i<=row-1 && j-1<=col-1){
                            if(i>=0 && j-1>=0){
                                if (arVesa[i][j-1] == 0){
                                    if (map[i][j-1] != 0){
                                        arVesa[i][j-1]=ves;
                                    }
                                }
                            }
                        }
                        if (i<=row-1 && j+1<=col-1){
                            if(i>=0 && j+1>=0){
                                if (arVesa[i][j+1] == 0){
                                    if (map[i][j+1] != 0){
                                        arVesa[i][j+1]=ves;
                                    }
                                }
                            }
                        }
                        ves--;
                    }
                }
            }
            ves++;
        }
        return arVesa;
    }

public static int [][] reverse(int [][] mapVes, int[][] map,int x1, int x2, int y1, int y2){
    int ves = mapVes[y1][y2];
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(map[y1][y2]);
    map[y1][y2] = 5;
    int i = y1;
    int j = y2;
    if (ves != 0){
        while (ves > 1){
            if (mapVes[i][j] == ves){
                if ((i-1<=row-1 && j<=col-1) && (i-1>=0 && j>=0) && (mapVes[i-1][j] == ves-1)){
                    i--;
                    ves--;
                    map[i][j] = 5;
                }
                else if ((i+1<=row-1 && j<=col-1) && (i+1>=0 && j>=0) && (mapVes[i+1][j] == ves-1)){
                    i++;
                    ves--;
                    map[i][j] = 5;
                }
                else if ((i<=row-1 && j-1<=col-1) && (i>=0 && j-1>=0) && (mapVes[i][j-1] == ves-1)){
                    j--;
                    ves--;
                    map[i][j] = 5;
                }
                else if ((i<=row-1 && j+1<=col-1) && (i>=0 && j+1>=0) && (mapVes[i][j+1] == ves-1)){
                    j++;
                    ves--;
                    map[i][j] = 5;
                }
                
            }
            System.out.println("Координаты : х = " +i +  "  у = "+j+"  ; ");
        }
    }

    System.out.println("\n");
    System.out.println("Карта с кратчайщим путем : ");
    return map;
}
}