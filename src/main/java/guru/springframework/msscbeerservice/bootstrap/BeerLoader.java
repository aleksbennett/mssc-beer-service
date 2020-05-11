package guru.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;

//Run every time the spring context starts
//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234200019";
    public static final String BEER_3_UPC = "0083783375213";

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
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .build());

            beerRepository.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .quantityOnHand(800)
                .minOnHand(150)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("11.8"))
                .build());

            beerRepository.save(Beer.builder()
                .beerName("Interstella")
                .beerStyle("LAGER")
                .quantityOnHand(600)
                .minOnHand(110)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("9.9"))
                .build());
        }
    }
}