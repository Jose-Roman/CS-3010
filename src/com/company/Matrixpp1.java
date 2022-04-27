package com.company;
//Programming Project 1
//Jose Roman
//CS 4080
//10-26-2021
//Matrix Addition, Subtraction and Multiplication
//4th version
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Matrixpp1 {
    public static void main(String[] args) {

      Scanner keyboard = new Scanner(System.in);

        int rowA;int colA;int rowB;int colB;
                         //Scanner for user input

        //Get 1st matrix info
        System.out.println("Enter row length of 1st Matrix: ");
        rowA = keyboard.nextInt();
        System.out.println("Enter column length of 1st Matrix: ");
        colA = keyboard.nextInt();
        float[][] ax = input(rowA,colA);
        //Get 2nd matrix info
        System.out.println("Enter row length of 2nd Matrix: ");
        rowB = keyboard.nextInt();
        System.out.println("Enter column length of 2nd Matrix: ");
        colB = keyboard.nextInt();

        float[][] bx = input(rowB,colB);


            boolean running = true;
            while(running)
            {
                MatrixOP a = new MatrixOP();
                System.out.println("Enter an operation (+,-,*): or q to quit ");
                String op = keyboard.next();
                if(op.equals("+"))
                {
                    if(rowA == rowB && colA == colB)
                    {
                        System.out.println("Addition Result: ");
                        a.addMatrix(ax,bx);
                    }
                    else
                    {
                        System.out.println("Unable to perform Addition because Matrices are not equal");
                    }
                }
                else if(op.equals("-"))
                {
                    if(rowA == rowB && colA == colB)
                    {
                        System.out.println("Subtraction Result: ");
                       a.subMatrix(ax,bx);
                    }
                    else
                    {
                        System.out.println("Unable to perform Subtraction because Matrices are not equal");
                    }

                }
                else if(op.equals("*"))
                {
                    if(colA == rowB )
                    {
                        System.out.println("Multiplication Result: ");
                        a.multMatrix(ax,bx,rowA,colB,colA);
                    }
                    else
                    {
                        System.out.println("Unable to perform Mulitplication because the column length of 1st Matrix does not equal row length of 2nd Matrix");
                    }
                }
                else if(op.equals("q") || op.equals("Q"))
                    running = false;
                else
                    continue;
            }
    }

    public static float[][] input(int row, int col)
    {
        Scanner keyboard = new Scanner(System.in);
        float[][] cx = new float[row][col];
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.println("Enter the " + (j + 1) + " value of the " + (i + 1) + " row: ");
                cx[i][j] = keyboard.nextFloat();
            }
        }
        return cx;
    }
}


   class MatrixOP {
        int row;
        int col;
        float[][] cx;


        public float[][] addMatrix(float[][] ax, float[][] bx) {
            float[][] cx = new float[ax.length][ax[0].length];
            for (int i = 0; i < ax.length; i++) {
                for (int j = 0; j < ax[0].length; j++) {
                    cx[i][j] = ax[i][j] + bx[i][j];
                }
            }
            print(cx);
            return cx;
        }

        public float[][] subMatrix(float[][] ax, float[][] bx) {
            float[][] cx = new float[ax.length][ax[0].length];
            for (int i = 0; i < ax.length; i++) {
                for (int j = 0; j < ax[0].length; j++) {
                    cx[i][j] = ax[i][j] - bx[i][j];
                }
            }
            print(cx);
            return cx;
        }

        public float[][] multMatrix(float[][] ax, float[][] bx, int rowA, int colB, int colA) {
            float[][] cx = new float[rowA][colB];
            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < colB; j++) {
                    for (int k = 0; k < colA; k++) {
                        cx[i][j] += ax[i][k] * bx[k][j];
                    }
                }
            }
            print(cx);
            return cx;
        }
       public static void print(float[][] cx)
       {
           for(int i = 0; i < cx.length;i++)
           {
               for(int j = 0; j < cx[0].length;j++)
               {
                    System.out.print(cx[i][j] + " ");
               }
               System.out.println();
           }
       }
    }
