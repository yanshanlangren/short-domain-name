package pers.elvis.shortdomain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.elvis.shortdomain.bo.Domain;
import pers.elvis.shortdomain.service.ShortDomainService;

import java.util.Optional;

@RestController
@RequestMapping("/shortDomain")
@Validated
public class ShortDomainController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ShortDomainController.class);

    private final ShortDomainService shortDomainService;

    public ShortDomainController(ShortDomainService shortDomainService) {
        this.shortDomainService = shortDomainService;
    }

    @PostMapping("/store")
    public ResponseEntity store(@RequestBody Domain domain) {
        Optional<Domain> shortDomain = shortDomainService.generateShortDomain(domain);
        return shortDomain.isPresent()
                ? ResponseEntity.ok(domain)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/read")
    public ResponseEntity read(@RequestBody Domain domain) {
        Optional<Domain> longDomain = shortDomainService.getLongDomain(domain);
        return longDomain.isPresent()
                ? ResponseEntity.ok(longDomain.get())
                : ResponseEntity.notFound().build();

    }
}
