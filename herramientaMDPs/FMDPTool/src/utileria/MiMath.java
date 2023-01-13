package utileria;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MiMath {

  // Central Limit Thorem Method

  public static double gaussianWhiteNoise(int N){

         double x=0;
         double u;

         for(int i = 1;i<N; i++){

            u = Math.random();
            x = x + u;
         }

 /* for uniform randoms in [0,1], mu = 0.5 and var = 1/12 */
 /* adjust X so mu = 0 and var = 1 */

         x = x - (double)N/2;               /* set mean to 0 */
         x = x * Math.sqrt(12 / (double)N); /* adjust variance to 1 */

 /*When the algorithm finishes, X will be our unit normal random. */

         return x;
  }

  public static double gaussianWhiteNoise(int N, double mean, double variance){
/* X can be further modified to have a particular mean and variance, e.g.: */
         return (mean + Math.sqrt(variance) * gaussianWhiteNoise(N));
  }

  /**
   * Round a double value to a specified number of decimal
   * places.
   *
   * @param val the value to be rounded.
   * @param places the number of decimal places to round to.
   * @return val rounded to places decimal places.
   */
  public static double round(double val, int places) {
      long factor = (long)Math.pow(10,places);

      // Shift the decimal the correct number of places
      // to the right.
      val = val * factor;

      // Round to the nearest integer.
      long tmp = Math.round(val);

      // Shift the decimal the correct number of places
      // back to the left.
      return (double)tmp / factor;
  }

  /**
   * Round a float value to a specified number of decimal
   * places.
   *
   * @param val the value to be rounded.
   * @param places the number of decimal places to round to.
   * @return val rounded to places decimal places.
   */
  public static float round(float val, int places) {
      return (float)round((double)val, places);
  }


  public static void main(String[] args) {

    // ejemplo de rounding
    double x = 1.23456789;
    float y = 9.87654f;
    double z;
    float w;

    z = round(x,2);
    System.out.println(z);
    z = round(x,5);
    System.out.println(z);

    System.out.println();

    w = round(y,3);
    System.out.println(w);
    w = round(y,0);
    System.out.println(w);

    // ejemplo de ruido Gaussiano
    double mean=10;
    double variance=0.2;

    int N=25;

   System.out.println("ruido para media 0 y varianza 1: "+gaussianWhiteNoise( N));
   System.out.println("ruido: "+gaussianWhiteNoise( N,mean,variance));
  }
}