public class Test {
    public static void test() {
        Election e = new Election("Are fries better with remoulade than ketchup?");
        e.vote('y');
        e.vote('y');
        e.vote('n');
        e.display();
    }

    public static void test21() {
        Election e = new Election(null);
    }

    public static void test22() {
        try {
            test21();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void test3() {
        Election e = new Election("Is McDonalds better than Burger King?");
        e.display();
    }

    public static void test41() {
        Election e = new Election("Is McDonalds better than Burger King?");
        e.vote('x');
    }

    public static void test42() {
        try {
            test41();
        } catch (IllegalVoteException e) {
            System.out.println(e.getMessage() + ": '" + e.getIllegalVote() + "'"); 
        }
    }
}
