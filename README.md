# Polynomdivision
A library to calculate Polynomdivisions

Documentation:
- First, add the Polynomdivision.jar as an external library to your project
- Then you have to import net.bplaced.lakinator.PolynomLib.PolyMain to use the lib methods
- After that you can call 4 methods with this library:
  - PolyMain.getVersion() returns the library version
  - PolyMain.runGui() runs a little JFrame window with that you can directly calculate something and see the output.
    It also contains an Error Logger, if something goes wrong, create an Issue with your Log on my Github page
  - PolyMain.validateInput(String input) checks if the given String is a valid Polynom and returns an Integer:
     - 0: The String is valid
     - 1: The elements in the String have a wrong syntax
     - 2: The order of each exponent is wrong (expo1 > expo2 > expo3)
  - PolyMain.runMainLoop(String polynom1, String polynom2, boolean log) returns the formatted result of the division between polynom1 and polynom2.
    The boolean is for logging

The GUI:

![vorschaubild](https://cloud.githubusercontent.com/assets/21976072/23831925/18405f00-072b-11e7-9927-9d69af3327f8.png)
