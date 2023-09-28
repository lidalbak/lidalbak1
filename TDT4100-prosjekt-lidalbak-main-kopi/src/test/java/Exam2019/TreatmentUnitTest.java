package Exam2019;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreatmentUnitTest {
    private TreatmentUnit treatmentUnit; 
    @BeforeEach
    public void setup(){
        treatmentUnit=new TreatmentUnit(); 
    }

    @Test
    public void testAddDoctorsPatient(){
        final Doctor doctor1=new Doctor("flu"); 
        final Doctor doctor2=new Doctor("noseblead","pneumonia");
        treatmentUnit.addDoctor(doctor1); 
        treatmentUnit.addDoctor(doctor2);
        assertEquals(true, doctor1.isAviable()); 
        assertEquals(true, doctor2.isAviable()); 

        final Patient patient=new Patient(); 
        patient.addCondition("flu","noseblead");
        treatmentUnit.addPatient(patient);
        assertEquals(1, treatmentUnit.getAvailableDoctors().size());
        Doctor patientDoctor=treatmentUnit.getDoctor(patient); 
        patientDoctor.treat();
        treatmentUnit.treatmentFinished(patientDoctor);
        assertEquals(true, patientDoctor.isAviable());
        assertEquals(1, treatmentUnit.getAvailableDoctors().size());
        

        patientDoctor=treatmentUnit.getDoctor(patient); 
        patientDoctor.treat();
        treatmentUnit.treatmentFinished(patientDoctor);
        assertEquals(true, doctor1.isAviable()); 
        assertEquals(true, doctor2.isAviable());        
    }

}
