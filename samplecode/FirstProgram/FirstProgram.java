//This is a single-line comment.  You can tell that because it begins with two forward-slashes.  It also should show up a different color in VSCode.

/*
    This is also a comment.  It is called a multi-line comment.  It is called that because anything between the two symbols that start and end it
    Will be included as a comment.  Comments are used to explain bits of code that might be confusing.  Anything that is a part of a comment will
    not be interpreted as code.  This can be useful, as you will see shortly.
*/

class FirstProgram{  //This line starts a new class.  You will learn what that means in a later lesson

    public static void main(String[] args) {  //This is the entry point for your program.  When you execute this program, it will run line-by-line, starting at "main".
        //Anything between the curly braces will run when main is started.  The "public static void" part will be explained in later lessons, as will the (String[] args) bit

        /*
            A variable is simply a place for us to store data.  There are tons of different data types in Java, but we will focus mainly on numerical ones.
            Java is what is known as "strongly typed", which means that we have to explicitly tell the computer what type of variable we want when we create it.
            Integer-type variables (called int) contain positive and negative numbers without decimal places.
            Double-type variables (called double) countain decimal numbers (like 0.5, or 2.7).  They use more computer resources than integers,
            which is why both types are allowed.  You should use ints when possible, and doubles only when you need them.
            Lines of code in Java end with a semicolon.  
        */

        int inputOne = 5;  //This creates a new integer-type variable named "inputOne" and assigns the value 5 to it.
        int inputTwo = 20;  //This creates another integer-type variable named "inputTwo".  Notice how the names are different.  You can't have two variables with different names.
        int outputOne;  //This creates a third integer-type variable, but assigns no value to it.  We will have to assign a value later.

        outputOne = inputOne + inputTwo;  //This sets tha value of "outputOne" to equal the sum of inputOne and inputTwo
        System.out.println("Sum = " + outputOne);  //This will print the value of outputOne

        outputOne = inputOne - inputTwo;  //This sets tha value of "outputOne" to equal the difference of inputOne and inputTwo
        System.out.println("Difference = " + outputOne);  //This will print the value of outputOne

        outputOne = inputOne * inputTwo;  //This sets tha value of "outputOne" to equal the product of inputOne and inputTwo
        System.out.println("Product = " + outputOne);  //This will print the value of outputOne

        double inputThree = 5;  //This creates a new variable of the double type
        double inputFour = 20;
        double outputTwo;

        outputTwo = inputThree / inputFour;  //This will divide inputThree by inputFour
        System.out.println("Dividend = " + outputTwo); //And this line will display the result



        //I will now show some more math examples in java.
        double numberVariable = 1;  //Creates the number variable to work with.

        numberVariable = numberVariable + 1;  //Adds one to our variable
        numberVariable += 1;  //This also adds one to our variable.  The +=, -=, /= and *= operators modify the variable on the left by the number on the right.
        System.out.println("1 + 1 + 1 = " + numberVariable);

        numberVariable = 27 % 6;  //This is the modulus operator.  The previous operation is pronounced "27 mod 6".  The modulus operator finds the remainder of dividing two numbers.
        System.out.println("27 mod 6 = " + numberVariable);

        //The Math library contains more advanced math functions, like powers, trig functions, Mathematical Constants,  and much more.

        numberVariable = Math.pow(5,3);  //Sets numberVariable equal to 5 to the power of 3.
        System.out.println("5 ^ 3 = " + numberVariable);

        numberVariable = Math.sin(Math.PI/2);  //This takes the sine function of pi/2.
        System.out.println("sine of pi halves = " + numberVariable); 

        //The following sets our variable to area of a hemisphere of radius equal to the starting value of our variable.
        numberVariable = ((Math.PI*Math.pow(numberVariable, 2)*4)/2) + (Math.PI*Math.pow(numberVariable, 2));
        System.out.println("Area of hemisphere of radius 1 = "+numberVariable);
        //It is okay if you don't understand that formula, that part isn't really important.  What is important is to notice how I used parentheses
        //to force the order of operations.  There is an established order of operations in Java, but they are different than what you use in math,
        //so most programmers explicitly force the order using parentheses to make things clearer, and avoid mistakes.

        
        
    } //This curly brace marks the end of main.

}  //This curly braces closes the class.