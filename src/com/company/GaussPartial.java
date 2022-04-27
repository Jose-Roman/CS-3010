package com.company;
import java.util.Scanner;
import java.util.Arrays;
//Jose Roman
//CS 3010
//9-22-20
public class GaussPartial
{
    public static void main(String[] args)
    {
        int n;
        Scanner keyboard = new Scanner(System.in);                 //Scanner for user input
        System.out.println("Enter number of equations: ");
        n = keyboard.nextInt();

        double [][] Ax = new double[n][n]; //Input coefficient array

        for(int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                System.out.println("Enter the "+ (col +1) + " coefficient of the " + (row +1) +" equation: ");
                Ax[row][col] = keyboard.nextInt();
            }
        }


        double [] b = new double[n];
        for(int q = 0; q < n; q++)                   //fill in b values
        {
            System.out.println("Enter the 'b' output for the " +  (q+1) + " equation:");
            b[q] = keyboard.nextInt();
        }
        System.out.print("Ax = ");                            //print starting points
        System.out.println(Arrays.deepToString(Ax));
        System.out.print("b = ");
        System.out.println(Arrays.toString(b));

        int[] L = new int[n];
        double[] X= new double[n];
        Gauss(n,Ax,L);                           //call functions
        Solve(n,Ax,L,b,X);


    }

    public static void Solve(int n, double[][] Ax, int[] L, double[] b, double[] X)
    {

        int i,k,j;
        double sum;
        for(k = 0; k <= n-2;k++)                             //reading b values
        {
            for(i = k+1; i <=n-1;i++)
            {
                b[L[i]] = b[L[i]] - (Ax[L[i]][k] * b[L[k]]);
            }
        }

        X[n-1] = b[L[n-1]] / Ax[L[n-1]][n-1];                //start solving
        System.out.print("While Solving Answer: ");
        System.out.println(Arrays.toString(X));
        for(i = n-1; i>=0; i--)
        {
            sum =  b[L[i]];
            for(j = i+1; j <=n-1;j++)
            {
                sum = sum - (Ax[L[i]][j] * X[j]);
            }

            X[i] = sum / Ax[L[i]][i];                     //find values

        }
        System.out.print("Final Answer: ");             //print final result
        System.out.println(Arrays.toString(X));
    }

    public static void Gauss(int n, double[][] Ax, int[] L)
    {

        int i,k,j=0,z;
        double [] s = new double[n];
        double r,Rmax,xmult,Smax;
        for(i = 0; i <= n-1 ; i++)                      //find Scale vector
        {
            L[i] = i;
            Smax = 0;
            for(j=0; j<= n-1 ; j++)
            {
                Smax = Math.max(Smax,Math.abs(Ax[i][j]));
            }
            s[i] = Smax;
        }
        System.out.print("Scale Vector \ns = ");             //print scale vector
        System.out.println(Arrays.toString(s));
        for(k = 0; k<= n-2;k++)
        {
            Rmax = 0;
            for(i=k;i<=n-1;i++)
            {
                r = Ax[L[i]][k] / s[L[i]];              //partial pivoting
                if(r >Rmax)
                {
                    Rmax = r;
                    j = i;
                }

            }
            z = L[j];
            L[j]=L[k];
            L[j] = z;
            System.out.print("During partial pivoting: ");
            System.out.println(Arrays.deepToString(Ax));
            for(i=k+1;i<=n-1;i++)
            {
                xmult = Ax[L[i]][k] / Ax[L[k]][k];
                Ax[L[i]][k] = xmult;
                for(j=k+1; j<=n-1;j++)
                {
                    Ax[L[i]][j] = Ax[L[i]][j] - (xmult * Ax[L[k]][j]);

                }

            }
            System.out.print("During partial pivoting: ");        //print after pivoting
            System.out.println(Arrays.deepToString(Ax));
        }

    }


}
