package logic;

import models.InternshipOffer;

import java.util.ArrayList;
import java.util.List;

public class OfferSearchService {

    // Search offers by keyword in title, description, or domain
    public List<InternshipOffer> searchByKeyword(List<InternshipOffer> offers, String keyword) {
        if (offers == null) {
            throw new IllegalArgumentException("Offers list must not be null.");
        }

        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(offers);
        }

        List<InternshipOffer> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (InternshipOffer offer : offers) {
            if (offer.isActive()
                    && (
                    offer.getTitle().toLowerCase().contains(lowerKeyword)
                            || offer.getDescription().toLowerCase().contains(lowerKeyword)
                            || offer.getDomain().toLowerCase().contains(lowerKeyword)
            )) {
                result.add(offer);
            }
        }

        return result;
    }

    // Filter offers by company name
    public List<InternshipOffer> filterByCompany(List<InternshipOffer> offers, String companyName) {
        if (offers == null) {
            throw new IllegalArgumentException("Offers list must not be null.");
        }

        if (companyName == null || companyName.trim().isEmpty()) {
            return new ArrayList<>(offers);
        }

        List<InternshipOffer> result = new ArrayList<>();
        String lowerCompanyName = companyName.toLowerCase();

        for (InternshipOffer offer : offers) {
            if (offer.isActive()
                    && offer.getCompany() != null
                    && offer.getCompany().getName().toLowerCase().contains(lowerCompanyName)) {
                result.add(offer);
            }
        }

        return result;
    }

    // Filter offers by domain
    public List<InternshipOffer> filterByDomain(List<InternshipOffer> offers, String domain) {
        if (offers == null) {
            throw new IllegalArgumentException("Offers list must not be null.");
        }

        if (domain == null || domain.trim().isEmpty()) {
            return new ArrayList<>(offers);
        }

        List<InternshipOffer> result = new ArrayList<>();
        String lowerDomain = domain.toLowerCase();

        for (InternshipOffer offer : offers) {
            if (offer.isActive()
                    && offer.getDomain().toLowerCase().contains(lowerDomain)) {
                result.add(offer);
            }
        }

        return result;
    }

    // Filter offers by location
    public List<InternshipOffer> filterByLocation(List<InternshipOffer> offers, String location) {
        if (offers == null) {
            throw new IllegalArgumentException("Offers list must not be null.");
        }

        if (location == null || location.trim().isEmpty()) {
            return new ArrayList<>(offers);
        }

        List<InternshipOffer> result = new ArrayList<>();
        String lowerLocation = location.toLowerCase();

        for (InternshipOffer offer : offers) {
            if (offer.isActive()
                    && offer.getLocation().toLowerCase().contains(lowerLocation)) {
                result.add(offer);
            }
        }

        return result;
    }
}