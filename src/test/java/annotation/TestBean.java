package annotation;

public class TestBean {

    public TestBean(){
        System.out.println("TestBean 构造方法执行。。");
    }

    public void sayhello() {
        System.out.println("Bean  Hi !!");
    }

    public void start(){
        System.out.println("start init Bean");
    }

    public void destroy(){
        System.out.println("destroy Bean");
    }
}
