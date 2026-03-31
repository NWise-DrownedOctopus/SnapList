import controller.CardListController;
import dao.CardDaoImplementation;
import service.CardService;
import view.CardListView;

public class AppUI {

    public static void main(String[] args) {
        CardDaoImplementation dao = new CardDaoImplementation();
        CardService service = new CardService(dao);

        CardListController controller = new CardListController(service);
        CardListView view = new CardListView(controller);

        controller.setView(view);

        view.setVisible(true);
    }    
}
