package ru.geekbrains.guess_the_number;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static char[][] map;
    static final int SIZE=3;
    static final int DOTS_TO_WIN=3;
    static final char X='X';
    static final char O='O';
    static final char empty='*';

    public static void main(String[] args) {
        InitMap();
        PrintMap();
        while(true){
        MakeHumanTurn();
            PrintMap();
            if(HasWin()){
                System.out.println("Победил человек! ");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья! ");
                break;
            }
        MakeAiTurn();
            PrintMap();
            if(HasWin()){
                System.out.println("Победил Ai! ");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья! ");
                break;
            }
        }
    }
    static void InitMap(){
        map = new char[SIZE][SIZE];
        for (int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                map[i][j]=empty;

            }
        }
    }
    static void PrintMap(){
        for (int i=0; i<=map.length;i++){
            System.out.print(i+" ");
        }
        System.out.println();

        for (int i=0; i<map.length; i++){
            System.out.print((i+1)+" ");
            for (int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void MakeHumanTurn(){
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do{
            System.out.println("Введите координаты x и y");
            x= scanner.nextInt()-1;
            y= scanner.nextInt()-1;
        }while(!isCellVallid(x, y));
        map[y][x]=X;
    }
    static void MakeAiTurn() {
        Random random = new Random();
        int x;
        int y;
        do {
            System.out.println("Введите координаты x и y");
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellVallid(x, y));
        System.out.println("AI сходил в точку " +(x+1)+" "+(y+1));
        map[y][x] = O;
    }
    static boolean isCellVallid(int x, int y){
        if(x<0 || x>=SIZE || y<0 || y>=SIZE){
            return false;
        }
        else if (map[y][x]==empty){
            return true;
        }
        else{
            return false;
        }
    }
    static boolean HasWin() {
       return CheckHorizontal() || CheckVertical() || CheckDiagonal();//улучшенная проверка выиграша, не понимаю почему метод возращает true без проверки
    }
    static boolean CheckHorizontal(){
        for (int i = 0; i < SIZE; i++) {
            boolean res = true;
            for (int j = 1; j < SIZE && res; j++)
                res = map[i][j] == map[i][0];
            if (res)
                return true;
        }
        return false;
        }

    static boolean CheckVertical(){
        for (int i = 0; i < SIZE; i++) {
            boolean res = true;
            for (int j = 1; j < SIZE && res; j++)
                res = map[j][i] == map[0][i];
            if (res)
                return true;
        }
        return false;
    }
    static boolean CheckDiagonal(){
        boolean res = true;
        for (int i = 1; i < SIZE && res; i++)
            res = map[i][i] == map[0][0];
        if (res)
            return true;
        res = true;
        for (int j = 1; j < SIZE && res; j++)
            res = map[SIZE - j - 1][j] == map[SIZE - 1][0];
        return res;
        }

    static boolean isMapFull(){
        for(int i=0; i<map.length;i++){
            for(int j=0; j<map[i].length; j++){
                if(map[i][j]==empty){
                    return false;
                }
            }
        }
        return true;
    }
}
