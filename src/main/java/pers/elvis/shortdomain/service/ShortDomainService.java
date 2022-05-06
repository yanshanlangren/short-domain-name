package pers.elvis.shortdomain.service;

import org.springframework.stereotype.Service;
import pers.elvis.shortdomain.bo.Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ShortDomainService {
    /**
     * @key longDomain
     * @value shortDomain
     */
    private Map<String, String> domainMap;

    /**
     * @key shortDomain
     * @value longDomain
     */
    private Map<String, String> seqMap;

    private long seq;

    static long MAX_SEQ = 218340105584896L; // 62^8

    public ShortDomainService(Map<String, String> domainMap, Map<String, String> seqMap, long seq) {
        this.domainMap = domainMap;
        this.seqMap = seqMap;
        this.seq = seq;
    }

    public Optional<Domain> generateShortDomain(Domain domain) {
        String longDomain = domain.getLongDomain();
        // case long domain exist
        if (domainMap.containsKey(longDomain)) {
            domain.setShortDomain(domainMap.get(longDomain));
            return Optional.of(domain);
        }

        // generate short domain and store
        Optional<Long> nextSeq = nextSeq();
        if (nextSeq.isPresent()) {
            String shortDomain = seqToString(nextSeq.get());
            domainMap.put(longDomain, shortDomain);
            seqMap.put(shortDomain, longDomain);
            domain.setShortDomain(shortDomain);
            return Optional.of(domain);
        }

        // no seq
        return Optional.empty();
    }

    public Optional<Domain> getLongDomain(Domain domain) {
        String shortDomain = domain.getShortDomain();
        if (seqMap.containsKey(shortDomain)) {
            domain.setLongDomain(seqMap.get(shortDomain));
            return Optional.of(domain);
        }
        return Optional.empty();
    }

    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    private String seqToString(long seq) {
        StringBuilder sb = new StringBuilder();
        while (seq > 0) {
            sb.append(digits[(int) (seq % 62)]);
            seq /= 62;
        }
        return sb.toString();
    }

    private Optional<Long> nextSeq() {
        long cur = seq;
        do {
            seq++;
            seq = seq % MAX_SEQ;
        } while (seqMap.containsKey(seqToString(seq)) && seq != cur);
        return seq == cur
                ? Optional.empty()
                : Optional.of(seq);
    }

//    public static void main(String[] args) {
//        ShortDomainService service = new ShortDomainService(new HashMap<>(), new HashMap<>(), 10000L);
//        for (long i = 10000L; i < 20000L; i++) {
//            service.seqMap.put(service.seqToString(i), String.valueOf(i));
//        }
//        System.out.println(service.nextSeq());
//        System.out.println(service.nextSeq());
//
////        System.out.println(service.generateShortDomain("http://www.baidu.com/123"));
////        System.out.println(service.generateShortDomain("http://www.baidu.com/123"));
//    }
}
