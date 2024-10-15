package com.clicked.app.services.pricing;

import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.CreateServiceRequestDto;
import com.clicked.app.dto.pricingdto.GetPricingResponseDto;
import com.clicked.app.dto.pricingdto.GetServicesResponseDto;
import com.clicked.app.models.Pricing;
import com.clicked.app.models.Service;
import com.clicked.app.repositories.IPricingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class PricingService implements IPricingService {
  private IPricingRepository pricingRepository;

  public PricingService(IPricingRepository pricingRepository) {
    this.pricingRepository = pricingRepository;
  }

  @Override
  public boolean createPricing(CreatePricingRequestDto request) {
    List<Service> services = new ArrayList<>();

    Pricing pricing = Pricing.builder()
      .title(request.getTitle())
      .description(request.getDescription())
      .amount(request.getAmount())
      .btnName(request.getBtnName())
      .color(request.getColor())
      .build();

    for (CreateServiceRequestDto serviceRequest : request.getServices()) {
      Service service = Service.builder()
        .serviceName(serviceRequest.getServiceName())
        .included(serviceRequest.isIncluded())
        .pricing(pricing)
        .build();
      services.add(service);
    }

    pricing.setServices(services);

    pricingRepository.save(pricing);

    return true;
  }

  // @Override
  //   public List<PricingResponseDto> getAllPricing() {
  //       List<Pricing> pricingList = pricingRepository.findAll(); // Fetch all pricing records
  //       return pricingList.stream()
  //           .map(this::mapToPricingResponseDto) // Map to DTO
  //           .collect(Collectors.toList());
  //   }

  @Override
  public List<GetPricingResponseDto> getPricing() {
    List<Pricing> pricingList = pricingRepository.findAll();

    return pricingList.stream()
      .map(this::mapToPricingResponseDto)
      .collect(Collectors.toList());
  }

  private GetPricingResponseDto mapToPricingResponseDto(Pricing pricing) {

    List<GetServicesResponseDto> serviceDto = pricing.getServices().stream()
      .map(service -> GetServicesResponseDto.builder()
        .id(service.getId())
        .serviceName(service.getServiceName())
        .included(service.isIncluded())
        .build())
      .collect(Collectors.toList());

    GetPricingResponseDto pricingDto = GetPricingResponseDto.builder()
      .id(pricing.getId())
      .title(pricing.getTitle())
      .description(pricing.getDescription())
      .services(serviceDto)
      .amount(pricing.getAmount())
      .btnName(pricing.getBtnName())
      .color(pricing.getColor())
      .build();

    return pricingDto;
  }
}
