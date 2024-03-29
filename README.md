# Polynomial Division
A library for polynomial division

> Originally programmed in 2017 for school

### API Documentation:
- First, add the Polynomdivision.jar as an external library to your project
- Then you have to import the class PolynomMain to use the lib methods and you also need the InvalidPolynomException if a polynom is invalid:

  ```Java
    import de.lakinator.polynomialdivision.PolynomMain;
    import de.lakinator.polynomialdivision.InvalidPolynomException;
  ```
 #### After that you can call 3 static methods with this library:
  - *PolynomMain.getVersion()* returns the library version:
  
    ```Java
    System.out.println("Library Version: " + PolynomMain.getVersion());
    ```
  - *PolynomMain.validateInput(String... input)* checks if the given strings are valid polynoms and returns true if they are valid, but if one of them is invalid it throws an **InvalidPolynomException**:
  
    ```Java
    String polynom1 = "3x^2-4x+2";
    String polynom2 = "x-1";
    
    try {
      if(PolynomMain.validateInput(polynom1, polynom2)) {
        //See calculation below
      }
    } catch(InvalidPolynomException e) {
      //The error message
      System.out.println(e.getLocalizedMessage());
      //The index of the invalid polynom
      System.out.println(e.getIndexOfInvalidPolynom());
      //Or print them out together
      System.out.println(e.toString());
    }
    ```
     > Note: If you don't validate your strings before you divide them, the variable that will be used in the output from calculation is default *x*. In certain circumstances the calculation step can throw an exception if one of the two Strings have a wrong polynom syntax
  - *PolynomMain.calculate(String polynom1, String polynom2, boolean fullOutput)* returns the result as a String: 
  
    ```Java
    //After the validation!
    String output = PolynomMain.calculate(polynom1, polynom2, true);
    System.out.println(output);
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
