package hcmute.spkt.nguyenphucan19110321.uidesign.event;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;

public interface IAddToCartListener {
    void AddToCart(Food food);
}
