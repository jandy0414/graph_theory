package cp3;

public class TestBreak {

    public TestBreak(){
//        while (true){
//            System.out.println("struct , break mark01");
//            while (true){
//                System.out.println("struct break mark02");
//                break;
//
//
//            }
//            System.out.println("struct break mark03");
//            break;
//        }
        System.out.println("struct init");
        for(int i=0;i<5;i++) {
            brak();
            System.out.println("struct break mark："+i);

        }

        System.out.println("struct finish");
    }

    private void brak(){
        while (true){
            System.out.println("brak , break mark01");
            while (true){
                System.out.println("brak break mark02");
                break;

            }
            System.out.println("brak break mark03");
            break;
        }
        System.out.println("brak break mark04");
    }

    public static void main(String[] args) {
        System.out.println("hello test break world!!!");
        TestBreak graphDFS = new TestBreak();
    }
}
