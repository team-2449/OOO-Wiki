class FlowControl {
    public static void main(String[] args) {
        //The code you need to understand starts here

        boolean a = true;
        if(a){ //If whatever is inside the parentheses is true, the first statement will run.  In this case, just a is being tested.
            //Code to run if true
            System.out.println("a is true");
        } else {
            //Code to run if false
            System.out.println("a is false");
        }

        int b = 5;

        if(b == 10 || a){//We can put boolean expressions in here.  Anything that simplifies to a boolean
            System.out.println("b is 10, or a is true");
        } else {
            System.out.println("b is not 10, and a is false");
        }

        int i = 0;
        while(i < 4){  //Run this code until i is not less than 4.
            System.out.println("I is still less than 4");
            i++;  //Increments i by one
        }
    }
}