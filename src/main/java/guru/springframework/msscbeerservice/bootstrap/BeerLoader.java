package guru.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;

//Run every time the spring context starts
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository){
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }    

    public void loadBeerObjects(){
        if( beerRepository.count() == 0 ){
            beerRepository.save(Beer.builder()
                .beerName("Punk IPA")
                .beerStyle("IPA")
                .quantityOnHand(1000)
                .minOnHand(200)
                .upc(33019273848323L)
                .price(new BigDecimal("12.95"))
                .build());

            beerRepository.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .quantityOnHand(800)
                .minOnHand(150)
                .upc(33019273848324L)
                .price(new BigDecimal("11.8"))
                .build());
        }
    }
}