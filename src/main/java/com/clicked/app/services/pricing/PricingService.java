package com.clicked.app.services.pricing;

import com.clicked.app.dto.pricingdto.CreatePricingRequestDto;
import com.clicked.app.dto.pricingdto.CreateServiceRequestDto;
import com.clicked.app.dto.pricingdto.PricingDto;
import com.clicked.app.dto.pricingdto.ServiceDto;
import com.clicked.app.models.Pricing;
import com.clicked.app.models.Service;
import com.clicked.app.repositories.IPricingRepository;

import jakarta.persistence.EntityNotFoundException;

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

  @Override
  public List<PricingDto> getPricing() {
    List<Pricing> pricingList = pricingRepository.findAll();

    return pricingList.stream()
      .map(this::mapToPricingDto)
      .collect(Collectors.toList());
  }

  @Override
  public PricingDto updatePricing(PricingDto request) {
    Pricing pricing = pricingRepository.findById(request.getId())
      .orElseThrow(() -> new EntityNotFoundException("Pricing not found"));

    pricing.setTitle(request.getTitle());
    pricing.setDescription(request.getDescription());
    pricing.setAmount(request.getAmount());
    pricing.setBtnName(request.getBtnName());
    pricing.setColor(request.getColor());

    List<Long> existingServicesIds = pricing.getServices().stream()
      .map(Service::getId)
      .collect(Collectors.toList());

    for (ServiceDto serviceDto : request.getServices()) {
      if (existingServicesIds.contains(serviceDto.getId())) {
        for (Service service : pricing.getServices()) {
          if (service.getId() == serviceDto.getId()) {
            service.setServiceName(serviceDto.getServiceName());
            service.setIncluded(serviceDto.isIncluded());
            break;
          }
        }
      } else {
        Service newService = Service.builder()
          .serviceName(serviceDto.getServiceName())
          .included(serviceDto.isIncluded())
          .build();
        pricing.getServices().add(newService);
      }
    }

    // Will delete the data if the service is no longer incluided in the request
    pricing.getServices().removeIf(service ->
      !request.getServices().stream()
        .map(ServiceDto::getId)
        .collect(Collectors.toList())
        .contains(service.getId())
    );

    pricingRepository.save(pricing);

    return mapToPricingDto(pricing);
  }

  @Override
  public boolean deletePricing(long id) {
    Pricing pricing = pricingRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Pricing not found"));

    pricingRepository.delete(pricing);

    return true;
  }

  private PricingDto mapToPricingDto(Pricing pricing) {

    List<ServiceDto> serviceDto = pricing.getServices().stream()
      .map(service -> ServiceDto.builder()
        .id(service.getId())
        .serviceName(service.getServiceName())
        .included(service.isIncluded())
        .build())
      .collect(Collectors.toList());

    PricingDto pricingDto = PricingDto.builder()
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
