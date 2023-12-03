package controller.customer;

import javafx.event.ActionEvent;
import view.customer.CartView;

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
