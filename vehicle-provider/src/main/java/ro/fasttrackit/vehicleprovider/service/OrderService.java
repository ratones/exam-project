package ro.fasttrackit.vehicleprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.WeakCacheKey;
import org.springframework.stereotype.Service;
import ro.fasttrackit.vehicleprovider.message.MessageSender;
import ro.fasttrackit.vehicleprovider.message.WebSocketSender;
import ro.fasttrackit.vehicleprovider.model.Deficiencies;
import ro.fasttrackit.vehicleprovider.model.OrderStatus;
import ro.fasttrackit.vehicleprovider.model.ServiceOrder;
import ro.fasttrackit.vehicleprovider.model.Vehicle;
import ro.fasttrackit.vehicleprovider.repository.DeficienciesRepository;
import ro.fasttrackit.vehicleprovider.repository.ServiceOrderRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ServiceOrderRepository repository;
    private final DeficienciesRepository deficienciesRepository;
    private final MessageSender sender;

    public List<ServiceOrder> getVehicleOrder(Integer id) {
        return repository.findByVehicleId(id);
    }

    public ServiceOrder insertOrder(ServiceOrder order) {
        if(order.getId() != null) {
            order.setId(null);
        }
        order.setStatus(OrderStatus.open);
        repository.save(order);
        return order;
    }

    public ServiceOrder updateOrder(Integer id, ServiceOrder order) {
        ServiceOrder exist = repository.findById(id).orElseThrow(() -> new RuntimeException("could not find record with id=" + id));
        return repository.save(order);
    }

    public ServiceOrder deleteOrder(Integer id) {
        ServiceOrder exist = repository.findById(id).orElseThrow(() -> new RuntimeException("could not find record with id=" + id));
        repository.delete(exist);
        return exist;
    }

    public ServiceOrder sendOrder(Integer id, ServiceOrder order) {
        ServiceOrder exist = repository.findById(id).orElseThrow(() -> new RuntimeException("could not find record with id=" + id));
        order.setStatus(OrderStatus.sent);
        System.out.println(order);
        sender.sendOrderToService(order);
        return repository.save(order);
    }

    public List<Deficiencies> getOrderDeficiencies(Integer id) {
        return deficienciesRepository.findByOrderId(id);
    }

    public Deficiencies insertDeficiency(Deficiencies deficiency) {
        if(deficiency.getId() != null){
            deficiency.setId(null);
        }
        return deficienciesRepository.save(deficiency);
    }

    public Deficiencies deleteDeficiency(Integer id) {
        Deficiencies def = deficienciesRepository.findById(id).orElseThrow(() -> new RuntimeException("could not find record with id=" + id));
        deficienciesRepository.deleteById(id);
        return def;
    }
}
