CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INT NOT NULL,
    gender TEXT NOT NULL,
    created_date DATE
);

CREATE TABLE prescription (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGSERIAL,
    date DATE NOT NULL,
    diagnosis TEXT,
    processed BOOLEAN,
    CONSTRAINT FK_PatientPrescription  FOREIGN KEY(patient_id) REFERENCES patient(id)
);