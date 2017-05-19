# Polynomdivision
A library to calculate polynomdivisions, you can find the latest compiled version [here](https://github.com/Lakinator/Polynomdivision/tree/master/compiled)

### API Documentation:
- First, add the Polynomdivision.jar as an external library to your project
- Then you have to import Polynomdivision.PolynomMain to use the lib methods:

  ```Java
  import Polynomdivision.PolynomMain;
  ```
 #### After that you can call 3 static methods with this library:
  - *PolynomMain.getVersion()* returns the library version:
  
    ```Java
    System.out.println("Library Version: " + PolynomMain.getVersion());
    ```
  - *PolynomMain.validateInput(String... input)* checks if the given strings are valid polynoms and returns an integer:
  
    ```Java
    String polynom1 = "3x^2-4x+2";
    String polynom2 = "x-1";
    int valid = PolynomMain.validateInput(polynom1, polynom2);
    ```
     | 0 | 1 | 2 |
     |---|---|---|
     |Valid|Invalid|Invalid|
     |All strings are valid|The elements in one of the strings have a wrong syntax|More than one variable was used (only x's or y's etc. but not various!)|
     > Note: If you don't validate your strings before you divide them, the variable that will be used in the output from calculation is default *x*. In certain circumstances the calculation step can throw an exception if one of the two Strings have a wrong polynom syntax
  - *PolynomMain.calculate(String polynom1, String polynom2, boolean fullOutput)* returns the result as a String: 
  
    ```Java
    String polynom1 = "3x^2-4x+2";
    String polynom2 = "x-1";
    
    int valid = PolynomMain.validateInput(polynom1, polynom2);
    
    if(valid == 0) {
      String output = PolynomMain.calculate(polynom1, polynom2, true);
      System.out.println(output);
    }
    ```
    |fullOutput == true|fullOutput == false|
    |---|---|
    |returns the ***fully formatted*** result of the division between polynom1 and polynom2|returns ***only*** the result of the division between polynom1 and polynom2|
    
     ```
     fullOutput == true:
     
     +3x^2-4x+2 : +x-1 = +3x-1 + (+1/+x-1)
     -(+3x^2-3x)
     ------
     -x+2
     -(-x+1)
     ------
     +1
     
     
     fullOutput == false:
     
     +3x-1 + (+1/+x-1)
    
     ```
