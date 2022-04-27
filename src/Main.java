//Jose Roman CS3010
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        int n;
        Scanner keyboard = new Scanner(System.in);                 //Scanner for user input
        System.out.println("Enter number of equations: ");
        n = keyboard.nextInt();

        double [][] Ax = new double[n][n];       //Input coefficient array

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
        double error;                                       // get stopping error
        System.out.println("Enter desired stopping error: ");
        error = keyboard.nextDouble();
        double [] x = new double[n];
        for(int f = 0; f< n;f++)
        {
            System.out.println("Enter the " + (f+1)+ " starting solution vector coefficient: " );
            x[f] = keyboard.nextDouble();
        }

        //copy user starting vector
        double[] xC = new double[n];
        for(int o = 0; o < n; o++)
        {
            double y = x[o];
            xC[o] = y;
        }
        System.out.println("Jacobi Method: ");
        Jacobi(Ax,b,x,error);
        System.out.println("------------------------------");
        System.out.println("Gauss Seidel: ");
        Gauss_Seidel(Ax,b,xC,error);
    }

    public static void Gauss_Seidel(double[][] Ax, double[] b, double[] xC, double error)
    {
        int i,j,k;
        int n = b.length;
        int Kmax = 10;

        double delta = 10^-10;
        double diag, sum;
        double[] y = {0,0,0};                   //array needed for copying
        double[] g = new double[n];
        for(k = 0; k <= Kmax-1;k++)
        {
            y=xC;
            for(i=0;i<= n-1;i++)
            {
                sum = b[i];
                diag = Ax[i][i];
                if (Math.abs(diag) < delta)
                {
                    System.out.println("Diagonal Element too small");
                    return;
                }
                for(j=0;j<=i-1;j++)                                         //k-1 equations
                {
                    sum = sum - (Ax[i][j]*xC[j]);
                }
                for(j= i+1; j<=n-1;j++)                         //k equations
                {
                    sum = sum - (Ax[i][j]*xC[j]);
                }
                xC[i] = sum/diag;
            }
            double result1=0,result2=0, norm1=0,norm2=0,norm=0;         //L2 norm solution
            for(int w = 0; w<=n-1;w++)
            {
                double L = xC[w] - g[w];
                double L1 = L * L;
                result1 += L1;                      //add each squared value
                double L2 = xC[w] * xC[w];
                result2 += L2;
            }
            norm1=Math.sqrt(result1);               //take square of top and bottom of error equation
            norm2=Math.sqrt(result2);
            norm = norm1 / norm2;

            System.out.println("Iteration: " +(k+1));               //print iteration, and norm
            System.out.println(Arrays.toString(xC));
            System.out.println("L2 Norm: " + norm);
            if(norm < error)
            {
                System.out.println("Number of iterations needed: " + (k+1));
                System.out.println("Max iterations reached");
                Kmax = k;
            }
            for(int u = 0; u <=n-1;u++)                     //copy x into y for k-1 equations
            {
                double h = xC[u];
                g[u] = h;
            }
        }
    }

    public static void Jacobi(double[][] Ax, double[] b, double[] x, double error)
    {
        int i,j,k;
        int n = b.length;
        int Kmax = 50;                  //max iterations before stopping
        double delta = 10^-10;
        double diag, sum;
        double[] y = {0,0,0};
        for(k = 0; k <= Kmax-1;k++)
        {
            for(i=0;i<= n-1;i++)
            {
                sum = b[i];                     //make sure linear equation can be solved
                diag = Ax[i][i];
                if(Math.abs(diag) < delta)
                {
                    System.out.println("Diagonal Element too small");
                    return;
                }
                for(j = 0; j<=n-1;j++)
                {
                    if(j != i)
                    {
                        sum = sum - (Ax[i][j]*y[j]);                //k-1 equations
                    }
                }
                x[i] = sum/diag;
            }
            double result1=0,result2=0, norm1=0,norm2=0,norm=0;
            for(int w = 0; w<=n-1;w++)
            {
                double L = x[w] - y[w];
                double L1 = L * L;                              //finding norm at each iteration
                result1 += L1;
                double L2 = x[w] * x[w];
                result2 += L2;
            }
            norm1=Math.sqrt(result1);
            norm2=Math.sqrt(result2);
            norm = norm1 / norm2;

            System.out.println("Iteration: " +(k+1));
            System.out.println(Arrays.toString(x));             //print answers
            System.out.println("L2 Norm: " + norm);

            if(norm < error)
            {
                System.out.println("Number of iterations needed: " + (k+1));            //stop program when error is reached
                System.out.println("Max iterations reached");
                Kmax = k;
            }
            for(int c = 0; c <=n-1;c++)                 //copying previous x into y to solve next solution
            {
                double h = x[c];
                y[c] = h;
            }
        }
    }
}
