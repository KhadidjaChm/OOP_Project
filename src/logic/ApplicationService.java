package logic;

import exceptions.DeadlinePassedException;
import exceptions.DuplicateApplicationException;
import exceptions.InvalidStatusTransitionException;
import exceptions.MaxApplicationReachedException;
import exceptions.UnauthorizedActionException;
import models.Application;
import models.InternshipOffer;
import models.Status;
import models.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationService {


    private static int nextApplicationId = 1;

    // Student applies to an internship offer
    public Application apply(Student student, InternshipOffer offer) {
        if (student == null || offer == null) {
            throw new IllegalArgumentException("Student and offer must not be null.");
        }

        if (student.isBlocked()) {
            throw new UnauthorizedActionException("Blocked student cannot apply to internship offers.");
        }

        if (offer.getDeadline() != null && LocalDate.now().isAfter(offer.getDeadline())) {
            throw new DeadlinePassedException("Cannot apply. The application deadline has passed.");
        }

        if (student.getApplications().size() >= 5) {
            throw new MaxApplicationReachedException("Student cannot have more than 5 applications.");
        }

        if (hasAlreadyAppliedToOffer(student, offer)) {
            throw new DuplicateApplicationException("Student has already applied to this offer.");
        }

        if (hasAcceptedCompanyApplication(student)) {
            throw new UnauthorizedActionException("Student already has an accepted internship and cannot apply again.");
        }

        Application application = new Application(nextApplicationId++, student, offer);
        student.getApplications().add(application);

        return application;
    }

    // Student cancels an application
    public void cancelApplication(Student student, Application application) {
        if (student == null || application == null) {
            throw new IllegalArgumentException("Student and application must not be null.");
        }

        if (application.getStudent() != student) {
            throw new UnauthorizedActionException("Student is not allowed to cancel this application.");
        }

        if (application.getStatus() != Status.PENDING_ADMIN) {
            throw new InvalidStatusTransitionException(
                    "Only applications with PENDING_ADMIN status can be cancelled."
            );
        }

        student.getApplications().remove(application);
    }

    // Return all applications of one student
    public List<Application> getApplicationsByStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null.");
        }

        return new ArrayList<>(student.getApplications());
    }

    // Check if student already applied to this specific offer
    private boolean hasAlreadyAppliedToOffer(Student student, InternshipOffer offer) {
        for (Application application : student.getApplications()) {
            if (application.getOffer() != null && application.getOffer().getId() == offer.getId()) {
                return true;
            }
        }
        return false;
    }

    // Check if student already has final acceptance from a company
    public boolean hasAcceptedCompanyApplication(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null.");
        }

        for (Application application : student.getApplications()) {
            if (application.getStatus() == Status.ACCEPTED_COMPANY) {
                return true;
            }
        }
        return false;
    }
}