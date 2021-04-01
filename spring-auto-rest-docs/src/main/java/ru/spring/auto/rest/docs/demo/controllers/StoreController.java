package ru.spring.auto.rest.docs.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ru.ryabov.swagger_library.rest.api.StoreApi;
import ru.ryabov.swagger_library.rest.model.Order;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Map;
import java.util.Optional;

public class StoreController implements StoreApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> deleteOrder(@Min(1L) Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Integer>> getInventory() {
        return null;
    }

    @Override
    public ResponseEntity<Order> getOrderById(@Min(1L) @Max(10L) Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<Order> placeOrder(@Valid Order body) {
        return null;
    }
}
