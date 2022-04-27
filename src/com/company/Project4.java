package com.company;

//Jose Roman
//CS 3010
//11/5/20 Project 4

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;
public class Project4
{
    public static void main(String[] args) throws FileNotFoundException {
        int n;
        Scanner keyboard1 = new Scanner(System.in);                 //Scanner for user input
        System.out.println("Enter number of X's: ");
        n = keyboard1.nextInt();
        double [] a = new double[2 *n];
        String fileLocation;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the file name:");
        fileLocation = keyboard.nextLine();         //tools to read file
        File file = new File(fileLocation);
        Scanner inputFile = new Scanner(file);
        int e = 0;
        while(inputFile.hasNextDouble())
        {
            a[e] = inputFile.nextDouble();          //get numbers from file
            e++;
        }
        double [] x = new double[n];
        double [] fx = new double[n];       //fill arrays with file numbers
        for(int z = 0 ; z < n;z++)
        {
            x[z] = a[z];
        }
        int f = 0;
        for(int g = n; g <(2*n);g++)
        {
            fx[f] = a[g];
            f++;
        }
        System.out.println("Given Data: "+Arrays.toString(a));
        System.out.println("x: " +Arrays.toString(x));      //print x
        System.out.println("fx: " +Arrays.toString(fx));    //print fx
        double [][] answers = new double[n][n];
        double [][] l = new double[n][n];               //arrays needed for methods
        double [] lag = new double[n];
        DividedDifference(n,x,fx,answers);          //get divided difference table
        System.out.println("Interpolating polynomial is: " );
        InterpolationPolynomial(n,x,answers);           //print interpolating polynomial
        System.out.println("Lagrange polynomial is: " );
        Lagrange(n,x,fx,l,lag);             //get lagrange polynomial
}

    public static void Lagrange(int n, double[] x, double[] fx, double[][] l, double [] lag)
    {
        int i,j,g,k;
        for(j = 0;j < n;j++)
        {
            for (i = 0; i < n; i++)
            {
                l[i][j] = x[i] - x[j];      //subtract x from other x's for each x
            }
        }
        for(g = 0;g < n;g++){
            for(k = 0;k < n;k++)
            {
                if(l[g][k] ==0)
                {
                    l[g][k] = 1;        //fill 0s with 1s so you can multiply the subtracted numbers in the denominator
                }
                lag[k] = 1;
            }
        }
        for(i = 0; i < n;i++)
        {
            for(j = 0; j < n;j++)
            {
                lag[i] *= l[i][j];      //multiply each x
            }
        }
        for(i = 0; i < n;i++)
        {
            lag[i] = 1/ lag[i];     //finish dividing 1/denominator
            lag[i] *= fx[i];        //multiply by fx value for that x
        }
        //System.out.println("lag: " +Arrays.toString(lag));
        ArrayList<String> poly = new ArrayList<String>();       //arraylist builds lagrange polynomial
        String s ="";
        String sign= null;
        for(i = 0; i < n;i++)
        {
            double tempX= x[i];     //get x values
            if(tempX > 0)
            {
                sign = "-";
            }
            else if(tempX < 0){     //get signs for x
                sign = "+";
            }
            if(tempX == 0)
            {
                poly.add(("x"));
            }
            else
            {
                poly.add(String.format("(x%s%.2f)", sign, Math.abs(tempX)));        //add x and sign to arraylist
            }
        }
        StringBuilder finalPoly = new StringBuilder(String.format("%s",""));
        for(i = 0; i < n;i++)
        {
            double tempFx = lag[i];
            if(tempFx != 0)     //if fx is 0 dont add anything
            {
                if(tempFx > 0)      //get fx
                {
                    sign = "+";     //get fx sign
                }
                else
                {
                    sign = "-";
                }
                StringBuilder add = new StringBuilder();        //string builder to build lagrange
               for(j = 0; j < n; j++)
                {
                    if(i != j)      //print (x - fx) for each x not including itself
                    {
                    add.append(poly.get(j));}
                }
                finalPoly.append(String.format(" %s %.2f%s", sign, Math.abs(tempFx), add.toString()));      //add everything
            }
        }
        System.out.println(finalPoly);      //print lagrange polynomial
    }

    public static void InterpolationPolynomial(int n, double[] x, double[][] answers)
    {
        int i,j;
        String sign = null;
        ArrayList<String> poly = new ArrayList<String>();   //use arraylist string to build polynomial
        for(i = 0; i < n;i++)
        {
            double tempX= x[i];         //get x value
            if(tempX > 0)
            {
                sign = "-";             //get sign for that value
            }
            else if(tempX < 0){
                sign = "+";
            }
           if(tempX == 0)
           {
               poly.add(("x"));
           }
           else
           {
               poly.add(String.format("(x%s%.2f)", sign, Math.abs(tempX)));     //add x and the sign to the arraylist
           }
        }
        StringBuilder finalPoly = new StringBuilder(String.format("%.2f", answers[0][0]));  //stringbuilder that prints first fx
        for(i = 1; i < n;i++)
        {
            double tempFx = answers[0][i];      //get c from each column
            if(tempFx != 0)
            {
                if(tempFx > 0)
                {
                    sign = "+";         //get sign for each c
                }
                else
                {
                    sign = "-";
                }
                StringBuilder add = new StringBuilder();        //use string builder to build polynomial
                for(j = 0; j < i; j++)
                {
                    add.append(poly.get(j));            //add arraylist to stringbuilder
                }
                finalPoly.append(String.format(" %s %.2f%s", sign, Math.abs(tempFx), add.toString()));   //add stringbuilder and arraylist
            }
        }
        System.out.println(finalPoly);
    }

    public static void DividedDifference(int n, double[] x, double[] fx, double[][] answers)
    {
      int i,j;
      for(i = 0; i < n;i++)     //fill first row with fx
      {
          answers[i][0]= fx[i];
      }

      for(j = 1; j < n; j++)        //get values for table with equation
      {
          for(i = 0; i < n-j;i++)
          {
              answers[i][j] = (answers[i+1][j-1] - answers[i][j-1]) / (x[i+j] - x[i]);
          }
      }
        System.out.println("answers: " +Arrays.deepToString(answers));  //print filled array
       System.out.println("Divided Differences Table: ");   //print table
        System.out.println("\t   x\t fx\t    f[,]\t f[,,]\t f[,,,] ");
        for(i = 0; i < n ; i++){
            for(j = 0; j < n - i; j++){
                if(j==0)
                {
                    System.out.printf("\t %.2f", x[i]);     //print x column
                }
                System.out.printf("\t %.2f", answers[i][j]);    //print f[,]
            }
            System.out.println();
        }
    }
}



