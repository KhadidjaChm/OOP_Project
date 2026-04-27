package logic;

import exceptions.UnauthorizedActionException;
import models.Company;
import models.InternshipOffer;

import java.time.LocalDate;

public class OfferService {

    private static int nextOfferId = 1;

    // Company creates a new internship offer
    public InternshipOffer createOffer(
            Company company,
            String title,
            String description,
            String domain,
            String location,
            String requiredSkills,
            int durationWeeks,
            double salary,
            LocalDate deadline
    ) {
        if (company == null) {
            throw new IllegalArgumentException("Company must not be null.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must not be empty.");
        }

        if (domain == null || domain.trim().isEmpty()) {
            throw new IllegalArgumentException("Domain must not be empty.");
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location must not be empty.");
        }

        if (requiredSkills == null || requiredSkills.trim().isEmpty()) {
            throw new IllegalArgumentException("Required skills must not be empty.");
        }

        if (durationWeeks <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }

        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }

        if (deadline == null) {
            throw new IllegalArgumentException("Deadline must not be null.");
        }

        InternshipOffer offer = new InternshipOffer(
                nextOfferId++,
                title,
                description,
                domain,
                location,
                requiredSkills,
                durationWeeks,
                salary,
                deadline,
                company
        );

        company.getOffers().add(offer);

        return offer;
    }

    // Company edits one of its own offers
    public void editOffer(
            Company company,
            InternshipOffer offer,
            String title,
            String description,
            String domain,
            String location,
            String requiredSkills,
            int durationWeeks,
            double salary,
            LocalDate deadline
    ) {
        if (company == null || offer == null) {
            throw new IllegalArgumentException("Company and offer must not be null.");
        }

        if (offer.getCompany().getId() != company.getId()) {
            throw new UnauthorizedActionException("This company is not allowed to edit this offer.");
        }

        offer.setTitle(title);
        offer.setDescription(description);
        offer.setDomain(domain);
        offer.setLocation(location);
        offer.setRequiredSkills(requiredSkills);
        offer.setDurationWeeks(durationWeeks);
        offer.setSalary(salary);
        offer.setDeadline(deadline);
    }

    // Company deletes one of its own offers
    public void deleteOffer(Company company, InternshipOffer offer) {
        if (company == null || offer == null) {
            throw new IllegalArgumentException("Company and offer must not be null.");
        }

        if (offer.getCompany().getId() != company.getId()) {
            throw new UnauthorizedActionException("This company is not allowed to delete this offer.");
        }

        company.getOffers().remove(offer);
        offer.setActive(false);
    }
}