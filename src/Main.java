import Controller.Controller;
import Model.ChatRoomFacade;
import Model.ChatRoomModel;
import View.ChatRoomView;

public class Main {
    public static void main(String[] args){
        System.out.println("andy changed this again");
        ChatRoomFacade model = new ChatRoomFacade(new ChatRoomModel(1,"a"));
        Controller controller = new Controller(model);
        ChatRoomView view = new ChatRoomView(controller, model);
    }
}
