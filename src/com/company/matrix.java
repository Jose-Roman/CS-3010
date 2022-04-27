package com.company;

//Programming Project 1
//Jose Roman
//CS 4080
//10-26-2021
//Matrix Addition, Subtraction and Multiplication
//4th version
import java.util.Arrays;
import java.util.Scanner;
public class matrix
{
    public static void main(String[] args)
    {
        int rowA; int colA;
        int rowB; int colB;
        Scanner keyboard = new Scanner(System.in);                 //Scanner for user input

        //Get 1st matrix info
        System.out.println("Enter row length of 1st Matrix: ");
        rowA = keyboard.nextInt();
        System.out.println("Enter column length of 1st Matrix: ");
        colA = keyboard.nextInt();
        float [][] Ax = new float[rowA][colA];
        for(int row = 0; row < rowA; row++)
        {
            for (int col = 0; col < colA; col++)
            {
                System.out.println("Enter the "+ (col +1) + " value of the " + (row +1) +" row: ");
                Ax[row][col] = keyboard.nextFloat();
            }
        }

        //Get 2nd matrix info
        System.out.println("Enter row length of 2nd Matrix: ");
        rowB = keyboard.nextInt();
        System.out.println("Enter column length of 2nd Matrix: ");
        colB = keyboard.nextInt();
        float [][] Bx = new float[rowB][colB];
        for(int row = 0; row < rowB; row++)
        {
            for (int col = 0; col < colB; col++)
            {
                System.out.println("Enter the "+ (col +1) + " value of the " + (row +1) +" row: ");
                Bx[row][col] = keyboard.nextFloat();
            }
        }
        boolean running = true;
        while(running)
        {
            System.out.println("Enter an operation (+,-,*): or q to quit ");
            String op = keyboard.next();
            if(op.equals("+"))
                Addition(Ax, Bx);
            else if(op.equals("-"))
                Subtraction(Ax,Bx);
            else if(op.equals("*"))
                Multiply(Ax,Bx);
            else if(op.equals("q"))
                running = false;
            else
                continue;
        }

        //Addition(Ax,Bx);
        //Subtraction(Ax,Bx);
        //Multiply(Ax,Bx);
        //int n = 3;
        //float [][] Ax = {{1,2,3}, {4,5,6}, {7,8,9}};
        //float [][] Bx = {{1,2,3}, {4,5,6}, {7,8,9}};

    }
    public static void Multiply(float[][] ax, float[][] bx)
    {
      if(ax[0].length == bx.length){
            float[][] cx = new float[ax.length][bx[0].length];
            for(int i = 0; i < ax.length;i++)
            {
                for(int j = 0; j < bx[0].length;j++)
                {
                    for(int k = 0; k < bx.length;k++)
                    {
                        cx[i][j] += ax[i][k] * bx[k][j];
                    }
                }
            }
            System.out.println("Multiplication Result: " +Arrays.deepToString(cx));
        }
        else
        {
            System.out.println("Unable to perform Mulitplication because the column length of 1st Matrix does not equal row length of 2nd Matrix");
        }
    }
    public static void Subtraction(float[][] ax, float[][] bx)
    {
        if(ax.length == bx.length && ax[0].length == bx[0].length)
        {
            float[][] cx = new float[ax.length][ax[0].length];
            for(int i = 0; i < ax.length;i++)
            {
                for(int j = 0; j < ax[0].length;j++)
                {
                    cx[i][j] = ax[i][j] - bx[i][j];
                }
            }
            System.out.println("Subtraction Result: " + Arrays.deepToString(cx));
        }
        else
        {
            System.out.println("Unable to perform Subtraction because Matrices are not equal");
        }
    }
    public static void Addition(float[][] ax, float[][] bx)
    {
            if(ax.length == bx.length && ax[0].length == bx[0].length)
            {
                float[][] cx = new float[ax.length][ax[0].length];
                for(int i = 0; i < ax.length;i++)
                {
                    for(int j = 0; j < ax[0].length;j++)
                    {
                        cx[i][j] = ax[i][j] + bx[i][j];
                    }
                }
                System.out.println("Addition Result: " + Arrays.deepToString(cx));
            }
            else
            {
                System.out.println("Unable to perform Addition because Matrices are not equal");
            }
    }
}
