# Polynomdivision
A library to calculate Polynomdivisions

Documentation:
- First, add the Polynomdivision.jar as an external library to your project
- Then you have to import net.bplaced.lakinator.PolynomLib.PolyMain to use the lib methods
- After that you can call 4 static methods with this library:
  - PolyMain.getVersion() returns the library version
  - PolyMain.runGui() runs a little JFrame window with that you can directly calculate something and see the output.
    It also contains an Error Logger, if something goes wrong, create an Issue with your Log on my Github page
    BUT: The Gui is currently outdated. So only use it in certain crcumstances!
  - PolyMain.validateInput(String... input) checks if the given Strings are valid Polynoms and returns an Integer:
     - 0: The Strings are valid
     - 1: The elements in one of the Strings have a wrong syntax -> Invalid
     - 2: More than one variable was used (only x's or y's etc. but not various) -> Invalid
  - PolyMain.calculate(String polynom1, String polynom2, boolean fullOutput) returns 
    if fullOutput is true the formatted result of the division between polynom1 and polynom2 or 
    if fullOutput is false only the result.
    Note: PolyMain.runMainLoop(String polynom1, String polynom2, boolean fullOutput) still works but shouldn't be used anymore

The (outdated) Gui:

![vorschaubild](https://cloud.githubusercontent.com/assets/21976072/23831925/18405f00-072b-11e7-9927-9d69af3327f8.png)
