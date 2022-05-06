package pers.elvis.shortdomain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pers.elvis.shortdomain.bo.Domain;
import pers.elvis.shortdomain.builder.DomainBuilder;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShortDomainServiceTest {

    private ShortDomainService service;

    @BeforeEach
    public void setUp() {
        service = new ShortDomainService(new HashMap<>(), new HashMap<>(), 3521614606208L);
    }

    @Test
    public void generateShortDomainTest() {
        Domain domain = new DomainBuilder().setLongDomain("www.baidu.com").build();
        assertThat(service.generateShortDomain(domain).get().getLongDomain() != null);
    }

    @Test
    public void getLongDomainTest() {
        String longDomain = "www.baidu.com";
        Domain domain = new DomainBuilder().setLongDomain(longDomain).build();
        Optional<Domain> resp = service.generateShortDomain(domain);
        System.out.println(resp.get().getShortDomain());
        assertThat(resp.isPresent());
        String shortDomain = resp.get().getShortDomain();
        assertThat(service.getLongDomain(new DomainBuilder().setShortDomain(shortDomain).build()).get().getLongDomain().equals(longDomain));
    }
}
