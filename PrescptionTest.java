package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrescptionTest {

			@Test
		    void testAddPrescription_validInputs() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertTrue(prescription1.addPrescription(), "Valid prescription 1 should be added successfully.");

		        Prescription prescription2 = new Prescription(2, "Nandini", "Modi", "12 Souvenir Street, Tarneit VIC, 3029", 15.0f, 1.0f, 100, "10/10/24", "Dr.DeepakSingla");
		        assertTrue(prescription2.addPrescription(), "Valid prescription 2 should be added successfully.");
		    }

		    @Test
		    void testAddPrescription_FirstName() {
		        Prescription prescription1 = new Prescription(1, "She", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid first name 'She' should cause failure.");

		        Prescription prescription2 = new Prescription(2, "HimaniHimaniHimani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription2.addPrescription(), "Invalid first name 'HimaniHimaniHimani' should cause failure.");
		    }

		    @Test
		    void testAddPrescription_LastName() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Her", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid last name 'Her' should cause failure.");

		        Prescription prescription2 = new Prescription(2, "Himani", "SinglaSinglaSing", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription2.addPrescription(), "Invalid last name 'SinglaSinglaSing' should cause failure.");
		    }

		    @Test
		    void testAddPrescription_Address() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Singla", "7 Sundial, Tarneit", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid address should cause failure.");

		        Prescription prescription2 = new Prescription(2, "Himani", "Singla", "0123456789987654321", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription2.addPrescription(), "Invalid short address should cause failure.");
		    }

		    @Test
		    void testAddPrescription_SphereCylinderAxis() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -21f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid sphere value (-21) should cause failure.");

		        Prescription prescription2 = new Prescription(2, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", 21f, 2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription2.addPrescription(), "Invalid sphere value (+21) should cause failure.");
		    }

		    @Test
		    void testAddPrescription_Date() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "32/12/24", "Dr.Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid date '32/12/24' should cause failure.");

		        Prescription prescription2 = new Prescription(2, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "00/12/24", "Dr.Nandini");
		        assertFalse(prescription2.addPrescription(), "Invalid date '00/12/24' should cause failure.");
		    }

		    @Test
		    void testAddPrescription_Optometrist() {
		        Prescription prescription1 = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Nandini");
		        assertFalse(prescription1.addPrescription(), "Invalid optometrist name 'Nandini' should cause failure.");

		        Prescription prescription2 = new Prescription(2, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.NandiniSinglaeyespecialist");
		        assertFalse(prescription2.addPrescription(), "Invalid optometrist name 'Dr.NandiniSinglaeyespecialist' should cause failure.");
		    }
		    
		    @Test
		    void testAddRemark_oneRemark() {
		        // Testing with a single valid remark
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertTrue(prescription.addRemark("This is a valid remark input to add", "Client"), "Valid remark for 'Client' should be added successfully.");
		    }

		    @Test
		    void testAddRemark_twoRemarks() {
		        // Testing with two valid remarks
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertTrue(prescription.addRemark("This is a valid remark input to add", "Client"), "First valid remark should be added.");
		        assertTrue(prescription.addRemark("Remark can be added to the addremark as input", "Optometrist"), "Second valid remark should be added.");
		    }

		    @Test
		    void testAddRemark_RemarkShort() {
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription.addRemark("This is a Valid remark", "Client"), "Remark with less than 6 words should not be added.");
		        assertFalse(prescription.addRemark("Definitely an invalid remark", "Optometrist"), "Remark with less than 6 words should not be added.");
		    }

		    @Test
		    void testAddRemark_RemarkLong() {
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        String longRemark = "This is a valid remark input to add. This is a valid remark input to add This is a valid remark input to add This is a valid remark input to add";
		        assertFalse(prescription.addRemark(longRemark, "Client"), "Remark with more than 20 words should not be added.");
		        
		        longRemark = "Remark can be added to the addremark as input. Remark can be added to the addremark as input. Remark can be added to the addremark as input.";
		        assertFalse(prescription.addRemark(longRemark, "Optometrist"), "Remark with more than 20 words should not be added.");
		    }

		    @Test
		    void testAddRemark_Lowercase() {
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription.addRemark("this is a valid remark input to add", "Client"), "Remark starting with a lowercase letter should not be added.");
		        assertFalse(prescription.addRemark("remark can be added to the addremark as input", "Optometrist"), "Remark starting with a lowercase letter should not be added.");
		    }

		    @Test
		    void testAddRemark_Category() {
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        assertFalse(prescription.addRemark("This is a valid remark input to add", "Doctor"), "Invalid category 'Doctor' should not be allowed.");
		        assertFalse(prescription.addRemark("Remark can be added to the addremark as input", "Eyecare"), "Invalid category 'Eyecare' should not be allowed.");
		    }

		    @Test
		    void testAddRemark_ThreeRemarks() {
		        Prescription prescription = new Prescription(1, "Himani", "Singla", "7 Sundial Blvd, Tarneit VIC, 3029", -1.5f, -2.2f, 90, "05/12/24", "Dr.Nandini");
		        prescription.addRemark("This is a valid remark input to add", "Client");
		        prescription.addRemark("Remark can be added to the addremark as input", "Optometrist");
		        assertFalse(prescription.addRemark("Adding the third remark in the database.", "Client"), "A third remark should not be added.");
		    }
		    
		    
}

