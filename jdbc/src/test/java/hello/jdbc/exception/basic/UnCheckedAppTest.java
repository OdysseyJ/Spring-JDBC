package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class UnCheckedAppTest {

    @Test
    void checked(){
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(controller::request).isInstanceOf(RuntimeSQLExcetipn.class);
    }

    static class Controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }

    }
    static class NetworkClient{
       public void call(){
           throw new RuntimeConnectExcetipn("예외");
       }
    }
    static class Repository{
        public void call() {
            try{
                runSQL();
            } catch (SQLException e){
                throw new RuntimeSQLExcetipn(e);
            }
        }

        public void runSQL() throws SQLException{
            throw new SQLException();
        }
    }

    static class RuntimeConnectExcetipn extends RuntimeException{
        public RuntimeConnectExcetipn(String message) {
                super(message);
        }
    }

    static class RuntimeSQLExcetipn extends RuntimeException{
        public RuntimeSQLExcetipn(Throwable cause) {
            super(cause);
        }
    }
}
