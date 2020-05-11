package guru.springframework.msscbeerservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		return mapper.beerToBeerDto(beer);
    }
    
    @Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        System.out.println("Fetching beer inventory: " + beer.getBeerName());
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));        
		return dto;
	}

	@Override
	public Beer beerDtoToBeer(BeerDto beer) {
		return mapper.beerDtoToBeer(beer);
	}
}