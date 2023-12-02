package controller;

//import model.CartRepository; // Import your cart repository class here
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CartView;

public class CartController {

    private final CartView cartView;

    public CartController(CartView cartView) {
        this.cartView = cartView;

        cartView.addCloseCartButtonListener(this::handleCloseButton);
        cartView.addBuyButtonListener(this::handleBuyButton);
    }



    private void handleBuyButton(ActionEvent event){

        cartView.getStage().close();
    }
    private void handleCloseButton(ActionEvent event){

        cartView.getStage().close();
    }
}
