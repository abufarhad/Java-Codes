/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package big.power.by.biginteger;

/**
 *
 * @author HP
 */
public class BigPowerByBiginteger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    int bitLength = 512; // 512 bits
    SecureRandom rnd = new SecureRandom();
    int certainty = 90; // 1 - 1/2(90) certainty
    System.out.println("BitLength : " + bitLength);
    BigInteger mod = new BigInteger(bitLength, certainty, rnd);
    BigInteger exponent = BigInteger.probablePrime(bitLength, rnd);
    BigInteger n = BigInteger.probablePrime(bitLength, rnd);

    BigInteger result = n.modPow(exponent, mod);
    System.out.println("Number ^ Exponent MOD Modulus = Result");
    System.out.println("Number");
    System.out.println(n);
    System.out.println("Exponent");
    System.out.println(exponent);
    System.out.println("Modulus");
    System.out.println(mod);
    System.out.println("Result");
    System.out.println(result);
  }
}