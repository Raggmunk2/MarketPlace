package client.model;

import shared.Order;

import java.util.ArrayList;

public class Inbox {
    private ArrayList<Order> ordersToConfirm;

    public Inbox(){
        ordersToConfirm = new ArrayList<>();
    }

    public void updateOrdersToConfirm(ArrayList<Order> waitingOrders){
        for(Order waitingOrder: waitingOrders){
            ordersToConfirm.add(waitingOrder);
        }
    }

    public ArrayList<Order> getOrdersToConfirm() {
        return ordersToConfirm;
    }
}
