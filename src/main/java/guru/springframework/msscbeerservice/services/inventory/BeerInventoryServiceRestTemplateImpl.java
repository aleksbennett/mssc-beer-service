package guru.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = true)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {
    
    private final String INVENORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;

    private String beerInventoryServiceHost;

    public BeerInventoryServiceRestTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }    
    
    @Override
    public Integer getOnHandInventory(UUID beerId) {
        
        log.debug("Calling Inventory Service");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
            .exchange(beerInventoryServiceHost + INVENORY_PATH, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);
        
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
            .stream()
            .mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();

        return onHand;
    }    
}