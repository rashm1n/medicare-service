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
    CONSTRAINT fk_prescription_patient_patient_id  FOREIGN KEY(patient_id) REFERENCES patient(id)
);

CREATE TABLE medicinetype (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

INSERT INTO medicinetype(id,name)  VALUES (1,'Pill');
INSERT INTO medicinetype(id,name)  VALUES (2,'Cream');
INSERT INTO medicinetype(id,name)  VALUES (3,'Syrup');

CREATE TABLE medicine (
    id BIGSERIAL PRIMARY KEY,
    medicinetype_id BIGSERIAL,
    name TEXT NOT NULL,
    unit_price FLOAT NOT NULL,
    units INT NOT NULL,
    CONSTRAINT fk_medicine_medicinetype_medicinetype_id  FOREIGN KEY(medicinetype_id) REFERENCES medicinetype(id)
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
    CONSTRAINT fk_prescription_medicine_prescription_id FOREIGN KEY (prescription_id) REFERENCES prescription(id),
    CONSTRAINT fk_prescription_medicine_medicine_id FOREIGN KEY (medicine_id) REFERENCES medicine(id)
);