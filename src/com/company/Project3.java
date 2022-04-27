package com.company;
//Jose Roman
public class Project3
{
    public static void main(String[] args)
    {
      double x = 1;
      double fx = 2*Math.pow(x,3)- 11.7*Math.pow(x,2) + 17.7*x - 5;                 //FUNCTIONS
      double gx = x + 10 - x*Math.cosh(50/x);
      double a =0;
      double b =1;
      double E = 0.01;                    //error
      int nmax = 100;
      double delta = 0.01;                  // δ for modified secant method
        System.out.println("Bisection Method:");                     //call methods
        Bisection(0,1,nmax,E); Bisection(1,2,nmax,E); Bisection(3,4,nmax,E);
        //Bisection(120,130,nmax,E);
        System.out.println("False Position Method:");
        FalsePosition(0,1,nmax,E); FalsePosition(1,2,nmax,E); FalsePosition(3,4,nmax,E);
        //FalsePosition(120,130,nmax,E);
        System.out.println("Newton's Method:");
       Newton(1,nmax,E); Newton(2,nmax,E); Newton(4,nmax,E);
        //Newton(130,nmax,E);
        System.out.println("Secant Method:");
        Secant(0,1,nmax,E);Secant(1,2,nmax,E);Secant(3,4,nmax,E);
        //Secant(120,130,nmax,E);
        System.out.println("Modified Secant Method:");
        ModifiedSecant(1,nmax,E,delta);ModifiedSecant(2,nmax,E,delta);ModifiedSecant(4,nmax,E,delta);
        //ModifiedSecant(130,nmax,E,delta);
    }

    public static void ModifiedSecant(double a,int nmax, double E, double delta)
    {
        int n;
        double fa,fb,rError,b,c,fc;             //variables needed
        fa = 2*Math.pow(a,3)- 11.7*Math.pow(a,2) + 17.7*a - 5;
        //fa = a + 10 - a*Math.cosh(50/a);
        System.out.println("n|   Xn               Xn+1               f(x+1)          Error");
        for(n = 0;n<nmax;n++)
        {
            c = a + (a*delta);                                              //c = Xi +  δXi
            fc = 2 * Math.pow(c, 3) - 11.7 * Math.pow(c, 2) + 17.7 * c - 5;
            //fc = c + 10 - c*Math.cosh(50/c);
            b = a - (fa*((delta*a)/(fc-fa)));                               //b = Xi+1 equation
            fb = 2*Math.pow(b,3)- 11.7*Math.pow(b,2) + 17.7*b - 5;
            //fb = b + 10 - b*Math.cosh(50/b);
            rError = Math.abs(b-a)/Math.abs(b);
            System.out.println(n+"| "+a+"   "+b+"   "+ fc+" "+rError);
            if(rError< E)                                           //stop program when error is reached
            {
                System.out.println("Zero = " + b);
                System.out.println("Finished");
                n = nmax;
            }
            a = b;                  //copy b values into a for next iteration
            fa = fb;
        }
    }

    public static void Secant(double a, double b, int nmax, double E)
    {
        int n;
        double fa,fb,c,fc,rError;               //a is Xn-1   b = Xn   c= Xn+1 fc = f(x+1)
        fa = 2*Math.pow(a,3)- 11.7*Math.pow(a,2) + 17.7*a - 5;      //need fx functions
       fb = 2*Math.pow(b,3)- 11.7*Math.pow(b,2) + 17.7*b - 5;
        //fa = a + 10 - a*Math.cosh(50/a);                          //to run the 2nd equation replace the comments
        //fb = b + 10 - b*Math.cosh(50/b);
        System.out.println("n|  Xn-1    Xn             Xn+1               f(x+1)          Error");
        for(n = 0; n < nmax; n++)
        {
            c = b - ((b-a)/(fb-fa))*fb;
            rError = Math.abs(c-b)/Math.abs(c);
            fc = 2 * Math.pow(c, 3) - 11.7 * Math.pow(c, 2) + 17.7 * c - 5;
            //fc = c + 10 - c*Math.cosh(50/c);                      //switch the comment lines for fc for 2nd equation
            System.out.println(n+"| "+a+"   "+b+"   "+ c+"  "+fc+"  "+rError);
            if(rError < E)
            {
                System.out.println("Zero = " + c);
                System.out.println("Finished");
                n = nmax;
            }
            a = b;              //copy b values into a for next loop
            fa = fb;
            b= c;               //copy c values into a
            fb =fc;
        }
    }
    public static void Newton(double x, int nmax, double E)
   {
       int n;
       double fx,fd,rError,c,X1 = x;
       fx = 2*Math.pow(x,3)- 11.7*Math.pow(x,2) + 17.7*x - 5;
      // fx = x + 10 - x*Math.cosh(50/x);
       System.out.println("n|   Xn           Xn+1        F(Xn)                   F'(Xn)                        Error: ");
       for(n = 0; n<nmax;n++)
       {
           fd = 6*Math.pow(x,2) - 22.4*x +17.7;                 //derivative of fx function
          // fd = 1- Math.cosh(50/x) + 50*(Math.sinh(50/x)/x);      //derivative for 2nd function
           c = fx/fd;                                           //c = f(x)/f'(x)
           x = x -c;                                                // x = Xi - f(x)/f'(x)
           rError = Math.abs(x - X1)/Math.abs(x);           //find error
           System.out.println(n+"|   "+X1 +"  "+x+"  "+fx + "    "+fd+"  "+rError);
           if(rError < E)
           {                                            //end loop
               System.out.println("Zero = " + x);
               System.out.println("Finished");
               n = nmax;
           }
           fx = 2*Math.pow(x,3)- 11.7*Math.pow(x,2) + 17.7*x - 5;       //fx used for next iteration
           //fx = x + 10 - x*Math.cosh(50/x);
           X1 = x;                              //copy x for relative error calculation
       }
    }

