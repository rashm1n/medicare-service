CREATE TABLE patient (
    patient_id BIGSERIAL PRIMARY KEY,
    patient_name TEXT NOT NULL,
    age INT NOT NULL,
    gender TEXT NOT NULL,
    created_date DATE
);

CREATE TABLE prescription (
    prescription_id BIGSERIAL PRIMARY KEY,
    patient_id BIGSERIAL,
    date DATE NOT NULL,
    diagnosis TEXT,
    processed BOOLEAN,
    CONSTRAINT fk_prescription_patient_patient_id  FOREIGN KEY(patient_id) REFERENCES patient(patient_id)
);

CREATE TABLE medicinetype (
    medicinetype_id BIGSERIAL PRIMARY KEY,
    type TEXT NOT NULL
);

INSERT INTO medicinetype(medicinetype_id,type)  VALUES (1,'Pill');
INSERT INTO medicinetype(medicinetype_id,type)  VALUES (2,'Cream');
INSERT INTO medicinetype(medicinetype_id,type)  VALUES (3,'Syrup');

CREATE TABLE medicine (
    medicine_id BIGSERIAL PRIMARY KEY,
    medicinetype_id BIGSERIAL,
    medicine_name TEXT NOT NULL,
    unit_price FLOAT NOT NULL,
    units INT NOT NULL,
    CONSTRAINT fk_medicine_medicinetype_medicinetype_id  FOREIGN KEY(medicinetype_id) REFERENCES medicinetype(medicinetype_id)
);

CREATE TABLE prescription_medicine (
    prescription_id INT NOT NULL,
    medicine_id INT NOT NULL,
    dose TEXT,
    duration INT,
    frequency INT,
    quantity INT,
    additional_info TEXT,
    PRIMARY KEY (prescription_id,medicine_id),
    CONSTRAINT fk_prescription_medicine_prescription_id FOREIGN KEY (prescription_id) REFERENCES prescription(prescription_id),
    CONSTRAINT fk_prescription_medicine_medicine_id FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);