package pers.elvis.shortdomain.builder;

import pers.elvis.shortdomain.bo.Domain;

public class DomainBuilder {
    String shortDomain = null;
    String longDomain = null;

    public DomainBuilder setShortDomain(String shortDomain) {
        this.shortDomain = shortDomain;
        return this;
    }

    public DomainBuilder setLongDomain(String longDomain) {
        this.longDomain = longDomain;
        return this;
    }

    public Domain build() {
        Domain domain = new Domain();
        if (shortDomain != null)
            domain.setShortDomain(shortDomain);
        if (longDomain != null)
            domain.setLongDomain(longDomain);
        return domain;
    }
}