    public static void FalsePosition(double a, double b, int nmax, double E)
    {
        int n;
        double fa,fb,fc,c=0,c1 = 0,rError;
        fa = 2*Math.pow(a,3)- 11.7*Math.pow(a,2) + 17.7*a - 5;
        fb = 2*Math.pow(b,3)- 11.7*Math.pow(b,2) + 17.7*b - 5;
       // fa = a + 10 - a*Math.cosh(50/a);
        //fb = b + 10 - b*Math.cosh(50/b);

        if(Math.signum(fa)==Math.signum(fb)){           //check if a and b can be used
            System.out.println("A and B are both positive or both negative"+fa+fb);
            return;
        }
        System.out.println("n|   a      b        c      fa       fb         fc         error"); //print table
        for(n = 0; n<nmax;n++)
        {
            c = a - fa*((b-a)/(fb-fa));
            rError = (Math.abs(c-c1))/c;
            fc = 2 * Math.pow(c, 3) - 11.7 * Math.pow(c, 2) + 17.7 * c - 5;
           // fc = c + 10 - c*Math.cosh(50/c);
            System.out.println(n + "|  "+ a +"  " +b+"     " +c+"   " +fa + "   "+fb+  " "+ fc+"    "+rError);//values
            if(rError < E)
            {
                System.out.println("Zero = " + c);          //end loop when error is reached
                System.out.println("Finished");
                n = nmax;
            }
            if(Math.signum(fa) != Math.signum(fc))          //copy c into b if values signs are different
            {
                b = c;
                fb = fc;
            }
            else
            { a = c;
                fa = fc;
            }
            c1 = c;         //copy c into c1 for error used in next iteration
        }
    }

    public static void Bisection(double a, double b, int nmax, double E)
    {
        int n;
        double c=0,fa,fb,fc,bis,rError = 0,c1 = 0;          //c and c1 used for error
        fa = 2*Math.pow(a,3)- 11.7*Math.pow(a,2) + 17.7*a - 5;          //functions for first equation
        fb = 2*Math.pow(b,3)- 11.7*Math.pow(b,2) + 17.7*b - 5;
       // fa = a + 10 - a*Math.cosh(50/a);                      //functions for 2nd equation
        //fb = b + 10 - b*Math.cosh(50/b);
        if(Math.signum(fa)==Math.signum(fb)){               //check if a and b can be used for the bisection method
            System.out.println("F(a) = "+fa+" F(b) = "+fb);
            return;
        }
        bis = b-a;
        System.out.println("n|   a      b      c      fa    fb          fc            error");
        for(n=0 ; n<nmax; n++)
        {
            bis = bis/2;
            c = a + bis;                    //create bisection
            rError = (Math.abs(c-c1))/c;        //find error
           fc = 2 * Math.pow(c, 3) - 11.7 * Math.pow(c, 2) + 17.7 * c - 5;          //function fc
          //  fc = c + 10 - c*Math.cosh(50/c);              //switch comments for 2nd equation
            System.out.println(n + "|  "+ a +"  " +b+"     " +c+"   " +fa + "   "+fb+  " "+ fc+"    "+rError);
            if(rError < E)                          //end loop when error is reached
            {
                System.out.println("Zero = " + c);
                System.out.println("Finished");
                n = nmax;
            }
            if(Math.signum(fa) != Math.signum(fc))      //put c into b if signs are not the same
            { b = c;
                fb = fc;
            }
            else
            { a = c;            //put c into a when signs are the same
                fa = fc;
            }
            c1 = c;         //copy c into c1 to find relative error
        }
    }
}
