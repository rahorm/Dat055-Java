import Controller.Controller;
import Model.ChatRoomFacade;
import View.ChatRoomView;

public class Main {
    public static void main(String[] args){
        System.out.println("andy changed this again");
        ChatRoomFacade model = new ChatRoomFacade();
        Controller controller = new Controller();
        ChatRoomView view = new ChatRoomView(controller, model);
    }
}
